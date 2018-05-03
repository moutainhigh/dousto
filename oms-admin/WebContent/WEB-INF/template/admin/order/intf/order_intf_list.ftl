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
<style type="text/css">
label.error {
	color: red;
	font-style: italic
}
div.error { display: none; }

input.error { border: 1px solid red; }
</style>

<#include "/WEB-INF/template/common/include.ftl">
    <link href="${base}/resources/admin/css/list.css" rel="stylesheet" type="text/css" />
    <link href="${base}/resources/admin/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${webuiPath}/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${webuiPath}/scripts/jquery/validation/dist/jquery.validate.js"></script>

<script type="text/javascript">
	$().ready(function() {
			
			$("#listForm").validate({
				/*errorLabelContainer: "#messageBox",		//显示错误信息的容器ID
				wrapper: "li",								//包含每个错误信息的容器*/
				rules:{
					"log.orderNo":{
						required: true
					}
				},
				messages:{
					"log.orderNo":{
						required: "请填写订单编号"
					}
				}
		});
	});
	

		
</script>
<script type="text/javascript">

$.gotoPage = function(pageclickednumber) {
    $("#pager").pager({ pagenumber: pageclickednumber, pagecount: $("#pageTotalCount").val(), buttonClickCallback: $.gotoPage });
    $("#pageNumber").val(pageclickednumber);
    $("#listForm").submit();
};

function addIntfCode(obj){
   
   var objs =  $("#intfCodeId");
   $("#intfCodeId").empty();
   var intfType = obj.value;
   if(intfType=='1'){
      <#list receiveList as rinfo>
      
       	<#if (rinfo)??>
       	 $("#intfCodeId").append("<option value='${rinfo.code}'>${rinfo.desc}</option>"); 
		<#else>	
		 $("#intfCodeId").append("<option value=''>全部</option>"); 
		</#if>		
	   </#list>
   
   }else{
    <#list sentList as sinfo>
    	<#if (sinfo)??>
    		 $("#intfCodeId").append("<option value='${sinfo.code}'>${sinfo.desc}</option>"); 	
		<#else>	
		     $("#intfCodeId").append("<option value=''>全部</option>"); 
		</#if>				
	   </#list>
   
   }
  
}

</script>


</head>
<div id="dialog-confirm" title="">
</div>
<body class="list">
	<div class="body">
		<div class="listBar">
			<h1><span class="icon">&nbsp;</span>订单操接口列表&nbsp;<span class="pageInfo">总记录数: ${pager.totalCount}(共${pager.pageCount}页)</span></h1>
		</div>	
		<form id="listForm" action="order_intf_query.action" method="post" validate="true">
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
					<td>接口类型:
					<select name="map['intfType']" onchange="addIntfCode(this)">
					
								<option value="1" <#if map['intfType']=='1'> selected="selected" </#if>>
									接收
								</option>
								<option value="2"  <#if map['intfType']=='2'> selected="selected" </#if>>
									发送
								</option>
					</select>
							</td>
					<td>接口名称:			
						<select name="map['intfCode']" id="intfCodeId">
						<#if map['intfType']=='2'>
								<#list sentList as list>
									<option value="${list.code}" <#if map['intfCode']==list.code> selected="selected" </#if>>
											${list.desc}
									</option>
								</#list>
						<#else>		
					     		<#list  receiveList  as list>
									<option value="${list.code}" <#if map['intfCode']==list.code> selected="selected" </#if>>
											${list.desc}
									</option>
								</#list>
						</#if>
						
						</select>
					</td>
					<!--
						<td>订单号： <input type="text" name="map['orderNo']" value="${(map['orderNo'])!}" ></td>
					-->
					<td>订单号： <input type="text" name="map['orderSubNo']" value="${(map['orderSubNo'])!}" ></td>
					<td>开始日期： <input type="text" readonly="true"  onclick="WdatePicker()" name="map['strStartDate']" value="${(map['strStartDate'])!}" ></td>
					<td>结束日期： <input type="text" readonly="true"  onclick="WdatePicker()" name="map['strEndDate']" value="${(map['strEndDate'])!}" ></td>
					<td style="text-align: left;padding-bottom: 3px;"><span id="orderNoTip"></span></td>
				</tr>
				
				<tr>
					<td colspan="4">
						<input type="submit" class="previewButton" name="search" value="查询"/>
					</td>
				</tr>	
			</table>
			<br>
	
			<table class="listTable" style="border: 1px solid #CCCCCC;width: 95%;">
				<tr>
					<th class="check">
						<input type="checkbox" class="allCheck" />
					</th>
					<th>
						<span class="sort" name="id">ID</span>
					</th>
					<th>
						<span class="sort" name="time">时间</span>
					</th>
					<th>
						<span class="sort" name="intfCode">接口编号</span>
					</th>
					<th>
						<span class="sort" name="intfName">接口名称</span>
					</th>
					
					<th>
						<span class="sort" name="flag">处理结果<span>
					</th>
					<th>
						<span class="sort" name="opt">操作</span>
					</th>
					
				</tr>
				<#list pager.list as list>
                     <tr>
						<td>
							<input type="checkbox" name="ids" value="${(list.id)!}" />
						</td>
						<td>
							${(list.id)!}
						</td>
						<td>${(list.createTime?string("yyyy-MM-dd"))!}</td>
						<td>${(list.intfCode)!}</td>
						
						<td>
						<#if map['intfType']=='1'>
						     <#list receiveList as rinfo>
									<#if list.intfCode == rinfo.code>
										${rinfo.desc}
									</#if>
								</#list>
						
						<#elseif map['intfType']=='2'>
						       <#list sentList as sinfo>
									<#if list.intfCode == sinfo.code>
										${sinfo.desc}
									</#if>
								</#list>
						</#if>
						
						</td>
						
						<td>
							 <#if list.succeed == 0>待执行
						 
							 <#elseif list.succeed== 1>成功
							 
							 <#else>失败
							 </#if> 
						</td>
						
						<td>
							<a href="order_intf_detail.action?intfType=${(map['intfType'])!}&id=${(list.id)!}" title="查看">[查看]</a>
						</td>		
						
					</tr>
					
                  
				</#list>
			</table>
		
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
		
	</div>
</body>
</html>