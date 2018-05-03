	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>资源列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/admin/js/list.js"></script>
</head>
<#if ajax == 'true'>
	<#assign ajax = true />
<#else>
	<#assign ajax = false />
</#if>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>资源管理&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="resource!list.action" method="post">
			<input type="hidden"  name="ajax" value="${ajax}"/>
			<div class="operateBar">
				<input type="button" class="addButton" onclick="location.href='resource!add.action?ajax=${ajax}'" value="添加资源" />
				<label>查找:</label>
				<select name="pager.property">
					<option value="name" <#if pager.property == "name">selected="selected" </#if>>
						资源名称
					</option>
					<option value="value" <#if pager.property == "value">selected="selected" </#if>>
						资源值
					</option>
				</select>
				<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button" id="searchButton" class="searchButton" value="" />
				<#if ajax != true >
				<label>每页显示:</label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10" <#if pager.pageSize == 10>selected="selected" </#if>>
						10
					</option>
					<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
						20
					</option>
					<option value="50" <#if pager.pageSize == 50>selected="selected" </#if>>
						50
					</option>
					<option value="100" <#if pager.pageSize == 100>selected="selected" </#if>>
						100
					</option>
				</select>
				</#if>
			</div>
			<table class="listTable">
				<tr>
					<th class="check"><input type="checkbox" class="allCheck" /></th>
					<th><span class="sort" name="name">资源名称</span></th>
					<th><span class="sort" name="value">资源值</span></th>
					<th><span class="sort" name="description">描述</span></th>
					<th>操作</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td><input type="checkbox" name="ids" value="${list.id}" /></td>
						<td>${list.name}</td>
						<td>${list.value}</td>
						<td>${list.description}</td>
						<td><a href="resource!edit.action?id=${list.id}&ajax=${ajax}" title="编辑">[编辑]</a></td>
					</tr>
				</#list>
			</table>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<#if ajax != true ><input type="button" class="deleteButton" url="resource!delete.action" value="删 除" disabled hidefocus="true" />
					<#else><input type="button" class="formButton" value="加到角色" onclick="addToRole()"/>
					</#if>
					<#include "/WEB-INF/template/admin/pager.ftl" />
				</div>
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		</form>
	</div>
<script>
	function addToRole(){
		var resourceTbody =$(window.parent.document).find("#resourceTbody");
		var resources =$(window.parent.document).find("#resources");
		var resIds="";
		var warnMsg="";
		$("input:checked[name=ids]").each(function(index) {
			var addId = $(this).val();
			var flag=false;
			$(resourceTbody).find("input[name='resourceIds']").each(function(i){
				if(this.value == addId ){
					flag=true;
					if(warnMsg==""){
						warnMsg="添加成功！部分资源已添加过，不再重复添加！";
					}
				}
			});
			$(resources).find("input[name='resourceIds']").each(function(i){
				if(this.value == addId ){
					flag=true;
					if(warnMsg==""){
						warnMsg="添加成功！部分资源已添加过，不再重复添加！";
					}
				}
			});
			
			if(flag==false){
				var tr=$(this).parents()[1];
				var newTr = $(tr).clone();
				$($(newTr).children()[4]).remove();
				$(newTr).appendTo($(resourceTbody));
				resIds=resIds+"<input type='hidden' name='resourceIds' value='"+addId+"' />";
				$($(resources)).html($(resources).html()+resIds);
			}
			
		});
		if(warnMsg!=""){
			alert(warnMsg);
		}	
	}
</script>
</body>
</html>