	
	<div class="body">
		 <div id="tabs"  style="width: 100%;">
			<ul>
				<li><a href="#orderItem">内容</a></li>
				<li><a href="#customerInfo">客户信息</a></li>
				<li><a href="#retInfo">退款信息</a></li>
				<li><a href="#deliverInfo">邮寄信息</a></li>
			</ul>
		   <div id="orderItem" style="display:block">
			<table class="inputTable tabContent">
				<tr class="title">
				    <th class="check" style="width:10px;"><input type="checkbox" id="allCheck" /></th>
					<th>订单明细号</th>
					<th>SKU</th>
					<th>退货数量</th>
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
					<th>操作</th>
				</tr>
				<#if orderSub.orderItems?exists>
				<#list orderSub.orderItems as list>
				 <#if (list.remainNum > 0) >
					<tr name="orderitemlist">
					   <td><input type="checkbox" name="ids" value="${list_index}" onclick="javascript:caculateCheckBox()"/></td>
							<input type="hidden" name="orderId" value="${list.id}" />
						<td>
							${list.orderItemNo}
							<input type="hidden" id="orderItemNo_${list_index}" name="orderItemsList[${list_index}].orderItemNo" value="${list.orderItemNo}" />
							<input type="hidden" name="orderItemsList[${list_index}].id" value="${list.id}" />
						</td>
						<td>
							${list.skuNo!}
							<input type="hidden" name="orderItemsList[${list_index}].skuNo"  id="skuNo_${list_index}"  value="${list.skuNo!}" />
						</td>
						<td>
							<input type="text" style="width:60px;" name="orderItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum(${list_index})" class="formText {required: true, min: 0}" value="${list.remainNum?default(0)}" />
							<input type="hidden" id="remainNum_${list_index}" value="${list.remainNum}"/>
						</td>
						<td>
							<input type="text" style="width:60px;" name="orderItemsList[${list_index}].unitPrice" id="unitPrice_${list_index}"  onblur="javascript:changeUnitPrice(${list_index})" class="formText {required: true, min: 0}" value="${list.unitPrice?default(0)}" />
						</td>
						<td>
							<input type="text" style="width:60px;" name="orderItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="${list.productPoint?default(0)}" />
						</td>
						<td>
							<input type="text" style="width:60px;" name="orderItemsList[${list_index}].unitDiscount" id="unitDiscount_${list_index}"  onblur="javascript:changeUnitDiscount(${list_index})" class="formText {required: true, min: 0}" value="${list.unitDiscount?default(0)}" />
						</td>
						<td>
							<input type="text" style="width:60px;" name="orderItemsList[${list_index}].couponAmount" id="couponAmount_${list_index}"  onblur="javascript:changeCouponAmount(${list_index})" class="formText {required: true, min: 0}" value="${list.couponAmount?default(0)}" />
						</td>
						<td id="unitDeductedPrice_${list_index}">
							 
						</td>
						<td id="payAmount_${list_index}">
					    
						</td>
						
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
						  <select name="orderItemsList[${list_index}].preRefundReason" id="preRefundReason_${list_index}" onchange="javascript:getRefundReasonFun(${list_index})">
						    <#list preRefundReasonList as preRefundReason>
						    <#if preRefundReason.reasonNo=="">
						    	<option value="">请选择</option>
						    <#else>
						    	<option value="${preRefundReason.reasonNo}">
								${preRefundReason.reasonName}
								</option>
						    </#if>
						   </#list>
					       </select>
						</td>
						<td>
						  <select name="orderItemsList[${list_index}].refundReason" id="refundReason_${list_index}">
						   
					       </select>
						</td>
						<td>
						   <input type="button" class="formButton" value="删除" hidefocus="true"  onclick="javascript:deleteItemRow(this,${list_index},${list_index})"/>
						</td>
					</tr>
				  </#if>	
				</#list>
					<#if (orderSub.totalRemainNum > 0)>
					<input type="button" class="formButton"  value="批量删除" hidefocus="true"  onclick="javascript:batchDeleteItemRow()"/>
					<#else>
					  	<tr>
					  		<td colspan="13" style="text-align:center;color:red;">无可退商品！</td>
					  	</tr>
					</#if>
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
						<#include "/WEB-INF/template/admin/order/address_linkage.ftl">
						<!--
							<input type="text" id="addressCode" name="orderSub.addressCode" class="formText {required: true}" value="${orderSub.addressCode}" />
						-->
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
			
			<#include "/WEB-INF/template/admin/order/sale_after_order/detail_order_delivery_page.ftl"> 
			 
			
	  </div>
	</div>
