	<div class="body">
		 <div id="tabs"  style="width: 110%;">
			<ul>
				<li><a href="#orderItem">内容</a></li>
				<li><a href="#customerInfo">客户信息</a></li>
				<li><a href="#retInfo">退款信息</a></li>
				<li><a href="#deliverInfo">邮寄信息</a></li>
			</ul>
		   <div id="orderItem" style="display:block">
			<table class="inputTable tabContent">
				<tr class="title">
				    <th class="check"><input type="checkbox" id="allCheck" /></th>
					<th>订单明细号</th>
					<th>退货数量</th>
					<th>折前单价</th>
					<th>积分</th>
					<th>优惠金额</th>
					<th>购物券使用金额</th>
					<th>折后单价</th>
					<th>总价</th>
					<!--
					<th>单位重量</th>
					<th>总重量</th>
					-->
					<th>原因</th>
					<th>详细原因</th>
					<th>操作</th>
				</tr>
				<#if orderSub.orderRetChgItems?exists>
				<#list orderSub.orderRetChgItems as list>
				    <input type="hidden" name="deleteIds" id="deleteIds_${list_index}"/>
					<tr name="orderitemlist">
						<td>
							${list.orderItemNo}
							<input type="hidden" name="orderRetChgItemsList[${list_index}].orderItemNo" value="${list.orderItemNo}" />
							<input type="hidden" name="orderRetChgItemsList[${list_index}].id" id="orderRetChgItemsListId_${list_index}"  value="${list.id}" />
							
						</td>
						<td>
							${list.skuNo!}
							<input type="hidden" name="orderItemsList[${list_index}].skuNo"  id="skuNo_${list_index}"  value="${list.skuNo!}" />
						</td>
						<td>
							<#if isDetail>
								${list.saleNum?default(0)}
							<#else>
								<input type="text"  style="width:80px;" name="orderRetChgItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum(${list_index})" class="formText {required: true, min: 0}" value="${list.saleNum?default(0)}" />
						 	</#if>
							<input type="hidden" id="remainNum_${list_index}" value="${list.remainNum}"/>
						</td>
						<td>
							<#if isDetail>
								${list.unitPrice?default(0)}
							<#else>
								<input type="text"  style="width:80px;" name="orderRetChgItemsList[${list_index}].unitPrice" id="unitPrice_${list_index}"  onblur="javascript:changeUnitPrice(${list_index})" class="formText {required: true, min: 0}" value="${list.unitPrice?default(0)}" />
						 	</#if>
						</td>
						<td>
							<#if isDetail>
								${list.productPoint?default(0)}
							<#else>
								<input type="text"  style="width:80px;" name="orderRetChgItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="${list.productPoint?default(0)}" />
						 	</#if>
						</td>
						<td>
							<#if isDetail>
								${list.unitDiscount?default(0)}
							<#else>
								<input type="text"  style="width:80px;" name="orderRetChgItemsList[${list_index}].unitDiscount" id="unitDiscount_${list_index}"  onblur="javascript:changeUnitDiscount(${list_index})" class="formText {required: true, min: 0}" value="${list.unitDiscount?default(0)}" />
						 	</#if>
						</td>
						<td>
							<#if isDetail>
								${list.couponAmount?default(0)}
							<#else>
								<input type="text"  style="width:80px;" name="orderRetChgItemsList[${list_index}].couponAmount" id="couponAmount_${list_index}"  onblur="javascript:changeCouponAmount(${list_index})" class="formText {required: true, min: 0}" value="${list.couponAmount?default(0)}" />
						 	</#if>
						</td>
						<#if isDetail>
								<td>${list.unitDeductedPrice?default(0)}</td>
						<#else>
							<td id="unitDeductedPrice_${list_index}">
							</td>
					 	</#if>
						<#if isDetail>
								<td>${list.payAmount?default(0)}</td>
						<#else>
							<td id="payAmount_${list_index}">
							</td>
					 	</#if>
						
						<!--
						<td>
						  <#if list.weight?exists&&list.saleNum?exists&&(list.saleNum>0)>
						     <#assign weightUnit = list.weight/list.saleNum>
						  <#else>
						     <#assign weightUnit = 0>
						  </#if>
							${weightUnit}
							<input type="hidden" id="weightUnit_${list_index}" value="${weightUnit}"/>
						</td>
						<td id="weight_${list_index}">
						  ${list.weight}
						</td>
						-->
						<td>
						  <select name="orderRetChgItemsList[${list_index}].preRefundReason" id="preRefundReason_${list_index}" onchange="javascript:getRefundReasonFun(${list_index})" <#if isDetail> disabled </#if>>
						    <#list preRefundReasonList as preRefundReason>
							<option value="${preRefundReason.reasonNo}" <#if preRefundReason.reasonNo == preReasonMap.get(list.reason)> selected </#if>>
								${preRefundReason.reasonName}
							</option>
						   </#list>
					       </select>
						</td>
						<td>
						  <select name="orderRetChgItemsList[${list_index}].reason" id="refundReason_${list_index}" <#if isDetail> disabled </#if>>
						   <#list reasonMap.get(list.reason) as detailReason>
						     <option value="${detailReason.reasonNo}" <#if detailReason.reasonNo == list.reason> selected </#if>>
								${detailReason.reasonName}
							</option>
							 </#list>  
					       </select>
						</td>
						<#if !isDetail> 
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
						<input type="text" id="addressCode" name="orderSub.addressCode" class="formText {required: true}" value="${orderSub.addressCode}" />
					</td>	
						
					<th>
						具体地址:
					</th>
					<td>
						<input type="text" id="addressDetail" name="orderSub.addressDetail" class="formText {required: true}" value="${orderSub.addressDetail}" />
					</td>				
				 </tr>
					
				 <tr>	
					<th>
						邮政编码:
					</th>
					<td>
						<input type="text" name="orderSub.postCode" class="formText {required: true, zipCode: true}" value="${orderSub.postCode}"  />
					</td>	
					
					<th>
						客户姓名:
					</th>
					<td>
						<input type="text" id="userName" name="orderSub.userName" class="formText {required: true}" value="${orderSub.userName}" />
					</td>
				 </tr>
				 
				  <tr>	
					<th>
						电话:
					</th>
					<td>
						<input type="text" name="orderSub.phoneNum" class="formText {requiredOne: '#shipMobile', phone: true, messages: {requiredOne: '电话、手机必须填写其中一项！'}}" value="${orderSub.phoneNum}" />
					</td>
					<th>
						手机:
					</th>
					<td>
						<input type="text" id="mobPhoneNum" name="orderSub.mobPhoneNum" class="formText {mobile: true, messages: {mobile: '手机格式错误！'}}" value="${orderSub.mobPhoneNum}"  />
					</td>
				 </tr>
				</table>
			</div> 
			 
			 
			<#include "/WEB-INF/template/admin/order/sale_after_order/detail_order_pay_page.ftl"> 
			 
			 
			<div id="deliverInfo" style="display:none">
			 <table class="inputTable tabContent">
			    <tr>
					<th>
						快递单号:
					</th>
					<td>
					   <input type="text" name="orderSub.logisticsOutNo" class="formText {required: true, min: 0}" value="${orderSub.logisticsOutNo}" />
					</td>
					<th>
						快递名称:
					</th>
					<td>
					    <input type="text" name="orderSub.expressType" class="formText {required: true, min: 0}" value="${orderSub.expressType}" />
					</td>
				</tr>
				</table>
			</div>
	  </div>
	</div>
