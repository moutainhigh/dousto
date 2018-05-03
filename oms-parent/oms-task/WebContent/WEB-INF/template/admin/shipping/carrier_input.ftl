<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑物流公司</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加物流公司<#else>编辑物流公司</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>carrier!save.action<#else>carrier!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" name="carrier.code" value="${carrier.code}" />
			<table class="inputTable tabContent">
				<tr>
					<th>
						物流公司名称:
					</th>
					<td>
						<input type="text" name="carrier.name" class="formText" value="${(carrier.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<#--
				<tr>
					<th>
						是否支持货到付款:
					</th>
					<td>
						<#if isAdd==true>
						<input type="checkbox" value="true" name="carrier.supportCod" />
						<#else>
						<input type="checkbox" value="true" <#if (carrier.supportCod)>checked</#if> name="carrier.supportCod" />
						</#if>
						<label class="requireField">*</label>
					</td>
				</tr>
				-->
				<tr>
					<th>
						物流公司网址:
					</th>
					<td>
						<input type="text" name="carrier.url" class="formText {url: true}" value="${(carrier.url)!}" title="必须以http://开头" />
					</td>
				</tr>
				<tr>
					<th>
						包裹跟踪地址:
					</th>
					<td>
						<input type="text" name="carrier.trackUrl" class="formText {url: true}" value="${(carrier.trackUrl)!}" title="必须以http://开头" />
					</td>
				</tr>
				<tr>
					<th>
						包裹号参数名:
					</th>
					<td>
						<input type="text" name="carrier.trackParam" class="formText {url: true}" value="${(carrier.trackParam)!}" title="必须以http://开头" />
					</td>
				</tr>
				<tr>
					<th>
						排序:
					</th>
					<td>
						<input type="text" name="carrier.displayOrder" class="formText {digits: true, required: true}" value="${(carrier.displayOrder)!50}" title="只允许输入零或正整数" />
						<label class="requireField">*</label>
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