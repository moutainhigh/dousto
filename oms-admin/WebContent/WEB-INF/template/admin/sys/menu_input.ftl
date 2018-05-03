<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑菜单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加菜单<#else>编辑菜单</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>menu!save.action<#else>menu!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="menu.parentId" value="${(menu.parentId)!}" />
			<table class="inputTable tabContent">
				<tr>
					<th>
						菜单名称:
					</th>
					<td>
						<input type="text" name="menu.name" class="formText {required: true, remote: 'menu!checkName.action?oldValue=${(menu.name?url)!}', messages: {remote: '菜单名称已存在!'}}" value="${(menu.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						菜单标识:
					</th>
					<td>
						<input type="text" name="menu.menuCode" class="formText" value="${(menu.menuCode)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						链接:
					</th>
					<td>
						<input type="text" name="menu.link" class="formText" value="${(menu.link)!}" />
					</td>
				</tr>
				<tr>
					<th>
						描述:
					</th>
					<td>
						<input type="text" name="menu.description" class="formText" value="${(menu.description)!}" />
					</td>
				</tr>
				<#if (menu.isSystem)!false>
					<tr>
						<th>
							&nbsp;
						</th>
						<td>
							<span class="warnInfo"><span class="icon">&nbsp;</span>系统提示：</b>系统内置菜单不允许修改!</span>
						</td>
					</tr>
				</#if>
			</table>
			<table class="inputTable tabContent">
				<tr>
					<td colspan="2">
						<#list allResource as list>
							<div style="width: 30%; float: left;"><label><input type="checkbox" name="resourceIds" value="${list.resourceId}" <#if (menu.resources.contains(list) == true)!> checked="checked"</#if> />${(list.name)!}</label></div>
						</#list>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>