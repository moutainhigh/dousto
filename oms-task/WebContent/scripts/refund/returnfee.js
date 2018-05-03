/*******************************************************************************
 * IBM Register JavaScript Copyright (c) 2008 IBM. All rights reserved.
 */


var clearAll = function(){
	$("#orderId").val("");
	$("#memberId").val("");
	$("#memberName").val("");
	$("#totalAmount").val("");
	$("#memberNameSpan").empty();
	$("#totalAmoutSpan").empty();
};

$().ready( function() {
	
	$("#orderNo").blur(function(){
		if ($("#orderNo").val()==""){
			clearAll();
			return;
		}
		$.ajax({
			url: "refund!searchOrder.action",
			data: $("#orderNo").serialize(),
			dataType: "json",
			async: false,
			beforeSend: function(data){
				clearAll();
			},
			success: function(data) {
				if (data.status=="error"){
					alert(data.message);
					$("#orderNo").val("");
					return;
				}
				
				$("#orderId").val(data.orderId);
				$("#memberId").val(data.memberId);
				$("#memberName").val(data.memberName);
				$("#totalAmount").val(data.totalAmount);

				$("#memberNameSpan").empty();
				$("#memberNameSpan").append(data.memberName);
				$("#totalAmoutSpan").empty();
				$("#totalAmoutSpan").append(data.totalAmount);
			}
		});
	});
});

function submitForm(form,url){
		$(form).attr("action",url);
		$(form).submit();
}

function debug_showForm(form){

	var debugDialog= $("#debugDialog");
	
	var result="";
	$(form).find("input").each(function(i) {
		result += $(this).attr("name") + "=" + $(this).attr("value") + " ";
		if (i%5==0) result += "<br>";
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

function openDialog(id,displayTitle,width,height){
	$("#"+id).dialog({
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

function closeDialog(id){
	$("#"+id).dialog("close");
}