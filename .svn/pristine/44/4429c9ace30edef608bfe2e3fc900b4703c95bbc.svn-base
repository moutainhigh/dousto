<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign sjg=JspTaglibs["/WEB-INF/tlds/struts-jquery-grid-tags.tld"]>
<#assign sjr=JspTaglibs["/WEB-INF/tlds/struts-jquery-richtext-tags.tld"]>
<#assign baseWeb="/sc-webui">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看发货单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>

<script type="text/javascript" >
	$().ready(function(){
	    // 内容窗口
	    $("body").prepend('<div id="contentWindow" class="contentWindow"><div class="windowTop"><div class="windowTitle"></div><a class="messageClose windowClose" href="#" hidefocus="true"></a></div><div class="windowMiddle"><div class="windowContent"></div></div><div class="windowBottom"></div></div>');
	    
	    // 消息提示窗口
	    $("body").prepend('<div id="messageWindow" class="messageWindow"><div class="windowTop"><div class="windowTitle">提示信息&nbsp;</div><a class="messageClose windowClose" href="#" hidefocus="true"></a></div><div class="windowMiddle"><div class="messageContent"><span class="icon">&nbsp;</span><span class="messageText"></span></div><input type="button" class="formButton messageButton windowClose" value="确  定" hidefocus="true"/></div><div class="windowBottom"></div></div>');
	    
	    // 滑动提示框
	    $("body").prepend('<div id="tipWindow" class="tipWindow"><span class="icon">&nbsp;</span><span class="messageText"></span></div>');
	    
	});
</script>

</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>查看发货单</h1>
		</div>
		<table class="inputTable">
			<tr>
				<th>
					发货单号:
				</th>
				<td>
					${shipping.shippingNo}
				</td>
				<th>
					状态:
				</th>
				<td>
					${(shipping.shippingStatus.display())!}
				</td>
			</tr>
			<tr>
				<th>
					订单总额:
				</th>
				<td>
					￥${(shipping.totalAmount)!}
				</td>
				<th>
					已付金额:
				</th>
				<td>
					￥${shipping.totalPaid}
				</td>
				<th>
					应付金额:
				</th>
				<td>
					￥${shipping.totalAmount.subtract(shipping.totalPaid)}
				</td>
			</tr>
			<tr>
				<th>
					付款方式:
				</th>
				<td>
					${(shipping.paymentMode.name)!}
				</td>
				<th>
					会员:
				</th>
				<td>
					${(shipping.member.username)!}
				</td>
			</tr>
			<tr>
				<th>
					配送方式:
				</th>
				<td>
					${(shipping.shipMode.deliveryMethod.display())!}
				</td>
				<th>
					物流公司:
				</th>
				<td>
					${(shipping.carrier.name)!}
				</td>
				<th>
					快递单号:
				</th>
				<td>
					${(shipping.deliveryNo)!}
				</td>
			</tr>
			<tr>
				<th>
					送货地址:
				</th>
				<td>
					${(shipping.shipAddress.address)!}
				</td>
				<th>
					邮编:
				</th>
				<td>
					${(shipping.shipAddress.zipcode)!}
				</td>
			</tr>
			<tr>
				<th>
					收货人:
				</th>
				<td>
					${(shipping.shipAddress.consignee)!}
				</td>			
				<th>
					固定电话:
				</th>
				<td>
					${(shipping.shipAddress.phone)!}
				</td>
				<th>
					移动电话:
				</th>
				<td>
					${(shipping.shipAddress.mobile)!}
				</td>
			</tr>
			<tr>
				<th>
					是否开发票:
				</th>
				<td>
					<#if shipping.isBilling!=1>否<#else>是</#if>
				</td>
				<#if shipping.isBilling==1>
				<th>
					发票抬头:
				</th>
				<td>
					${(shipping.billingTitle)!}
				</td>
				<th>
					发票内容:
				</th>
				<td>
					${(shipping.billingContent)!}
				</td>
				</#if>
			</tr>
			<tr>
				<th>
					提交时间:
				</th>
				<td>
					${(shipping.shippingDate?string("yyyy-MM-dd HH:mm:ss"))!}
				</td>
				<#if (shipping.shippingStatus==RECEIVED)||(shipping.shippingStatus==SHIPPED)>
				<th>
					发货时间:
				</th>
				<td>
					${(shipping.shippedDate?string("yyyy-MM-dd HH:mm:ss"))!}
				</td>
				<#if (shipping.shippingStatus==RECEIVED)>
				<th>
					收货时间:
				</th>
				<td>
					${(shipping.receivedDate?string("yyyy-MM-dd HH:mm:ss"))!}
				</td>
				</#if>
				</#if>
			</tr>	
			<tr>
				<#if (shipping.shippingStatus==INVALID)>
				<th>
					失败原因:
				</th>
				<td>
					${(shipping.invalidReason)!}
				</td>
				</#if>
			</tr>						
			<tr>
				<td colspan="4">
					&nbsp;
				</td>
			</tr>
		</table>
		<table class="inputTable">
			<tr class="title">
				<th>订单号</th>
				<th>订单行项目类型</th>
				<th>SKU码</th>
				<th>商品名称</th>
				<th>颜色</th>
				<th>尺码</th>
				<th>数量</th>
				<th>销售价</th>
				<th>成交价</th>
				<th>小计</th>
			</tr>
			<#list shipping.shippingItems as list>
				<tr>
					<td>
						${(list.orderSn)!}
					</td>
					<td>
						${(list.orderItemType.display())!}
					</td>
					<td>
						${(list.skuId)!}
					</td>
					<td>
						${(list.productName)!}
					</td>
					<td>
						${(list.sku.color)!}
					</td>
					<td>
						${(list.sku.size)!}
					</td>
					<td>
						${(list.quantity)!}
					</td>

					<td>
						${(list.price)!}
					</td>
					<td>
						${(list.finalPrice)!}
					</td>
					<td>
						${(list.subtotal)!}
					</td>
					
					
				</tr>
			</#list>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		</div>
	</div>
</body>
</html>