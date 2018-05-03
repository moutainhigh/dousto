<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理菜单</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/menu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webuiPath}/scripts/jqueryui/ui/minified/jquery-ui.min.js"></script>
<link href="${webuiPath}/scripts/jqueryui/themes/custom-theme/jquery-ui.css" rel="stylesheet" type="text/css" />
<script>
	$(function() {
		$("#menuContent").accordion({ autoHeight: false });
	});
</script>
</head>
<body class="menu" style=""><div id="tipWindow" class="tipWindow"><span class="icon">&nbsp;</span><span class="messageText"></span></div><div id="messageWindow" class="messageWindow jqmID2"><div class="windowTop"><div class="windowTitle">提示信息&nbsp;</div><a class="messageClose windowClose" href="${base}/sys/user!menu.action#" hidefocus="true"></a></div><div class="windowMiddle"><div class="messageContent"><span class="icon">&nbsp;</span><span class="messageText"></span></div><input type="button" class="formButton messageButton windowClose" value="确  定" hidefocus="true"></div><div class="windowBottom"></div></div><div id="contentWindow" class="contentWindow jqmID1"><div class="windowTop"><div class="windowTitle"></div><a class="messageClose windowClose" href="${base}/sys/user!menu.action#" hidefocus="true"></a></div><div class="windowMiddle"><div class="windowContent"></div></div><div class="windowBottom"></div></div>
	<div class="menuContent ui-accordion ui-widget ui-helper-reset" id="menuContent" role="tablist">

<#assign n1 = -1>
<#assign n2 = "0">
<#assign b1 = "true">
<#assign b2 = "false">
<#assign c1 = "s">
<#assign c2 = "ui-accordion-header-active ui-state-active">
<#assign c3 = "top">
<#assign c4 = "ui-accordion-content-active">
<#assign baseUrl=menu.link>
<#list menusList as map>
	<#list map?keys as secondMenu>
		<#assign n1 = n1 + 1>
		<#if n1 != 0>
			<#assign b1 = "false">
			<#assign b2 = "true">
			<#assign n2 = -1>
			<#assign c1 = "e">
			<#assign c2 = "">
			<#assign c3 = "all">
			<#assign c4 = "">
		</#if>
		<#assign thirdList = map.get(secondMenu)>
			<h3 class="ui-accordion-header ui-helper-reset ui-state-default ${c2} ui-corner-${c3} ui-accordion-icons" role="tab" id="ui-accordion-menuContent-header-${n1}" aria-controls="ui-accordion-menuContent-panel-${n1}" aria-selected=${b1} aria-expanded=${b1} tabindex="0"><span class="ui-accordion-header-icon ui-icon ui-icon-triangle-1-${c1}"></span><a href="#">&nbsp;&nbsp;${secondMenu.name}</a></h3>
			<div class="ui-accordion-content ui-helper-reset ui-widget-content ui-corner-bottom ${c4}" id="ui-accordion-menuContent-panel-${n1}" aria-labelledby="ui-accordion-menuContent-header-${n1}" role="tabpanel" aria-hidden=${b2}><dl>
		<#list thirdList as thirdMenu>
			<dt><a href="${base}/${thirdMenu.link}" target="mainFrame">${thirdMenu.name}</a></dt>
		</#list>
			</dl></div>	
	</#list>
</#list>
	</div>
	<div id="calendar_div"></div>
</body>
</html>