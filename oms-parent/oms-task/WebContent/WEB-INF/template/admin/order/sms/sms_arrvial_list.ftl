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
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">

$().ready( function() {
	$('#updateOrder').click(function(){
	        alert("updateOrder");
	});
});

function orderSearch(){
	var form = document.getElementById("listForm");
	form.action="sms_arrvial_query.action";
	$('#pageNumber').val(0);
	form.submit();
}


function sendSmsVirtual(object,orderSubNo){
//alert(orderSubNo);
    var form = document.getElementById("sendForm");
	form.action="sms_send_arrvial.action";
	$('#torderSubNo').val(orderSubNo);
	form.submit();
}
</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>${orderColumnTitle}虚拟商品订单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="sms_arrvial_query.action" method="post" >
	    	<input type="hidden" name="column" value="0" />
	    	<div id="MainTable">
			<div class="input">
				<input id="status" type="hidden" name="pager.queryMap.status" value="">
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
	    	
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 100%;">
				<tr>
					<td>主订单号：&nbsp;&nbsp;&nbsp;<input type="text" id="orderNo" name="orderMain.orderNo" value="${orderMain.orderNo!}" /></td>
					<td>子订单号：&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="orderSubNo" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}" /></td>
					<td>手机号码：&nbsp;<input type="text" id="mobPhoneNum" name="orderSub.mobPhoneNum" value="${orderSub.mobPhoneNum!}" /></td>
				</tr>
				<tr>
				    <td colspan="2">下单日期：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"></td>
				    <td></td>
				</tr>
				<tr>
					<td colspan="3">
						<input type="button" class="button_red" name="search" id="search" onclick="orderSearch();" value="查询">
					</td>
				</tr>	
				
			</table>
			<br>
			<table class="listTable"  style="border: 1px solid #CCCCCC;width:100%">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>

					<th width="100px">
						<span class="sort" name="orderNo">订单号</span> <br/>
					</th>
					<th width="55px">
						<span class="sort" name="submitDate">下单日期</span>
					</th>
					<th width="110px">
						<span name="userName">收件人信息</span>
					</th>
	
					<th width="100px">
						<span class="sort" name="statusConfirm">订单状态</span>
					</th>

                     <th width="100px">
                         <span class="sort" name="statusConfirm">商品明细</span>      
                     </th>
                     <th width="55px">
                         <span class="sort" name="statusConfirm">操作</span>      
                     </th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${(list.orderNo)!}" />
							<input type="hidden" id="statusTotalCode_${list_index}" value="${list.statusTotalCode}" />
						</td>
						
						<td>主订单号：<font color="#008B8B">${list.orderNo}</font> <br/>
							子订单号：<font color="#CD661D">${list.orderSubNo}</font>
						</td>
						<td>
							<span title="${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
						<td>收件人：<font color="#104E8B">${(list.userName)!}</font><br/>
							手机号：<font color="red">${(list.mobPhoneNum)!}</font><br/>
							配送地址：<font color="#008B8B">${(list.areaName)!}
							${(list.addressDetail)!}</font>
						</td>
						
						<td>
							<#if (list.statusConfirm)??>
							审核状态：<font color="red">		
							<#list statusConfirmList as statusConfirm>
							    <#if statusConfirm.statusCode==list.statusConfirm>
									${statusConfirm.displayName} 
								 </#if>		
							 </#list></font> <br/>
							</#if>
							<#if (list.statusPay)??>
							支付状态：<font color="#104E8B">						
							<#list orderStatusList as statusPay>
							    <#if statusPay.statusCode==list.statusPay>
									${statusPay.displayName} 
								 </#if>		
							 </#list></font> <br/>
							</#if>
							<#if (list.logisticsStatus)??>
							物流状态：<font color="#008B8B">					    	
							<#list logisticsStatusList as logisticsStatus>
							    <#if logisticsStatus.statusCode==list.logisticsStatus>
									${logisticsStatus.displayName} 
								 </#if>		
							 </#list></font> <br/>
							</#if>
							<#if (list.statusTotal)??>
							处理状态：<font color="#CD661D">
							<#list orderStatusList as orderStatus>
							    <#if orderStatus.statusCode==list.statusTotal>
									${orderStatus.displayName}
								 </#if>		
							 </#list></font>
							</#if>
						</td>
						
						<td>
							<#if (list.orderItems)??>
								<#list list.orderItems as item>
									<#if (item.commodityName)??>
									<span style="width:260px; display:inline-block;line-height:20px;">
									<font color="#104E8B">${(item.commodityName)!}</font> 
									</span>
									&nbsp;&nbsp;  <font color="#CD661D">${(item.saleNum)!}</font> &nbsp;&nbsp;&nbsp;&nbsp; <font color="red">￥${(item.unitPrice)!}</font><font color="#008B8B"></font><br/>
									</#if>
								</#list>
							</#if>
						</td>
						<td>
							<input type="button" value="发送短信" onclick="sendSmsVirtual(this,'${list.orderSubNo}');"/>
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
		 <form id="sendForm" action="" method="get">
			<input type="hidden" id="torderSubNo" name="torderSubNo" value="" />
		</form>
	</div>
</body>
</html>