<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看收款单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>查看收款单</h1>
		</div>
		<table class="inputTable">
			<tr>
				<th>
					收款单号:
				</th>
				<td>
					${payment.paymentSn}
				</td>
				<th>
					订单号:
				</th>
				<td>
					${(payment.order.orderNo)!}
				</td>
				<th>
					付款金额:
				</th>
				<td>
					${(payment.totalAmount?string(orderUnitCurrencyFormat))!}
				</td>				
			</tr>
			<tr>
				<th>
					付款方式:
				</th>
				<td>
					${payment.paymentMode.name}
				</td>
				<th>
					支付平台(银行):					
				</th>
				<td>
					${payment.bankName}
				</td>
				<th>
					帐户:
				</th>
				<td>
					${payment.bankAccount}
				</td>				
			</tr>
			<tr>
				<th>
					会员:
				</th>
				<td>
					${payment.payer}
				</td>
				<th>
					操作员:
				</th>
				<td>
					${payment.operator}
				</td>
				<th>
					状态:
				</th>
				<td>
					${payment.status.display()}
				</td>							
			</tr>
			<tr>
				<th>
					支付时间:
				</th>
				<td>
					${(payment.reconciledDate?string("yyyy-MM-dd"))!}
				</td>
				<th>					
				</th>
				<td>					
				</td>
				<th>					
				</th>
				<td>					
				</td>				
			</tr>
			<tr>
				<th>
					备注:
				</th>
				<td colspan="3">
					${payment.memo}
				</td>
			</tr>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		</div>
	</div>
</body>
</html>