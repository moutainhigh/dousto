<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<#-- security.tld 角色权限控制 -->
<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>虚拟订单列表 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
    
    <script src="${base}/scripts/list.js"></script> 
    <!-- 按“Enter”键，搜索 -->
    <script src="${base}/scripts/list_search.js"></script> 

    <script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
    
	

    

<script type="text/javascript">

$().ready( function() {
	
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
<body class="list">
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
		<h4 id="searchCondition" style="border:solid 1px #ddd;background:#eee;padding:5px">
			<span id="searchConditionIcon" class="displaySearchCondition"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搜索条件
		</h4>
		<div id="conditions" style="margin:0;padding:0;">
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 100%;">
				<tr>
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
					<td>商品关键字：<input type="text" id="commodityName" name="orderItem.commodityName" value="${orderItem.commodityName!}" /></td>
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
					<td>物流状态：&nbsp;&nbsp;&nbsp;&nbsp;<select name="orderSub.logisticsStatus">
						<#list logisticsStatusList as list>
							<option value="${list.statusCode}"<#if list.statusCode == orderSub.logisticsStatus> selected </#if>>
								<#if list.statusCode == "">
									全部
								<#else>
									${list.displayName}
								</#if>
							</option>
						</#list>
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
					<td>
						收货人地址：&nbsp;&nbsp;&nbsp;<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />
					</td>
					-->
				</tr>
				<tr>
					<td>收货人信息：<input type="text" id="userName" name="orderSub.receiverInfo" value="${orderSub.receiverInfo!}" /></td>
					<td colspan="2">下单日期：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeFrom" name="orderMain.orderTimeFrom" value="${(orderMain.orderTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="orderTimeTo" name="orderMain.orderTimeTo" value="${(orderMain.orderTimeTo?string("yyyy-MM-dd"))!}"></td>
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
					<td colspan="2">支付方式：
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
							<option value="" <#if '' == orderPay.payCode> selected </#if>>
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
								<#if list.id != '830'>
								<option value="${list.id}"<#if list.id == orderPay.payCode> selected </#if>>
									<#if list.id == "">
									请选择
									<#else>
										${list.name}
									</#if>
								</option>
								</#if>
							</#list>
						</select>
					</td>
				</tr>
			    <tr>
			    	<td colspan="2">
			    		自提点：<#include "/WEB-INF/template/admin/order/selfTakePoint_linkage.ftl">
			    	</td>
					<td colspan="2">配送地址：<#include "/WEB-INF/template/admin/order/address_linkage.ftl"><!--<input type="text" id="addressDetail" name="orderSub.addressDetail" value="${orderSub.addressDetail!}" />-->
					</td>
					
				</tr>
				<tr>
					<td>
						会员信息：<input type="text" id="memberInfo" name="orderMain.memberInfo" value="${orderMain.memberInfo!}" /></td>
					</td>
					<td colspan="3">
						订单完成时间：从 <input type="text" readonly="true"  onclick="WdatePicker()" id="finishTimeFrom" name="orderMain.finishTimeFrom" value="${(orderMain.finishTimeFrom?string("yyyy-MM-dd"))!}">至 <input type="text" readonly="true"  onclick="WdatePicker()" id="finishTimeTo" name="orderMain.finishTimeTo" value="${(orderMain.finishTimeTo?string("yyyy-MM-dd"))!}"></td>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="button_red" name="search" id="search" onclick="orderSearch();" value="查询">
					</td>
				</tr>	
				<tr>
					<td colspan="4">
							<#if column == 1>
							<input type="button" class="previewButton" <#if (pager.list?size <= 0)>disabled</#if> name="confirm" id="confirm" onclick="orderConfirm();" value="审核通过">
							</#if>
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
								<input type="button" class="previewButton" <#if (pager.list?size <= 0)>disabled</#if> name="cancel" id="cancel" onclick="orderCancel();" value="取消">
							</span>
							</#if>
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
			<table class="listTable detailTable"  style="border: 1px solid #CCCCCC;width:1412px;/*width:135%*/">
				<tr>
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
					<th width="70px">
						<span class="sort" name="submitDate">下单时间</span>
					</th>
					<!--
					<th>
						<span class="sort" name="totalAmount">订单总额</span>
					</th>
					-->
					<th width="160px">
						<span class="sort" name="statusConfirm">订单状态</span>
					</th>
					<th width="75px">
						<span class="sort" name="totalPayAmount">订单总金额</span>
					</th>
					<th width="120px">
						<span name="payName">支付方式</span>
					</th>
					<th width="65px">
						<span class="sort" name="distributeType">配送方式</span>
					</th>
					<th width="280px">
						<span class="sort" name="orderItem">商品</span>
					</th>
					<th width="50px">
						<span class="sort" name="confirmerName">审核人</span>
					</th>
					<th  width="105px">
						<span class="sort" name="orderNo">外部渠道订单号</span>
					</th>
					<th width="70px">
						<span class="sort" name="orderSource">订单渠道</span>
					</th>
					<th width="40px">
						操作
					</th>
				</tr>
				<#list pager.list as list>
					<tr ondblclick="window.open('order!view.action?orderNo=${list.orderNo}&column=${column}')">
						<td>
							 <#if list.orderCategory != 'chgOut'>
								<input type="checkbox" name="ids" value="${(list.orderNo)!}" />
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
						<td>
							<span style="/*border:1px solid red;*/width:65px;display:block;" title="${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.orderTime?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
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
							审核状态：<font color="red">${(list.statusConfirmName)!}</font> <br/>
							支付状态：<font color="#104E8B">${list.statusPayName!}</font> <br/>
							物流状态：<font color="#008B8B">${(list.logisticsStatusName)!}</font> <br/>
							处理状态：<font color="#CD661D">${(list.statusTotalName)!}</font>
						</td>
						<td>
							<#if (list.totalPayAmount)??><font color="red">${((list.totalPayAmount)!0)?string.currency}</font>
							</#if>
							<input type="hidden" id="totalPayAmountTmp" value="${(list.totalPayAmount)!0}"/>
						</td>
						
						<td>
								<#list list.orderPayInfoList as list>
									<#if (list.payNo)??>
										支付号：${list.payNo}<br/>
									</#if>	
							      		${list.payName} : <font color="red">${(list.payAmount!0)?string.currency}</font> <br/>
							      		<input type="hidden" id="payNameTmp" value="${list.payName}"/>
							      		<input type="hidden" id="payAmountTmp" value="${(list.payAmount!0)}"/>
								</#list>
						</td>
						<td>
							${(list.distributeTypeName)!} <br/>
						</td>
						<td>
							<#if (list.orderItemVirtuals)??>
								<#list list.orderItemVirtuals as item>
									<#if (item.productName)??>
									<span style="width:190px; display:inline-block;line-height:20px;">
									<font color="#104E8B">${(item.productName)!}</font> 
										<#if (item.receiveMobile)??>
										(电话:<font color="gray">${(item.receiveMobile)!}</font> )
										</#if>
									</span>
									&nbsp;&nbsp;&nbsp;&nbsp;  <font color="#CD661D">${(item.saleNum)!}</font> &nbsp;&nbsp;&nbsp;&nbsp; <font color="red">${((item.unitPrice)!0)?string.currency}</font><font color="#008B8B"></font><br/>
									</#if>
								</#list>
							</#if>
						</td>
						<td>${(list.confirmerName)!}</td>
						<td><span style="width:105px;display:block;word-wrap:break-word;">${list.aliasOrderNo}</span>
						</td>
						<td>
							${(list.orderSourceName)!}
						</td>
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
			<input type="hidden" name="orderSub.logisticsStatus" value="${orderSub.logisticsStatus!}"/>
			<input type="hidden" name="orderMain.statusTotal" value="${orderMain.statusTotal!}"/>
			<input type="hidden" name="orderMain.orderSource" value="${orderMain.orderSource!}"/>
			<input type="hidden" name="orderMain.orderType" value="${orderMain.orderType!}"/>
			<input type="hidden" name="orderPay.payCode" value="${orderPay.payCode!}"/>
			<input type="hidden" name="orderSub.distributeType" value="${orderSub.distributeType!}"/>
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
</body>
</html>