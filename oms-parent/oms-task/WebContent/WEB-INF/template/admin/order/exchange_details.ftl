<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title><#if mode == "new">新建<#elseif mode == "view">查看</#if>换货单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/scripts/reship/reship.js"></script>


<#if mode == "new">
<script type="text/javascript">
$().ready(function() {
	$('#inputForm').validate();
	    
	$('#refundMethod').change(updateOfflineInputStatus);
	
	$('#inputForm input[name$=".rmaQuantity"]').change(updateReshipOrderDetail);
	$('#inputForm input[name$=".returnPrice"]').change(updateReshipOrderDetail);
	
	$('#inputForm input[name$=".rmaQuantity"]').focus(function() { $(this).select(); });
	$('#inputForm input[name$=".returnPrice"]').focus(function() { $(this).select(); });
	
	updateOfflineInputStatus();
	updateReshipOrderDetail();
});

function getSizeByCommodity(s){
	var color_opt = "";
	var color = "#skuColor"+s+" option:selected";
     var commodityId = $(color).attr("name");
     if(commodityId!=""){
     	
				    
					 $.get("${base}"+"/order/get_sizes_by_commodity.action?commodityId="+commodityId,
					 function(data){
					 if(data.skuList.length!=0){
					 	
						  for(var i=0; i<data.skuList.length;i++){
						  color_opt=color_opt+"<option value='"+data.skuList[i].id+"' >"+data.skuList[i].size+"</option>"
						 	
						 }
					 }
					 color_opt = "<option value=''>---请选择---</option>"+color_opt
					$("#skuSize"+s).html(color_opt);
					 },"json"
					 );
	 
	  }else{
	   color_opt = "<option value=''>---请选择---</option>"+color_opt
					$("#skuSize"+s).html(color_opt);
	  }
}

</script>
</#if>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span><#if mode == "new">新建<#elseif mode == "view">查看</#if>换货单</h1>
		</div>
		<#if mode != "view">
		<form id="inputForm" class="validate" action="exchange!submit.action" method="post">
			<input type="hidden" name="rmaorder.orderId" value="${order.id}"/></#if>
			<span id="totalPaidAmount" style="display: none;">${order.totalPaid!0}</span>
			<table class="inputTable">
				<#if mode == "view">
				<tr>
					<th><span>换货单号: </span></th>
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
					<td colspan="6"/>
				</tr>
				<tr>
					<th><span>订单金额: </span></th>
					<td>${((order.totalAmount!0)?string(orderCurrencyFormat))!}</td>
					<th><span>已付金额: </span></th>
					<td><label id="totalPaid">${((order.totalPaid!0)?string(orderCurrencyFormat))!}</label></td>
					
					
					<th><span>换货总数量: </span></th>
					<td colspan="10"><label id="totalRmaQuantity"/>${(rmaorder.totalRmaQuantity)!}</td>
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
					<th><span>换货原因: </span></th>
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
					<td colspan="8"><#if rmaorder.giftReturnFlag == "true">是<#else>否</#if></td>
							
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
					<th><span>换货地址: </span></th>
					<td colspan="10">${shippingAddress}</td>
				</tr>
				
				<tr>
					<th><span>备注: </span></th>
					<td colspan="3">
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
					<th><div>款式编码</div></th>
					<th><div>SKU编码</div></th>
					<th><div>商品名称</div></th>
					<th><div>颜色</div></th>
					<th><div>尺寸</div></th>
					<th><div>订单行项目类型</div></th>
					<th><div>数量</div></th>
					<th><div>换货商品类型</div></th>
					<th><div>换货数量</div></th>
					<th><div>备注</div></th>
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
							<tr  type="${component.orderItem.type}" orderItemId="${component.orderItem.id}" parentOrderItemId="${orderItem.id}">
								<td >${component.orderItem.product.partNumber}</td>
								<td>${component.orderItem.sku.skuCode}</td>
								<td>${component.orderItem.product.name}</td>
								<td>${component.orderItem.sku.color}</td>
								<td>${component.orderItem.sku.size}</td>
								<td>${component.orderItem.type.display()}</td>
								<td>${component.orderItem.quantity}</td>
								<td>${component.orderItem.price?string(orderCurrencyFormat)}</td>
								<td></td>
								<td></td>
								<td columnType="quantity">
								<#if mode == "new">
								${orderItem.quantity}
								<#elseif mode == "view">
								${component.rmaorderItem.rmaQuantity}
								</#if>
								</td>
								<td></td>
								<td></td>
								<td></td>
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
				<input type="submit" class="formButton"  value="提交" hidefocus="true" />
			</div>
		</form>
		</#if>
		
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
		
		
	
		
		<#macro productTpl bean idx>
		<#if mode == "new">
		<#if bean.orderItem??>
			
		
			<tr  style="background: #cccccc" type="${bean.orderItem.type}" orderItemId="${bean.orderItem.id}">
				<input type="hidden" name="rmaorderItems[${idx}].orderItemId" value="${bean.orderItem.id}"/>
				<td>${bean.orderItem.product.partNumber}</td>
				<td>${bean.orderItem.sku.skuCode}</td>
				<td>${bean.orderItem.product.name}</td>
				<td>
				<#--  颜色        -->
				<#if mode == "new">
				<select id="skuColor${idx}" class="formTextT {required: true}" name="rmaorderItems[${idx}].sku.color" onchange="getSizeByCommodity(${idx});">
				<option name="" value="" >---请选择---</option>
				<#list bean.orderItem.product.commodityRelList as commodityList>
					 
					<option name="${commodityList.id}" <#if "${bean.orderItem.sku.commodityId}"=="${commodityList.id}">selected</#if> value="${commodityList.color}">${commodityList.color}</option>
					
				</#list>
				</select >
				<#elseif mode == "view">
				
				${(bean.rmaorderItem.sku.color)!}
				</#if>
				</td>
				
				<#--  	尺寸        -->
				<td>
				<#if mode == "new">
				<select id="skuSize${idx}" class="formTextT {required: true}"  name="rmaorderItems[${idx}].sku.id">
			    <option value="">---请选择---</option>
			    <#list bean.orderItem.product.commodityRelList as commodities>
					 <#if bean.orderItem.sku.commodityId==commodities.id>
					 <#list commodities.skus as sku>
					<option <#if "${(bean.orderItem.sku.id)!}"=="${sku.id}">selected</#if> value="${sku.id}">${sku.size}</option>
					 </#list>
					 </#if>
				</#list>
				</select >
				<#elseif mode == "view">
				${(bean.rmaorderItem.sku.size)!}
				</#if>
				
				
				</td>
				<td>${bean.orderItem.type.display()}</td>
				<td>${bean.orderItem.quantity}</td>
				
				
				
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
			
			<#if bean.rmaorderItem.exchangeType=="Return" >
				<tr  style="background: #cccccc" type="${bean.orderItem.type}" orderItemId="${bean.orderItem.id}">
			<#else>
				<tr  style="background: #99CCCC" type="${bean.orderItem.type}" orderItemId="${bean.orderItem.id}">
			</#if>
			
				<input type="hidden" name="rmaorderItems[${idx}].orderItemId" value="${bean.orderItem.id}"/>
				<td>${bean.orderItem.product.partNumber}</td>
				<td>${bean.rmaorderItem.sku.skuCode}</td>
				<td>${bean.orderItem.product.name}</td>
				<td>
				<#--  颜色        -->
				<#if mode == "new">
				<select id="skuColor${idx}" class="formTextT {required: true}" name="rmaorderItems[${idx}].sku.color" onchange="getSizeByCommodity(${idx});">
				<option name="" value="" >---请选择---</option>
				<#list bean.orderItem.product.commodityRelList as commodityList>
					 
					<option name="${commodityList.id}" <#if "${bean.orderItem.sku.commodityId}"=="${commodityList.id}">selected</#if> value="${commodityList.color}">${commodityList.color}</option>
					
				</#list>
				</select >
				<#elseif mode == "view">
				
				${(bean.rmaorderItem.sku.color)!}
				</#if>
				</td>
				
				<#--  	尺寸        -->
				<td>
				<#if mode == "new">
				<select id="skuSize${idx}" class="formTextT {required: true}"  name="rmaorderItems[${idx}].sku.id">
			    <option value="">---请选择---</option>
			    <#list bean.orderItem.product.commodityRelList as commodities>
					 <#if bean.orderItem.sku.commodityId==commodities.id>
					 <#list commodities.skus as sku>
					<option <#if "${(bean.orderItem.sku.id)!}"=="${sku.id}">selected</#if> value="${sku.id}">${sku.size}</option>
					 </#list>
					 </#if>
				</#list>
				</select >
				<#elseif mode == "view">
				${(bean.rmaorderItem.sku.size)!}
				</#if>
				
				
				</td>
				<td>${bean.orderItem.type.display()}</td>
				<td>${bean.orderItem.quantity}</td>
				
				
				
				<td>
				<#if mode == "new">
				<input class="formTextT {required: true, digits: true, min: 0, max: ${bean.orderItem.quantity}}" name="rmaorderItems[${idx}].rmaQuantity" value="${bean.orderItem.quantity}"/>
				<#elseif mode == "view">
				${bean.rmaorderItem.exchangeType.display()}
				</#if>
				</td>
				
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
				<td><!--${bean.orderItem.price?string(orderCurrencyFormat)}-->
				0
				</td>
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
				<td></td>
				<td></td>
			</tr>
		</#macro>
	</div>
</body>
</html>