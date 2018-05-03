<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新增订单审核店铺配置 - Powered By ${systemConfig.systemName}</title>
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
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>新增订单审核店铺配置</h1>
		</div>
			<div class="blank"></div>
			<div style="clear:both;"/>	
			<#assign width=-50 />	
			<form id="inputForm" class="validate" action="order_audit_merchant_config!create.action" method="post">
			<div class="blank"></div>
			<table class="inputTable">
				<tr>
					<th>
						店铺名称:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.merchantName" id="merchantName" class="formText {required: true, minlength: 1, maxlength: 64}" title="长度不能超过64" />
						<label class="requireField">*</label>					
					</td>
				</tr>
				<tr>
					<th>
						店铺编码:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.merchantCode" id="merchantCode" class="formText {required: true, minlength: 1, maxlength: 64}" title="长度不能超过64" />
						<label class="requireField">*</label>					
					</td>
				</tr>
				<tr>
					<th>
						是否启用:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.enabled" id="enabled" type="checkbox" value="true"/>
					</td>
				</tr>
				<tr>
					<th>
						金额下限:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.minAmount" id="minAmount"/>
					</td>
				</tr>
				<tr>
					<th>
						金额上限:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.maxAmount" id="maxAmount"/>
					</td>
				</tr>
				<tr>
					<th>
						拆分单免审:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.isApprovedOrderSplit" id="isApprovedOrderSplit" type="checkbox" value="true"/>
					</td>
				</tr>
				<tr>
					<th>
						忽略顾客留言:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.isIgnoredClientRemark" id="isIgnoredClientRemark" type="checkbox" value="true"/>
					</td>
				</tr>
				<tr>
					<th>
						忽略客服备注:
					</th>
					<td>
						<input name="orderAuditMerchantConfig.isIgnoredClientServiceRemark" id="isIgnoredClientServiceRemark" type="checkbox" value="true"/>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.location.href='order_audit_config!view.action#merchantConfigDiv';" value="返  回" hidefocus="true" />
			</div>
		</form>	
	</div>
		
</body>

</html>