<table class="inputTable">
				<tr>
					<th>
						billType:
					</th>
					<td>
						<#if isAdd??>
							<input type="text" name="order.aliasOrderId" class="formText {required: true, userName: true, remote: 'user!checkUsername.action', minlength: 2, maxlength: 20, messages: {remote: '用户名已存在,请重新输入!'}}" title="用户名只允许包含中文、英文、数字和下划线" />
							<label class="requireField">*</label>
						<#else>
							<input type="text" name="order.billType" value="${(order.billType)!}" />
						</#if>
					</td>
				</tr>
	</table>