		<div id="retInfo" style="display:none">
			   <table border="1px" class="inputTable tabContent">
			   	<tr class="title">
			   	  <th>原支付方式</th>
			   	  <td>
				   	<#if order.originalOrderPayList?exists>
					<#list order.originalOrderPayList as list>
					   <#list refundMethodList as modelist> 
							<#if modelist.id == list.payCode> 
								${modelist.name}
							</#if>	
						</#list>
					  &nbsp;￥${list.payAmount?default(0)}
					  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   	</#list>
				   	</#if> 
			   	   </td>
			   	</tr>
			   </table>
			   <br/>
			   <table class="inputTable tabContent" id="retInfoTab">
			   <tr class="title">
			     <th>退款方式</th>
			     <th>退款金额</th>
			     <#if !isDetail && order.statusConfirm != '0807' && !(((orderCategory == 'ref') || (orderCategory == 'tsf')))>
			     <th>操作</th>
			     </#if>
			   </tr>
			   
			 
			 <input type="hidden" id="statusConfirmFlag" value="${order.statusConfirm}"/>
			 
			<!-- 创建页 begin -->	
			 <#if isCreated>
				<input type="hidden" name="deleteOrderPayIds" id="deleteOrderPayIds_0"/>
				<!-- 国贸云店退款方式 begin -->
				<#if order.merchantNo =='y00124'>
					<input type="hidden" name="orderPayFlag"/>
				<#list order.originalOrderPayList as list>
				<!-- 301：优惠券  无需退 -->
				<#if list.payCode != '301'>
					<tr>
						<td>
						  <select name="orderPayLists[${list_index}].payCode">
								<#list refundMethodList as modelist> 
									<#if modelist.id != '1' && modelist.id != '830' && modelist.id != '301' && modelist.id != '50600'>
										<option value="${modelist.id}" <#if modelist.id == list.payCode> selected </#if>>
											<#if modelist.id == "">
											请选择
											<#else>
												${modelist.name}
											</#if>
										</option>
									</#if>
								</#list>
							</select>
						</td>
						<td>
					   		<input type="text" name="orderPayLists[${list_index}].payAmount"    class="formText {required: true, min: 0}"  onblur="javascript:changePayAmount(this)" value="${list.payAmount?default(0)}" />
						</td>
						<td>
						   <input type="button" class="formButton" value="删除" hidefocus="true" onclick="javascript:deletePayRow(this,${list_index},${list.id})"/>
						</td>
					</tr>
				</#if>
				</#list>
				<!-- 国贸云店退款方式 end -->
				<#else>
				<!-- 其它退款方式 begin -->
			    <tr>
					<td>
						<input type="hidden" name="orderPayFlag"/>
					  <select name="orderPayLists[0].payCode">
							<#list refundMethodList as modelist> 
								<#if modelist.id != '1' && modelist.id != '830' && modelist.id != '301' && modelist.id != '50600'>
									<option value="${modelist.id}" <#if modelist.id == '303'> selected </#if>>
										<#if modelist.id == "">
										请选择
										<#else>
											${modelist.name}
										</#if>
									</option>
								</#if>
							</#list>
						</select>
					</td>
					<td>
				   		<input type="text" name="orderPayLists[0].payAmount" id="orderPayPayAmount_0"   class="formText {required: true, min: 0}"  onblur="javascript:changePayAmount(this)" value="${list.payAmount?default(0)}" />
					</td>
					<td>
					   <input type="button" class="formButton" value="删除" hidefocus="true" style="color:gray;" disabled/>
					</td>
				</tr>
				<!-- 其它退款方式 end -->
				</#if>
			<!-- 创建页 end -->
			<!-- 退款中或退款完成 begin -->
			 <#elseif !isDetail && (order.statusTotal == '0270' || order.statusTotal == '0280') && ((orderCategory == 'ret') || (orderCategory == 'rej'))>
				<#list order.orderPays as list>
			   <input type="hidden" name="deleteOrderPayIds" id="deleteOrderPayIds_${list_index}"/>
			   <input type="hidden" name="orderPayLists[${list_index}].id" value="${(list.id)!}" />
			    <tr>
					<td>
						<input type="hidden" name="orderPayFlag"/>
					  <select name="orderPayLists[${list_index}].payCode">
							<#list refundMethodList as modelist> 
								<#if modelist.id != '1' && modelist.id != '830' && modelist.id != '301' && modelist.id != '50600'>
									<option value="${modelist.id}" <#if modelist.id == list.payCode> selected </#if>>
										<#if modelist.id == "">
										请选择
										<#else>
											${modelist.name}
										</#if>
									</option>
								</#if>
							</#list>
						</select>
					</td>
					<td>
					   	<input type="text" name="orderPayLists[${list_index}].payAmount" id="orderPayPayAmount_${list_index}"    class="formText {required: true, min: 0}"   value="${list.payAmount?default(0)}"  onblur="javascript:changePayAmount4Return(this)"/>
					</td>
				</tr>
				</#list>
			<!-- 退款中或退款完成 end -->	
			<!-- 详情页或已审核 begin -->
			 <#elseif isDetail || order.statusConfirm == '0807' && !((orderCategory == 'ref') || (orderCategory == 'tsf'))>
			 	<#list order.orderPays as list>
			 	<input type="hidden" name="orderPayFlag"/>
			    <tr>
					<td>
					  <select disabled name="orderPayLists[${list_index}].payCode">
							<#list refundMethodList as modelist> 
								<#if modelist.id != '1' && modelist.id != '830' && modelist.id != '301' && modelist.id != '50600'>
									<option value="${modelist.id}" <#if modelist.id == list.payCode> selected </#if>>
										<#if modelist.id == "">
										请选择
										<#else>
											${modelist.name}
										</#if>
									</option>
								</#if>
							</#list>
						</select>
					</td>
					<td>
						${list.payAmount?default(0)}
					</td>
				</tr>
				</#list>
			<!-- 详情页或已审核 end -->	
			 <#else>
			 <!-- 修改页或其它 begin -->
				<#list order.orderPays as list>
			   <input type="hidden" name="deleteOrderPayIds" id="deleteOrderPayIds_${list_index}"/>
			   <input type="hidden" name="orderPayLists[${list_index}].id" value="${(list.id)!}" />
			    <tr>
					<td>
						<input type="hidden" name="orderPayFlag"/>
					  <select name="orderPayLists[${list_index}].payCode">
							<#list refundMethodList as modelist> 
								<#if modelist.id != '1' && modelist.id != '830' && modelist.id != '301' && modelist.id != '50600'>
									<option value="${modelist.id}" <#if modelist.id == list.payCode> selected </#if>>
										<#if modelist.id == "">
										请选择
										<#else>
											${modelist.name}
										</#if>
									</option>
								</#if>
							</#list>
						</select>
					</td>
					<td>
					   	<input type="text" name="orderPayLists[${list_index}].payAmount" <#if order.merchantNo !='y00124'>id="orderPayPayAmount_${list_index}"</#if>  <#if (orderCategory == 'ref')>readonly</#if>  class="formText {required: true, min: 0}"  onblur="javascript:changePayAmount(this)" value="${list.payAmount?default(0)}" />
					</td>
					<#if !(orderCategory == 'ref') && !(orderCategory == 'tsf')>
					<td>
					   <input type="button" class="formButton" value="删除" hidefocus="true"  onclick="javascript:deletePayRow(this,${list_index},${list.id})"/>
					</td>
					</#if>
				</tr>
				</#list>
			<!-- 修改页或其它 end -->	
			  </#if>
		
				</table>
				<#if !isDetail && order.statusConfirm != '0807' && !((orderCategory == 'ref') || (orderCategory == 'tsf'))>
					<div class="buttonArea">
					   <input type="button" class="formButton" value="增加" hidefocus="true"  id="addOrderPay" onclick="addOrderPayRow()"/>&nbsp;&nbsp;&nbsp;&nbsp;
				    </div>
			    </#if>
	</div>