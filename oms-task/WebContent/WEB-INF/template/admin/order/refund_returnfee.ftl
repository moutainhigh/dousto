<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http//www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http//www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新建退费用单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">

<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/scripts/refund/returnfee.js"></script>

</head>
<script type="text/javascript">
$().ready( function() {
	$("#submitbutton").click(function(){
		if ($("#orderNo").val()==""){
			alert("订单号不能为空");
			$("#orderNo").focus();
			return ;
		}

		if ($("#orderType").val()=="returnOrder"&&$("#refundSn").val()==""){
			alert("退换货订单号不能为空");
			$("#refundSn").focus();
			return ;
		}		
	
		var totalPay = Number($("#totalAmount").val());
		var reg = RegExp('^\\d+(\\.\\d{0,2})?$');		
		
		var offlineBankName = $("#offlineBankName").val().trim();
		var offlineBankAccount = $("#offlineBankAccount").val().trim();
		var offlinePayee = $("#offlinePayee").val().trim();

		var prypayAmount = $("#prepayAmount").val();
		if (prypayAmount!=""&&!reg.test(prypayAmount)){
			alert("预存款退款金额必须为正的数字，且小数位不能超过两位");
			$("#prepayAmount").focus();
			return ;
		}
		prypayAmount=Number(prypayAmount);
		
		var offlinePayAmount = $("#offlinePayAmount").val();
		if (offlinePayAmount!=""&&!reg.test(offlinePayAmount)){
			alert("线下退款金额必须为正的数字");
			$("#offlinePayAmount").focus();
			return ;
		}

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
		
		offlinePayAmount=Number(offlinePayAmount);
		
		var sumPay = offlinePayAmount+prypayAmount;
		
		if (sumPay<=0){
			alert("退款金额合计必须大于0");
			return ;		
		}
		
		$("#applyReturnAmount").val(sumPay);
		$("#inputForm").submit();
	});
	$(".returnOrder").hide();
});

function orderChange(orderTypeSelect){
	var value= $(orderTypeSelect).val();
	if (value=="refundOrder"){
		$(".returnOrder").hide();
	}else if (value=="returnOrder"){
		$(".returnOrder").show();
	}
}

</script>

<body class="input list">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>退费用</h1>
		</div>		
		<table class="inputTable">
			<tr>
				<th>
					订单类型
				</th>
				<td>
					<select name="orderType" id="orderType" onchange="orderChange(this)">
						<option value="refundOrder">
							订单
						</option>
						<option value="returnOrder">
							退换货订单
						</option>						
					</select>
				</td>
				<th>
					订单号
				</th>
				<td>
					<label class="searchText"><input type="text" id="orderNo" name="orderNo"></label>
					<label class="requireField">*</label>
				</td>
				<th class="returnOrder">
					退换货订单号
				</th>
				<td class="returnOrder">
					<label class="searchText"><input type="text" id="refundSn" name="refundSn"></label>
					<label class="requireField">*</label>								
				</td>							
			</tr>
		<form id="inputForm" action="refund!returnFee.action" method="post">
			<input type="hidden" id="orderId" name="refund.orderId">
			<input type="hidden" id="memberId" name="refund.memberId">
			<input type="hidden" id="totalAmount">
			<input type="hidden" id="applyReturnAmount" name="refund.applyReturnAmount">
			<tr>
				<th>
					会员
				</th>
				<td>
					<span id="memberNameSpan"></span>
				</td>
				<th>
					申请退款总额
				</th>
				<td>
					<span id="totalAmoutSpan"></span>					
				</td>
				<th>
					
				</th>
				<td>
					
				</td>				
			</tr>
			<tr>
				<th>
					备注
				</th>
				<td colspan="5">
					<input type="text" name="refund.memo" size="100">
				</td>
			
			</tr>
		</table>
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
						<input type="hidden" name="items[0].id" value="" >
					</td>
					<td>
					</td>
					<td>
					</td>
					<td>
					</td>
					<td>
					<input id="prepayAmount" type="text" name="items[0].amount" value="" >
					</td>															
				</tr>
				<tr>
					<td>
						线下退款
						<input type="hidden" name="items[1].refundMethod" value="offline" />
						<label class="requireField">*</label>
					</td>
					<td>
						<input type="text" id="offlineBankName" name="items[1].bankName" value="" >				
					</td>												
					<td>
						<input type="text" id="offlineBankAccount" name="items[1].bankAccount" value="" >	
					</td>												
					<td>
						<input type="text" id="offlinePayee" name="items[1].payee" value="" >
					</td>
					<td>
					<input id="offlinePayAmount" type="text" name="items[1].amount" value="" >
					</td>															
				</tr>				
		</table>
		<div class="buttonArea">
			<input id="submitbutton" type="button" class="formButton" value="提   交" hidefocus="true" />
		</div>
		</form>
	
	<div id="searchDialog"></div>
	</div>
</body>
</html>