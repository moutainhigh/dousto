		<div id="retInfo" style="display:none">
			   <table border="1px" class="inputTable tabContent">
			   	<tr class="title">
			   	  <th>原支付方式</th>
			   	  <td>
			   	  </td>
			   	</tr>
				   	<#if order.originalOrderPayList?exists>
					<#list order.originalOrderPayList as list>
						<tr class="title">
						<th>
					   <#list refundMethodList as modelist> 
							<#if modelist.id == list.payCode> 
								${modelist.name}
							</#if>	
						</#list>
						</th>
					    <td>￥${list.payAmount?default(0)}</td>					 
					  </tr>
				   	</#list>
				   	</#if> 
			   </table>			   
	</div>