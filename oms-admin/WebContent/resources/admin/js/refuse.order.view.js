$().ready( function() {

	$( "#tabs" ).tabs();
		
	$( "#accordion" ).accordion({
		collapsible: true
	});
	

	

    
	$.getRefundReason=function(indes){
	  
	    var preRefundReason = $("#preRefundReason_"+indes).val();
	    $("#refundReason_"+indes).empty();
        $.getJSON("refund_reason!getRefundReason.action?preRefundReason="+preRefundReason, function(data){ 
             var list = data.refundReasonList;
             $.each(list, function(index, item)
             {
                $("#refundReason_"+indes).append("<option value= ' " + item.reasonNo + " '> " + item.reasonName + "</option>");
             });    
        });
    }
    
    
    $().caculate();
    
});


    function getRefundReasonFun(index){
       $.getRefundReason(index);
    
    }
    
    
    function deletePayRow(obj, index, orderPayId){
        if (orderPayId != null){
            $("#deleteOrderPayIds_"+index).val(orderPayId);
        }
        $(obj).parent().parent().remove();
      }
      
      
    function changePayAmount(obj){
      	
      if(!jQuery.isNumeric ($(obj).val()) || $(obj).val()<0){
              alert("请输入合法数字");
              $(obj).val(0);
              $(obj).focus();
              return;
      }	
      	  
      var totalPayAmount = $("#totalPayAmount").text();
   
  	  var orderPayAmount = 0; 
  	  $("#retInfoTab").find("tr").find("td:eq(1)").each(function(){
  		   orderPayAmount += ($(this).find("input[type='text']").val())*1;
  	  });
  	  
  	  if(orderPayAmount>totalPayAmount){
  	      alert("不能超过退款总额");
  	      $(obj).val(0);
            $(obj).focus();
  	  }
     }
    
    function changePayAmount4Return(obj){
    	
    	if (!jQuery.isNumeric ($(obj).val()) || $(obj).val()<0){
            alert("请输入合法数字");
            $(obj).val(0);
            $(obj).focus();
            return;
         }	
      
    	var orderPayAmount = 0; 
		$("#retInfoTab").find("tr").find("td:eq(1)").each(function(){
			   orderPayAmount += ($(this).find("input[type='text']").val())*1;
		});
		$("#totalPayAmount").text(orderPayAmount);
		$("#totalPayAmountTmp").val(orderPayAmount);
		$("#totalPayAmountRefTsf").val(orderPayAmount);
    }
    
    
    jQuery.fn.caculate=function () {
        var totalPayAmount = 0;
        var totalGivePoints = 0;
        
        
        /*$("[name='orderitemlist']").each(function(i, o){
             saleNum =$(this).find("#saleNum_"+i).val(); 
             unitPrice =$(this).find("#unitPrice_"+i).val(); 
             unitDiscount =$(this).find("#unitDiscount_"+i).val(); 
             couponAmount =$(this).find("#couponAmount_"+i).val(); 
             
             productPoint =$(this).find("#productPoint_"+i).val(); 
             
             //unitDeductedPrice = unitPrice-unitDiscount-couponAmount;
             //payAmount = saleNum*unitDeductedPrice;
             unitDeductedPrice = unitPrice-unitDiscount-couponAmount;
             payAmount = saleNum*unitDeductedPrice;
             
             $(this).find("#unitDeductedPrice_"+i).text(unitDeductedPrice.toFixed(2));
             $(this).find("#payAmount_"+i).text(payAmount.toFixed(2));
             
             totalPayAmount+=payAmount;
             totalGivePoints+=productPoint*1;
         });  */
        
        $("[name='ids']").each(function(i, o){
        	var i=$(this).val();
        	saleNum =$("#saleNum_"+i).val(); 
            unitPrice =$("#unitPrice_"+i).val(); 
            unitDiscount =$("#unitDiscount_"+i).val(); 
            couponAmount =$("#couponAmount_"+i).val(); 
            
            productPoint =$("#productPoint_"+i).val();
        	
        	//unitDeductedPrice = unitPrice-unitDiscount-couponAmount;
            //payAmount = saleNum*unitDeductedPrice;
            unitDeductedPrice = unitPrice-unitDiscount-couponAmount;
            payAmount = saleNum*unitDeductedPrice;
            
            $("#unitDeductedPrice_"+i).text(unitDeductedPrice.toFixed(2));
            $("#payAmount_"+i).text(payAmount.toFixed(2));
            
            totalPayAmount+=payAmount;
            totalGivePoints+=productPoint*1;
        });
        
        transportFee = $("#transportFee").val();   
        
        if($("#statusStatusTotal").val() == '0270' || $("#statusStatusTotal").val() == '0280')
        {
        	totalPayAmount = $("#totalPayAmountRefTsf").val();
        }
        
    	//alert(totalPayAmount);
    	/*if(transportFee > totalPayAmount)
		{
    		alert("物流费用不能大于退款总金额");
    		$("#transportFee").focus();
            $("#transportFee").val(0);
		}
		else
		{*/
       		$("#totalPayAmount").text((totalPayAmount - transportFee).toFixed(2));
       		// 不需要退款处理
        	noNeedRefund();
       		$("#totalPayAmountTmp").val($("#totalPayAmount").text());
       		var totalPayAmountText = $("#totalPayAmount").text();
       		
	       	 if($("#statusStatusTotal").val() == '0270' || $("#statusStatusTotal").val() == '0280'){
	         	$("#orderPayPayAmount_0").val((totalPayAmount - transportFee).toFixed(2)); 
	         }
	       	 else{
	       		$("#orderPayPayAmount_0").val(totalPayAmountText); 
	       	 }
       		
		//}    
    	
        //$("#totalPayAmount").text(totalPayAmount);
        $("#totalGivePoints").text(totalGivePoints);
     }
    
    
    // 物流费用
    function changeTransportFee(){
    	transportFee = $("#transportFee").val();
    	if (!jQuery.isNumeric (transportFee)){
            alert("请输入合法数字");
             $("#transportFee").focus();
              $("#transportFee").val(0);
            return;
         }
           
        $().caculate();
      }
    
    // 不需要退款处理
    function noNeedRefund(){
    	if($("#ifNeedRefund").val() == '0'){
    		$("#totalPayAmount").text("0");
    	}
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