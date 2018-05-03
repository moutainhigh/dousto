<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign sjg=JspTaglibs["/WEB-INF/tlds/struts-jquery-grid-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑商品价格</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<script type="text/javascript" src="/sc-webui/scripts/jquery/js/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="/sc-webui/scripts/jquery/js/plugins/jquery.metadata.js"></script>
<script type="text/javascript" src="/sc-webui/scripts/jquery/js/plugins/jquery.validate.methods.js"></script>
<script type="text/javascript" src="/sc-webui/scripts/jquery/js/plugins/jquery.validate.cn.js"></script> 

<script type="text/javascript" src="/sc-webui/scripts/jquery/js/plugins/jquery-calendar.js"></script>
<script type="text/javascript">
	$.subscribe('gridInit', function() {
		var newData = new Array();
		
		$("td[aria-describedby='gridTable_delete']").each(function(i){
			$('#gridTable').jqGrid('editRow',$(this).parent().attr("id"), true);
			$(this).html("<input type='button' class='formButton' value='更换' onclick='change("+$(this).parent().attr("id")+")' />")
		})
		localData = newData;
		
    });
    function change(id){
    	location.href='${base}/order/order_change!view.action?orderId='+id;
    }
</script>
</head>
<body class="input">
	<table  class="inputTable tabContent">
		<tr>
			<@sjg.grid
		    	id="gridTable"
		    	caption="订单项目列表"
		    	dataType="json"
		    	href="order_item_list.action?orderId=${(id)!}"
		    	pager="false"
		    	navigator="false"
		    	gridModel="gridModel"
		    	onGridCompleteTopics="gridInit"
		    >
		    	<@sjg.gridColumn id="id" name="id" index="id" hidden="true" title="ID"  formatter="integer" sortable="false"/>
		    	<@sjg.gridColumn id="partNo" name="partNo" index="partNo" width="120" title="款号" sortable="true"/>
		    	<@sjg.gridColumn id="sku.skuCode" name="sku.skuCode" index="sku.skuCode" width="120" title="sku编号" sortable="false"/>
		    	<@sjg.gridColumn id="productName" name="productName"  width="80"  index="productName" title="品名" sortable="false"/>
		    	<@sjg.gridColumn id="sku.color" name="sku.color" index="sku.color" width="80" title="颜色" sortable="false"/>
		    	<@sjg.gridColumn id="sku.size" name="sku.size" index="sku.size" width="80" title="尺寸" sortable="false"/>
		    	<@sjg.gridColumn id="type" name="type" index="type" width="80" title="类型" sortable="false"/>
		    	<@sjg.gridColumn id="quantity" name="quantity" index="quantity" width="80" title="数量" sortable="false"/>
		    	<@sjg.gridColumn id="price" name="price" index="price" width="80" title="销售价" sortable="false"/>
		    	<@sjg.gridColumn id="finalPrice" name="finalPrice" index="finalPrice" width="80" title="成交价" sortable="false"/>
		    	<@sjg.gridColumn id="amountProduct" name="amountProduct" index="amountProduct" width="80" title="小计" sortable="false"/>
		    	<@sjg.gridColumn align="left" name="delete" index="delete" width="80" editable="false"  title="操作" sortable="false"/>
		    </@sjg.grid>
		</tr>	
	</table>
	<div class="buttonArea">
		<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
	</div>
</body>