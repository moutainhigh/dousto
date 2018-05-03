<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑管理员 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<@sj.head debug="true" compressed="false" jquerytheme="custom-theme" scriptPath="${webuiPath}/scripts/jquery/" ajaxhistory="%{ajaxhistory}" defaultIndicator="myDefaultIndicator" defaultLoadingText="Please wait ..."/>
<#if !(order.id)??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加管理员<#else>编辑管理员</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>all_order!save.action<#else>all_order!update.action</#if>" method="post">
			<input type="hidden" name="order.id" value="${order.id}" />
			<div class="blank"></div>
		
			 <@sj.tabbedpanel id="localtabs">
			      <@sj.tab id="tab1" target="t1" label="基本信息"/>
			      <@sj.tab id="tab2" target="t2" label="个人资料"/>
			<div id="t1" >
				<#include "/WEB-INF/template/admin/order/customer/all_order_input_base.ftl" />
			</div>
			</@sj.tabbedpanel>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>