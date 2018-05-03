function orderAdvancedSearch(){
	$("#advancedSearchFrame").dialog({
		width: 580,
		height: 350,
		modal: true,
		buttons: {
			"确定": function(){
			$("[id^='hid_']").each(function(){$(this).val("");});
			 assemblyProSymbolValue();

				var consigneeMobile_v=$("#consigneeMobile").val();
				
				var startOrderPaymentDate_v2 = $("#startOrderPaymentDate").val();	
				var endOrderPaymentDate_v2 = $("#endOrderPaymentDate").val();
				var startDownOrderDate_v2 = $("#startDownOrderDate").val();	
				var endDownOrderDate_v2 = $("#endDownOrderDate").val();
				var startTotalPaid_v2=$("#startTotalPaid").val();
				var endTotalPaid_v2=$("#endTotalPaid").val();
				if(consigneeMobile_v!=""){
					 var patrn = /^[1][0-9]{10}$/;
					    if (!patrn.exec(consigneeMobile_v)) {
					    	alert("请输入有效手机号！");
					        return false;
					    }
				}
				if((startOrderPaymentDate_v2=="" && endOrderPaymentDate_v2!="") || (startOrderPaymentDate_v2!="" && endOrderPaymentDate_v2=="")){
					alert("支付日期请填写完整");
					return false;
				}if((startDownOrderDate_v2=="" && endDownOrderDate_v2!="") || (startDownOrderDate_v2!="" && endDownOrderDate_v2=="")){
					alert("下单日期请填写完整");
					return false;
				}
				
				if(startOrderPaymentDate_v2!="" && endOrderPaymentDate_v2!=""){
					var result = compareDate(startOrderPaymentDate_v2,endOrderPaymentDate_v2);  
					if(result>0 ){
						alert("支付日期选择的范围有误");
						return false;
					}
				}if(startDownOrderDate_v2!="" && endDownOrderDate_v2!=""){
					var result = compareDate(startDownOrderDate_v2,endDownOrderDate_v2); 
					if( result>0 ){
						alert("下单日期选择的范围有误");
						return false;
					}
				}
				if((startTotalPaid_v2=="" && endTotalPaid_v2!="" )||(startTotalPaid_v2!="" && endTotalPaid_v2=="")){
					alert("已付总金额请填写完整");
					return false;
				}if(startTotalPaid_v2!="" && endTotalPaid_v2!=""){
					var int_v = "^\d|([0-9]+(.[0-9]{2})?)$";
					var   re   =   new   RegExp(int_v); 
					if(endTotalPaid_v2.match(re)==null || startTotalPaid_v2.match(re)==null){
						alert("已付总金额请填写数字！");
						return false;
					}else{
						if(parseFloat(endTotalPaid_v2)<parseFloat(startTotalPaid_v2)){
							alert("已付总金额范围填写有误！");
							return false;
						}
					}
				}
				var memberEmail_v2=$("#memberEmail").val();
				var myreg =  /^(?:[a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/; 
				if(memberEmail_v2!="" && myreg.test(memberEmail_v2)==false){
					alert('请输入有效的E_mail！'); 
				    return false;
				}
			$("#pageNumber").val("1");
			$("#isadvancedList").val("true");
			$(".list form").submit();
			$(this).dialog("close");
			},
			"取消": function(){
				$(this).dialog("close");
			}
		}
	});
}
function compareDate(strDate1,strDate2)
{ 
	var date1 = new Date(strDate1.replace(/\-/g, "\/"));
	var date2 = new Date(strDate2.replace(/\-/g, "\/"));
	return date1-date2;
}
function assemblyProSymbolValue(){
	var orderNo_v = $("#orderNo").val();
	if(orderNo_v!=""){
		$("#hid_orderNo").val(orderNo_v);
	}
	
	var accountName_v = $("#accountName").val();
	if(accountName_v!=""){
		$("#hid_accountName").val(accountName_v);	
	}
	
	var consignee_v = $("#consignee").val();
	if(consignee_v!=""){
		$("#hid_consignee").val(consignee_v);
	}
	
	var stateSelect_v = $("#stateSelect option:selected").val();
	var citySelect_v = $("#citySelect option:selected").val();
	var countySelect_v = $("#countySelect option:selected").val();	
	
	if(stateSelect_v!=""){
		
		$("#hid_state_city_county").val(stateSelect_v+"+"+citySelect_v+"+"+countySelect_v);
	}
	
	var consigneeMobile_v = $("#consigneeMobile").val();	
	if(consigneeMobile_v!=""){
		$("#hid_consigneeMobile").val(consigneeMobile_v);
	}
	
	var startDownOrderDate_v = $("#startDownOrderDate").val();	
	var endDownOrderDate_v = $("#endDownOrderDate").val();
	if(startDownOrderDate_v!="" && endDownOrderDate_v!=""){
		$("#hid_downOrderDate").val(startDownOrderDate_v+"+"+endDownOrderDate_v);
	}
	
	var startOrderPaymentDate_v = $("#startOrderPaymentDate").val();	
	var endOrderPaymentDate_v = $("#endOrderPaymentDate").val();
	if(startOrderPaymentDate_v!="" && endOrderPaymentDate_v!=""){
		$("#hid_OrderPaymentDate").val(startOrderPaymentDate_v+"+"+endOrderPaymentDate_v);
	}
	var memberEmail_v=$("#memberEmail").val();
	if(memberEmail_v!=""){
		$("#hid_memberEmail").val(memberEmail_v);
		
	}
	
	var status_v=$("#status option:selected").val();
	if(status_v!=""){
		$("#hid_status").val(status_v);
		var taboptions=$('#localtabs').data('taboptions');
		for(var i=0; i<taboptions.length; i++){
			  var optionId = taboptions[i].id;
			  if(optionId == status_v){
				  $("#selected").val(i);
				  break;
			  }
		}
	}
	
	var paymentMode_v=$("#paymentMode option:selected").val();
	if(paymentMode_v!=""){
		$("#hid_paymentMode").val(paymentMode_v);
		
	}
	var startTotalPaid_v=$("#startTotalPaid").val();
	var endTotalPaid_v=$("#endTotalPaid").val();
	if(startTotalPaid_v !="" && endTotalPaid_v!=""){
	    if(!/^[0-9]*$/.test(startTotalPaid_v) || !/^[0-9]*$/.test(endTotalPaid_v)){
	    	$("#hid_totalPaid").val("0|0");
	    }else{
			$("#hid_totalPaid").val(startTotalPaid_v+"|"+endTotalPaid_v);
	    }
	}
	
	 
}