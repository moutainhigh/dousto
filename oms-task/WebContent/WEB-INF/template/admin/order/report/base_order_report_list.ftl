<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<#-- security.tld 角色权限控制 -->
<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单列表 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" /> 
<script src="${base}/scripts/list.js"></script>     
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

$().ready( function() {
	$('#updateOrder').click(function(){
	        alert("updateOrder");
	});
});

function orderSearch(){
	var form = document.getElementById("listForm");
	form.action="base_order_report!search.action";
	$('#pageNumber').val(0);
	form.submit();
}

function downExcel(){
    var form1 = document.getElementById("listForm");
    form1.action="base_order_report_export_excel.action";
	form1.submit();
}
</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>订单基础数据报表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="base_order_report!search.action" method="post" >
	    	<input type="hidden" name="column" value="${column}" />
	    	<div id="MainTable">
			<div class="input">
				<input id="status" type="hidden" name="pager.queryMap.status" value="" >
			</div>		
			<div class="operateBar">
				<label>每页显示:</label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10" <#if pager.pageSize == 10>selected="selected" </#if>>
						10
					</option>
					<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
						20
					</option>
					<option value="50" <#if pager.pageSize == 50>selected="selected" </#if>>
						50
					</option>
					<option value="100" <#if pager.pageSize == 100>selected="selected" </#if>>
						100
					</option>
				</select>
			</div>	    	
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<td>下单日期：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderReport.orderTimeFrom" value="${(orderReport.orderTimeFrom?string("yyyy-MM-dd"))!}"></td>
					<td>下单日期：至 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderReport.orderTimeTo" value="${(orderReport.orderTimeTo?string("yyyy-MM-dd"))!}"></td>				
					<td>订单类型：&nbsp;&nbsp;&nbsp;<select name="orderReport.orderType">
							<#list orderTypeList as list>
								<option value="${list.dicCode}"<#if list.dicCode == orderReport.orderType> selected </#if>>
									<#if list.dicCode == "">
									全部
									<#else>
										${list.dicName}
									</#if>
								</option>
							</#list>
					</select>
					</td>
					<td>子订单号：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="orderSubNo" name="orderReport.orderSubNo" value="${orderReport.orderSubNo!}" /></td>
					<!--<td>外部渠道订单号：&nbsp;<input type="text" id="aliasOrderNo" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}" /></td> -->
				</tr>
				<tr>
				    <td>外部订单号：&nbsp;<input type="text" id="aliasOrderNo" name="orderReport.aliasOrderNo" value="${orderReport.aliasOrderNo!}" /></td>
					<td>收件人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="userName" name="orderReport.userName" value="${orderReport.userName!}" /></td>
					<td>会员账号：&nbsp;&nbsp;&nbsp;<input type="text" id="memberNo" name="orderReport.memberNo" value="${orderReport.memberNo!}" /></td>
					<td>手机号码：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="mobPhoneNum" name="orderReport.mobPhoneNum" value="${orderReport.mobPhoneNum!}" /></td>

				</tr>
				<tr>
					<td>审核状态：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderReport.statusConfirm">
						<#list statusConfirmList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderReport.statusConfirm> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.statusName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
					<td>支付状态：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderReport.statusPay">
						<#list statusPayList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderReport.statusPay> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.statusName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
					<td>物流状态：&nbsp;&nbsp;&nbsp;<select name="orderReport.logisticsStatus">
						<#list logisticsStatusList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderReport.logisticsStatus> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.statusName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
					<td>处理状态：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderReport.statusTotal">
						<#list statusTotalList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderReport.statusTotal> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.statusName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
				</tr>
				<tr>
					<td>订单渠道：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderReport.orderSource">
							<#list orderSourceList as list>
								<option value="${list.dicCode}"<#if list.dicCode == orderReport.orderSource> selected </#if>>
									<#if list.dicCode == "">
									全部
									<#else>
										${list.dicName}
									</#if>
								</option>
							</#list>
						</select>
					</td>
					<td>支付方式：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderReport.ifPayOnArrival">
							<#list arrivalPayList as list>
								<option value="${list.code}"<#if list.code == orderReport.ifPayOnArrival> selected </#if>>
									<#if list.code == "">
									全部
									<#else>
										${list.name}
									</#if>
								</option>
							</#list>
						</select>
					</td>
					<td>配送方式：&nbsp;&nbsp;&nbsp;<select name="orderReport.distributeType">
							<#list distributeTypeList as list>
								<option value="${list.code}"<#if list.code == orderReport.distributeType> selected </#if>>
									<#if list.code == "">
									全部
									<#else>
										${list.name}
									</#if>
								</option>
							</#list>
						</select>
					</td>
					<td>物流商：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="deliveryMerchantName" name="orderReport.deliveryMerchantName" value="${orderReport.deliveryMerchantName!}" />
					</td>
				</tr>
				<tr>
				<td>会员等级：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderReport.vipLevel">
							<#list memberVipLevelList as list>
								<option value="${list.code}"<#if list.code == orderReport.vipLevel> selected </#if>>
									<#if list.code == "">
									全部
									<#else>
										${list.name}
									</#if>
								</option>
							</#list>
						</select>
					</td>
				<td>订单大类：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderReport.billType">
								<option value="" <#if orderReport.billType == ''> selected </#if>>
									全部
								</option>
								<option value="1" <#if orderReport.billType == '1'> selected </#if>>
									正向单
								</option>
								<option value="-1" <#if orderReport.billType == '-1'> selected </#if>>
									逆向单
								</option>
						</select>
					</td>
					<!--<td>供应商编码：&nbsp;<input type="text" id="supplierCode" name="orderReport.supplierCode" value="${orderReport.supplierCode!}" />
					</td>-->
					<td colspan="2">配送地址：&nbsp;&nbsp;&nbsp;<#include "/WEB-INF/template/admin/order/address_linkage.ftl"><!--<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />--></td>
					<td></td>				
				</tr>
				<tr>
					<td colspan="4">
			    		自提点：<#include "/WEB-INF/template/admin/order/selfTakePoint_linkage.ftl">
			    	</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="button_red" name="search" id="search" onclick="orderSearch();" value="查询">
						<#if (pager.list?size > 0)>
							<input type="button" class="button" name="downexcel" id="downexcel" onclick="downExcel();"  value="导出"/>
						</#if>
					</td>
				</tr>	
			</table><table class="listTable" style="border: 1px solid #CCCCCC;width:125%">
				<tr>
					<th>
						<span class="sort" name="orderTime">下单日期</span>
					</th>
					<th>
						<span class="sort" name="orderType">订单类型</span>
					</th>
					<th>
						<span class="sort" name="orderNo">子订单号</span>
					</th>
					<th>
						<span  class="sort" name="userName">收件人</span>
					</th>
					<th>
						<span  class="sort" name="memberNo">会员账号</span>
					</th>
					<th>
						<span  class="sort" name="mobPhoneNum">手机号码</span>
					</th>
					<th>
						<span  class="sort" name="areaName">配送地址</span>
					</th>
					<th>
						<span class="sort" name="weight">订单总重量</span>
					</th>
					<th>
						<span class="sort" name="totalAmount">订单总金额</span>
					</th>
					<th>
						<span class="sort" name="statusConfirm">审核状态</span>
					</th>
					<th>
						<span class="sort" name="statusPay">支付状态</span>
					</th>
					<th>
						<span class="sort" name="logisticsStatus">物流状态</span>
					</th>
					<th>
						<span class="sort" name="statusTotal">处理状态</span>
					</th>
					<th>
						<span class="sort" name="orderSource">订单来源</span>
					</th>
					<th>
						<span class="sort" name="ifPayOnArrival">支付方式</span>
					</th>
					<th>
						<span class="sort" name="distributeType">配送方式</span>
					</th>
					<th>
						<span class="sort" name="selfFetchAddress">配送门店</span>
					</th>
					<th>
						<span class="sort" name="deliveryMerchantName">物流商</span>
					</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							<span title="${(list.orderTime?string("yyyy-MM-dd"))!}">${(list.orderTime?string("yyyy-MM-dd HH:MM:SS"))!}</span>
						</td>
						<td>${list.orderType}</td>
						<td>${list.orderSubNo}</td>
						<td>${list.userName}</td>
						<td>${list.memberNo}</td>
						<td>${list.mobPhoneNum}</td>
						<td>${list.areaName}</td>
						<td>${list.weight}</td>		
						<td>${list.totalPayAmount}</td>
						<td>${list.statusConfirm}</td>
						<td>${list.statusPay}</td>
						<td>${list.logisticsStatus}</td>
						<td>${list.statusTotal}</td>
						<td>${list.orderSource}</td>
						<td><#if (list.ifPayOnArrival = 0)>在线支付<#else>货到付款</#if></td>		
						<td>${list.distributeType}</td>
						<td><#if (list.selfFetchAddress == "")>总仓<#else>${list.selfFetchAddress}</#if></td>
						<td>${list.deliveryMerchantName}</td>
					</tr>
				</#list>
			</table>
			<input  type="text" id="currentStatus" name="currentStatus" value="${currentStatus}" style="display:none"></input>
			<input  type="text" id="selected" name="selected" value="${selected}" style="display:none"></input>  
			<input  type="text" id="selectedTabName" name="selectedTabName" value="${selectedTabName}" style="display:none"></input>  
			</div>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<#include "/WEB-INF/template/admin/pager.ftl" />
				</div>
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		</form>
		 <form id="excelForm" action="" method="post">
		 	<input type="hidden" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}" /> 	
		 	<input type="hidden" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}" />	
			<input type="hidden" name="orderMain.orderType" value="${orderMain.orderType!}" />	
			<input type="hidden" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}" />			
			<input type="hidden" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}" />
			<input type="hidden" name="orderSub.userName" value="${orderSub.userName!}" />
			<input type="hidden" name="orderMain.memberNo" value="${orderMain.memberNo!}" />
			<input type="hidden" name="orderSub.mobPhoneNum" value="${orderSub.mobPhoneNum!}" />				
			<input type="hidden" name="orderSub.statusConfirm" value="${orderSub.statusConfirm!}" />
			<input type="hidden" name="orderSub.logisticsStatus" value="${orderSub.logisticsStatus!}"/>
			<input type="hidden" name="orderSub.statusPay" value="${orderSub.statusPay!}"/>
			<input type="hidden" name="orderSub.statusTotal" value="${orderSub.statusTotal!}"/>
			<input type="hidden" name="orderMain.orderSource" value="${orderMain.orderSource!}"/>		
			<input type="hidden" name="orderMain.ifPayOnArrival" value="${orderMain.ifPayOnArrival!}"/>
			<input type="hidden" name="orderSub.distributeType" value="${orderSub.distributeType!}"/>					
			<input type="hidden" name="orderSub.deliveryMerchantName" value="${orderSub.deliveryMerchantName!}"/>
			<input type="hidden" name="orderItem.supplierCode" value="${orderItem.supplierCode!}"/>
			<input type="hidden" name="distributeAddress.state" value="${distributeAddress.state!}"/>
			<input type="hidden" name="distributeAddress.city" value="${distributeAddress.city!}"/>
			<input type="hidden" name="distributeAddress.county" value="${distributeAddress.county!}"/>
			<input type="hidden" name="orderSub.checkCode" value="${orderSub.checkCode!}" />
		</form>
	</div>
</body>
</html>