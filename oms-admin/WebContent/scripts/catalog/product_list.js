$.metadata.setType("attr", "validate");
/*
搜索条件有两类，一类是comparealbe的，一类则是只能严格equal的。
对于只能严格equal的则不允许显示其他逻辑操作符，只显示"="
还有部分条件是code-value对应类型的，需要做单独处理。
故此部分放置于action中，然后再在ftl中动态组成出来。
*/
var seacherType = "onlyEqual";
var booleanHtmStr = "<option value='true'>是	</option><option value='false'>否</option>";
var attributeGroupHtmStr = "";

$(document).ready(function(){
	initOption(base+"/catalog/attribute_group!jsonList.action",function(data){
		attributeGroupHtmStr=data;
		refrashSeacher();
	});
});

function searcherChange(){
	keywordStr = "";
	refrashSeacher();
	var selectedValue = $("#codeValue").val();
	$("#keyword").val(selectedValue);
}

function refrashSeacher(){
	var _this = $("#proertySel option:selected")[0];
	var seacherType = _this.attributes['seacherType'].value;
	var valueType = _this.attributes['valueType'].value;
	var optionSrc = "";
	if (_this.attributes['optionSrc']!=""&&_this.attributes['optionSrc']!=undefined){
		optionSrc = _this.attributes['optionSrc'].value;
	}
	
//	初始搜索条件设置。
	$("#keyword").val(keywordStr);
	$("#keyword").show();
	$("#keywordLable").show();
	$("#codeValue").hide();
//	初始逻辑操作符设置。
	$("#symbol").removeAttr("disabled");
//	部分情况只能选择严格相等	
	if(seacherType=="onlyEqual"){
		$("#symbol").attr("selectedIndex","0");	
		$("#symbol option:nth-child(1)").attr("selected","selected");
		$("#symbol option:nth-child(2)").hide();
		$("#symbol option:nth-child(3)").hide();
		$("#symbol option:nth-child(4)").hide();
	}else{
		$("#symbol option:nth-child(2)").show();
		$("#symbol option:nth-child(3)").show();
		$("#symbol option:nth-child(4)").show();
	}	
//	字符类型可以显示like，其他情况下不可。
	if(valueType=="text")
		$("#symbol option:nth-child(4)").show();
	else
		$("#symbol option:nth-child(4)").hide();
//	日期类型需要转换成日期选择器，其他情况下还原成默认方式。
	if(valueType=="date")
		$( "#keyword" ).datepicker({ dateFormat: 'yy-mm-dd' });
	else
		$("#keyword").datepicker("destroy");
//	布尔型的则显示“是/否”选择框，并将条件输入框隐藏
	if(valueType=="select"||valueType=="boolean"){
		$("#keyword").hide();
		$("#keywordLable").hide();
		$("#codeValue").show();
		if(valueType=="boolean")
			$("#codeValue").html(booleanHtmStr);
		if(optionSrc=="attributeGroupHtmStr")			
			$("#codeValue").html(attributeGroupHtmStr);
		$("#codeValue option[value='"+keywordStr+"']").attr("selected","true");			
	}		


}

function initOption(optionSrc,callback){
		var rtn = "";
		$.getJSON(optionSrc, function(data) {
			for(var i in data.codeValueList){
				rtn=rtn+'<option value="'+data.codeValueList[i].code+'">'+data.codeValueList[i].value+'</option>';
			}
			callback(rtn);
		});
		
}

function codeValueOnChange(value){
	$("#keyword").val(value);
}