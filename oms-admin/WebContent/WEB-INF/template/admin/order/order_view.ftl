<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/order.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/global.css" rel="stylesheet" type="text/css" />
<script src="${base}/template/admin/js/order_view_manifest.js" charset="utf-8"></script>
<script>
	var base = '${base}';
	/*PRD-39获取前台商店路径*/
	var shopUrl='${systemConfig.shopUrl}';
	var imgUrl='${systemConfig.resourceWebRootPath}';
	var imgBase='${imgBase}';
$().ready( function() {
	updateTotal();
	refreshCart(${orderId});
})


function updateTotal(){

	$.getJSON('${base}/order/order_subtotal_calculate.action', {
    	orderId: ${orderId}
    }, function(data){
    	var htmlStr = ""
    	htmlStr = htmlStr + "商品数量总计：";
    	htmlStr = htmlStr + "<span class=\"tbTotal_1\">"+data.totalProductQuantity+" 件</span>";
    	htmlStr = htmlStr + "商品总金额：￥";
    	htmlStr = htmlStr + "<span class=\"tbTotal_1\">"+data.totalProduct+" 元</span>";
    	if(data.totalAdjust>0){
    		htmlStr = htmlStr + "<span class=\"tbTotal_right\">直减金额：-￥"+data.totalAdjust+" 元</span>";
    	}
    	if(data.couponAdjust>0){
    		htmlStr = htmlStr + "<span class=\"tbTotal_right\">优惠券抵扣：-￥"+data.couponAdjust+" 元</span>";
    	}
    	if(data.shippingFee>0){
    		htmlStr = htmlStr + "<span class=\"tbTotal_right\">运费：+￥"+data.shippingFee+" 元</span>";
    	}
    	if(data.shippingAdjust>0){
    		htmlStr = htmlStr + "<span class=\"tbTotal_right\">运费优惠：-￥"+data.shippingAdjust+" 元</span>";
    	}
    	if(data.paymentTypeAdjust>0){
    		htmlStr = htmlStr + "<span class=\"tbTotal_right\">支付优惠：-￥"+data.paymentTypeAdjust+" 元</span>";
    	}
    	if(data.usedDeposit>0){
    		htmlStr = htmlStr + "<span class=\"tbTotal_right\">预存款抵扣：-￥"+data.usedDeposit+" 元</span>";
    	}
    	htmlStr = htmlStr + "<span class=\"tbTotal_right\">您需要为订单支付：￥<strong class=\"red14\">"+data.subTotal2+"</strong> 元</span>";
    	$("#tdSubtoal").html(htmlStr);
    });
}
	
function mergeShippingView(){
	$("<iframe id='mergeFrame' src='${base}/order/merge_shipping!list.action?mergeNo=${mergeNo!}' />").dialog(
	{   autoOpen: true,
	        width: 600,
	        height: 500,
	        modal: true,
	        resizable: true,
	        autoResize: true,
			close: function(event, ui) { $(this).dialog('destroy') },
			open: function (event,ui) {$(this).css('width',"100%").css('padding',"0")},
	  title: "合并发货列表" 
	});
}
</script>
</head>
<body>
<span class="blank16"></span>
<!-- middle -->
<div class="content"> 
    <div><h1><strong class="blue14">订单号：${order.orderNo}</strong>
	<span style="float:right;margin-right:780px;">
	<input type="hidden" class="formButton" onclick="javascript:docAuditView();return false;" value="状态跟踪" hidefocus="true" />
	<input type="hidden" class="formButton" onclick="javascript:orderMemoView();return false;" value="查看备注" hidefocus="true" />
	</span>
	</h1>
	</div>
	
<span class="blank16"></span>
<#assign orderflow="order_flow1">
<#if order.status =='submitted'>
	<#assign orderflow="order_flow1">
<#elseif order.status =='paid'  || order.status =='wms_receivedfailed' || order.status =='canceling'>
	<#assign orderflow="order_flow2">
<#elseif order.status =='wms_received' || order.status =='wms_packaged' || order.status =='canceledfail'  >
	<#assign orderflow="order_flow3">
<#elseif order.status =='shipped' || order.status =='outOfStock' >
	<#assign orderflow="order_flow4">
<#elseif order.status =='canceled' || order.status =='refund' ||order.status =='closed' || order.status =='completed'|| order.status =='invalid'>
	<#assign orderflow="order_flow5">
</#if>
    <div class="${orderflow}"></div><!--shopbagbread-->
	
<span class="blank16"></span>
	<div class="content_order">
		<div class="ordertitle"><strong>收货人信息</strong></div>
		<div class="order_content">${shippingAddress.consignee}，${shippingAddress.displayAddress}<#if !shippingAddress.zipcode?? && shippingAddress.zipcode!=null > ，${shippingAddress.zipcode}</#if><#if shippingAddress.phone?? && shippingAddress.phone!=null >，${shippingAddress.phone}</#if><#if shippingAddress.mobile?? && shippingAddress.mobile!=null>，${shippingAddress.mobile}</#if></div>
		
		<div class="ordertitle"><strong>配送方式</strong></div>
		<div class="order_content">
		${shipMode.name}<br />
			<#--
			<#if (order.status) == 'shipped'  || (order.status) == 'received' ||(order.status) ==  'completed'>
			快递公司：<a target="_blank" href="${(order.carrier.trackUrl)!}">${(order.carrier.name)!}</a><br />
			单号：${(order.deliveryNo)!}
			</#if>
			-->
		</div>
		
		<div class="ordertitle"><strong>配送时间范围</strong></div>
		<div class="order_content">
		<#if order.receiveDate=='anyday'>
			工作日、双休日与假日均可送货
		<#elseif order.receiveDate=='holiday'>
			只有双休日、假日送货（工作日不用送货）
		<#elseif order.receiveDate=='workingday'>
			只有工作日送货（双休日、假日不用送货）
		</#if>
		</div>
		
		<div class="ordertitle"><strong>支付方式</strong></div>
		<div class="order_content">
			<#if order.paymentMethodId=='9'>
				网上支付-银联
			<#elseif order.paymentMethodId=='2'>
				网上支付-支付宝
			<#elseif order.paymentMethodId=='3'>
				招商银行
			<#elseif order.paymentMethodId=='4'>
				工商银行
			<#elseif order.paymentMethodId=='5'>
				农业银行
			<#elseif order.paymentMethodId=='6'>
				中国银行
		    <#elseif order.paymentMethodId=='7'>
				建设银行
			<#elseif order.paymentMethodId=='8'>
				交通银行
			</#if>
			<#if coupon!=null>
			<p>使用优惠卡卡号：${coupon.couponNo!}，金额：￥${coupon.parValue!}</p>
			</#if>
		</div>
		<div class="ordertitle"><strong>附加说明</strong></div>
		<div class="order_content">${order.memo!}</div>
		<div class="ordertitle"><strong>开发票</strong></div>
		<div class="order_content">发票抬头：${order.billingTitle!}
			<p>发票内容：<#if order.billingContent=='mingxi'>明细</#if><#if order.billingContent=='bangongyongpin'>办公用品</#if><#if order.billingContent=='lipin'>礼品</#if><#if order.billingContent=='huliyongpin'>护理用品</#if><#if order.billingContent=='riyongpin'>日用品</#if><#if order.billingContent=='xidiyongpin'>洗涤用品</#if><#if order.billingContent=='shipin'>食品</#if></p>
		</div>
		<#if order.outOfStockMemo??>
		<div class="ordertitle"><strong>缺货原因</strong></div>
		<div class="order_content">
			${(order.outOfStockMemo)!}
		</div>
		</#if>
		<#if order.failReason??>
		<div class="ordertitle"><strong>作废原因</strong></div>
		<div class="order_content">
			${(order.failReason)!}
		</div>
		</#if>
	</div><!--content_order-->
	
	<span class="blank16"></span>
	<div class="shopingbag">
		<h2>产品清单</h2>

		<table id="mainTable" width="920px" cellpadding="20" cellspacing="2" align="center">
			<tr class="tb_title">
				<th class="cel02">商品名称</th>
				<th class="cel03">SKU编号</th>
				<th class="cel03">颜色</th>
				<th class="cel03">尺码</th>
				<th class="cel03">单价</th>
				<th class="cel03">数量</th>
				<th class="cel03">优惠</th>
				<th class="cel03">小计</th>
			</tr>
		</table>	
		<table width="920px" cellpadding="20" cellspacing="2" align="center">
			<tr class="blank8"></tr>
			<tr>
				<td id="tdSubtoal" class="tbTotal" colspan="9">
				     
				</td>
			</tr>
		</table>
	
	</div>
	<HR align=center width="100%" color=#EEEEEE noShade SIZE=1>
		<div align="center" class="buttonArea">
					<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		</div>
</div>
<#assign docType = 'order' />
<#assign docId = order.id />
</body>
</html>
