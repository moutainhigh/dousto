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
    	
});


jQuery.fn.caculate=function () {
    var totalPayAmount = 0;
    var totalGivePoints = 0;
    
    
   $("[name='ids']").each(function(i, o) {

     //if ($(this).prop('checked') ==true) {
     	var i=$(this).val();
         saleNum =$("#saleNum_"+i).val(); 
         unitPrice =$("#unitPrice_"+i).val(); 
         unitDiscount =$("#unitDiscount_"+i).val(); 
         couponAmount =$("#couponAmount_"+i).val(); 
         
         productPoint =$("#productPoint_"+i).val(); 
         
         unitDeductedPrice = unitPrice-unitDiscount-couponAmount;
         //unitDeductedPrice = unitPrice-unitDiscount;
         payAmount = saleNum*unitDeductedPrice;
         
         $("#unitDeductedPrice_"+i).text(unitDeductedPrice.toFixed(2));
         $("#payAmount_"+i).text(payAmount.toFixed(2));
         
         
         totalPayAmount+=payAmount;
         totalGivePoints+=productPoint*1;
     //}
   });
           
    /*transportFee = $("#transportFee").val();   
    
 	//alert(totalPayAmount);
 	if(transportFee > totalPayAmount)
	{
	alert("物流费用不能大于退款总金额");
	$("#transportFee").focus();
     $("#transportFee").val(0);
	}
	else
	{
		$("#totalPayAmount").text((totalPayAmount - transportFee).toFixed(2));
	}*/  
   
    $("#totalPayAmount").text(0);
    $("#totalGivePoints").text(totalGivePoints);
    $("#orderPayPayAmount").text(totalPayAmount);
 }



    function getRefundReasonFun(index){
       $.getRefundReason(index);
    
    }
    
    
    
    function changeSaleNum(index){    
      saleNum = $("#saleNum_"+index).val(); 
      if (!(/^(\+|-)?\d+$/.test(saleNum)) || saleNum < 0){
         alert("请输入合法数字");
        $("#saleNum_"+index).focus();
         return false;
      }
      
      remainNum = $("#remainNum_"+index).val();
      if ((saleNum - remainNum) >0){
         alert("换货数量不得超过可换数量:"+remainNum);
         $("#saleNum_"+index).val(remainNum);
           $("#saleNum_"+index).focus();
         return;
      }
     
      unightweight= $("#weightUnit_"+index).val();
      weight = saleNum*unightweight;
      $("#weight_"+index).text(weight);
      
      $().caculate();
            
    }
    
    
    function changeSaleNum4ModifyPage(index){    
    	saleNum = $("#saleNum_"+index).val(); 
    	if (!(/^(\+|-)?\d+$/.test(saleNum)) || saleNum < 0){
            alert("请输入合法数字");
           $("#saleNum_"+index).focus();
            return false;
         }
         
         remainNum = $("#remainNum_"+index).val();      
         var originalNum = $("#originalNum_"+index).val();  
         remainNum = parseInt(remainNum) + parseInt(originalNum);
         if(saleNum - remainNum >0){
        	 alert("换货数量不得超过可换数量:"+remainNum);
             $("#saleNum_"+index).val(remainNum);
               $("#saleNum_"+index).focus();
             return;
         }
         
         unightweight= $("#weightUnit_"+index).val();
         weight = saleNum*unightweight;
         $("#weight_"+index).text(weight);
         
         $().caculate();
    }
    
    
   function deleteRow(obj,index){
    
    $("#deleteIds_"+index).val($("#orderRetChgItemsListId_"+index).val());
     
    $(obj).parent().parent().remove();
    
    $().caculate();
     
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

   function changeCouponAmount(index){
	      
	      saleNum = $("#saleNum_"+index).val()*1;   
	      unitPrice = $("#unitPrice_"+index).val();
	      unitDiscount = $("#unitDiscount_"+index).val();
	      couponAmount = $("#couponAmount_"+index).val();
	      
	      
	      if (!jQuery.isNumeric (couponAmount) || couponAmount<0){
	         alert("请输入合法数字");
	          $("#couponAmount_"+index).focus();
	           $("#couponAmount_"+index).val(0);
	         return;
	      }
	      
	      if (unitPrice-unitDiscount<couponAmount){
	         alert("折前单价不能小于优惠金额和购物券使用金额之和");
	          $("#couponAmount_"+index).focus();
	            $("#couponAmount_"+index).val(0);
	         return;
	      }
	         
	      $().caculate();
	    }
