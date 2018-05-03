function updateReshipOrderDetail(event) {
	$('#inputForm').validate();
	var rmaQuantities = $('#inputForm input[name$=".rmaQuantity"]');
	var returnPrices = $('#inputForm input[name$=".returnPrice"]');
	var totalRmaQuantity = $('#totalRmaQuantity');
	var returnProductAmount = $('#returnProductAmount');
	var totalReturnAmount = $('#totalReturnAmount');
	var totalPaidAmount = $('#totalPaidAmount');
	
	if (rmaQuantities.valid()) {
		if (event) {
			var that = $(this);
			var rmaorderItemRow = that.parent().parent();
			var orderItemId = rmaorderItemRow.attr('orderItemId');
			if (rmaorderItemRow.attr('type') == 'Bundle' && that.attr('name').match(/rmaQuantity$/)) {
				var componentItemRows = rmaorderItemRow.siblings('[type="Component"][parentOrderItemId="' + orderItemId + '"]');
				componentItemRows.each(function(index, row) {
					$(row).children('[columnType="quantity"]').text(that.val());
				});
			}
		}
		
		var rmaQuantity = 0;
		rmaQuantities.each(function(index, element) {
			rmaQuantity += parseInt($(element).val());
		});
		totalRmaQuantity.text(rmaQuantity);
		
		if (returnPrices.valid()) {
			var returnPrice = 0.0;
			returnPrices.each(function(index, element) {
				var itemReturnPrice = parseFloat($(element).val());
				var matchQuantityElement = $(element).parent().prev().children().first();
				var matchQuantity = parseInt(matchQuantityElement.val());
				var subReturnPrice = matchQuantity * itemReturnPrice;
				$(element).parent().next().children().first().text(priceCurrencyFormat(subReturnPrice));
				returnPrice += subReturnPrice;
			});
			
			var totalPaidAmoutValue = parseFloat(totalPaidAmount.text());
			
			returnProductAmount.text(priceCurrencyFormat(returnPrice));
			totalReturnAmount.text(priceCurrencyFormat(Math.min(totalPaidAmoutValue, returnPrice)));
		} else {
			returnProductAmount.text(priceCurrencyFormat(0));
			totalReturnAmount.text(priceCurrencyFormat(0));
		}
	} else {
		totalRmaQuantity.text("");
		returnProductAmount.text(priceCurrencyFormat(0));
		totalReturnAmount.text(priceCurrencyFormat(0));
	}
}

function updateOfflineInputStatus() {
	var bankName = $('#bankName');
	var bankAccount = $('#bankAccount');
	var payee = $('#payee');
	
	if (isOfflineRefund()) {
		bankName.attr("disabled", false);
		bankAccount.attr("disabled", false);
		payee.attr("disabled", false);
		bankName.next().show();
		bankAccount.next().show();
		payee.next().show();
	} else {
		bankName.attr("disabled", true);
		bankAccount.attr("disabled", true);
		payee.attr("disabled", true);
		bankName.val("");
		bankAccount.val("");
		payee.val("");
		bankName.next().hide();
		bankAccount.next().hide();
		payee.next().hide();
	}
}

function isOfflineRefund() {
	return $('#refundMethod').val() == 'offline';
}