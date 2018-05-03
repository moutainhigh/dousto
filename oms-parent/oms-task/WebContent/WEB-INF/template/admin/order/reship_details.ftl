<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><#if mode == "new">新建<#elseif mode == "view">查看</#if>退货单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/scripts/reship/reship.js"></script>
<#if mode == "new">
<script type="text/javascript">

$(document).ready(function() {
	$('#inputForm').validate();
	
	$('#refundMethod').change(updateOfflineInputStatus);
	
	$('#inputForm input[name$=".rmaQuantity"]').change(updateReshipOrderDetail);
	$('#inputForm input[name$=".returnPrice"]').change(updateReshipOrderDetail);
	
	$('#inputForm input[name$=".rmaQuantity"]').focus(function() { $(this).select(); });
	$('#inputForm input[name$=".returnPrice"]').focus(function() { $(this).select(); });
	
	updateOfflineInputStatus();
	updateReshipOrderDetail();
});

</script>
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if mode == "new">新建<#elseif mode == "view">查看</#if>退货单</h1>
		</div>
		<#if mode != "view">
		<form id="inputForm" class="validate" action="reship!submit.action" method="post">
			<input type="hidden" name="rmaorder.orderId" value="${order.id}"/></#if>
			<span id="totalPaidAmount" style="display: none;">${order.totalPaid!0}</span>
			<table class="inputTable">
				<#if mode == "view">
				<tr>
					<th><span>退货单号: </span></th>
					<td>${rmaorder.rmaorderSn}</td>
					<th><span>状态: </span></th>
					<td>${rmaorder.rmaorderStatus.display()}</td>
					<th><span>提交时间: </span></th>
					<td>${(rmaorder.submitDate)?string("yyyy-MM-dd HH:mm:ss")}</td>
					<td colspan="4" />
				</tr>
				</#if>
				<tr>
					<th><span>订单号: </span></th>
					<td>${order.orderNo}</td>
					
					<th><span>会员: </span></th>
					<td>${(order.member.realName)!order.member.accountName}</td>
					<th><span>退款时间: </span></th>
					<td>${(rmaorder.returnDate)?string("yyyy-MM-dd")}</td>
					<td colspan="4" />
				</tr>
				<tr>
					<th><span>订单金额: </span></th>
					<td>${((order.totalAmount!0)?string(orderCurrencyFormat))!}</td>
					<th><span>已付订单总金额: </span></th>
					<td><label id="totalPaid">${((order.totalPaid!0)?string(orderCurrencyFormat))!}</label></td>
					<th><span>退货总数量: </span></th>
					<td colspan="10"><label id="totalRmaQuantity"/>${(rmaorder.totalRmaQuantity)!}</td>
					
				</tr>
				<tr>
					<th><span>退款方式: </span></th>
					<td colspan="10">
						<#if mode == "new">
						<select id="refundMethod" name="rmaorder.refundMethod">
						<#list refundMethodArray as refundMethod>
							<option value="${refundMethod.name()}">${refundMethod.display()}</option>
						</#list>
						</select>
						<#elseif mode == "view">
						${rmaorder.refundMethod.display()}
						</#if>
					</td>
					<#if mode == "new">
					<th><span>退款银行: </span></th>
					<td colspan="2">
						<input id="bankName" type="text" name="rmaorder.bankName" class="formTextS {required: isOfflineRefund}"/>
						<label class="requireField">*</label>
					</td>
					<th><span>银行帐号: </span></th>
					<td colspan="2">
						<input id="bankAccount" type="text" name="rmaorder.bankAccount" class="formTextS {required: isOfflineRefund}"/>
						<label class="requireField">*</label>
					</td>
					
					<#elseif mode == "view">
					</#if>
				</tr>
				<#if mode == "new">
				<th><span>收款人: </span></th>
					<td>
						<input id="payee" type="text" name="rmaorder.payee" class="formTextES {required: isOfflineRefund}"/>
						<label class="requireField">*</label>
					</td>
				<#elseif mode == "view">
				</#if>
				<tr>
				</tr>
				<tr>					
					<th><span>退货配送公司: </span></th>
					<td>
					<#if mode == "view">
					<#list carrierList as carrier>
						<#if (rmaorder.carrierCode==carrier.code) !>
						${carrier.name}
						</#if>
						</#list>
					<#else>
					
					<select id="carrier" name="rmaorder.carrierCode" >
					   <option value="">请选择配送公司..</option>
						<#list carrierList as carrier>
						<option value="${carrier.code}" <#if (rmaorder.carrierCode==carrier.code) !>selected</#if> >${carrier.name}</option>
						</#list>
						</select>
						</#if>
					</td>
					<th><span>退货运单号: </span></th>
					<td colspan="10">
					<#if  mode == "view">
					${rmaorder.refundTrackNo}
					<#else>
					<input type="text"  name="rmaorder.refundTrackNo" />
					</#if>
					
					
					</td>
					
				</tr>
			
				<tr>
					<th><span>退货原因: </span></th>
					<td>${rmaorder.reasonType.display()}</td>
					<th><span>商品状态: </span></th>
					<td>${rmaorder.itemStatus.display()}</td>
					<th><span>包装状态: </span></th>
					<td>${rmaorder.packageStatus.display()}</td>
					<td colspan="4" />
				</tr>
				
				
				<tr>
					<th><span>详细原因: </span></th>
					<td colspan="10">${rmaorder.reasonDetails}</td>
				</tr>
				
				<tr>
					<th><span>可提供检测报告: </span></th>
					<td><#if rmaorder.testreportIncluded == "true">是<#else>否</#if></td>
					<th><span>寄回赠品: </span></th>
					<td colspan="10"><#if rmaorder.giftReturnFlag == "true">是<#else>否</#if></td>
							
				</tr>
				<tr>
					<th><span>检测报告: </span></th>
					<td colspan="10">
					<#if rmaorder.testreportIncluded == "true">
						<#if rmaorder.testRep1Url != "">
							<a id="a_callback90" href="${systemConfig.resourceWebRootPath}${rmaorder.testRep1Url}" style="text-decoration:underline; color:#cc9966" target="_blank">${rmaorder.testRep1FileName}(${rmaorder.testRep1Size})</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<#if rmaorder.testRep2Url != "">
								<a id="a_callback91" href="${systemConfig.resourceWebRootPath}${rmaorder.testRep2Url}" style="text-decoration:underline; color:#cc9966" target="_blank">${rmaorder.testRep2FileName}(${rmaorder.testRep2Size})</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<#if rmaorder.testRep3Url != "">
									<a id="a_callback92" href="${systemConfig.resourceWebRootPath}${rmaorder.testRep3Url}" style="text-decoration:underline; color:#cc9966" target="_blank">${rmaorder.testRep3FileName}(${rmaorder.testRep3Size})</a>&nbsp;&nbsp;&nbsp;&nbsp;
								</#if>
							</#if>
						</#if>
					</#if>
					</td>
				</tr>
				<tr>
					<th><span>审核备注: </span></th>
					<td colspan="10">${rmaorder.approvalComments}</td>
				</tr>
				<tr>
					<th><span>优惠券类型: </span></th>
					<td>${rmaOrderItemLogBean.couponType.display()}</td>
					<th><span>实际应退积分: </span></th>
					  <#if order.member.isOfflineCard >
					  	<td>该会员为线下会员，积分由线下管理</td>
					  <#else>
						<#if rmaOrderItemLogBean.isDeducted == "true">
						<td>${rmaOrderItemLogBean.actualPoint} （积分余额不足）</td>
						<#else>
						<td>${rmaOrderItemLogBean.actualPoint}</td>
						</#if>
					  </#if>
					<td colspan="10" />
				</tr>
				
				<tr>
					<th><span>银行流水号: </span></th>
					<td >
					<#if mode == "new">
					
					<#elseif mode == "view">
					&nbsp;${rmaorder.order.referenceNo}
					</#if>
					</td>
					<th><span>备注: </span></th>
					<td colspan="10">
					<#if mode == "new">
					<textarea name="rmaorder.memo" class="formTextarea"></textarea>
					<#elseif mode == "view">
					${rmaorder.memo}
					</#if>
					</td>
				</tr>
				
				
			</table>
			<table class="inputTable2">
				<tr>
					<th>款式编码</th>
					<th>SKU编码</th>
					<th>商品名称</th>
					<th>颜色</th>
					<th>尺寸</th>
					<th>订单行项目类型</th>
					<th>数量</th>
					<th>销售价</th>
					<th>成交价</th>
					<th>小计</th>
					<th>退货数量</th>
					<th>备注</th>
				</tr>
			<#assign index = 0>
			<#list orderItemBeans as bean>
				<#assign orderItem = bean.orderItem>
				<#assign components = bean.components>
				<#assign gifts = bean.gifts>
				<#if orderItem.type == "General" || orderItem.type == "Bundle">
					<@productTpl bean index/>
					<#assign index = index + 1>
					<#if orderItem.type == "Bundle">
						<#list components as component>
							<tr type="${component.orderItem.type}" orderItemId="${component.orderItem.id}" parentOrderItemId="${orderItem.id}">
								<td align="right">${component.orderItem.product.partNumber}</td>
								<td align="right">${component.orderItem.sku.skuCode}</td>
								<td align="right">${component.orderItem.product.name}</td>
								<td align="right">${component.orderItem.sku.color}</td>
								<td align="right">${component.orderItem.sku.size}</td>
								<td align="right">${component.orderItem.type.display()}</td>
								<td align="right">${component.orderItem.quantity}</td>
								<td align="right">${component.orderItem.price?string(orderCurrencyFormat)}</td>
								<td align="right"></td>
								<td align="right"></td>
								<td columnType="quantity">
								<#if mode == "new">
								${orderItem.quantity}
								<#elseif mode == "view">
								${component.rmaorderItem.rmaQuantity}
								</#if>
								</td>
								<td align="right"></td>
							</tr>
						</#list>
					</#if>
					<#list gifts as gift>
						<@giftTpl gift index "#99ccff"/>
						<#assign index = index + 1>
					</#list>
				<#elseif orderItem.type == "Gift" && orderItem.source == null>
					<@giftTpl bean index "#9966ff"/>
					<#assign index = index + 1>
				</#if>
			</#list>
			</table>
			<#if mode != "view">
			<div class="buttonArea">
				<input type="submit" class="formButton" value="提交" hidefocus="true" />
			</div>
		</form>
		</#if>
	<table width="100%" border="0">
	<tr>
		<td>
			
			<#if mode == "view">
			
				<div id="flowTraceDiv" width="450px" align="left" style="padding: 10px 0px 10px 20px;">
				<font size="3">库存系统流程跟踪</font><br/><br/>
				
				<table width="445px" border="0" align="left" style="border-top:solid 1px #000000;border-left:solid 1px #000000;">
					<tr>
						<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;" width="140px">时间</td>
						<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;" width="140px">状态</td>
						<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;" width="155px">备注</td>
					</tr>
					<#list orderStatusBeans as bean>
					<#assign orderStatus = bean.orderStatus>
						<tr>
						<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">${orderStatus.recvTime?string("yyyy-MM-dd HH:mm:ss")}</td>
						<#if orderStatus.stat=="WMS_ACCEPT">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">WMS接单</td>
						<#elseif orderStatus.stat=="WMS_REJECT">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">WMS接单失败</td>
						<#elseif orderStatus.stat=="WMS_CANCELED">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">取消</td>
						<#elseif orderStatus.stat=="REJECTED">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">仓库拒收</td>
						<#elseif orderStatus.stat=="WMS_CLOSED">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">关闭</td>
						<#elseif orderStatus.stat=="FULFILLED">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">收货完成</td>
						<#elseif orderStatus.stat=="WMS_CREATE_RMA">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">接单</td>
						<#elseif orderStatus.stat=="WMS_RMA_REJECT">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">接单失败</td>
						<#elseif orderStatus.stat=="WMS_SO_EREJECT">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">接单失败</td>
						<#elseif orderStatus.stat=="DELIVERED">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">已发货</td>
						<#elseif orderStatus.stat=="EXCEPTION">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">发货异常</td>
						<#elseif orderStatus.stat=="CLOSED">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">关闭</td>
						<#elseif orderStatus.stat=="CANCELED">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">取消</td>
						<#elseif orderStatus.stat=="CANCELEDFAIL">
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">取消失败</td>
						<#else>
							<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;"></td>
						</#if>
						<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">${orderStatus.note}</td>
					
						</tr>
					</#list>
				</table>
				</div>		
			
			</#if>
		</td>
		<td>
		
	    <div class="last_num" style=" text-align:right; padding-right:15px; line-height: 25px;">退货单总金额：${rmaOrderItemLogBean.originalAmount?string(orderCurrencyFormat)}元</div>
	    <div class="last_num" style=" text-align:right; padding-right:15px; line-height: 25px;">- 券折扣：${rmaOrderItemLogBean.coponDisaccount?string(orderCurrencyFormat)} 元</div>
	    <div class="last_num" style=" text-align:right; padding-right:15px; line-height: 25px;">- 单品直降金额：${rmaOrderItemLogBean.productDisaccount?string(orderCurrencyFormat)} 元</div>
	    <div class="last_num" style=" text-align:right; padding-right:15px; line-height: 25px;">- 会员优惠分摊金额：${rmaOrderItemLogBean.memberDisaccount?string(orderCurrencyFormat)} 元</div>
	    <div class="last_num" style=" text-align:right; padding-right:15px; line-height: 25px;">- 订单优惠分摊金额：${rmaOrderItemLogBean.orderDisaccount?string(orderCurrencyFormat)} 元</div>
	    <div class="last_num" style=" text-align:right; padding-right:15px; line-height: 25px;">- 积分扣除金额：${rmaOrderItemLogBean.deductedAmount?string(orderCurrencyFormat)} 元</div>
	    <div class="last_num" style=" text-align:right; padding-right:15px; line-height: 25px;">实际应退金额：${rmaOrderItemLogBean.actualRefundAmount?string(orderCurrencyFormat)} 元</div>
		</td>
	</tr>
    </table>
    		
		<#macro productTpl bean idx>
			<#if mode == "new">
			<#if bean.orderItem??>
			<tr style="background: #cccccc" type="${bean.orderItem.type}" orderItemId="${bean.orderItem.id}">
				<input type="hidden" name="rmaorderItems[${idx}].orderItemId" value="${bean.orderItem.id}"/>
				<td>${bean.orderItem.product.partNumber}</td>
				<td>${bean.orderItem.sku.skuCode}</td>
				<td>${bean.orderItem.product.name}</td>
				<td>${bean.orderItem.sku.color}</td>
				<td>${bean.orderItem.sku.size}</td>
				<td>${bean.orderItem.type.display()}</td>
				<td>${bean.orderItem.quantity}</td>
				<td>${bean.orderItem.price?string(orderCurrencyFormat)}</td>
				<td>${bean.orderItem.finalPrice?string(orderCurrencyFormat)}</td>
				<td>${bean.orderItem.amountProduct?string(orderCurrencyFormat)}</td>
				<td>
				<#if mode == "new">
				<input class="formTextT {required: true, digits: true, min: 0, max: ${bean.orderItem.quantity}}" name="rmaorderItems[${idx}].rmaQuantity" value="${bean.orderItem.quantity}"/>
				<#elseif mode == "view">
				${bean.rmaorderItem.rmaQuantity}
				</#if>
				</td>

				<td>
				<#if mode == "new">
				<input class="formTextS" name="rmaorderItems[${idx}].memo" value=""/>
				<#elseif mode == "view">
				${bean.rmaorderItem.memo}
				</#if>
				</td>
			</tr>
			</#if>
			<#elseif mode == "view">
			<#if bean.rmaorderItem??>
			<tr style="background: #cccccc" type="${bean.orderItem.type}" orderItemId="${bean.orderItem.id}">
				<input type="hidden" name="rmaorderItems[${idx}].orderItemId" value="${bean.orderItem.id}"/>
				<td>${bean.orderItem.product.partNumber}</td>
				<td>${bean.rmaorderItem.sku.skuCode}</td>
				<td>${bean.orderItem.product.name}</td>
				<td>${bean.rmaorderItem.sku.color}</td>
				<td>${bean.rmaorderItem.sku.size}</td>
				<td>${bean.orderItem.type.display()}</td>
				<td>${bean.orderItem.quantity}</td>
				<td>${bean.orderItem.price?string(orderCurrencyFormat)}</td>
				<td>${bean.orderItem.finalPrice?string(orderCurrencyFormat)}</td>
				<td>${bean.orderItem.amountProduct?string(orderCurrencyFormat)}</td>
				<td>
				<#if mode == "new">
				<input class="formTextT {required: true, digits: true, min: 0, max: ${bean.orderItem.quantity}}" name="rmaorderItems[${idx}].rmaQuantity" value="${bean.orderItem.quantity}"/>
				<#elseif mode == "view">
				${bean.rmaorderItem.rmaQuantity}
				</#if>
				</td>
				<td>
				<#if mode == "new">
				<input class="formTextS" name="rmaorderItems[${idx}].memo" value=""/>
				<#elseif mode == "view">
				${bean.rmaorderItem.memo}
				</#if>
				</td>
			</tr>
			</#if></#if>
		</#macro>
		
		<#macro giftTpl bean idx color>
			<tr style="background: ${color}" type="${bean.orderItem.type}" orderItemId="${bean.orderItem.id}">
				<input type="hidden" name="rmaorderItems[${idx}].orderItemId" value="${bean.orderItem.id}"/>
				<td>${bean.orderItem.product.partNumber}</td>
				<td>${bean.orderItem.sku.skuCode}</td>
				<td>${bean.orderItem.product.name}</td>
				<td>${bean.orderItem.sku.color}</td>
				<td>${bean.orderItem.sku.size}</td>
				<td>${bean.orderItem.type.display()}</td>
				<td>${bean.orderItem.quantity}</td>
				<td>${bean.orderItem.price?string(orderCurrencyFormat)}</td>
				<td></td>
				<td></td>
				<td>
				<#if mode == "new">
				<input class="formTextT {required: true, digits: true, min: 0, max: ${bean.orderItem.quantity}}" name="rmaorderItems[${idx}].rmaQuantity" value="${bean.orderItem.quantity}"/>
				<#elseif mode == "view">
				${bean.rmaorderItem.rmaQuantity}
				</#if>
				</td>
				<td></td>
			</tr>
		</#macro>
	</div>
	
</body>
</html>