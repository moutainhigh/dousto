<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>修改换货单</title>
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

<script type="text/javascript">
$().ready( function() {

	 $( "#change" ).click( function() {
	 
	 	// defined in order.create.form.check.js
	    var isSelectSelfPoint = setHiddenIfNeedRefundValue();
	    if(!isSelectSelfPoint)
	    {
	    	return;
	    }
	    $("form").submit();
	 });
	
     $().caculate();

	
});

    jQuery.fn.caculate=function () {
       var totalPayAmount = 0;
       var totalGivePoints = 0;
       
       
       $("[name='orderitemlist']").each(function(i, o){
            saleNum =$(this).find("#saleNum_"+i).val(); 
            unitPrice =$(this).find("#unitPrice_"+i).val(); 
            unitDiscount =$(this).find("#unitDiscount_"+i).val(); 
            couponAmount =$(this).find("#couponAmount_"+i).val(); 
            
            productPoint =$(this).find("#productPoint_"+i).val(); 
            
            unitDeductedPrice = unitPrice-unitDiscount-couponAmount;
            //unitDeductedPrice = unitPrice-unitDiscount;
            payAmount = saleNum*unitDeductedPrice;
            
            $(this).find("#unitDeductedPrice_"+i).text(unitDeductedPrice);
            $(this).find("#payAmount_"+i).text(payAmount);
            
            totalPayAmount+=payAmount;
            totalGivePoints+=productPoint*1;
        });  
              
       $("#totalPayAmount").text(totalPayAmount);
       $("#totalGivePoints").text(totalGivePoints);
    }
    


</script>
<script src="${base}/resources/admin/js/change.order.view.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/order.create.form.check.js"></script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>更新换货订单</h1>
		</div>
		<form id="inputForm" class="validate" action="change!updateChange.action" method="post">
		    <input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="order.id" value="${order.id}" />
			<input type="hidden" name="order.orderNo" value="${order.orderNo}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="orderCategory" value="${orderCategory}" />
			<div class="blank"></div>
			
			<div id="accordion">
			  <h2>换货订单信息</h2>
			  <#include "/WEB-INF/template/admin/order/sale_after_order/new_detail_information_page.ftl">	
			</div>
			
  
		    <#--include "/WEB-INF/template/admin/order/sale_after_order/change_sub_order_view.ftl"-->
		    <#include "/WEB-INF/template/admin/order/sale_after_order/new_sub_order.ftl">
							
			<div class="buttonArea">
				<input type="button" class="formButton" value="保存" hidefocus="true"  id="change"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true"/>
			</div>
			
			
		</form>
	</div>
</body>
</html>