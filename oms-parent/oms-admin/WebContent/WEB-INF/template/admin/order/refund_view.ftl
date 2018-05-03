<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看退款单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
</head>
<body class="input list">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>查看退款单</h1>
		</div>
		<table class="inputTable">
			<tr>
				<th>
					退款单号:
				</th>
				<td>
					${refund.refundSn}
				</td>
				<th>
					状态:
				</th>
				<td>
					${refund.status.display()}
				</td>
				<th>
					生成时间
				</th>
				<td>
					${refund.dateCreated?string("yyyy-MM-dd hh:mm")}
				</td>									
			</tr>
			<tr>
				<th>
					订单编号:
				</th>
				<td>
					${(refund.order.orderNo)!}
				</td>			
				<th>
					退换货订单号:
				</th>
				<td>
					${(refund.rmaorderId)!}
				</td>
				<th>
					会员:
				</th>
				<td>
					${(refund.member.accountName)!}
				</td>
			</tr>
			<tr>
				<th>
					退款类型:
				</th>
				<td>
					${refund.refundType.display()}
				</td>
				<th>
					退款总额:
				</th>
				<td>
					${(refund.totalReturnAmount)?string(orderUnitCurrencyFormat)!}
				</td>
				<th>
					原订单预存款金额
				</th>
				<td>
					${((refund.totalPaidPrepay)!0)?string(orderUnitCurrencyFormat)}
				</td>				
			</tr>
		<#if refund.isRefunded()>
			<tr>
				<th>
					操作员:
				</th>
				<td>
					${(refund.operator)!}
				</td>
				<th>
					退款日期:
				</th>
				<td colspan="3">
					${((refund.refundDate)?string("yyyy-MM-dd HH:mm:ss"))!}
				</td>
			</tr>
		</#if>			
		</table>
		<table class="listTable">

				<tr>
					<th>
						<span>退款方式</span>
					</th>
					<th>
						<span>银行</span>
					</th>
					<th>
						<span>帐号</span>
					</th>
					<th>
						<span>收款人</span>
					</th>
					<th>
						<span>金额</span>
					</th>
				</tr>		
		<#list items as list>		
				<tr>
					<td>
						${(list.refundMethod.display())}
					</td>
					<td>
						${(list.bankName)!}
					</td>												
					<td>
						${(list.bankAccount)!}
					</td>												
					<td>
						${(list.payee)!}
					</td>
					<td>
						${(list.amount)?string(orderUnitCurrencyFormat)!}
					</td>															
				</tr>
		</#list>
		</table>
		<div class="buttonArea">
			<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		</div>
	</div>
</body>
</html>