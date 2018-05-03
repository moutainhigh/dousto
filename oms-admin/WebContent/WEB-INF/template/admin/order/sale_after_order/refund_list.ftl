<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<#-- security.tld 角色权限控制 -->
<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>逆向单列表</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
    
    <script src="${base}/scripts/list.js"></script>
    <!-- 按“Enter”键，搜索 -->
    <script src="${base}/scripts/list_search.js"></script>
    <script src="${base}/scripts/refund/refundList.js"></script>    
    
    <script src="${base}/resources/admin/js/ui/jquery.ui.core.js"></script>
	<script src="${base}/resources/admin/js/ui/jquery.ui.widget.js"></script>
	<script src="${base}/resources/admin/js/ui/jquery.ui.accordion.js"></script>
    <script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>


<script type="text/javascript">

$().ready( function() {
	// 折叠显示
	$( "#accordion" ).accordion({
		collapsible: true
	});

	$('#updateOrder').click(function(){
	        alert("updateOrder");
	});
	
	 $("td[name='display']").each(function(i, o){
	    
	    if ($(o).text() == null || $(o).text()==""){
		     
	         //alert($(o).text());
         }else{
            code = $(o).text();
            $.getJSON("refund_desc!getDescription.action?code="+code, function(data){ 
	             desc = data.desc;
	             $(o).text(desc);
	         });
         }
	 });
	 
	// 搜索条件图标切换
 	$("#searchCondition").toggle(
	  function(){
	  $("#searchConditionIcon").removeClass("displaySearchCondition").addClass("hideSearchCondition");
	  },
	  function(){
	  $("#searchConditionIcon").removeClass("hideSearchCondition").addClass("displaySearchCondition");
	  }
	);
	
});

function orderSearch(){
	var compare = new Date($("#orderTimeFrom").val()) > new Date($("#orderTimeTo").val());
	if(compare)	
	{
		alert("下单开始日期不能大于结束日期");
		return;
	}
	
	var form = document.getElementById("listForm");
	$('#pageNumber').val(0);
	form.submit();
}

function downExcel(){
	if(${pager.totalCount} > 500)
	{
		alert("导出记录数不能超过500条");
		return;
	}
    var form1 = document.getElementById("excelForm");
   form1.action="../order_excel.action";
	form1.submit();
}

function orderConfirm(){
	var count = 0;
    $("[name='ids']").each(function(){
//      if($(this).attr("checked"))
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
	
	var confirmed = false;
	var index = 0;
	var conOrder = "";
	$("[name='ids']").each(function(){
//      if($(this).attr("checked")) 
      if(this.checked)
	  {   
	   	if($("#statusConfirmCode_"+index).val() == "0807")
		{
			conOrder += $(this).val() + ",";
			confirmed = true;
		}
	  }   
	  index++;  
	});
	if(confirmed)
	{
		alert("订单" + conOrder.substring(0,conOrder.length-1) + "已审核，不需再审!");
		return;
	}
	
	var as = []; 
	
	
	var index2 = 0;
	var notConOrder = "";
	var noNeedConfirmed = false;
	var yundianNotConOrder = "";
	var yundianNoNeedConfirmed = false;
	
	$("[name='ids']").each(function(){
//      if($(this).attr("checked"))
      if(this.checked)     
	  {   
	  //alert($("#statusTotalCode_"+index2).val());
	   	if($("#statusTotalCode_"+index2).val() != "0220")
		{
			notConOrder += $(this).val() + ",";
			noNeedConfirmed = true;
		}
		//if($("#merchantType_"+index2).val() == "invoiceOrg")
		//{
			//yundianNotConOrder += $(this).val() + ",";
			//yundianNoNeedConfirmed = true;
		//}
		as.push($(this).val());
	  }
	  index2++;
	});
	if(noNeedConfirmed)
	{
		alert("逆向订单" + notConOrder.substring(0,notConOrder.length-1) + "订单不满足审核条件, 订单状态必须为0220退货审核中且审核状态不能为0807审核已通过");
		return;
	}
	
	var params = {
			ids:as
	};	
	
	var form = document.getElementById("listForm");
	form.action="refund!search.action";
	if($(this)[0].confirm('确定审核订单吗?'))
	{
		//form.submit();
		$.ajax({
			type:"POST",
			url:"refund!orderAudit.action",
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
}

function orderUnConfirm(){
	var count = 0;
    $("[name='ids']").each(function(){  
      if(this.checked)   
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要审核不通过的订单");
		return;
	}
	
	var canceled = false;
	var index = 0;
	var canOrder = "";
	$("[name='ids']").each(function(){ 
      if(this.checked)    
	  {   
	   	if($("#statusTotalCode_"+index).val() == "0221")
		{
			canOrder += $(this).val() + ",";
			canceled = true;
		}
	  }   
	  index++;  
	});
	if(canceled)
	{
		alert("订单状态" + canOrder.substring(0,canOrder.length-1) + "已审核不通过");
		return;
	}
	
	var as = []; 
	var noNeedConfirmed = false;
	var index2 = 0;
	var notCanOrder = "";
	$("[name='ids']").each(function(){
      //if($(this).attr("checked")) 
      if(this.checked)    
	  {  
	   	if($("#statusTotalCode_"+index2).val() != "0220")
		{
			notCanOrder += $(this).val() + ",";
			noNeedConfirmed = true;
		}
		as.push($(this).val()); 
	  }   
	  index2++;  
	});
	if(noNeedConfirmed)
	{
		alert("逆向订单" + notCanOrder.substring(0,notCanOrder.length-1) + "订单不满足审核条件, 订单状态必须为0220退货审核中且审核状态为未审核");
		return;
	}
	
	var params = {
			ids:as
	};	
	
	var form = document.getElementById("listForm");
	form.action="refund!search.action";
	if($(this)[0].confirm('确定订单审核不通过吗?'))
	{
		$.ajax({
			type:"POST",
			url:"refund!orderUnConfirm.action",
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

function cancelConfirm(){
	var count = 0;
    $("[name='ids']").each(function(){ 
      if(this.checked)   
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要取消确认的订单");
		return;
	}
	
	var canceled = false;
	var index = 0;
	var canOrder = "";
	$("[name='ids']").each(function(){ 
      if(this.checked)    
	  {   
	   	if($("#statusTotalCode_"+index).val() == "0220")
		{
			canOrder += $(this).val() + ",";
			canceled = true;
		}
	  }   
	  index++;  
	});
	if(canceled)
	{
		alert("订单" + canOrder.substring(0,canOrder.length-1) + "确认已取消");
		return;
	}
	
	var as = [];
	var noNeedConfirmed = false;
	var index2 = 0;
	var notCanOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)    
	  {  
//	  	alert($("#statusTotalCode_"+index2).val());
	   	if($("#statusTotalCode_"+index2).val() != "0240" || $("#statusTotalCode_"+index2).val() != "0221") 
		{
			notCanOrder += $(this).val() + ",";
			noNeedConfirmed = true;
		}
		as.push($(this).val()); 
	  }   
	  index2++;  
	});
	if(noNeedConfirmed)
	{
		alert("订单" + notCanOrder.substring(0,notCanOrder.length-1) + "不满足取消确认条件");
		return;
	}
	
	var params = {
			ids:as
	};	
	
	var form = document.getElementById("listForm");
	form.action="refund!search.action";
	if($(this)[0].confirm('订单确定取消确认吗?'))
	{
		$.ajax({
			type:"POST",
			url:"refund!orderCancelConfirm.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    //window.location.reload(force=true) 	
				    //window.location.href="refund.action?column=-1";	
				    form.submit();
				}else{
				   alert(data.message);
				   //window.location.href=window.location.href; 
				   //window.location.reload(force=true) 
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
}

function fast(){
	var count = 0;
    $("[name='ids']").each(function(){
      if(this.checked)   
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要快速入库的订单");
		return;
	}
	
	var canceled = false;
	var index = 0;
	var canOrder = "";
	$("[name='ids']").each(function(){ 
      if(this.checked)    
	  {   
	   	if($("#statusTotalCode_"+index).val() == "0270" || $("#statusTotalCode_"+index).val() == "0280")
		{
			canOrder += $(this).val() + ",";
			canceled = true;
		}
	  }   
	  index++;  
	});
	if(canceled)
	{
		alert("订单" + canOrder.substring(0,canOrder.length-1) + "已入库");
		return;
	}
	
	var as = []; 
	var noNeedConfirmed = false;
	var index2 = 0;
	var notCanOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)    
	  {
//	   	alert($("#statusTotalCode_"+index2).val());
	   	//if($("#statusTotalCode_"+index2).val() == "0241" || $("#statusTotalCode_"+index2).val() == "0260" || $("#statusTotalCode_"+index2).val() == "0261" || $("#statusTotalCode_"+index2).val() == "0220" || $("#statusTotalCode_"+index2).val() == "0221" || $("#statusTotalCode_"+index2).val() == "0210")
		if($("#statusTotalCode_"+index2).val() != "0231" && $("#statusTotalCode_"+index2).val() != "0240" && $("#statusTotalCode_"+index2).val() != "0250")
		{
			notCanOrder += $(this).val() + ",";
			noNeedConfirmed = true;
		}
		as.push($(this).val()); 
	  }   
	  index2++;  
	});
	if(noNeedConfirmed)
	{
		alert("订单" + notCanOrder.substring(0,notCanOrder.length-1) + "不满足快速入库条件");
		return;
	}
	
	var params = {
			ids:as
	};	
	
	var form = document.getElementById("listForm");
	form.action="refund!search.action";
	if($(this)[0].confirm('确定订单快速入库吗?'))
	{
		$.ajax({
			type:"POST",
			url:"refund!orderFastStorage.action",
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

function orderCancel(){
	var count = 0;
    $("[name='ids']").each(function(){
      //if($(this).attr("checked"))  
      if(this.checked)   
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要置无效的订单");
		return;
	}
	
	var canceled = false;
	var index = 0;
	var canOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)    
	  {   
	   	if($("#statusTotalCode_"+index).val() == "0279")
		{
			canOrder += $(this).val() + ",";
			canceled = true;
		}
	  }   
	  index++;
	});
	if(canceled)
	{
		alert("订单" + canOrder.substring(0,canOrder.length-1) + "已置无效");
		return;
	}
	
	var as = []; 
	var noNeedConfirmed = false;
	var index2 = 0;
	var notCanOrder = "";
	$("[name='ids']").each(function(){
      if(this.checked)    
	  {  
	   	if($("#statusTotalCode_"+index2).val() == "0270" || $("#statusTotalCode_"+index2).val() == "0280")
		{
			notCanOrder += $(this).val() + ",";
			noNeedConfirmed = true;
		}
		as.push($(this).val());
	  }   
	  index2++;  
	});
	if(noNeedConfirmed)
	{
		alert("订单" + notCanOrder.substring(0,notCanOrder.length-1) + "不满足置无效条件");
		return;
	}
	
	var params = {
			ids:as
	};	
	
	var form = document.getElementById("listForm");
	form.action="refund!search.action";
	if($(this)[0].confirm('订单确定置无效吗?'))
	{
		//form.submit();
		$.ajax({
			type:"POST",
			url:"refund!orderCancel.action",
			data:params,
			dataType : "json",
			success:function(data){
				if(data.status == "success"){
				    alert(data.message); 
				    //window.location.reload(force=true) 	
				    //window.location.href="refund.action?column=-1";	
				    form.submit();
				}else{
				   alert(data.message);
				   //window.location.href=window.location.href; 
				   //window.location.reload(force=true) 
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
}
</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>逆向订单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="refund!search.action" method="post" >
	    	<input type="hidden" name="column" value="${column}" />
	    	<div id="MainTable">
			<div class="input">
				<input id="status" type="hidden" name="pager.queryMap.status" value="" >
			</div>		
			<div class="operateBar">
				<label>每页显示:</label><input type="text" id="pageSize" name="pager.pageSize" value="${pager.pageSize!}" />				
			</div>
	    <div id="accordion">
		<h4 id="searchCondition" style="border:solid 1px #ddd;background:#eee;padding:5px">
			<span id="searchConditionIcon" class="displaySearchCondition"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搜索条件
		</h4>
		<div style="margin:0;padding:0;">	
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 100%;">
				<tr>
					<!--
					<td>订单编号：<input type="text" id="orderSubId" name="orderSub.id" value="${orderSub.id!}" /></td>
					-->
					<!--<td>主订单号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="orderNo" name="orderMain.orderNo" value="${orderMain.orderNo!}" /></td>-->
					<td>逆向单号：<input type="text" id="orderNo" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}" /></td>
					<td>关联销售单号：&nbsp;<input type="text" id="orderSubRelatedOrigin" name="orderSub.orderSubRelatedOrigin" value="${orderSub.orderSubRelatedOrigin!}" /></td>
					<td>外部渠道订单号：<input type="text" id="aliasOrderNo" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}" /></td>
					<td>换货出库单号：<input type="text" id="chgOurOrderNo" name="orderMain.chgOurOrderNo" value="${orderMain.chgOurOrderNo!}" /></td>
				</tr>
				<tr>
					<td>处理状态：<select name="orderMain.statusTotal">
						<#list statusTotalList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderMain.statusTotal> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.displayName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
					<td>审核状态：&nbsp;&nbsp;&nbsp;<select name="orderMain.statusConfirm">
						<#list statusConfirmList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderMain.statusConfirm> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.displayName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
					<td>退款状态：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderMain.statusPay">
						<#list statusPayList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderMain.statusPay> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.displayName}
								</#if>
							</option>
						</#list>
					</select>
					</td>
					<td>入库方式：&nbsp;&nbsp;&nbsp;<select name="orderSub.distributeType">
							<#list allDistributeType as list>
								<option value="${list.code}"<#if list.code == orderSub.distributeType> selected </#if>>
									<#if list.code == "">
									全部
									<#else>
										${list.desc}
									</#if>
								</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td>建单人：&nbsp;&nbsp;&nbsp;<input type="text" id="createdBy" name="orderMain.createdBy" value="${orderMain.createdBy!}" /></td>
					<td>商品关键字：<input type="text" id="commodityName" name="orderItem.commodityName" value="${orderItem.commodityName!}" />
					</td>
					<td colspan="2">下单日期：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"></td>
				</tr>
				<tr>					
					<td colspan="1">退款方式：
			      		<input type="hidden" id="orderPayCode" name="orderPay.payCode" value="${orderPay.payCode}"/>
			      		<select id="payWay" onchange="getCodPayWay()">
							<#list paymentModeList as list>
							  <#if list.code != '50300' && list.code != '307' && list.code != '50301'>
								<option value="${list.code}"<#if list.code == orderPay.payCode> selected </#if>>
									<#if list.code == "">
									全部
									<#else>
										${list.name}
									</#if>
								</option>
							  </#if>
							</#list>
						</select>
						<select id="cod" style="display:none;" onchange="changePayCode(cod)">
							<option value="1" <#if '1' == orderPay.payCode> selected </#if>>
								请选择
							</option>
							<option value="50300" <#if '50300' == orderPay.payCode> selected </#if>>
								现金
							</option>
							<option value="307" <#if '307' == orderPay.payCode> selected </#if>>
								银行卡
							</option>							
						</select>
						<select id="onlinePay" style="display:none;" onchange="changePayCode(onlinePay)">
							<#list paymentMethodList as list>
								<option value="${list.id}"<#if list.id == orderPay.payCode> selected </#if>>
									<#if list.id == "830">
									请选择
									<#else>
										${list.name}
									</#if>
								</option>
							</#list>
						</select>
					</td>
					<td>退单类型：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderMain.orderCategory">					
							<option value="" <#if "" == orderMain.orderCategory> selected </#if>>全部</option>
							<option value="ret" <#if "ret" == orderMain.orderCategory> selected </#if>>退货</option>
							<option value="chg" <#if "chg" == orderMain.orderCategory> selected </#if>>换货</option>
							<option value="rej" <#if "rej" == orderMain.orderCategory> selected </#if>>拒收</option>							
							</select>
					</td>
					<td>入库时间：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="inStockTimeFrom" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="inStockTimeTo" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"></td>
				</tr>				
				<tr>
					<td>问题描述:<input type="text" id="remark" name="orderMain.remark" value="${orderMain.remark!}" /></td>					
					<td>订单来源：<select name="orderMain.orderSource">
							<#list orderSourceList as list>
								<option value="${list.dicCode}"<#if list.dicCode == orderMain.orderSource> selected </#if>>
									<#if list.dicCode == "">
									全部
									<#else>
										${list.dicName}
									</#if>
								</option>
							</#list>		
						</select>
					</td>
					<td>配送地址：&nbsp;&nbsp;&nbsp;<#include "/WEB-INF/template/admin/order/area_linkage.ftl"></td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="button_red" name="search" id="search" onclick="orderSearch();" value="查询">
					</td>
				</tr>	
				<tr>
					<td colspan="4">
						<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="confirm" id="confirm" onclick="orderConfirm();" value="确认通过">
						<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="unConfirm" id="unConfirm" onclick="orderUnConfirm();" value="不通过">						
						<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="orderCancelConfirm" id="orderCancelConfirm" onclick="cancelConfirm();" value="取消确认">
						<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="orderFastStorage" id="orderFastStorage" onclick="fast();" value="快速入库">
						<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="cancel" id="cancel" onclick="orderCancel();" value="置无效">																									
					</td>
				</tr>	
			</table>
		</div>
		</div>	
			<br>
	
			<h4 id="searchCondition" style="border: 1px solid #e0e2e3; background-image: url(../resources/admin/images/admin_info.png);padding: 11px 0px;  width:100%; color:#58666e;" >
			<span id="searchConditionIcon" class="displaySearchCondition"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搜索结果
		</h4>
			<table class="listTable detailTable" style="border: 1px solid #CCCCCC;width:100%;">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" id="allCheck"/>
					</th>
					<th  width="115px">
						<span class="sort" name="orderNo">逆向单号</span>
					</th>
					<th  width="55px">
						<span class="sort" name="orderCategory">类型</span>
					</th>
					<th  width="135px">
						<span class="sort" name="orderSubRelatedOrigin">关联销售单号</span>
					</th>
					<th  width="60px">
						<span class="sort" name="submitDate">下单时间</span>
					</th>
					<th  width="115px">	
						<span class="sort" name="finishTime">退款完成时间</span>
					</th>
					<th  width="135px">
						<span class="sort" name="relatedFinishTime">原订单完成时间</span>
					</th>
					<th  width="145px">
						<span class="sort" name="statusConfirm">订单状态</span>
					</th>
					<th  width="200px">
						<span class="sort" name="payName">退款明细</span>
					</th>
					<!--
					<th  width="60px">
						<span  class="sort" name="ifNeedRefund">是否需退款</span>
					</th>
					-->
					<th  width="60px">
						<span  class="sort" name="createdBy">建单人</span>
					</th>
					<th  width="60px">
						<span class="sort" name="confirmerName">审核人</span>
					</th>
					<th  width="60px">
						<span class="sort" name="updatedBy">操作人</span>
					</th>
					<th  width="130px">
						<span class="sort" name="chgOurOrderNo">换货出库单号</span>
					</th>
					<th width="130px">
						<span class="sort" name="remark">问题描述</span>
					</th>
					<th width="80px">
						<span class="sort" name="orderSource">订单来源</span>
					</th>
					<th width="100px">
						<span class="sort" name="orderNo">渠道订单号</span>
					</th>
					<th width="50px">
						操作
					</th>
				</tr>
				<#list pager.list as list>
					<tr ondblclick="window.open('refund!view.action?orderNo=${list.orderNo}&column=${column}')">
						<td>
							<input type="checkbox"  name="ids" value="${(list.orderSubNo)!}" />
							<input type="hidden" id="statusTotalCode_${list_index}" value="${list.statusTotalCode}" />
							<input type="hidden" id="statusConfirmCode_${list_index}" value="${(list.statusConfirmCode)!}" />
							<input type="hidden" id="distributeType_${list_index}" value="${(list.distributeType)!}" />
							<input type="hidden" id="refundType_${list_index}" value="${(list.ifNeedRefund)!}" />
							<input type="hidden" id="merchantType_${list_index}" value="${(list.merchantType)!}" />
						</td>						
						<td>
							<font color="#008B8B"><a href="refund!view.action?orderNo=${list.orderNo}&column=${column}" title="详情" target="_blank">${list.orderSubNo}</a></font>							
							<#if (list.statusTotal == '0241')>
							<br/>
							<font color="gray"><b>[已取消]</b></font>
							</#if>
							<#if (list.statusTotal == '0279')>
							<br/>
							<font color="red"><b>[已无效]</b></font>
							</#if>								
						</td>
						<td>${orderCategoryNameMap.get(list.orderCategory)}
						</td>
						<td>
							<a href="../order!view.action?orderNo=${list.orderRelatedOrigin}&column=${column}" title="详情" target="_blank">${list.orderSubRelatedOrigin}</a>
						</td>
						<td>
							<span style="/*border:1px solid red;*/width:65px;display:block;" title="${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
						<td>
							<span style="/*border:1px solid red;*/width:65px;display:block;" title="${(list.finishTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.finishTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
						<td>
							<span style="/*border:1px solid red;*/width:65px;display:block;" title="${(list.relatedFinishTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.relatedFinishTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
						<td>
							审核状态：<font color="red">${(list.statusConfirmName)!}</font> <br/>
							退款状态：<font color="#104E8B">${list.statusPayName!}</font> <br/>					
							处理状态：<font color="#CD661D">${(list.statusTotalName)!}</font>
						</td>
						<td>
							<#list list.orderPayInfoList as list>
									<#if (list.payNo)??>
										支付号：${list.payNo}<br/>
									</#if>	
							      		${list.payName} : <font color="red">${(list.payAmount!0)?string.currency}</font> <br/>
								</#list>
						</td>
						<td>${(list.createdBy)!}</td>
						<td>${(list.confirmerName)!}</td>
						<td>${(list.updatedBy)!}</td>
						<td>
							<a href="../order!view.action?orderNo=${list.chgOurOrderNo}&column=${column}" title="详情" target="_blank">${list.chgOurOrderNo!}<#if (list.chgOurOrderNo)??>01</#if></a>
						</td>
						<td>${list.remark}</td>
						<td>
							<#if (list.orderSource == "oms")>中台</#if>
							<#if (list.orderSource == "WX")>微信商城</#if>
							<#if (list.orderSource == "LS")>浪沙</#if>
							<#if (list.orderSource == "BS")>百胜</#if>
						</td>
						<td>${list.aliasOrderNo}</td>
						<td>
						    <a href="refund!view.action?orderNo=${list.orderNo}&column=${column}" title="详情" target="_blank">[详情]</a>						  
						</td>
					</tr>
				</#list>
			</table>
			<input  type="text" id="currentStatus" name="currentStatus" value="${currentStatus}" style="display:none"></input>
			<input  type="text" id="selected" name="selected" value="${selected}" style="display:none"></input>  
			<input  type="text" id="selectedTabName" name="selectedTabName" value="${selectedTabName}" style="display:none"></input>  
			</div>
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<#include "/WEB-INF/template/admin/pager.ftl" />
				</div>
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		</form>
		<form id="excelForm" action="" method="post">
		 	<input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id!}" />
			<input type="hidden" name="orderMain.orderNo" value="${orderMain.orderNo!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}" />
			<input type="hidden" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}" />
			<input type="hidden" name="orderMain.statusConfirm" value="${orderMain.statusConfirm!}"/>
			<input type="hidden" name="orderMain.statusPay" value="${orderMain.statusPay!}"/>
			<input type="hidden" name="orderMain.statusTotal" value="${orderMain.statusTotal!}"/>
			<input type="hidden" name="orderSub.distributeType" value="${orderSub.distributeType!}"/>
			<input type="hidden" name="orderItem.commodityName" value="${orderItem.commodityName!}"/>
			<input type="hidden" name="orderMain.createdBy" value="${orderMain.createdBy!}"/>
			<input type="hidden" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}"/>
			<input type="hidden" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"/>
			<input type="hidden" name="orderMain.orderCategory" value="${orderMain.orderCategory!}"/>
			<input type="hidden" name="orderSub.orderSubRelatedOrigin" value="${orderSub.orderSubRelatedOrigin!}"/>
			<input type="hidden" name="distributeAddress.state" value="${distributeAddress.state!}"/>
			<input type="hidden" name="distributeAddress.city" value="${distributeAddress.city!}"/>
			<input type="hidden" name="distributeAddress.county" value="${distributeAddress.county!}"/>
		</form>
	</div>
</body>
</html>