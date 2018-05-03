<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<#-- security.tld 角色权限控制 -->
<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员购买记录- Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<style type="text/css">
label.error {
	color: red;
	font-style: italic
}
div.error { display: none; }

input.error { border: 1px solid red; }
</style>

<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/validation/dist/jquery.validate.js"></script>

<script type="text/javascript">

$.gotoPage = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageTotalCount").val(), buttonClickCallback: $.gotoPage });
    $("#pageNumber").val(pageclickednumber);
    $("#listForm1").submit();
};

function orderHistorySearch(){
    var form1 = document.getElementById("listForm1");
    form1.action="order!history.action";
	form1.submit();
}

</script>


</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>会员购买记录列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm1" action="order!history.action" method="post" validate="true">
			<input type="hidden" name="orderMain.memberNo" value="${(orderMain.memberNo)!}" />
			<input type="hidden" name="orderSub.memberNo" value="${(orderMain.memberNo)!}" />
			
	    	<div id="MainTable">
	    	
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
			
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
			<tr>
					<td>订购人关键字：<input type="text" id="" name="orderSub.userName" value="${orderSub.userName!}"/></td>
					<td>下单日期从： <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}"></td>
					<td>订单来源：
						<select name="orderMain.orderSource">
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
				</tr>
				<tr>
				    <td>订单号:<input type="text" id="orderNo" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}"/></td>
				    <td>下单时间到:<input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"></td>
				    <td>订单类型:
				     <select name="orderMain.orderType">
							<#list orderTypeList as list>
								<option value="${list.dicCode}"<#if list.dicCode == orderMain.orderType> selected </#if>>
									<#if list.dicCode == "">
									全部
									<#else>
										${list.dicName}
									</#if>
								</option>
							</#list>
						</select>
				    </td>
				</tr>
				<tr>
				    <td>外部渠道订单号：<input type="text" id="" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}"/></td>
				    <td>商品关键字：<input type="text" id="commodityName" name="orderItem.commodityName" value="${orderItem.commodityName!}" /></td>
				    <!--
				    <td>显示数量：<input type="text" id="" name="pager.pageSize" value="5"/></td>
				    -->
				</tr>
				
				<tr>
					<td colspan='3'>
							<input type="button" class="previewButton" name="search" id="search" onclick="orderHistorySearch();" value="查询">
					</td>
				</tr>
			</table>
			<br>
	
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<th width="5%">
						<span class="sort" name="id">序号</span>
					</th>
					<th width="10%">
						<span class="sort" name="no">订单号</span> <br/>
					</th>
					<th width="10%">
						<span class="sort" name="aliasOrderNo">外部号</span>
					</th>
					<th width="10%">
						<span name="status">状态</span>
					</th>
					<th  width="10%">
						<span name="payType">支付方式</span>
					</th>
					<th width="10%">
						<span class="sort" name="dType">配送方式</span>
					</th>
					<!--
					<th width="10%">
						<span name="merchant">当前商家</span>
					</th>
					-->
					<th width="10%">
						<span name="time">下单时间</span>
					</th>
					<th width="10%">
						<span name="purchaser">购买人</span>
					</th>
					<th width="10%">
						<span name="commodity">商品</span>
					</th>
				</tr>
				<#assign rowid=0>
			   <#list pager.list as list>
			   
			   
			   <#assign rowid=rowid+1>
	               <tr  ondblclick="window.open('order!view.action?orderNo=${list.orderNo}&column=${column}')">
	                  <td width="5%">${rowid}</td>
	                  <td  width="5%"><a href="order!view.action?orderNo=${list.orderNo}&column=${column}">${list.orderSubNo!}</a></td>
	                  <td  width="5%">${list.aliasOrderNo}</td>

	                  <td  width="20%">
	                  	    <#if (list.statusConfirm)??>
							审核状态：<font color="red">
								 <#list statusConfirmList as statusConfirm>
								    <#if statusConfirm.statusCode==list.statusConfirm>
										 ${(statusConfirm.displayName)!}
									 </#if>		
								 </#list>
								 </font> <br/>
							</#if>
							<#if (list.statusPay)??>
							支付状态：<font color="#104E8B"><#list orderStatusList as statusPay>
							    <#if statusPay.statusCode==list.statusPay>
									${statusPay.displayName} 
								 </#if>		
							 </#list>
							 </font> <br/>
							</#if>
							<#if (list.logisticsStatus)??>
							物流状态：<font color="#008B8B">
							  <#list logisticsStatusList as logisticsStatus>
							    <#if logisticsStatus.statusCode==list.logisticsStatus>
									${logisticsStatus.displayName} 
								 </#if>		
							  </#list>
							 </font> <br/>
							</#if>
							<#if (list.statusTotal)??>
								处理状态：<font color="#CD661D">
								  <#list orderStatusList as orderStatus>
								    <#if orderStatus.statusCode==list.statusTotal>
										${orderStatus.displayName} 
									 </#if>		
								 </#list>
								</font>
						
							</#if>
	                  </td>
	                  <td  width="10%">
                           <#list list.orderPayInfoList as list>
								<#if (list.payNo)??>
									支付号：${list.payNo}<br/>
								</#if>	
						      		${list.payName} : <font color="red">${(list.payAmount!0)?string.currency}</font> <br/>
							</#list>
	                  </td>
	                  <td  width="5%">
	                  	<#list distributeTypeList as typeList>
								<#if typeList.code == list.distributeType>
										${typeList.name}
								</#if>
							</#list>
	                     
	                  </td>
	                  <!--
	                  <td></td>
	                  -->
	                  <td  width="5%">
	                  	<span title="${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
	                  </td>
	                  <td  width="10%">${(list.userName)!}</td>
	                  <td  width="30%">
	                  <#if (list.orderItems)??>
								<#list list.orderItems as item>
									<#if (item.commodityName)??>
									<span style="width:260px; display:inline-block;line-height:20px;">
									<font color="#104E8B">${(item.commodityName)!}</font> 
									</span>
									 <font color="#CD661D">${(item.saleNum)!}</font> &nbsp;&nbsp;&nbsp;&nbsp; <font color="red">${((item.unitPrice)!0)?string.currency}</font><font color="#008B8B"></font><br/>
									</#if>
									</br>
								</#list>
							</#if>
	                  </td>
	               </tr>
				
				</#list>
			</table>
		
			
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
	</div>
</body>
</html>