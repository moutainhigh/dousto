<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>创建退货订单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />


<link href="${base}/resources/common/css/base_order.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />




<link rel="stylesheet" href="${base}/resources/admin/css/jquery.ui.all.css">
<script src="${base}/resources/admin/js/jquery-1.10.2.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.core.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.widget.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.accordion.js"></script>
<link rel="stylesheet" href="${base}/resources/admin/css/demos.css">
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<@sj.head compressed="false" scriptPath="${webuiPath}/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>

<#assign orderItemCount=0>
<#if orderSub.orderItems?exists>
	<#assign orderItemCount=orderSub.orderItems.size()>
</#if>
<script type="text/javascript">
$().ready( function() {
	var count = ${orderItemCount};
    $( "#allCheck" ).click( function() {
		if ($("#allCheck").prop("checked")) {
			   $("[name='ids']").prop("checked", true);//
		} else {
			    $("[name='ids']").prop("checked", false);
		}
		
		$().caculate();
	});
	
	 $( "#return" ).click( function() {
	 
	   $("form").submit();
	 
	 });

  
    
     index = ${(orderPayLists.size())}; 
     $("#addOrderPay").click( function() {
    
        var targetTab = document.getElementById("retInfoTab");
        var newRow = targetTab.rows.length;
        
		var row = targetTab.insertRow(newRow);  
		
		var cell0 = row.insertCell(0); 
		cell0.innerHTML=" <select name='orderPayLists[" + index + "].payCode'><#list payTypeList as type><option value='${type.id}'>${type.desc}</option></#list></select> ";
		
		var cell1 = row.insertCell(1);  
		cell1.innerHTML="  <input type='text' name='orderPayLists[" + index + "].payAmount' id='payAmount_" + index + "'  onblur='javascript:changePayAmount(this)' class='formText {required: true, min: 0}' value='0' /> ";
		

		var cell2 = row.insertCell(2); 
		cell2.innerHTML="<input type='button' class='formButton' value='删除' hidefocus='true'   onclick='javascript:deletePayRow(this,null, null)'/>";
		
		index++;
    
     });
     
     //一上来就全选择
       $("#allCheck").prop("checked",true);
       $("[name='ids']").prop("checked", true);
       $().caculate();
    
   
});

    function caculateCheckBox(){
      $().caculate();
    }
    
    jQuery.fn.caculate=function () {
       var totalPayAmount = 0;
       var totalGivePoints = 0;
       
       
      $("[name='ids']").each(function(i, o) {

        if ($(this).prop('checked') ==true) {
            
            saleNum =$("#saleNum_"+i).val(); 
            unitPrice =$("#unitPrice_"+i).val(); 
            unitDiscount =$("#unitDiscount_"+i).val(); 
            couponAmount =$("#couponAmount_"+i).val(); 
            
            productPoint =$("#productPoint_"+i).val(); 
            
            unitDeductedPrice = unitPrice-unitDiscount-couponAmount;
            payAmount = saleNum*unitDeductedPrice;
            
            $("#unitDeductedPrice_"+i).text(unitDeductedPrice.toFixed(2));
            $("#payAmount_"+i).text(payAmount.toFixed(2));
            
            
            totalPayAmount+=payAmount;
            totalGivePoints+=productPoint*1;
        }
      });
              
       transportFee = $("#transportFee").val();   
       
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
		}    
       $("#totalGivePoints").text(totalGivePoints);
       $("#orderPayPayAmount").text(totalPayAmount);
    }
    

</script>
<script src="${base}/resources/admin/js/return.order.view.js"></script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>退货单审批</h1>
		</div>
		<form id="inputForm" class="validate" action="drawback!returnPayment.action" method="post">
		    <input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="order.id" value="${order.id}" />
			<input type="hidden" name="order.orderNo" value="${order.orderNo}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="orderCategory" value="${orderCategory}" />
			<div class="blank"></div>
			
			<div id="accordion">
			  <h2>退款订单信息</h2>
			 <#include "/WEB-INF/template/admin/order/sale_after_order/detail_information_page.ftl">
				
			</div>
			
  
		    <#include "/WEB-INF/template/admin/order/sale_after_order/return_sub_payment.ftl">
	
			<div class="buttonArea">
			 <#if order.statusTotal!='0280'>
				<input type="button" class="formButton"  value="确认退款" hidefocus="true"  id="return"/>&nbsp;&nbsp;&nbsp;&nbsp;
			</#if>	
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true"/>
			</div>
			
			
		</form>
	</div>
</body>
</html>