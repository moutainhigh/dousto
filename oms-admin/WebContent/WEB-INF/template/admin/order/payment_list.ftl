<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>收款单列表 - Powered By ${systemConfig.systemName}</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${baseWeb}/scripts/jquery/themes/base/jquery-ui.css" id="theme">
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.pager.js"></script>

<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>

<script type="text/javascript">
	$().ready( function() {
		$.subscribe('tabchange', function(event,data) {
			var status = event.originalEvent.ui.tab.parentNode.id;
			if ("all"==status){
				status=""
			}
			$("#selected").val(event.originalEvent.ui.index);
			submitStatus(status);
			
			$("#searchButton").click();
	    });
    });
    
    function submitStatus(status){
    	$('#status').val(status);
    	$('#listForm').submit();
    }
    
    function approveAll(){
    	var idsChecked = $(".list input[name='ids']:checked");
    	if (idsChecked.size()<=0){
    		alert("请选择要支付的收款单！");
    		return;
    	}
    	if (confirm("您确定已支付成功吗？") == true) {
			$.ajax({
				url: "payment!successPaid.action",
				data: idsChecked.serialize(),
				dataType: "json",
				async: false,
				beforeSend: function(data) {
					$("approveButton").attr("disabled", true)
				},
				error:function(data){
					alert(data.responseText);
				},
				success: function(data) {
					$("approveButton").attr("disabled", false)
					alert(data.message);
					submitStatus("waitingApproval");
				}
			});
		}
    }
</script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>收款单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>
		<form id="listForm" action="payment!list.action" method="post">
			<input  type="text" id="selected" name="selected" value="${selected}" style="display:none"></input>
			<@sj.tabbedpanel id="localtabs" onChangeTopics="tabchange" selectedTab="${selected}" >
				  	<@sj.tab id="all" target="MainTable" label="全部" />
					<@sj.tab id="approving" target="MainTable" label="等待付款" />
					<@sj.tab id="waitingApproval" target="MainTable" label="等待审核" />
					<@sj.tab id="success" target="MainTable" label="已付款" />
					<@sj.tab id="reconciled" target="MainTable" label="已对账" />
					<@sj.tab id="failure" target="MainTable" label="失败" />
		<div id="MainTable">
			<div class="input">
				<input id="status" type="hidden" name="pager.queryMap.status" value="" >
			</div>		
			<div class="operateBar">
			<input type="button" class="addButton" onclick="location.href='payment!input.action?storeId=${(storeId)!}'" value="新建收款单" />
				<label>查找:</label>
				<select name="pager.property">			
					<option value="paymentSn" <#if pager.property == "paymentSn">selected="selected" </#if>>
						收款单号
					</option>
					<option value="orderNo" <#if pager.property == "orderNo">selected="selected" </#if>>
						订单号
					</option>
					<option value="payer" <#if pager.property == "payer">selected="selected" </#if>>
						会员用户名
					</option>																									
					<option value="paymentMode" <#if pager.property == "paymentMode">selected="selected" </#if>>
						支付方式
					</option>				
				</select>
				<select name="pager.symbol">					
					<option value="equal" <#if pager.symbol == "equal">selected="selected" </#if>>
					=
					</option>
					<option value="like" <#if pager.symbol=="like">selected="selected" </#if>>
					like
					</option>
					<option value="greater" <#if pager.symbol=="greater">selected="selected" </#if>>
					>
					</option>
					<option value="less" <#if pager.symbol=="less">selected="selected" </#if>>
					<
					</option>
				</select>
				<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label>
				<input type="button" id="searchButton" class="searchButton" value="" />
				<label>每页显示:</label>
				<select name="pager.pageSize" id="pageSize">
					<option value="10" <#if pager.pageSize == 10>selected="selected" </#if>>
						10
					</option>
					<option value="20" <#if pager.pageSize == 20>selected="selected" </#if>>
						20
					</option>
					<option value="50" <#if pager.pageSize == 50>selected="selected" </#if>>
						50
					</option>
					<option value="100" <#if pager.pageSize == 100>selected="selected" </#if>>
						100
					</option>
				</select>
			<#if selected=="2">
			<input type="button" id="approveButton" class="addButton" onclick="approveAll()" value="成功支付" />
			</#if>
			</div>
			<table class="listTable">
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
					<th>
						<span class="sort" name="reconciledDate">付款日期</span>
					</th>
					<th>
						<span class="sort" name="totalAmount">付款金额</span>
					</th>					
					<th>
						<span class="sort" name="paymentModeId">付款方式</span>
					</th>
					<th>
						<span>支付平台（银行）</span>
					</th>
					<th>
						<span>帐户</span>
					</th>

					<th>
						<span>状态</span>
					</th>
					<th>
						操作
					</th>
				</tr>
				<#assign currentDate="2011-01-01"?datetime("yyyy-MM-dd")>
				<#list pager.list as list>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${(list.id)!}" />
						</td>
						<td>
							${(list.paymentSn)!}
						</td>
						<td>
							${(list.order.orderNo)!}
						</td>
						<td>
							${(list.payerMember.accountName)!}
						</td>
						<td>						
							<#if list.reconciledDate?? && (list.reconciledDate>currentDate)>
							${list.reconciledDate?string("yyyy-MM-dd HH:mm")!}
							</#if>
						</td>	
						<td>
							${(list.totalAmount?string(orderUnitCurrencyFormat))!}
						</td>
					
						<td>
							<#assign mode=list.paymentMode />
							${(mode.name)!}
						</td>						
						<td>
							${(list.bankName)!}
						</td>
						<td>
							${(list.bankAccount)!}
						</td>
						<td>
							${list.status.display()}
						</td>
						<td>
							<a href="payment!view.action?id=${list.id}" title="查看">[查看]</a>
						</td>
					</tr>
				</#list>
			</table>
			</div>
			</@sj.tabbedpanel>
			<#if (pager.list?size > 0)>
			<!--
				<div class="pagerBar">
					<input type="button" class="deleteButton" url="payment!delete.action" value="删 除" disabled hidefocus="true" />
					<#include "/WEB-INF/template/admin/pager.ftl" />
				</div>
			-->
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		
		
		</form>
	</div>
</body>
</html>