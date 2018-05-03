<div id="advancedSearchFrame" class="ui-dialog hidden">
<table class="inputTable tabContent tableSearch" width="100%">
		<tbody>
		<tr>
		<th>订单编号：</th>
		<td><input type="text" id="orderNo" ></td>
		</tr>
		<tr>
		<th>会员：</th>
		<td><input type="text" id="accountName" ></td>
		</tr>
		<tr>
		<th>收货人：</th>
		<td><input type="text" id="consignee" ></td>
		</tr>
		<tr>
		<th>收货地址：</th>
		<td><#include "/WEB-INF/template/admin/stateSelect.ftl" ></td>
		</tr>
		<tr>
		<th>收货人手机号：</th>
		<td><input type="text" id="consigneeMobile" ></td>
		</tr>
		<tr>
		<th>下单日期：</th>
		<td>
			<@sj.datepicker id="startDownOrderDate" buttonImage = "/sc-webui/scripts/jquery/js/calendar.gif" changeMonth="true" changeYear="true" value="${(productMarket.endDate)!\"${NewYearDate}\"}" displayFormat="yy-mm-dd" name="startDate" label="Change Month and Year" ></@sj.datepicker>
			-
			<@sj.datepicker id="endDownOrderDate" buttonImage = "/sc-webui/scripts/jquery/js/calendar.gif" changeMonth="true" changeYear="true" value="${(productMarket.endDate)!\"${NewYearDate}\"}" displayFormat="yy-mm-dd" name="endDate" label="Change Month and Year" ></@sj.datepicker>
			
		</td>
		</tr>
		<tr>
		<th>支付日期：</th>
		<td>
			<@sj.datepicker id="startOrderPaymentDate" buttonImage = "/sc-webui/scripts/jquery/js/calendar.gif" changeMonth="true" changeYear="true" value="${(productMarket.endDate)!\"${NewYearDate}\"}" displayFormat="yy-mm-dd" name="startOrderPaymentDate" label="Change Month and Year" ></@sj.datepicker>
			-
			<@sj.datepicker id="endOrderPaymentDate" buttonImage = "/sc-webui/scripts/jquery/js/calendar.gif" changeMonth="true" changeYear="true" value="${(productMarket.endDate)!\"${NewYearDate}\"}" displayFormat="yy-mm-dd" name="endOrderPaymentDate" label="Change Month and Year" ></@sj.datepicker>
			
		</td>
		</tr>
		<tr>
		<th>电子邮箱：</th>
		<td><input type="text" id="memberEmail" ></td>
		</tr>
		<tr>
		<th>订单状态：</th>
		<td>
		<select id="status" >
		<option value="">请选择---</option>	
		<option value="all">全部</option>	
		   <@security.authorize ifNotGranted="ROLE_Financial">
		<option value="submitted">已提交</option>			
		<option value="paid">付款完成</option>
		<option value="paiderror">付款失败</option>
		<option value="wms_received">接单处理中</option>
		<option value="wms_receivedfailed">接单失败</option>
		<option value="wms_packaged">已打包</option>
		<option value="shipped">已发货</option>
		<option value="shipederror">发货异常</option>
		<option value="completed">已完成</option>
		<option value="closed">已关闭</option>
		<option value="canceling">申请取消</option>
		 </@security.authorize>
		   <@security.authorize ifAnyGranted="ROLE_Financial,ROLE_ADMIN,ROLE_ROOT">
		<option value="canceled">已取消</option>
			 </@security.authorize>
			  <@security.authorize ifNotGranted="ROLE_Financial">
		<option value="canceledfail">取消失败</option>
		<option value="outOfStock">缺货等待</option>
		 </@security.authorize>
		  <@security.authorize ifAnyGranted="ROLE_Financial,ROLE_ADMIN,ROLE_ROOT">
		<option value="refund">已退款</option>
		            </@security.authorize>
		            <@security.authorize ifNotGranted="ROLE_Financial">
		<option value="invalid">已作废</option>
		 </@security.authorize>
		</select>
		</td>
		</tr>
		<tr>
		<th>支付方式：</th>
		<td>
		<select id="paymentMode" >
			<option value="">请选择---</option>
		<#list AllPaymentMethod as paymentMethod>
		<option value="${paymentMethod.id}">${paymentMethod.name}</option>
		</#list>
		</select>
		</td>
		</tr>
		<tr>
		<th>已付总金额：</th>
		<td><input type="text" id="startTotalPaid" maxlength="7" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="var code;if(event.charCode != undefined){code = event.charCode;}else{code = event.keyCode;}if(code==0){code=event.which;}if ((code>47 && code<58)||code==8||code==46){}else{return false;}" style="ime-mode:disabled;">-<input type="text" id="endTotalPaid" maxlength="7" onkeydown="if(event.keyCode==13)event.keyCode=9" onkeypress="var code;if(event.charCode != undefined){code = event.charCode;}else{code = event.keyCode;}if(code==0){code=event.which;}if ((code>47 && code<58)||code==8||code==46){}else{return false;}" style="ime-mode:disabled;"></td>
		</tr>
		</tbody>
</table>
</div>