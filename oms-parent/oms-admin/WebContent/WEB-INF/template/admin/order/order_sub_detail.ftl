	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>商品明细列表</h1>
		</div>
			<!--
				<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			-->
			<div class="blank"></div>
			

     
           <!--<div>商品明细</div>-->
			<table class="inputTable tabContent">
				<tr class="title">
					<th style="width:110px;">SKU</th>
					<th style="width:110px;">商品编码</th>
					<th style="width:300px;">商品名称</th>
					<th style="width:120px;">品类</th>
					<th style="width:130px;">品牌</th>
					<th style="width:60px;">单位</th>
					<th style="width:60px;">数量</th>
					<th style="width:200px;">价格</th>
					<th style="width:80px;">赠送积分</th>
					<th style="width:80px;">是否赠品</th>
				</tr>
				<!-- 虚拟商品 begin -->
				<#if order.orderType == 'VIRTUAL'>
					<#if order.orderItemVirtuals?exists>
				<#list order.orderItemVirtuals as item>
					<tr>
						<td>
							${(item.skuNo)!}
						</td>
						<td>
						    ${(item.productCode)!}	
						</td>
						<td>
						<a href="${(configMap['btcUrl'])!}?skuId=${(item.skuId)!}" target="_blank"><font color="red">	${(item.productName)!} </font></a>
						<font color="gray">
						<#list item.productPropertys as productProperty>
							<br/>
							${productProperty.propertyName}：${productProperty.propertyValue}
						</#list>
						</font>
						<#if item.promotionType == '2'>
						<br/><font color="blue">(活动商品)</font>
						<#elseif item.promotionType == '3'>
						<br/><font color="blue">(积分商品)</font>
						</#if>
						</td>
						<td>
							${(item.productCategoryName)!}
						</td>
						<td>
							${(item.brandName)!}
						</td>
						<td>
							${(item.saleUnit)!}
						</td>
						<td>
							${(item.saleNum)!}
						</td>
						<td>
							<table>
								<tr>
									<td>名称</td>
									<td>单价</td>
									<td>总价</td>
								</tr>
								<tr>
									<td>您的价格</td>
									<td><font color="red">${((item.unitPrice)!0)?string.currency}</font></td>
									<td><font color="red">${(((item.unitPrice)!0)*((item.saleNum)!0))?string.currency}</font></td>
								</tr>
								<tr>
									<td>优惠金额</td>
									<#assign saleTotalMoney=((item.unitPrice)!0)*((item.saleNum)!0)>
									<#assign payAmount=(item.saleAmount)!0>
									<#assign saleNum=(item.saleNum)!1>
									<#if (item.saleNum == 0)>
										<#assign saleNum=1>
									</#if>
									<!--<td><font color="red">${((item.unitDiscount)!0)?string.currency}</font></td>-->
									<!--<td><font color="red">${((item.itemDiscount)!0)?string.currency}</font></td>-->
									<td><font color="red">${((saleTotalMoney-payAmount)/saleNum)?string.currency}</font></td>
									<td><font color="red">${((saleTotalMoney-payAmount)!0)?string.currency}</font></td>
								</tr>
								<tr>
									<td>实付金额</td>
									<#assign payAmount=(item.saleAmount)!0>
									<#assign saleNum=(item.saleNum)!1>
									<#if (item.saleNum == 0)>
										<#assign saleNum=1>
									</#if>
									<!--<td><font color="red">${((item.unitDeductedPrice)!0)?string.currency}</font></td>-->
									<td><font color="red">${((payAmount/saleNum)!0)?string.currency}</font></td>
									<td><font color="red">${((item.saleAmount)!0)?string.currency}</font></td>
								</tr>
								<tr>
									<td>券使用金额</td>
									<#assign couponAmount=(item.couponAmount)!0>
									<#assign saleNum=(item.saleNum)!1>
									<#if (item.saleNum == 0)>
										<#assign saleNum=1>
									</#if>
									<td><font color="red">${(couponAmount/saleNum)?string.currency}</font></td>
									<td><font color="red">${((item.couponAmount)!0)?string.currency}</font></td>
									<!--<td><font color="red">${((item.couponTotalMoney)!0)?string.currency}</font></td>-->
								</tr>
							</table>
						</td>
						<td>
							${(item.productPoint)!}
						</td>
						<td>
							 <#if item.hasGift == '0'>否
						 
							 <#elseif item.hasGift == '1'>是
							 
							 <#else>否
							 
							 </#if>
						</td>
					</tr>
				</#list>
				</#if>
				<!-- 虚拟商品 end -->
				<#else>
				<!-- 非虚拟商品 begin -->
				<#if orderSub.orderItems?exists>
				<#list orderSub.orderItems as item>
					<tr>
						<td>
							${(item.skuNo)!}
						</td>
						<td>
						    ${(item.commodityCode)!}	
						</td>
						<td>
						<a href="${(configMap['btcUrl'])!}?skuId=${(item.skuId)!}" target="_blank"><font color="red">	${(item.commodityName)!} </font></a>
						<font color="gray">
						<#list item.productPropertys as productProperty>
							<br/>
							${productProperty.propertyName}：${productProperty.propertyValue}
						</#list>
						</font>
						<#if item.promotionType == '2'>
						<br/><font color="blue">(活动商品)</font>
						<#elseif item.promotionType == '3'>
						<br/><font color="blue">(积分商品)</font>
						</#if>
						</td>
						<td>
							${(item.productCategoryName)!}
						</td>
						<td>
							${(item.brandName)!}
						</td>
						<td>
							${(item.saleUnit)!}
						</td>
						<td>
							${(item.saleNum)!}
						</td>
						<td>
							<table>
								<tr>
									<td>名称</td>
									<td>单价</td>
									<td>总价</td>
								</tr>
								<tr>
									<td>您的价格</td>
									<td><font color="red">${((item.unitPrice)!0)?string.currency}</font></td>
									<td><font color="red">${((item.saleTotalMoney)!0)?string.currency}</font></td>
								</tr>
								<tr>
									<td>优惠金额</td>
									<#assign saleTotalMoney=(item.saleTotalMoney)!0>
									<#assign payAmount=(item.payAmount)!0>
									<#assign saleNum=(item.saleNum)!1>
									<#if (item.saleNum == 0)>
										<#assign saleNum=1>
									</#if>
									<!--<td><font color="red">${((item.unitDiscount)!0)?string.currency}</font></td>-->
									<!--<td><font color="red">${((item.itemDiscount)!0)?string.currency}</font></td>-->
									<td><font color="red">${((saleTotalMoney-payAmount)/saleNum)?string.currency}</font></td>
									<td><font color="red">${((saleTotalMoney-payAmount)!0)?string.currency}</font></td>
								</tr>
								<tr>
									<td>实付金额</td>
									<#assign payAmount=(item.payAmount)!0>
									<#assign saleNum=(item.saleNum)!1>
									<#if (item.saleNum == 0)>
										<#assign saleNum=1>
									</#if>
									<!--<td><font color="red">${((item.unitDeductedPrice)!0)?string.currency}</font></td>-->
									<td><font color="red">${((payAmount/saleNum)!0)?string.currency}</font></td>
									<td><font color="red">${((item.payAmount)!0)?string.currency}</font></td>
								</tr>
								<tr>
									<td>券使用金额</td>
									<#assign couponAmount=(item.couponAmount)!0>
									<#assign saleNum=(item.saleNum)!1>
									<#if (item.saleNum == 0)>
										<#assign saleNum=1>
									</#if>
									<td><font color="red">${(couponAmount/saleNum)?string.currency}</font></td>
									<td><font color="red">${((item.couponAmount)!0)?string.currency}</font></td>
									<!--<td><font color="red">${((item.couponTotalMoney)!0)?string.currency}</font></td>-->
								</tr>
							</table>
						</td>
						<td>
							${(item.productPoint)!}
						</td>
						<td>
							 <#if item.orderItemClass == 'GIFT'>是 
						 
							 <#-- elseif item.hasGift == '1' -->
							 
							 <#else>否 
							 
							 </#if>
						</td>
					</tr>
				</#list>
				</#if>
				<!-- 非虚拟商品 end -->
			</#if>	
			</table>
	</div>
