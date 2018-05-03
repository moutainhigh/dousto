/*******************************************************************************
 * IBM Register JavaScript Copyright (c) 2008 IBM. All rights reserved.
 */

function submitInputForm() {
	var currentDate = new Date();
	currentDate.setHours(0, 0, 0, 0);
	currentDate=new Date(Date.parse(currentDate)+86400000);
	if ($("#reconciledDate").val() == "") {
		alert("请输入付款日期！");
		$("#reconciledDateButton").focus();
		return;
	}
	var reDate = cal.date;
	if (currentDate - reDate <= 0) {
		alert("收款日期只能是过去的时间！");
		$("#reconciledDateButton").focus();
		return;
	}
	if ($("#orderId").val() == "") {
		alert("请选择可用的订单！");
		$("#orderNo").focus();
		return;
	}
	var reg = RegExp('^\\d+(\\.\\d{0,2})?$');
	var totalAmount = $("#totalAmount").val();
	if (totalAmount == "" || !reg.test(totalAmount)) {
		alert("付款金额必须为正的数字，且小数位不能超过两位");
		$("#totalAmount").focus();
		return;
	}

	$("form")[0].submit();
}

var setBank = function() {
	var $_select = $("#paymentMethodSelect").find("option:selected");
	var bankName = $_select.attr("bankName");
	var bankAccount = $_select.attr("bankAccount");
	$("#bankNameInput").val(bankName);
	$("#bankAccountInput").val(bankAccount);

	$("#bankName").empty();
	$("#bankName").append(bankName);

	$("#bankAccount").empty();
	$("#bankAccount").append(bankAccount);

};

var modeChange = function(){
	var $_select = $("#paymentModeSelect").find("option:selected");
	var status = $_select.attr("status");
	if (status =="prypay"){
		$("#paymentMethodSelect").hide();
		$("#bankName").empty();
		$("#bankAccount").empty();		
		$("#payTh").hide();	
		$("#bankTr").hide();		
	}else if (status =="online"){
		$("#paymentMethodSelect").show();
		$("#payTh").show();	
		$("#bankTr").show();
		setBank();
	}
}


var clearValues = function() {
	$("#orderId").val("");
	$("#memberId").val("");
	$("#totalAmount").val("");
	$("#memberNameSpan").empty();
	$("#payment_orderNo").val("");
};
var cal;
$().ready(function() {

	$("#orderNo").blur(function() {
		if ($("#orderNo").val() == "") {
			clearValues();
			return;
		}

		$.ajax({
			url : "refund!searchOrder.action",
			data : $("#orderNo").serialize(),
			dataType : "json",
			async : false,
			beforeSend : function() {
				$("#orderId").val("");
			},
			error: function(data){
				alert(data);
			},
			success : function(data) {
				if (data.status == "error") {
					clearValues();
					alert(data.message);
					return;
				}

				$("#orderId").val(data.orderId);
				$("#memberId").val(data.memberId);
				$("#totalAmount").val(data.totalAmount);

				$("#memberNameSpan").empty();
				$("#memberNameSpan").append(data.memberName);

				$("#payment_orderNo").val($("#orderNo").val());

				setBank();
			}
		});
	});

	$("#paymentModeSelect").change(modeChange);
	
	$("#paymentMethodSelect").change(setBank);
	
	
	
	cal=Calendar.setup({
		trigger : "reconciledDateButton",
		inputField : "reconciledDate",
		dateFormat : "%Y-%m-%d %H:%M",
		showTime : true,
		onSelect : function() {
			this.hide();
		}
	});

});

function submitForm(form, url) {
	$(form).attr("action", url);
	$(form).submit();
}

function debug_showForm(form) {

	var debugDialog = $("#debugDialog");

	var result = "";
	$(form).find("input").each(function(i) {
		result += $(this).attr("name") + "=" + $(this).attr("value") + " ";
		if (i % 5 == 0)
			result += "<br>";
	});

	$("<p>" + result + "</p>").appendTo(debugDialog);
	debugDialog.dialog({
		autoOpen : true,
		width : 600,
		height : 400,
		modal : true,
		resizable : true,
		autoResize : true,
		close : function(event, ui) {
			$(this).dialog('destroy');
			$("#debugDialog").empty();
		},
		open : function(event, ui) {
			$(this).css('width', "100%").css('padding', "0")
		},
		title : "Debug窗口"
	});

}

function openDialog(id, displayTitle, width, height) {
	$("#" + id).dialog({
		autoOpen : true,
		width : width,
		height : height,
		modal : true,
		resizable : true,
		autoResize : true,
		close : function(event, ui) {
			$(this).dialog('destroy');
		},
		open : function(event, ui) {
			$(this).css('width', "100%").css('padding', "0")
		},
		title : displayTitle
	});
}

function closeDialog(id) {
	$("#" + id).dialog("close");
}