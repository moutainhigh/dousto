<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理员列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>管理员列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount} (共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="all_order!list.action" method="post">
			<div class="operateBar">
				<input type="button" class="addButton" onclick="location.href='all_order!add.action'" value="添加订单" />
				<label>查找:</label>
				<select name="pager.property">
					<input type="text" name="pager.property"  />
				</select>
				<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button" id="searchButton" class="searchButton" value="" />
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
			<table class="listTable">
				<tr>
					<th class="check"><input type="checkbox" class="allCheck" /></th>
					<th><span class="sort" name="aliasOrderId">aliasOrderId</span></th>
					<th><span class="sort" name="billType">billType</span></th>
					<th><span class="sort" name="cartTypeId">cartTypeId</span></th>
					<th><span class="sort" name="clientServiceRemark">clientServiceRemark</span></th>
					<th><span class="sort" name="confirmTime">confirmTime</span></th>
					<th><span class="sort" name="confirmerId">confirmerId</span></th>
					<th>操作</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td><input type="checkbox" name="ids" value="${list.id}" /></td>
						<td>${list.aliasOrderId}</td>
						<td>${list.billType}</td>
						<td>${(list.cartTypeId)}</td>
						<td>${list.clientServiceRemark}</td>
						<td><span title="${(list.confirmTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.confirmTime)!}</span></td>
						<td>${list.confirmerId}</td>
						<td><a href="all_order!edit.action?id=${list.id}" title="编辑">[编辑]</a></td>
					</tr>
				</#list>
			</table>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<input type="button" class="deleteButton" url="all_order!delete.action" value="删 除" disabled hidefocus="true" />
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
