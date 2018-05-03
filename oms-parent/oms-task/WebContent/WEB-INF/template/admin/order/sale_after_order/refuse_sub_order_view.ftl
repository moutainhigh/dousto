	<div class="body">
		 <div id="tabs">
			<ul>
				<li><a href="#orderItem">内容</a></li>
				<li><a href="#customerInfo">客户信息</a></li>
				<#if order.statusPay =='0420' || (isNeed == 1)>
				<li><a href="#retInfo">退款信息</a></li>
				</#if>
			</ul>
			
		   <div id="orderItem" style="display:block">
			<table class="inputTable tabContent">
				<tr class="title">
					<th>订单明细号</th>
					<th>SKU</th>
					<th>数量</th>
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
				</tr>
				<#if orderSub.orderRetChgItems?exists>
				<#list orderSub.orderRetChgItems as list>
					<tr name="orderitemlist">
						<td>
							${list.orderItemNo}
							<input type="hidden" name="orderRetChgItemsList[${list_index}].orderItemNo" value="${list.orderItemNo}" />
							<input type="hidden" name="orderRetChgItemsList[${list_index}].id" value="${list.id}" />
						</td>
						<td>
							${list.skuNo!}
							<input type="hidden" name="orderItemsList[${list_index}].skuNo"  id="skuNo_${list_index}"  value="${list.skuNo!}" />
						</td>
						<td>
						    ${list.saleNum?default(0)}
							<input type="hidden"  id="saleNum_${list_index}"  value="${list.saleNum?default(0)}" />
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
							${weightUnit?default(0)}
						</td>
						<td>
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
					<#if orderSub.addressCode == '1602'>
						中国
					<#else>
						${orderSub.combinedAddress}
					</#if>
				</td>	
					
				<th>
					具体地址:
				</th>
				<td>
					${orderSub.addressDetail}
				</td>				
			 </tr>
				
			 <tr>	
				<th>
					邮政编码:
				</th>
				<td>
					${orderSub.postCode}
				</td>	
				
				<th>
					客户姓名:
				</th>
				<td>
					${orderSub.userName}
				</td>
			 </tr>
			 
			  <tr>	
				<th>
					电话:
				</th>
				<td>
					${orderSub.phoneNum}
				</td>
				<th>
					手机:
				</th>
				<td>
					${orderSub.mobPhoneNum}
				</td>
			 </tr>
			</table>
			</div>
			
			<#if order.statusPay =='0420' || (isNeed == 1)>
				<!-- 在线支付已支付拒收需退款 -->
  				<#include "/WEB-INF/template/admin/order/sale_after_order/detail_order_pay_page.ftl">
  			</#if> 
	  </div>
	</div>
