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
<link href="${base}/resources/common/css/base_order.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
 
<@sj.head compressed="false" scriptPath="${webuiPath}/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>  
    
<script type="text/javascript">

//$().ready( function() {
	//$( "#tabs" ).tabs();	
//});

function doExecute(){
    var form = document.getElementById("inputForm");
	form.submit();
}

function deleteExecute(){
    var form = document.getElementById("deleteForm");
	form.submit();
}

</script>

</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>异常信息</h1>
		</div>
		<form id="inputForm" class="validate" action="maintenance_run.action" method="post">
			<input type="hidden" name="ordiErrOptLog.id" value="${(ordiErrOptLog.id)!}" />
		</form>
			
			<div id="tabs">
				
			<div id="orderMianDiv" style="display:block">
				<table class="inputTable tabContent">
					<tr>
						<th>
							订单号:
						</th>
						<td>
							${(ordiErrOptLog.orderNo)!}
						</td>
					</tr>
					<tr>
						<th>
							子订单号:
						</th>
						<td>
							${(ordiErrOptLog.orderSubNo)!}
						</td>
					</tr>
					
					<tr>
						<th>
							异常描述:
						</th>
						<td>
							<textarea  rows="20"  cols="130">
								${(ordiErrOptLog.errorDesc)!}
							</textarea>
							
						</td>
					</tr>

				</table>
          </div>
          </div>
          
       	  <div class="buttonArea">
       	        
				<input type="submit" class="formButton" value="执 行" hidefocus="true"  onclick="doExecute();" />&nbsp;&nbsp;&nbsp;&nbsp;
				
				<input type="button" class="formButton" onclick="deleteExecute();" value="删  除" hidefocus="true" />
				
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
				
				
		  </div>
		  
		  <div>
		  
		  	<form id="deleteForm" action="maintenance_delete.action" method="post">
			   <input type="hidden" name="ordiErrOptLog.id" value="${(ordiErrOptLog.id)!}" />
			</form>
		  </div>

		</form>
	</div>
</body>
</html>