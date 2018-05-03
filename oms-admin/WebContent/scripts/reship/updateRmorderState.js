function reshipUpdateState(obj){
var status=obj.name;
var id ;
if($("input[name='ids']:checked").length!=1){
	alertInfo("对不起，请选择一项进行处理!",false);
	return;
}else{
	$("input[name='ids']").each(
			function (){
				if($(this).attr("checked")){
					
					id=$(this).attr("value");
				}
			}
		);
}

if(status=="sendedwms" || status=="exc_sendedwms"){
		$("#btnOk").click(function () {
		 	var carrierCode = $('#carrier option:selected').attr("value");
		 	var trackNo =$("#trackNo").val();
		 	if(trackNo.length<1 || carrierCode.length<1){
		    	alert("请把内容填写完整后提交！");
		    }else{
		    $.get("reship_oper_state.action",{"id":id,"rmaorderStatus":status,"carrierCode":carrierCode,"refundTrackNo":trackNo},function (data){
				alert(data.memo);
				window.location.reload();
				},"json");
		     $.blockUI({
		        message: "<h2>正在提交数据，请稍候……</h2>",
		        css: {
		            border: '1px solid black'
		        }
		    });
		   
		    setTimeout(function () { $.unblockUI() }, 1000);
		    }
		   
		});
		$("#btnCancel").click(function () {
		    $.unblockUI();
		});
		 $.blockUI({
		        message: $("#carrier_trackNo"),
		        css: {
		            width: '300px',
		            height: '200px',
		            left: ($(window).width() - 300) / 2 + 'px',
		            top: ($(window).height() - 300) / 2 + 'px',
		            border: 'none'
		        }
		    });
	}else if(status=='approved'||status=='notapproved'||status=='exc_approved'||status=='exc_notapproved'){
		$("#confrim_info").attr("title","请填写备注信息（最多200个字符）");
		$("#confrim_info").html("<textArea maxlength='200' id='approvalComments' name='approvalComments' cols='35' rows='7'></textArea>");
		//$('#approvalComments').limit('6');
		$("#approvalComments").keypress(function(event){
			var code;
			if(event.charCode != undefined){
				code = event.charCode;
			}else{
				code = event.keyCode;
			}
			if(code==0){
				code=event.which;
			}
			var curLength=$("#approvalComments").val().length;
			if(curLength>200){
				if (code==8||code==46){
					
				}else{
					return false;
				}
			}
		});
		$("#confrim_info").dialog({
			autoOpen:true,
			modal: true,
			resizable:false,
	        //draggable:false,
			buttons: {
				确定: function(){
					var curLength=$("#approvalComments").val().length;
					if(curLength>200){
						alertInfo("超过最大长度!",false);
						return false;
					}
					$.get("reship_oper_state.action",{"id":id,"rmaorderStatus":status,"approvalComments":$("#approvalComments").val()},function (data){
						$(this).dialog("close");
						alertInfo(data.memo,true);
					},"json");
					$(this).dialog("close");
				},
				取消: function() {
					$(this).dialog("close");
				}
			}
		});
	}else if(status=='completed'){
		$("#confrim_info").attr("title","确认退款？");
		$("#confrim_info").html("退款金额：<input type='text' maxlength='10'  onkeydown='if(event.keyCode==13)event.keyCode=9' onkeypress='var code;if(event.charCode != undefined){code = event.charCode;}else{code = event.keyCode;}if(code==0){code=event.which;}if ((code>47 && code<58)||code==8||code==46){}else{return false;}' name='actualRefundAmount' id='actualRefundAmount' style='ime-mode:disabled;width:200px;' /><br>备注信息：<textArea maxlength='80' id='actualRefundComments' name='actualRefundComments' cols='24' rows='7' style='vertical-align:top;'></textArea>");
		//$('#approvalComments').limit('6');
		$("#actualRefundComments").keypress(function(event){
			var code;
			if(event.charCode != undefined){
				code = event.charCode;
			}else{
				code = event.keyCode;
			}
			if(code==0){
				code=event.which;
			}
			var curLength=$("#actualRefundComments").val().length;
			if(curLength>80){
				if (code==8||code==46){
					
				}else{
					return false;
				}
			}
		});
		$("#confrim_info").dialog({
			autoOpen:true,
			modal: true,
			resizable:false,
	        //draggable:false,
			buttons: {
				确定: function(){
					var curLength=$("#actualRefundComments").val().length;
					if(curLength>80){
						alertInfo("超过最大长度!",false);
						return false;
					}
					var curLength=$("#actualRefundAmount").val().length;
					if(curLength<=0){
						alertInfo("请输入退款金额!",false);
						return false;
					}
					$.get("reship_oper_state.action",{"id":id,"rmaorderStatus":status,"actualRefundComments":$("#actualRefundComments").val(),"actualRefundAmount":$("#actualRefundAmount").val()},function (data){
						$(this).dialog("close");
						alertInfo(data.memo,true);
					},"json");
					$(this).dialog("close");
				},
				取消: function() {
					$(this).dialog("close");
				}
			}
		});
	}else{
		$("#confrim_info").html("您确认要进行此操作么?");
		$("#confrim_info").dialog({
			autoOpen:true,
			modal: true,
			resizable:false,
	        //draggable:false,
			buttons: {
				确定: function(){
					$.get("reship_oper_state.action",{"id":id,"rmaorderStatus":status},function (data){
						$(this).dialog("close");
						alertInfo(data.memo,true);
					},"json");
					$(this).dialog("close");
				},
				取消: function() {
					$(this).dialog("close");
				}
			}
		});
	}
}


function showFlowTrace(){
	var id ;
	if($("input[name='ids']:checked").length!=1){
		alert("对不起，请选择一项进行处理");
		return;
	}else{
		$("input[name='ids']").each(
				function (){
					if($(this).attr("checked")){
						
						id=$(this).attr("value");
					}
				}
			);
	}
	 $("#flowTraceDiv").load("flow_trace.action?rma_id="+id,"", function(){
		 $("#closeButton").click( function() {
			 $.unblockUI();
			});			 
	 });
	 $.blockUI({
	        message: $("#flowTraceDiv"),
	        css: {
	            width: '450px',
	            height: '200px',
	            left: ($(window).width() - 300) / 2 + 'px',
	            top: ($(window).height() - 300) / 2 + 'px',
	            border: 'none'
	        }
	    });
	
}

function reloadFrame(){
	//alert(window.location.href);
	window.location.href=window.location.href+"?currentStatus="+$("#currentStatus").val()+"&selected="+$("#selected").val();
}


function alertInfo(message,reLoad){
	$("#result_info").html(message);
	$("#result_info").dialog({
		autoOpen:true,
		modal: true,
		resizable:false,
       // draggable:false,
		buttons: {
			确定: function() {
				$(this).dialog("close");
				if(reLoad){
					window.location.reload();
				}
			}
		}
	});
}




