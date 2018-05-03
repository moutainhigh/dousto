<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑部门- Powered By ${systemConfig.systemName}</title>
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
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加部门<#else>编辑部门</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>dept!save.action<#else>dept!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden"  name="ajax" value="${ajax}"/>
			<table class="inputTable">
				<tr>
					<th>
						部门名称:
					</th>
					<td>
						<input type="text" name="dept.deptName" class="formText {required: true, remote: 'dept!checkName.action?oldValue=${(dept.deptName?url)!}', messages: {remote: '部门名称已存在!'}}" value="${(dept.deptName)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						部门代码:
					</th>
					<td>
						<input type="text" name="dept.deptCode" class="formText {required: true, remote: 'dept!checkCode.action?oldValue=${(dept.deptCode?url)!}', messages: {remote: '部门代码已存在!'}}" value="${(dept.deptCode)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						&nbsp;
					</th>
					<td>
						<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>