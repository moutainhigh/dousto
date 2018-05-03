
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
							<!--
							<#if !isCreated>
								<#if (order.customerName)??>
									${order.customerName}
								<#else>
									${order.memberNo}
								</#if>
								${order.memberNo}
							<#else>
							<input type="text" id="customerName"  name="order.customerName" class="formText {required: true, min: 0}" value="<#if (order.customerName)??>${order.customerName}<#else>${order.memberNo}</#if>" />
							<label class="requireField">*</label>
						 	</#if>-->
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
								<input type="text" id="transportFee" name="order.transportFee" class="formText {required: true, min: 0}" value="<#if !isCreated>${order.transportFee}<#else>0</#if>" onblur="changeTransportFee()" <#if (orderCategory=='chg')>readonly</#if>/>
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
						<!--
						<th>
						              经手人:
						</th>
						<td>
						    <input type="hidden" name="order.createdBy" value="${loginUser.id}"/> 
						</td>
						-->
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
							&nbsp;&nbsp;&nbsp;&nbsp;(当前账户积分：<span id="integral" style="color:red;">${(order.integral)!0}</span>)
							<!-- 保留加载页面时的积分值 
							<input type="hidden" id="integralLoadInit" value="${(order.integral)!0}"/>
							-->
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
											<#elseif list.code=='2'>
												上门取货
											<#else>
												${list.desc}
											</#if>
										</option>
									</#list>
							    </#if>
								
							</select>
							
							<!-- 详情页 入库物流方式二级选项  begin -->
							<#if isDetail || (orderCategory == 'ref') || (orderCategory == 'tsf')>
								<select id="deliveryPriority" disabled  <#if ('3' != orderSub.distributeType)!>style="display:none"</#if>>
								<#if 'rej'==orderCategory>
									<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
							        <option value="1" <#if ('1' == orderSub.deliveryPriority)!> selected</#if>>门店寄回</option>
							        <option value="2"<#if ('2'= orderSub.deliveryPriority)!> selected</#if>>物流到店取回</option>
							    <#elseif 'chg'==orderCategory>
							    	<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
								    <option value="3" <#if ('3' == orderSub.deliveryPriority)!> selected</#if>>原商品需取回</option>
							        <option value="4"<#if ('4'= orderSub.deliveryPriority)!> selected</#if>>原商品无需取回</option>
							    <#else>
							    	<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
							    	<option value="1" <#if ('1' == orderSub.deliveryPriority)!> selected</#if>>门店寄回</option>
							        <option value="2"<#if ('2'= orderSub.deliveryPriority)!> selected</#if>>物流到店取回</option>
							        <option value="4"<#if ('4'= orderSub.deliveryPriority)!> selected</#if>>原商品无需取回</option>
							    </#if>
								</select>
							<!-- 详情页 入库物流方式二级选项  end -->	
							<!-- 修改且为已审核 入库物流方式二级选项  begin -->
							<#elseif isModify && (order.statusConfirm = '0807')>
								<input type="hidden" name="orderSub.deliveryPriority" value="${(orderSub.deliveryPriority)!}"/>
								<select id="deliveryPriority" disabled  <#if ('3' != orderSub.distributeType)!>style="display:none"</#if>>
								<#if 'rej'==orderCategory>
									<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
							        <option value="1" <#if ('1' == orderSub.deliveryPriority)!> selected</#if>>门店寄回</option>
							        <option value="2"<#if ('2'= orderSub.deliveryPriority)!> selected</#if>>物流到店取回</option>
							    <#elseif 'chg'==orderCategory>
							    	<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
								    <option value="3" <#if ('3' == orderSub.deliveryPriority)!> selected</#if>>原商品需取回</option>
							        <option value="4"<#if ('4'= orderSub.deliveryPriority)!> selected</#if>>原商品无需取回</option>
							    <#else>
							    	<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
							    	<option value="1" <#if ('1' == orderSub.deliveryPriority)!> selected</#if>>门店寄回</option>
							        <option value="2"<#if ('2'= orderSub.deliveryPriority)!> selected</#if>>物流到店取回</option>
							        <option value="4"<#if ('4'= orderSub.deliveryPriority)!> selected</#if>>原商品无需取回</option>
							    </#if>
								</select>
							<!-- 修改且为已审核 入库物流方式二级选项  end -->	
							<!-- 其它 入库物流方式二级选项  begin -->
							<#else>
								<select id="deliveryPriority"  name="orderSub.deliveryPriority"  <#if ('3' != orderSub.distributeType)!>style="display:none"</#if>>
								<#if 'rej'==orderCategory>
									<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
							        <option value="1" <#if ('1' == orderSub.deliveryPriority)!> selected</#if>>门店寄回</option>
							        <option value="2"<#if ('2'= orderSub.deliveryPriority)!> selected</#if>>物流到店取回</option>
							    <#elseif 'chg'==orderCategory>
							    	<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
								    <option value="3" <#if ('3' == orderSub.deliveryPriority)!> selected</#if>>原商品需取回</option>
							        <option value="4"<#if ('4'= orderSub.deliveryPriority)!> selected</#if>>原商品无需取回</option>
							    <#else>
							    	<option value="" <#if ('' == orderSub.deliveryPriority)!> selected</#if>>请选择</option>
							    	<option value="1" <#if ('1' == orderSub.deliveryPriority)!> selected</#if>>门店寄回</option>
							        <option value="2"<#if ('2'= orderSub.deliveryPriority)!> selected</#if>>物流到店取回</option>
							        <option value="4"<#if ('4'= orderSub.deliveryPriority)!> selected</#if>>原商品无需取回</option>
							    </#if>
								</select> 	
							</#if>
							<!-- 其它 入库物流方式二级选项  end -->
							<#if !isDetail><label class="requireField">*</label></#if>
						</td>
						<th>
						              是否需要退款:
						</th>
						<td>	
							
							<!--
							<select name="order.ifNeedRefund" disabled>
								<#list allIfNeedRefundList as list>
									<option value="${list.codeLong}"<#if (list.codeLong == 1)!> selected</#if>>
										${list.desc}
									</option>
								</#list>
							</select>
							<input type="hidden" name="order.ifNeedRefund" value="1" />
							-->
							<#assign isNeed=0>
							<#if (orderCategory == 'ref') || (orderCategory == 'tsf')>
								<select disabled>
									<#list allIfNeedRefundList as list>
										<option value="${list.codeLong}"<#if (list.codeLong == order.ifNeedRefund)!> selected</#if>>
											<#if (list.codeLong == '')!>
												请选择
											<#elseif (list.codeLong == '1')!>
												网天退款
											<#else>
												${list.desc}
											</#if>
										</option>
									</#list>
								</select>
							<#else>
							<!-- 详情 begin-->
							<#if isDetail>
								<select id="ifNeedRefund"  name="order.ifNeedRefund" disabled>
									<#list allIfNeedRefundList as list>
										<option value="${list.codeLong}"<#if (list.codeLong == order.ifNeedRefund)!> selected</#if>>
											<#if (list.codeLong == '')!>
												请选择
											<#elseif (list.codeLong == '1')!>
												网天退款
											<#else>
												${list.desc}
											</#if>
										</option>
									</#list>
								</select>
							<!-- 详情 end-->
							<!-- 创建逆向单 begin-->	
							<#elseif isCreated>
								<#if (orderCategoryNameMap.get(orderCategory) == '退货') || ((orderCategoryNameMap.get(orderCategory) == '拒收') && (order.statusPay =='0420'))>	
									<select id="ifNeedRefund" onchange="isDisplayCreditNO()">
									<#list allIfNeedRefundList as list>
									  <#if (list.codeLong != '0')>	
										<option value="${list.codeLong}">
											<#if (list.codeLong == '')!>
												请选择
											<#elseif (list.codeLong == '1')!>
												网天退款
											<#else>
												${list.desc}
											</#if>
										</option>
									  </#if>
									</#list>
									</select>
									<select id="ifNeedRefund2"  style="display:none;">
										<option value="1">网天退款</option>
									</select>
									<input id="hiddenIfNeedRefund" type="hidden" name="order.ifNeedRefund" value=""/>
									<#assign isNeed=1>
								<#else>
									<input id="ifNeedRefund" name="order.ifNeedRefund" type="hidden" value="0"/>
									<select>
										<option value="0">
											否
										</option>
									</select>
								</#if>
							<!-- 创建逆向单 end-->
							<!-- 修改 begin-->	
							<#elseif isModify>
							   <#if orderCategory != 'chg' && ((orderCategoryNameMap.get(orderCategory) == '退货') || (order.ifNeedRefund != '0') || ((orderCategoryNameMap.get(orderCategory) == '拒收') && (order.statusPay =='0420'))) >	
								<select id="ifNeedRefund" <#if order.statusConfirm = '0807'>disabled</#if> onchange="isDisplayCreditNO()">
									<#list allIfNeedRefundList as list>
									  <#if (list.codeLong != '0')>
										<option value="${list.codeLong}"<#if (list.codeLong == order.ifNeedRefund)!> selected</#if>>
											<#if (list.codeLong == '')!>
												请选择
											<#elseif (list.codeLong == '1')!>
												网天退款
											<#else>
												${list.desc}
											</#if>
										</option>
									  </#if>
									</#list>
								</select>
								<select id="ifNeedRefund2" style="display:none;">
									<option value="1">网天退款</option>
								</select>
								<input id="hiddenIfNeedRefund" type="hidden" name="order.ifNeedRefund" value=""/>
							   <#assign isNeed=1>
							 <#else>
								<input id="ifNeedRefund" type="hidden" value="0"/>
								<select name="order.ifNeedRefund">
									<option value="0">
										否
									</option>
								</select>
							 </#if>
							<!-- 修改 end-->
							</#if>
							</#if>
							<span id="creditNO" style="display:none;">&nbsp;赊销单号：
								<#if (orderCategory == 'ref') || (orderCategory == 'tsf')>
									<input id="creditNOval"  type="text" value="${(orderSub.bolNo)!}"  disabled/>
								<#else> 
									<#if isCreated>
										<input id="creditNOval" name="orderSub.bolNo"  type="text" value="" />
									<#elseif isDetail>	
										<input id="creditNOval" name="orderSub.bolNo"  type="text" value="${(orderSub.bolNo)!}"  disabled/>
									<#else>		
										<input id="creditNOval" name="orderSub.bolNo"  type="text" value="${(orderSub.bolNo)!}" />
									</#if>	
								</#if>
							</span>
							<#if !isDetail><label class="requireField">*</label></#if>
							</td>	
						</tr>
						
						<!--
							<th>
								代退门店:
							</th>
							<td><select id="selfTakePoint" name="selfTakePoint.id" disabled>
									<#list selfSalePointList as list>
										<option value="${list.id}"<#if list.id == selfTakePoint.id> selected </#if>>
											<#if list.id == "">
											请选择
											<#else>
												${list.pointName}
											</#if>
										</option>
									</#list>
								</select>
							</td>
						-->
					
					<tr id="rowAddress" <#if ('3' != orderSub.distributeType)!>style="display:none"</#if>>
						<th>
							自提点：
						</th>
					    <td>
			    		    <#include "/WEB-INF/template/admin/order/selfTakePoint_linkage_DetailPage.ftl">
			    	    </td>
			    	    <th>
							自提点地址
						</th>
					    <td>
					    	<!--
			    		    	<span id="address">${(selfTakePoint.address)!}</span>	
			    		    -->
			    		    <textarea rows="2" cols="40" id="address" name="orderSub.provideAddress" readonly>${(orderSub.provideAddress)!}</textarea>
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
				
			
 <!--
 <button id="btnConfirm" type="button">询问信息</button>
 -->                   
 
</div>