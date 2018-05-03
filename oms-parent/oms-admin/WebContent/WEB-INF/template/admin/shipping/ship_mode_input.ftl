<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑配送方式 - Powered By ${systemConfig.systemName}</title>
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
<script>
$(document).ready(function(){
	$("#btn_submit").click(function(){
		$("#operation").val("submit");
		if($("#chk_enabled").get(0).checked){
			$("#is_enabled").val(true);
		}else{
			$("#is_enabled").val(false);
		}
		if($("#chk_supprt_cod").get(0).checked){
			$("#supprt_cod").val('true');
		}else{
			$("#supprt_cod").val('false');
		}
		$("#inputForm").submit();
	});
	$("#btn_shipZone").click(function(){
		$("#operation").val("editZone");
		
		$("#inputForm").submit();
	});
	
});
</script>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加配送方式<#else>编辑配送方式</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>ship_mode!save.action<#else>ship_mode!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<input type="hidden" id="operation" name="operation" value="submit" />
			<input type="hidden" id="is_enabled" name="shipMode.enabled" value="submit" />
			<input type="hidden" id="supprt_cod" name="shipMode.supportCod" value="submit" />
			<table class="inputTable">
				<tr>
					<th>
						配送方式名称:
					</th>
					<td>
						<input type="text" name="shipMode.name" class="formText" value="${(shipMode.name)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						是否启用:
					</th>
					<td>
						<input type="checkbox" id="chk_enabled" value="true" <#if (shipMode.enabled)!false>checked</#if> name="chk_enabled" />
					</td>
				</tr>
				<tr>
					<th>
						是否支持货到付款:
					</th>
					<td>
						<input type="checkbox" disabled id="chk_supprt_cod" value="true" <#if (shipMode.supportCod)!false>checked</#if> name="chk_supprt_cod" />
					</td>
				</tr>
				<tr>
					<th>
						优先级:
					</th>
					<td>
						<input type="text" name="shipMode.priority" class="formText {digits: true, required: true}" value="${(shipMode.priority)!}" title="只允许输入零或正整数" />输入数字如：1,2,3
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						排序:
					</th>
					<td>
						<input type="text" name="shipMode.orderList" class="formText {digits: true, required: true}" value="${(shipMode.orderList)!0}" title="只允许输入零或正整数" />输入数字如：1,2,3
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						默认物流公司:
					</th>
					<td>
						<@s.select id="storeSelect" cssStyle="width:150px" name="shipMode.defaultCarrierId" value="${(shipMode.defaultCarrierId)!}" list="carrierList" listKey="id" listValue="name" />
					</td>
				</tr>
				<tr>
					<th>
						运费:
					</th>
					<td>
						<input type="text" name="shipMode.firstWeightPrice" value="${(shipMode.firstWeightPrice)!0}" class="" />元
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						描述:
					</th>
					<td>
						<textarea name="shipMode.description" class="" rows="3" cols="50">${(shipMode.description)!}</textarea>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				
				<input type="button" id="btn_submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="hidden" id="btn_shipZone" class="formButton" value="配送区域" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>