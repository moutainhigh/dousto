<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
</head>

<script type="text/javascript" src="${webuiPath}/scripts/jquery/js/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/js/plugins/jquery.metadata.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/js/plugins/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/js/plugins/jquery.validate.cn.js"></script> 
<script type="text/javascript">
$().ready(function(){

	$("#submitButton").live("click",function(){
		var ps=$("#password").val();
		var reps=$("#rePassword").val();
		if(ps!=""){
			if(reps!=""){
			 	if(ps==reps){
			 	if(ps.length<4 || reps.length<4){
			 		window.alert("密码长度在4-20之间");
			 		return;
			 	}else{
				 	$("#editPasswor").val("true");
					$(".input form").submit();
			 	}
			 	}else{
				window.alert("两次输入的密码不一致！");
				return;
			}
			}else{
				window.alert("请输入确认密码");
				return;
			}
		}
	});
	var isSuccess = "${isSuccess}";
	if(isSuccess){
	window.alert("操作成功");
	}
});
</script>
<body class="input">
		<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>个人资料</h1>
		</div>
		<form  action="admin_profile!edit.action" method="post">
<table class="inputTable">
				<tr>
					<th>
						用户名:
					</th>
					<td>
							${(user.userName)!}
							<input type="hidden" name="user.userName" value="${(user.userName)!}" />
							<input type="hidden" name="user.id" value="${(user.id)!}" />
							<input type="hidden" id="editPasswor" name="editPasswor" value="false" />
							
					</td>
				</tr>
				<tr>
					<th>
						密 码:
					</th>
					<td>
						<input type="password" name="user.password" id="password"  maxlength="20" class="formText {minlength: 4, maxlength: 20}" title="密码长度只允许在4-20之间" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						重复密码:
					</th>
					<td>
						<input type="password" name="rePassword" id="rePassword"  maxlength="20" class="formText {equalTo: '#password', messages: {equalTo: '两次密码输入不一致!'}}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
						<th>&nbsp;</th>
						<td>
							<span class="warnInfo"><span class="icon">&nbsp;</span>如果要修改密码,请填写密码,若留空,密码将保持不变!</span>
						</td>
					</tr>
				<tr>
					<th>
						E-mail:
					</th>
					<td>
						${(user.email)!}
					</td>
				</tr>
				<tr>
					<th>
				管理角色:
					</th>
					<td>
						<#list allRole as role>
							<label>
							<#if (user.roles?contains(role))!> 
							${(role.name)!} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</#if>
							</label>
						</#list>
						<span id="roleMessagePosition"></span>
					</td>
				</tr>
				<tr>
					<th>
						是否启用:
					</th>
					<td> 
					<#if (isAdd || user.getIsEnabled() == true)!>已启用<#else>未启用</#if> 
					</td>
				</tr>
					<tr>
						<td colspan="2" align="center">
						<input type="button" id="submitButton" class="formButton" value="确认">
						</td>
					</tr>
				</table></form>
				</div>
				
				</body></html>