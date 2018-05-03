function chnageColor(valueGroupId) {
	if (valueGroupId.length>0){
		var attrGroupId = $("input[name=attributeGroupId]").val();
		$("#colorEdit").attr("href",
				"color!edit.action?id=" + valueGroupId+"&redirectionUrl=attribute_group!edit.action?attributeGroupId="+attrGroupId);
	}else{
		$("#colorEdit").attr("href", "#");
		return false;
	}
	
	var url = "attribute_value!list.action";
	var result = "";
	$.ajax({
		url : url,
		data : "groupId=" + valueGroupId,
		dataType : "json",
		async : true,
		success : function(data) {
			var list = data.gridModel;
			if (list.length > 0) {
				for (var i = 0; i < list.length; i++) {
					if (result == "")
						result = list[i].value;
					else
						result += "," + list[i].value;
				}
				$("#colorOutput").text(result);
				
			} else {
				$("#colorOutput").text("");				
			}
		}
	});
}

function changeSize(valueGroupId) {
	
	if (valueGroupId.length>0){
		var attrGroupId = $("input[name=attributeGroupId]").val();
		$("#sizeEdit").attr("href",
				"size!edit.action?id=" + valueGroupId+"&redirectionUrl=attribute_group!edit.action?attributeGroupId="+attrGroupId);
	}else{
		$("#sizeEdit").attr("href", "#");
		return false;
	}
	
	var url = "attribute_value!list.action";
	var result = "";
	$.ajax({
		url : url,
		data : "groupId=" + valueGroupId,
		dataType : "json",
		async : true,
		success : function(data) {
			var list = data.gridModel;
			if (list.length > 0) {
				for (var i = 0; i < list.length; i++) {
					if (result == "")
						result = list[i].value;
					else
						result += "," + list[i].value;
				}
				$("#sizeOutput").text(result);
			} else {
				$("#sizeOutput").text("");
			}
		}
	});
}

function initEditRow(rowNo,delFlag) {
	var sx = $("#gridTable").jqGrid("getCell", rowNo, "act");
	if (sx == "") {
		if(delFlag==1){
		content = $("<a href='#' onclick=\"jQuery('#gridTable').jqGrid('delRowData','"
				+ rowNo + "');\">[删除]</a>");
		$("#" + rowNo + " td:last").append(content);
		}
	}
	var dis =$("#"+rowNo+"_displayType");
	if (dis.val()=="2"){
		$("#"+rowNo+"_optionType").val("");
		$("#"+rowNo+"_optionType").attr("readonly","true");
	}
	dis.change(function () { 		
		if ($(this).val()=="2"){
			$("#"+rowNo+"_optionType").val("");
			$("#"+rowNo+"_optionType").attr("readonly","true");
		}else{
			$("#"+rowNo+"_optionType").removeAttr("readonly");
		}
	});
}

/**
 * 添加行
 * 
 * @returns
 */
var totalCount = -1;
function addData() {
	if (totalCount == -1)
		totalCount = $("#gridTable").jqGrid('getGridParam', 'records');
	else
		totalCount = totalCount + 1;

	var datarow = {
		id : 0,
		name : "",
		dataType : 1,
		options : "",
		isDisplay : "true",
		act : ""
	};

	$("#gridTable").jqGrid("addRowData", totalCount, datarow);
	$("#gridTable").jqGrid("editRow", totalCount, true);
	initEditRow(totalCount);
};

function submitForm() {

	var canSubmit = $("#inputForm").validate().form();
	if (canSubmit) {
		var gridInput = $("#gridTable input[name='name']");
		if (gridInput.length <= 0) {
			alert("请在“属性”标签页新增至少一个属性后再进行保存!");
			canSubmit = false;
			return false;

		}
		if (canSubmit == false)
			return;
		$("#gridTable input[name='name']").each(function(i, elm) {
			if ($(elm).val() == "") {
				alert("第" + (i + 1) + "行验证失败。请输入商品规格名!");
				elm.focus();
				canSubmit = false;
				return;
			}

		});
		if (canSubmit == false)
			return;
		$("#gridTable input[name='options']")
				.each(
						function(i, elm) {
							if ($(elm).val() == "") {
								if ($("#" + i + "_displayType").val() == 1) {
									alert("第"
											+ (i+1)
											+ "行验证失败。前台列表为下拉框，您必须在规格选项定制列进行下拉框的定义。选项定制的格式为[选项1,选项2,选项3]。各选项之间以','相隔。");
									elm.focus();
									canSubmit = false;
									return;
								}
							}
						});
		if (canSubmit == false)
			return;
	} else {
		alert("数据验证出现错误，不能提交。请检查您的输入项。");
		return;
	}
	
	// $("#inputForm input").each(function(i) {
	// result += $(this).attr("name") + "=" + $(this).attr("value") + " ";
	// });
	//
	// $("select").each(function(i) {
	// result += $(this).attr("name") + "=" + $(this).attr("value") + " ";
	// });
	//
	// $("<p>" + result + "</p>").appendTo($("#inputForm"));
	// alert($("#inputForm").attr("action"));
	if (canSubmit == true){
		
//		debug_showForm($("#inputForm"));
		//这段程序是用于更新的。从gridTable里找到规格的id值。然后将这些id拼装到ids里面，提交到服务器。
		var ids = $("#gridTable").jqGrid("getDataIDs");
		var result = "";

		for ( var i = 0; i < ids.length; i++) {
			var tempId = $("#gridTable").jqGrid("getCell", ids[i], "id");

			$("<input>", {
				"name" : "ids",
				val : tempId,
				type : "hidden"
			}).appendTo($("#inputForm"));
		}
//		debug_showForm($("#inputForm"));
		$("#inputForm").get(0).submit();
	}

}

function debug_showForm(form){

	var debugDialog= $("#debugDialog");
	
	var result="";
	$(form).find("input").each(function(i) {
		result += $(this).attr("name") + "=" + $(this).attr("value") + " ";
		if (i%5==0) result += "<br>";
	});

	$("<p>" + result + "</p>").appendTo(debugDialog);
	debugDialog.dialog({
		autoOpen : true,
		width : 600,
		height : 400,
		modal : true,
		resizable : true,
		autoResize : true,
		close : function(event, ui) {
			$(this).dialog('destroy');
			$("#debugDialog").empty();
		},
		open : function(event, ui) {
			$(this).css('width', "100%").css('padding', "0")
		},
		title : "Debug窗口"
	});
	
}


