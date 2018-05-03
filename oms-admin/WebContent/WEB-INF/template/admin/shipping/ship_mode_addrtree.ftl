<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜单列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${baseWeb}/scripts/jquery/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link href="${baseWeb}/scripts/jquery/ztree/zTreeStyle/zTreeIcons.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${baseWeb}/scripts/jquery/ztree/jquery-ztree-2.5.min.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/ztree/demoTools.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/menuTree.js"></script>
<script type="text/javascript">
	var url="${base}/shipping/ship_zone_tree.action?shipModeId=${id}";
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
	    reloadTree();
		expandAll(false);
		var nodea = zTree1.getNodeByParam('id',130215);
    	zTree1.updateNode(nodea, $("#connTrue").attr("checked"));
		$("#btn_submit").click(function(){
			var formelement = $("#inputForm");
			var nodes = zTree1.getCheckedNodes(true); 
			var state,city;
			$.each(nodes,function(i,n){
				var county = nodes[i].id
				if(county.length==6){
					formelement.append("<input type='hidden' name='shipZone' value='"+county.substring(0,2)+","+county.substring(0,4)+","+county+"'/>");
				}
			});
			
			formelement.submit();
		});
	});
</script>
<#if !id??>
	<#assign isAdd = true />
<#else>
	<#assign isEdit = true />
</#if>
</head>
<body class="input">

	<div class="body">
		<form id="inputForm" class="validate" action="<#if isAdd??>ship_mode!saveZones.action<#else>ship_mode!updateZones.action</#if>" method="post">
		<input type="hidden" name="id" value="${id}"/>
		<div class="zTreeDemoBackground">
			<ul id="treeDemo" class="tree"></ul>
		</div>
		<div class="buttonArea">
				<input type="button" id="btn_submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		</div>
		</form>
	</div>
</body>
</html>