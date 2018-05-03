<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单列表 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${baseWeb}/scripts/jquery/themes/base/jquery-ui.css" id="theme">
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.pager.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.metadata.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.methods.js"></script>
<script type="text/javascript" src="${baseWeb}/scripts/jquery/js/plugins/jquery.validate.cn.js"></script> 
<script type="text/javascript" src="${base}/template/admin/js/list.js"></script>

<script type="text/javascript">
	var base = '${base}';
	$().ready( function() {
		$.subscribe('tabchange', function(event,data) {
			$("#currentStatus").val(event.originalEvent.ui.tab.parentNode.id);
			$("#selected").val(event.originalEvent.ui.index);
			$("#searchButton").click();
	    });
    });
	
	function showDialog(message,_buttons){
		$( "#dialog-confirm" ).html(message);
		$( "#dialog-confirm" ).dialog({
			resizable: false,
			height:140,
			modal: true,
			buttons:_buttons
		});
	}
	
	function approve(){
		ajaxAction(base+'/order/order!approve.action?event=approved');
	}
	
	function preNotApprove(){
		var message="请输入不通过原因<input id='reasonInput' type=text ></input>";
		showDialog(
			message,{
				"取消": function(){
					$( this ).dialog( "close" );
				}, 
				"确定": function() {
					var faileReason=$("#resonInput").val();
					notApprove(faileReason);
					$( this ).dialog( "close" );
				}
			}
		)
	}
	
	

	
	function notApprove(faileReason){
		ajaxAction(base+'/order/order!approve.action?event=not-approved&faileReason='+faileReason);
	}
	
	function ajaxAction(_url){
		var $idsCheckedCheck = $(".list input[name='ids']:checked");
		$.ajax({
			  url: _url,
			  data: $idsCheckedCheck.serialize(),
			  dataType: "json",
			  cache: false,
			  async: false,
			  success: function(data){
			  	  $idsCheckedCheck.parent().parent().remove();
				  showDialog(data,
				  			{
								"确定": function() {
									$( this ).dialog( "close" );
								}
							})
			  }
		});
	}
</script>
</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>发货单列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="shipping!list.action" method="post">
			<@sj.tabbedpanel id="localtabs" onChangeTopics="tabchange" selectedTab="${selected}" >
				  	<@sj.tab id="all" target="MainTable" label="全部订单" />
					<@sj.tab id="submitted" target="MainTable" label="已提交" />
					<@sj.tab id="shipping" target="MainTable" label="发货中" />
					<@sj.tab id="shipped" target="MainTable" label="已发货" />
					<@sj.tab id="received" target="MainTable" label="确认收货" />
					<@sj.tab id="completed" target="MainTable" label="已完成" />
					<@sj.tab id="invalid" target="MainTable" label="作废" />
	    	<div id="MainTable" >			
			<div class="operateBar">
				<label>查找:</label>
				<select name="pager.property">
					<option value="shippingNo" <#if pager.property == "shippingNo">selected="selected" </#if>>
						发货单号
					</option>
					<option value="member.username" <#if pager.property == "member.username">selected="selected" </#if>>
						会员
					</option>
					<option value="consignee" <#if pager.property == "consignee">selected="selected" </#if>>
						收货人
					</option>
					<option value="shipAddress" <#if pager.property == "shipAddress">selected="selected" </#if>>
						收货地址
					</option>
				</select>
				<label class="searchText"><input type="text" name="pager.keyword" value="${pager.keyword!}" /></label><input type="button" id="searchButton" class="searchButton" value="" />
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
				<input type="button" id="approveButton" class="formButton" onclick="approve()" value="审核通过"  hidefocus="true" />
				<input type="button" id="approveButton" class="formButton" onclick="preNotApprove()" value="审核不通过"  hidefocus="true" />
				<input type="button"  class="formButton" url="order!invalid.action" value="作废"  hidefocus="true" />
			</div>
			<table class="listTable" >
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span class="sort" name="shippingNo">发货单号</span>
					</th>
					<th>
						<span class="sort" name="member">会员</span>
					</th>
					<th>
						<span name="orderSn">订单号</span>
					</th>
					<th>
						<span class="sort" name="totalAmount">订单总额</span>
					</th>
					<th>
						<span class="sort" name="shippingDate">提交日期</span>
					</th>																
					<th>
						<span name="consignee">收货人</span>
					</th>
					<th>
						<span class="sort" name="shippingStatus">状态</span>
					</th>					

					<th>
						<span name="shipMode.name">配送方式</span>
					</th>
					<th>
						<span name="carrierName">物流公司</span>
					</th>						
					<th>
						<span class="sort" name="deliveryNo">快递单号</span>
					</th>						
					<th>
						操作
					</th>
				</tr>
				<#list pager.list as list>
					<tr>
						<td>
							<input type="checkbox" name="ids" value="${(list.id)!}" />
						</td>
						<td>
							${list.shippingNo}
						</td>
						<td>
							${(list.member.username)!"-"}
						</td>
						<td>
							${(list.orderSn)!""}
						</td>
						<td>
							${(list.totalAmount?string(orderUnitCurrencyFormat))!}
						</td>								
						<td>
							<span title="${(list.shippingDate?string("yyyy-MM-dd HH:mm:ss"))!}">${(list.shippingDate?string("yyyy-MM-dd HH:mm:ss"))!}</span>
						</td>	
						<td>
							${(list.shipAddress.consignee)!}
						</td>																					
						<td>
							${(list.shippingStatus.display())}
						</td>
						<td>
							${(list.shipMode.name)!}
						</td>
						<td>
							${(list.carrier.name)!}
						</td>			
						<td>
							${(list.deliveryNo)!}
						</td>										
						
						<td>
							<a href="shipping!view.action?id=${list.id}" title="查看">[查看]</a>
						</td>
					</tr>
				</#list>
			</table>
			<input  type="text" id="currentStatus" name="currentStatus" value="${currentStatus}" style="display:none"></input>
			<input  type="text" id="selected" name="selected" value="${selected}" style="display:none"></input>  
			</div>
			</@sj.tabbedpanel>	
			<#if (pager.list?size > 0)>
				<div class="pagerBar">
					<#include "/WEB-INF/template/admin/pager.ftl" />
				</div>
			<#else>
				<div class="noRecord">
					没有找到任何记录!
				</div>
			</#if>
		</form>
	</div>
</body>
</html>