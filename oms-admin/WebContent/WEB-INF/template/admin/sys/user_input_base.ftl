<table class="inputTable">
				<tr>
					<th>
						用户名:
					</th>
					<td>
						<#if isAdd??>
							<input type="text" name="user.userName" class="formText {required: true, userName: true, remote: 'user!checkUsername.action', minlength: 2, maxlength: 20, messages: {remote: '用户名已存在,请重新输入!'}}" title="用户名只允许包含中文、英文、数字和下划线" />
							<label class="requireField">*</label>
						<#else>
							${(user.userName)!}
							<input type="hidden" name="user.userName" value="${(user.userName)!}" />
						</#if>
					</td>
				</tr>
				<tr>
					<th>
						密 码:
					</th>
					<td>
						<input type="password" name="user.password" id="password" <#if isAdd??>class="formText {required: true, minlength: 4, maxlength: 20}"<#else>class="formText {minlength: 4, maxlength: 20}"</#if> title="密码长度只允许在4-20之间" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						重复密码:
					</th>
					<td>
						<input type="password" name="rePassword" class="formText {equalTo: '#password', messages: {equalTo: '两次密码输入不一致!'}}" />
						<#if isAdd??><label class="requireField">*</label></#if>
					</td>
				</tr>
				<tr>
					<th>
						E-mail:
					</th>
					<td>
						<input type="text" name="user.email" class="formText {required: true, email: true}" value="${(user.email)!}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
				管理角色:
					</th>
					<td>
						<#list allRole as role>
							<label>
								<input type="checkbox" name="roleList.id" class="{required: true, messages: {required: '请至少选择一个角色!'}, messagePosition: '#roleMessagePosition'}" value="${role.id}" <#if (user.roles?seq_contains(role))!> checked="checked"</#if> />
								${(role.name)!}
							</label>
						</#list>
						<span id="roleMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						是否启用:
					</th>
					<td>
						<label><input type="radio" name="user.isEnabled" value="true"<#if (isAdd || user.getIsEnabled() == true)!> checked</#if> />是</label>
						<label><input type="radio" name="user.isEnabled" value="false"<#if (user.getIsEnabled() == false)!> checked</#if> />否</label>
					</td>
				</tr>
				<#if isEdit??>
					<tr>
						<th>&nbsp;</th>
						<td>
							<span class="warnInfo"><span class="icon">&nbsp;</span>如果要修改密码,请填写密码,若留空,密码将保持不变!</span>
						</td>
					</tr>
				</#if>
				<tr>
					<th>
				所属商品部门:
					</th>
					<td>
						<#list allDept as dept>
							<label>
								<input type="checkbox" name="deptList.id" value="${dept.id}" <#if (user.depts?contains(dept))!> checked="checked"</#if> />
								${(dept.deptName)!}
							</label>
						</#list>
						<span id="deptMessagePosition"></span>
					</td>
				</tr>
				</table>