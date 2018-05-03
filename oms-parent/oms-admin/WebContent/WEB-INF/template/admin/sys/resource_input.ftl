<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑资源 - Powered By ${systemConfig.systemName}</title>
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
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加资源<#else>编辑资源</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>resource!save.action<#else>resource!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden"  name="ajax" value="${ajax}"/>
			<table class="inputTable">
				<tr>
					<th>
						资源名称:
					</th>
					<td>
						<input type="text" name="resource.name" maxlength="10" class="formText {required: true, remote: 'resource!checkName.action?oldValue=${(resource.name?url)!}', messages: {remote: '资源名称已存在!'}}" value="${(resource.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						资源值:
					</th>
					<td>
						<input type="text" name="resource.value" maxlength="30" class="formText {required: true, remote: 'resource!checkValue.action?oldValue=${(resource.value?url)!}', messages: {remote: '此资源值已存在!'}}" value="${(resource.value)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						描述:
					</th>
					<td>
						<input type="text" name="resource.description" maxlength="80" class="formText" value="${(resource.description)!}" />
					</td>
				</tr>
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<input type="submit" class="formButton" value="确  定"<#if (resource.isSystem)!> disabled</#if> hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>