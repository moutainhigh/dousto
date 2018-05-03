
<#include "/WEB-INF/template/admin/order/include_order_list_common.ftl">


//function kc(){alert($("input[type='text']")[0].value); if (event.keyCode==13) { alert("都可以！"); orderSearch();} }

function orderSearch(){
	var compare = new Date($("#orderTimeFrom").val()) > new Date($("#orderTimeTo").val());
	if(compare)	
	{
		alert("下单开始日期不能大于结束日期");
		return;
	}
	var compare1 = new Date($("#payTimeFrom").val()) > new Date($("#payTimeTo").val());
	if(compare1)	
	{
		alert("支付开始日期不能大于结束日期");
		return;
	}
	var compare2 = new Date($("#finishTimeFrom").val()) > new Date($("#finishTimeTo").val());
	if(compare2)	
	{
		alert("完成时间开始日期不能大于结束日期");
		return;
	}
	var compare3 = new Date($("#productYearStart").val()) > new Date($("#productYearEnd").val());
	if(compare3)	
	{
		alert("商品年份开始日期不能大于结束日期");
		return;
	}
	var amountUp = $("#amountUp").val();
	var amountDown = $("#amountDown").val();
	if(!checkNum(amountUp) || !checkNum(amountDown)){
		alert("请输入数字");
		return;
	}
	
	var skuNumMin = $("#skuNumMin").val();
	var skuNumMax = $("#skuNumMax").val();
	if(!checkNum(skuNumMin) || !checkNum(skuNumMax)){
		alert("请输入数字");
		return;
	}
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	$('#pageNumber').val(0);
	form.submit();
}

function checkNum(value) {
    var r = value.match(/^[0-9]*$/);
    if(r){
        return true;
    }else{
        return false;
    }
}

function downExcel(){
	if(${pager.totalCount} > 500)
	{
		alert("导出记录数不能超过500条");
		return;
	}
	
    var form1 = document.getElementById("excelForm");
   form1.action="order_excel.action";
	form1.submit();
}
function orderSuspension(){
	var count = 0;
    $("[name='ids']").each(function(){
      if(this.checked)     
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要挂起的订单");
		return;
	}
	 
	var as = []; 
	var index = 0;
	var conOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)     
	  {  
	   	if($("#statusTotalCode_"+index).val() != "0141")
		{
			conOrder += $("#orderSubNo_" + index).val() + ",";
		}
		as.push(this.value); 
	  }  
	  index++;   
	});
	if(conOrder) {
		alert(conOrder+"订单状态不能挂起");
		return;
	}
	
	var params = {
			ids:as
	};
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	
	if($(this)[0].confirm('确定挂起订单吗?'))
	{
		$.ajax({
			type:"POST",
			url:"order!updateOrderSuspension.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    form.submit(); 	
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				form.submit();
			}
		});	
	}
}



function relieveOrderSuspension(){
	var count = 0;
    $("[name='ids']").each(function(){
      if(this.checked)     
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要解挂的订单");
		return;
	}
	 
	var as = []; 
	var index = 0;
	var conOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)     
	  {  
	   	if($("#statusTotalCode_"+index).val() != "0143")
		{
			conOrder += $("#orderSubNo_" + index).val() + ",";
		}
		as.push(this.value); 
	  }  
	  index++;   
	});
	if(conOrder) {
		alert(conOrder+"订单状态不能解挂");
		return;
	}
	
	var params = {
			ids:as
	};
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	if($(this)[0].confirm('确定解挂订单吗?'))
	{
		$.ajax({
			type:"POST",
			url:"order!updateOrderRelieveSuspensionon.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    form.submit(); 	
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				form.submit();
			}
		});	
	}
}




function orderConfirm(){
	var count = 0;
    $("[name='ids']").each(function(){
      if(this.checked)     
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要审核的订单");
		return;
	}
	
	var payNameTmp = $("#payNameTmp").val();
	var payAmountTmp = $("#payAmountTmp").val();
	var totalPayAmountTmp = $("#totalPayAmountTmp").val();
	if((payNameTmp == '购物券支付' || payNameTmp == '优惠券') && payAmountTmp == '0' && totalPayAmountTmp >= 100)
	{
	 	alert("亲，此单购物券异常，请暂时不要审核！");
	 	return;
	}
	
	var as = []; 
	var index = 0;
	var conOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)     
	  {  
	   	if($("#statusTotalCode_"+index).val() != "0141")
		{
			conOrder += $("#orderSubNo_" + index).val() + ",";
		}
		as.push(this.value); 
	  }  
	  index++;   
	});
	if(conOrder) {
		alert(conOrder+"订单状态不能审核");
		return;
	}
	var params = {
			ids:as
	};
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	if($(this)[0].confirm('确定审核订单吗?'))
	{
		//form.submit();
		$.ajax({
			type:"POST",
			url:"order!orderManualAudit.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    //window.location.href="refund.action?column=-1";
				    form.submit(); 	
				}else{
				   alert(data.message);
				   //window.location.href="refund.action?column=-1";
				   form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				//window.location.href="refund.action?column=-1"; 
				form.submit();
			}
		});	
	}
	/*if($(this)[0].confirm('确定审核订单吗?'))
	{
		form.submit();
	}*/
}
function showOperateDiv(operateDiv){
	var count = 0;
	$("[name='ids']").each(function(){
		if(this.checked)     
		{     
			count=count+1;
		}     
	});
	if(count<=0){
		alert('请选择订单');
		return;
	}
	if(isWaitAudit()){	
		ShowDIV(operateDiv);
	}
}

function refreshSku(splitType) {
	 if(splitType == "sku"){
	 	$("#sku_tr").hide();
	 }
	 if(splitType == "assignSku") {
	 	$("#sku_tr").show();
	 	$("#splitSku").val("");
	 }
	 $("#splitType").val(splitType);
}

function orderUpdateDelivery(){
	var newDeliveryNo = $("#newDeliveryNo").val();
	var newDeliveryName = $("#newDeliveryNo").text();
	if(!newDeliveryNo){
		alert('请选择物流公司');
		return;
	}
	
	var as = []; 
	var index = 0;
	$("[name='ids']").each(function(){
      if(this.checked)     
	  {  
		as.push(this.value); 
	  }  
	  index++;   
	});
	
	var params = {
			ids:as,
			deliveryMerchantNo:newDeliveryNo,
			deliveryMerchantName:newDeliveryName
	};
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	if($(this)[0].confirm('确定修改订单物流公司吗?')){
		$.ajax({
			type:"POST",
			url:"order!updateOrderDelivery.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    form.submit(); 	
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				form.submit();
			}
		});	
	}
}
function updateClientServiceRemark(){
	var clientServiceRemark = $("#clientServiceRemark").val();
	if(!clientServiceRemark){
		alert('请填写备注信息');
		return;
	}
	var clientServiceRemarkFlag = $("[name='clientServiceRemarkFlag']:checked").val();
	
	var as = []; 
	var index = 0;
	$("[name='ids']").each(function(){
      if(this.checked)     
	  {  
		as.push(this.value); 
	  }  
	  index++;   
	});
	var params = {
			ids:as,
			clientServiceRemark:clientServiceRemark,
			clientServiceRemarkFlag:clientServiceRemarkFlag
	};
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	if($(this)[0].confirm('确定添加备注吗?')){
		$.ajax({
			type:"POST",
			url:"order!batchUpdateClientServiceRemark.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
					alert(data.message); 
					form.submit(); 	
				}else{
					alert(data.message);
					form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				form.submit();
			}
		});	
	}
}
function splitOrder(){
	var splitType = $("#splitType").val();
	var sku = $("#splitSku").val();
	if(splitType == "assignSku" && !sku) {
		alert("sku不可为空");
		return;
	}
	var as = []; 
	var index = 0;
	$("[name='ids']").each(function(){
      if(this.checked)     
	  {  
		as.push(this.value); 
	  }  
	  index++;   
	});
	var params = {
			ids:as,
			splitType:splitType,
			sku:sku
	};
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	if($(this)[0].confirm('确定拆分订单吗?')){
		$.ajax({
			type:"POST",
			url:"order!splitOrder.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
					alert(data.message); 
					form.submit(); 	
				}else{
					alert(data.message);
					form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				form.submit();
			}
		});	
	}
}

function updateOrderWareHouse(){
	var newWareHouseNo = $("#newWareHouseNo").val();
	if(!newWareHouseNo){
		alert('请选择仓库');
		return;
	}
	
	var as = []; 
	var index = 0;
	var conOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)     
	  {  
		as.push(this.value); 
	  }  
	  index++;   
	});
	var params = {
			ids:as,
			wareHouseNo:newWareHouseNo
	};
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	if($(this)[0].confirm('确定修改发货仓库吗?')){
		$.ajax({
			type:"POST",
			url:"order!batchUpdateWareHouseNo.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    form.submit(); 	
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				form.submit();
			}
		});	
	}
}

 function ShowDIV(thisObjID) {
     $("#BgDiv").css({ display: "block", height: $(document).height() });
     var yscroll = document.documentElement.scrollTop;
     $("#" + thisObjID ).css("top", "100px");
     $("#" + thisObjID ).css("display", "block");
  	document.documentElement.scrollTop = 0;
}
 
function closeDiv(thisObjID) {
     $("#BgDiv").css("display", "none");
     $("#" + thisObjID).css("display", "none");
}
function orderCancel(){
	var count = 0;
    $("[name='ids']").each(function(){
    	if(this.checked){     
    		count=count+1;
    	}     
	});
	if(count<=0)
	{
		alert("请选择需要取消的订单");
		return;
	}
	
	var cancelReasonNo = $("#cancelReasonNo").val();
	if(cancelReasonNo == '')
	{
		alert("请选择取消原因！");
		return;
	}
	
	var as = []; 
	var canceled = false;
	var index = 0;
	var canOrder = "";
	$("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {    
	   	if($("#statusTotalCode_"+index).val() == "0153")
		{
			canOrder += $("#orderSubNo_" + index).val() + ",";
			canceled = true;
		}

		as.push($(this).val()); 
	  }  
	  index++;   
	});
	if(canceled)
	{
		alert("订单" + canOrder.substring(0,canOrder.length-1) + "已取消!");
		return;
	}
	var form = document.getElementById("listForm");
	form.action="order!search.action";
	var params = {
			ids:as,
			cancelReasonNo:cancelReasonNo
	};	
	
	if($(this)[0].confirm('确定取消订单吗?'))
	{
		$.ajax({
			type:"POST",
			url:"order!batchCancelOrder.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    form.submit(); 
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
			
				alert(params)
				alert("error!") 
				form.submit(); 
			}
		});	
	}
	}
	
	function isWaitAudit(){
		var index = 0;
		var conOrder = "";
		var as=[]
		$("[name='ids']").each(function(){
	      if(this.checked)     
		  {  
		  	var statusTotalCode_ = $("#statusTotalCode_"+index).val();
		   	if(statusTotalCode_ != "0141" && statusTotalCode_ != "0140")
			{
				conOrder += $("#orderSubNo_" + index).val() + ",";
			}
			as.push(this.value); 
		  }  
		  index++;   
		});
		if(conOrder) {
			alert(conOrder+"订单状态不是待审核,无法执行操作");
			return false;
		}
		
		return true;
	}

