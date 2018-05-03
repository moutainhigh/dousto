$().ready( function() {
	// 加载时显示物流公司链接
	logi($("#expressType")[0]);
});


function checkDetail(){
	// 校验申请人
	if($("#customerName").val() == "")
    {
    	alert("申请人必填");
    	$("#customerName").focus();
    	return false;
    }
    // 校验物流费用
    if($("#transportFee").val() == "")
    {
    	alert("物流费用必填");
    	$("#transportFee").focus();
    	return false;
    }
    // 校验开发票
    if($("#invoicePrinted").val() == "")
    {
    	alert("是否已开发票必填");
    	$("#invoicePrinted").focus();
    	return false;
    }
    // 校验是否需要退款
    if($("#ifNeedRefund").val() == "")
    {
    	//alert($("#ifNeedRefund").is(":hidden"));
    	if(!($("#ifNeedRefund").is(":hidden"))){
    		alert("是否需退款必填");
    		$("#ifNeedRefund").focus();
    		return false;
    	}
    }
    // 校验积分
    /*if(!checkIntegral()){
    	return false;
    }*/
    // 校验入库物流方式
    if($("#distributeType").val() == "")
    {
    	alert("入库物流方式必填");
    	$("#distributeType").focus();
    	return false;
    }
    // 校验备注和问题描述
    if(!checkRemark() || !checkClientServiceRemark()){
    	return false;
    }
    // 校验代退门店
    if($("#distributeType").val() == "3" && $("#selfTakePoint").val() == "")
    {
    	alert("代退门店必填");
    	$("#selfTakePoint").focus();
    	return false;
    }
    // 内容原因
    //if(count > 0)
    //{
    	var refundCount = false;
    	var reasonIsNull = false;
    	var sItemNo = "";
    	var pItemNo = "";
    	$("[name='ids']").each(function(){
    		var index=$(this).val();
          if($("#saleNum_"+index).val() <= 0)
		    {
		    	//alert("明细号"+$("#orderItemNo_"+index).val()+"的退货数量小于1，无法退货");
        	    sItemNo += $("#orderItemNo_"+index).val() + ",";
		    	$("#saleNum_"+index).focus();
		    	refundCount = true;
		    	return;
		    }
		    
		    if($("#preRefundReason_"+index).val() == "")
		    {
		    	//alert("明细号"+$("#orderItemNo_"+index).val()+"的内容原因必填");
		    	pItemNo += $("#orderItemNo_"+index).val() + ",";
		    	$("#preRefundReason_"+index).focus();
		    	reasonIsNull=true;
		    	return;
		    }    
		});
		// 内容原因不能为空
		if(refundCount)
		{
			alert("明细号"+sItemNo.substring(0,sItemNo.length-1)+"的数量小于1，不能提交");
			return false;
		}
		if(reasonIsNull)
		{
			alert("明细号"+pItemNo.substring(0,pItemNo.length-1)+"的内容原因必填");
			return false;
		}
	
    	/*for(var index=0;index<=count;index++)
    	{
		    if($("#saleNum_"+index).val() <= 0)
		    {
		    	alert("退货数量小于1，无法退货");
		    	$("#saleNum_"+index).focus();
		    	return;
		    }
		    if($("#preRefundReason_"+index).val() == "")
		    {
		    	alert("内容原因必填");
		    	$("#preRefundReason_"+index).focus();
		    	return;
		    }
    	}*/
    //}
    // 校验配送地区
    if($("#addressCode").val() == "")
    {
    	alert("客户信息中的配送地区必填");
    	$("#addressCode").focus();
    	return false;
    }
    // 校验配送地区
    if($("#stateSelect").val() == "")
    {
    	alert("客户信息中的配送地区必填");
    	$("#stateSelect").focus();
    	return false;
    }
    // 校验具体地址
    if($("#addressDetail").val() == "")
    {
    	alert("客户信息中的具体地址必填");
    	$("#addressDetail").focus();
    	return false;
    }
    // 校验客户姓名
    if($("#userName").val() == "")
    {
    	alert("客户信息中的客户姓名必填");
    	$("#userName").focus();
    	return false;
    }
    // 校验手机号码
    if($("#mobPhoneNum").val() == "")
    {
    	alert("客户信息中的手机号码必填");
    	$("#mobPhoneNum").focus();
    	return false;
    }
    
    return true;
}
		

/**
 * 校验备注
 */
function checkClientServiceRemark(){
	var remark = $("#clientServiceRemark").val();
	if(remark.length > 20){
		alert("备注字数不能超过20");
		$("#clientServiceRemark").focus();
		return false;
	}
	return true;
}
/**
 * 校验问题描述
 */
function checkRemark(){
	var remark = $("#remark").val();
	if(remark.length > 150){
		alert("问题描述字数不能超过150");
		$("#remark").focus();
		return false;
	}
	return true;
}




function deleteItemRow(obj, index, orderPayId){
    //if (orderPayId != null){
        //$("#deleteOrderPayIds_"+index).val(orderPayId);
    //}
    $(obj).parent().parent().remove();
    $().caculate();
  }

function deleteItemRow4ModifyPage(obj, index, orderPayId){
    if (orderPayId != null){
        $("#deleteIds_"+index).val(orderPayId);
    }
    $(obj).parent().parent().remove();
    $().caculate();
  }

function batchDeleteItemRow(){
	var count = 0;
	$("[name='ids']").each(function(){
      //if($(this).attr("checked")) 
    	  if(this.checked)
		  {   count++; 
    	  $(this).parent().parent().remove();
		  }     
		});
	if(count<=0)
	{
		alert("请选择需要删除的明细");
	}
	$().caculate();
}

/**
 * 选择色码款
 */
/*function chooseColorSize() {
	var url = "color_size.action";
	var count = 0;
	$("[name='ids']").each(function(i,o) {
		if ($(this).attr("checked")) {
			count++;
		}
	});
	if (count <= 0) {
		alert("请选择要操作的记录");
		return;
	}
	if (count > 1) {
		alert("只能操作一条记录");
		return;
	}
	
	$("[name='ids']").each(function(i,o) {
		if ($(this).attr("checked")) {
			var orderItemId = $("#orderItemId_" + i).val();
			var skuNo = $("#skuNo_" + i).val();
			//window.location.href = url +"?orderItemId="+ orderItemId +"&skuNo=" + skuNo;
			var retVal = window.showModalDialog(url +"?orderItemId="+ orderItemId +"&skuNo=" + skuNo,"newwin","dialogWidth=460px;dialogHeight=400px;status=no;help=no;scroll=no;resizable=no;location=no;toolbar=no;");
		}
	});
}*/
/*function chooseColorSize() {
	var url = "color_size.action";
	var count = 0;
	$("[name='ids']").each(function(i, o) {
		if ($(this).attr("checked")) {
			count++;
		}
	});
	if (count <= 0) {
		alert("请选择要操作的记录");
		return;
	}
	if (count > 1) {
		alert("只能操作一条记录");
		return;
	}

	$("[name='ids']").each(function(i, o) {
		if ($(this).attr("checked")) {
			var orderItemId = $("#orderItemId_" + $(this).val()).val();
			var skuNo = $("#skuNo_" + $(this).val()).val();
			
			// 是否是色码款商品标示
			var productPropertyFlag = $("#productPropertyFlag_" + $(this).val()).val();
			if(productPropertyFlag != 1){
				alert(skuNo + "不是色码款商品");
				return false;
			}
			
			// window.location.href = url +"?orderItemId="+
			// orderItemId +"&skuNo=" + skuNo;
			// var retVal = window.showModalDialog(url
			// +"?orderItemId="+ orderItemId +"&skuNo=" +
			// skuNo,"newwin","dialogWidth=460px;dialogHeight=400px;status=no;help=no;scroll=no;resizable=no;location=no;toolbar=no;");

			var params = {
				orderItemId : orderItemId,
				skuNo : skuNo
			};
			$.ajax({
					type : "POST",
					url : "color_size.action",
					data : params,
					dataType : "json",
					success : function(data) {
						if (data.status == "success") {
							//alert(data.message);
							var message = data.message;
							if (message.indexOf("异常") == -1) {
								//alert("aaa");

								$("#colorSizeDisplay").css(
										'display', 'block');
								eval("var movie = "
										+ data.message);
								//alert(movie);
								var content = '<label style="position: absolute;top: 5px;right: 8px;cursor: pointer;" onclick="colorSizeClose()">X</label>';
								var group = 0;
								for ( var m in movie) {
									// alert("m"+m);
									content += '<input type="hidden" id="colorSize'+group+'" value="'+m+'"/>' + m + ":";
									for ( var v in movie[m]) {
										// alert("V"+movie[m][v]);
										content += "<label><input type='radio' onclick='selectColorSize()' name='colorSize" + 
										+ group + "' value='" + movie[m][v]
										+ "'/>"
										+ movie[m][v]
										+ "</label>";
									}
									content += "<br/><br/>";
									group++;
								}
								content += '<input type="button" class="formButton" style="position: absolute;bottom: 10px;left: 118px;" onclick="colorSizeConfirm()" value="确定" hidefocus="true"/>';
								$("#colorSizeDisplay")
										.html(content);
								// alert(movie['颜色-女装'][0]);
							}
						} else {
							alert(data.message);
						}
					},
					error : function(data) {
						alert("error!")
					}
				});
		}
	});
}*/
/**
 * 选择色码款
 */
function chooseColorSize() {
	var url = "color_size.action";
	var count = 0;
	$("[name='ids']").each(function(i, o) {
		//if ($(this).attr("checked")) {
		if(this.checked){
			count++;
		}
	});
	if (count <= 0) {
		alert("请选择要操作的记录");
		return;
	}
	if (count > 1) {
		alert("只能操作一条记录");
		return;
	}
	
	$("[name='ids']").each(function(i, o) {
		//if ($(this).attr("checked")) {
		if(this.checked){
			var orderItemId = $("#orderItemId_" + $(this).val()).val();
			var skuNo = $("#skuNo_" + $(this).val()).val();
			
			// 是否是色码款商品标示
			/*var productPropertyFlag = $("#productPropertyFlag_" + $(this).val()).val();
			if(productPropertyFlag != 1){
				alert(skuNo + "不是色码款商品");
				return false;
			}*/
			
			// window.location.href = url +"?orderItemId="+
			// orderItemId +"&skuNo=" + skuNo;
			// var retVal = window.showModalDialog(url
			// +"?orderItemId="+ orderItemId +"&skuNo=" +
			// skuNo,"newwin","dialogWidth=460px;dialogHeight=400px;status=no;help=no;scroll=no;resizable=no;location=no;toolbar=no;");
			
			var params = {
					orderItemId : orderItemId,
					skuNo : skuNo
			};
			$.ajax({
				type : "POST",
				url : "color_size.action",
				data : params,
				dataType : "json",
				success : function(data) {
					if (data.status == "success") {
						//alert(data.message);
						var message = data.message;
						if (message.indexOf("异常") == -1) {
							//alert("aaa");
							
							$("#colorSizeDisplay").html("");
							
							eval("var movie = "
									+ data.message);
							//alert(movie);
							var content = '<label style="position: absolute;top: 5px;right: 8px;cursor: pointer;" onclick="colorSizeClose()">X</label>';
							var group = 0;
							var col = 1;
							//alert("V"+movie[2]["colorSizeInfos"][1]["colorSizeValueName"]);
							for ( var m in movie) {
								 //alert("m"+m+" "+movie[m]["colorSizeInfos"][0]["colorSizeValueName"]+movie[m]["colorSizeInfos"][1]["colorSizeValueName"]);
								//content += '<input type="hidden" id="colorSize'+group+'" value="'+m+'"/>' + m + ":";
								/*for ( var v in movie[m]) {
									//alert("aaa"+v);
									 //alert("V"+movie[m][v]);
									content += "<label><input type='radio' onclick='selectColorSize()' name='colorSize" + 
									+ group + "' value='" + movie[m][v]
									+ "'/>"
									+ movie[m][v]
									+ "</label>";
								}*/
								//alert(movie[m]["colorSizeInfos"][0]["colorSizeValueName"]);
								var colorSize1 = "";
								var colorSize2 = "";
								if(movie[m]["colorSizeInfos"][0]){
									//alert("false");
									//continue;
									colorSize1 = movie[m]["colorSizeInfos"][0]["colorSizeValueName"];
								}
								if(movie[m]["colorSizeInfos"][1]){
									//alert("false");
									//continue;
									colorSize2 = movie[m]["colorSizeInfos"][1]["colorSizeValueName"];
								}
								//alert(movie[m]["colorSizeInfos"][0]["colorSizeValueName"]+" "+movie[m]["colorSizeInfos"][1]["colorSizeValueName"]);
								col++;
								content += "<label><input type='radio' onclick='selectColorSize()' colorSize='"
								+ colorSize1 +" "+ colorSize2
								+"' name='colorSize" + 
								"' value='" + movie[m]["skuCode"]
								+ "'/>"
								+ colorSize1+" "+colorSize2
								+ "</label>  &nbsp;";
								if(col > 5){
									col = 1;
									content += "<br/><br/>";
								}
								
								group++;
							}
							content += '<div style="padding-top: 45px;text-align: center;padding-right: 25%;"><input type="button" class="formButton" style="position: absolute;bottom: 10px;" onclick="colorSizeConfirm()" value="确定" hidefocus="true"/></div>';
							$("#colorSizeDisplay")
							.html(content);
							
							$("#colorSizeDisplay").css(
									'display', 'block');
							// alert(movie['颜色-女装'][0]);
						}
						else{
							alert(message);
						}
					} else {
						alert(data.message);
					}
				},
				error : function(data) {
					alert("error!")
				}
			});
		}
	});
}

function selectColorSize(){
	//alert("123");
}

/**
 * 关闭色码款选择框
 */
function colorSizeClose(){
	//alert("close");
	$("#colorSizeDisplay").css('display','none');
}

/**
 * 点击色码款选择框“确定”按钮
 */
function colorSizeConfirm(){
	colorSizeClose();
	var content = "";
	var skuCode = "";
	$('input:radio:checked').each(function(){
		skuCode = this.value;
		//alert($(this)[0].getAttribute("colorSize"));
		var colorSize = $(this)[0].getAttribute("colorSize");
		content += "<font color='green'>\"" + colorSize + "\"</font>";
		});
	$("[name='ids']").each(function(i, o) {
		if ($(this).attr("checked")) {
			//alert($(this).val());
			$("#submitColorSize_"+$(this).val()).html(content);
			$("#submitSkuCode_"+$(this).val()).val(skuCode);
		}
	});
	
}
/*function colorSizeConfirm(){
	colorSizeClose();
	var content = "";
	$('input:radio:checked').each(function(){
		//alert(this.value);
		
		//alert($(this)[0].getAttribute("name"));
		var groupId =$(this)[0].getAttribute("name");
		var groupName = $("#"+groupId).val(); 
		//alert(groupName);
		content += groupName + ":" + this.value + "<br/>";
	});
	$("[name='ids']").each(function(i, o) {
		if ($(this).attr("checked")) {
			//alert($(this).val());
			$("#submitColorSize_"+$(this).val()).html(content);
		}
	});
	
}*/

function setHiddenIfNeedRefundValue(){
	// 如果是入库物流方式为门店代退
	if($("#distributeType")[0].value == "3")
    {
    	$("#hiddenIfNeedRefund").val($("#ifNeedRefund").val());
    	//alert("hiddenIfNeedRefund:"+$("#hiddenIfNeedRefund").val());
    	if($("#address").val() == ''){
    		alert("请选择自提点");
    		$("#merchantSelect").focus();
    		return false;
    	}
    	return true;
    }
    else
    {
    	$("#hiddenIfNeedRefund").val($("#ifNeedRefund2").val());
    	return true;
    }
}

// 物流公司
function logi(obj){
	if(!obj){
		return;
	}
	var index=obj.selectedIndex;   
    var link=obj.options[index].getAttribute("link");
	    if(link){
			var searchSpan=jQuery("#searchSpan").show();
			var searchA=jQuery("#searchA").attr("href",link);
	    }else{
	    	var searchSpan=jQuery("#searchSpan").hide();
			var searchA=jQuery("#searchA").attr("href","");
	    }
}


function checkPayAmountSum(){
	var orderCategoryFlag2 = $("#orderCategoryFlag").val();
	// 已审核的不校验退款信息// 如果是退款单或运费补款单
	if($("#statusConfirmFlag").val() == '0807' && !((orderCategoryFlag2 == 'ref' || orderCategoryFlag2 == 'tsf'))){
		return true;
	}
	
	// 校验是否有退款信息
	/*if($("[name='orderPayFlag']").size() <= 0)
    {
    	alert("请填写退款信息");
    	return false;
    }*///20180416
	
  // 校验退款方式是否为空
 /* var methodFlag = false;
  $("#retInfoTab").find("tr").find("td:eq(0)").each(function(){
		   if($(this).find("select").val() == "")
	   {
			   alert("请选择退款方式");
			   $(this).find("select").focus();
			   methodFlag = true;
	   }
	  });	
  if(methodFlag)
  {
	  return false;
  }*///20180416
	
  // 校验退款金额总和是否与退款总额一致
  /*var totalPayAmount = $("#totalPayAmount").text();
	  var orderPayAmount = 0; 
	  $("#retInfoTab").find("tr").find("td:eq(1)").each(function(){
		   //orderPayAmount += parseFloat(($(this).find("input[type='text']").val())*1).toFixed(2);
		  var tmp= parseFloat(($(this).find("input[type='text']").val())*1).toFixed(2);
		  orderPayAmount = (parseFloat(orderPayAmount) + parseFloat(tmp)).toFixed(2);
	  });
	  
	  if(orderPayAmount != totalPayAmount){
	      alert("退款信息中的退款金额总和应与退款总额一致");
	      //$(obj).val(0);
        //$(obj).focus();
	      return false;
	  }*///20180416
  return true;
}

