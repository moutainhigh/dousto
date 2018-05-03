function getCodPayWay()
{
	if($("#payWay").val() == '1')
	{
		$("#onlinePay").val("830");
		$("#onlinePay").attr({style:"display:none"});
		$("#cod").attr({style:"display:inline-block"});
		var codVal = $("#cod").val();
		if(codVal == "")
		{
			$("#orderPayCode").val("1");
		}
		else
		{
			$("#orderPayCode").val(codVal);
		}
	}
	else if($("#payWay").val() == '830')
	{
		$("#cod").val("1");
		$("#cod").attr({style:"display:none"});
		$("#onlinePay").attr({style:"display:inline-block"});
		if($("#onlinePay").val() == "")
		{
			$("#orderPayCode").val("830");
		}
		else
		{
			$("#orderPayCode").val($("#onlinePay").val());
		}
	}
	else
	{
		$("#cod").val("1");
		$("#onlinePay").val("830");
		$("#cod").attr({style:"display:none"});
		$("#onlinePay").attr({style:"display:none"});
		var payWay = $("#payWay").val();
		$("#orderPayCode").val(payWay);
	}
}

function changePayCode(object)
{
	var objectId = object.id;
	$("#orderPayCode").val($("#"+ objectId).val());
}

// 支付方式选中
function selectPayCode()
{
	if($("#payWay").val() == '1')
	{
		$("#cod").attr({style:"display:inline-block"});
		$("#orderPayCode").val("1");
		return;
	}
	if($("#payWay").val() == '830')
	{
		$("#onlinePay").attr({style:"display:inline-block"});
		$("#orderPayCode").val("830");
		return;
	}
	
	var codList=new Array("50300","307","50301");
	for(var i=0;i<codList.length;i++)
	{
		if($("#cod").val() == codList[i])
		{
			$("#cod").attr({style:"display:inline-block"});
			$("#orderPayCode").val($("#cod").val());
			
			var orderPayCodeCount = "${paymentModeList.size()}";
			for(var i=0;i<orderPayCodeCount;i++) 
			{ 
				if($("#payWay")[0].options[i].value == '1') 
				{ 
					$("#payWay ").get(0).options[i].selected = true; 
					break; 
				}
			} 
			return;
		}
	}
	
	//var onlinePayList=new Array("880","200","203","820","860","50400","50900","850","50100","52000","50800");
	var onlinePayList=new Array();
	var payCount = "${paymentMethodList.size()}";
	for(var i=1;i<(payCount-1);i++)
	{
		onlinePayList[i] = $("#onlinePay")[0].options[i].value;
	}
	
	for(var i=0;i<onlinePayList.length;i++)
	{
		if($("#onlinePay").val() == onlinePayList[i])
		{
			$("#onlinePay").attr({style:"display:inline-block"});
			$("#orderPayCode").val($("#onlinePay").val());
			
			var orderPayCodeCount = "${paymentModeList.size()}";
			for(var i=0;i<orderPayCodeCount;i++) 
			{ 
				if($("#payWay")[0].options[i].value == '830') 
				{ 
					$("#payWay ").get(0).options[i].selected = true; 
					break; 
				}
			} 
			return;
		}
	}
}