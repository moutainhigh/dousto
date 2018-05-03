<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心首页 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin" style=""><div id="tipWindow" class="tipWindow"><span class="icon">&nbsp;</span><span class="messageText"></span></div><div id="messageWindow" class="messageWindow jqmID2"><div class="windowTop"><div class="windowTitle">提示信息&nbsp;</div><a class="messageClose windowClose" href="${base}/sys/user!index.action#" hidefocus="true"></a></div><div class="windowMiddle"><div class="messageContent"><span class="icon">&nbsp;</span><span class="messageText"></span></div><input type="button" class="formButton messageButton windowClose" value="确  定" hidefocus="true"></div><div class="windowBottom"></div></div><div id="contentWindow" class="contentWindow jqmID1"><div class="windowTop"><div class="windowTitle"></div><a class="messageClose windowClose" href="${base}/sys/user!index.action#" hidefocus="true"></a></div><div class="windowMiddle"><div class="windowContent"></div></div><div class="windowBottom"></div></div>
	<div class="adminBar">
		<span class="icon">&nbsp;</span>欢迎使用网店管理系统！
	</div>
	<div class="body">

		<div class="bodyLeft">
	
			<div class="blank"></div>
			<div class="blank"></div>
			<table class="listTable">
				<tbody><tr>
					<th colspan="4">
						<span style="padding-left: 30px;"></span>信息统计
					</th>
				</tr>
				<tr>
					<td width="110">
						已上架商品:
					</td>
					<td>
						${marketableProductCount}
					</td>
				</tr>
				<tr>
					<td>
						未上架商品:
					</td>
					<td>
						${unMarketableProductCount}
					</td>
				</tr>
				<tr>
					<td>
						后台用户总数:
					</td>
					<td>
						${memberTotalCount}
					</td>
				</tr>
				
			</tbody></table>
		</div>
		<p class="copyright">Copyright © 2012 ibm.com All Rights Reserved.</p>
	</div>
</body>
</html>