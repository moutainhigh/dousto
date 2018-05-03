<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>配送方式列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>
</head>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>配送方式管理&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="ship_mode!list.action" method="post">
			<div class="operateBar">
				<input type="button" class="addButton" onclick="location.href='ship_mode!add.action'" value="添加方式" />
			</div>
			<table class="listTable">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span class="sort" name="name">配送方式名称</span>
					</th>
					<th>
						<span class="sort" name="enabled">是否启用</span>
					</th>
					<th>
						<span class="sort" name="supportCod">支持货到付款</span>
					</th>
					<th>
						<span class="sort" name="orderList">排序</span>
					</th>
					<th>
						<span class="sort" name="firstWeightPrice">运费</span>
					</th>
					<th>
						操作
					</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${(list.id)!}" />
						</td>
						<td>
							${list.name}
						</td>
						<td>
							
							<#if list.enabled>
							启用
							<#else>
							未启用
							</#if>
						</td>
						<td>
						<#if list.supportCod>
							支持
							<#else>
							不支持
							</#if>
						</td>
						<td>
							${list.orderList}
						</td>
						<td>
							${list.firstWeightPrice}
						</td>
						<td>
							<a href="ship_mode!edit.action?id=${list.id}" title="编辑">[编辑]</a>
						</td>
					</tr>
				</#list>
			</table>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<input type="button" class="deleteButton" url="ship_mode!delete.action" value="删 除" disabled hidefocus="true" />
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