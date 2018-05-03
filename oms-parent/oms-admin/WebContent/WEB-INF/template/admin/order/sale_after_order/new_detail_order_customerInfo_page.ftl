<div id="customerInfo" style="display:none">
			  <table class="inputTable tabContent">
			  	<tr>	
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
				 </tr>
			  	<tr>			
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
				 </tr>
				 <tr>
				    <th>
						配送地区:
					</th>
					<td>
						<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.combinedAddress}
							<input type="hidden" name="orderSub.addressCode"  value="${orderSub.addressCode}" />
						<#else>
							<#include "/WEB-INF/template/admin/order/area_linkage.ftl">
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
				 <!-- 邮寄信息 -->
				<#if orderCategory != 'rej'>
					<tr>		
						<th>
							物流公司:
						</th>
						<td>
							<#if isDetail || (order.statusConfirm == '0807')>
							<select id="expressType" name="orderSub.expressType" onChange="logi(this);" disabled>				    			
				    			<#if companyList!=null&&(companyList?size>0)>
				        			<#list companyList as company>
				        				<option value="${company.code}" <#if (company.code == orderSub.expressType)!> selected</#if>>${company.name}</option>
				        			</#list>
				        		</#if>
				    			<option value="其它">其它...</option>
				    		</select>				    		
							<input type="hidden" name="orderSub.expressType" value="${orderSub.expressType}" />
							<#else>
							<select id="expressType" name="orderSub.expressType" onChange="logi(this);">
				    			<option value="">请选择物流公司</option>
				    			<#if companyList!=null&&(companyList?size>0)>
				        			<#list companyList as company>
				        				<option value="${company.code}" <#if (company.code == orderSub.expressType)!> selected</#if>>${company.name}</option>
				        			</#list>
				        		</#if>
				    			<option value="其它">其它...</option>
				    		</select>				    		
						 	</#if>
						</td>
						<th>
							快递单号:
						</th>
						<td>
							<#if isDetail || (order.statusConfirm == '0807')>
							${orderSub.logisticsOutNo}
							<input type="hidden" name="orderSub.logisticsOutNo" value="${orderSub.logisticsOutNo}" />
							<#else>
							<input type="text" name="orderSub.logisticsOutNo" class="formText {required: true, min: 0}"  value="${orderSub.logisticsOutNo}" />
						 	</#if>
						</td>
					</tr>
				</#if>
				</table>
			</div>