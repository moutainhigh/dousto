<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>换货单列表 - Powered By ${systemConfig.systemName}</title>
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
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery-calendar.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$.subscribe('tabchange', function(event,data) {
		$("#currentStatus").val(event.originalEvent.ui.tab.parentNode.id);
		$("#selected").val(event.originalEvent.ui.index);
		$("#searchButton").click();
    });
});
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
			<h1><span class="icon">&nbsp;</span>换货单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>
		<div class="operateBar">
		<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />
		<#if false>
			<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROOT">
			<input type="button" class="addButton" onclick="location.href='exchange!add.action'" value="添加换货单" />
			</@security.authorize>
		</#if>
			<input type="button" class="otherButton2" onclick="showFlowTrace();" value="查看流程跟踪" />
		</div>
		<form id="listForm" action="exchange!list.action" method="post">
			<@sj.tabbedpanel id="localtabs" onChangeTopics="tabchange" selectedTab="${selected}" >
				<@sj.tab id="all" target="MainTable" label="全部" />
				<@sj.tab id="exc_submitted" target="MainTable" label="已提交" />
				<@sj.tab id="exc_approved" target="MainTable" label="审核已通过" />
				<@sj.tab id="exc_notapproved" target="MainTable" label="审核未通过" />
				<@sj.tab id="exc_sendedwms" target="MainTable" label="已发送给WMS" />
				<@sj.tab id="exc_wms_received" target="MainTable" label="WMS接单" />
				<@sj.tab id="exc_wms_reject" target="MainTable" label="接单失败" />
				<@sj.tab id="exc_wms_getgoods" target="MainTable" label="WMS已收货" />
				<@sj.tab id="exc_return" target="MainTable" label="拒收退回" />
				<@sj.tab id="exc_partreturn" target="MainTable" label="部分退回" />
				<@sj.tab id="exc_outofstock" target="MainTable" label="换货缺货" />
				<@sj.tab id="exc_shipped" target="MainTable" label="换货已出库" />
				<@sj.tab id="exc_completed" target="MainTable" label="换货完成" />
				<@sj.tab id="exc_exception" target="MainTable" label="发货异常" />
				<@sj.tab id="exc_closed" target="MainTable" label="关闭" />
				

				<div id="MainTable">
					<div class="operateBar">
						<label>查找:</label>
						<select name="pager.property">
							<option value="rmaorderSn" <#if pager.property == "rmaorderSn">selected="selected" </#if>>
								换货订单号
							</option>
							<option value="orderNo" <#if pager.property == "orderNo">selected="selected" </#if>>
								订单号
							</option>
							<option value="memberAccountName" <#if pager.property == "memberAccountName">selected="selected" </#if>>
								会员
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
							<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROOT,ROLE_CALLCENTER">
									<#if "${selected}"=="1">
									<input type="button"  onclick="reshipUpdateState(this);" value="审核通过 " name="exc_approved" class="batchButton">
									<input type="button" onclick="reshipUpdateState(this);"  value="驳 回" name="exc_notapproved" class="batchButton">
									</#if>
									<#if "${selected}"=="2">
									<input type="button" onclick="reshipUpdateState(this);"  value="换货单下传" name="exc_sendedwms" class="batchButton">
									</#if>
							</@security.authorize>	
							<@security.authorize ifAnyGranted="ROLE_ADMIN,ROLE_ROOT">		
							
									<#if "${selected}"=="5">
									<input type="button" onclick="reshipUpdateState(this);"  value="驳回" name="exc_notapproved" class="batchButton">
									</#if>
									<#if "${selected}"=="exc_closed">
									<input type="button" onclick="reshipUpdateState(this);"  value="换货完成" name="exc_completed" class="batchButton">
									</#if>
							</@security.authorize>		
					</div>
					<table class="listTable">
						<tr>
							<th class="check">
								<input type="checkbox" class="allCheck" />
							</th>
							<th>
								<span   name="rmaorderSn">换货订单号</span>
							</th>
							<th>
								<span   name="orderNo">订单号</span>
							</th>
							<th>
								<span   name="memberAccountName">会员</span>
							</th>
							
							<th>
								<span   name="rmaorderStatus">状态</span>
							</th>
							<th>
								<span   name="submitDate">提交时间</span>
							</th>
							<th>
								<span   name="totalProductAmount">原订单总额</span>
							</th>
							<th>
								<span   name="totalPaid">已付金额</span>
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
									${list.memberAccountName}
								</td>
								
								<td>
									${list.rmaorderStatus.display()}
								</td>
								<td>
									<span title="${list.submitDate?string("yyyy-MM-dd HH:mm:ss")}">${list.submitDate}</span>
								</td>
								<td>
									${list.totalProductAmount?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									${list.totalPaid?string(orderUnitCurrencyFormat)}
								</td>
								<td>
									<a href="exchange_view.action?id=${list.id}" title="查看">[查看]</a>
									
									
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