
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
<style type="text/css">
    body{margin:0px;}   
	#BgDiv{background-color:#e3e3e3; position:absolute; z-index:99; left:0; top:0; display:none; width:100%; height:1000px;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}  
	  
	.popDiv {position:absolute;width:400px; left:50%; top:50%; margin-left:-200px; height:200px; z-index:100;background-color:#fff; border:1px #dc7a95 solid; padding:1px;}  
	.popDiv h2{ height:25px; font-size:14px; background-color:#dc7a95; position:relative; padding-left:10px; line-height:25px;}  
	.popDiv h2 a{position:absolute; right:5px; font-size:12px; color:#000000}  
	.popDiv table{width: 100%;border: 1px solid #e1e1e1;}  
	.popDiv .buttonArea{margin-top: 70px;margin-left: 96px;}
	
	.flag_span {
		background-color: #E2E6E9;
	    border-bottom: 1px solid #cfcfcf;
	    border-right: 1px outset #bdbdbd;
	    color: #000000;
	    font-size: 12px;
	    padding: 0 2px;
	    margin: 1px;
	    display: inline-block;
	 }
</style>
<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${base}/resources/admin/css/jquery.ui.all.css">
	<script src="${base}/resources/datepicker/ui/jquery-ui.js"></script>




    <script src="${base}/scripts/list.js"></script> 
    <!-- 按“Enter”键，搜索 -->
    <script src="${base}/scripts/list_search.js"></script> 

    <script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${webuiPath}/scripts/jqueryui/themes/base/jquery.multiselect.css" />
	<link rel="stylesheet" type="text/css" href="${webuiPath}/scripts/jqueryui/themes/base/jquery-ui.css" />
	
	<script src="${base}/resources/admin/js/ui/jquery.ui.core.js"></script>
	<script src="${base}/resources/admin/js/ui/jquery.ui.widget.js"></script>
	
	<script type="text/javascript" src="${webuiPath}/scripts/jqueryui/ui/jquery.multiselect.js"></script>

<script type="text/javascript">


$().ready( function() {
	var activeTabMapping = {0:0,1:1, 4:2, 10:3, 8:4, 7:5};
	$( "#tabs" ).tabs({
		  active: activeTabMapping[${column}]
	});

	$(".multipleSelect").multiselect({
        noneSelectedText: "==全部==",
        checkAllText: "全选",
        uncheckAllText: '全不选',
        selectedList:4
    });
	
	$('#updateOrder').click(function(){
	        alert("updateOrder");
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
	// 搜索栏收起和展开
	$("#searchCondition").click(function(){
	    $("#conditions").slideToggle("slow");
	  });
	  
	  
	// 支付方式选择  defind in include_order_list.ftl -> include_order_list_common.ftl
	selectPayCode();
	
});

<#include "/WEB-INF/template/admin/order/include_order_list.ftl">

</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list" style="background: #f0f3f4;"><div id="tipWindow" class="tipWindow"><span class="icon">&nbsp;</span><span class="messageText"></span></div><div id="messageWindow" class="messageWindow jqmID2"><div class="windowTop"><div class="windowTitle">提示信息&nbsp;</div><a class="messageClose windowClose" href="${base}/order/order.action?column=1#" hidefocus="true"></a></div><div class="windowMiddle"><div class="messageContent"><span class="icon">&nbsp;</span><span class="messageText"></span></div><input type="button" class="formButton messageButton windowClose" value="确  定" hidefocus="true"></div><div class="windowBottom"></div></div><div id="contentWindow" class="contentWindow jqmID1"><div class="windowTop"><div class="windowTitle"></div><a class="messageClose windowClose" href="${base}/order/order.action?column=1#" hidefocus="true"></a></div><div class="windowMiddle"><div class="windowContent"></div></div><div class="windowBottom"></div></div><div id="dialog-confirm" title="">
</div>
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>${orderColumnTitle}订单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="order!search.action" method="post" >
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
		<h4 id="searchCondition" style="border: 1px solid #e0e2e3; background-image: url(../resources/admin/images/admin_info.png); padding: 11px 0px; display: none; width:100%; color:#58666e;" >
			<span id="searchConditionIcon" class="displaySearchCondition"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搜索条件
		</h4>
		<div id="conditions" style="margin:0;padding:0;">
			<table class="listTable" style="border: 1px solid #e6e6e6; background: #FFFFFF;line-height: 30px;">
				<tbody><tr>
					<!--
					<td>订单编号：<input type="text" id="orderSubId" name="orderSub.id" value="${orderSub.id!}"/></td>
					-->
					<!--<td>主订单号：&nbsp;&nbsp;&nbsp;<input type="text" id="orderNo" name="orderMain.orderNo" value="${orderMain.orderNo!}" /></td>-->
					<td>订单号：&nbsp;&nbsp;&nbsp;<input type="text" id="orderSubNo" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}"/></td>
					<!--
					<td>
			                           下单人工号:&nbsp;&nbsp;&nbsp;<input type="text" id="orderMem" name="orderMain.memberNo" value="${orderMain.memberNo!}" />
			        </td>
			        -->
					<td>外部渠道订单号：&nbsp;<input type="text" id="aliasOrderNo" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}" /></td>
					<td>自提验证码：<input type="text" id="checkCode" name="orderSub.checkCode" value="${orderSub.checkCode!}" /></td>
					
					<td>商品名称：<select id="isCommodityName" name="includeOrNot.isCommodityName">
							<option value="" >
								全部
							</option>
							<option value="0" >
								不包含
							</option>
							<option value="1" >
								包含
							</option>
						</select>
						<input type="text" id="commodityName" name="orderItem.commodityName" value="${orderItem.commodityName!}" /></td>
				</tr>
				<tr>
				${orderSub.deliveryMerchantN}
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
							<#if list.statusCode != '0806' && list.statusCode != '0807' && list.statusCode != '0808'>
							<option value="${list.statusCode}"<#if list.statusCode == orderMain.statusConfirm> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.displayName}
								</#if>
							</option>
							</#if>
						</#list>
					</select>
					</td>
					<td>支付状态：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderMain.statusPay">
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
					<td colspan="4">物流状态：
					     
					<select class="multipleSelect" id ="logisticsStatus" name="orderSub.logisticsStatus"  title="Basic example" multiple="multiple" >
					<#if logisticsStatusList!=null&&(logisticsStatusList?size>0)>
        				<#list orderSub.logisticsStatus?split(",") as x>   
        			<#list logisticsStatusList as list>
        			
        				<option value="${list.statusCode}"
        				<#if list.statusCode == x&&x!=""> selected </#if>>
							
									${list.displayName}
							
							</option>
							</#list>
        			</#list>
        		</#if>
                   </select>
              </td>
				</tr>
				<tr>
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
						</select>
					</td>
					<!--<td>订单大类：&nbsp;&nbsp;&nbsp;<select name="orderMain.orderCategory">
						<#list orderCategoryList as list>
							<option value="${list.code}"<#if list.code == orderMain.orderCategory> selected </#if>>
								<#if list.code == "">
									全部
									<#else>
										${list.desc}
									</#if>
							</option>
						</#list>
						</select>
					</td>-->
					<td>订单类型：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderMain.orderType">
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
					<!--
					<td>配送方式：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderSub.distributeType">
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
					-->
					<td colspan="1">大客户：<select name="orderMain.ifPriviledgedMember">
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
				</tr>
				<tr>
					<!--<td>购买区域：&nbsp;&nbsp;&nbsp;<input type="text" id="addressCode" name="orderSub.addressCode" value="${orderSub.addressCode!}" />
					</td>
					-->
					<!--<td>自提点：&nbsp;&nbsp;&nbsp;<select name="selfTakePoint.id">
							<#list selfTakePointList as list>
								<option value="${list.id}"<#if list.id == selfTakePoint.id> selected </#if>>
										<#if list.id == "">
									全部
									<#else>
										${list.pointName}
									</#if>
								</option>
							</#list>
						</select>
					</td>-->
					<!--
					<td>
						收货人姓名：&nbsp;&nbsp;&nbsp;<input type="text" id="userName" name="orderSub.userName" value="${orderSub.userName!}" />
					</td>
					<td>
						收货人手机号：&nbsp;&nbsp;&nbsp;<input type="text" id="mobPhoneNum" name="orderSub.mobPhoneNum" value="${orderSub.mobPhoneNum!}" />
					</td>
					-->
					<td>
						收货人地址：
						<select id="isAddressDetail" name="includeOrNot.isAddressDetail">
							<option value="" <#if '' == includeOrNot.isAddressDetail> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == includeOrNot.isAddressDetail> selected </#if>>
								不包含
							</option>
							<option value="1" <#if '1' == includeOrNot.isAddressDetail> selected </#if>>
								包含
							</option>
						</select>
						&nbsp;&nbsp;&nbsp;<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />
					</td>
				</tr>
				<tr>
					<td>收货人信息：<input type="text" id="userName" name="orderSub.receiverInfo" value="${orderSub.receiverInfo!}" /></td>
					<td>
						会员信息：<input type="text" id="memberInfo" name="orderMain.memberInfo" value="${orderMain.memberInfo!}" /></td>
					</td>
					<td>
						支付号：<input type="text"  id="payNo" name="orderPay.payNo" value="${(orderPay.payNo)!}"></td>
					</td>
					<td>黑名单：<select name="orderMain.ifBlackListMember">
								<option value="" <#if orderMain.ifBlackListMember == ""> selected </#if>>
									全部
								</option>
								<option value="1" <#if orderMain.ifBlackListMember == "1"> selected </#if>>
									是
								</option>
								<option value="0" <#if orderMain.ifBlackListMember == "0"> selected </#if>>
									否
								</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
					 订单金额下限： <input type="text" id="amountUp" name="orderMain.amountUp" value="${orderMain.amountUp!}" />
					</td>
					
					<td colspan="1">
					订单金额上限：<input type="text" id="amountDown" name="orderMain.amountDown" value="${orderMain.amountDown!}" />
					</td>
					<td colspan="1">支付方式：
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
							<option value="830" <#if '830' == orderPay.payCode> selected </#if>>请选择</option>
							<#list paymentMethodList as list>
								<#if list.id != "830">
									<option value="${list.id}"<#if list.id == orderPay.payCode> selected </#if>>
										${list.name}
									</option>
								</#if>
							</#list>
						</select>
					</td>
										<td colspan="1">
						支付时间：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="payTimeFrom" name="orderPay.payTimeFrom" value="${(orderPay.payTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="payTimeTo" name="orderPay.payTimeTo" value="${(orderPay.payTimeTo?string("yyyy-MM-dd"))!}"></td>
					</td>
				</tr>
			    <tr>
			    <!--
			    	<td colspan="2">
			    		自提点：<#include "/WEB-INF/template/admin/order/selfTakePoint_linkage.ftl">
			    	</td>
			    	-->
					<td colspan="2">配送地址：<#include "/WEB-INF/template/admin/order/area_linkage.ftl"><!--<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />-->
					</td>
				</tr>
				<tr>
					<td colspan="2">下单日期：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"></td>
					<td colspan="2">
						完成时间：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="finishTimeFrom" name="orderMain.finishTimeFrom" value="${(orderMain.finishTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="finishTimeTo" name="orderMain.finishTimeTo" value="${(orderMain.finishTimeTo?string("yyyy-MM-dd"))!}"></td>
					
				</tr>
				<tr>
					<td>商家类型：
						<select id="merchantType" name="orderMain.merchantType">
							<option value="" <#if '' == orderMain.merchantType> selected </#if>>
								全部
							</option>
							<option value="bbc" <#if 'bbc' == orderMain.merchantType> selected </#if>>
								商家
							</option>
							<option value="invoiceOrg" <#if 'invoiceOrg' == orderMain.merchantType> selected </#if>>
								云店
							</option>
							<option value="client" <#if 'client' == orderMain.merchantType> selected </#if>>
								供应商
							</option>
							<option value="platform" <#if 'client' == orderMain.merchantType> selected </#if>>
								网天
							</option>
						</select>
					</td>
					<td>商家编号：&nbsp;&nbsp;&nbsp;<input type="text" id="merchantNo" name="orderMain.merchantNo" value="${orderMain.merchantNo!}" /></td>
					<td>退款类型：&nbsp;&nbsp;&nbsp;
					<select name="orderMain.refundType">
						<#list orderRefundTypeList as list>
							<option value="${list.code}"<#if list.code == orderMain.refundType> selected </#if>>
								<#if list.code == "">
									全部
									<#else>
										${list.desc}
									</#if>
						
						</#list>	
						</option>
								<option value="0">
								无
							</option>
					</select>
					</td>
					<td>客户留言<select id="isClientRemark" name="includeOrNot.isClientRemark">
							<option value="" <#if '' == includeOrNot.isClientRemark> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == includeOrNot.isClientRemark> selected </#if>>
								不包含
							</option>
							<option value="1" <#if '1' == includeOrNot.isClientRemark> selected </#if>>
								包含
							</option>	
							<option value="2" <#if '2' == includeOrNot.isClientRemark> selected </#if>>
								无
							</option>
						</select>
						：&nbsp;&nbsp;&nbsp;<input type="text" id="clientRemark" name="orderMain.clientRemark" value="${orderMain.clientRemark!}" /></td>
				</tr>
				<tr>
					<td>客服备注：<select id="isRemark" name="includeOrNot.isRemark">
							<option value="" <#if '' == includeOrNot.isRemark> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == includeOrNot.isRemark> selected </#if>>
								不包含
							</option>
							<option value="1" <#if '1' == includeOrNot.isRemark> selected </#if>>
								包含
							</option>
							<option value="2" <#if '2' == includeOrNot.isRemark> selected </#if>>
								无
							</option>
						</select>
						<input type="text" id="clientServiceRemark" name="orderMain.clientServiceRemark" value="${orderMain.clientServiceRemark!}" /></td>
					<td colspan='2'>商家留言：
					<select id="isSellerMessage" name="includeOrNot.isSellerMessage">
							<option value="" <#if '' == includeOrNot.isSellerMessage> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == includeOrNot.isSellerMessage> selected </#if>>
								不包含
							</option>
							<option value="1" <#if '1' == includeOrNot.isSellerMessage> selected </#if>>
								包含
							</option>
							<option value="2" <#if '2' == includeOrNot.isSellerMessage> selected </#if>>
								无
							</option>
						</select>
						<input type="text" id="sellerMessage" name="orderMain.sellerMessage" value="${orderMain.sellerMessage!}" /></td>
				</tr>
				<tr>
				<td>是否挂起：
						<select id="isSuspension" name="orderMain.isSuspension">
							<option value="" <#if '' == orderMain.isSuspension> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == orderMain.isSuspension> selected </#if>>
								否
							</option>
							<option value="1" <#if '1' == orderMain.isSuspension> selected </#if>>
								是
							</option>
						</select>
					</td>
					<td>是否合并：
						<select id="isMerge" name="orderMain.isMerge">
							<option value="" <#if '' == orderMain.isMerge> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == orderMain.isMerge> selected </#if>>
								否
							</option>
							<option value="1" <#if '1' == orderMain.isMerge> selected </#if>>
								是
							</option>
						</select>
					</td>
					<td>是否拆分：
						<select id="isSplit" name="orderMain.isSplit">
							<option value="" <#if '' == orderMain.isSplit> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == orderMain.isSplit> selected </#if>>
								否
							</option>
							<option value="1" <#if '1' == orderMain.isSplit> selected </#if>>
								是
							</option>
						</select>
					</td>
					<td>是否换货：
						<select id="isBarter" name="orderMain.isBarter">
							<option value="" <#if '' == orderMain.isBarter> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == orderMain.isBarter> selected </#if>>
								否
							</option>
							<option value="1" <#if '1' == orderMain.isBarter> selected </#if>>
								是
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						商品年份：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="productYearStart" name="orderItem.productYearStart" value="${(orderItem.productYearStart?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="productYearEnd" name="orderItem.productYearEnd" value="${(orderItem.productYearEnd?string("yyyy-MM-dd"))!}"></td>
					</td>
					<td>商品sku：<select id="isskuNo" name="includeOrNot.isSkuNo">
							<option value="" <#if '' == includeOrNot.isSkuNo> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == includeOrNot.isSkuNo> selected </#if>>
								不包含
							</option>
							<option value="1" <#if '1' == includeOrNot.isSkuNo> selected </#if>>
								包含
							</option>
						</select>
						<input type="text" id="skuNo" name="orderItem.skuNo" value="${orderItem.skuNo!}" /></td>
					<td>商品货号：
						<select id="isCommodityCode" name="includeOrNot.isCommodityCode">
							<option value="" <#if '' == includeOrNot.isCommodityCode> selected </#if>>
								全部
							</option>
							<option value="0" <#if '0' == includeOrNot.isCommodityCode> selected </#if>>
								不包含
							</option>
							<option value="1" <#if '1' == includeOrNot.isCommodityCode> selected </#if>>
								包含
							</option>
						</select>
						<input type="text" id="commodityCode" name="orderItem.commodityCode" value="${orderItem.commodityCode!}" /></td></tr>
				<tr>
						<td>商品数量 ：<input type="text"  size="5" id="skuNumMin" name="includeOrNot.skuNumMin" value="${includeOrNot.skuNumMin!}" />-
						<input type="text" id="skuNumMax" size="5"  name="includeOrNot.skuNumMax" value="${includeOrNot.skuNumMax!}" /></td>
				</tr>
				
				<tr>
					<td>发货仓库：<input type="text" id="warehouseNo" name="orderItem.warehouseNo" value="${orderItem.warehouseNo!}" /></td>
					<td>手机号：<input type="text" id="customerPhone" name="orderMain.customerPhone" value="${orderMain.customerPhone!}" /></td>
					<td>姓名：<input type="text" id="customerName" name="orderMain.customerName" value="${orderMain.customerName!}" /></td>
					<td>运单号：<input type="text" id="logisticsOutNo" name="orderSub.logisticsOutNo" value="${orderSub.logisticsOutNo!}" /></td>
					</tr>
	               <tr>
	               <td colspan="4">
					物流商：<select class="multipleSelect" id ="deliveryMerchantNo" name="orderSub.deliveryMerchantNo"  title="Basic example" multiple="multiple">
					<#if companyList!=null&&(companyList?size>0)>
					
					
        				<#list orderSub.deliveryMerchantNo?split(",") as x>  
        			<#list companyList as company>
        			
        				
        				<option value="${company.code}" "<#if company.code == x&&x!=null> selected </#if>>
        			
									${company.name}
								</option>
        				
							</#list>
        			</#list>
        		</#if>
                   </select>
              </td>
              
					</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="button_red" name="search" id="search" onclick="orderSearch();" value="查询">
						<input type="button" class="button" name="reset" id="reset" value="重置">
					</td>
				</tr>	
				<tr>
					<td colspan="4">
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="confirm" id="confirm" onclick="orderConfirm();" value="审核通过">
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="updateDelivery" id="updateDelivery" onclick="showOperateDiv('updateDeliveryNoDiv');" value="批量修改物流公司" style="width:150px">
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="updateWareHouse" id="updateWareHouse" onclick="showOperateDiv('updateWareHouseNoDiv');" value="批量修改发货仓库" style="width:150px">
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="updateClientServiceRemark" id="updateClientServiceRemark" onclick="showOperateDiv('updateClientServiceRemarkDiv');" value="批量修改客服备注" style="width:150px">
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="splitOrder" id="splitOrder" onclick="showOperateDiv('splitOrderDiv');" value="拆单" style="width:150px">
							<#if column == 1 || column == 4 || column == 2 || column==6>
							<span style="border: solid 1px rgb(223, 223, 223);padding-top: 2px;padding-left: 4px;padding-bottom: 5px;background-color: rgb(253, 250, 250);">
								<select id="cancelReasonNo">
			                 	     <#list cancelReasonList as cancelReason>
									    <option value="${cancelReason.code}">
									    	<#if cancelReason.code == ''>
									    	取消原因
									    	<#else>
											${cancelReason.name}
											</#if>
										</option>
									 </#list>
							   </select>
								<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="cancel" id="cancel" onclick="orderCancel();" value="取消">
							</span>
							</#if>
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="downexcel" id="downexcel" onclick="downExcel();"  value="导出excel"/>
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="orderSuspensionOn" id="orderSuspensiOn" onclick="orderSuspension();"  value="挂起"/>
							<input type="button" class="button" <#if (pager.list?size <= 0)>disabled</#if> name="relieveorderSuspension" id="relieveorderSuspension" onclick="relieveOrderSuspension();"  value="解挂"/>
					</td>
				</tr>	
			</tbody></table>
			</div>
			</div>
			<br>
			<div id="tabs">
				<ul>
					<li><a href="#dataList" onclick="window.location.href='/oms-admin/order/order.action?column=0'">全部</a></li>
					<li><a href="#dataList" onclick="window.location.href='/oms-admin/order/order.action?column=1'">未确定</a></li>
					<li><a href="#dataList" onclick="window.location.href='/oms-admin/order/order.action?column=4'">已确定</a></li>
					<li><a href="#dataList" onclick="window.location.href='/oms-admin/order/order.action?column=10'">挂起</a></li>
					<li><a href="#dataList" onclick="window.location.href='/oms-admin/order/order.action?column=8'">无效</a></li>
					<li><a href="#dataList" onclick="window.location.href='/oms-admin/order/order.action?column=7'">完成</a></li>
				</ul>
			<div id="dataList">
			<h4 id="searchCondition" style="border: 1px solid #e0e2e3; background-image: url(../resources/admin/images/admin_info.png);padding: 11px 0px;  width:2200px; color:#58666e;" >
			<span id="searchConditionIcon" class="displaySearchCondition"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搜索结果
		</h4>
			<table class="listTable detailTable"  style="border: 1px solid #CCCCCC;width:2200px;>
				<tbody style="background: #ffffff;"><tr Dlass="th_gray" style="background: #dbdbdb;">
				<tr>
					<th width="125px">标签</th>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					
					<!--
					<th width="55px">
						<span class="sort" name="orderSubId">订单编号</span>
					</th>
					-->
					<th width="125px">
						<span class="sort" name="orderNo">订单号</span> <br/>
					</th>
					
					<th  width="105px">
						<span class="sort" name="aliasOrderNo">外部渠道订单号</span>
					</th>
					<th width="40px">
						<span class="sort" name="merchantNo">商店</span>
					</th>
					
					<th width="250px">
						<span class="sort" name="statusConfirm">订单状态</span>
					</th>
					<th width="120px">
						<span class="sort" name="merchantNo">配送方式</span>
					</th>
					
					<th width="70px">
						<span class="sort" name="skuNumber">sku数量</span>
					</th>
					
					<th width="170px">
						<span class="sort" name="clientRemark">客户留言</span>
					</th>
					
					<th width="170px">
						<span class="sort" name="sellerMessage">商家留言</span>
					</th>
					<th width="170px">
						<span class="sort" name="remark">客服备注</span>
					</th>
					
					<th width="150px">
						<span class="sort" name="orderItem">仓库</span>
					</th>
					
					<th width="170px">
						<span name="userName">收货人信息</span>
					</th>
					
					<th width="180px">
						<span name="userName">物流单号</span>
					</th>
					
					<th width="180px">
						<span name="userName">商品货号</span>
					</th>
					<th width="150px">
						<span name="payName">支付方式</span>
					</th>
					<!--
					// 增加支付时间
					-->
					<th width="120px">
						<span name="payTime">支付时间</span>
					</th><!--
                    // END YUSL 1/15-->
					<th width="50px">
						<span class="sort" name="submitDate">操作</span>
					</th>
					<th width="70px">
						<span class="sort" name="submitDate">下单时间</span>
					</th>
					<#if column=='7'>
					<th width="70px">
						<span class="sort" name="submitDate">完成时间</span>
					</th>
					</#if>
					<!--
					<th>
						<span class="sort" name="totalAmount">订单总额</span>
					</th>
					-->
					<th width="85px">
						<span class="sort" name="totalPayAmount">订单总金额</span>
					</th>
					
					<th width="50px">
						<span class="sort" name="confirmerName">审核人</span>
					</th>
					<th width="70px">
						<span class="sort" name="orderSource">订单渠道</span>
					</th>
				</tr>
				<#list pager.list as list>
					<tr ondblclick="window.open('order!view.action?orderNo=${list.orderNo}&column=${column}')">
						<td>
							<#list list.flags as flag>
								<span class="flag_span">${flag}</span>
							</#list>
						</td>
						<td>
							 <#if list.orderCategory != 'chgOut'>
								<input type="checkbox" name="ids" value="${(list.orderSubNo)!}" />
							</#if>
							<input type="hidden" id="orderSubNo_${list_index}" name="orderSubNo" value="${list.orderSubNo}" />
							<input type="hidden" id="statusTotalCode_${list_index}" value="${list.statusTotalCode}" />
						</td>
						
						<!--<td>${list.orderSubId}</td>-->
							<td><!--主订单号：<font color="#008B8B">${list.orderNo}</font> <br/>-->
							<font color="#CD661D"><a href="order!view.action?orderNo=${list.orderNo}&column=${column}" title="详情" target="_blank">${list.orderSubNo}</a></font>
							<#if (list.ifWarnOrder == 1)>
							<br/>
							<font color="red"><b>[预警]</b></font>
							</#if>
							<#if (list.ifPriviledgedMember == 1)>
							<br/>
							<font color="blue"><b>[大客户]</b></font>
							</#if>
							<#if (list.ifBlackListMember == 1)>
							<br/>
							<font color="red"><b>[黑名单]</b></font>
							</#if>
							<#if (list.statusTotal == '0151')>
							<br/>
							<font color="red"><b>[库存扣减失败]</b></font>
							</#if>
							<#if list.orderCategory == 'chgOut'>
								<br/><font color="#CD661D"><b>[换货]</b></font>
							</#if>
							<#if (list.statusTotal == '0110') || (list.statusTotal == '0111') || (list.statusTotal == '0112') || (list.statusTotal == '0113')>
							<br/>
							<font color="gray"><b>[无效单]</b></font>
							</#if>
						</td>
						<td><span style="width:105px;display:block;word-wrap:break-word;">${list.aliasOrderNo}</span>
						</td>
						
						<td><span style="width:105px;display:block;word-wrap:break-word;">${list.merchantNo}</span>
						</td>
						
						<td>
							审核状态：<font color="red">${(list.statusConfirmName)!}</font> <br/>
							支付状态：<font color="#104E8B">${list.statusPayName!}</font> <br/>
							物流状态：<font color="#008B8B">${(list.logisticsStatusName)!}</font> <br/>
							处理状态：<font color="#CD661D">${(list.statusTotalName)!}</font>
						</td>
						
						<td>
						<#list companyList as company>
						<#if (list.deliveryMerchantNo == company.code)>
							物流商：<font color="#CD661D">${(company.name)!}</font>
							 </#if>
							</#list> 
						</td>	
								<td>
							<#if (list.orderItems)??>
							<#assign x = 0> 
								<#list list.orderItems as item>
									<#if (item.saleNum)??>
									
							<#assign x = x+(item.saleNum)> 
								
									</#if>
								</#list>
							</#if>
								&nbsp;&nbsp;&nbsp;&nbsp;  <font color="#CD661D">${(x)!}</font> &nbsp;&nbsp;&nbsp;&nbsp; <br/>
						</td>
						
						<td>
							<font color="#CD661D">${(list.clientRemark)!}</font> <br/>
							</td>
						<td>
							<font color="#CD661D">${(list.sellerMessage)!}</font> <br/>
							</td>
						<td>
							<font color="#CD661D">${(list.clientServiceRemark)!}</font> <br/>
							</td>
							<td>
							<#if (list.orderItems)??>
								<#list list.orderItems as item>
								${(item.warehouseNo)!}</font><br/>
								
								</#list>
							</#if>
						</td>
							<td>收货人姓名：<font color="#104E8B">${(list.userName)!}</font><br/>
							手机号：<font color="red">${(list.mobPhoneNum)!}</font><br/>
							配送地址：<font color="#008B8B">${(list.areaName)!}
							${(list.addressDetail)!}</font>
						</td>
						
						<td>
							单号：<font color="#104E8B">${(list.logisticsOutNo)!}</font><br/>	
						</td>
						<td>
							<#if (list.orderItems)??>
								<#list list.orderItems as item>
									<span style="width:200px; display:inline-block;line-height:20px;">
									<font color="gray">
									&nbsp;&nbsp;&nbsp;&nbsp;  <font color="#CD661D">${(item.commodityCode)!}</font><br/>
								
								</#list>
							</#if>
						</td>
						<td>
								<#list list.orderPayInfoList as list>
									<#if (list.payNo)??>
										支付号：${list.payNo}<br/>
									</#if>	
							      		${list.payName} : <font color="red">${(list.payAmount!0)?string.currency}</font> <br/>
							      		<input type="hidden" id="payNameTmp" value="${list.payName}"/>
							      		<input type="hidden" id="payAmountTm p" value="${(list.payAmount!0)}"/>
								
						</#list>
						</td>
						
						
					<!--
					// 增加支付时间
					--><#assign n = 0 />
						<td><#list list.orderPayInfoList as list>
						
							<#if (n)==0>
							${(list.payTime?string("yyyy-MM-dd HH:mm:ss"))!}
								</#if>	
							<#assign n = n+1 />
							<br/>
									
						</#list>
						</td>
						
					<!--
					// END yusl 1/15
					-->
						<td>
							<a href="order!view.action?orderNo=${list.orderNo}&column=${column}" title="详情"  target="_blank">[详情]</a> <br/>
							<#if column != 0>
								<#if (list.statusTotalCode == "0180")>
								<a href="sale_after_order/return!returnView.action?orderNo=${list.orderNo}&column=${column}&orderCategory=ret" title="退货"  target="_blank">[退货]</a> <br/>
								</#if>	
								<#if (list.statusTotalCode == "0180")>
								<a href="sale_after_order/change!change.action?orderNo=${list.orderNo}&column=${column}&orderCategory=chg" title="换货"  target="_blank">[换货]</a><br/>
						        </#if>
						        <#if (list.statusTotalCode == "0170") || (list.statusTotalCode == "0171")>
								<a href="sale_after_order/refuse!refuse.action?orderNo=${list.orderNo}&column=${column}&orderCategory=rej" title="拒收"  target="_blank">[拒收]</a>			
								</#if>
							</#if>
						</td>
						<td>
							<span style="/*border:1px solid red;*/width:65px;display:block;" title="${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
						<#if column=='7'>
						<td>
							<span style="/*border:1px solid red;*/width:65px;display:block;" title="${(list.finishTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.finishTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
						</#if>
					
						<!--
						<td>
							<#if (list.totalProductPrice)??>
							商品总价：<font color="red">￥${(list.totalProductPrice?string(orderUnitCurrencyFormat))!}</font>
							<br/>
							</#if>
							
							<#if (list.discountTotal)??>
							折扣优惠金额：<font color="red">￥${(list.discountTotal?string(orderUnitCurrencyFormat))!}</font>
							<br/>
							</#if>
							
							<#if (list.transportFee)??>
							运费总额：<font color="red">￥${(list.transportFee?string(orderUnitCurrencyFormat))!}</font>
							</#if>
						</td>
						-->
						<td>
							<#if (list.totalPayAmount)??><font color="red">${((list.totalPayAmount)!0)?string.currency}</font>
							</#if>
							<input type="hidden" id="totalPayAmountTmp" value="${(list.totalPayAmount)!0}"/>
						</td>
						
			
						
						<td>${(list.confirmerName)!}</td>
						<td>
							${(list.orderSourceName)!}
						</td>
					</tr>
				</#list>
			</tbody></table>
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
		</div>
		
		 <form id="excelForm" action="" method="post">
		 	<input type="hidden" name="column" value="${column}" />
			<input type="hidden" name="orderSub.id" value="${orderSub.id!}" />
			<input type="hidden" name="orderMain.orderNo" value="${orderMain.orderNo!}" />
			<input type="hidden" name="orderSub.orderSubNo" value="${orderSub.orderSubNo!}" />
			<input type="hidden" name="orderMain.aliasOrderNo" value="${orderMain.aliasOrderNo!}" />
			<input type="hidden" name="orderMain.statusConfirm" value="${orderMain.statusConfirm!}"/>
			<input type="hidden" name="orderMain.statusPay" value="${orderMain.statusPay!}"/>
			<input type="hidden" name="orderSub.logisticsStatus" value="${orderSub.logisticsStatus!}"/>
			<input type="hidden" name="orderMain.statusTotal" value="${orderMain.statusTotal!}"/>
			<input type="hidden" name="orderMain.orderSource" value="${orderMain.orderSource!}"/>
			<input type="hidden" name="orderMain.orderType" value="${orderMain.orderType!}"/>
			<input type="hidden" name="orderPay.payCode" value="${orderPay.payCode!}"/>
			<input type="hidden" name="orderSub.distributeType" value="${orderSub.distributeType!}"/>
			<input type="hidden" name="orderSub.deliveryMerchantNo" value="${orderSub.deliveryMerchantNo!}"/>		
			<input type="hidden" name="orderSub.addressCode" value="${orderSub.addressCode!}"/>
			<input type="hidden" name="selfTakePoint.id" value="${selfTakePoint.id!}"/>
			<input type="hidden" name="orderItem.commodityName" value="${orderItem.commodityName!}"/>
			<input type="hidden" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}"/>
			<input type="hidden" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"/>
			<input type="hidden" name="orderMain.customerName" value="${orderMain.customerName!}"/>
			<input type="hidden" name="distributeAddress.state" value="${distributeAddress.state!}"/>
			<input type="hidden" name="distributeAddress.city" value="${distributeAddress.city!}"/>
			<input type="hidden" name="distributeAddress.county" value="${distributeAddress.county!}"/>
			<input type="hidden" name="orderSub.checkCode" value="${orderSub.checkCode!}" />
			<input type="hidden" name="orderSub.receiverInfo" value="${orderSub.receiverInfo!}" />
		</form>
	</div>
	</div>
	
	<div id="calendar_div"></div></body></html>
	
	<div id="BgDiv"></div>
  
	 <div id="updateDeliveryNoDiv" style="display:none" class="popDiv">
	 <h2><span class="icon">&nbsp;</span>批量修改物流公司</h2>
			<div class="blank"></div>
			<div style="clear:both;"></div>	
			<#assign width=-50 />	
			<div class="blank"></div>
	 	<table>
		 	<tr>
		 		<th>物流公司</th>
		 		<td>
		        	<select id = "newDeliveryNo">
		        	<#list logisticsCodes as logisticsCode>
		        		<option value="${logisticsCode.code}">${logisticsCode.name}</option>
		        	</#list>
		        	</select>
		        </td>
	        </tr>	
        </table>
		<div class="buttonArea">
			<input id=btnClose class="formButton" type=button onclick="orderUpdateDelivery()" value="保存"/>
    		<input id=btnClose class="formButton" type=button onclick="closeDiv('updateDeliveryNoDiv')" value="关闭"/>
		</div>

	 </div>
	 
	 <div id="updateWareHouseNoDiv" style="display:none" class="popDiv">
	 	<h2><span class="icon">&nbsp;</span>批量修改发货仓库</h2>
		<div class="blank"></div>
		<div style="clear:both;"></div>	
		<#assign width=-50 />	
		<div class="blank"></div>
	 	<table>
		 	<tr>
		 		<th>发货仓库</th>
		 		<td>
		        	<select id = "newWareHouseNo">
						<#list wareHouses as wareHouse>
			        		<option value="${wareHouse.warehouseID}">${wareHouse.warehouseName}</option>
			        	</#list>		        	
		        	</select>
		        </td>
	        </tr>	
	    </table>
		<div class="buttonArea">
			<input id=btnClose class="formButton" type=button onclick="updateOrderWareHouse()" value="保存"/>
    		<input id=btnClose class="formButton" type=button onclick="closeDiv('updateWareHouseNoDiv')" value="关闭"/>
		</div>

	 </div>
	  <div id="updateClientServiceRemarkDiv" style="display:none" class="popDiv">
	 	<h2><span class="icon">&nbsp;</span>批量修改客服备注</h2>
		<div class="blank"></div>
		<div style="clear:both;"></div>	
		<#assign width=-50 />	
		<div class="blank"></div>
	 	<table class="inputTable tabContent">
	 		<tr>
				<td>
					<span>
						<input type="radio" id="flag1" name="clientServiceRemarkFlag" value="1" style="width:15px;border:2px;">
						<img src="${webuiPath}/images/op_memo_red.png">
						<input type="radio" id="flag2" name="clientServiceRemarkFlag" value="2" style="width:15px;border:2px;">
						<img src="${webuiPath}/images/op_memo_yellow.png">
						<input type="radio" id="flag3" name="clientServiceRemarkFlag" value="3" style="width:15px;border:2px;">
						<img src="${webuiPath}/images/op_memo_green.png">
						<input type="radio" id="flag4" name="clientServiceRemarkFlag" value="4" style="width:15px;border:2px;">
						<img src="${webuiPath}/images/op_memo_blue.png">
						<input type="radio" id="flag5" name="clientServiceRemarkFlag" value="5" style="width:15px;border:2px;">
						<img src="${webuiPath}/images/op_memo_purple.png">
					</span>
				</td>
			</tr>
		 	<tr>
				<td width="100%" style="padding:0">
					<textarea id="clientServiceRemark" name="clientServiceRemark" rows="5" style="overflow: auto ;width:98%;height:100%  padding: 0; border-width: 0px; margin-bottom: 0px; margin-top: 0px;"></textarea>
				</td>
			</tr>	
	    </table>
		<div class="buttonArea" style="margin-top:10px;">
			<input id=btnClose class="formButton" type=button onclick="updateClientServiceRemark()" value="保存"/>
    		<input id=btnClose class="formButton" type=button onclick="closeDiv('updateClientServiceRemarkDiv')" value="关闭"/>
		</div>

	 </div>
	 
	 <div id="splitOrderDiv" style="display:none" class="popDiv">
			<h2><span class="icon">&nbsp;</span>订单拆单</h2>
			<div class="blank"></div>
			<div style="clear:both;"></div>	
			<#assign width=-50 />	
			<div class="blank"></div>
			<table class="inputTable">
				<tr>
					<th>
						拆单类型:
					</th>
					<td>
						<input id="r1" type="radio" name="splitType" value="sku" checked="checked" onclick="refreshSku(this.value);">根据sku拆单(每个sku拆成一单)
					</td>
				</tr>
				<tr>
					<th>
					</th>
					<td>
						<input id="r1" type="radio" name="splitType" value="assignSku" onclick="refreshSku(this.value);">指定sku拆单
					</td>
					<input type="hidden" id="splitType" value="sku">
				</tr>
				<tr id="sku_tr" style="display:none">
					<th>
						sku:
					</th>
					<td>
						<input id="splitSku"  name="splitSku">
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="button" class="formButton" value="确  定" onclick="splitOrder();" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="closeDiv('splitOrderDiv')" value="关   闭" hidefocus="true" />
			</div>
	</div>
</body>
</html>