<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新增收款单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="/sc-webui/scripts/jscal2/css/jscal2.css" />
<link rel="stylesheet" type="text/css" href="/sc-webui/scripts/jscal2/css/border-radius.css" />
<link rel="stylesheet" type="text/css" href="/sc-webui/scripts/jscal2/css/steel/steel.css" />


<script type="text/javascript" src="/sc-webui/scripts/jscal2/js/jscal2.js"></script>
<script type="text/javascript" src="/sc-webui/scripts/jscal2/js/lang/cn.js"></script>
<script type="text/javascript" src="${base}/scripts/payment/payment.js"></script>

</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>新增收款单</h1>
		</div>
		<form id="inputForm" class="validate" action="payment!save.action" method="post">
		
		<input type="hidden" id="orderId" name="payment.orderId">
		<table class="inputTable">
			<tr>
				<th>
					订单号:
				</th>
				<td>
					<label class="searchText"><input type="text" id="orderNo" name="orderNo"></label>
					<label class="requireField">*</label>
					<input type="hidden" id="payment_orderNo" name="payment.orderNo">
				</td>
				<th>
					付款金额:
				</th>
				<td>
					<input type="text" id="totalAmount" name="payment.totalAmount">元
					<label class="requireField">*</label>
				</td>				

			</tr>
			<tr>
				<th>
					会员:
				</th>
				<td>
					<label class="searchText" id="memberNameSpan"></label>
				</td>
			</tr>
			<tr>
				<th>
					付款方式:
				</th>
				<td>
					<select id="paymentModeSelect" name="payment.paymentModeId">
						<option value="${onlinePaymentModeId}" status="online" selected>
							在线支付
						</option>
						<option value="${depositPaymentModeId}" status="prypay">
							预存款
						</option>						
					</select>
				</td>
				<th id="payTh">
					支付平台:					
				</th>
				<td >
					<select id="paymentMethodSelect" name="payment.paymentMethodId">
					<#list paymentMethods as paymentMethod>
						<option value="${paymentMethod.id}" bankName="${paymentMethod.bankName}" bankAccount="${paymentMethod.bankAccount}">
						${paymentMethod.name}
						</option>
					</#list>
					</select>
				</td>
			</tr>
			<tr id="bankTr">
				<th>
					银行名称:
				</th>
				<td>
					<input type="hidden" id="bankNameInput" name="payment.bankName">
					<label class="searchText" id="bankName"></label>
				</td>				
				<th>
					帐户:
				</th>
				<td >
					<input type="hidden" id="bankAccountInput" name="payment.bankAccount">
					<label class="searchText" id="bankAccount"></label>
				</td>							
			</tr>
			<tr>
				<th>
					支付时间:
				</th>
				<td>
					<input type="text" readonly="true" id="reconciledDate" name="payment.reconciledDate">
					<input type="button" id="reconciledDateButton" value="选择日期">
				</td>	
				<th>
					流水号:
				</th>
				<td >
					<input type="text" id="referenceNo" name="payment.referenceNo">
				</td>
			</tr>
			<tr>
				<th>
					备注:
				</th>
				<td colspan="5">
					<textarea id="memo" name="payment.memo"></textarea>
				</td>
			</tr>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" value="确  定" onclick="submitInputForm()" />&nbsp;&nbsp;&nbsp;&nbsp;	
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		</div>
		</form>
	</div>
</body>
</html>