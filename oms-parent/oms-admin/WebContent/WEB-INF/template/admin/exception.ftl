<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<#include "/WEB-INF/template/common/include.ftl">
<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<script language="javascript">

function showContent(){

    if(document.getElementById("errorMessage").style.display == 'block'){

       document.getElementById("errorMessage").style.display = 'none';

    }else{

       document.getElementById("errorMessage").style.display = 'block';

    }

}

</script>

</head>

<body scroll="auto">

<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">

<tr>

    <td align="center" class="bg" valign="top">

       <table width="100%" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;word-break:break-all;">

           <tr>

              <td align="center" width="100%" height="80">

                    <s:property value="exception.message" /> 

              </td>

           </tr>

           <tr>

              <td height="30" align="center">

                  <a href="#" onclick="javascript:history.go(-1);"><s:text name="global.return"/></a>&nbsp; &nbsp;

                  <a href="#" onclick="javascript:showContent();">查看详细信息</a>

              </td>

           </tr>

           <tr>

              <td align="left" valign="top">

                  <!-- 异常堆栈信息(开发人员用) -->

                  <div style="display:none;" id="errorMessage">

                  <pre>   <s:property value="exceptionStack" /></pre>

                  </div>

              </td>

           </tr>

       </table>

    </td>

</tr>

</table>

</body>

</html>