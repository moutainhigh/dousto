	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>编辑子订单</h1>
		</div>
		
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<div class="blank"></div>
			

     
           <div>商品明细</div>
			<table class="inputTable tabContent">
				<tr class="title">
					<th>订单明细号</th>
					<th>明细来源</th>
					<th>销售号</th>
					<th>单位</th>
					<th>单位价格</th>
					<th>单位折扣</th>
					<th>总价</th>
					<th>商品类型</th>
					<th>出库成本价</th>
					<th>仓库号</th>
					<th>仓储号</th>
					<th>账单类型</th>
					<th>留言</th>
				</tr>
				<#if orderSub.orderItems?exists>
				<#list orderSub.orderItems as list>
					<tr>
						<td>
							${list.orderItemNo}
						</td>
						<td>
							${list.itemSource}
						</td>
						<td>
							${list.saleNum}
						</td>
						<td>
							${list.saleUnit}
						</td>
						<td>
							${list.unitPrice}
						</td>
						<td>
							${list.unitDiscount}
						</td>
						<td>
							${list.payAmount}
						</td>
						<td>
							${list.productCategory}
						</td>
						<td>
							${list.INVENTORY_PRICE}
						</td>
						<td>
							${list.stockNo}
						</td>
						<td>
							${list.warehouseNo}
						</td>
						<td>
							${list.billType}
						</td>
						<td>
							${list.remark}
						</td>
					</tr>
				</#list>
				</#if>
			</table>
	</div>
