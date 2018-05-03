<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑订单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />


<link href="${base}/resources/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />




<link rel="stylesheet" href="${base}/resources/admin/css/jquery.ui.all.css">
<script src="${base}/resources/admin/js/jquery-1.10.2.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.core.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.widget.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.accordion.js"></script>
<link rel="stylesheet" href="${base}/resources/admin/css/demos.css">

<@sj.head compressed="false" scriptPath="${webuiPath}/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>


<script type="text/javascript">
$().ready( function() {
	$( "#tabs" ).tabs();	
	
		$( "#accordion" ).accordion({
			collapsible: true
		});
	
	$('#statusConfirm').change(function(){
	    var message="确认更改审核状态吗";
	    ( function showDialog(message,_buttons){
			$("#dialog-confirm" ).html(message);
			$( "#dialog-confirm" ).dialog({
				resizable: false,
				height:140,
				modal: true,
				buttons:_buttons
			});}(
			     message,
				 { "取消": function(){
						$( this ).dialog( "close" );
					}, 
					"确定": function() {
					    var statusConfirm = $("#statusConfirm").val();
					    alert(statusConfirm);
					    $.ajax({
				            type: "post",
				            url: "order!updateStatusConfirm.action",
				            data: {"orderSubNo": <#if (orderSub.orderNo)?exists>${orderSub.orderNo}<#else>"aaa"</#if>,
				                   "statusConfirm": statusConfirm},
				            beforeSend: function(XMLHttpRequest){
				            //ShowLoading();
				            },
				            success: function(data, textStatus){
				               //alert("success");
				                alert(data);
				            },
				            complete: function(XMLHttpRequest, textStatus){
				              // alert("complete");
				              
				            },
				            error: function(){
				                alert("执行后台出错");
				            }
                         });
                         
                         $( this ).dialog( "close" );
					}
				  }
			    )
		);
    });
    
    
    
    $('#updateOrder').click(function(){
        alert("updateOrder");
    });
    
    $('#updateOrderSub').click(function(){
        alert("updateOrderSub");
    });
	
	
});

</script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>编辑订单</h1>
		</div>
		<form id="inputForm" class="validate" action="order!update.action" method="post">
		    <input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="order.id" value="${order.id}" />
			<input type="hidden" name="order.orderNo" value="${order.orderNo}" />
			<div class="blank"></div>
			
			
			<div id="tabs">
			<ul>
				<li><a href="#orderMianDiv">基本信息</a></li>
				<li><a href="#orderSubDiv">子订单明细</a></li>
			</ul>			
			<div id="orderMianDiv" style="display:block">
			
			
			<div id="accordion">
			
			  <h3>主订单信息</h3>
			  <div>
				<table class="inputTable tabContent">
					<tr>
						<th>
							订单编号:
						</th>
						<td>
							${order.orderNo}
						</td>
						<th>
							下单时间:
						</th>
						<td>
							${(order.dateCreated?string("yyyy-MM-dd HH:mm:ss"))!}
						</td>
					</tr>
					<tr>
						<th>
							订单来源:
						</th>
						<td>
							${order.orderSource}
						</td>
						<th>
							订单类型:
						</th>
						<td>
							${order.orderType}
						</td>
					</tr>
					<tr>
						<th>
							发票类型:
						</th>
						<td>
							${order.merchantType}
						</td>
						<th>
							发票号码:
						</th>
						<td>
							${order.merchantNo}
						</td>
					</tr>
					<tr>
						<th>
							会员号:
						</th>
						<td>
							${order.memberNo}
						</td>
						<th>
							客户姓名:
						</th>
						<td>
							${order.customerName}
						</td>
					</tr>
					
					
					
					<tr>
						<th>
							商品总金额:
						</th>
						<td>
							<span id="totalProductPrice" class="red">${(order.totalProductPrice?string(orderUnitCurrencyFormat))!}</span>
						</td>
						<th>
							订单总金额:
						</th>
						<td>
							<span id="totalPayAmount" class="red">${(order.totalPayAmount?string(orderUnitCurrencyFormat))!}</span>
						</td>
					</tr>
					<tr>
						<th>
							配送方式:
						</th>
						<td>
							<select name="order.deliveryType.id">
								<#list allDeliveryType as list>
									<option value="${list.id}"<#if (list == order.deliveryType)!> selected</#if>>
										${list.name}
									</option>
								</#list>
							</select>
							<label class="requireField">*</label>
						</td>
						<th>
							支付方式:
						</th>
						<td>
							<select name="order.paymentConfig.id">
								<option value="">
									货到付款
								</option>
								<#list allPaymentConfig as list>
									<option value="${list.id}"<#if (list == order.paymentConfig)!> selected</#if>>
										${list.name}
									</option>
								</#list>
							</select>
							<label class="requireField">*</label>
						</td>
					</tr>
					<tr>
						<th>
							配送费用:
						</th>
						<td>
							<input type="text" name="order.transportFee" class="formText {required: true, min: 0}" value="${order.transportFee}" />
							<label class="requireField">*</label>
						</td>
						<th>
							支付手续费:
						</th>
						<td>
							<input type="text" name="order.paymentFee" class="formText {required: true, min: 0}" value="${order.paymentFee}" />
							<label class="requireField">*</label>
						</td>
					</tr>
					
					
					
					<tr>
						<th>
							留言:
						</th>
						<td>
							${order.clientServiceRemark}
						</td>
						<th>
							系统留言:
						</th>
						<td>
							${order.remark}
						</td>
					</tr>
					
					<#if column==1>
					  <tr>
					    <th>
							审核状态:
						</th>
			        	<td>
				          <select name="orderMain.statusConfirm" id="statusConfirm">
						  <#list orderConfirmStatusList as list>
							<option value="${list.code}"<#if list.code == orderMain.statusConfirm> selected </#if>>
								${list.desc}
							</option>
						   </#list>
					       </select>
					   </td>
					 </tr>
					</#if>
	
					<tr>
						<td colspan="4">
						  <div class="buttonArea">
				              <input type="button" class="formButton" value="更新主订单" hidefocus="true"  id="updateOrder"/>&nbsp;&nbsp;&nbsp;&nbsp;
			               </div>
						</td>
					</tr>
	
	
	
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
				</table>
				</div>
				
				
				<h3>子订单信息</h3>
				<div>
				<table class="inputTable tabContent">
				<tr>
					<th>
						子订单编号:
					</th>
					<td>
						${orderSub.orderNo}
					</td>
				</tr>
				<tr>
					<th>
						发票编号:
					</th>
					<td>
						${orderSub.deliveryMerchantNo}
					</td>
					<th>
					            配送方式:
					</th>
					<td>
						${orderSub.distributeType}
					</td>
				</tr>
				<tr>
					<th>
						运费:
					</th>
					<td>
						<span id="totalProductPrice" class="red">${(orderSub.transportFee?string(orderUnitCurrencyFormat))!}</span>
					</td>
					<th>
						包裹详情:
					</th>
					<td>
						${orderSub.packageDetail}
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						收货人姓名:
					</th>
					<td>
						<input type="text" name="orderSub.userName" class="formText {required: true}" value="${orderSub.userName}" />
					</td>
					<th>
						收货地区:
					</th>
					<td>
						<input type="text" name="orderSub.addressCode" class="areaSelect hidden {required: true, messagePosition: '#areaMessagePosition'}" value="${(orderSub.addressCode)!}" />
						<span id="areaMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						收货地址:
					</th>
					<td>
						<input type="text" name="orderSub.addressDetail" class="formText {required: true}" value="${orderSub.addressDetail}" />
					</td>
					<th>
						邮编:
					</th>
					<td>
						<input type="text" name="orderSub.postCode" class="formText {required: true, zipCode: true}" value="${orderSub.postCode}"  />
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
				
				<tr>
						<td colspan="4">
						  <div class="buttonArea">
				              <input type="button" class="formButton" value="更新子订单" hidefocus="true" id="updateOrderSub"/>&nbsp;&nbsp;&nbsp;&nbsp;
			               </div>
						</td>
					</tr>
				
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
			</table>
			</div>
			</div>
			
          </div>
          
         
            <div id="orderSubDiv" style="display:none">
				
				  <#include "/WEB-INF/template/admin/order/order_sub_input.ftl">
				
			</div>
			</div>
			
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
			
			
		</form>
	</div>
</body>
</html>