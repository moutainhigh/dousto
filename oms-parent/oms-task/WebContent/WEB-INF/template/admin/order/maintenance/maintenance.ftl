<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<#assign baseWeb="/sc-webui">
<#-- security.tld 角色权限控制 -->
<#assign security=JspTaglibs["/WEB-INF/tlds/security.tld"] />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>异常列表 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
    <script src="${base}/scripts/list.js"></script> 
<script type="text/javascript">
   
$.gotoPage = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageTotalCount").val(), buttonClickCallback: $.gotoPage });
    $("#pageNumber").val(pageclickednumber);
    $("#listForm").submit();
};
	
</script>

<style type="text/css">
.loading {
	position: absolute;
	margin-top: 110px;
	margin-left: 400px;
	border: solid 1px gray;
	width: 200px;
	height: 50px;
	padding-left: 50px;
	padding-top: 20px;
	background-color: gold;
}
</style>
<script type="text/javascript">
function exceptionSearch(){
	var form = document.getElementById("listForm");
	form.submit();
}

function downExcel(){
    var form1 = document.getElementById("excelForm");
   form1.action="maintenance_excel.action";
	form1.submit();
}
function batchR(){
	var count = 0;
    $("[name='ids']").each(function(){
      if($(this).attr("checked"))     
	  {     
	   	count=count+1;
	  }     
	});
	if(count<=0)
	{
		alert("请选择需要执行的订单");
		return;
	}
	
	
	var as = []; 
	var orderSubNos = []; 
	$("[name='ids']").each(function(i,o){
      if($(this).attr("checked"))     
	  {  
	  	var id = $(this).val();
	  	var subNo = $("#orderSubNo_" + id).val();
		as.push($(this).val()); 
		orderSubNos.push(subNo); 
	  }  
	});
	
	var params = {
			ids:as,
			orderSubNos:orderSubNos
	};
	var form = document.getElementById("listForm");
	form.action="maintenance_query.action";
	if($(this)[0].confirm('确定执行吗?'))
	{
		$.ajax({
			type:"POST",
			url:"maintenance_run!batchRun.action",
			data:params,
			dataType : "json",
			beforeSend: function () { $("#loading").attr({style:"display:inline-block"}); },
			success:function(data){
				$("#loading").attr({style:"display:none"});
				if(data.status == "success"){
				    alert(data.message); 
				    form.submit(); 	
				}else{
				   alert(data.message);
				   form.submit(); 
				}
			},
			error : function(data){
				alert("error!")
				form.submit();
			}
		});	
	}
}

</script>
<script  type="text/javascript" src="${base}/template/admin/js/order_advanced_search.js"></script>

</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		 <div id="loading" class="loading" style="display:none;">正在执行，请耐心等待...</div>
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>异常列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="maintenance_query.action" method="post" validate="true">
	    	<div id="MainTable">
	    	
	    	</div>	
	    	
	    	<div class="operateBar">
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
			</div>		
			
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<td>行订单号:<input type="text" name="map['orderItemNo']" value="${(map['orderItemNo'])!}" /></td>
					<!--<td>订单号:<input type="text" name="map['orderNo']" value="${(map['orderNo'])!}" /></td>-->
					<td>订单号:<input type="text" name="map['orderSubNo']" value="${(map['orderSubNo'])!}" /></td>
                    <!--
                    <td>订单状态:<select name="map['orderStatus']">
							<option value="" <#if map['orderStatus'] == "">selected="selected" </#if>  >全部</option>
							<option value="1" <#if map['orderStatus'] == "1">selected="selected" </#if> >购物车</option>
							<option value="2"<#if map['orderStatus']== "2">selected="selected" </#if> >已支付</option>
						</select>
					</td>
					<td>来源系统:
						<select name="map['orderSource']">
							<option value="" <#if map['orderSource'] == "">selected="selected" </#if> >全部</option>
							<option value="1" <#if map['orderSource'] == "1">selected="selected" </#if> >B2C</option>
							<option value="2" <#if map['orderSource'] == "2">selected="selected" </#if> >移动app</option>
							<option value="3" <#if map['orderSource'] == "3">selected="selected" </#if> >微信</option>
						</select>
					</td>
					-->
					<td>错误码：<select name="map['errorCode']">
									<#list errorCodeList as list>
									<option value="${list.code}" <#if list.code == map['errorCode']>selected</#if>>
									<#if list.code == ''>
									全部
									<#else>
									${list.desc}
									</#if>
									</option>
									</#list>
								</select>
					</td>
				</tr>
				<tr>
					<td>开始日期:<input type="text" readonly="true"  onclick="WdatePicker()" name="map['strStartDate']" value="${(map['strStartDate'])!}" ></td>
					<td colspan="2">结束日期:<input type="text" readonly="true"  onclick="WdatePicker()" name="map['strEndDate']" value="${(map['strEndDate'])!}" ></td>
				</tr>
				
				<tr>
					<td colspan="3">
						<input type="button" class="button_red" name="search" id="search" onclick="exceptionSearch();" value="查询"/>
						<#if (pager.list?size > 0)>
							<input type="button" class="button" name="downexcel" id="downexcel" onclick="downExcel();"  value="导出excel"/>
						</#if>
					</td>
				</tr>	
			</table>
			<br>
			
			<input type="button" class="button" name="batchRun" id="batchRun" onclick="batchR();" value="批量执行"/>
			
			
			<h4 id="searchCondition" style="border: 1px solid #e0e2e3; background-image: url(../resources/admin/images/admin_info.png);padding: 11px 0px;  width:100%; color:#58666e;" >
			<span id="searchConditionIcon" class="displaySearchCondition"></span>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;搜索结果
		</h4>
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 100%;">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th width="5%">
						<span class="sort" name="id">ID</span>
					</th>
					<!--
					<th width="10%">
						<span class="sort" name="orderItemNo">行订单号</span>
					</th>
					-->
					<!--
					<th width="10%">
						<span class="sort" name="orderNo">主订单号</span>
					</th>
					-->
		
			
					<th width="10%">
						<span class="sort" name="orderSubNo">订单号</span>
					</th>
					<th width="10%">
						<span class="sort" name="time">时间</span>
					</th>
					<th width="10%">
						<span class="sort" name="errorType">异常类型</span>
					</th>
					<th width="20%">
						<span class ="sort" name="errorCode">异常编码</span>
					</th>
					<!--
					<th width="20%">
						<span class="sort" name="errorDesc">异常描述</span>
					</th>
					-->
					<th  width="4%">
						操作
					</th>
				</tr>
				<#list pager.list as list>
                     <tr>
                     	<td  width="2%">
							<input type="checkbox" name="ids" value="${(list.id)!}" />
							<input type="hidden" id="orderSubNo_${(list.id)!}" value="${(list.orderSubNo)!}" />
						</td>	
						<td width="5%">
							${(list.id)!}
						</td>
						<!--
						<td width="10%">${(list.orderItemNo)!}</td>
						-->
						<!--
						<td width="10%">${(list.orderNo)!}</td>
						-->
		
						<td width="10%">
							${(list.orderSubNo)!}
						</td>							
					   <td width="10%">${(list.operateTime?string("yyyy-MM-dd"))!}</td>
						<td width="10%">
							${(list.errorType)!}
						</td>					
						<td width="20%">
							${(list.errorCode)!}
						</td>
						<!--						
						<td>
							${(list.errorDesc)!}
						</td>
					    -->
						<td>
							<a href="maintenance_view.action?id=${(list.id)!}" title="详情">[详情]</a>
						</td>
					</tr>
					
                  
				</#list>
			</table>
			<input  type="text" id="currentStatus" name="currentStatus" value="${currentStatus}" style="display:none"></input>
			<input  type="text" id="selected" name="selected" value="${selected}" style="display:none"></input>  
			<input  type="text" id="selectedTabName" name="selectedTabName" value="${selectedTabName}" style="display:none"></input>  
			</div>
			
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
		 <form id="excelForm" action="maintenance!query.action" method="post">
						<input type="hidden" name="map['orderItemId']" value="${(map['orderItemId'])!}" />
						<input type="hidden" name="map['orderId']" value="${(map['orderId'])!}" />
						<input type="hidden" name="map['orderStatus']" value="${(map['orderStatus'])!}" />
						<input type="hidden" name="map['orderSource']" value="${(map['orderSource'])!}" />
						<input type="hidden" name="map['operateType']" value="${(map['operateType'])!}" />
						<input type="hidden" name="map['errorCode']" value="${(map['errorCode'])!}" />
		</form>
		
	</div>
</body>
</html>