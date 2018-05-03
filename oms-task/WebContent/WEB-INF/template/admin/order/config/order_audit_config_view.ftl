<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>自动审核配置 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/resources/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${base}/resources/admin/css/jquery.ui.all.css">
<script src="${base}/resources/admin/js/jquery-1.10.2.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.core.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.widget.js"></script>
<script src="${base}/resources/admin/js/ui/jquery.ui.accordion.js"></script>
<script src="${base}/resources/datepicker/ui/jquery-ui.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script src="${base}/resources/common/js/common.js"></script>
<@sj.head compressed="false" scriptPath="${webuiPath}/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<style type="text/css">
	a:link{
	color: #336699;
	}
	a:hover {
	color: #AB0000;
	}
	
</style>
<script type="text/javascript">
$().ready( function() {
	$( "#tabs" ).tabs();	
        $( "#accordion" ).accordion({
			collapsible: true,
		    autoHeight: true ,
		      clearStyle :true,
		    fillSpace:true
		});
		
		$( "#accordion01" ).accordion({
			collapsible: true,
		    autoHeight: true ,
		      clearStyle :true,
		    fillSpace:true
		});
		
	    $( "#accordion11" ).accordion({
			collapsible: true,
		    autoHeight: true ,
		     clearStyle :true,
		    fillSpace:true
		});
		
		//  $('#accordion').accordion('option', 'autoHeight', false);
		//  $('#accordion').accordion({ header: 'h2' });

		
		$( "#accordion1" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		
	    $( "#accordion2" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		
		$( "#accordion21" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		
	    $( "#accordion22" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
		$( "#accordion12" ).accordion({
			collapsible: true,
			autoHeight: true ,
			fillSpace:true
		});
});

	function updateAuditConfig(){
		if(this.confirm("确定保存吗?")){
			$("#orderAuditConfig").submit();
		}
	}
	function batchUpdateMerchantConfig(){
		if(this.confirm("确定保存吗?")){
			$("#merchantConfigs").submit();
		}
	}
	
	function checkedAllMerchants(box){
		if(box.checked){
			$("[name='merchantIds']").prop("checked",true);
		} else {
			$("[name='merchantIds']").prop("checked",false);
		}
	}
	function checkedAllRegions(box){
		if(box.checked){
			$("[name='regionIds']").prop("checked",true);
		} else {
			$("[name='regionIds']").prop("checked",false);
		}
	}
	
	function deleteRegionConfig() {
		var count = 0;
	    $("[name='regionIds']").each(function(){
	      if(this.checked)     
		  {     
		   	count=count+1;
		  }     
		});
		if(count<=0)
		{
			alert("请勾选数据");
			return;
		}
		
		var as = []; 
		var index = 0;
		var conOrder = "";
		$("[name='regionIds']").each(function(){
	    	if(this.checked) {  
				as.push(this.value); 
			}  
			index++;   
		});
		
		var params = {
				regionIds:as
		};
		if(confirm('确定删除配置吗?'))
		{
			$.ajax({
				type:"POST",
				url:"order_audit_region_config!delete.action",
				data:params,
				dataType : "json",
				success:function(data){
					if(data.status == "success"){
					    alert(data.message); 
					    window.location.href="order_audit_config!view.action#orderConfigDiv1";
					}else{
					   alert(data.message);
					   window.location.href="order_audit_config!view.action#orderConfigDiv1";
					}
				},
				error : function(data){
					alert("error!");
					window.location.href="order_audit_config!view.action#orderConfigDiv1"; 
				}
			});	
		}
	}
	
	function deleteConfig() {
		var count = 0;
	    $("[name='merchantIds']").each(function(){
	      if(this.checked)     
		  {     
		   	count=count+1;
		  }     
		});
		if(count<=0)
		{
			alert("请勾选数据");
			return;
		}
		
		var as = []; 
		var index = 0;
		var conOrder = "";
		$("[name='merchantIds']").each(function(){
	    	if(this.checked) {  
				as.push(this.value); 
			}  
			index++;   
		});
		
		var params = {
				merchantIds:as
		};
		if(confirm('确定删除配置吗?'))
		{
			$.ajax({
				type:"POST",
				url:"order_audit_merchant_config!delete.action",
				data:params,
				dataType : "json",
				success:function(data){
					if(data.status == "success"){
					    alert(data.message); 
					    window.location.href="order_audit_config!view.action#merchantConfigDiv";
					}else{
					   alert(data.message);
					   window.location.href="order_audit_config!view.action#merchantConfigDiv";
					}
				},
				error : function(data){
					alert("error!");
					window.location.href="order_audit_config!view.action#merchantConfigDiv"; 
				}
			});	
		}
	}
</script>
<div id="dialog-confirm" title="">
</div>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>自动审核配置</h1>
		</div>
		
			<div class="blank"></div>
			<div id="tabs">
			<ul>
				<li><a href="#orderConfigDiv">系统设置</a></li>
				<li><a href="#merchantConfigDiv">店铺配置</a></li>
			</ul>
			<div style="clear:both;"/>	
			<#assign width=-50 />		
			<div id="orderConfigDiv" style="display:block;height:100%;">
				<div id="orderConfigDiv1">
					<form id = "orderAuditConfig" action="order_audit_config!update.action" method="post">
					<input type="button" class="formButton" onclick="updateAuditConfig();" value="保存" />
					<input name = "orderAuditConfig.id" type="hidden" value=${orderAuditConfig.id}>
						<table class="inputTable">
							<tr>
								<th>是否启用</th>
								<td><input name="orderAuditConfig.enabled" type="checkbox" value="true" <#if orderAuditConfig.enabled>checked</#if>></td>
							</tr>
							<tr>
								<th>审核数量</th>
								<td><input name="orderAuditConfig.auditCount" value=${orderAuditConfig.auditCount}></td>
							</tr>
							<tr>
								<th>延时时间</th>
								<td><input name="orderAuditConfig.minutesDelay" value=${orderAuditConfig.minutesDelay}>分钟</td>
							</tr>
							<tr>
								<th>金额下限</th>
								<td><input name="orderAuditConfig.minAmount" value=${orderAuditConfig.minAmount}></td>
							</tr>
							<tr>
								<th>金额上限</th>
								<td><input name="orderAuditConfig.maxAmount" value=${orderAuditConfig.maxAmount}></td>
							</tr>
							<tr>
								<th>不免审sku</th>
								<td><input name="orderAuditConfig.interceptSkus" value=${orderAuditConfig.interceptSkus}>(多个用,号隔开)</td>
							</tr>
							<tr>
								<th>合并单免审</th>
								<td><input name="orderAuditConfig.isApprovedOrderMerge" type="checkbox" value="true" <#if orderAuditConfig.isApprovedOrderMerge>checked</#if>/></td>
							</tr>
							<tr>
								<th>促销单免审</th>
								<td><input name="orderAuditConfig.isApprovedOrderPromotion" type="checkbox" value="true" <#if orderAuditConfig.isApprovedOrderPromotion>checked</#if>/></td>
							</tr>
							<tr>
								<th>拆分单免审</th>
								<td><input name="orderAuditConfig.isApprovedOrderSplit" type="checkbox" value="true" <#if orderAuditConfig.isApprovedOrderSplit>checked</#if>/></td>
							</tr>
							<tr>
								<th>换货单免审</th>
								<td><input name="orderAuditConfig.isApprovedOrderBarter" type="checkbox" value="true" <#if orderAuditConfig.isApprovedOrderBarter>checked</#if>/></td>
							</tr>
							<tr>
								<th>货到付款免审</th>
								<td><input name="orderAuditConfig.isApprovedPayOnArrival" type="checkbox" value="true" <#if orderAuditConfig.isApprovedPayOnArrival>checked</#if>/></td>
							</tr>
							<tr>
								<th>单品免审</th>
								<td><input name="orderAuditConfig.isApprovedSingleProduct" type="checkbox" value="true" <#if orderAuditConfig.isApprovedSingleProduct>checked</#if>/></td>
							</tr>
							<tr>
								<th>忽略顾客留言</th>
								<td><input name="orderAuditConfig.isIgnoredClientRemark" type="checkbox" value="true" <#if orderAuditConfig.isIgnoredClientRemark>checked</#if>/></td>
							</tr>
							<tr>
								<th>忽略客服备注</th>
								<td><input name="orderAuditConfig.isIgnoredClientServiceRemark" type="checkbox" value="true" <#if orderAuditConfig.isIgnoredClientServiceRemark>checked</#if>/></td>
							</tr>
						</table>
					</form>
				</div>
				
				<div id="regionConfigsDiv">
					<h3 style="margin-top: 20px;">不免审地区配置</h3>
	            	<input type="button" class="formButton" onclick="location.href='order_audit_region_config!edit.action'" value="新增" />
	            	<input type="button" class="formButton" onclick="deleteRegionConfig();" value="删除" />
            		<table class="inputTable" border="1">
            			<tr>
	            			<td class="check">
								<input type="checkbox" class="allCheck" onclick="checkedAllRegions(this);" />
							</td>
            				<td>地区编码</td>
            				<td>地区名称</td>
            			</tr>
            			
            			<#list 0..(auditRegionConfigs!?size-1) as i>
						    <tr>
						    	<td><input type=checkbox name="regionIds" value="${auditRegionConfigs[i].id!}"></td>
            					<td><input name="auditRegionConfigs[${i}].code" value="${auditRegionConfigs[i].code!}" readonly="true"></td>
            					<td><input name="auditRegionConfigs[${i}].name" value="${auditRegionConfigs[i].name!}"  readonly="true"></td>
            				</tr>
						</#list>
            		</table>
           		</div>
            </div>
            <div id="merchantConfigDiv" style="display:none">
            	<input type="button" class="formButton" onclick="location.href='order_audit_merchant_config!edit.action'" value="新增" />
            	<input type="button" class="formButton" onclick="deleteConfig();" value="删除" />
            	<input type="button" class="formButton" onclick="batchUpdateMerchantConfig();" value="保存" />
            	<form id="merchantConfigs" action="order_audit_merchant_config!batchUpdateMerchantConfig.action" method="post">
            		<table class="inputTable">
            			<tr>
	            			<td class="check">
								<input type="checkbox" class="allCheck" onclick="checkedAllMerchants(this);" />
							</td>
            				<td>店铺名称</td>
            				<td>店铺编码</td>
            				<td>是否启用</td>
            				<td>最小金额</td>
            				<td>最大金额</td>
            				<td>是否启用延时审单</td>
            				<td>拆分单免审</td>
            				<td>忽略顾客留言</td>
            				<td>忽略客服备注</td>
            			</tr>
            			
            			<#list 0..(merchantConfigs!?size-1) as i>
						    <tr>
						    	<input type="hidden" name="merchantConfigs[${i}].id" value="${merchantConfigs[i].id!}">
						    	<td><input type=checkbox name="merchantIds" value="${merchantConfigs[i].id!}"></td>
            					<td><input name="merchantConfigs[${i}].merchantName" value="${merchantConfigs[i].merchantName!}" readonly="true"></td>
            					<td><input name="merchantConfigs[${i}].merchantCode" value="${merchantConfigs[i].merchantCode!}"  readonly="true"></td>
            					<td><input type="checkbox" value="true" name="merchantConfigs[${i}].enabled" <#if merchantConfigs[i].enabled>checked</#if> ></td>
            					<td><input name="merchantConfigs[${i}].minAmount" value="${merchantConfigs[i].minAmount!}"></td>
            					<td><input name="merchantConfigs[${i}].maxAmount" value="${merchantConfigs[i].maxAmount!}"></td>
            					<td><input type="checkbox" value="true" name="merchantConfigs[${i}].isDelay" <#if merchantConfigs[i].isDelay>checked</#if> ></td>
            					<td><input type="checkbox" value="true" name="merchantConfigs[${i}].isApprovedOrderSplit" <#if merchantConfigs[i].isApprovedOrderSplit>checked</#if> ></td>
            					<td><input type="checkbox" value="true" name="merchantConfigs[${i}].isIgnoredClientRemark" <#if merchantConfigs[i].isIgnoredClientRemark>checked</#if> ></td>
            					<td><input type="checkbox" value="true" name="merchantConfigs[${i}].isIgnoredClientServiceRemark" <#if merchantConfigs[i].isIgnoredClientServiceRemark>checked</#if> ></td>
            				</tr>
						</#list>
            		</table>
            	</form>
            </div>
		</div>
		
</body>

</html>