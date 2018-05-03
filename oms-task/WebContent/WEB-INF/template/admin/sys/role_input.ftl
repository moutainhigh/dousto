<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>添加/编辑角色 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/role.css" rel="stylesheet" type="text/css" />
<link href="${webuiPath}/scripts/jquery/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link href="${webuiPath}/scripts/jqueryui/themes/custom-theme/jquery-ui.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webuiPath}/scripts/jquery/ztree/jquery-ztree-2.5.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/menuTree.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/js/plugins/jquery.bgiframe.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jqueryui/ui/minified/jquery-ui.min.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
<script type="text/javascript">
	<#if isAdd??> var url="${base}/json/menu_json.action";
	<#else> var url="${base}/json/menu_json!roleMenu.action?id=${id}";
	</#if>
   var zNodes;		
	$.ajax({
	  url: url,
	  dataType: 'json',
	  async:false,
	  success: function(data){  
		zNodes = data.menuList;
	    }
	});
	$(document).ready(function(){
    zTree1 = $("#treeDemo").zTree(setting,zNodes);
	expandAll(true);
});
function roleSubmit(){
var nodes = zTree1.getCheckedNodes(true);
var menuIds="";
for(var i=0;i<nodes.length;i++){
	menuIds=menuIds+"<input type='hidden' name='menuIds' value='"+nodes[i].id+"' />";
}
$("#menus").html(menuIds);
$("#inputForm").submit();
}
</script>

</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if isAdd??>添加角色<#else>编辑角色</#if></h1>
		</div>
		<form id="inputForm" class="validate" action="<#if isAdd??>role!save.action<#else>role!update.action</#if>" method="post">
			<input type="hidden" name="id" value="${id}" />
			<div class="centerDiv">
				<#include "/WEB-INF/template/admin/sys/role_input_leftDiv.ftl">
				<#include "/WEB-INF/template/admin/sys/role_input_rightDiv.ftl">
				<div id="menus"></div>
				<div id="resources"></div>
				<div class="buttonArea">
					<input type="button" class="formButton" value="确  定" hidefocus="true" onclick="roleSubmit()"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>