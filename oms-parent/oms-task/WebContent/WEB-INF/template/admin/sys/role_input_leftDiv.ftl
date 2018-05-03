<div class="leftDiv">
			<div class="inputBar"><h2>基本属性</h2></div>
			
			<table class="inputTable tabContent">
				<tr>
					<th>角色名称:</th>
					<td><input type="text" name="role.name" class="formText {required: true, remote: 'role!checkName.action?oldValue=${(role.name?url)!}', messages: {remote: '角色名称已存在!'}}" value="${(role.name)!}" /><label class="requireField">*</label></td>
				</tr>
				<tr>
					<th>角色标识:</th>
					<td>
						<input type="text" name="role.value" maxlength="20" class="formText {required: true, minlength: 6, maxlength: 20, prefix: 'ROLE_', remote: 'role!checkValue.action?oldValue=${(role.value?url)!}', messages: {remote: '角色标识已存在!'}}" value="${(role.value)!'ROLE_'}" title="角色标识长度不能小于6,不能多于20,且必须以ROLE_开头" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						描述:
					</th>
					<td>
						<input type="text" name="role.description" class="formText" value="${(role.description)!}" />
					</td>
				</tr>
				<#if (role.isSystem)!false>
					<tr>
						<th>&nbsp;</th>
						<td><span class="warnInfo"><span class="icon">&nbsp;</span>系统提示：</b>系统内置角色不允许修改!</span></td>
					</tr>
				</#if>
			</table>
			
	
			<div class="operateBar">
				<input type="button" class="aButton"  value="添加资源" onclick="addRole()"/>
			</div>
			<div class="list roleResourceDiv">
				
				<table class="listTable">
				<tbody id="resourceTbody">
					<tr>
						<th class="check"><input type="checkbox" class="allCheck" /></th>
						<th><span class="sort" name="name">资源名称</span></th>
						<th><span class="sort" name="value">资源值</span></th>
						<th><span class="sort" name="description">描述</span></th>
					</tr>		
					<#if !isAdd?? >
					<#list role.resources as list>
						<tr>
						<td><input type="checkbox" name="ids"/><input type="hidden" value="${list.id}" name="resourceIds"></td>
						<td>${list.name}</td>
						<td>${list.value}</td>
						<td>${list.description}</td>
						</tr>
					</#list>
					</#if>
				</tbody>
			</table>
			</div>
			<div class="buttonBar">
				<input type="button" class="formButton"  value="删除资源" onclick="delRole()"  />
			</div>
</div>
<script>
	function addRole(){
		$("<iframe id='editFrame' src="+IBM.base+"/sys/resource!list.action?ajax=true />").dialog(
		{   autoOpen: true,
                width: 600,
                height: 500,
                modal: false,
                resizable: true,
                autoResize: true,
				close: function(event, ui) { $(this).dialog('destroy') },
				open: function (event,ui) {$(this).css('width',"100%").css('padding',"0")},
		  title: "资源" 
		});
	}
	function delRole(){
		$(":checked").each(function(){
			var $delObj=$(this);
			$("input:hidden").each(function(){
		  		if($(this).val()===$delObj.val()){
		  			$(this).remove();
		  		}
		  	});
		});
		$($(":checked").parent().parent()).remove();
	}
</script>