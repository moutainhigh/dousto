<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http//www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http//www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>提交退款单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
$().ready( function() {
	$("#submitbutton").click(function(){
		var totalPay = Number("${(refund.applyReturnAmount)!}");
		var totalReturnAmount = Number("${(refund.totalReturnAmount)!}");		
		var totalPaidPrepay = Number("${(refund.totalPaidPrepay)!}");
		var refundType = "${refund.refundType.name()}";
		var reg = RegExp('^\\d+(\\.\\d{0,2})?$');		
		
		var prypayAmount = $("#prepayAmount").val();
		if (prypayAmount!=""&&!reg.test(prypayAmount)){
			alert("预存款退款金额必须为正的数字，且小数位不能超过两位");
			$("#prepayAmount").focus();
			return ;
		}

		if (prypayAmount==""||prypayAmount=="undifined"){
			prypayAmount="0";
		}
		prypayAmount=Number(prypayAmount);
		//当退款类型为退货款时，预存款退款金额需要大于等于min（退款总额、原订单预存款金额）。
		if (refundType=="refundProductAmount"){
			if (totalReturnAmount<=totalPaidPrepay&&prypayAmount<totalReturnAmount){
				alert("预存款退款金额必须大于等于 退款总额和原订单预存款金额 两者中的小者");
				$("#prepayAmount").focus();
				return ;
			}
			if (totalPaidPrepay<=totalReturnAmount&&prypayAmount<totalPaidPrepay){
				alert("预存款退款金额必须大于等于原订单预存款金额");
				$("#prepayAmount").focus();
				return ;
			}
		}
		
		var offlinePayAmount = $("#offlinePayAmount").val();
		if (offlinePayAmount!=""&&!reg.test(offlinePayAmount)){
			alert("线下退款金额必须为正的数字");
			$("#offlinePayAmount").focus();
			return ;
		}
		offlinePayAmount=Number(offlinePayAmount);
				
		if (prypayAmount!=""&&prypayAmount>totalPay){
			alert("预存款退款金额不能大于申请退款总金额");
			$("#prepayAmount").focus();
			return ;
		}
		
		if (offlinePayAmount!=""&&offlinePayAmount>totalPay){
			alert("线下退款金额不能大于申请退款总金额");
			$("#offlinePayAmount").focus();
			return ;
		}
		
		var offlineBankName = $("#offlineBankName").val().trim();
		var offlineBankAccount = $("#offlineBankAccount").val().trim();
		var offlinePayee = $("#offlinePayee").val().trim();
		
		if (offlinePayAmount!=""&&offlineBankName==""){
			alert("线下退款必须指定银行");
			$("#offlineBankName").focus();
			return ;
		}
		
		if (offlinePayAmount!=""&&offlineBankAccount==""){
			alert("线下退款必须指定帐号");
			$("#offlineBankAccount").focus();
			return ;
		}
		
		if (offlinePayAmount!=""&&offlinePayee==""){
			alert("线下退款必须指定收款人");
			$("#offlinePayee").focus();
			return ;
		}			
		
		var sumPay = offlinePayAmount+prypayAmount;
		if (sumPay>totalPay){
			alert("退款金额合计起来不能大于申请退款总金额");
			return ;		
		}
		
		if (sumPay<=0){
			alert("退款金额合计必须大于0");
			return ;		
		}
		$("#inputForm").submit();
	});
});
</script>

<body class="input list">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>提交退款单</h1>
		</div>
		<table class="inputTable">
			<tr>
				<th>
					退款单号
				</th>
				<td>
					${refund.refundSn}
				</td>
				<th>
					状态
				</th>
				<td>
					${refund.status.display()}
				</td>
				<th>
					生成时间
				</th>
				<td>
					${refund.dateCreated?string("yyyy-MM-dd hh:mm")}
				</td>					
			</tr>
			<tr>
				<th>
					订单编号
				</th>
				<td>
					${(refund.order.orderNo)!}
				</td>			
				<th>
					退换货订单号
				</th>
				<td>
					${(refund.rmaorderId)!}
				</td>
				<th>
					会员
				</th>
				<td>
					${(refund.member.accountName)!}
				</td>
			</tr>
			<tr>
				<th>
					退款类型
				</th>
				<td colspan="5">
					${refund.refundType.display()}
				</td>
			</tr>
			<tr>
				<th>
					退款总额
				</th>
				<td >
				
					${((refund.totalReturnAmount)!0)?string(orderUnitCurrencyFormat)}
				</td>
				<th>
					原订单预存款金额
				</th>
				<td>
					${((refund.totalPaidPrepay)!0)?string(orderUnitCurrencyFormat)}
				</td>
				<th>
					申请退款总额
				</th>
				<td>
					${((refund.applyReturnAmount)!0)?string(orderUnitCurrencyFormat)}
				</td>				

			</tr>
		</table>
		<form id="inputForm" action="refund!submit.action" method="post">
		<input type="hidden" name="id" value="${refund.id}">
		<table class="listTable">
				<tr>
					<th>
						<span>退款方式</span>
					</th>
					<th>
						<span>银行</span>
					</th>
					<th>
						<span>帐号</span>
					</th>
					<th>
						<span>收款人</span>
					</th>
					<th>
						<span>金额</span>
					</th>
				</tr>
				<tr>
					<td>
						预存款退款
						<input type="hidden" name="items[0].refundMethod" value="prepay" >
						<input type="hidden" name="items[0].id" value="${(prepay.id)!}" >
					</td>
					<td>
					</td>												
					<td>
					</td>												
					<td>
					</td>
					<td>
					<input id="prepayAmount" type="text" name="items[0].amount" value="${(prepay.amount)!}" >
					</td>															
				</tr>
				<tr>
					<td>
						线下退款
						<input type="hidden" name="items[1].refundMethod" value="offline" >
						<input type="hidden" name="items[1].id" value="${(offline.id)!}" >		
					</td>
					<td>
						<input type="text" id="offlineBankName" name="items[1].bankName" value="${(offline.bankName)!}" >				
					</td>												
					<td>
						<input type="text" id="offlineBankAccount" name="items[1].bankAccount" value="${(offline.bankAccount)!}" >	
					</td>												
					<td>
						<input type="text"" id="offlinePayee" name="items[1].payee" value="${(offline.payee)!}" >
					</td>
					<td>
					<input id="offlinePayAmount" type="text" name="items[1].amount" value="${(offline.amount)!}" >元
					</td>						

														
				</tr>				
		</table>
		<div class="buttonArea">
		
			<input id="submitbutton" type="button" class="formButton" value="提   交" hidefocus="true"   />
		</div>
		</form>

	</div>
</body>
</html>