<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心首页 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/resources/admin/css/admin.css" rel="stylesheet" type="text/css" />
</head>
<body class="admin">
	<div class="adminBar">
		<span class="icon">&nbsp;</span>欢迎使用天虹电子商务系统！
	</div>
	<div class="body">
		<div class="bodyLeft">
	
			<div class="blank"></div>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="4">
						待办事项：
					</th>
				</tr>
				<tr>
					<td width="110">
						质检失败:
					</td>
					<td>
						${orderReport.inspectFailedOrderCount} 条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/order/order.action?column=0">[处理]</a>
					</td>
				</tr>
				<tr>
					<td width="110">
						退货：
					</td>
					<td>
						${orderReport.toReturnOrderCount} 条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/order/sale_after_order/refund.action?column=-1">[处理]</a>
					</td>
				</tr>
				
				<tr>
					<td width="110">
						换货：
					</td>
					<td>
						${orderReport.toChangeOrderCount} 条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/order/sale_after_order/refund.action?column=-1">[处理]</a>
					</td>
				</tr>
				
				<tr>
					<td width="110">
						拒收：
					</td>
					<td>
						${orderReport.toRefundOrderCount} 条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/order/sale_after_order/refund.action?column=-1">[处理]</a>
					</td>
				</tr>
				
				<tr>
					<td width="110">
						待审核：
					</td>
					<td>
						${orderReport.toAuditOrderCount} 条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/order/order.action?column=1">[处理]</a>
					</td>
				</tr>
				<tr>
					<td width="110">
						预警：
					</td>
					<td>
						${orderReport.warnOrderCount} 条&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${base}/order/order.action?column=0">[处理]</a>
					</td>
				</tr>
			</table>
		</div>
		<div class="bodyRight">
	
			<div class="blank"></div>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="4">
						信息统计
					</th>
				</tr>
				<tr>
					<td width="110">
						 昨日总订单量：
					</td>
					<td>
						${orderReport.orderCount} 条
					</td>
				</tr>
				<tr>
					<td>
						退货订单总量:
					</td>
					<td>
						${orderReport.returnOrderCount} 条
					</td>
				</tr>
				<tr>
					<td>
						换货订单总量:
					</td>
					<td>
						${orderReport.changeOrderCount} 条
					</td>
				</tr>
				<tr>
					<td>
						拒收订单总量:
					</td>
					<td>
						${orderReport.refundOrderCount} 条
					</td>
				</tr>
			</table>
		</div>
		<p class="copyright">Copyright Â© 2012 ibm.com All Rights Reserved.</p>
	</div>
</body>
</html>