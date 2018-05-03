<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<#-- security.tld 角色权限控制 -->
<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单物流状态日志 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<style type="text/css">
label.error {
	color: red;
	font-style: italic
}
div.error { display: none; }

input.error { border: 1px solid red; }
</style>

<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/validation/dist/jquery.validate.js"></script>

<script type="text/javascript">
	$().ready(function() {
			
			$("#listForm").validate({
				/*errorLabelContainer: "#messageBox",		//显示错误信息的容器ID
				wrapper: "li",								//包含每个错误信息的容器*/
				rules:{
					"log.orderNo":{
						required: true
					}
				},
				messages:{
					"log.orderNo":{
						required: "请填写订单号"
					}
				}
		});
	});
	

		
</script>
<script type="text/javascript">

$.gotoPage = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageTotalCount").val(), buttonClickCallback: $.gotoPage });
    $("#pageNumber").val(pageclickednumber);
    $("#listForm").submit();
};

</script>


</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>订单物流状态日志&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="order_status_log_query.action" method="post" validate="true">
	    	<div id="MainTable">
	    	
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
			
			<!-- <table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<td>订单号:<input type="text" name="log.orderNo" value="${(log.orderNo)!}" /></td>
					<td>开始日期： <input type="text" readonly="true"  onclick="WdatePicker()" name="map['strStartDate']" value="${(map['strStartDate'])!}" ></td>
					<td>结束日期： <input type="text" readonly="true"  onclick="WdatePicker()" name="map['strEndDate']" value="${(map['strEndDate'])!}" ></td>
					<td style="text-align: left;padding-bottom: 3px;"><span id="orderNoTip"></span></td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="submit" class="previewButton" name="search" value="查询"/>
					</td>
				</tr>	
			</table>
			<br>
			-->
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<th>
						<span class="sort" name="orderNo">订单号</span>
					</th>
					
					<th>
						<span class="sort" name="orderSubNo">子订单号</span>
					</th>
					<!--
					<th>
						<span class="sort" name="previousStatus">前状态</span>
					</th>
					-->
					<th>
						<span class="sort" name="currentStatus">现状态</span>
					</th>
					<th>
						<span class="sort" name="operator">操作人</span>
					</th>
					<th>
						<span class ="sort" name="operateTime">操作时间</span>
					</th>
				</tr>
				<#list pager.list as list>
                     <tr>
						<td>${(list.orderNo)!}</td>
						
						<td>
							${(list.orderSubNo)!}
						</td>	
						<!--						
						<td>
							${(list.previousStatus)!}
						</td>	
						-->						
						<td>${(list.remark)!}</td>	
						<td>
							${(list.operator)!}
						</td>					
						<td>
							${(list.operateTime?string("yyyy-MM-dd HH:mm:ss"))!}
						</td>									
					</tr>
                  
				</#list>
			</table>
			
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
		 <form id="excelForm" action="maintenance!query.action" method="post">
						<input type="hidden" name="map['orderItemId']" value="${(map['orderItemId'])!}" />
						<input type="hidden" name="map['orderId']" value="${(map['orderId'])!}" />
						<input type="hidden" name="map['orderStatus']" value="${(map['orderStatus'])!}" />
						<input type="hidden" name="map['orderSource']" value="${(map['orderSource'])!}" />
						<input type="hidden" name="map['operateType']" value="${(map['operateType'])!}" />
						<input type="hidden" name="map['errorCode']" value="${(map['errorCode'])!}" />
		</form>
	</div>
</body>
</html>