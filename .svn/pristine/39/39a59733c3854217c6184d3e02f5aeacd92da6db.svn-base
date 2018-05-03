

var invalidgiftItemResult;
var cookieMemberId="";

function refreshCart(orderId){
	$.getJSON(base+'/order/product_manifest!view.action?orderId='+orderId, function(data) {
		if	(data.cartItemResult.cart!=null){
			orderId = data.cartItemResult.cart.order.id;
			buildCartList(data.cartItemResult.cart.cartItem);
			//buildGiftList(data.giftItemResult.giftItems);
			//bulidFooter(data.totalQuantity,data.totalPrice);
		  
		}else{
			alert(data.cartItemResult.warnings[0].message);
		}  
	});
	$("#mainTable").html('<tr class="tb_title"><th class="cel01">商品名称</th><th class="cel03">SKU编号</th>'+
						'<th class="cel03">颜色</th><th class="cel03">尺码</th>'+
						'<th class="cel03">单价</th><th class="cel03">数量</th>'+
						'<th class="cel03">优惠</th><th class="cel03">小计</th>');
	
}

function buildCartList(cartItems){
	for (i in cartItems){
		if (cartItems[i].orderitem.type=='General')
			buildGeneralRow(cartItems[i]);
		if (cartItems[i].orderitem.type == 'Gift') {
			if (cartItems[i].orderitem.sourceId == null) 
				buildOrderGiftRow(cartItems[i]);
			else 
				buildGiftRow(cartItems[i]);
		}				
		if (cartItems[i].orderitem.type=='Bundle')
			buildBundleRow(cartItems[i]);	
		if (cartItems[i].orderitem.type=='Component')
			buildComponentRow(cartItems[i]);	
	}
}
/*PRD-39 添加页面链接*/
function buildGeneralRow(cartItem){
	var prompotionType = "";
	if(cartItem.prompotionType =='ProductRush')	prompotionType="抢";
	if(cartItem.prompotionType =='ProductDisc')	prompotionType="减";	
	$("#mainTable").append(
	'<tr class="cell01" id="'+cartItem.orderitem.id+'">'+
		'<td class="tb_productname">'+
		'<span class="tb_productpic"><a target="_blank" href="'+shopUrl+'/catalog/product_detail.html?pid='+cartItem.orderitem.productId+'"><img src="'+imgUrl+imgBase+cartItem.imagpath+'"  alt="'+cartItem.productname+'"/></a></span>'+	
		'<span class="tb_producttitle">'+cartItem.productname+'</span></td>'+
		'<td class="cel03" align="center">'+cartItem.skuCode+'</td>'+
		'<td class="cel03" align="center">'+cartItem.color+'</td>'+
		'<td class="cel04" align="center">'+cartItem.size+'</td>'+
		'<td class="cel03" align="center">￥'+cartItem.orderitem.price+'</td>'+
		'<td class="cel03" align="center">'+
			cartItem.orderitem.quantity+
		'</td>'+
		'<td class="cel03" align="center"><strong class="red12">'+prompotionType+'</strong>￥'+cartItem.orderitem.amountAdjust+'</td>'+
		'<td class="cel03" align="center">￥'+cartItem.orderitem.amountProduct+'</td>'+
	'</tr>'
	);
}

function buildGiftRow(cartItem){
	$("#mainTable").append(
   '<tr class="cell03" id="'+cartItem.orderitem.id+'">'+
       '<td colspan="9">'+
	       '<ul>'+
			   '<li>'+
			       '<table class="tab_fdlist" width="960px" cellspacing="2" cellpadding="20" align="center">'+
				       '<tr class="cell03">'+
					        '<td class="tb_productname1">'+
					        '<span class="tb_productpic"><img src="'+imgBase+cartItem.imagpath+'"  alt="'+cartItem.productname+'"/></span>'+
                            '<span class="tb_producttitle1"><strong class="blue12">赠品:</strong>'+cartItem.productname+'</span></td>'+
                            '<td class="cel03">'+cartItem.skuCode+'</td>'+
                            '<td class="cel03">'+cartItem.color+'</td>'+
							'<td class="cel03">'+cartItem.size+'</td>'+
							'<td class="cel03">-</td>'+
							'<td class="cel03">'+cartItem.orderitem.quantity+'</td>'+
							'<td class="cel03">-</td>'+
							'<td class="cel03">-</td>'+
					   '</tr>'+   
				   '</table>'+
			   '</li>'+
		   '</ul>'+
	   '</td>'+
   '</tr>'
	);
}
/*PRD-39 添加页面链接*/
function buildBundleRow(cartItem){
	var prompotionType = "";
	if(cartItem.prompotionType =='ProductRush')	prompotionType="抢";
	if(cartItem.prompotionType =='ProductDisc')	prompotionType="减";
	$("#mainTable").append(
	'<tr class="cell01" id="'+cartItem.orderitem.id+'">'+
		'<td class="tb_productname">'+
		'<span class="tb_productpic"><a target="_blank" href="'+shopUrl+'/catalog/product_detail.html?pid='+cartItem.orderitem.productId+'"><img src="'+imgBase+cartItem.imagpath+'"  alt="'+cartItem.productname+'"/></a></span>'+
		'<span class="tb_producttitle">'+cartItem.productname+'</span></td>'+
		'<td class="cel03" align="center">-</td>'+
		'<td class="cel03" align="center">-</td>'+
		'<td class="cel04" align="center">-</td>'+
		'<td class="cel03" align="center">￥'+cartItem.orderitem.price+'</td>'+
		'<td class="cel03" align="center">'+
			cartItem.orderitem.quantity+
		'</td>'+
		'<td class="cel03" align="center"><strong class="red12">'+prompotionType+'</strong>￥'+cartItem.orderitem.amountAdjust+'</td>'+
		'<td class="cel03" align="center">￥'+cartItem.orderitem.amountProduct+'</td>'+
	'</tr>'
	);
}
/*PRD-39 添加页面链接*/
function buildComponentRow(cartItem){
	$("#mainTable").append(
   '<tr id="'+cartItem.orderitem.id+'">'+
       '<td colspan="9">'+
	       '<ul>'+
			   '<li>'+
			       '<table class="tab_fdlist" width="960px" cellspacing="2" cellpadding="20" align="center">'+
				       '<tr>'+
					        '<td class="tb_productname1">'+
					        '<span class="tb_productpic"><a target="_blank" href="'+shopUrl+'/catalog/product_detail.html?pid='+cartItem.orderitem.productId+'"><img src="'+imgBase+cartItem.imagpath+'"  alt="'+cartItem.productname+'"/></a></span>'+
                            '<span class="tb_producttitle1">'+cartItem.productname+'</span></td>'+
                            '<td class="cel03">'+cartItem.skuCode+'</td>'+
                            '<td class="cel03">'+cartItem.color+'</td>'+
							'<td class="cel03">'+cartItem.size+'</td>'+
							'<td class="cel03">-</td>'+
							'<td class="cel03">'+cartItem.orderitem.quantity+'</td>'+
							'<td class="cel03">-</td>'+
							'<td class="cel03">-</td>'+
					   '</tr>'+   
				   '</table>'+
			   '</li>'+
		   '</ul>'+
	   '</td>'+
   '</tr>'
	);
}

function buildOrderGiftRow(cartItem){
	$("#mainTable").append(
		'<tr class="cell03" id="'+cartItem.orderitem.id+'">'+
			'<td class="tb_productname">'+
			'<span class="tb_productpic"><a target="_blank" href="'+base+'/catalog/product_detail.html?pid='+cartItem.orderitem.productId+'"><img src="'+imgBase+cartItem.imagpath+'"  alt="领带"/></a></span>'+
			'<span class="tb_producttitle1"><strong class="blue12">订单赠品</strong><a target="_blank" href="'+base+'/catalog/product_detail.html?pid='+cartItem.orderitem.productId+'">'+cartItem.productname+'</a></span>'+
			'</td>'+
			'<td class="cel03">'+cartItem.skuCode+'</td>'+
			'<td class="cel03">'+cartItem.color+'</td>'+
			'<td class="cel03">'+cartItem.size+'</td>'+
			'<td class="cel03">-</td>'+
			'<td class="cel03">'+cartItem.orderitem.quantity+'</td>'+
			'<td class="cel03">-</td>'+
			'<td class="cel03">-</td>'+
		'</tr>'
	);
}

function bulidFooter(totalQuantity,totalPrice,orderId){
	alert("kdjfkdjf");
	$("#mainTable").append(
	'<tr class="blank8"></tr>'+
	'<tr>'+
	'<td class="tbTotal" colspan="9">'+
	'商品数量总计：<span class="tbTotal_1">'+totalQuantity+'件</span>'+
	'总金额：<span class="tbTotal_1">￥'+totalPrice+'</span>'+
	'</td>'+
	'</tr>'
	);
}

