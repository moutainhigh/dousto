<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>销售订单详情 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/resources/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base}/resources/admin/css/jquery.ui.all.css">
<script src="${base}/resources/admin/js/jquery-1.10.2.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.core.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.widget.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.accordion.js"></script>
<script src="${base}/resources/datepicker/ui/jquery-ui.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script src="${base}/resources/common/js/common.js"></script>
<@sj.head compressed="false" scriptPath="${webuiPath}/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<style type="text/css">
	a:link{
	color: #336699;
	}
	a:hover {
	color: #AB0000;
	}
	
</style>

<script type="text/javascript">
$().ready( function() {
	$( "#tabs" ).tabs();	
        $( "#accordion" ).accordion({
			collapsible: true,
		    autoHeight: true ,
		      clearStyle :true,
		    fillSpace:true
		});
		
		$( "#accordion01" ).accordion({
			collapsible: true,
		    autoHeight: true ,
		      clearStyle :true,
		    fillSpace:true
		});
		
	    $( "#accordion11" ).accordion({
			collapsible: true,
		    autoHeight: true ,
		     clearStyle :true,
		    fillSpace:true
		});
		
		//  $('#accordion').accordion('option', 'autoHeight', false);
		//  $('#accordion').accordion({ header: 'h2' });

		
		$( "#accordion1" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		
	    $( "#accordion2" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		
		$( "#accordion21" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		
	    $( "#accordion22" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		$( "#accordion12" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
});
	
	function isNeedInvoice(){
		if($("#needInvoice").val() == "0")
		{
			$("#invoiceHead").css("display","none");
			$("#invoiceContent").css("display","none");
			$("#invoiceNum").css("display","none");
			
			//$("#invoiceHead").val("");
			//$("#invoiceContent").val("");
			//$("#invoiceNum").val("");
		}
		else
		{
			$("#invoiceHead").css("display","block");
			$("#invoiceContent").css("display","block");
			$("#invoiceNum").css("display","block");
			
			//$("#invoiceHead").val($("#invoiceHeadHidden").html());
			//$("#invoiceContent").val($("#invoiceContentHidden").html());
			//$("#invoiceNum").val($("#invoiceNumSpan").html());
		}
	}
</script>

<script type="text/javascript">
function saveOrderSub(){
	/*if(!this.confirm("确定保存吗？"))
	{
		return;
	}*/
	var form = document.getElementById("listForm");
	form.action="order!updateSubDetail.action";
	form.submit();
}

function saveOrderInvoice(){
	/*if(!this.confirm("确定保存吗？"))
	{
		return;
	}*/
	var form = document.getElementById("listForm");
	form.action="order!updateOrderInvoice.action"; 
	form.submit();
}
function changeOrderStatus(){
	if($("#payCode_select").val() == '830')
	{
		alert("请先选择具体的在线支付方式并保存！");
		$("#payCode_select").focus();
		return;
	}
	var select = $("#payCode_select").val();
	if(typeof(select) != 'undefined')
	{
		alert("请先保存支付明细！");
		$("#save_select").focus();
		return;
	}
	if(typeof($("#payCodeTmp").val()) == 'undefined' || $("#payCodeTmp").val() == '')
	{
		alert("无在线支付明细，无法做此操作！");
		return;
	}
	if(typeof($("#payCodeTmp2").val()) != 'undefined' && $("#payCodeTmp2").val() == '')
	{
		alert("请先选择具体的在线支付方式并保存！");
		$("#payCodeTmp2").focus();
		return;
	}
	
	if(!this.confirm("确认已支付？"))
	{
		return;
	}
	var form = document.getElementById("listForm");
	form.action="order!changeOrderStatus.action"; 
	form.submit();
}

//设置取消订单form表单提交的取消订单原因编号
function setCancelReasonNo4CancelForm(){
	$("#cancelReasonNoCancelForm").val($("#cancelReasonNo").val());
}

function cancleOrderSub(){
	var cancelReasonNo = $("#cancelReasonNo").val();
	if(cancelReasonNo == '')
	{
		alert("请选择取消原因！");
		$("#cancelReasonNo").focus();
		return;
	}

	var form = document.getElementById("cancleForm");
	if(!this.confirm("确认取消订单？"))
	{
		return;
	}
	form.submit();
}

function finishOrderSub(){
	var form = document.getElementById("finishForm");
	if(!this.confirm("确认完成订单？"))
	{
		return;
	}
	form.submit();
}

function approveOrderSub(){
	var payCode = $("#payCodeTmp").val();
	var payAmount= $("#payAmountTmp").val();
	var totalPayAmount = $("#totalPayAmountTmp").val();
	if(payCode == '301' && payAmount == '0' && totalPayAmount >= 100)
	{
	 	alert("亲，此单购物券异常，请暂时不要审核！");
	 	return;
	}
	var form = document.getElementById("approveForm");
	form.submit();
}


function refundOrder(orderNo,column){
	var url="sale_after_order/return!returnView.action?orderNo="+orderNo+"&column="+column+"&orderCategory=ret";
	window.open(url);
}

function exchangeOrder(orderNo,column){
	var url ="sale_after_order/change!change.action?orderNo="+orderNo+"&column="+column+"&orderCategory=chg";
	window.open(url);
}

function rejectOrder(orderNo,column){
	var url= "sale_after_order/refuse!refuse.action?orderNo="+orderNo+"&column="+column+"&orderCategory=rej";
	window.open(url);
}

function addPayment(){
	var targetTab = document.getElementById("paymentTab");
	var row = targetTab.insertRow(2);  
	var cell0 = row.insertCell(0);  
	cell0.innerHTML=" <select name='payCode' style='width: 80px' onchange='checkPayCode(this)'><#list addOrderPayList as list> <option value='${list.id}'> ${list.payName}</option></#list></select>";
	
	var cell1 = row.insertCell(1); 
	cell1.innerHTML="<input type='text' style='width:60px' name='payAmount' /> ";
	
	var cell2 = row.insertCell(2);  
	cell2.innerHTML=" <input type='text' style='width:100px' name='serialNo' /> ";
	
	//var cell3 = row.insertCell(3); 
	//cell3.innerHTML=" 状态 ";
	
	var cell4 = row.insertCell(3); 
	cell4.innerHTML="<input type='button' value='删除' onclick='deletePayment(this,\"0\")'/> <input type='button' value='保存' onclick='savePayment(this,\"0\")'/>";
	
}

function checkPayCode(obj){
    
    if(obj.value==""||obj.value==null){
    	alert("选择支付方式");
    	return ;
    }

     var td = obj.parentNode;
	 var tr = td.parentNode;
	
     var tab  = document.getElementById("paymentTab");
     var input ;
     for(var i=2;i<tab.rows.length;i++){
		if(tr.rowIndex!=i){
	        var node = tab.rows[i].cells[0].childNodes;
	        for(var j=0;j<node.length;j++){
	        
	            var childNode = tab.rows[i].cells[0].childNodes[j];
	       	 	var name =childNode.name;
		        if('payCode'==name){
			        input =  childNode.value;
			
			        if(obj.value==input){
			              alert("支付方式已添加!");
			              obj.options[0].selected=true;
			              obj.focus();
			              return;
				     }
			     }
		     }
	     }
     }
}

function savePayment(obj,id){
   var tr = this.getRowObj(obj);
    
   var allAmount = getTableAllPayAmount(tr);
   
    var cells = tr.cells;
    var payCode =  getTdValue('payCode',cells[0]);
    if(payCode==""||payCode==null){
    	alert("选择支付方式");
    	return ;
    }
    if(payCode=="830"){
    	alert("请选择具体在线支付方式");
    	$("#payCode_select").focus();
    	return ;
    }
    
    var payAmount =  getTdValue('payAmount',cells[1]);
    
    if(payAmount=="" || payAmount==null||isNumber(payAmount)=='false'){
       alert("请输入数字");
	   return ;
    }
        
    var allAmount = getTableAllPayAmount(tr);

    var totalPayAmount = $( "#totalPayAmount").val();
    
   
   
   allAmount = parseFloat(allAmount)+parseFloat(payAmount);

   if(totalPayAmount<allAmount){
   		alert('支付总和超过应付金额!');
   		return ;
   }


	var ids =  getTdValue('ids',cells[0]);
	var serialNo = getTdValue('serialNo',cells[2]);
	
	$( "#ids").val(ids);
    $( "#payCode").val(payCode);
    $( "#payAmount").val(payAmount);
    $( "#serialNo").val(serialNo);
    var form = document.getElementById("updatePayForm");
	form.action="order!updateOrderPayment.action";
	
	if($(this)[0].confirm("确定保存吗？"))
	{
		form.submit();
	}
}

function getTdValue(name,cellObj){
    var nodeObj = cellObj.childNodes;
	for(var i=0;i<nodeObj.length;i++){
	    var childNode = nodeObj[i];
	    if(childNode.name==name){
	        input =  childNode.value;
			return input;
	     }
	 }
}

function getTableAllPayAmount(tr){
     var payAuount=0;
	 var tab  = document.getElementById("paymentTab");
	 
	 for(var i=2;i<tab.rows.length;i++){
		if(tr.rowIndex!=i){
	        var node = tab.rows[i].cells[1].childNodes;
	        for(var j=0;j<node.length;j++){
	        
	            var childNode = node[j];
	       	 	var name =childNode.name;
		        if('payAmount'==name){
		           payAuount= parseFloat(payAuount)+parseFloat(childNode.value);
			        //payAuount = payAuount+  childNode.value;
			     }
		     }
	     }
     }
     
     return payAuount;

}

function deletePayment(obj,id){
   if(id=='0'){
	   var tr = this.getRowObj(obj);
	   if(tr != null){
	       tr.parentNode.removeChild(tr);
	   }else{
	       throw new Error("the given object is not contained by the table");
	   }
   }else{
       $( "#payId").val(id);
       var form = document.getElementById("deletePayForm");
	   form.action="order!deleteOrderPayment.action"
	   
	   if($(this)[0].confirm("确定删除吗？"))
		{
			form.submit();
		}
   }
}

function getRowObj(obj){
   while(obj.tagName.toLowerCase() != "tr"){
    obj = obj.parentNode;
    if(obj.tagName.toLowerCase() == "table")return null;
   }
   return obj;
}

function addBlacklist(){
  var form = document.getElementById("addBlacklistForm");
  var blackType =  $( "#tblackType").val();

  $( "#blackType").val(blackType);
  
  form.submit();
}


</script>


</head>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>订单详情</h1>
		</div>
		
			<div class="blank"></div>
		
			
			<div id="tabs">
			<form id="listForm" action="order!updateSubDetail.action" method="post">
		    <input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="order.id" value="${order.id}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="order.orderNo" value="${order.orderNo}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${orderSub.orderSubNo}" />
			<input type="hidden" name="order.totalPayAmount" id="totalPayAmount" value="${(order.totalPayAmount)!'0'}" />
			<input type="hidden" name="orderInvoice.id" value="${orderInvoice.id}" />
			<ul>
				<li><a href="#orderMianDiv">基本信息</a></li>
				<li><a href="#orderSubDiv">商品明细</a></li>
				<li><a href="#orderOperateLogDiv">订单日志</a></li>
				<li><a href="#orderMainStatusLogDiv">订单主状态日志</a></li>
				<li><a href="#orderStatusLogDiv">订单物流状态日志</a></li>
				<li><a href="#orderThirdStatusLogDiv">第三方物流状态日志</a></li>
				<li><a href="#purchaseRecordsDiv">会员购买记录</a></li>
			</ul>
			<div style="clear:both;"/>	
			<#assign width=-50 />		
			<div id="orderMianDiv" style="display:block;height:100%;">
               <ul>
                 <li style="float:left;margin-top:15px;">
                 	<#if order.statusTotal == '0131'> 
                 	<#elseif order.statusTotal == '0142'>
                 	<#else>
                 	<button type="button" onclick="saveOrderSub();" >保存收货人信息/客服备注/配送信息 </button>
                 	<button type="button" onclick="saveOrderInvoice();" >保存发票信息 </button>
                 	</#if>
                 	<button type="button" <#if order.statusPay != '0410'>disabled</#if> onclick="changeOrderStatus();" >订单已支付</button>
                 	<#if order.statusTotal == '0141' || order.statusTotal =='0140'>
                 		<#if order.orderCategory != 'chgOut'> 
                 		<button type="button" onclick="approveOrderSub();">审核通过</button>
                 		</#if>
                 	<#assign width=0 />		
                 	</#if>
                 	<#if order.statusTotal == '0170'> 
                 	<#elseif order.statusTotal == '0171'> 
                 	<#elseif order.statusTotal == '0180'> 
                 	<#elseif order.statusTotal == '0181'> 
                 	<#elseif order.statusTotal == '0153'> 
                 	<#elseif order.statusTotal == '0131'> 
                 	<#elseif order.statusTotal == '0142'> 
                 	<#elseif order.statusTotal == '0131'> 
                 	<#elseif order.orderCategory == 'chgOut'> 
                 	<#else>
             		<span style="border: solid 1px rgb(223, 223, 223);padding-top: 2px;padding-left: 4px;padding-bottom: 5px;background-color: rgb(253, 250, 250);">
							<select id="cancelReasonNo" onchange="setCancelReasonNo4CancelForm()">
		                 	     <#list cancelReasonList as cancelReason>
								    <option value="${cancelReason.code}" <#if cancelReason.code == order.cancelReasonNo>selected</#if>>
								    	<#if cancelReason.code == ''>
								    	取消原因
								    	<#else>
										${cancelReason.name}
										</#if>
									</option>
								 </#list>
						   </select>
	                 	<button type="button" onclick="cancleOrderSub();" >订单取消</button>
	                 </span>
	                 	<#assign width=0 />		
                 	</#if>
                 	<#if (order.statusTotal == '0180')> 
	                 	<button type="button" onclick="refundOrder(${order.orderNo},${(column)!});" >退货</button>
	                 	<button type="button" onclick="exchangeOrder(${order.orderNo},${(column)!});"> 换货</button>
	                 	<#assign width=0 />	
                 	</#if>
                 	<#if (order.statusTotal == '0170' || order.statusTotal == '0171') &&  order.orderCategory != 'chgOut'> 
                    	<button type="button" onclick="rejectOrder(${order.orderNo},${(column)!});"> 拒收</button>
                    	<!--<button type="button" onclick="finishOrderSub();"> 订单完成</button>-->
                     	<#assign width=0 />	
                 	</#if>
                 	<!-- 订单拒收退货 -->
                 	<#if (order.statusTotal == '0181')> 
                    	<button type="button" onclick="rejectOrder(${order.orderNo},${(column)!});"> 拒收</button>
                     	<#assign width=0 />	
                 	</#if>
                 	<#if order.statusTotal == '0141' || order.statusTotal =='0140'> 
                 	   <select name="tblackType" id="tblackType">
	                 	     <#list blackList as back>
							    <option value="${back.code}">
									${back.desc}
								</option>
							 </#list>
					   </select>
                 	<button type="button" onclick="addBlacklist();"> 加入黑名单</button>
                 	<#assign width=0 />		
                 	</#if>
                 	
                 </li>
                 <div style="clear:both;"/>
                 <li style="float:left;margin-top:30px;margin-left:0px;">
				<ul>
					<li style="float:left;margin-left:-1px;margin-top:13px;">
					<!--111-->
			<div id="accordion" style="width: 305px;">
			
			  <h2>主订单信息</h2>
			  <div  style="display:block;overflow: hidden ">
				<table class="inputTable tabContent">
					<!--
					<tr>
						<th width="40%">主订单号:</th>
						<td width="60%">${(orderSub.orderNo)!}</td>
					</tr>
					-->
					<tr>
						<th width="40%">订单号:</th>
						<td width="60%">${(orderSub.orderSubNo)!}</td>
					</tr>
					<tr>
						<th width="40%">商家编号:</th>
						<td width="60%">${(order.merchantNo)!}</td>
					</tr>
					 <tr>
						<th>关联换货单号</th>
						<td><a href="sale_after_order/refund!view.action?orderNo=${(order.orderRelatedOrigin)!}" title="逆向订单详情" target="_blank">${(order.orderRelatedOrigin)!}<#if (order.orderRelatedOrigin)??>01</#if></a></td>
					</tr>
					 <tr>
						<th>订单外部号</th>
						<td><span style="display:block;width:130px;word-wrap:break-word;">${(order.aliasOrderNo)!}</span></td>
					</tr>
					<tr>
						<th>会员:</th>
						<td>
							<span style="display:block;width:130px;word-wrap:break-word;">
							<!-- 如果会员账号存在且不为空，则显示会员账号，否则显示会员编号 -->
							<#if (order.customerName)?? && (order.customerName?trim != '')>
							${order.customerName}
							<#else>
							${order.memberNo}
							</#if>
							</span>
						</td>
					</tr>
					<tr>
						<th>下单时间:</th>
						<td>${(order.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}</td>
					</tr>
					<tr>
						<th>订单类型:</th>
						<td>
						 <#if orderSub.billType == '1'>正向订单 
						 <#elseif orderSub.billType == '-1'>逆向订单
						 <#else> 正向订单  </#if>
						</td>
					</tr>
					<tr>
						<th>订单来源:</th>
						<td>
						  <#list orderSourceList as ds>
						       <#if ds.dicCode== order.orderSource>
						      		 ${ds.dicName}
						       </#if>
							</#list>
							<#if order.orderCategory == 'chgOut'>
								<b>[换货订单]</b>
							</#if>
						</td>
					</tr>
					<tr>
						<th>商家类型:</th>
						<td>
						  <#if order.merchantType == "bbc">商家</#if>
							<#if order.merchantType == "invoiceOrg">云店</#if>
							<#if order.merchantType == "client">供应商</#if>
							<#if order.merchantType == "platform">网天</#if>
						</td>
					</tr>
					<tr>
						<th>是否需要发票:</th>
						<td>
							<select name="order.needInvoice" id="needInvoice" onchange="isNeedInvoice()">
								<option value="" <#if order.needInvoice == ''>selected="selected" </#if>>
									请选择
								</option>
								<option value="0" <#if order.needInvoice == '0'>selected="selected" </#if>>
									不需要
								</option>
								<option value="1" <#if order.needInvoice == '1'>selected="selected" </#if>>
									需要
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>是否开具发票:</th>
						<td>
							<select name="orderSub.invoicePrinted" id="invoicePrinted">
								<option value="" <#if orderSub.invoicePrinted == ''>selected="selected" </#if>>
									请选择
								</option>
								<option value="0" <#if orderSub.invoicePrinted == '0'>selected="selected" </#if>>
									未开发票
								</option>
								<option value="1" <#if orderSub.invoicePrinted == '1'>selected="selected" </#if>>
									已开发票
								</option>
							</select>
							<!--
							 <#if orderSub.invoicePrinted == '0'>未开发票
							 <#elseif orderSub.invoicePrinted == '1'>已开发票
							 <#else>未开发票
							 </#if>
							 -->
						</td>
				     </tr>
				     <tr>
						<th>发票号码:</th>
						<td>
							<!--<span id="invoiceNumSpan" style="<#if order.needInvoice != '1'>display:none;</#if> width:120px">${(orderInvoice.invoiceNum)!}</span>-->
							<input type="input" id="invoiceNum" style="<#if order.needInvoice != '1'>display:none;</#if> width:120px" name="orderInvoice.invoiceNum" value="${orderInvoice.invoiceNum!}"/>
							<span id="invoiceNumHidden" style="display:none;">${orderInvoice.invoiceNum!}</span>
						</td>
					</tr>
					<tr>
						<th>发票台头:</th>
						<td>
						<!--<span id="invoiceHeadSpan" style="<#if order.needInvoice == '1'>display:none;</#if> width:120px"></span>-->
						<input type="text" id="invoiceHead" style="<#if order.needInvoice != '1'>display:none;</#if> width:120px" name="orderInvoice.invoiceHead" value="${orderInvoice.invoiceHead!}"/>
						<span id="invoiceHeadHidden" style="display:none;">${orderInvoice.invoiceHead!}</span>
						</td>
					</tr>
					<tr>
						<th>发票内容:</th>
						<td>
							<!--<span id="invoiceContentSpan" style="<#if order.needInvoice == '1'>display:none;</#if> width:120px"></span>-->
							<input type="text" id="invoiceContent" style="<#if order.needInvoice != '1'>display:none;</#if> width:120px" name="orderInvoice.invoiceContent" value="${orderInvoice.invoiceContent!}"/>
							<span id="invoiceContentHidden" style="display:none;">${orderInvoice.invoiceContent!}</span>
						</td>
					</tr>
					<tr>
						<th>是否显示金额:</th>
						<td>
						 <#if order.ifShowPrice == '0'>不显示
						 <#elseif order.ifShowPrice == '1'>显示
						 <#else>不显示</#if>
						</td>
					</tr>
					<tr>
						<th>订单商品总重:</th>
						<td>${(order.weight)!'0'}kg</td>
					</tr>
					<tr>
						<th>下单IP:</th>
						<td>${(order.ip)!}</td>
					</tr>
					<tr>
						<th>验证码:</th>
						<td>${(orderSub.checkCode)!}</td>
					</tr>
				</table>
				</div>
				</div>
					
				<div id="accordion01" style="width: 305px; margin-top:0px;">
				
		<h2>配送信息</h2>
				<div style="display:block;overflow: hidden;height: 350px;margin-bottom: 10px;">
				<table class="inputTable tabContent">
				<tr>
					<th width="30%">配送方式:</th>
					<td  width="70%">
						<#list distributeTypeList as distributeType>
						    <#if distributeType.code==orderSub.distributeType>
								${distributeType.name} 
							 </#if>		
						 </#list>
					</td>
				</tr>
				<tr>
					<th>自提门店:</th>
					<td>
						<#list oneSelfTakePointList as point>
							${(point.pointName)!} 
						 </#list>	
					</td>
				</tr>
				<tr>
					<th>配送商:</th>
					<td>${(orderSub.deliveryMerchantName)!}	</td>
				</tr>
				<tr>
					<th>配送地区:</th>
					<td>${orderSub.combinedAddress}${(orderSub.addressDetail)!}</td>
				</tr>
				<tr>
					<th>配送要求:</th>
					<td>${(orderSub.deliveryRemark)!}</td>
				</tr>
				
				<tr>
					<th>自提时间:</th>
					<td>
						<input type="text" readonly="true"  onclick="WdatePicker()" name="orderSub.pickTime" value="${(orderSub.pickTime?string("yyyy-MM-dd"))!}" >
					</td>
				</tr>
				<tr>
					<th>配送时间:</th>
					<td>
					  <select name="order.deliveryDateFlag" style="width: 150px">
					   <#list deliveryDateFlagList as deliveryDateFlag>
							    <option value="${(deliveryDateFlag.code)!}" <#if deliveryDateFlag.code==order.deliveryDateFlag>selected</#if>>
									${deliveryDateFlag.name} 
								 </option> 
							 </#list>
					
						</select>
					</td>
				</tr>
				<tr>
					<th>运单号:</th>
					<td><span style="/*border:1px solid red;*/width:157px;display:block;word-wrap:break-word;">${(orderSub.logisticsOutNo)!}</span></td>
				</tr>
			</table>
			</div>
			</div>
			</li>
			
		 <li style="float:left;margin-left:2px;margin-top:13px;">
		 <div id="accordion1" style="width: 305px;">
		 <h2>收货人信息</h2>
			  <div  style="display:block;overflow: hidden;">
				<table class="inputTable tabContent">
					<tr>
						<th width="30%">姓名:</th>
						<td width="70%">
						<input type="text"  name="orderSub.userName" value="${(orderSub.userName)!}"/>		
						</td>
					</tr>
					<tr>
						<th>手机:</th>
						<td><input type="text"  name="orderSub.mobPhoneNum" value="${(orderSub.mobPhoneNum)!}"/></td>
					</tr>
					<tr>
						<th>电话:</th>
						<td><input type="text"  name="orderSub.phoneNum" value="${(orderSub.phoneNum)!}"/></td>
					</tr>
					<tr>
						<th>地址:</th>
						<td><textarea  cols="20"  name="orderSub.addressDetail">${(orderSub.addressDetail)!}</textarea></td>
					</tr>
					<tr>
						<th>邮编:</th>
						<td><input type="text"  name="orderSub.postCode" value="${(orderSub.postCode)!}"/></td>
					</tr>
					
				</table>
				</div>
				</div>
				<div id="accordion11" style="width: 305px;">
				
		 <h2>客服备注</h2>
			  <div style="display:block;">
				<table class="inputTable tabContent">
					<tr>
						<td>
							<span>
								<input type="radio" id="flag1" name="order.clientServiceRemarkFlag" <#if order.clientServiceRemarkFlag == 1>checked</#if> value="1" style="width:15px;border:2px;">
								<img src="${webuiPath}/images/op_memo_red.png">
								<input type="radio" id="flag2" name="order.clientServiceRemarkFlag" <#if order.clientServiceRemarkFlag == 2>checked</#if> value="2" style="width:15px;border:2px;">
								<img src="${webuiPath}/images/op_memo_yellow.png">
								<input type="radio" id="flag3" name="order.clientServiceRemarkFlag" <#if order.clientServiceRemarkFlag == 3>checked</#if> value="3" style="width:15px;border:2px;">
								<img src="${webuiPath}/images/op_memo_green.png">
								<input type="radio" id="flag4" name="order.clientServiceRemarkFlag" <#if order.clientServiceRemarkFlag == 4>checked</#if> value="4" style="width:15px;border:2px;">
								<img src="${webuiPath}/images/op_memo_blue.png">
								<input type="radio" id="flag5" name="order.clientServiceRemarkFlag" <#if order.clientServiceRemarkFlag == 5>checked</#if> value="5" style="width:15px;border:2px;">
								<img src="${webuiPath}/images/op_memo_purple.png">
							</span>
						</td>
					</tr>
					<tr>
						<td width="100%" style="padding:0">
							<textarea name="order.clientServiceRemark" rows="5" style="overflow: auto ;width:98%;height:100%  padding: 0; border-width: 0px; margin-bottom: 0px; margin-top: 0px;">${(order.clientServiceRemark)!}</textarea>
						</td>
					</tr>
				</table>
				</div>
			</div>
			<div id="accordion12" style="width: 305px;">
		<h2>顾客备注</h2>
			  <div style="display:block;overflow: hidden ">
				<table class="inputTable tabContent">
					<tr>
						<td width="100%" style="padding:0">
							<textarea rows="5" name="order.clientRemark" disabled style="overflow: auto ;width:99%;height:100%  padding: 0px; border-width: 0px; margin-bottom: 0px; margin-top: 0px;">${(order.clientRemark)!}</textarea>
						</td>
					</tr>
				</table>
			  </div>
			</div>
					</li>
					<li style="float:left;margin-left:2px;margin-top:13px;">
					<!--333-->
					<div id="accordion2" style="width: 400px;">
	    <h2>订单状态</h2>
			  <div style="display:block;overflow: hidden;">
				<table class="inputTable tabContent">
				   <#if order.statusTotal??>
					<tr>
				    	<th width="40%">处理状态:</th>
						<td width="60%">
					    	<#list orderStatusList as orderStatus>
							    <#if orderStatus.statusCode==order.statusTotal>
									${orderStatus.displayName} <#--${order.statusTotal}--> 
								 </#if>		
							 </#list>
						</td>
					</tr>
					 </#if>
					  <#if orderSub.logisticsStatus??>
					<tr>
				    	<th width="40%">物流状态:</th>
						<td width="60%">
					    	<#list logisticsStatusList as logisticsStatus>
							    <#if logisticsStatus.statusCode==orderSub.logisticsStatus>
									${logisticsStatus.displayName} 
								 </#if>		
							 </#list>
						</td>
					</tr>
					 </#if>
					  <#if order.statusConfirm??>
					<tr>
				    	<th width="40%">审核状态:</th>
						<td width="60%">
					    	<#list statusConfirmList as statusConfirm>
							    <#if statusConfirm.statusCode==order.statusConfirm>
									${statusConfirm.displayName} 
								 </#if>		
							 </#list>
						</td>
					</tr>
					 </#if>
					  <#if order.statusPay??>
					<tr>
				    	<th width="40%">订单支付状态:</th>
						<td width="60%">
						<#list orderStatusList as statusPay>
							    <#if statusPay.statusCode==order.statusPay>
									${statusPay.displayName} 
								 </#if>		
							 </#list>
						</td>
					</tr>
					 </#if>
				</table>
				</div>
				</div>
				<div id="accordion21" style="width: 400px;">
		 <h2>订单总价</h2>
			  <div>
				<table class="inputTable tabContent">
					<tr>
				    	<th style="text-align:left;">名称</th>
						<th style="text-align:left;">金额</th>
					</tr>
					<tr>
					    <td>运费:</td>
						<td>${((order.transportFee)!0)?string.currency}</td>
					</tr>
					<tr>
					    <td>商品金额</td>
						<td>${((order.totalProductPrice)!0)?string.currency}</td>
					</tr>
					<tr>
					    <td>优惠金额</td>
						<td>${((order.discountTotal)!0)?string.currency}</td>
					</tr>
					<tr>
					    <td>人民币总计</td>
						<td>${((order.totalPayAmount)!0)?string.currency}<input type="hidden" id="totalPayAmountTmp" value="${(order.totalPayAmount)!0}"/></td>
					</tr>
					<tr>
					    <td>赠送积分</td>
						<td>${((order.totalGivePoints)!0)?string.currency}</td>
					</tr>
				</table>
				</div>
				</div>
				<div id="accordion22" >
				
			 <h2>支付明细</h2>
			  <div>
				<table id="paymentTab"  class="inputTable tabContent" style="width:500px;">
					<#assign ifHasPay="1" /><!--是否已支付-->
					<#if order.statusPay == '0450' || order.statusPay == '0470' || order.statusPay == '0410'  || order.statusPay=='0430'>
						<#assign ifHasPay="0" />
					</#if>
					<!--<#if order.statusPay == '0450' && order.orderCategory=='sale' >
						<tr>
					    	<td colspan="4"><input type="button" value="添加记录" onclick="addPayment();"/></td>
						</tr>
					</#if>-->
					<tr>
				    	<th style="text-align:left;padding-left:10px">支付方式</th>
						<th style="text-align:left;padding-left:10px">金额</th>
						<th style="text-align:left;padding-left:10px">银行流水</th>
						<th style="text-align:left;padding-left:10px">状态</th>
						<#if ifHasPay='0'>
							<th style="text-align:left;padding-left:10px">操作</th>
						</#if>
					</tr>

					<!--order_pay start-->
					<#if order.orderPays?exists>
						<#list order.orderPays as orderPay>
							<#assign hidden=0 />
							<#if orderPay.isDeleted!=1>
							<tr>
						    	<td>
								<input type="hidden" name="ids" value="${(orderPay.id)!}"/> 
									${orderPay.payName}
									<input type="hidden" id="payCodeTmp" value="${orderPay.payCode}"/>
								</td>
								<td>
									${((orderPay.payAmount)!0)?string.currency}
								   <input type="hidden" style="width: 60px" id="payAmountTmp"  name="payAmount"  value="${(orderPay.payAmount)!}" />
								</td>
								<td>
								   <input type="text" style="width: 100px"  name="serialNo"  value="${(orderPay.serialNo)!}" />
								</td>
								<td><#if order.statusPay=='0430'>支付取消<#else>已支付</#if></td>
								<td>&nbsp;</td>
							</tr>
							</#if>
						</#list>
					</#if>
					<!--order_pay End-->
						
					<!--order_pay_mode Start-->
					<#if order.statusPay=='0410'>
						<!--在线支付未支付 start-->
						<#if order.orderPayModes?exists>
							<#list order.orderPayModes as orderPay>
								<#assign hidden=0 />
								<#if orderPay.isDeleted!=1 && orderPay.payStatus != '1'>
								<tr>
							    	<td style="width: 100px">
									<#if '830' != orderPay.payModeCode>
										${orderPay.payModeName}
									<#else>
										<select name="payCode" id="payCode_select"  onchange="checkPayCode(this)" >
											<#list paymentMethodList as pmList>
												<option value="${pmList.id}" <#if pmList.id == '830'>selected</#if>>
													<#if pmList.id == ''>请选择
													<#else>${pmList.name}</#if>
												</option>
											</#list>
										</select>
									</#if>
									</td>
									<td style="width: 50px">
										${((orderPay.payAmount)!0)?string.currency}
										<input type="hidden"  name="payAmount" value="${(orderPay.payAmount)!}" />
									</td>
									<td style="width:50px;">
										<#if '830' != orderPay.payModeCode>
										${orderPay.serialNo}
										<#else>
										   <input type="text" width="50px"  name="serialNo" value="${(orderPay.serialNo)!}" />
										</#if>
									</td>
									<td>未支付</td>
									<td style="width: 50px">
										<!--<input type="button" value="删除" onclick="deletePayment(this,'${orderPay.id}');" <#if (orderPay.payCode != '830') && (orderPay.payCode != '')>disabled</#if>/>-->
										<input id="save_select" type="button" value="保存" onclick="savePayment(this,'${orderPay.id}');" <#if ('830' != orderPay.payModeCode) && (orderPay.payCode != '830') && (orderPay.payCode != '')>disabled</#if>/>
									</td>
							  </tr>
							  </#if>
						  </#list>
					  </#if>
					  <!--在线支付未支付 end-->
					  
					<#elseif order.statusPay=='0450' || order.statusPay=='0470' || order.statusPay=='0430'>
					    <!--货到付款未支付 Start--> 
						<#if order.orderPayModes?exists>	
						  <#list order.orderPayModes as orderPay>
								<#assign hidden=0 />
								<#if orderPay.isDeleted!=1 && orderPay.payStatus != '1'>
								
								<!-- 用来标识是否需要保存，no:不需要,yes:需要 -->
								<#assign isNeedSave="no">
								<tr>
							    	<td style="width: 100px">
									<#if '830' != orderPay.payModeCode>
										${orderPay.payModeName}
									<#else>
										<select name="payCode" id="payCode_select"  onchange="checkPayCode(this)" <#if order.statusPay=='0430'>disabled</#if>>
											<#list paymentMethodList as pmList>
												<option value="${pmList.id}" <#if pmList.id == '830'>selected</#if>>
													<#if pmList.id == ''>请选择
													<#else>${pmList.name}</#if>
												</option>
											</#list>
										</select>
										<#assign isNeedSave="yes">
									</#if>
									</td>
									<td style="width: 100px">
										${((orderPay.payAmount)!0)?string.currency}
										<input type="hidden"  name="payAmount" value="${(orderPay.payAmount)!}" />
									</td>
									<td style="width: 100px">
										 <input type="text"  name="serialNo" value="${(orderPay.serialNo)!}" />
									</td>
									<td><#if order.statusPay=='0430'>支付取消<#else>未支付</#if></td>
									<td style="width: 50px">
										<!--<input type="button" value="删除" onclick="deletePayment(this,'${orderPay.id}');" <#if (orderPay.payCode != '830') && (orderPay.payCode != '')>disabled</#if>/>-->
										<input id="save_select" type="button" value="保存" onclick="savePayment(this,'${orderPay.id}');" <#if (('830' != orderPay.payModeCode) && (orderPay.payCode != '830') && (orderPay.payCode != '')) || isNeedSave== "no" || order.statusPay=='0430'>disabled</#if>/>
									</td>
							  </tr>
							  </#if>
						  </#list>
						</#if>
					    <!--货到付款未支付 End-->  
					</#if>
					<!--order_pay_mode End-->
					
				</table>
				</div>
				</div>
				
				</li>
				</ul>
				
			</li>
			</ul>
			
            </div>

         </form>	
            <div id="orderSubDiv" style="display:none">
				  <#include "/WEB-INF/template/admin/order/order_sub_detail.ftl">
			</div>
			 <div id="purchaseRecordsDiv" style="display:none">

				 <iframe id="travelExpenseFrame" src="order!history.action?memberNo=${(order.memberNo)!}&time=New Date()"  height='800px' width="1600px" frameborder=0 marginwidth=0 marginheight=0 > </iframe>
			</div>
			 <div id="orderOperateLogDiv" style="display:none">

				 <iframe id="orderOperateLogFrame" src="maintenance/order_log_query.action?log.orderSubNo=${(orderSub.orderSubNo)!}&time=New Date()"  height='800px' width="1600px" frameborder=0 marginwidth=0 marginheight=0 > </iframe>
			</div>
			<div id="orderMainStatusLogDiv" style="display:none">

				 <iframe id="orderMainStatusLog" src="maintenance/order_main_status_log_query.action?log.orderNo=${(order.orderNo)!}&time=New Date()"  height='800px' width="1600px" frameborder=0 marginwidth=0 marginheight=0 > </iframe>
			</div>
			<div id="orderStatusLogDiv" style="display:none">

				 <iframe id="orderStatusLog" src="maintenance/order_status_log_query.action?log.orderNo=${(order.orderNo)!}&time=New Date()"  height='800px' width="1600px" frameborder=0 marginwidth=0 marginheight=0 > </iframe>
			</div>
			<div id="orderThirdStatusLogDiv" style="display:none">

				 <iframe id="orderThirdStatusLog" src="maintenance/order_third_status_log_query.action?log.orderNo=${(order.orderNo)!}&time=New Date()"  height='800px' width="1600px" frameborder=0 marginwidth=0 marginheight=0 > </iframe>
			</div>
			</div>
			
			<!--
				<div class="buttonArea" style="width: 305px; margin-left:1000px;margin-top:-340px;">
					
					<input type="button" id="11" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
				</div>
			-->
		<form id="finishForm" action="order!finishOrder.action" method="post">
		    <input type="hidden" name="ids" value="${(order.orderNo)!}" />
			<input type="hidden" name="order.id" value="${(order.id)!}" />
			<input type="hidden" name="order.orderNo" value="${(order.orderNo)!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${(orderSub.orderSubNo)!}" />
			<input type="hidden" name="column" value="${(column)!}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
		</form>
		<form id="cancleForm" action="order!cancelOrder.action" method="post">
		    <input type="hidden" name="ids" value="${(order.orderNo)!}" />
			<input type="hidden" name="order.id" value="${(order.id)!}" />
			<input type="hidden" name="order.orderNo" value="${(order.orderNo)!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${(orderSub.orderSubNo)!}" />
			<input type="hidden" name="column" value="${(column)!}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" id="cancelReasonNoCancelForm" name="order.cancelReasonNo" value="${order.cancelReasonNo}" />
		</form>
		<form id="approveForm" action="order!approveOrder.action" method="post">
		    <input type="hidden" name="column" value="${(column)!}" />
			<input type="hidden" name="order.id" value="${(order.id)!}" />
			<input type="hidden" name="order.orderNo" value="${(order.orderNo)!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${(orderSub.orderSubNo)!}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
		</form>
		
		<form id="updatePayForm" action="order!updateOrderPayment.action" method="post">
			<input type="hidden" name="order.id" value="${(order.id)!}" />
			<input type="hidden" name="order.orderNo" value="${(order.orderNo)!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${(orderSub.orderSubNo)!}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="column" value="${(column)!}" />
			<input type="hidden" name="ids" id="ids"/>
			<input type="hidden" name="payCode"  id="payCode"/>
			<input type="hidden" name="payAmount"  id="payAmount" />
			<input type="hidden" name="serialNo" id="serialNo"/>
		</form>
		<form id="deletePayForm" action="order!deleteOrderPayment.action" method="post">
		    <input type="hidden" name="column" value="${(column)!}" />
			<input type="hidden" name="order.id" value="${(order.id)!}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="order.orderNo" value="${(order.orderNo)!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${(orderSub.orderSubNo)!}" />
			<input type="hidden" name="payId" id="payId"/>
		</form>
		
		<form id="addBlacklistForm" action="order!addBlacklist.action" method="post">
		    <input type="hidden" name="column" value="${(column)!}" />
			<input type="hidden" name="order.id" value="${(order.id)!}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id}" />
			<input type="hidden" name="order.orderNo" value="${(order.orderNo)!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${(orderSub.orderSubNo)!}" />
			<input type="hidden" name="blackType" id="blackType" value="" />
		</form>

	</div>
		
</body>

</html>