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
	form.action="cancel_order_report!search.action";
	$('#pageNumber').val(0);
	form.submit();
}

function downExcel(){
    var form1 = document.getElementById("listForm");
    form1.action="cancel_order_report_export_excel.action";
	form1.submit();
}
</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>订单取消报表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="cancel_order_report!search.action" method="post" >
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
					<td>订单渠道：&nbsp;&nbsp;&nbsp;<select name="orderReport.orderSource">
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
				</tr>
				<tr>
					<!--<td>自提点：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="selfTakePoint.id">
							<#list selfTakePointList as list>
								<option value="${list.id}"<#if list.id == selfTakePoint.id> selected </#if>>
									<#if list.id == "">
									全部
									<#else>
										${list.pointName}
									</#if>
								</option>
							</#list>
						</select>
					</td>-->
					<td colspan="2">
			    		自提点：<#include "/WEB-INF/template/admin/order/selfTakePoint_linkage.ftl">
			    	</td>
					<td colspan="2">配送地址：&nbsp;&nbsp;&nbsp;&nbsp;<#include "/WEB-INF/template/admin/order/address_linkage.ftl"><!--<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />-->
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
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
						<span class="sort" name="orderTime">下单日期</span>
					</th>
					<th>
						<span class="sort" name="orderSubNo">子订单号</span>
					</th>
					<th>
						<span class="sort" name="orderType">订单类型</span>
					</th>
					<th>
						<span class="sort" name="orderSource">订单来源</span>
					</th>
					<th>
						<span class="sort" name="statusConfirm">审核状态</span>
					</th>
					<th>
						<span class="sort" name="statusPay">支付状态</span>
					</th>
					<th>
						<span  class="sort" name="customerName">会员账号</span>
					</th>
					<th>
						<span  class="sort" name="vipLevel">会员级别</span>
					</th>
					<th>
						<span class="sort" name="totalPayAmount">订单总金额</span>
					</th>
					<th>
						<span  class="sort" name="areaName">配送地址</span>
					</th>
					<th>
						<span class="sort" name="selfTakePointId">自提点</span>
					</th>
				</tr>
				<#list pager.list as list>
					<tr">
						<td>
							<span title="${(list.orderTime?string("yyyy-MM-dd"))!}">${(list.orderTime?string("yyyy-MM-dd HH:MM:SS"))!}</span>
						</td>
						<td>${list.orderSubNo}</td>
						<td>${list.orderType}</td>
						<td>${list.orderSource}</td>
						<td>${list.statusConfirm}</td>					
						<td>${list.statusPay}</td>
						<td>${list.memberNo}</td>
						<td>${list.vipLevel}</td>					
						<td>${list.totalPayAmount}</td>		
						<td>${list.areaName}</td>
						<td>${list.pointName}</td>
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