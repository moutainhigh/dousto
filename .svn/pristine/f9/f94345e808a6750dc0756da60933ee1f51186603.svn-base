	
	
	<div class="body">
		 <div id="tabs">
			<ul>
				<li><a href="#orderItem">内容</a></li>
				<li><a href="#customerInfo">客户信息</a></li>
				<#if orderCategory == 'ret' || (orderCategory == 'rej' && order.statusPay =='0420') || (isNeed == 1) || (order.ifNeedRefund == 1)>
					<!-- 退货、在线支付已支付拒收需退款 -->
	  				<li><a href="#retInfo">退款信息</a></li>
				</#if>
			</ul>
			
			<!-- 内容 -->
		   <div id="orderItem" style="display:block">
			<table class="inputTable tabContent">
				<tr class="title">
					<#if isCreated>
					<th class="check" style="width:10px;"><input type="checkbox" id="allCheck" /></th>
					</#if>
					<th style="width:150px;">订单明细号</th>
					<th style="width:110px;">SKU</th>
					<th style="width:350px;">商品</th>
					<th style="width:100px;">
					<#if orderCategory == 'ret' || orderCategory=='ret'>
						退货数量
					<#elseif orderCategory == 'chg' || orderCategory=='chg'>
					        换货数量
					<#elseif orderCategory == 'ref' || orderCategory=='ref'>
						退款数量
					<#elseif orderCategory == 'rej' || orderCategory=='rej'>
					        数量
					</#if>
					</th>
					<th style="width:100px;">折前单价</th>
					<th style="width:100px;">积分</th>
					<th style="width:100px;">优惠金额</th>
					<th style="width:100px;">券使用金额</th>
					<th style="width:100px;">折后单价</th>
					<th style="width:100px;">总价</th>
					<!--
					<th>单位重量</th>
					<th>总重量</th>
					-->
					<th style="width:160px;">原因</th>
					<!--th>详细原因</th-->
					<#if !isDetail && (order.statusConfirm != '0807') && orderCategory != 'rej' && orderCategory != 'chg'>
					<th style="width:50px;">删除</th>
					</#if>
				</tr>
				
				<!-- 创建页 begin -->
				<#if isCreated>
					<#if orderSub.orderItems?exists>
					<#list orderSub.orderItems as list>
					  <#assign isdiplay = "yes">
					  <#if (orderCategory != 'rej' && (list.remainNum <= 0))>
					  	 <#assign isdiplay = "no">
					  </#if>
					  
					 <#if isdiplay = "yes">
						<tr name="orderitemlist">
						   <td><input type="checkbox" name="ids" value="${list_index}" /></td>
								<input type="hidden" id="orderItemId_${list_index}" name="orderId" value="${list.id}" />
								<input type="hidden" id="productPropertyFlag_${list_index}"  value="${list.productPropertyFlag}" />
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
								<a href="${(configMap['btcUrl'])!}?skuId=${(list.skuId)!}" target="_blank"><font color="red">	${(list.commodityName)!} </font></a>
								<#list list.productPropertys as productProperty>
									<br/>
									${productProperty.propertyName}：<font color="blue">${productProperty.propertyValue}</font>
								</#list>
							</td>
							<td>
							   <#if orderCategory = "rej"> 
							   	  ${list.saleNum?default(0)}
								  <input type="hidden" style="width:60px;"  name="orderItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum(${list_index})" class="formText {required: true, min: 0}" value="${list.saleNum?default(0)}"  readonly/>
								<#else>
							  	 <input type="text" style="width:60px;"  name="orderItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum(${list_index})" class="formText {required: true, min: 0}" value="${list.remainNum?default(0)}" />
								</#if>
								<input type="hidden" id="remainNum_${list_index}" value="${list.remainNum}"/>
								<br/>
								<span id="submitColorSize_${list_index}"></span>
								<input type="hidden" id="submitSkuCode_${list_index}" name="submitSkuCode_${list.id}" value="${list.skuNo!}"/>
							</td>
							<td>
								${list.unitPrice?default(0)}
								<input type="hidden"  name="orderItemsList[${list_index}].unitPrice" id="unitPrice_${list_index}"  onblur="javascript:changeUnitPrice(${list_index})" class="formText {required: true, min: 0}" value="${list.unitPrice?default(0)}" />
							</td>
							<td>
								<#if list.productPoint?exists&&list.saleNum?exists&&(list.saleNum>0)>
							    	<#assign productPointUnit = list.productPoint/list.saleNum>
								<#else>
								    <#assign productPointUnit = 0>
								</#if><!--积分单价-->
								<#assign productPoint = list.remainNum*productPointUnit><!--单件商品应退总积分-->
								<#if orderCategory == "chg"> 
								${list.productPoint?default(0)}
								<input type="hidden" style="width:60px;"  name="orderItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="0"  readonly/>
								<#elseif orderCategory == "rej"> 
								${list.productPoint?default(0)}
								<input type="hidden" style="width:60px;"  name="orderItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="0" />
								<#else>
								<input type="text" style="width:60px;"  name="orderItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="${productPoint?default(0)}" />
								</#if>
								<input type="hidden" id="productPointUnit_${list_index}" value="${productPointUnit}"/>
							</td>
							<td>
								<#assign saleTotalMoney=(list.saleTotalMoney)!0>
								<#assign payAmount=(list.payAmount)!0>
								<#assign saleNum=(list.saleNum)!1>
								<#if (list.saleNum == 0)>
									<#assign saleNum=1>
								</#if>
								<!--${((saleTotalMoney-payAmount)/saleNum)?string.currency}-->
								<!--${list.unitDiscount?default(0)}-->
								<input type="text" style="width:60px;"  name="orderItemsList[${list_index}].unitDiscount" id="unitDiscount_${list_index}"  onblur="javascript:changeUnitDiscount(${list_index})" class="formText {required: true, min: 0}" value="${((saleTotalMoney-payAmount)/saleNum)!0}" />
							</td>
							<td>
								<#assign couponAmount=(list.couponAmount)!0>
								<#assign saleNum=(list.saleNum)!1>
								<#if (list.saleNum == 0)>
									<#assign saleNum=1>
								</#if>
								<!--${list.couponAmount?default(0)}-->
								<input type="text" style="width:60px;"  name="orderItemsList[${list_index}].couponAmount" id="couponAmount_${list_index}"  onblur="javascript:changeCouponAmount(${list_index})" class="formText {required: true, min: 0}" value="${(couponAmount/saleNum)!0}" />
							</td>
							<td id="unitDeductedPrice_${list_index}">
								 
							</td>
							<td id="payAmount_${list_index}">
						    
							</td>
														
							<td>
							  <select name="orderItemsList[${list_index}].preRefundReason" id="preRefundReason_${list_index}" onchange="javascript:getRefundReasonFun(${list_index})">
							    <#list preRefundReasonList as preRefundReason>
							    
							    <#if preRefundReason.reasonNo=="">
							    	
							    <#else>
							    	<option value="${preRefundReason.reasonNo}">
									${preRefundReason.reasonName}
									</option>
							    </#if>
							   </#list>
						       </select>
							</td>
							<!--td>
							  <select name="orderItemsList[${list_index}].refundReason" id="refundReason_${list_index}">
							   
						       </select>
							</td-->
							<#if orderCategory != 'rej'>
							<td>
							   <input type="button" class="formButton" value="删除" hidefocus="true"  onclick="javascript:deleteItemRow(this,${list_index},${list_index})"/>
							</td>
							</#if>
						</tr>
					  </#if>	
					</#list>
						<#if (orderSub.totalRemainNum > 0) && orderCategory != 'rej'>
						<input type="button" class="formButton"  value="批量删除" hidefocus="true"  onclick="javascript:batchDeleteItemRow()"/>
						<#else>
						  	<tr>
						  		<td colspan="13" style="text-align:center;color:red;">
						  		<#if (orderCategory == 'ret')>无可退商品！
						  		<#elseif (orderCategory == 'chg')>无可换商品！
						  		</#if>
						  		</td>
						  	</tr>
						</#if>
						<#if (orderSub.totalRemainNum > 0) && orderCategory == 'chg'>
						&nbsp;<input type="button" class="formButton"  value="选择色码款" hidefocus="true"  onclick="javascript:chooseColorSize()"/>
						</#if>
					</#if>
					<!-- 创建页 end -->
				
				<!-- 详情页或已审核 begin -->
				<#elseif isDetail || (order.statusConfirm == '0807')>
					<#if orderSub.orderRetChgItems?exists>
					<#list orderSub.orderRetChgItems as list>
						<input type="hidden" name="deleteIds" id="deleteIds_${list_index}"/>
						<input type="hidden" name="ids" value="${list_index}"/>
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
								<a href="${(configMap['btcUrl'])!}?skuId=${(list.skuId)!}" target="_blank"><font color="red">${(list.commodityName)!} </font></a>
								<#list list.productPropertys as productProperty>
									<br/>
									${productProperty.propertyName}：<font color="blue">${productProperty.propertyValue}</font>
								</#list>
								<#if (orderCategory == 'chg')>
								<br/>
								<font color="blue">换:</font>${list.adsPage}
								</#if>
							</td>
							<td>
								${list.saleNum?default(0)}
								<input type="hidden" name="orderRetChgItemsList[${list_index}].saleNum" id="saleNum_${list_index}" value="${list.saleNum?default(0)}" />
								<input type="hidden" id="remainNum_${list_index}" value="${list.remainNum}"/>
								<input type="hidden" id="originalNum_${list_index}" value="${list.saleNum}"/>
							</td>
							<td>
								${list.unitPrice?default(0)}
								<input type="hidden"   name="orderRetChgItemsList[${list_index}].unitPrice" id="unitPrice_${list_index}"   value="${list.unitPrice?default(0)}" />
							</td>
							<td>
								${list.productPoint?default(0)}
								<input type="hidden"   name="orderRetChgItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  value="${list.productPoint?default(0)}" />
							</td>
							<td>
								${list.unitDiscount?default(0)}
								<input type="hidden"   name="orderRetChgItemsList[${list_index}].unitDiscount" id="unitDiscount_${list_index}"  value="${list.unitDiscount?default(0)}" />
							</td>
							<td>
								${list.couponAmount?default(0)}
								<input type="hidden"   name="orderRetChgItemsList[${list_index}].couponAmount" id="couponAmount_${list_index}"  value="${list.couponAmount?default(0)}" />
							</td>
							<td>${list.unitDeductedPrice?default(0)}</td>
							<td>${list.payAmount?default(0)}</td>
														
							<td>
							  <select  id="preRefundReason_${list_index}" onchange="javascript:getRefundReasonFun(${list_index})" disabled>
							    <#list preRefundReasonList as preRefundReason>
									<option value="${preRefundReason.reasonNo}" <#if preRefundReason.reasonNo == list.reason> selected </#if>>
										${preRefundReason.reasonName}
									</option>
							   </#list>
						       </select>
							   <input type="hidden" name="orderRetChgItemsList[${list_index}].reason"  value="${list.reason}" />
							</td>
							<!--td>
							  <select  id="refundReason_${list_index}" disabled>
							   <#list reasonMap.get(list.reason) as detailReason>
							     <option value="${detailReason.reasonNo}" <#if detailReason.reasonNo == list.reason> selected </#if>>
									${detailReason.reasonName}
								</option>
								 </#list>  
						       </select>
							   <input type="hidden" name="orderRetChgItemsList[${list_index}].reason"  value="${list.reason}" />
							</td-->
						</tr>
					</#list>
					</#if>
				<!-- 详情页或已审核 end -->
				
				
				
				
				
				<#elseif isModify>
				
				
				
				<!-- 修改页 begin -->
				<#if orderSub.orderRetChgItems?exists>
				<#list orderSub.orderRetChgItems as list>
					<input type="hidden" name="deleteIds" id="deleteIds_${list_index}"/>
					<!-- 如果是换货，则明细不能修改 -->
					<!-- begin 修改换货 -->
					<#if (orderCategory == 'chg')>
						<tr name="orderitemlist">
						<td>
							${list.orderItemNo}
							<input type="hidden" name="orderRetChgItemsList[${list_index}].orderItemNo" value="${list.orderItemNo}" />
							<input type="hidden" name="orderRetChgItemsList[${list_index}].id" id="orderRetChgItemsListId_${list_index}"  value="${list.id}" />
							<input type="hidden" id="orderItemNo_${list_index}"  value="${list.orderItemNo}" />
							<input type="hidden" name="ids" value="${list_index}"/>
						</td>
						<td>
							${list.skuNo!}
							<input type="hidden" name="orderItemsList[${list_index}].skuNo"  id="skuNo_${list_index}"  value="${list.skuNo!}" />
						</td>
						<td>
							<a href="${(configMap['btcUrl'])!}?skuId=${(list.skuId)!}" target="_blank"><font color="red">	${(list.commodityName)!} </font></a>
								<#list list.productPropertys as productProperty>
									<br/>
									${productProperty.propertyName}：<font color="blue">${productProperty.propertyValue}</font>
								</#list>
								<#if (orderCategory == 'chg')>
								<br/>
								<font color="blue">换:</font>${list.adsPage}
								</#if>
						</td>
						<td>
							${list.saleNum?default(0)}
							<input type="hidden" style="width:60px;"  name="orderRetChgItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum4ModifyPage(${list_index})" class="formText {required: true, min: 0}" value="${list.saleNum?default(0)}"/>
							<input type="hidden" id="remainNum_${list_index}" value="${list.remainNum}"/>
							<input type="hidden" id="originalNum_${list_index}" value="${list.saleNum}"/>
						</td>
						<td>
							${list.unitPrice?default(0)}
							<input type="hidden"   name="orderRetChgItemsList[${list_index}].unitPrice" id="unitPrice_${list_index}"  onblur="javascript:changeUnitPrice(${list_index})" class="formText {required: true, min: 0}" value="${list.unitPrice?default(0)}" />
						</td>
						<td>
							${list.productPoint?default(0)}
							<input type="hidden"  style="width:60px;" name="orderRetChgItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="0"  readonly/>
						</td>
						<td>
							<!--${list.unitDiscount?default(0)}-->
							<input type="text"  style="width:60px;"  name="orderRetChgItemsList[${list_index}].unitDiscount" id="unitDiscount_${list_index}"  onblur="javascript:changeUnitDiscount(${list_index})" class="formText {required: true, min: 0}" value="${list.unitDiscount?default(0)}" readonly/>
						</td>
						<td>
							<!--${list.couponAmount?default(0)}-->
							<input type="text"  style="width:60px;"  name="orderRetChgItemsList[${list_index}].couponAmount" id="couponAmount_${list_index}"  onblur="javascript:changeCouponAmount(${list_index})" class="formText {required: true, min: 0}" value="${list.couponAmount?default(0)}" readonly/>
						</td>
						<td id="unitDeductedPrice_${list_index}">
						</td>
						<td id="payAmount_${list_index}">
						</td>
												
						<td>
							<input type="hidden" name="orderRetChgItemsList[${list_index}].reason" value="${list.reason}"/>
						  <select  id="preRefundReason_${list_index}" onchange="javascript:getRefundReasonFun(${list_index})" name="orderRetChgItemsList[${list_index}].reason" disabled>
						    <#list preRefundReasonList as preRefundReason>
								<option value="${preRefundReason.reasonNo}" <#if preRefundReason.reasonNo == list.reason> selected </#if>>
									${preRefundReason.reasonName}
								</option>
						   </#list>
					       </select>
						</td>
						<!--td>
							<input type="hidden" name="orderRetChgItemsList[${list_index}].reason" value="${list.reason}"/>
						  <select  id="refundReason_${list_index}" name="orderRetChgItemsList[${list_index}].reason" disabled>
						   <#list reasonMap.get(list.reason) as detailReason>
						     <option value="${detailReason.reasonNo}" <#if detailReason.reasonNo == list.reason> selected </#if>>
								${detailReason.reasonName}
							</option>
							 </#list>  
					       </select>
						</td-->
					</tr>
					<!-- end 修改换货 -->
					<#else>
					<!-- begin 修改退货、拒收 -->
					<tr name="orderitemlist">
						<td>
							${list.orderItemNo}
							<input type="hidden" name="orderRetChgItemsList[${list_index}].orderItemNo" value="${list.orderItemNo}" />
							<input type="hidden" name="orderRetChgItemsList[${list_index}].id" id="orderRetChgItemsListId_${list_index}"  value="${list.id}" />
							<input type="hidden" id="orderItemNo_${list_index}"  value="${list.orderItemNo}" />
							<input type="hidden" name="ids" value="${list_index}"/>
						</td>
						<td>
							${list.skuNo!}
							<input type="hidden" name="orderItemsList[${list_index}].skuNo"  id="skuNo_${list_index}"  value="${list.skuNo!}" />
						</td>
						<td>
							<a href="${(configMap['btcUrl'])!}?skuId=${(list.skuId)!}" target="_blank"><font color="red">	${(list.commodityName)!} </font></a>
								<#if (orderCategory == 'chg')>
								<br/>
								<font color="blue">换:</font>${list.adsPage}
								</#if>
						</td>
						<td>
							<#if (orderCategory == 'rej')>
							${list.saleNum?default(0)}
							<input type="hidden" style="width:60px;"  name="orderRetChgItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum4ModifyPage(${list_index})" class="formText {required: true, min: 0}" value="${list.saleNum?default(0)}"  readonly/>
							<#else>
							<input type="text" style="width:60px;"  name="orderRetChgItemsList[${list_index}].saleNum" id="saleNum_${list_index}" onblur="javascript:changeSaleNum4ModifyPage(${list_index})" class="formText {required: true, min: 0}" value="${list.saleNum?default(0)}" />
							<input type="hidden" id="remainNum_${list_index}" value="${list.remainNum}"/>
							<input type="hidden" id="originalNum_${list_index}" value="${list.saleNum}"/>
							</#if>
						</td>
						<td>
							${list.unitPrice?default(0)}
							<input type="hidden"   name="orderRetChgItemsList[${list_index}].unitPrice" id="unitPrice_${list_index}"  onblur="javascript:changeUnitPrice(${list_index})" class="formText {required: true, min: 0}" value="${list.unitPrice?default(0)}" />
						</td>
						<td>
							<#if (orderCategory == 'chg')>
							${list.productPoint?default(0)}
							<input type="hidden"  style="width:60px;" name="orderRetChgItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="0"  readonly/>
							<#elseif (orderCategory == 'rej')>
							${list.productPoint?default(0)}
							<input type="hidden"  style="width:60px;" name="orderRetChgItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="0" />
							<#else>
							<input type="text"  style="width:60px;" name="orderRetChgItemsList[${list_index}].productPoint" id="productPoint_${list_index}"  onblur="javascript:changeProductPoint(${list_index})" class="formText {required: true, min: 0}" value="${list.productPoint?default(0)}" />
							</#if>
						</td>
						<td>
							<!--${list.unitDiscount?default(0)}-->
							<input type="text"  style="width:60px;"  name="orderRetChgItemsList[${list_index}].unitDiscount" id="unitDiscount_${list_index}"  onblur="javascript:changeUnitDiscount(${list_index})" class="formText {required: true, min: 0}" value="${list.unitDiscount?default(0)}" />
						</td>
						<td>
							<!--${list.couponAmount?default(0)}-->
							<input type="text"  style="width:60px;"  name="orderRetChgItemsList[${list_index}].couponAmount" id="couponAmount_${list_index}"  onblur="javascript:changeCouponAmount(${list_index})" class="formText {required: true, min: 0}" value="${list.couponAmount?default(0)}" />
						</td>
						<td id="unitDeductedPrice_${list_index}">
						</td>
						<td id="payAmount_${list_index}">
						</td>
						
			
						<td>
						  <select  id="preRefundReason_${list_index}" onchange="javascript:getRefundReasonFun(${list_index})" name="orderRetChgItemsList[${list_index}].reason">
						    <#list preRefundReasonList as preRefundReason>
								<option value="${preRefundReason.reasonNo}" <#if preRefundReason.reasonNo == list.reason> selected </#if>>
									${preRefundReason.reasonName}
								</option>
						   </#list>
					       </select>					      
						</td>
						<!--td>
						  <select  id="refundReason_${list_index}" name="orderRetChgItemsList[${list_index}].reason">
						   <#list reasonMap.get(list.reason) as detailReason>
						     <option value="${detailReason.reasonNo}" <#if detailReason.reasonNo == list.reason> selected </#if>>
								${detailReason.reasonName}
							</option>
							 </#list>  
					       </select>
						</td-->
						<#if orderCategory != 'rej'>
						<td id="deleteRow_${list_index}">
							<input type="button" class="formButton" onclick="javascript:deleteItemRow4ModifyPage(this,${list_index},${list.id})" value="删除" hidefocus="true" id="deleteButton_${list_index}"/>
						</td>
						</#if>
					</tr>
					</#if>
				</#list>
				<!-- end 修改退货、拒收 -->
				</#if>
				<!-- 修改页 end -->
			 </#if>
			</table>
			</div>
			 
			
			<!-- 客户信息 -->
			<#include "/WEB-INF/template/admin/order/sale_after_order/new_detail_order_customerInfo_page.ftl">
		   
			
			
			<!-- 退款信息 -->
			<#if orderCategory == 'chg'>
				<!-- 换货不需退款 -->
			<#elseif (orderCategory == 'rej' && order.statusPay =='0420') || (isNeed == 1) || (order.ifNeedRefund == 1)>
				<!-- 在线支付已支付拒收需退款 -->
  				<#include "/WEB-INF/template/admin/order/sale_after_order/new_detail_order_pay_page.ftl">
  			<#else>
  				<!-- 退货需退款 -->
		    	<#include "/WEB-INF/template/admin/order/sale_after_order/new_detail_order_pay_page.ftl"> 
			</#if>
						
			<div id="colorSizeDisplay" style="border:1px solid red;padding-right: 35px;background-color: wheat;position: absolute;z-index: 100;top: 30px;left: 200px;padding-top: 20px;padding-left: 20px;display:none;">
			&nbsp;<label style="position: absolute;top: 5px;right: 8px;cursor: pointer;" onclick="colorSizeClose()">X</label>
			</div>
	  </div>
	</div>
