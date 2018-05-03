<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理登录 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/login_new.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

// 登录页面若在框架内，则跳出框架
if (self != top) {
	top.location = self.location;
};

$().ready( function() {

	var $username = $("#username");
	var $password = $("#password");
	var $captcha = $("#captcha");
	var $captchaImage = $("#captchaImage");
	var $isSaveUsername = $("#isSaveUsername");

	// 判断"记住用户名"功能是否默认选中,并自动填充登录用户名
	if($.cookie("adminUsername") != null) {
		$isSaveUsername.attr("checked", true);
		$username.val($.cookie("adminUsername"));
		$password.focus();
	} else {
		$isSaveUsername.attr("checked", false);
		$username.focus();
	}

	// 提交表单验证,记住登录用户名
	$("#loginForm").submit( function() {
		if ($username.val() == "") {
			$.message("请输入您的用户名!");
			return false;
		}
		if ($password.val() == "") {
			$.message("请输入您的密码!");
			return false;
		}
		if ($captcha.val() == "") {
			$.message("请输入您的验证码!");
			return false;
		}
		
		if($isSaveUsername.attr("checked") == true) {
			$.cookie("adminUsername", $username.val(), {expires: 30});
		} else {
			$.cookie("adminUsername", null);
		}
		
	});

	// 刷新验证码
	$captchaImage.click( function() {
		var timestamp = (new Date()).valueOf();
		var imageSrc = $captchaImage.attr("src");
		if(imageSrc.indexOf("?") >= 0) {
			imageSrc = imageSrc.substring(0, imageSrc.indexOf("?"));
		}
		imageSrc = imageSrc + "?timestamp=" + timestamp;
		$captchaImage.attr("src", imageSrc);
	});

	<#if (actionErrors?size > 0)>
		$.message("error", "<#list errorMessages as list>${list}<br></#list>");
	</#if>

});
</script>
</head>
<body>
<div id="main">
	<div class="login">
			 <form id="loginForm" class="form" action="${base}/admin/loginVerify" method="post">
	<table width="80%" border="0" align="right" cellpadding="3" cellspacing="0">
    <tr>
      <td height="70" colspan="4" valign="top" nowrap="nowrap" class="login-dazi" >&nbsp;</td>
    </tr>
    <tr>
      <td width="53" height="50" nowrap="nowrap">&nbsp;</td>
      <td colspan="3"><input id="username" class="input_out" name="j_username" type="text" onfocus="this.className='input_on';this.onmouseout=''" onblur="if(this.value!=''){return;}this.className='input_off';this.onmouseout=function(){this.className='input_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input_out'" /></td>
    </tr>
    <tr>
      <td height="50">&nbsp;</td>
      <td colspan="3"><input id="password" class="input2_out" name="j_password" type="password" onfocus="this.className='input_on';this.onmouseout=''" onblur="if(this.value!=''){return;}this.className='input2_out';this.onmouseout=function(){this.className='input2_out'};" onmousemove="this.className='input_move'" onmouseout="this.className='input2_out'" /></td>
    </tr>
		                
	<tr>
      <td height="50"></td>
      <td>
        
        <input id="captcha" class="input3_out" name="j_captcha" type="text" onfocus="this.className='input3_on';this.onmouseout=''" onblur="if(this.value!=''){return;}this.className='input3_out';this.onmouseout=function(){this.className='input3_out'};" onmousemove="this.className='input3_move'" onmouseout="this.className='input3_out'" />
      </td>
      <td width="73"><img id="captchaImage" style="vertical-align:middle" src="${base}/captcha.jpg" width="60" height="30" /></td>
     <td width="70" nowrap="nowrap">点图片更换</td>
    </tr>
		                
		                
<tr>
	<td >&nbsp;</td>

      <td height="25" colspan="3" ><table border="0">
          <tr>
            <td ><table border="0">
          <tr>
            <td nowrap="nowrap" width="20"><input type="checkbox" id="isSaveUsername" /></td>
            <td align="left" nowrap="nowrap" class="zhuce-zi2">记住账号密码</td>
            </tr>
        </table></td>
          
          </tr>
      </table></td>
    </tr>     
		                
		                
		<tr>
		<td >&nbsp;</td>
		<td  colspan="3">
    		<div>
      			<input type="submit" style="margin:0px 0 0 0px;" class="login_button" name="commit" value="登 录" hidefocus="true" />
    		</div>
  		</form>
		</td>
		</tr>
  		</table>
	</div>
	
	<div id="foot">
    <table width="100%" height="" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="260" align="center" valign="bottom"><span class="zhifu_small"> Copyright  2018 ibm.com All Rights Reserved.</span></td>
      </tr>
      <tr>
        <td align="center"><a href="1" class="foot"></a></td>
      </tr>
    </table>
  </div>
	
</body>
</html>