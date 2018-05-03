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
<link href="${base}/resources/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${baseWeb}/scripts/jquery/themes/base/jquery-ui.css" id="theme">

<link rel="stylesheet" href="${baseWeb}/scripts/jquery/themes/base/jquery.ui.dialog.css" >
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.pager.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.metadata.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.cn.js"></script> 
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/jquery.PrintArea.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery-calendar.js"></script>
<script type="text/javascript">
	var base = '${base}';
	$().ready( function() {
		$.datepicker.regional[ "zh-CN" ];
		$.subscribe('tabchange', function(event,data) {
			$("#currentStatus").val(event.originalEvent.ui.tab.parentNode.id);
			$("#selected").val(event.originalEvent.ui.index);
			$("#selectedTabName").val(event.originalEvent.ui.id);
			$("#searchButton2").click();
	    });
    });
	
	function managerButton(currentStatus){
		switch(currentStatus) {
		case 'all':
			break;
		case 'submitted':
			break;
		case 'partlyPaid':
			break;
		case 'waitingManualApproval':
			break;
		case 'waitingShip':
			break;
		case 'shipping':
			break;
		case 'shipped':
			break;
		case 'received':
			break;
		case 'completed':
			break;
		case 'invalidRefunding':
			break;
		case 'invalid':
			break;
		case 'outOfStock':
			break;			
		}

	}
	
	function showDialog(message,_buttons){
		$("#dialog-confirm" ).html(message);
		if($("#dateInput"))
			$("#dateInput").datepicker({ dateFormat: 'yy-mm-dd',maxDate: new Date() });
		$( "#dialog-confirm" ).dialog({
			resizable: false,
			height:140,
			modal: true,
			buttons:_buttons
		});
	}
	
	function change(){
		
	}
	
	function changeAddress(){
		
	}
	
	function approve(){
		ajaxAction(base+'/order/order!approve.action?event=approved');
	}
	
	function preNotApprove(){
		var message="请输入不通过原因<input id='reasonInput' type=text ></input>";
		showDialog(
			message,{
				"取消": function(){
					$( this ).dialog( "close" );
				}, 
				"确定": function() {
					var faileReason=$("#resonInput").val();
					notApprove(faileReason);
					$( this ).dialog( "close" );
				}
			}
		)
	}
	
	function notApprove(faileReason){
		ajaxAction(base+'/order/order!approve.action?event=not-approved&faileReason='+faileReason);
	}	
	
	function preInvalid(){
		var message="请输入作废原因<input id='reasonInput' type=text ></input>";
		showDialog(
			message,{
				"取消": function(){
					$( this ).dialog( "close" );
				}, 
				"确定": function() {
					var faileReason=$("#resonInput").val();
					invalid(faileReason);
					$( this ).dialog( "close" );
				}
			}
		)
	}
	
	function invalid(faileReason){
		ajaxAction(base+'/order/order!invalid.action?event=invalid&faileReason='+faileReason);
	}
	
	function preReceive(){
		var message="请输入收货时间<input id='dateInput' type=text ></input>";
		showDialog(
			message,{
				"取消": function(){
					$( this ).dialog( "close" );
				}, 
				"确定": function() {
					var takeDeliveryDate=$("#dateInput").val();
					receive(takeDeliveryDate);
					$( this ).dialog( "close" );
				}
			}
		)
	}
	
	function receive(takeDeliveryDate){
		ajaxAction(base+'/order/order!receive.action?event=receive&takeDeliveryDate='+takeDeliveryDate);
	}

	function preStockRemoval(){
		var message="请输入出库时间<input id='dateInput' type=text ></input>";
		showDialog(
			message,{
				"取消": function(){
					$( this ).dialog( "close" );
				}, 
				"确定": function() {
					var shippedDate=$("#dateInput").val();
					stockRemoval(shippedDate);
					$( this ).dialog( "close" );
				}
			}
		)
	}

	function stockRemoval(shippedDate){
		ajaxAction(base+'/order/order!stockRemoval.action?event=invalid&shippedDate='+shippedDate);
	}
	
	function preOutOfStock(){
		var message="请输入缺货原因<input id='reasonInput' type=text ></input>";
		showDialog(
			message,{
				"取消": function(){
					$( this ).dialog( "close" );
				}, 
				"确定": function() {
					var faileReason=$("#resonInput").val();
					outOfStock(faileReason);
					$( this ).dialog( "close" );
				}
			}
		)
	}
	
	function outOfStock(faileReason){
		ajaxAction(base+'/order/order!outOfStock.action?event=outOfStock&faileReason='+faileReason);
	}
	
	function ajaxAction(_url){
		var $idsCheckedCheck = $(".list input[name='ids']:checked");
		$.ajax({
			  url: _url,
			  data: $idsCheckedCheck.serialize(),
			  dataType: "json",
			  cache: false,
			  async: false,
			  success: function(data){
			  	  $idsCheckedCheck.parent().parent().remove();
				  showDialog(data.message,
				  			{
								"确定": function() {
									$( this ).dialog( "close" );
								}
							})
			  }
		});
	}
	
	function propertyChange(event){
//		alert(event.explicitOriginalTarget.value);
	}
	
	function updateOrderState(obj){
		var status=obj.name;
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
	        $.get("update_order_state.action",{"id":id,"orderState":status},function(data){alert(data.memo);window.location.reload();},"json");
		}
	
	}
	
	function reSendOrder(obj){
		if($("input[name='ids']:checked").length!=1){
			alert("对不起，请选择一项进行处理");
			return;
		}else{
			$.ajax({
		        url: base + '/order/order!reSendOrder.action',
		        dataType: "json",
		        data:"orderId="+$("input[name='ids']:checked").val(),
		        async: false,
		        cache: false,
		        success: function (data){
		        	alert(data.message);
		        },
		        error: function(XMLHttpRequest, textStatus, errorThrown) {
		            //alert(XMLHttpRequest.status);
		            //alert(XMLHttpRequest.readyState);
		            //alert(textStatus);
		        	alert("重新发送订单失败，请稍后再试！错误代码["+textStatus+"]");
		        }
	      });
	   }
	}
	
	
	
	function searchBykeyword(){
    $("#pageNumber").val("1");
	$("#isadvancedList").val("false");
	$(".list form").submit();
}

	function printMainTableFun(){
	$("#MainTable").printArea();
	}
	function operatPaidOrderException(){
	if($("input[name='ids']:checked").length!=1){
			alert("对不起，请选择一项进行处理");
			return;
		}else{
			 if(confirm("请您务必确认此订单网银支付完成，且订单状态为“已提交”或“已作废”，\r\n\r\n并确认商品库存为可用，之后再点击确认！")){ 
				 var id=$("input[name='ids']:checked").val();
				 $.get("order_helper.action",
				 {"id":id},
				 function(data){
				 	if(data.operatResult=="success"){
				 		alert("执行成功！");
				 	}if(data.operatResult=="error"){ 
				 		alert("执行过程中出现异常，请联系管理员！");
				 	}if(data.operatResult=="other"){
				 		alert("选择的订单状态不为已提交或已作废状态。");
				 	}
				    window.location.reload();
				 },
				 "json");
			}else{
			    return false;
			}
		}
	}
</script>
<script type="text/javascript">
function orderSearch(){
	var form = document.getElementById("listForm");
	form.submit();
}
</script>
<script  type="text/javascript" src="${base}/template/admin/js/order_advanced_search.js"></script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>订单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="order.action" method="post" validate="true">
			<@sj.tabbedpanel id="localtabs" onChangeTopics="tabchange" selectedTab="${selected}" >
				  	<@sj.tab id="all" target="MainTable" label="全部" />
                <@security.authorize ifNotGranted="ROLE_Financial">
					<@sj.tab id="submitted" target="MainTable" label="已提交" />
					<@sj.tab id="paid" target="MainTable" label="付款完成" />
					<@sj.tab id="paiderror" target="MainTable" label="付款失败" />
					<@sj.tab id="wms_received" target="MainTable" label="接单处理中" />
					<@sj.tab id="wms_receivedfailed" target="MainTable" label="接单失败" />
					<@sj.tab id="wms_packaged" target="MainTable" label="已打包" />
					<@sj.tab id="shipped" target="MainTable" label="已发货" />
					<@sj.tab id="shipederror" target="MainTable" label="发货异常" />
					<@sj.tab id="completed" target="MainTable" label="已完成" />
					<@sj.tab id="closed" target="MainTable" label="已关闭" />
					<@sj.tab id="canceling" target="MainTable" label="申请取消" />
                </@security.authorize>
                 
                <@security.authorize ifAnyGranted="ROLE_Financial,ROLE_CALLCENTER,ROLE_DevelopDept_1,ROLE_ADMIN,ROLE_ROOT">
					<@sj.tab id="canceled" target="MainTable" label="已取消" />
                </@security.authorize>

                <@security.authorize ifNotGranted="ROLE_Financial">
					<@sj.tab id="canceledfail" target="MainTable" label="取消失败" />
					<@sj.tab id="outOfStock" target="MainTable" label="缺货等待" />
                </@security.authorize>

                <@security.authorize ifAnyGranted="ROLE_Financial,ROLE_CALLCENTER,ROLE_DevelopDept_1,ROLE_ADMIN,ROLE_ROOT">
					<@sj.tab id="refund" target="MainTable" label="已退款" />
                </@security.authorize>

				<@sj.tab id="invalid" target="MainTable" label="已作废" />
	    	<div id="MainTable">
	    	
	    	<div class="operateBar">
				<label>查找:</label>
				<select name="pager.property" onchange="propertyChange(event)">
					<option value="orderNo" <#if pager.property == "orderNo">selected="selected" </#if>>
						订单编号
					</option>
					<option value="member.username" <#if pager.property == "member.username">selected="selected" </#if>>
						会员
					</option>
					<option value="consignee" <#if pager.property == "consignee">selected="selected" </#if>>
						收货人
					</option>
					<option value="shipAddress" <#if pager.property == "shipAddress">selected="selected" </#if>>
						收货地址
					</option>
				</select>
				<select name="pager.symbol">
					<option value="equal" <#if pager.symbol = EQUAL>selected="selected" </#if>>
					=
					</option>
					<option value="like" <#if pager.symbol=LIKE>selected="selected" </#if>>
					like
					</option>
					<option value="greater" <#if pager.symbol=GREATER>selected="selected" </#if>>
					>
					</option>
					<option value="less" <#if pager.symbol=LESS>selected="selected" </#if>>
					<
					</option>
				</select>
				<label class="searchText"><input type="text" id="keywordInput" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button"  onclick="searchBykeyword();" id="searchButton2"  class="searchButton" value="" />
				<#include "/WEB-INF/template/admin/order/order_advanced_search.ftl" />
				<input type="hidden" id="hid_orderNo" name="hid_orderNo" value="${hid_orderNo}"/>
				<input type="hidden" id="hid_accountName" name="hid_accountName" value="${hid_accountName}"/>
				<input type="hidden" id="hid_consignee" name="hid_consignee" value="${hid_consignee}"/>
				<input type="hidden" id="hid_state_city_county" name="hid_state_city_county" value="${hid_state_city_county}"/>
				<input type="hidden" id="hid_consigneeMobile" name="hid_consigneeMobile" value="${hid_consigneeMobile}"/>
				<input type="hidden" id="hid_downOrderDate" name="hid_downOrderDate" value="${hid_downOrderDate}"/>
				<input type="hidden" id= "hid_memberEmail" name="hid_memberEmail" value="${hid_memberEmail}"/>
				<input type="hidden" id="hid_status" name="hid_status" value="${hid_status}"/>
				<input type="hidden" id= "hid_paymentMode" name="hid_paymentMode" value="${hid_paymentMode}"/>
				<input type="hidden" id= "hid_totalPaid" name="hid_totalPaid" value="${hid_totalPaid}"/>
				<input type="hidden" id= "isadvancedList" name="isadvancedList" value="${isadvancedList}"/>
				<input type="hidden" id= "hid_OrderPaymentDate" name="hid_OrderPaymentDate" value="${hid_OrderPaymentDate}"/>
				
				<input type="button" class="previewButton" name="advancedSearch" id="advancedSearch" onclick="orderAdvancedSearch();" value="高级搜索">

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
				        <#--<#if "${selected}"=="1">
							<input type="button"  class="formButton" name="submitted" onclick="updateOrderState(this);" value="订单取消" />
							</#if>-->
				<@security.authorize ifAnyGranted="ROLE_REDO_PAIDORDER,ROLE_ROOT"> 
					<#if "${currentStatus}"=="submitted" || "${currentStatus}"=="invalid">
					<input type="button" id="operatPaidOrderButton"  onclick="operatPaidOrderException()" value="处理已支付的异常订单"  />
					</#if>
                 </@security.authorize>
						<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROOT">
							<#if "${currentStatus}"=="paid">
							<input type="button"  class="formButton" name="paid" onclick="updateOrderState(this);" value="订单取消" />
							<input type="button"  class="formButton" name="paid" onclick="reSendOrder();" value="重新发送订单" />
							</#if>
							<#if "${currentStatus}"=="wms_received">
							<input type="button"  class="formButton"  name="wms_received" onclick="updateOrderState(this);" value="订单取消" />
							</#if>
						</@security.authorize>
						<@security.authorize ifAnyGranted="ROLE_Financial,ROLE_ADMIN,ROLE_ROOT">
							<#if "${currentStatus}"=="canceled">
							<input type="button"  class="formButton"  name="canceled" onclick="updateOrderState(this);" value="确认退款" />
                            <input type="button" class="previewButton" name="printMainTable" id="printMainTable" onclick="printMainTableFun();" value="打印">
							</#if>
						</@security.authorize>
				<#--<input type="button" id="changeSkuButton" class="formButton" onclick="change()" value="更换"  />
				<input type="button" id="changeAddressButton" class="formButton" onclick="changeAddress()" value="更改地址"  />
				<input type="button" id="approveButton" class="formButton" onclick="approve()" value="审核通过"  />
				<input type="button" id="notApproveButton" class="formButton" onclick="preNotApprove()" value="审核不通过" />
				<input type="button" id="stockRemovalButton" class="formButton" onclick="preStockRemoval()" value="出库"  />
				<input type="button" id="receiveButton" class="formButton" onclick="preReceive()" value="确认收货"  />
				<input type="button" id="outOfStockButton" class="formButton" onclick="preOutOfStock()" value="缺货"  />
				<input type="button" id="invalidButton" class="formButton"  onclick="preInvalid()" value="作废"  />-->
			</div>			
			<br><br>
			
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<td>订购人信息:<input type="text" id="memberNo" name="orderMain.memberNo" value="${orderMain.memberNo!}" /></td>
					<td>订单编号：<input type="text" id="orderNo" name="orderMain.orderNo" value="${orderMain.orderNo!}" /></td>
					<td>订单类型：
						<select name="pager.orderType">
							<option value="-1" >全部</option>
							<option value="1" >普通订单</option>
							<option value="2" >虚拟订单</option>
						</select>
					</td>
					<td>订单来源：
						<select name="pager.orderSource">
							<option value="-1" >全部</option>
							<option value="1" >B2C</option>
							<option value="2" >移动app</option>
							<option value="3" >微信</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>支付方式：
						<select name="payType">
							<option value="-1" >全部</option>
							<option value="1" >网银在线支付</option>
							<option value="2" >招商银行</option>
							<option value="3" >工商银行</option>
						</select>
					</td>
					<td>配送方式：
						<select name="deliveryType">
							<option value="-1" >全部</option>
							<option value="1" >天虹配送</option>
							<option value="2" >天虹门店自提</option>
							<option value="3" >网上天虹分拣中心自提</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="button" class="previewButton" name="search" id="search" onclick="orderSearch();" value="查询">
					</td>
				</tr>	
			</table>
			<br>
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span class="sort" name="id">ID</span>
					</th>
					<th>
						<span class="sort" name="orderNo">订单编号</span>
					</th>
					<th>
						<span class="sort" name="memberNo">会员</span>
					</th>
					<th>
						<span class="sort" name="submitDate">下单日期</span>
					</th>
					<th>
						<span class="sort" name="totalAmount">订单总额</span>
					</th>
					<th>
						<span class="sort" name="totalPaid">已付金额</span>
					</th>
					<th>
						<span class="sort" name="referenceNo">银行流水号</span>
					</th>
					<th>
						<span name="consignee">收货人</span>
					</th>
					<th>
						<span class="sort" name="status">订单状态</span>
					</th>
					<th>
						<span name="paymentConfigName">支付方式</span>
					</th>
					<th>
						<span class="sort" name="deliveryTypeName">配送方式</span>
					</th>
					<th>
						操作
					</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${(list.id)!}" />
						</td>
						<td>
							${list.id}
						</td>
						<td>${list.orderNo}</td>
						<td><#--<#if list.member??>${(list.member.accountName)!""}<#else>用户不存在</#if>-->
							${list.memberNo}
						</td>
						<td>
							<span title="${(list.dateCreated?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.dateCreated?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>
						<td>
							${(list.totalAmount?string(orderUnitCurrencyFormat))!}
						</td>
						<td>
							${(list.totalPaid?string(orderUnitCurrencyFormat))!}
						</td>
						<td>
							<#if list.paymentMode.id!= "4"> <!--非工行-->
							   ${(list.payments[0].referenceNo)!}
							<#else><!--工行-->
							   ${(list.referenceNo)!}
							</#if>
						</td>							
						<td>${(list.consignee)!}</td>	
						<td>
							${list.status.display()}
						</td>					
						<td>
							${(list.paymentMode.name)!}
						</td>						
						<td>
							${list.shipMode.name}
						</td>
						<td>
							<a href="order!view.action?orderId=${list.id}" title="查看">[查看]</a>
							<#--
							<#if list.orderStatus != "completed" && list.orderStatus != "invalid" && list.paymentStatus == "unpaid" && list.shippingStatus == "unshipped">
								<a href="order!edit.action?orderId=${list.id}" title="编辑">[编辑]</a>
							<#else>
								<span title="订单状态无法编辑">[编辑]</span>
							</#if>
							<a href="order_change!list.action?id=${list.id}" title="更换">[更换]</a>
							<a href="order_change_address!view.action?id=${list.id}&memberId=${(list.member.id)!''}" title="更改地址">[更改地址]</a>
							-->
							
						
						</td>
					</tr>
				</#list>
			</table>
			<input  type="text" id="currentStatus" name="currentStatus" value="${currentStatus}" style="display:none"></input>
			<input  type="text" id="selected" name="selected" value="${selected}" style="display:none"></input>  
			<input  type="text" id="selectedTabName" name="selectedTabName" value="${selectedTabName}" style="display:none"></input>  
			</div>
			</@sj.tabbedpanel>	
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