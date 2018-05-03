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
	form.action="refund_order_report!search.action";
	$('#pageNumber').val(0);
	form.submit();
}


function downExcel(){
    var form1 = document.getElementById("listForm");
    form1.action="refund_order_report_export_excel.action";
	form1.submit();
}
</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>${orderColumnTitle}&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="refund_order_report!search.action" method="post" >
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
					<td>创建日期：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderReport.orderTimeFrom" value="${(orderReport.orderTimeFrom?string("yyyy-MM-dd"))!}"></td>
					<td>创建日期：至 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderReport.orderTimeTo" value="${(orderReport.orderTimeTo?string("yyyy-MM-dd"))!}"></td>				
					<td>审核状态：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderSub.statusConfirm">
						<#list statusConfirmList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderSub.statusConfirm> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.statusName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
					<td colspan="2">配送地址：&nbsp;&nbsp;&nbsp;&nbsp;<#include "/WEB-INF/template/admin/order/address_linkage.ftl"><!--<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />-->
					</td>
				</tr>
				<tr>
					<td>供应商编码：&nbsp;<input type="text" id="supplierCode" name="orderReport.supplierCode" value="${orderReport.supplierCode!}" /></td>
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
					<td colspan="2">商品品类：&nbsp;&nbsp;&nbsp;&nbsp;<#include "/WEB-INF/template/admin/order/report/category_linkage.ftl"><!--<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />-->
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
			</table>
			<br>
	
			<table class="listTable" style="border: 1px solid #CCCCCC;width:125%">
				<tr>
					<th>
						<span class="sort" name="orderTime">创建日期</span>
					</th>
					<th>
						<span class="sort" name="orderSubNo"><#if (column = 1)>退货单号<#elseif column = 2>换货单号<#else>拒收单号</#if></span>
					</th>
					<th>
						<span class="sort" name="orderRelatedOrigin">关联原单号</span>
					</th>
					<th>
						<span class="sort" name="orderRelatedPayType">原订单支付方式</span>
					</th>
					<th>
						<span class="sort" name="orderRelatedClosedTime">原订单封闭时间</span>
					</th>
					<th>
						<span  class="sort" name="statusConfirm">审核状态</span>
					</th>
					<th>
						<span  class="sort" name="areaName">配送区域</span>
					</th>
					<th>
						<span class="sort" name="distributeType">入库物流方式</span>
					</th>
					<th>
						<span class="sort" name="supplierCode">供应商</span>
					</th>
					<th>
						<span class="sort" name="productCategory">
						<#if (column = 1)>退货商品品类<#elseif column = 2>换货商品品类<#else>拒收商品品类</#if>
						</span>
					</th>
					<th>
						<span  class="sort" name="skuNo">
						<#if (column = 1)>退货商品编码<#elseif column = 2>换货商品编码<#else>拒收商品编码</#if>
						</span>
					</th>
					<th>
						<span  class="sort" name="commodityName">
						<#if (column = 1)>退货商品名称<#elseif column = 2>换货商品名称<#else>拒收商品编码</#if>					
						</span>
					</th>
					<th>
						<span class="sort" name="saleNum">
						<#if (column = 1)>退货商品数量<#elseif column = 2>换货商品数量<#else>拒收商品数量</#if>		
						</span>
					</th>
					<th>
						<span class="sort" name="payAmount">
						<#if (column = 1)>退货商品金额<#elseif column = 2>换货商品金额<#else>拒收商品金额</#if>	
						</span>
					</th>
					<th>
						<span class="sort" name="preRefundReason">售后原因</span>
					</th>
					<th>
						<span class="sort" name="refundReason">问题描述</span>
					</th>
					<#if (column = 1 || column = 3)>
					<th>
						<span class="sort" name="payFlag">退款状态</span>
					</th>
					</#if>
					<#if (column = 1 || column = 3)>
					<th>
						<span class="sort" name="payType">退款方式</span>
					</th>
					</#if>	
					<#if (column = 1 || column = 3)>
					<th>
						<span class="sort" name="payAmount">退款金额</span>
					</th>
					</#if>		
					<th>
						<span class="sort" name="signOffTime">封闭时间</span>
					</th>
					<th>
						<span class="sort" name="inspectStatus">质检状态</span>
					</th>
					<th>
						<span class="sort" name="operateTime">质检时间</span>
					</th>
					<th>
						<span class="sort" name="vipLevel">会员等级</span>
					</th>
				</tr>
				<#list pager.list as list>
					<tr">
						<td>
							<span title="${(list.orderTime?string("yyyy-MM-dd"))!}">${(list.orderTime?string("yyyy-MM-dd HH:MM:SS"))!}</span>
						</td>
						<td>${list.orderSubNo}</td>
						<td>${list.orderSubRelatedOrigin}</td>
						<td><#if (list.ifPayOnArrival = 0)>在线支付<#else>货到付款</#if></td>	
						<td>${list.relatedSignOffTime}</td>
						<td>${list.statusConfirm}</td>	
						<td>${list.areaName}</td>
						<td><#list allDistributeType as dlist>
						<#if (dlist.code == list.distributeType)!>${dlist.desc}</#if></#list></td>
						<td>${list.supplierCode}</td>	
						<td>${list.productCategory}</td>				
						<td>${list.skuNo}</td>
						<td>${list.commodityName}</td>
						<td>${list.saleNum}</td>					
						<td>${list.payAmount}</td>
						<td>${list.preRefundReason}</td>					
						<td>${list.refundReason}</td>
						<#if (column = 1 || column = 3)>
							<td><#if (list.payName == "")>已退款<#else>未退款</#if></td>
						</#if>
						<#if (column = 1 || column = 3)>
							<td>${list.payName}</td>
						</#if>
						<#if (column = 1 || column = 3)>
							<td>${list.payAmout}</td>
						</#if>
						<td>${list.signOffTime}</td>
						<td><#if (list.currentStatus = '0250')>质检通过<#elseif (list.currentStatus = '0160')>质检失败<#else></#if></td>	
						<td>
							<span title="${(list.operateTime?string("yyyy-MM-dd"))!}">${(list.operateTime?string("yyyy-MM-dd HH:MM:SS"))!}</span>
						</td>
						<td><#if list.vipLevel = "1">微卡<#elseif list.vipLevel = "2">银卡<#elseif list.vipLevel = "3">金卡<#else></#if></td>	
					</tr>
				</#list>
			</table>
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
	</div>
</body>
</html>