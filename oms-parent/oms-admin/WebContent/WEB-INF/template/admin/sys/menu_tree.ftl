<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>菜单列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${webuiPath}/scripts/jquery/ztree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
<link href="${webuiPath}/scripts/jquery/ztree/zTreeStyle/zTreeIcons.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webuiPath}/scripts/jquery/ztree/jquery-ztree-2.5.min.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/ztree/demoTools.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/menuTree.js"></script>

<script type="text/javascript">
	  var url="${base}/json/menu_json.action";
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
	expandAll(true);
});
</script>

</head>
<body>
	<div class="zTreeDemoBackground">
			<ul id="treeDemo" class="tree"></ul>
		</div>		
</body>
</html>