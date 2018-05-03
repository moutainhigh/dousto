
<div>

<script type="text/javascript">
$().ready(function(){
	isDisplaySelfTakePoint();
	isDisplayCreditNO();
});


function isDisplaySelfTakePoint(){
	// 审核状态
	var statusConfirm = $("#statusConfirm").val();

	// 详情页标识
	var isDetailPageFlag = $("#isDetailPageFlag").val();
    if($("#distributeType")[0].value == "3")
    {
    	//document.getElementById("selfTakePoint").disabled = false;
    	//$("#selfTakePoint").attr("disabled",false);
    	$("#rowAddress").removeAttr("style");
    	$("#deliveryPriority").removeAttr("style");
    	$("#ifNeedRefund").attr({style:"display:inline-block"});
    	$("#ifNeedRefund2").attr({style:"display:none"});
    	// 如果不是详情页且不是已审核，则使生效
    	if(!isDetailPageFlag && (statusConfirm != '0807'))
		{
	    	$("#merchantSelect").attr("disabled",false);
	    	$("#takePointSelect").attr("disabled",false);
	    	$("#address").attr("disabled",false);
	    	$("#deliveryPriority").attr("disabled",false);
    	}
    }
    else if($("#distributeType")[0].value == "2" && $("#orderCategoryFlag").val() == "chg"){
        $("#deliveryPriority").removeAttr("style");
        // 如果不是详情页且不是已审核，则使生效
    	if(!isDetailPageFlag && (statusConfirm != '0807'))
		{
        	$("#deliveryPriority").attr("disabled",false);
        }
        
        // 置失效
        $("#merchantSelect").attr("disabled",true);
    	$("#takePointSelect").attr("disabled",true);
    	$("#address").attr("disabled",true);
    }
    else
    {
    	//document.getElementById("selfTakePoint").disabled = true;
    	//document.getElementById("selfTakePoint").value = "";
    	 $("#rowAddress").attr({style:"display:none"});
    	 $("#deliveryPriority").attr({style:"display:none"});
    	 if(!isDetailPageFlag)
	    {
	    	$("#ifNeedRefund").attr({style:"display:none"});
	    	$("#ifNeedRefund2").attr({style:"display:inline-block"});
	    }
    	// 置失效
    	$("#merchantSelect").attr("disabled",true);
    	$("#takePointSelect").attr("disabled",true);
    	$("#address").attr("disabled",true);
    	$("#deliveryPriority").attr("disabled",true);
    }
    
    
    if($("#distributeType")[0].value == "3")
    {
    	if($("#orderCategoryFlag").val() == "ret")
 		{
 			if($("#ifNeedRefund").val()=='2')
		 	{
		 		$("#creditNO").removeAttr("style");
		 		// 如果不是详情页，则使生效
		    	if(!isDetailPageFlag)
				{
		 			$("#creditNOval").attr("disabled",false);
		 		}
		 	}
 		}
    }
    else 
 	{
 		$("#creditNO").attr({style:"display:none"});
 		// 置失效
 		$("#creditNOval").attr("disabled",true);
 	}
}

 function isDisplayCreditNO(){
 	// 详情页标识
	var isDetailPageFlag = $("#isDetailPageFlag").val();
	
 	if($("#orderCategoryFlag").val() == "ret")
 	{
	 	if($("#ifNeedRefund").val()=='2')
	 	{
	 		$("#creditNO").removeAttr("style");
	 		// 如果不是详情页且不是已审核，则使生效
	    	if(!isDetailPageFlag)
			{
	 			$("#creditNOval").attr("disabled",false);
	 		}
	 	}
	 	else
	 	{
	 		$("#creditNO").attr({style:"display:none"});
	 		// 置失效
	 		$("#creditNOval").attr("disabled",true);
	 	}
 	}
 };
</script>
				<table class="inputTable tabContent">
					<tr>
						<th>
							订单号:
						</th>
						<td>
							${orderSub.orderSubNo}
							<!-- 经手人 -->
							<input type="hidden" name="order.createdBy" value="${loginUser.id}"/>
						</td>
						<th>
							申请类型:
						</th>
						<td>
							<b>${orderCategoryNameMap.get(orderCategory)}</b><#if order.statusConfirm = '0807'>(<font color="red">已审核</font>)</#if>
							<input type="hidden" id="orderCategoryFlag" value="${orderCategory}"/>
							<input type="hidden" id="isDetailPageFlag" value="${isDetail}"/>
							<input type="hidden" id="statusConfirm" value="${(order.statusConfirm)!}"/>
							<input type="hidden" id="statusStatusTotal" value="${(order.statusTotal)!}"/>
						</td>
					</tr>
					<tr>
						<th>
						              申请人:
						</th>
						<td>
							<input type="hidden" name="order.memberNo" value="${order.memberNo}"/>
							${order.customerName!}
						</td>
						<th>
							申请日期:
						</th>
						<td>
							<!-- 如果是退款单(取消订单)或运费补款单，则设为只读 -->
							<#if (orderCategory == 'ref') || (orderCategory == 'tsf')>
								${order.dateCreated}
							<#else>
								<#if !isCreated>
								${order.dateCreated}
								<#else>
								<input type="text" readonly="true"  onclick="WdatePicker()" name="order.dateCreated" value="${.now}">
								</#if>
							</#if>	
						</td>
					</tr>
					<tr>
						<th>
							物流费用:
						</th>
						<td>
							<#if isDetail || (order.statusConfirm = '0807') || (orderCategory == 'ref') || (orderCategory == 'tsf')>
								${order.transportFee}
								<input type="hidden" id="transportFee" name="order.transportFee" class="formText {required: true, min: 0}" value="<#if !isCreated>${order.transportFee}<#else>0</#if>" onblur="changeTransportFee()"/>
							<#else>
								<input type="text" id="transportFee" name="order.transportFee" class="formText {required: true, min: 0}" value="<#if !isCreated>${order.transportFee}<#else>0</#if>" onblur="changeTransportFee()" <#if (orderCategory=='chg' || (orderCategory == 'ret'))>readonly</#if>/>
							<label class="requireField">*</label>
							</#if>
						</td>
						
						<th>
						              是否已开发票:
						</th>
						<td>	
							<select id="invoicePrinted" name="orderSub.invoicePrinted" <#if isDetail || (order.statusConfirm = '0807') || (orderCategory == 'ref') || (orderCategory == 'tsf')> disabled </#if>>
								<#list allInvoicePrintedList as invoice>
									<#assign codeLong = invoice.codeLong>
									<#if codeLong=="">
										<#assign codeLong = "0">
									</#if>
									<option value="${codeLong}"<#if (codeLong == orderSub.invoicePrinted)!> selected</#if>>
										${invoice.desc}
									</option>
								</#list>
							</select>
							<#if !isDetail><label class="requireField">*</label></#if>
						</td>
					</tr>					
					<tr>
						<th>
							退款总金额:
						</th>
						<#if isDetail>
							<td><#if orderCategory == 'chg'>0<#else>${order.totalPayAmount}</#if></td>
						<#else>
							<#if (order.statusConfirm = '0807') && !((orderCategory == 'ref') || (orderCategory == 'tsf'))>
								<td><#if orderCategory == 'chg'>0<#else><span id="totalPayAmount">${order.totalPayAmount}</span><input type="hidden" id="totalPayAmountTmp" name="order.totalPayAmount" value="${order.totalPayAmount}"/></#if></td>
								<input type="hidden" id="totalPayAmountRefTsf" value="${order.totalPayAmount}"/>
							<#elseif (orderCategory == 'ref') || (orderCategory == 'tsf')>
								<input type="hidden" name="order.totalPayAmount" id="totalPayAmountRefTsf" value="${order.totalPayAmount}"/>
								<td id="totalPayAmount"></td>
							<#else>
								<td id="totalPayAmount"></td>
								<input type="hidden" name="order.totalPayAmount" id="totalPayAmountTmp" value=""/>
								<input type="hidden" id="totalPayAmountRefTsf" value="${order.totalPayAmount}"/>
							</#if>
						</#if>
						<th>
								回扣积分总额:
						</th>
						<td>
							<#if isDetail>
								${order.totalGivePoints}
							<#else>
								<input type="hidden" id="totalGivePointsTmp" name="order.totalGivePoints" value=""/>
								<span id="totalGivePoints"></span>
							</#if>							
						</td> 
						</tr>									
						<tr>
							<th>
							入库物流方式:
						</th>
						<td>
							<#if (order.statusConfirm = '0807') || (orderCategory == 'ref') || (orderCategory == 'tsf')>
								<input type="hidden" name="orderSub.distributeType" value="${orderSub.distributeType}"/>
							</#if>
							<select id="distributeType" name="orderSub.distributeType" <#if isDetail || (order.statusConfirm = '0807') || (orderCategory == 'ref') || (orderCategory == 'tsf')> disabled </#if> onchange="isDisplaySelfTakePoint()">
							    <#if 'rej'==orderCategory>
							        <option value="4" <#if ('4' == orderSub.distributeType)!> selected</#if>>物流返回</option>
							        <option value="3"<#if ('3'= orderSub.distributeType)!> selected</#if>>门店代退</option>
							    <#elseif 'chg'==orderCategory>
								    <#list allDistributeType as list>
								    	<#if list.code != '3'>
										<option value="${list.code}"<#if (list.code == orderSub.distributeType)!> selected</#if>>
											<#if list.code=="">
												请选择
											<#else>
												${list.desc}
											</#if>
										</option>
										</#if>
									</#list>
							    <#else>
								    <#list allDistributeType as list>
										<option value="${list.code}"<#if (list.code == orderSub.distributeType)!> selected</#if>>
											<#if list.code=="">
												请选择											
											<#else>
												${list.desc}
											</#if>
										</option>
									</#list>
							    </#if>								
							</select>							
							<#if !isDetail><label class="requireField">*</label></#if>
						</td>
						<th>
							当前账户积分：
						</th>
						<td>
						<span id="integral" style="color:red;">${(order.integral)!0}</span>
						</td>
						</tr>
					
					<tr>
						<th>
						              问题描述:
						</th>
						<td style="width:37%;">
							<#if isDetail || (orderCategory == 'ref') || (orderCategory == 'tsf')>  
							<textarea rows="6" cols="40" id="remark"  disabled>${order.remark}</textarea>
							<#else>
							<textarea rows="6" cols="40" id="remark" name="order.remark" onblur="checkRemark()"><#if !isCreated>${order.remark}</#if></textarea>
							</#if>
						</td>
						<th>
						              备注:
						</th>
						<td>
							<#if isDetail || (orderCategory == 'ref') || (orderCategory == 'tsf')>  
							<textarea rows="6" cols="40" name="order.clientServiceRemark" disabled>${order.clientServiceRemark}</textarea>
							<#else>
							<textarea rows="6" cols="40" id="clientServiceRemark" name="order.clientServiceRemark" onblur="checkClientServiceRemark()"><#if !isCreated>${order.clientServiceRemark}</#if></textarea>
							</#if>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
				</table>
</div>