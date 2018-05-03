<style type="text/css">
	a:link{
	color: blue;
	}
	a:visited {
	color: blue;
	}
	a:hover {
	color: #ff6600;
	}
	
</style>

<div id="deliverInfo" style="display:none">
 <table class="inputTable tabContent">
    <tr>
    <th>
			物流公司:
		</th>
		<td>
			<#if isDetail || (order.statusConfirm == '0807')>
			<select id="expressType" name="orderSub.expressType" onChange="logi(this);" disabled>
    			<option value="">请选择物流公司</option>
    			<#if companyList!=null&&(companyList?size>0)>
        			<#list companyList as company>
        				<option value="${company.name}" link="${company.description}">${company.name}</option>
        			</#list>
        		</#if>
    			<option value="其它">其它...</option>
    		</select>
    		&nbsp;&nbsp;<span id="searchSpan" style="display:none;"><a id="searchA"  href="" target="_blank">查查进度</a></span>
			<input type="hidden" name="orderSub.expressType" value="${orderSub.expressType}" />
			<#else>
			<select id="expressType" name="orderSub.expressType" onChange="logi(this);">
    			<option value="">请选择物流公司</option>
    			<#if companyList!=null&&(companyList?size>0)>
        			<#list companyList as company>
        				<option value="${company.code}" link="${company.description}">${company.name}</option>
        			</#list>
        		</#if>
    			<option value="其它">其它...</option>
    		</select>
    		&nbsp;&nbsp;<span id="searchSpan" style="display:none;"><a id="searchA" href="" target="_blank">查查进度</a></span>
		 	</#if>
		</td>
		<th>
			快递单号:
		</th>
		<td>
			<#if isDetail || (order.statusConfirm == '0807')>
			${orderSub.logisticsOutNo}
			<input type="hidden" name="orderSub.logisticsOutNo" value="${orderSub.logisticsOutNo}" />
			<#else>
			<input type="text" name="orderSub.logisticsOutNo" class="formText {required: true, min: 0}"  value="${orderSub.logisticsOutNo}" />
		 	</#if>
		</td>
		
	</tr>
	</table>
</div>