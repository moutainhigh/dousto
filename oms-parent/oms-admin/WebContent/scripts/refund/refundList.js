/**
 * 门店收获(逆向订单)
*/
function orderReceive(){
	var count = 0;
	$("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要收货的订单");
		return;
	}
	var as = []; 
	var notStoreFlag = false; //非门店代退标识
	var isOk = false; // 是否满足执行条件标识
	var index = 0;
	var conOrder = "";
	var notConOrder = "";
	$("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {   
    	notStoreFlag = false;  
    	isOk = false; 
	   	if($("#distributeType_"+index).val() != "3"){
	   		alert("逆向订单：" + $(this).val() + " 入库方式不是门店代退，门店不能收货!");
	   		notStoreFlag = true;
			return;
	   	}
   		if($("#statusTotalCode_"+index).val() == "0233")
		{
			conOrder += $(this).val() + ",";
			as.push($(this).val()); 
			isOk = true; 
		}else{
			alert("逆向订单：" + $(this).val() + " 当前状态不符合条件，门店不能收货!");
			notStoreFlag = true;
			return;
		}
	  }   
	  index++;  
	});
	// 如果非门店代退
	if(notStoreFlag){
		return;
	}
	// 如果不满足执行条件
	if(!isOk){
		return;
	}
	
	var params = {
			ids:as
	};	
	var form = document.getElementById("listForm");
	form.action="refund!search.action";
	if($(this)[0].confirm('确定已收到订单吗?'))
	{
		$.ajax({
			type:"POST",
			url:"refund!orderStoreReceive.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert("操作成功！"); 
				    form.submit(); 	
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
				alert("操作失败，请联系管理员!")
				form.submit();
			}
		});	
	}
}

/**
 * 门店退款
*/
function orderRefund(){
	var count = 0;
	$("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要退款的订单");
		return;
	}
	var as = []; 
	var notStoreFlag = false; //非门店退款标识
	var isOk = false; // 是否满足执行条件标识
	var index = 0;
	var conOrder = "";
	var notConOrder = "";
	$("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {   
    	notStoreFlag = false;  
    	isOk = false;
	   	if($("#refundType_"+index).val() != "2"){
	   		alert("逆向订单：" + $(this).val() + " 退款方式不是门店退款!");
	   		notStoreFlag = true;
			return;
	   	}
   		if($("#statusTotalCode_"+index).val() == "0270")
		{
			conOrder += $(this).val() + ",";
			as.push($(this).val()); 
			isOk = true;
		}else{
			alert("逆向订单：" + $(this).val() + " 不符合门店退款条件!");
			notStoreFlag = true;
			return;
		}
	  }   
	  index++;  
	});
	
	// 如果非门店退款
	if(notStoreFlag){
		return;
	}
	// 如果不满足执行条件
	if(!isOk){
		return;
	}
	
	var params = {
			ids:as
	};	
	var form = document.getElementById("listForm");
	form.action="refund!search.action";
	if($(this)[0].confirm('确定退款吗?'))
	{
		$.ajax({
			type:"POST",
			url:"refund!orderStoreRefund.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert("操作成功！"); 
				    form.submit(); 	
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
				alert("操作失败，请联系管理员!")
				form.submit();
			}
		});	
	}
}