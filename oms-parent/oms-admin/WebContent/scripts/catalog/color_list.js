$.metadata.setType("attr", "validate");
    
$.subscribe('showEditCell', function(event, data) {

	var ids = $("#gridTable").jqGrid("getDataIDs");

	for ( var i = 0; i < ids.length; i++) {
		$("#gridTable").jqGrid("editRow", ids[i], true);
		initEditRow(ids[i]);
	}
});

jQuery(document).ready(function() {
	// 装载tab 1
	var options_tab_uploadTab = {};
	options_tab_uploadTab.id = "uploadTab";
	options_tab_uploadTab.href = "#uploadTabDiv";
	options_tab_uploadTab.label = "上传图片";
	var tabs = jQuery('#localtabs').data('taboptions');
	if (!tabs) {
		tabs = [];
	}
	tabs.push(options_tab_uploadTab);
	jQuery('#localtabs').data('taboptions', tabs);

	// 装载tab 2
	var options_tab_imageTab = {};
	options_tab_imageTab.id = "imageTab";
	options_tab_imageTab.href = "#imageTabDiv";
	options_tab_imageTab.label = "图库选择";
	var tabs = jQuery('#localtabs').data('taboptions');
	if (!tabs) {
		tabs = [];
	}
	tabs.push(options_tab_imageTab);
	jQuery('#localtabs').data('taboptions', tabs);

	// 装载tab nav
	var options_localtabs = {};
	options_localtabs.jqueryaction = "tabbedpanel";
	options_localtabs.id = "localtabs";
	jQuery.struts2_jquery.bind(jQuery('#localtabs'), options_localtabs);

	// Initialize jQuery File Upload (Extended User Interface Version):
	$('#file_upload').fileUploadUIX();

	loadImageLib(4);
	
	$("#inputForm").validate();	

	$("#inputForm input").keypress(function(e) {

		if (e.keyCode==13){
			submitForm();
		}
	});

});

function loadImageLib(colNo) {
	var imageLib = $("#image_lib_table");
	var trS = "<tr class='new'>";
	for (x = 0; x < colNo; x++) {
		trS += "<td padding='5px'><span class='boder_" + x + "'></span></td>";
	}
	trS += "</tr>";

	$
			.getJSON(
					"/emallmgr/catalog/attribute_value!findImages.action",
					function(data) {

						$
								.each(
										data.images,
										function(i, item) {
											// alert(item);
											var trTemplate = $(trS);
											var cellNo = i % colNo;
											if (cellNo == 0) {
												imageLib.find(".new")
														.removeClass("new");
												imageLib.append(trTemplate);
											}
											var imageRootPath=$("#resourceRootPath").val();

											var image = $("<img width='25px' height='25px' >");
											image.attr("src", imageRootPath+item);
											//image.attr("src", item);
											image
													.click(function() {
														$(
																"#image_lib_table span")
																.attr("style",
																		"");
														$(this)
																.parent()
																.attr(
																		"style",
																		"border: 3px solid rgb(255, 102, 0); padding: 1px; background: none; display: block; margin: 3px; overflow: hidden; text-align: center; visibility: visible; opacity: 1;");
														$("#lib_image_callback")
																.attr(
																		"call_back_value",
																		item);
													});
											imageLib.find(".new").find(
													".boder_" + cellNo).append(
													image);

										});
					});
}

function imageCallback(callBackButton) {
	var imageRootPath=$("#resourceRootPath").val();
	var call_back_div = $("#file_upload").attr("call_back_div");
	var value = $(callBackButton).attr("call_back_value");
	$("#" + call_back_div).find("img").attr("src", imageRootPath+value);
	$("#" + call_back_div).find(".image_value").val(value);
	// alert($("#file_upload").attr("call_back_div"));
	// alert($("#file_upload").attr("call_back_value"));
	$("#file_upload").dialog("close");
}

function openUplodImageWindow(divId) {
	$("#file_upload").dialog({
		autoOpen : true,
		width : 600,
		height : 400,
		modal : true,
		resizable : true,
		autoResize : true,
		close : function(event, ui) {
			$(this).dialog('destroy');
		},
		open : function(event, ui) {
			$(this).css('width', "100%").css('padding', "0")
		},
		title : "文件上传"
	});

	$("#file_upload").attr("call_back_div", divId);
}

function initEditRow(rowNo) {

	var sx = $("#gridTable").jqGrid("getCell", rowNo, "act");
	if (sx == "") {
		content = $("<a href='#' onclick=\"jQuery('#gridTable').jqGrid('delRowData','"
				+ rowNo + "');\">[删除]</a>");
		$("#" + rowNo + " td:last").append(content);
	}

	var rowData = $("#gridTable").jqGrid("getRowData", rowNo);
	var defaultImage = $("#default_image").val();
	var imageRootPath=$("#resourceRootPath").val();
	if (rowData.image == "") {
		content = $("<div id='image_div_"
				+ rowNo
				+ "'><image src='"+defaultImage+"' height='15' width='15'/>&nbsp;&nbsp;&nbsp;"
				+ "<input type='button' value='请选择图片' onclick='openUplodImageWindow(\"image_div_"
				+ rowNo
				+ "\")' />"
				+ "<input id='val_"
				+ rowNo
				+ "' class='image_value' type=hidden name=image value='' /> </div>");
		$("#" + rowNo + " td:eq(3)").empty().append(content);
	} else if (!($(rowData.image).is("div"))) {
		var srcString = rowData.image;
		content = $("<div id='image_div_"
				+ rowNo
				+ "'><image src='"
				+ imageRootPath+srcString
				+ "' height='15' width='15'/>&nbsp;&nbsp;&nbsp;"
				+ "<input type='button' value='请选择图片' onclick='openUplodImageWindow(\"image_div_"
				+ rowNo + "\")' /><input id='val_" + rowNo
				+ "' class='image_value' type=hidden name=image value='"
				+ srcString + "' /></div>");
		$("#" + rowNo + " td:eq(3)").empty().append(content);
	}
}

/**
 * 添加行
 * 
 * @returns
 */
var totalCount = -1;
function addData() {
	if (totalCount == -1)
		totalCount = $("#gridTable").jqGrid('getGridParam', 'records')+1;
	else
		totalCount = totalCount + 1;

	var datarow = {
		id : 0,
		value : "",
		code: "",
		image : "",
		act : ""
	};

	$("#gridTable").jqGrid("addRowData", totalCount, datarow);
	$("#gridTable").jqGrid("editRow", totalCount, true);
	//initEditRow(totalCount);
};

function submitForm() {
	var canSubmit = $("#inputForm").validate().form();
	if (canSubmit) {
		if($("#gridTable input[name='value']").length > 0) {
			$("#gridTable input[name='value']").each(function(i, elm) {
				if ($(elm).val() == "") {
					alert("颜色列表第" + (i + 1) + "行请输入颜色名");
					elm.focus();
					canSubmit = false;
					return false;
				}
			});
			
			$('#gridTable input[name="code"]').each(function(i, element) {
				if ($(element).val() == "") {
					alert("颜色列表第" + (i + 1) + "行请输入编码");
					element.focus();
					canSubmit = false;
					return false;
				}
			});			
		}else if($("#gridTable input[name='value']").length == 0){
			alert("请添加颜色值！");
			return false;
		}
	}

	if (canSubmit) {
		var ids = $("#gridTable").jqGrid("getDataIDs");
		for ( var i = 0; i < ids.length; i++) {
			var tempId = $("#gridTable").jqGrid("getCell", ids[i], "id");

			$("<input>", {
				"name" : "ids",
				val : tempId,
				type : "hidden"
			}).appendTo($("#inputForm"));
		}
		$("#inputForm").get(0).submit();
	}

	// $("#inputForm input").each(function(i) {
	// result += $(this).attr("name") + "=" + $(this).attr("value") + " ";
	// if (i%4==0) result += "<br>";
	// });

	// $("select").each(function(i) {
	// result += $(this).attr("name") + "=" + $(this).attr("value") + " ";
	// });

	// $("<p>" + result + "</p>").appendTo($("#inputForm"));
	// alert($("#inputForm").attr("action"));
	// $("#inputForm").get(0).submit();

}

function deletePicture(){
	var id = $("#id").val();
	if (confirm("您确定要删除吗？") == true) {
		$.ajax({
			url: base + "/catalog/color!deletePicture.action",
			data: {"id":id},
			dataType: "json",
			async: false,
			beforeSend: function(data) {
				//$deleteButton.attr("disabled", true);
			},
			success: function(data) {
				if (data.status == "success") {
					$("#deletePicture_aid").remove();
					$("#imagePreview_aid").remove();
					//$idsCheckedCheck.parent().parent().remove();
				}
				alert(data.message);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("删除失败！");
			}
		});
	}
}