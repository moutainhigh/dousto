<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign sjg=JspTaglibs["/WEB-INF/tlds/struts-jquery-grid-tags.tld"]>
<#assign sjr=JspTaglibs["/WEB-INF/tlds/struts-jquery-richtext-tags.tld"]>
<#assign baseWeb="/sc-webui">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>更改订单项 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<script type="text/javascript">
	function colorChange(event){
			$.ajax({
				  url: "${base}"+"/order/order_change!ajaxSkuList.action?commodityId="+event.explicitOriginalTarget.value,
				  cache: false,
				  success: function(result){
				  	  var html = ""
				  	  var res = eval(result);
				  	  for(i=0;i<res.length;i++){
				  	  		html=html+"<option value="+res[i].id+">"+res[i].size+"</option>"
				  	  }
					  $("#skuId").html(html);
				  }
			});
	}
</script>
</head>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>更改订单项</h1>
		</div>
		<form id="mainForm" action="${base}/order/order_change!change.action?oldId=${(orderId)}" method="post" class="validate">
			<table class="inputTable">
				<tr>
					<th>
						款号:
					</th>
					<td>
						${(orderItem.product.partNumber)!}
					</td>
					<th>
						订单编号:
					</th>
					<td>
						${(orderItem.order.orderNo)!}
					</td>
				</tr>
				<tr>
					<th>
						项目类型:
					</th>
					<td>
						${(orderItem.type.display())!}
					</td>
					<th>
						原数量:
					</th>
					<td>
						${(orderItem.quantity)!}
					</td>
				</tr>
				<tr>
					<th>
						颜色:
					</th>
					<td>
						<select  id="commodityId" name="commodityId" class="{required: true}" onchange="colorChange(event)" title="请选择颜色" >
							<#list commodityList as list>
								<option value="${list.id}"<#if (list == orderItem.sku.color)!> selected</#if>>
								${list.color}
								</option>
							</#list>
						</select>
					</td>
					<th>
						尺寸:
					</th>
					<td>
						<select id="skuId" name="skuId" class="{required: true}" title="请选择尺寸" >
							<#list skuList as list>
								<option value="${list.id}"<#if (list.size == orderItem.sku.size)!> selected</#if>>
								${list.size}
								</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						更换数量:
					</th>
					<td>
						<select id="sizeSel" name="quantity" class="{required: true}" title="请选择更换数量" >
							<#list 1..orderItem.quantity as list>
								<option value="${list}"<#if (list == orderItem.quantity)!> selected</#if>>
								${list}
								</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />
			</div>
		</form>				
	</div>
</body>
</html>