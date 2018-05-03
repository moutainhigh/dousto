$.metadata.setType("attr", "validate");

$.subscribe('showEditCell', function(event, data) {

	var ids = $("#gridTable").jqGrid("getDataIDs");

	for ( var i = 0; i < ids.length; i++) {
		$("#gridTable").jqGrid("editRow", ids[i], true);
		initEditRow(ids[i]);
	}
});

$().ready(function() {
	$("#inputForm").validate();	
	$("#inputForm input").keypress(function(e) {
		if (e.keyCode==13){
			submitForm();
		}
	});
});	

function initEditRow(rowNo) {
	var sx = $("#gridTable").jqGrid("getCell", rowNo, "act");
	if (sx == "") {
		content = $("<a href='#' onclick=\"jQuery('#gridTable').jqGrid('delRowData','"
				+ rowNo + "');\">[删除]</a>");
		$("#" + rowNo + " td:last").append(content);
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
		totalCount = $("#gridTable").jqGrid('getGridParam', 'records');
	else
		totalCount = totalCount + 1;

	var datarow = {
		id : 0,
		value : "",
		code: "",
		act : ""
	};

	$("#gridTable").jqGrid("addRowData", totalCount, datarow);
	$("#gridTable").jqGrid("editRow", totalCount, true);
	initEditRow(totalCount);
};

function submitForm() {
	var canSubmit = $("#inputForm").validate().form();
	if (canSubmit) {
		if($("#gridTable input[name='value']").length > 0){
			$("#gridTable input[name='value']").each(function(i, elm) {
				if ($(elm).val() == "") {
					alert("第" + (i + 1) + "行验证失败，请输入尺码值!");
					elm.focus();
					canSubmit = false;
					return false;
				}
			});
			$('#gridTable input[name="code"]').each(function(i, element) {
				if ($(element).val() == "") {
					alert("第" + (i + 1) + "行验证失败，请输入编码!");
					element.focus();
					canSubmit = false;
					return false;
				}
			});			
		}else if($("#gridTable input[name='value']").length == 0) {
			alert("请添加尺码值！");
			//canSubmit = false;
			return false;
		}

	}

	if (canSubmit){
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
		

}
