<#assign s=JspTaglibs["/WEB-INF/tlds/struts-tags.tld"]>
<#assign sj=JspTaglibs["/WEB-INF/tlds/struts-jquery-tags.tld"]>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>新增订单审核店铺配置 - Powered By ${systemConfig.systemName}</title>
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
<div id="dialog-confirm" title="">
</div>
<script type="text/javascript">
	function selectedProvice(province) {
		loadSelect("city", province.value);
	}
	function selectedCity(city) {
		loadSelect("county", city.value);
	}
	function selectedCounty(county) {
		updateRegion();
	}
	function loadSelect(selectId, parentId) {
		$.ajax({
			type:"GET",
			url:"order_audit_region_config!findByParentId.action",
			data: {parentId : parentId},
			dataType : "json",
			success:function(data){
				var select = $("#"+selectId);
				select.empty();
				select[0].options.add(new Option("请选择", ""));
				$(data).each(function(index, result){
					select[0].options.add(new Option(result.areaName, result.areaCode));
				});
				
				updateRegion();
			}
		});	
	}
	
	function updateRegion() {
		var regionCode;
		var regionName;
		var regionLevel;
		
		var proviceSelected = $("#province")[0].selectedOptions[0]; 		
		var citySelected = $("#city")[0].selectedOptions[0]; 		
		var countySelected = $("#county")[0].selectedOptions[0]; 		
		if(countySelected && countySelected.value) {
			regionCode = countySelected.value;
			regionName = proviceSelected.text+citySelected.text+countySelected.text;
			regionLevel = 3;
		} else if(citySelected && citySelected.value) {
			regionCode = citySelected.value;
			regionName = proviceSelected.text+citySelected.text;
			regionLevel = 2;
		} else if(proviceSelected && proviceSelected.value){
			regionCode = proviceSelected.value;
			regionName = proviceSelected.text;
			regionLevel = 1;
		} else {
			regionCode = "";
			regionName = "";
		}
		
		$("#regionName").val(regionName);
		$("#regionCode").val(regionCode);
		$("#regionLevel").val(regionLevel);
	}
</script>
<body class="input">
	<div class="body">
		<div class="inputBar">
			<h1><span class="icon">&nbsp;</span>新增订单审核店铺配置</h1>
		</div>
			<div class="blank"></div>
			<div style="clear:both;"/>
			<#assign width=-50 />	
			<form id="inputForm" class="validate" action="order_audit_region_config!createConfig.action" method="post">
			<div class="blank"></div>
			<table class="inputTable">
				<tr>
					<th>
						地区
					</th>
					<td>
						<input id="regionName" name = "regionConfig.name" readonly>
						<input type="hidden" id="regionCode" name = "regionConfig.code">
						<input type="hidden" id="regionLevel" name = "regionConfig.level">
					</td>
				</tr>
				<tr>
					<th>
						省
					</th>
					<td>
						<select id="province"  onchange="selectedProvice(this)">
							<option value="">请选择</option>
							<#list provinces as province> 
								<option value="${province.areaCode}">${province.areaName}</option>
							</#list>
						</select>
					</td>
				</tr>
				<tr>
					<th>
						市
					</th>
					<td>
						<select id="city" onchange="selectedCity(this)"></select>
					</td>
				</tr>
				<tr>
					<th>
						区
					</th>
					<td>
						<select id="county" onchange="selectedCounty(this)"></select>
					</td>
				</tr>
			</table>
			<div class="buttonArea">
				<input type="submit" class="formButton" value="确  定" hidefocus="true" />&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" class="formButton" onclick="window.location.href='order_audit_config!view.action#orderConfigDiv';" value="返  回" hidefocus="true" />
			</div>
		</form>	
	</div>
		
</body>

</html>