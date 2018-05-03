<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>创建换货订单</title>
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
<script type="text/javascript" src="${base}/resources/admin/js/order.create.form.check.js"></script>
<@sj.head compressed="false" scriptPath="${webuiPath}/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>


<script type="text/javascript">
$().ready( function() {
	$().caculate();

    $( "#allCheck" ).click( function() {
		if ($("#allCheck").prop("checked")) {
			   $("[name='ids']").prop("checked", true);//
		} else {
			    $("[name='ids']").prop("checked", false);
		}
		
		$().caculate();
	});
	
	 $( "#change" ).click( function() {
	    
	    if($("[name='ids']").size() <= 0)
	 	{
	 		alert("无退货明细，无法换货");
		   	return;
	 	}
	    
	    
	    /*var sel=false;
	    $("[name='ids']").each(function(){
          if($(this).attr("checked"))     
		  {     
		   sel=true;
		  }     
		});
		
		if(!sel){
		   alert("请选择换货明细");
		   return;
		}*/ 
	    
	    // defind in order.create.form.check.js 检验订单信息
	    if(!checkDetail()){
	    	return;
	    }
	    
	    // defined in order.create.form.check.js
	    var isSelectSelfPoint = setHiddenIfNeedRefundValue();
	    if(!isSelectSelfPoint)
	    {
	    	return;
	    }
	    

	   $("form").submit();
	 
	 });

  
     
     //一上来就全选择
       /*$("#allCheck").prop("checked",true);
       $("[name='ids']").prop("checked", true);
       $().caculate();*/
    
   
});

    function caculateCheckBox(){
      $().caculate();
    }
    
    
    // 使用change.order.view.js中的caculate
    /*jQuery.fn.caculate=function () {
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
            
            //totalPayAmount+=payAmount;
            totalGivePoints+=productPoint*1;
        }
      });
              
       $("#totalPayAmount").text(totalPayAmount);
       $("#totalGivePoints").text(totalGivePoints);
       $("#orderPayPayAmount").text(totalPayAmount.toFixed(2));
    }*/
    

</script>
<script src="${base}/resources/admin/js/change.order.view.js"></script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>创建换货订单</h1>
		</div>
		<form id="inputForm" class="validate" action="change!addChange.action" method="post">
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
			
  
		    <#--include "/WEB-INF/template/admin/order/sale_after_order/change_sub_order.ftl"-->
		    <#include "/WEB-INF/template/admin/order/sale_after_order/new_sub_order.ftl">
							
			<div class="buttonArea">
				<input type="button" class="formButton" value="确认换货" hidefocus="true"  id="change" <#if (orderSub.totalRemainNum <= 0)>disabled style="color:gray;"</#if>/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.close();" value="关  闭" hidefocus="true"/>
			</div>
			
			
		</form>
	</div>
</body>
</html>