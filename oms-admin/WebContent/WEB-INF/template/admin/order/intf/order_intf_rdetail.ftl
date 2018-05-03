<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑订单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/resources/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
 
<@sj.head compressed="false" scriptPath="${webuiPath}/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>  
    

</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>异常信息</h1>
		</div>
		<form id="inputForm" class="validate" action="" method="post">
			<input type="hidden" name="intfReceived.id" value="${(intfReceived.id)!}" />
		</form>
			
			<div id="tabs">
				
			<div id="orderMianDiv" style="display:block">
				<table class="inputTable tabContent">
				  <tr>
						<th>
							接口编号:
						</th>
						<td>
							${(intfReceived.intfCode)!}
						</td>
					</tr>
					<tr>
						<th>
							接口名称:
						</th>
						<td>
						  <#list receiveList as rinfo>
									<#if intfReceived.intfCode == rinfo.code>
										${rinfo.desc}
									</#if>
								</#list>
						</td>
						<tr>
					<tr>
						<th>
							报文:
						</th>
						<td>
							<textarea  rows="20"  cols="130">
								${(intfReceived.msg)!}
							</textarea>
							
						</td>
					</tr>

				</table>
          </div>
          </div>
          
       	  <div class="buttonArea">
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		  </div>

		</form>
	</div>
</body>
</html>