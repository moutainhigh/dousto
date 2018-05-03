<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/header.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
<script type="text/javascript">
	DD_belatedPNG.fix(".headerLogo");
</script>
<![endif]-->
</head>
<body class="header">
	<div class="body">
		<div class="headerLogo"></div>
		<div class="headerTop">
			<div class="headerLink">
				<span class="welcome">
					<strong><@sec.authentication property="name" /></strong>&nbsp;您好!&nbsp;
				<a href="${base}/j_spring_cas_security_logout" target="_top"><span class="logoutIcon"></span></a>
			</div>
		</div>
		<div class="headerBottom">
			<div class="headerMenu">
				<div class="menuLeft"></div>
				<ul>
				<#list rootMenuList as list>
	            	<!--<li><a href="${list.link}" target="menuFrame" hidefocus="true">${list.name}</a></li>-->
	            	<li><a href="user!menu.action?menuId=${list.id}" target="menuFrame" hidefocus="true">${list.name}</a></li>
	            </#list>
	            </ul>
	            <div class="menuRight"></div>
	            <div class="sysname"></div>
			</div>
		</div>
	</div>
</body>
</html>