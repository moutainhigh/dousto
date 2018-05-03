<div id="customerInfo" style="display:none">
			  <table class="inputTable tabContent">
				 <tr>
				    <th>
						配送地区:
					</th>
					<td>
						<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.combinedAddress}
							<input type="hidden" name="orderSub.addressCode"  value="${orderSub.addressCode}" />
						<#else>
							<#include "/WEB-INF/template/admin/order/address_linkage.ftl">
							<!--
							<input type="text" name="orderSub.addressCode" class="formText {required: true}" value="${orderSub.addressCode}" />
					 		-->
					 	</#if>	
					</td>	
						
					<th>
						具体地址:
					</th>
					<td>
						<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.addressDetail}
							<input type="hidden" name="orderSub.addressDetail" value="${orderSub.addressDetail}" />
						<#else>
							<input type="text" name="orderSub.addressDetail" class="formText {required: true}" value="${orderSub.addressDetail}" />
					 		<label class="requireField">*</label>
					 	</#if>
					</td>				
				 </tr>
					
				 <tr>	
					<th>
						邮政编码:
					</th>
					<td>
						<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.postCode}
							<input type="hidden" name="orderSub.postCode" value="${orderSub.postCode}" />
						<#else>
							<input type="text" name="orderSub.postCode" class="formText {required: true, zipCode: true}" value="${orderSub.postCode}"  />
					 	</#if>
					</td>	
					
					<th>
						客户姓名:
					</th>
					<td>
						<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.userName}
							<input type="hidden" name="orderSub.userName" value="${orderSub.userName}" />
						<#else>
							<input type="text" name="orderSub.userName" class="formText {required: true}" value="${orderSub.userName}" />
					 		<label class="requireField">*</label>
					 	</#if>
					</td>
				 </tr>
				 
				  <tr>	
					<th>
						电话:
					</th>
					<td>
						<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.phoneNum}
							<input type="hidden" name="orderSub.phoneNum" value="${orderSub.phoneNum}" />
						<#else>
							<input type="text" name="orderSub.phoneNum" class="formText {requiredOne: '#shipMobile', phone: true, messages: {requiredOne: '电话、手机必须填写其中一项！'}}" value="${orderSub.phoneNum}" />
					 	</#if>
					</td>
					<th>
						手机:
					</th>
					<td>
						<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.mobPhoneNum}
							<input type="hidden" name="orderSub.mobPhoneNum" value="${orderSub.mobPhoneNum}" />
						<#else>
							<input type="text" name="orderSub.mobPhoneNum" class="formText {mobile: true, messages: {mobile: '手机格式错误！'}}" value="${orderSub.mobPhoneNum}"  />
					 		<label class="requireField">*</label>
					 	</#if>
					</td>
				 </tr>
				</table>
			</div>