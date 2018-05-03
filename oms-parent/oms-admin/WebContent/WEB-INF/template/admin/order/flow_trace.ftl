
<div id="flowTraceListDiv" width="450px" align="center">
<font size="3">流程跟踪</font><br/><br/>

<#if flowTraceList?size==0>
	<table align="center" style="border-top:solid 1px #000000;border-left:solid 1px #000000;">
			<tr>
				<td colspan="4" align="center" style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">
					<#if rmaType=="Return">退货单号:${rmaSN}<#else>换货单号:${rmaSN}</#if>
					${rmaOrderStatus}
		</td>
	</tr>
<#else>
<table width="445px" border="0" align="center" style="border-top:solid 1px #000000;border-left:solid 1px #000000;">
	<tr>
		<td  style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">
		<#if rmaType=="Return">
			退货单号
			<#else>
			换货单号
		</#if>
		</td >
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">执行的操作</td>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">操作人</td>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">操作时间</td>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">备注</td>
	</tr>
	<#list flowTraceList as  flowTrace>
		<tr>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">${flowTrace.flowNo}</td>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">${flowTrace.operateName}</td>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">${flowTrace.operater}</td>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">${flowTrace.dateCreated!''}</td>
		<td style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;">${flowTrace.memo}</td>
	
		</tr>
	</#list>
</#if>
<tr>
		<td colspan="5" align="center" style="border-right:solid 1px #000000;border-bottom:solid 1px #000000;"><input type="button" value="关闭" id="closeButton" ></td>
</tr>
</table>
</div>