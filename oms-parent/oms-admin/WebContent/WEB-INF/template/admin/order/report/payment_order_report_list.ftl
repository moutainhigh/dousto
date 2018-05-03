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
	form.action="payment_order_report!search.action";
	$('#pageNumber').val(0);
	form.submit();
}


function downExcel(){
    var form1 = document.getElementById("listForm");
    form1.action="payment_order_report_export_excel.action";
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
		<form id="listForm" action="payment_order_report!search.action" method="post" >
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
						<span class="sort" name="orderSubNo">订单号</span>
					</th>
					<th>
						<span class="sort" name="cmbOrderId">招行订单号</span>
					</th>
					<th>
						<span class="sort" name="serialNo">交易流水号</span>
					</th>
					<th>
						<span class="sort" name="payAmount">金额</span>
					</th>
					<th>
						<span class="sort" name="payTime">交易时间</span>
					</th>
					<th>
						<span  class="sort" name="payStatus">支付状态</span>
					</th>			
				</tr>
				<#list pager.list as list>
					<tr">
						<td>
							<span title="${(list.orderTime?string("yyyy-MM-dd"))!}">${(list.orderTime?string("yyyy-MM-dd HH:MM:SS"))!}</span>
						</td>
						<td>${list.orderSubNo}</td>
						<td>0${list.cmbOrderId}</td>
						<td>${list.serialNo}</td>
						<td>${list.payAmount}</td>
						<td><span title="${(list.payTime?string("yyyy-MM-dd"))!}">${(list.orderTime?string("yyyy-MM-dd hh:mm:ss"))!}</td>	
						<td>成功</td>
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