<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>编辑订单 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">

    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="${base}/resources/datepicker/themes/base/jquery.ui.base.css">   
    <link rel="stylesheet" href="${base}/resources/datepicker/themes/sunny/jquery.ui.theme.css">   
       
    <link rel="stylesheet" href="${base}/resources/datepicker/demos.css">
    <link rel="stylesheet" href="${base}/resources/datepicker/main.css">   
    <link rel="stylesheet" href="${base}/resources/datepicker/datepicker/timepicker-Addon/jquery-ui-timepicker-addon.css">   
    <script src="${base}/resources/datepicker/jquery-1.9.1.js"></script>   
       
    <script src="${base}/resources/datepicker/ui/jquery-ui.js"></script> 
    <script src="${base}/resources/datepicker/ui/jquery.ui.core.js"></script>   
    <script src="${base}/resources/datepicker/ui/jquery.ui.widget.js"></script>   
    <script src="${base}/resources/datepicker/ui/jquery.ui.datepicker.js"></script> 
   
       
    <script src="${base}/resources/datepicker/datepicker/timepicker-Addon/jquery-ui-timepicker-addon.js"></script>   
    <script src="${base}/resources/datepicker/datepicker/timepicker-Addon/jquery-ui-sliderAccess.js"></script>   
    <script src="${base}/resources/datepicker/ui/i18n/jquery.ui.datepicker-zh-CN.js"></script>   
    <script src="${base}/resources/datepicker/datepicker/timepicker-Addon/i18n/jquery-ui-timepicker-zh-CN.js"></script> 
    
    <script src="${base}/resources/datepicker/jquery.cTabs.js"></script>

<script type="text/javascript">

$().ready( function() {
	
	$( "#confirmDateFrom, #confirmDateTo" ).datepicker({   
            changeMonth: true,   
            changeYear: true  
    });   
    $( "#confirmDateFrom, #confirmDateTo" ).datepicker( "option",$.datepicker.regional[ 'zh-CN' ] ); 
    
    
    
   $("#tab1").click( function() {
     $("a[href*='#']","#ctabs").removeClass("selected").each(function(){
				$($(this).attr("href")).hide();
	});
     $("a[href*='#']","#tab1").addClass("selected");
     $("#ctabs1").show();
   });
   
   $("#tab2").click( function() {
          $("a[href*='#']","#ctabs").removeClass("selected").each(function(){
				$($(this).attr("href")).hide();
			});
       $("a[href*='#']","#tab2").addClass("selected");
        $("#ctabs2").show();
   });
   
    
    
    $( "#infoo" ).click( function() {
        var message="请输入缺货原因<input id='reasonInput' type=text ></input>";
        showDialog(
			message,
			{ "取消": function(){
					$( this ).dialog( "close" );
				}, 
				"确定": function() {
					$( this ).dialog( "close" );
				}
			}
		)
            
    });  
    
    
     function showDialog(message,_buttons){
		$("#dialog-confirm" ).html(message);
		$( "#dialog-confirm" ).dialog({
			resizable: false,
			height:140,
			modal: true,
			buttons:_buttons
		});
	}
     

});


  
</script>

</head>

<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>查看/修改客户售后意向单</h1>
		</div>
		<form id="inputForm" class="validate" action="order!update.action" method="post">
			<input type="hidden" name="order.id" value="${order.id}" />
			<div class="blank"></div>
			<ul class="tab">
				<li>
					<input type="button" id="infoo" value="订单信息" hidefocus="true" />
				</li>
				<li>
					<input type="button" value="商品信息" hidefocus="true" />
				</li>
			</ul>
			<table class="inputTable tabContent">
				<tr>
					<th>
						订购人信息:
				    </th>
					<td>
						<input type="text" name="order.people" class="formText {required: true}" value="${order.people}" />
					</td>
					<th>
						下单时间从:
					</th>
					<td>
					  <input type="text" readonly="true" id="confirmDateFrom" name="order.confirmDateFrom">
				    </td>	
					<th>
						订单来源:
					</th>
					<td>
						<select name="order.orderResource">
						       <option value="">请选择
								</option>
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.orderResource> selected </#if>>
									${list}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
				</tr>
				
				
				
				<tr>
					<th>
						订单编号:
					</th>
					<td>
						<input type="text" name="order.shipName" class="formText {required: true}" value="${order.shipName}" />
					</td>
					<th>
						下单时间到:
					</th>
					<td>
						 <input type="text" readonly="true" id="confirmDateTo" name="order.confirmDateTo">
					</td>
					<th>
						订单类型:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						订单外部号:
					</th>
					<td>
						<input type="text" name="order.shipName" class="formText {required: true}" value="${order.shipName}" />
					</td>
					<th>
						订单金额下限:
					</th>
					<td>
						<input type="text" name="order.shipName" class="formText {required: true}" value="${order.shipName}" />
					</td>
					<th>
						配送方式:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						商品关键字:
					</th>
					<td>
						<input type="text" name="order.deliveryFee" class="formText {required: true, min: 0}" value="${order.deliveryFee}" />
						<label class="requireField">*</label>
					</td>
					<th>
						订单金额上限:
					</th>
					<td>
						<input type="text" name="order.shipName" class="formText {required: true}" value="${order.shipName}" />
					</td>
					<th>
						支付方式:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
				</tr>
				<tr>
					<th>
						下单工人号:
					</th>
					<td>
						<input type="text" name="order.deliveryFee" class="formText {required: true, min: 0}" value="${order.deliveryFee}" />
						<label class="requireField">*</label>
					</td>
					<th>
						配送地区:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
					<th>
						商业伙伴:
					</th>
					<td>
						<input type="text" name="order.deliveryFee" class="formText {required: true, min: 0}" value="${order.deliveryFee}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				
				<tr>
					<th>
						选择商家:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
					<th>
						包括子商家:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
					<th>
						显示数量:
					</th>
					<td>
						<input type="text" name="order.deliveryFee" class="formText {required: true, min: 0}" value="${order.deliveryFee}" />
						<label class="requireField">*</label>
					</td>
				</tr>
				
				<tr>
					<th>
						品类:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
					<th>
						订单验证码:
					</th>
					<td>
						<input type="text" name="order.deliveryFee" class="formText {required: true, min: 0}" value="${order.deliveryFee}" />
						<label class="requireField">*</label>
					</td>
					<th>
						购买区域:
					</th>
					<td>
						<select name="order.productWeightUnit">
							<#list orderResourceList as list>
								<option value="${list}"<#if list == order.productWeightUnit> selected </#if>>
									${action.getText("WeightUnit." + list)}
								</option>
							</#list>
						</select>
						<span id="productWeightMessagePosition"></span>
						<label class="requireField">*</label>
					</td>
				</tr>
			</table>
			
			
			<table>
			<tr>
			<div id="ctabs"  class="indextab">
			    <ul class="tab">
			      <li id="tab1"><a href="#ctabs1" class="selected">ctabs0</a></li>
			      <li id="tab2"><a href="#ctabs2" >ctabs1</a></li>
			    </ul>
			    <div id="ctabs1">ctabs000</div>
			    
			  
			    <div>
			    	<table class="listTable"  id="ctabs2" hidden="hidden">
				     <tr>
					<th >
						<input type="checkbox" class="allCheck"/>
					</th>
					<th>
						<span class="sort" name="paymentSn">收款单号</span>
					</th>
					<th>
						<span>订单号</span>
					</th>
					<th>
						<span>会员</span>
					</th>
				</tr>
				</table>
				</div>	
			    
			</div>
			</tr>
			</table>
			
		
			
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
			</div>
		</form>
	</div>
</body>
</html>