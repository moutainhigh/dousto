<#assign sjr=JspTaglibs["/WEB-INF/tlds/struts-jquery-richtext-tags.tld"]>
<#assign baseWeb="/sc-webui">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>更改订单项 - Powered By IBM</title>
<meta name="Author" content="IBM Team" />
<meta name="Copyright" content="IBM" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/template/common/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/input.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/list.css" rel="stylesheet" type="text/css" />
<link href="${base}/template/admin/css/order.css" rel="stylesheet" type="text/css"  />
<@sj.head compressed="false" scriptPath="/sc-webui/scripts/jquery/" jqueryui="true" loadAtOnce="true"/>
<style>
	.mask {
		left: 0px;
		position: absolute;
		top: 0px;
		background-image: none;
		z-index :65535;
	}
</style>
<script type="text/javascript">
	var _orderId = '${id}';
	$().ready(function(){
    	hideAddressDiv();
    	
        var addressState = '${(address.state)!}';
        var addressCity = '${(address.city)!}';
        var addressCounty = '${(address.county)!}';
        
        $.getJSON('${base}/member/state_json.action', {
            selectName: addressState
        }, stateJson);
        $.getJSON('${base}/member/city_json.action', {
            stateAbbr: addressState,
            selectName: addressCity
        }, cityJson);
        $.getJSON('${base}/member/county_json.action', {
            cityCode: addressCity,
            selectName: addressCounty
        }, countyJson);

        $("#stateSelect").change(function(){
            $.getJSON('${base}/member/city_json.action', {
                stateAbbr: this.value,
                selectName: addressCity
            }, cityJson);
        });
        
        $("#citySelect").change(function(){
            $.getJSON('${base}/member/county_json.action', {
                cityCode: this.value,
                selectName: addressCounty
            }, countyJson);
        });
        $("#addressSaveBtn").click(function(){
        	saveAddress();
        });
		$("#cancelSaveBtn").click(function(){
			hideAddressDiv();
		});
        $("input[id='useAddress']").change(function(){
        	
        	hideAddressDiv();
			show();
        	$.getJSON('${base}/shipping/shipping_address_change.action', {
                addressId: this.value,
                orderId: _orderId
            },userAddressChanged);
        });
		
			$("input[id=ship_mode]").change(function(){
		//计算支付促销
		show();
		$.getJSON('${base}/shipping/shipping_mode_change.html', {
            shipModeId: this.value,
            orderId: _orderId
        }, function(data){
        	//当前选择运送方式是否支持货到付款
        	if(data.shipModeSupportCod=='true'){
        		$("input[id='payment_mode']:first").removeAttr("disabled");
        	}else{
        		$("input[id='payment_mode']:first").attr("disabled","true");
        	}
        	hide();
        });
	});
	$("input[id='receive_date']").change(function(){
		//计算支付促销
		show();
		$.getJSON('${base}/shipping/shipping_time_change.html', {
            receiveDate: this.value,
            orderId: _orderId
        }, function(){
        	hide();
        });
	});
    });
	
	function userAddressChanged(data){
         //根据配送地址查询配送方式
    	updateShipMode(data);
    	hide();
	}
	function stateJson(data){
	    var list = data.list;
	    var options = "<option value=''>请选择</option>";
	    for (var i = 0; i < list.length; i++) {
			if (list[i].checked == true) {
				options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
			}else {
				options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
			}
		}
	    $("#stateSelect").html(options);
	}
	function cityJson(data){
	    var list = data.list;
	    var options = "<option value=''>请选择</option>";
	    for (var i = 0; i < list.length; i++) {
	        if (list[i].checked == true) {
	            options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
	        }
	        else {
	            options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
	        }
	    }
	    $("#citySelect").html(options);
	} 
	function countyJson(data){
	    var list = data.list;
	    var options = "<option value=''>请选择</option>";
	    for (var i = 0; i < list.length; i++) {
	        if (list[i].checked == true) {
	            options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
	        }
	        else {
	            options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
	        }
	    }
	    $("#countySelect").html(options);
	}
	function saveAddress(){
		//验证必输
		var regxphone = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
		var regxzip = /^[0-9]{6}$/;
		//收件人地址
		if($("#address_addressName").val()==""){
			$(".errorContent").html("请填写收货人姓名！");
			$("#ajaxErrorBox").dialog("open");
			return;
		}
		//城市
		if($("#countySelect option:selected").val()==""){
			$(".errorContent").html("请填写收货人所在城市！");
			$("#ajaxErrorBox").dialog("open");
			return;
		}
		if($("#address_detail").val()==""){
			$(".errorContent").html("请填写收货人详细地址！");
			$("#ajaxErrorBox").dialog("open");
			return;
		}
		
		if($("#address_zipcode").val()==""){
			$(".errorContent").html("请填写收货人邮政编码！");
			$("#ajaxErrorBox").dialog("open");
			return;
		}
		if(regxzip.test($("#address_zipcode").val())==false){
			$(".errorContent").html("收货人邮政编码格式不正确！");
			$("#ajaxErrorBox").dialog("open");
			return;
		}
		if($("#address_phone").val()==""&&$("#address_mobile").val()==""){
			$(".errorContent").html("手机和固定电话至少有一项必填！");
			$("#ajaxErrorBox").dialog("open");
			return;
		}else{
			if($("#address_phone").val()!=""){
				if((regxphone.test($("#address_phone").val()))==false){
					$(".errorContent").html("电话号码格式不正确！");
					$("#ajaxErrorBox").dialog("open");
					return;
				}
			}
			if($("#address_mobile").val()!=""){
				if((/^[1][0-9]{10}$/.test($("#address_mobile").val()))==false){
					$(".errorContent").html("手机号码格式不正确！");
					$("#ajaxErrorBox").dialog("open");
					return;
				}
			}
		}
		
		var parameters = $("input[name^='address']").serialize();
		parameters = parameters +"&memberId=${memberId}"+"&address.state="+ $("#stateSelect").val()+"&address.city="+$("#citySelect").val()+"&address.county="+$("#countySelect").val();
		show();
		hideAddressDiv();
		$.getJSON('${base}/shipping/shipping_address!save.action', parameters
	    , updateAddressSection);
	}
	function updateAddressSection(data){
		if(data.status=="warn"){
			hide();
			$(".errorContent").html(data.message);
			$("#ajaxErrorBox").dialog("open");
			return;
		}
		var list = data.addressList;
	
		$("#addressSection").html("");
		var htmlStr = "";
		$.each(list,function(i,n){
			htmlStr = htmlStr+"<div class=\"address\">";
			htmlStr = htmlStr+"<input type=\"radio\" id=\"useAddress\" name=\"order.shippingAddressId\" value=\""+this.id+"\" class=\"style03\" />";
			htmlStr = htmlStr+"<span class=\"style02\">&nbsp;"+this.addressName+"，"+this.country+"，"+this.state+"，"+this.city+"，"+this.county+"，"+this.address+"，"+this.zipcode+"，"+this.phone+"，"+this.mobile+"</span><span><a href=\"javascript:editAddress("+this.id+");\">[修改]</a></span>";
			htmlStr = htmlStr+"</div>";
		});
		$("#addressSection").html(htmlStr);
		$("#addressSection").css("order_address");
		hide();
		
	}
	function editAddress(addressId){
		$.getJSON('${base}/shipping/shipping_address!edit.action', {
	    	id: addressId
	    }, function(data){
	
	    	$("#address_id").val(data.address.id);
	    	$("#address_addressName").val(data.address.addressName);
	    	$("#address_detail").val(data.address.address);
	    	$("#address_zipcode").val(data.address.zipcode);
	    	$("#address_phone").val(data.address.phone);
	    	$("#address_mobile").val(data.address.mobile);
	    	
	    	$.getJSON('${base}/member/state_json.action', {
	            selectName: data.address.state
	        }, stateJson);
	        $.getJSON('${base}/member/city_json.action', {
	            stateAbbr: data.address.state,
	            selectName: data.address.city
	        }, cityJson);
	        $.getJSON('${base}/member/county_json.action', {
	            cityCode: data.address.city,
	            selectName: data.address.county
	        }, countyJson);
	        showAddressDiv();
	    });
		
	}
	function deleteAddress(addressId){
		$.getJSON('${base}/order/shipping_address!delete.action', {
	    	id: addressId
	    }, function(data){
	    	updateAddressSection(data);
	    	alert("删除地址！！"+data.address.addressName);
	    });
	}
	function showAddressDiv(){
		$("#new_address").css("display","block");
	}
	function hideAddressDiv(){
		$("#address_id").val("");
    	$("#address_addressName").val("");
    	$("#address_detail").val("");
    	$("#address_zipcode").val("");
    	$("#address_phone").val("");
    	$("#address_mobile").val("");
		$("#new_address").css("display","none");
	}
	
	function updateShipMode(data){
		var opts = $("input[id=ship_mode]");
		if(data.shipMode!=null){
			$.each(opts,function(i,n){
				if(this.value==data.shipMode.id){
					$(this).attr("checked","true");
					$(this).removeAttr("disabled");
					//是否支持货到付款
					$.getJSON('${base}/shipping/shipping_mode_change.action', {
			            shipModeId: this.value,
			            orderId: _orderId
			        }, updateTotal);
				}else{
					$(this).attr("disabled","true");
				}
			});
		}else{
			$.each(opts,function(i,n){
				$(this).removeAttr("disabled");
				$(this).removeAttr("checked");
			});
		}
	}
	
	function show()
	{
		document.body.style.cursor = 'wait'
	    $("#mask").css("width",document.body.scrollWidth);
		$("#mask").css("height",document.body.scrollHeight);
		$("#mask").show();

	}
	function hide(){
		$("#mask").hide();
		document.body.style.cursor = 'auto'
	}
</script>
</head>
<body class="input">
<div id ="mask" class="mask" style="display:none;"></div>
<#include "/WEB-INF/template/common/ajax_message_div.ftl" />
	<div class="ordertitle">
		<span class="titletext">请输入新的&nbsp;<strong>收货人信息</strong></span>
		<a href="#" class="text_left32" onclick="showAddressDiv();">新增收货人信息</a>
	</div>
	<div class="order_content2">
		<div id="addressSection" class="order_address">
			<#list addressList as list>
				<div class="address">
			       <input type="radio" id="useAddress" <#if order.shippingAddressId == list.id>checked</#if> name="order.shippingAddressId" value="${(list.id)!}" class="style03" />
			       <span class="style02">${(list.addressName)!}，${(list.country)!}，${(list.displayAddress)!}，${(list.zipcode)!}，${(list.phone)!}，${(list.mobile)!}</span><span><a href="javascript:editAddress(${(list.id)!});">[修改]</a></span>
				</div>
			</#list>
			
		</div>
		<span class="blank8"></span>
		<span class="linedot"></span>
		<div id="new_address" class="address2">
			<div class="addressleft">
				<ul>
					<li>*&nbsp;</span>收货人姓名：</li>
					<li>*&nbsp;</span>收货城市：</li>
					<li>*&nbsp;</span>详细地址：</li>
					<li>邮政编码：</li>
					<li>固定电话：</li>
					<li>手机：</li>
				</ul>
			</div>
			<div class="addressright">
				<ul>
					<li>
						<input type="hidden" id="address_id" name="address.id" value=""/>
						<input id="address_addressName" type="text" name="address.addressName"  class="input"  value="" />
						&nbsp;请准确填写真实姓名，以确保商品准确无误的到达
					</li>
					<li>
						<select id="stateSelect" name="address.state" class="{required: true}">
						    <option value="">请选择</option>
						</select>
						<select id="citySelect" name="address.city" class="{required: true}">
						    <option value="">请选择</option>
						</select>
						<select id="countySelect" name="address.county" class="{required: true}">
						    <option value="">请选择</option>
						</select>		
					</li>
					<li>
						<input id="address_detail" type="text" name="address.address" class="input" style="width:280px" value="" />
					</li>
					<li>
						<input id="address_zipcode" type="text" name="address.zipcode"  class="input"  value="" />
					</li>
					<li>
						<input id="address_phone" type="text" name="address.phone"  class="input"  value="" />
						*&nbsp;请填写区号
					</li>
					<li>
						<input id="address_mobile" type="text" name="address.mobile"  class="input"  value="" />
						&nbsp;手机和固定电话至少有一项必填
					</li>
				</ul>
			</div>
			<div id="addressSave" class="buttom_area">
				<input class="formButton" type="button" id="addressSaveBtn" value="确  定"/>
				<input class="formButton" type="button" id="cancelSaveBtn" value="取消" />
			</div>
			<input type="text" id="useAddressId" name="useAddressId" value="${useAddressId}" style="display:none"></input>
		</div>
		
		<div class="ordertitle"><span class="titletext">请选择&nbsp;<strong>配送方式</strong></span></div>
		<div class="order_content">
			<#list shipModeList as list>
				<input type="radio" id="ship_mode" name="order.shipModeId" <#if order.shipModeId==list.id>checked</#if> value="${list.id}" class="style01" />
				<span class="style02">${list.name}</span>	
			</#list>
			
		</div>
		
	</div>
	<div class="buttonArea">
		<input type="button" class="formButton" onclick="window.history.back(); return false;" value="返  回" hidefocus="true" />
		<input type="submit" class="formButton" value="确  定" hidefocus="true" />
	</div>
</body>
</html>