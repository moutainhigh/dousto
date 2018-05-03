<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>创建退货订单</title>
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
	$().caculate();

	//var count = ${orderItemCount};
    $( "#allCheck" ).click( function() {
		if ($("#allCheck").prop("checked")) {
			   $("[name='ids']").prop("checked", true);//
		} else {
			    $("[name='ids']").prop("checked", false);
		}
		
		$().caculate();
	});
	
	 $( "#return" ).click( function() {
	 	if($("[name='ids']").size() <= 0)
	 	{
	 		alert("无退货明细，无法退货");
		   	return;
	 	}
	 	
	 	/*
	    var sel=false;
	    $("[name='ids']").each(function(){
          if($(this).attr("checked"))     
		  {     
		   sel=true;
		  }     
		});
		
		if(!sel){
		   alert("请选择退货明细");
		   //return;
		} 
	    */
	    
	    // defind in order.create.form.check.js 检验订单信息
	    if(!checkDetail()){
	    	return;
	    }
	    
	    
	    // defind in order.create.form.check.js  校验退款信息
	    //if(!checkPayAmountSum())
	    //{
	   // 	return;
	   // }
	    
	    // defined in order.create.form.check.js
	    var isSelectSelfPoint = setHiddenIfNeedRefundValue();
	    if(!isSelectSelfPoint)
	    {
	    	return;
	    }
	    
	   // 校验积分  defind in return.order.view.js
	    checkIntegral(); 
	   $("form").submit();
	 
	 });
	 
	 
	 
    

  		/*
			方法：全选
		*/
       function selectAll()
       {
	       $("#allCheck").prop("checked",true);
	       $("[name='ids']").prop("checked", true);
	       $().caculate();
       	
       }
       
     //一上来就全选择
     //selectAll();
       
 
});

    function caculateCheckBox(){
      $().caculate();
    }
    
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
            payAmount = saleNum*unitDeductedPrice;
            
            $("#unitDeductedPrice_"+i).text(unitDeductedPrice.toFixed(2));
            $("#payAmount_"+i).text(payAmount.toFixed(2));
            
            
            totalPayAmount+=payAmount;
            totalGivePoints+=productPoint*1;
        //}
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
       		$("#totalPayAmountTmp").val($("#totalPayAmount").text());
       		var totalPayAmountText = $("#totalPayAmount").text();
       		$("#orderPayPayAmount_0").val(totalPayAmountText);
		}    
			
       $("#totalGivePoints").text(totalGivePoints);
       $("#totalGivePointsTmp").val(totalGivePoints);
       $("#orderPayPayAmount").text(totalPayAmount);
    }
    
<#include "/WEB-INF/template/admin/order/sale_after_order/addOrderPayRow.ftl">

</script>
<script src="${base}/resources/admin/js/return.order.view.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/order.create.form.check.js"></script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>创建退货单</h1>
		</div>
		<form id="inputForm" class="validate" action="return!addReturn.action" method="post">
		    <input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="order.id" value="${order.id}" />
			<input type="hidden" name="order.orderNo" value="${order.orderNo}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="orderCategory" value="${orderCategory}" />
			<div class="blank"></div>
			
			<div id="accordion">
			  <h2>退货订单信息</h2>
			 <#include "/WEB-INF/template/admin/order/sale_after_order/new_detail_information_page.ftl">				
			</div>
  					    
		    <#include "/WEB-INF/template/admin/order/sale_after_order/new_sub_order.ftl">
							
			<div class="buttonArea">				
				<input type="button" class="formButton"  value="确认退货" hidefocus="true"  id="return" <#if (orderSub.totalRemainNum <= 0)>disabled style="color:gray;"</#if> />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.close();" value="关  闭" hidefocus="true"/>
			</div>						
		</form>
	</div>
</body>
</html>