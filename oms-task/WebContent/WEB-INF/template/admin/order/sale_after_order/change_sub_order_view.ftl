	<div class="body">
		 <div id="tabs">
			<ul>
				<li><a href="#orderItem">内容</a></li>
				<li><a href="#customerInfo">客户信息</a></li>
				<li><a href="#deliverInfo">邮寄信息</a></li>
			</ul>
			
		   <div id="orderItem" style="display:block">
			<table class="inputTable tabContent">
				<tr class="title">
					<th>订单明细号</th>
					<th>SKU</th>
					<th>换货数量</th>
					<th>折前单价</th>
					<th>积分</th>
					<th>优惠金额</th>
					<th>券使用金额</th>
					<th>折后单价</th>
					<th>总价</th>
					<!--
					<th>单位重量</th>
					<th>总重量</th>
					-->
					<th>原因</th>
					<th>详细原因</th>
					<#if (order.statusConfirm != '0807')>
					<th>删除</th>
					</#if>
				</tr>
				<#if orderSub.orderRetChgItems?exists>
				<#list orderSub.orderRetChgItems as list>
				 <input type="hidden" name="deleteIds" id="deleteIds_${list_index}"/>
				     <tr name="orderitemlist">
						<td>
							${list.orderItemNo}
							<input type="hidden" name="orderRetChgItemsList[${list_index}].orderItemNo" value="${list.orderItemNo}" />
							<input type="hidden" name="orderRetChgItemsList[${list_index}].id" id="orderRetChgItemsListId_${list_index}" value="${list.id}" />
						</td>
						<td>
							${list.skuNo!}
							<input type="hidden" name="orderItemsList[${list_index}].skuNo"  id="skuNo_${list_index}"  value="${list.skuNo!}" />
						</td>
						<td>
							<#if order.statusConfirm == '0807'>
							${list.saleNum?default(0)}
							<input type="hidden" name="orderRetChgItemsList[${list_index}].saleNum" value="${list.saleNum?default(0)}" />
							<#else>
							<input type="text" style="width:60px;" name="orderRetChgItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum4ModifyPage(${list_index})" class="formText {required: true, min: 0}" value="${list.saleNum?default(0)}" />
						    <input type="hidden" id="remainNum_${list_index}" value="${list.remainNum}"/>
						    <input type="hidden" id="originalNum_${list_index}" value="${list.saleNum}"/>
						    </#if>
						</td>
						<td>
							${list.unitPrice?default(0)}
							<input type="hidden"  id="unitPrice_${list_index}"  value="${list.unitPrice?default(0)}" />
						</td>
						<td>
							${list.productPoint?default(0)}
							<input type="hidden"  id="productPoint_${list_index}" value="${list.productPoint?default(0)}" />
						</td>
						<td>
							${list.unitDiscount?default(0)}
							<input type="hidden"  id="unitDiscount_${list_index}" value="${list.unitDiscount?default(0)}" />
						</td>
						<td>
							${list.couponAmount?default(0)}
							<input type="hidden" id="couponAmount_${list_index}"  value="${list.couponAmount?default(0)}" />
						</td>
						<!--<td id="unitDeductedPrice_${list_index}">
							 
						</td>
						<td id="payAmount_${list_index}">
						    
						</td>-->
						<td>
							${list.unitDeductedPrice?default(0)}
						</td>
						<td>
							${list.payAmount?default(0)}
						</td>
						<!--
						<td>
						    <#if list.weight?exists&&list.saleNum?exists&&(list.saleNum>0)>
						     <#assign weightUnit = list.weight/list.saleNum>
						  <#else>
						     <#assign weightUnit = 0>
						  </#if>
							${weightUnit?default(0)}
							<input type="hidden" id="weightUnit_${list_index}" value="${weightUnit?default(0)}"/>
						</td>
						<td id="weight_${list_index}">
						  ${list.weight?default(0)}
						</td>
						-->
						<td>
						  <select  id="preRefundReason_${list_index}" onchange="javascript:getRefundReasonFun(${list_index})" <#if order.statusConfirm == '0807'>disabled<#else>name="orderRetChgItemsList[${list_index}].preRefundReason"</#if>>
						    <#list preRefundReasonList as preRefundReason>
							<#if preRefundReason.reasonNo=="">
						    	<option value="">请选择</option>
						    <#else>
								<option value="${preRefundReason.reasonNo}" <#if preRefundReason.reasonNo == preReasonMap.get(list.reason)> selected </#if>>
									${preRefundReason.reasonName}
								</option>
							 </#if>   
							 <#if (order.statusConfirm == '0807') && (preRefundReason.reasonNo == preReasonMap.get(list.reason))>
							    	<input type="hidden" name="orderRetChgItemsList[${list_index}].preRefundReason"  value="${preRefundReason.reasonNo}" />
							 </#if>  
						   </#list>
					       </select>
					   </td>
					   <td>
						  <select id="refundReason_${list_index}" <#if order.statusConfirm == '0807'>disabled<#else>name="orderRetChgItemsList[${list_index}].reason"</#if>>
						   <#list reasonMap.get(list.reason) as detailReason>
						     <option value="${detailReason.reasonNo}" <#if detailReason.reasonNo == list.reason> selected </#if>>
								${detailReason.reasonName}
							 </option>
							 <#if (order.statusConfirm == '0807') && (detailReason.reasonNo == list.reason)>
						    	<input type="hidden" name="orderRetChgItemsList[${list_index}].reason"  value="${detailReason.reasonNo}" />
						     </#if> 
						   </#list>  
					       </select>
					    </td>
					    <#if (order.statusConfirm != '0807')>
						<td id="deleteRow_${list_index}">
							<input type="button" class="formButton" onclick="javascript:deleteRow(this,${list_index})" value="删除" hidefocus="true" id="deleteButton_${list_index}"/>
						</td>
						</#if> 
					</tr>
				</#list>
				</#if>
			</table>
			</div>
			 
			 
			<div id="customerInfo" style="display:none">
			  <table class="inputTable tabContent">
				 <tr>
				    <th>
						配送地区:
					</th>
					<td>	
						<#if (order.statusConfirm == '0807')>
						${orderSub.combinedAddress}
						<#-- ${orderSub.addressCode!} -->
						<input type="hidden" name="orderSub.addressCode" value="${orderSub.addressCode}" />
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
						<#if (order.statusConfirm == '0807')>
						${orderSub.addressDetail}
						<input type="hidden" name="orderSub.addressDetail" value="${orderSub.addressDetail}" />
						<#else>
						<input type="text" name="orderSub.addressDetail" class="formText {required: true}" value="${orderSub.addressDetail}" />
						</#if> 
					</td>				
				 </tr>
					
				 <tr>	
					<th>
						邮政编码:
					</th>
					<td>
						<#if (order.statusConfirm == '0807')>
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
						<#if (order.statusConfirm == '0807')>
						${orderSub.userName}
						<input type="hidden" name="orderSub.userName" value="${orderSub.userName}" />
						<#else>
						<input type="text" name="orderSub.userName" class="formText {required: true}" value="${orderSub.userName}" />
						</#if> 
					</td>
				 </tr>
				 
				  <tr>	
					<th>
						电话:
					</th>
					<td>
						<#if (order.statusConfirm == '0807')>
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
						<#if (order.statusConfirm == '0807')>
						${orderSub.mobPhoneNum}
						<input type="hidden" name="orderSub.mobPhoneNum" value="${orderSub.mobPhoneNum}" />
						<#else>
						<input type="text" name="orderSub.mobPhoneNum" class="formText {mobile: true, messages: {mobile: '手机格式错误！'}}" value="${orderSub.mobPhoneNum}"  />
						</#if>
					</td>
				 </tr>
				</table>
			</div>
			 
			
			 
			<#include "/WEB-INF/template/admin/order/sale_after_order/detail_order_delivery_page.ftl">
			
	  </div>
	</div>
