<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新建退货单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(document).ready(function() {

	$('#orderNo').focus(function() {
		$(this).select();
		$(this).removeClass('validateError');
		$('#msg').hide();
	});
	
	$('#orderNo').blur(checkOrderNo);
	
	$('#orderNo').keydown(function(event) {
		if (event.keyCode == '13') {
			return checkOrderNo();
		}
	});
	
	$('input[type="submit"]').click(checkOrderNo);
});

function checkOrderNo() {
	var that = $('#orderNo');
	var orderNo = that.val();
	var result = true;
	if (!!$.trim(orderNo)) {
		$.ajax({
			url: '${base}/order/reship!checkOrderStatus.action',
			async: false,
			data: {orderNo: orderNo},
			dataType: 'json',
			success: function(data) {
				if (data.status && data.status === 'error') {
					that.addClass('validateError');
					$('#msg').text(data.message);
					$('#msg').show();
					result = false;
				}
			}
		});
	}
	return result;
}
</script>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>新建退货单</h1>
		</div>
		<form id="inputForm" class="validate" action="reship_input.action" method="post">
			<table class="inputTable">
				<tr>
					<th><span>订单号: </span></th>
					<td>
						<input type="text" id="orderNo" name="orderNo" class="required"/>
						<label id="msg" class="validateError" style="display: none"/>
					</td>
					
					
					
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="下一步" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>