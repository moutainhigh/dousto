<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>逆向订单详情</title>
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
	 $( "#return" ).click( function() {
	 	var orderCategory = "${order.orderCategory}";
	 	if(orderCategory == "ret" || orderCategory == "ref" || orderCategory == "tsf")
	 	{
	 		$("#inputForm").action = "return!view.action?orderNo=${order.orderNo}";
	 	}
	 	else if(orderCategory == "chg")
	 	{
	 		$("#inputForm").attr("action", "change!view.action?orderNo=${order.orderNo}"); 
	 	}
	 	else
	 	{
	 		$("#inputForm").attr("action", "refuse!view.action?orderNo=${order.orderNo}");
	 	}
	    $("#inputForm").submit();
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
            payAmount = saleNum*unitDeductedPrice;
            
            $(this).find("#unitDeductedPrice_"+i).text(unitDeductedPrice);
            $(this).find("#payAmount_"+i).text(payAmount);
            
            totalPayAmount+=payAmount;
            totalGivePoints+=productPoint*1;
        });  
              
       $("#totalPayAmount").text(totalPayAmount);
       // 不需要退款处理
       noNeedRefund();
       $("#totalGivePoints").text(totalGivePoints);
    }
    


</script>
<script src="${base}/resources/admin/js/return.order.view.js"></script>
<script type="text/javascript" src="${base}/resources/admin/js/order.create.form.check.js"></script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>订单详情</h1>
		</div>
		<!--
		<form id="inputForm" class="validate" action="return!updateReturn.action" method="post">
		-->
		<form id="inputForm" class="validate" action="return!view.action?orderNo=${order.orderNo}" method="post">
		    <input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="order.id" value="${order.id}" />
			<input type="hidden" name="order.orderNo" value="${order.orderNo}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="orderCategory" value="${orderCategory}" />
			<div class="blank"></div>
			
			<div id="accordion">
			  <h2>${orderCategoryNameMap.get(order.orderCategory)}订单信息</h2>
			  <#include "/WEB-INF/template/admin/order/sale_after_order/new_detail_information_page.ftl">	
			</div>
		      <#--include "/WEB-INF/template/admin/order/sale_after_order/return_sub_order_view.ftl"-->
		      <#include "/WEB-INF/template/admin/order/sale_after_order/new_sub_order.ftl">
			<div class="buttonArea"> 
				<input type="button" <#if order.statusTotal == '0250'>disabled style="color:gray;"</#if> class="formButton" value="修改" hidefocus="true"  id="return"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.close();" value="关  闭" hidefocus="true"/>
			</div>
			
			
		</form>
	</div>
</body>
</html>