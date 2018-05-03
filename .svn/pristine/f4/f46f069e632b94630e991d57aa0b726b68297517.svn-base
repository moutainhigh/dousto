<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>退货单列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${baseWeb}/scripts/jquery/themes/base/jquery-ui.css" id="theme">
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.pager.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.metadata.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.cn.js"></script> 
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.blockUI.js"></script> 
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>
<script type="text/javascript" src="${base}/template/admin/js/jquery.PrintArea.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery-calendar.js"></script>
<#include "/WEB-INF/template/common/jquery-ui-dialog.ftl" />
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('tabchange', function(event,data) {
		$("#currentStatus").val(event.originalEvent.ui.tab.parentNode.id);
		$("#selected").val(event.originalEvent.ui.index);
		$("#searchButton").click();
    });
    var paym = $("#selectProperty option:selected").val();
   if(paym=="paymentMethod"){
	var paymentMethodList="<select name='pager.keyword'><option value=''>请选择---</option><option value='2'<#if pager.keyword == '2'>selected='selected' </#if> >支付宝</option><option value='3'<#if pager.keyword == '3'>selected='selected' </#if> >招商银行---网上支付</option><option value='4'<#if pager.keyword == '4'>selected='selected' </#if> >工商银行---网上支付</option><option value='5'<#if pager.keyword == '5'>selected='selected' </#if> >农业银行---网上支付</option><option value='6'<#if pager.keyword == '6'>selected='selected' </#if> >中国银行---网上支付</option><option value='7'<#if pager.keyword == '7'>selected='selected' </#if> >建设银行---网上支付</option><option value='8'<#if pager.keyword == '8'>selected='selected' </#if> >交通银行---网上支付</option><option value='9' <#if pager.keyword == '9'>selected='selected' </#if> >中国银联---网上支付</option></select>"
	$(".searchText").html(paymentMethodList)
	}else{
		$(".searchText").html("<input type='text' name='pager.keyword' value='${pager.keyword!}' />")
	}
});
function findByPaymentMethod(){
var pm = $("#selectProperty option:selected").val();
	if(pm=="paymentMethod"){
	var paymentMethodList="<select name='pager.keyword'><option value=''>请选择---</option><option value='2'>支付宝</option><option value='3'>招商银行---网上支付</option><option value='4'>工商银行---网上支付</option><option value='5'>农业银行---网上支付</option><option value='6'>中国银行---网上支付</option><option value='7'>建设银行---网上支付</option><option value='8'>交通银行---网上支付</option><option value='9' >中国银联---网上支付</option></select>"
	$(".searchText").html(paymentMethodList)
	}else{
		$(".searchText").html("<input type='text' name='pager.keyword' />")
	}
}
function printMainTableFun(){
	$("#MainTable").printArea();
	}
</script>
<script type="text/javascript" src="${base}/scripts/reship/updateRmorderState.js"></script>
</head>
<body class="list">
<div id="flowTraceDiv" style="display:none"></div>
<div id="carrier_trackNo" style="display:none">
	<table>
		<tr>
			<td align="center" colspan="2">请填写客户邮寄的物流公司名称和运单号</td>
			
		</tr>
			<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">物流公司名称：</td>
			<td align="left"><select id="carrier" name="carrier">
			<option value="">---- 请选择 ----</option>
			<#list carrierList as carrier>
			<option value="${carrier.code}">${carrier.name}</option>
			</#list>
			</select>
			*</td>
		</tr>
	<tr><td>&nbsp;</td></tr>
		<tr>
			<td align="right">运  单   号：</td>
			<td align="left"><input id="trackNo" type="text" />*</td>
		</tr>
		<tr>
			<td><input id="btnOk" type="button" value="确定" /></td>
			<td><input id="btnCancel" type="button" value="取消" /></td>
		</tr>
	</table>
</div>



	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>退货单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>
		<div class="operateBar">
		<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />
		<#if false>
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROOT">
			<input type="button" class="addButton" onclick="location.href='reship!add.action'" value="添加退货单" />
			</@security.authorize>
		</#if>
			<input type="button" class="otherButton2" onclick="showFlowTrace();" value="查看流程跟踪" />
		</div>
		<form id="listForm" action="reship!list.action" method="post">
			<@sj.tabbedpanel id="localtabs" onChangeTopics="tabchange" selectedTab="${selected}" >
				<@sj.tab id="all" target="MainTable" label="全部" />
            <@security.authorize ifNotGranted="ROLE_Financial">
				<@sj.tab id="submitted" target="MainTable" label="已提交" />
				<@sj.tab id="approved" target="MainTable" label="审核已通过" />
				<@sj.tab id="notapproved" target="MainTable" label="审核未通过" />
				<@sj.tab id="sendedwms" target="MainTable" label="已发送给WMS" />
				<@sj.tab id="wms_received" target="MainTable" label="WMS接单" />
				<@sj.tab id="wms_fulfilled" target="MainTable" label="收货完成" />
				<@sj.tab id="wms_rejected" target="MainTable" label="仓库拒收" />
				<@sj.tab id="wms_closed" target="MainTable" label="关闭" />
            </@security.authorize>

            <@security.authorize ifAnyGranted="ROLE_Financial,ROLE_CALLCENTER,ROLE_DevelopDept_1,ROLE_ADMIN,ROLE_ROOT">
				<@sj.tab id="pendingrefund" target="MainTable" label="等待退款" />
            </@security.authorize>

			<#--<@sj.tab id="fefunded" target="MainTable" label="已退款" />-->
			<@sj.tab id="completed" target="MainTable" label="已完成" />

				<div id="MainTable">
					<div class="operateBar">
						<label>查找:</label>
						<select id="selectProperty" name="pager.property" onchange="findByPaymentMethod();">
							<option value="rmaorderSn" <#if pager.property == "rmaorderSn">selected="selected" </#if>>
								退货订单号
							</option>
							<option value="orderNo" <#if pager.property == "orderNo">selected="selected" </#if>>
								订单号
							</option>
							<option value="memberAccountName" <#if pager.property == "memberAccountName">selected="selected" </#if>>
								会员
							</option> 
							<option value="paymentMethod" <#if pager.property == "paymentMethod">selected="selected" </#if>>
								支付方式
							</option> 
						</select>
						<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button" id="searchButton" class="searchButton" value="" />
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
						<!--
									具有"退货单审核"角色的用户可以对rma_rmaorder.rmaorder_status=submitted("已提交")的退货单做驳回或者审核通过操作:
									-->
									<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROOT,ROLE_CALLCENTER">
									<#if "${currentStatus}"=="submitted">

									<input type="button"  onclick="reshipUpdateState(this);" value="审核通过 " name="approved" class="batchButton">
									<input type="button"  onclick="reshipUpdateState(this);"  value="驳 回" name="notapproved" class="batchButton">
									</#if>
									<#if "${currentStatus}"=="approved">
									
									<!--具有"退货单下传"角色的用户收到rma_rmaorder.rmaorder_status=approved("审核已通过")的退货单，线下通知用户邮寄退货商品，并获取用户邮寄退货物品的物流公司名称以及运单号，可以执行”退货单下传”的操作-->
									<input type="button" onclick="reshipUpdateState(this);"  value="退货单下传" name="sendedwms" class="batchButton">
									
									</#if>
								</@security.authorize>	
								<@security.authorize ifAnyGranted="ROLE_REFUND,ROLE_ADMIN,ROLE_ROOT">
									<#if "${currentStatus}"=="wms_fulfilled">
									<input type="button" onclick="reshipUpdateState(this);"  value="等待退款" name="pendingrefund">
									</#if>
									<#if "${currentStatus}"=="wms_rejected">
									<!--具有"退货单审核"角色的用户可以对rma_rmaorder.rmaorder_status=wms_rejected("仓库拒收")的退货单做"驳回"操作-->
									<input type="button" onclick="reshipUpdateState(this);"  value="驳回" name="wms_rejected">
									</#if>
									<#if "${currentStatus}"=="wms_closed">
									<!--具有"退货单审核"角色的用户可以对rma_rmaorder.rmaorder_status=wms_closed("关闭")?的退货单做"等待退款"操作(退货单状态置为pendingrefund("等待退款"))-->
									<input type="button" onclick="reshipUpdateState(this);"  value="等待退款" name="pendingrefund">
									</#if>
								</@security.authorize>
								<@security.authorize ifAnyGranted="ROLE_Financial,ROLE_ADMIN,ROLE_ROOT">
									<#if "${currentStatus}"=="pendingrefund">
									<!--具有"退货单退款"角色的用户可以对rma_rmaorder.rmaorder_status=pendingrefund("等待退款")的退货单做"确认退款"操作(退货单状态置为completed("确认退货"))，并调用"确认退货"接口通知进销存系统退货完成-->
									<input type="button"  onclick="reshipUpdateState(this);"  value="确认退款" name="completed">	
									<input type="button" class="previewButton" name="printMainTable" id="printMainTable" onclick="printMainTableFun();" value="打印">
									</#if>	
								</@security.authorize>		
					</div>
					<table class="listTable">
						<tr>
							<th class="check">
								<input type="checkbox" class="allCheck" />
							</th>
							<th>
								<span  name="rmaorderSn">退货订单号</span>
							</th>
							<th>
								<span   name="orderNo">订单号</span>
							</th>
							
							<th>
								<span  name="paymentModeName">支付方式</span>
							</th>
							<th>
								<span   name="rmaorderStatus">状态</span>
							</th>
							<th>
								<span name="submitDate">提交时间</span>
							</th>
							<th>
								<span name="returnDate">退款时间</span>
							</th>
							<th>
								<span  name="totalProductAmount">订单总额</span>
							</th>
							<th>
								<span  name="totalPaid">已付金额</span>
							</th>
							<th>
								<span  name="returnProductAmount">退货总额</span>
							</th>
							<th>
								<span   name="totalReturnAmount">应退总额</span>
							</th>
							<th>
								<span  name="totalRmaQuantity">数量</span>
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
									${list.rmaorderSn}
								</td>
								<td>
									${list.orderNo}
								</td>
								
								<td>
									${list.order.paymentMode.name}
								</td>
								<td>
								<#if "${currentStatus}"=="completed">
								           已完成
								<#else>
									${list.rmaorderStatus.display()}
								</#if>
								</td>
								<td>
									<span title="${list.submitDate?string("yyyy-MM-dd HH:mm:ss")}">${list.submitDate}</span>
								</td>
								<td>
									<span title="${list.returnDate?string("yyyy-MM-dd HH:mm:ss")}">${list.returnDate}</span>
								</td>
								<td>
									${list.totalProductAmount?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									${list.totalPaid?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									${list.returnProductAmount?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									${list.totalReturnAmount?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									${list.totalRmaQuantity}
								</td>
								<td>
									<a href="reship_view.action?id=${list.id}" title="查看">[查看]</a>
												
								</td>
							</tr>
						</#list>
					</table>
					<input type="text" id="currentStatus" name="currentStatus" value="${currentStatus}" style="display:none"></input>
					<input type="text" id="selected" name="selected" value="${selected}" style="display:none"></input>
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
		<div id="confrim_info" style="display:none;" title="提示">

		</div>
		<div id="result_info" style="display:none;" title="提示">

		</div>
</body>
</html>