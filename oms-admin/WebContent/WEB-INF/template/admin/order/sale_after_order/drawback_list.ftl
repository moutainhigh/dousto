<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<#-- security.tld 角色权限控制 -->
<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单列表 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
    
    <script src="${base}/scripts/list.js"></script>  
    <!-- 按“Enter”键，搜索 -->
    <script src="${base}/scripts/list_search.js"></script>
    
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
	
	// 支付方式选择  defind in include_order_list_common.ftl
	selectPayCode();
	
});

<#include "/WEB-INF/template/admin/order/include_order_list_common.ftl">

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
	
	var confirmed = false;
	var index = 0;
	var conOrder = "";
	$("[name='ids']").each(function(i,o){
      if($(this).attr("checked"))     
	  {   
	   	if($("#statusTotalCode_"+index).val() == "0280")
		{
			conOrder += $("#orderSubNo_" + index).val() + ",";
			confirmed = true;
		}
	  }   
	  index++;  
	});
	if(confirmed)
	{
		alert("订单" + conOrder.substring(0,conOrder.length-1) + "已退款，不需再退!");
		return;
	}
	
	var as = []; 
	
	var noNeedConfirmed = false;
	var index2 = 0;
	var notConOrder = "";
	$("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {   
	   	if($("#statusTotalCode_"+index2).val() != "0270")
		{
			notConOrder += $(this).val() + ",";
			noNeedConfirmed = true;
		}
		as.push($(this).val()); 
	  }   
	  index2++;  
	});
	if(noNeedConfirmed)
	{
		alert("订单" + notConOrder.substring(0,notConOrder.length-1) + "订单不满足退款条件");
		return;
	}
	
	var params = {
			ids:as
	};	
	
	var form = document.getElementById("listForm");
	form.action="drawback!search.action";
	if($(this)[0].confirm('确定退款订单吗?'))
	{
		//form.submit();
		$.ajax({
			type:"POST",
			url:"drawback!orderAudit.action",
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

/* ---已废弃----
function orderConfirm(){
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
	
	var confirmed = false;
	var index = 0;
	var conOrder = "";
	$("[name='ids']").each(function(i,o){
      if($(this).attr("checked"))     
	  {   
	   	if($("#statusTotalCode_"+index).val() == "0280")
		{
			conOrder += $("#orderSubNo_" + index).val() + ",";
			confirmed = true;
		}
	  }   
	  index++;  
	});
	if(confirmed)
	{
		alert("订单" + conOrder.substring(0,conOrder.length-1) + "已退款，不需再退!");
		return;
	}
	
	var noNeedConfirmed = false;
	var index2 = 0;
	var notConOrder = "";
	$("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {   
	   	if($("#statusTotalCode_"+index2).val() != "0270")
		{
			notConOrder += $("#orderSubNo_" + index2).val() + ",";
			
			noNeedConfirmed = true;
		}
	  }   
	  index2++;  
	});
	if(noNeedConfirmed)
	{
		alert("订单" + notConOrder.substring(0,notConOrder.length-1) + "订单不满足退款条件");
		return;
	}
	
	var form = document.getElementById("listForm");
	form.action="drawback!orderAudit.action";
	if($(this)[0].confirm('确定退款订单吗?'))
	{
		form.submit();
	}
}---已废弃----*/


</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>退款单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="drawback!search.action" method="post" >
	    	<input type="hidden" name="column" value="${column}" />
	    	<div id="MainTable">
			<div class="input">
				<input id="status" type="hidden" name="pager.queryMap.status" value="" >
			</div>		
			<div class="operateBar">
				<label>每页显示:</label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10" <#if pager.pageSize == 10>selected="selected" </#if>>
						10
					</option>
					<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
						20
					</option>
					<option value="50" <#if pager.pageSize == 50>selected="selected" </#if>>
						50
					</option>
					<option value="100" <#if pager.pageSize == 100>selected="selected" </#if>>
						100
					</option>
				</select>
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
					<td>退款单号：<input type="text" id="orderNo" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}" /></td>
					<td>关联销售单号：&nbsp;<input type="text" id="orderSubRelatedOrigin" name="orderSub.orderSubRelatedOrigin" value="${orderSub.orderSubRelatedOrigin!}" /></td>
					<td colspan="2">外部渠道订单号：<input type="text" id="aliasOrderNo" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}" /></td>
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
					<td>审核状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderMain.statusConfirm">
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
					<td>配送方式：&nbsp;&nbsp;&nbsp;<select name="orderSub.distributeType">
							<#list distributeTypeList as list>
								<option value="${list.code}"<#if list.code == orderSub.distributeType> selected </#if>>
									<#if list.code == "">
									全部
									<#else>
										${list.name}
									</#if>
								</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td>创建人：&nbsp;&nbsp;&nbsp;<input type="text" id="createdBy" name="orderMain.createdBy" value="${orderMain.createdBy!}" /></td>
					<td>商品关键字：&nbsp;&nbsp;&nbsp;<input type="text" id="commodityName" name="orderItem.commodityName" value="${orderItem.commodityName!}" />
					</td>
					<td colspan="2">下单日期：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"></td>
				</tr>
				<tr>
					<!--<td>购买区域：&nbsp;&nbsp;&nbsp;<input type="text" id="addressCode" name="orderSub.addressCode" value="${orderSub.addressCode!}" />
					</td>
					-->
					<td>类型：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderMain.orderCategory">
						<#list allOrderCategoryList as list>
							<option value="${list.code}"<#if list.code == orderMain.orderCategory> selected </#if>>
								<#if list.code == "">
									全部
									<#else>
										${list.desc}
									</#if>
							</option>
						</#list>
					</select>
					</td>
					<td>大客户：<select name="orderMain.ifPriviledgedMember">
								<option value="" <#if orderMain.ifPriviledgedMember == ""> selected </#if>>
									全部
								</option>
								<option value="1" <#if orderMain.ifPriviledgedMember == "1"> selected </#if>>
									是
								</option>
								<option value="0" <#if orderMain.ifPriviledgedMember == "0"> selected </#if>>
									否
								</option>
						</select>
						&nbsp;&nbsp;
						预警：<select name="orderMain.ifWarnOrder">
								<option value="" <#if orderMain.ifWarnOrder == ""> selected </#if>>
									全部
								</option>
								<option value="1" <#if orderMain.ifWarnOrder == "1"> selected </#if>>
									是
								</option>
								<option value="0" <#if orderMain.ifWarnOrder == "0"> selected </#if>>
									否
								</option>
						</select>
					</td>
					<td colspan="2">配送地址：&nbsp;&nbsp;&nbsp;&nbsp;<#include "/WEB-INF/template/admin/order/address_linkage.ftl"><!--<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />-->
					</td>
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
							<option value="50301" <#if '50301' == orderPay.payCode> selected </#if>>
								天虹购物卡
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
					<td>订单渠道：<select name="orderMain.orderSource">
							<#list orderSourceList as list>
								<option value="${list.dicCode}"<#if list.dicCode == orderMain.orderSource> selected </#if>>
									<#if list.dicCode == "">
									全部
									<#else>
										${list.dicName}
									</#if>
								</option>
							</#list>
							<option value="oms"<#if 'oms' == orderMain.orderSource> selected </#if>>
								订单中台
							</option>
						</select>
					</td>
					<td colspan="2">
						完成时间：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="finishTimeFrom" name="orderMain.finishTimeFrom" value="${(orderMain.finishTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="finishTimeTo" name="orderMain.finishTimeTo" value="${(orderMain.finishTimeTo?string("yyyy-MM-dd"))!}"></td>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="button_red" name="search" id="search" onclick="orderSearch();" value="查询">
					</td>
				</tr>	
				<tr>
					<td colspan="4">
						<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="confirm" id="confirm" onclick="orderConfirm();" value="已退款">
						<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="downexcel" id="downexcel" onclick="downExcel();"  value="导出excel"/>
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
			<table class="listTable detailTable" style="border: 1px solid #CCCCCC;width:110%">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<!--
					<th  width="50px">
						<span class="sort" name="orderSubId">订单编号</span>
					</th>
					-->
					<th  width="90px">
						<span class="sort" name="orderNo">逆向单号</span>
					</th>
					<th  width="35px">
						<span class="sort" name="orderCategory">类型</span>
					</th>
					<th  width="90px">
						<span class="sort" name="orderSubRelatedOrigin">关联销售单号</span>
					</th>
					<th  width="55px">	
						<span class="sort" name="submitDate">下单时间</span>
					</th>
					<th  width="75px">	
						<span class="sort" name="finishTime">退款完成时间</span>
					</th>
					<th  width="85px">
						<span class="sort" name="relatedFinishTime">原订单完成时间</span>
					</th>
					<th  width="90px">
						<span class="sort" name="statusConfirm">订单状态</span>
					</th>
					<th  width="90px">
						<span class="sort" name="payName">退款明细</span>
					</th>
					<!--
					<th  width="60px">
						<span  class="sort" name="ifNeedRefund">是否需退款</span>
					</th>
					-->
					<th  width="50px">
						<span  class="sort" name="createdBy">创建人</span>
					</th>
					<th  width="60px">
						<span class="sort" name="confirmerName">审核人</span>
					</th>
					<th  width="60px">
						<span class="sort" name="updatedBy">操作人</span>
					</th>
					<!--
					<th width="130px">
						<span class="sort" name="orderNo">外部渠道订单号</span>
					</th>
					-->
					<th width="50px">
						操作
					</th>
				</tr>
				<#list pager.list as list>
					<tr ondblclick="window.open('refund!view.action?orderNo=${list.orderNo}&column=${column}')">
						<td>
							<input type="checkbox" name="ids" value="${(list.orderSubNo)!}" />
							<input type="hidden" id="orderSubNo_${list_index}" name="orderSubNo" value="${list.orderSubNo}" />
							<input type="hidden" id="statusTotalCode_${list_index}" value="${list.statusTotalCode}" />
						</td>
						<!--<td><font color="#CD661D">${list.orderSubId}</font></td>-->
						<td style="width:90px;"><!--主订单号：<font color="#008B8B">${list.orderNo}</font> <br/>-->
							<font color="#008B8B"><a href="refund!view.action?orderNo=${list.orderNo}&column=${column}" title="详情" target="_blank">${list.orderSubNo}</a></font>
							<#if (list.ifWarnOrder == 1)>
							<br/>
							<font color="red"><b>[预警]</b></font>
							</#if>
							<#if (list.ifPriviledgedMember == 1)>
							<br/>
							<font color="blue"><b>[大客户]</b></font>
							</#if>
						</td>
						<td style="width:50px;">${orderCategoryNameMap.get(list.orderCategory)}
						</td>
						<td>
							<#if list.orderCategory=='tsf'><!--运费补款单直接关联逆向订单-->
								<a href="../sale_after_order/refund!view.action?orderNo=${list.orderRelatedOrigin}&column=${column}" title="逆向订单详情" target="_blank">${list.orderSubRelatedOrigin}</a>	
							<#else>
								<a href="../order!view.action?orderNo=${list.orderRelatedOrigin}&column=${column}" title="订单详情" target="_blank">${list.orderSubRelatedOrigin}</a>
							</#if>
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
							<!--物流状态：<font color="#008B8B">${(list.logisticsStatus)!}</font> <br/>-->
							处理状态：<font color="#CD661D">${(list.statusTotalName)!}</font>
						</td>
						<!--
							<td><#if (list.ifNeedRefund) == 1><font color="red">是</font>
								<#else><font color="#104E8B">否</font>
								</#if>
							</td>
						-->
						<td>
							<#list list.orderPayInfoList as list>
									<#if (list.payNo)??>
										支付号：${list.payNo}<br/>
									</#if>	
							      		${list.payName} : <font color="red">${(list.payAmount!0)?string.currency}</font> <br/>
								</#list>
						</td>
						<td>${(list.createdBy)!} <input type="hidden" name="operator" value="${(list.createdBy)!}"/>
						</td>
						<td>${(list.confirmerName)!}
						</td>
						<td>${(list.updatedBy)!}
						</td>
						<!--
							<td>${list.aliasOrderNo}
							</td>
						-->
						<td>
						    <a href="refund!view.action?orderNo=${list.orderNo}&column=${column}" title="详情" target="_blank">[详情]</a>
						  <#if list.statusConfirm != '0804'>	
							  <#if list.orderCategory == 'ret' && list.statusTotalCode!='0280'>
								<br/><a href="return!view.action?orderNo=${list.orderNo}&column=${column}" title="修改" target="_blank">[修改]</a>
							  <#elseif list.orderCategory == 'chg'  && list.statusTotalCode!='0280'>
							    <br/><a href="change!view.action?orderNo=${list.orderNo}&column=${column}" title="修改" target="_blank">[修改]</a>
							  <#elseif list.orderCategory == 'rej'  && list.statusTotalCode!='0280'>
							    <br/><a href="refuse!view.action?orderNo=${list.orderNo}&column=${column}" title="修改" target="_blank">[修改]</a>  
							  </#if>
						  </#if>
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