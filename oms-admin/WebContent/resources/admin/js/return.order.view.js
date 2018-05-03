$().ready(
		function() {
			$("#tabs").tabs();

			$("#accordion").accordion({
				collapsible : true
			});
			$.getRefundReason = function(indes) {
				var preRefundReason = $("#preRefundReason_" + indes).val();
				$("#refundReason_" + indes).empty();
				$.getJSON(
						"refund_reason!getRefundReason.action?preRefundReason="
								+ preRefundReason, function(data) {
							var list = data.refundReasonList;
							$.each(list, function(index, item) {
								$("#refundReason_" + indes).append(
										"<option value= ' " + item.reasonNo
												+ " '> " + item.reasonName
												+ "</option>");
							});
						});
			}
		});

function getRefundReasonFun(index) {
	$.getRefundReason(index);
}

function changeSaleNum(index) {
	saleNum = $("#saleNum_" + index).val();
	if (!(/^(\+|-)?\d+$/.test(saleNum)) || saleNum < 0) {
		alert("请输入合法数字");
		$("#saleNum_" + index).val(0);
		$("#saleNum_" + index).focus();
		return false;
	}
	remainNum = $("#remainNum_" + index).val();
	if ((saleNum - remainNum) > 0) {
		alert("退货数量不得超过能够退的数量:" + remainNum);
		$("#saleNum_" + index).val(0);
		$("#saleNum_" + index).focus();
		return;
	}
	//重置重量
	unightweight = $("#weightUnit_" + index).val();
	weight = saleNum * unightweight;
	$("#weight_" + index).text(weight);
	//重置积分
	var unitProductPoint = $("#productPointUnit_" + index).val();
	var productPoint = saleNum * unitProductPoint;
	$("#productPoint_" + index).val(productPoint);
	
	$().caculate();
}

function changeSaleNum4ModifyPage(index) {
	saleNum = $("#saleNum_" + index).val();
	if (!(/^(\+|-)?\d+$/.test(saleNum)) || saleNum < 0) {
		alert("请输入合法数字");
		$("#saleNum_" + index).focus();
		return false;
	}

	remainNum = $("#remainNum_" + index).val();
	var originalNum = $("#originalNum_" + index).val();
	remainNum = parseInt(remainNum) + parseInt(originalNum);
	if (saleNum - remainNum > 0) {
		alert("换货数量不得超过可换数量:" + remainNum);
		$("#saleNum_" + index).val(remainNum);
		$("#saleNum_" + index).focus();
		return;
	}

	unightweight = $("#weightUnit_" + index).val();
	weight = saleNum * unightweight;
	$("#weight_" + index).text(weight);

	$().caculate();
}

/**
 * 校验积分
 * 
 * @returns
 */
function checkIntegral() {
	if ($("#orderCategoryFlag").val() != 'ret') {
		return;
	}

	// $("#integral").text($("#integralLoadInit").val());
	var totalGivePoints = $("#totalGivePoints").text();
	var integral = $("#integral").text();
	var totalPayAmount = $("#totalPayAmount").text();
	if (parseFloat(totalGivePoints) > parseFloat(integral)) {
		// 不够扣的积分折算成的金额 = （回扣积分总额 - 当前账户积分）x 5%
		var convertVal = (parseFloat(totalGivePoints) - parseFloat(integral)) * 0.05;
		// 扣除后的退款总金额 = 扣除前的退款总金额 - 不够扣的积分折算成的金额
		var calcResult = (parseFloat(totalPayAmount) - convertVal).toFixed(2);
		/*
		 * var flag =
		 * confirm("顾客积分账户余额不足，应扣"+totalGivePoints+"积分，顾客账户仅剩"+integral+"积分，建议通过如下方式弥补不足扣回的积分：\n\n" +
		 * "1.修改退款金额为：" + totalPayAmount + "-" + convertVal + "=" + calcResult +
		 * "元；\n" + "2.修改需扣回积分为：" + integral + "积分。\n\n" +
		 * "您是否需要修改金额和积分并重新审批呢？如果选择否，系统会从顾客账户扣除" + integral + "积分，需退金额仍为" +
		 * totalPayAmount + "元。");
		 */

		var flag = confirm("顾客积分账户余额不足，应扣" + totalGivePoints + "积分，顾客账户仅剩"
				+ integral + "积分。\n\n" + "1.如果选择\“确定\”，系统将先扣除账户剩余积分" + integral
				+ "，不足扣的积分折算成现金在退款金额中扣除，修改退款总金额：" + totalPayAmount + "-("
				+ totalGivePoints + "-" + integral + ")x5% =" + calcResult
				+ "元；\n\n" + "2.如果选择\“取消\”，系统将只扣除账户剩余积分" + integral
				+ "，退款总金额仍为" + totalPayAmount + "元。");

		if (flag) {
			// 扣除后的退款总金额
			$("#totalPayAmount").text(calcResult);
			$("#totalPayAmountTmp").val(calcResult);
			$("#orderPayPayAmount_0").val(calcResult);

			// 扣除后的账户积分为0
			// $("#integral").text(0);
			// return false;
		}
		/*
		 * else{ // 如果不折算成金额扣除，则直接扣除账户剩余积分。 $("#integral").text(0); }
		 */
		$("#totalGivePoints").text(integral);
		$("#totalGivePointsTmp").val(integral);
	} else {
		$("#totalGivePointsTmp").val(totalGivePoints);
	}
}

function changeProductPoint(index) {
	productPoint = $("#productPoint_" + index).val();
	if (!jQuery.isNumeric(productPoint) || productPoint < 0) {
		alert("请输入合法数字");
		$("#productPoint_" + index).val(0);
		$("#productPoint_" + index).focus();
		return;
	}
	$().caculate();
	// 校验积分
	checkIntegral();
}

function changeUnitPrice(index) {
	saleNum = $("#saleNum_" + index).val();
	unitPrice = $("#unitPrice_" + index).val();
	unitDiscount = $("#unitDiscount_" + index).val();
	couponAmount = $("#couponAmount_" + index).val();
	if (!jQuery.isNumeric(unitPrice) || unitPrice <= 0) {
		alert("请输入合法数字");
		$("#unitPrice_" + index).val(0);
		$("#unitPrice_" + index).focus();
		return;
	}
	if (unitPrice - unitDiscount < couponAmount) {
		alert("单位价格不能小于优惠金额和购物券使用金额之和");
		$("#unitPrice_" + index).val(0);
		$("#unitPrice_" + index).focus();
		return;
	}
	$().caculate();
}

function changeUnitDiscount(index) {
	saleNum = $("#saleNum_" + index).val();
	unitPrice = $("#unitPrice_" + index).val();
	unitDiscount = $("#unitDiscount_" + index).val();
	couponAmount = $("#couponAmount_" + index).val();
	if (!jQuery.isNumeric(unitDiscount) || unitDiscount < 0) {
		alert("请输入合法数字");
		$("#unitDiscount_" + index).val(0);
		$("#unitDiscount_" + index).focus();
		return;
	}
	if (unitPrice - unitDiscount < couponAmount) {
		alert("单位价格不能小于优惠金额和购物券使用金额之和");
		$("#unitDiscount_" + index).val(0);
		$("#unitDiscount_" + index).focus();
		return;
	}
	$().caculate();
}

// 物流费用
function changeTransportFee() {
	transportFee = $("#transportFee").val();
	if (!jQuery.isNumeric(transportFee)) {
		alert("请输入合法数字");
		$("#transportFee").focus();
		$("#transportFee").val(0);
		return;
	}
	$().caculate();
}

function changeCouponAmount(index) {
	saleNum = $("#saleNum_" + index).val() * 1;
	unitPrice = $("#unitPrice_" + index).val();
	unitDiscount = $("#unitDiscount_" + index).val();
	couponAmount = $("#couponAmount_" + index).val();
	if (!jQuery.isNumeric(couponAmount) || couponAmount < 0) {
		alert("请输入合法数字");
		$("#couponAmount_" + index).focus();
		$("#couponAmount_" + index).val(0);
		return;
	}
	if (unitPrice - unitDiscount < couponAmount) {
		alert("折前单价不能小于优惠金额和购物券使用金额之和");
		$("#couponAmount_" + index).focus();
		$("#couponAmount_" + index).val(0);
		return;
	}
	$().caculate();
}

function deleteRow(obj, index) {
	$("#deleteIds_" + index).val($("#orderRetChgItemsListId_" + index).val());
	$(obj).parent().parent().remove();
	$().caculate();
}

function deletePayRow(obj, index, orderPayId) {
	if (orderPayId != null) {
		$("#deleteOrderPayIds_" + index).val(orderPayId);
	}
	$(obj).parent().parent().remove();
}

function changePayAmount(obj) {
	if (!jQuery.isNumeric($(obj).val()) || $(obj).val() < 0) {
		alert("请输入合法数字");
		$(obj).val(0);
		$(obj).focus();
		return;
	}
	var orderPayAmount = 0;
	var orderCategoryFlag = $("#orderCategoryFlag").val();
	// 如果是退款单或运费补款单，更改了退款信息中的退款金额，退款总金额随之变化
	if (orderCategoryFlag == 'ref' || orderCategoryFlag == 'tsf') {
		$("#retInfoTab").find("tr").find("td:eq(1)").each(function() {
			orderPayAmount += ($(this).find("input[type='text']").val()) * 1;
		});
		$("#totalPayAmount").text(orderPayAmount);
		$("#totalPayAmountRefTsf").val(orderPayAmount);
	}
	// 退、换、拒收单校验退款信息中的退款金额之和不能超过退款总金额
	else {
		var totalPayAmount = $("#totalPayAmount").text();

		$("#retInfoTab").find("tr").find("td:eq(1)").each(function() {
			orderPayAmount += ($(this).find("input[type='text']").val()) * 1;
		});

		if (orderPayAmount > totalPayAmount) {
			alert("不能超过退款总额");
			$(obj).val(0);
			// $(obj).focus();
		}
	}
}

function changePayAmount4Return(obj) {
	if (!jQuery.isNumeric($(obj).val()) || $(obj).val() < 0) {
		alert("请输入合法数字");
		$(obj).val(0);
		$(obj).focus();
		return;
	}
	var orderPayAmount = 0;
	$("#retInfoTab").find("tr").find("td:eq(1)").each(function() {
		orderPayAmount += ($(this).find("input[type='text']").val()) * 1;
	});
	$("#totalPayAmount").text(orderPayAmount);
	$("#totalPayAmountTmp").val(orderPayAmount);
	$("#totalPayAmountRefTsf").val(orderPayAmount);
}

// 不需要退款处理
function noNeedRefund() {
	if ($("#ifNeedRefund").val() == '0') {
		$("#totalPayAmount").text("0");
	}
}

jQuery(function() {
	jQuery("#btnConfirm").click(function() {
		jQuery.confirm({
			callback : function(result) {
				if (result) {
					alert("您选择了\"确定\"");
				} else {
					alert("您选择了\"取消\"");
				}
			}
		});
	});
});
