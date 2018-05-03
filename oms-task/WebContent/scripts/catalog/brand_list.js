var echoSelRow = function() {
	var id = $("#gridTable").jqGrid("getGridParam", "selrow");
	alert("current selected ID：" + id);
};

var getData = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	var rowData = $("#gridTable").jqGrid("getRowData", selectedId);
	alert("First Name: " + rowData.firstName);
};

var updateData = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	var dataRow = {
		lastName : "Li",
		firstName : "Si",
		email : "li_si@126.com"
	};
	var cssprop = {
		color : "#FF0000"
	};
	$("#gridTable").jqGrid('setRowData', selectedId, dataRow, cssprop);
};

var deleteData = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	$("#gridTable").jqGrid('delRowData', selectedId);
};
var changeGridOptions = function() {
	$("#gridTable").jqGrid("setGridParam", {
		rowNum : 50,
		page : 16
	}).trigger('reloadGrid');

	$("#gridTable").jqGrid("setCaption", "Data List").trigger('reloadGrid');

	alert($("#gridTable").jqGrid("getGridParam", "caption"));
	alert($("#gridTable").jqGrid("getGridParam", "rowNum"));
};
var resetWidth = function() {
	$("#gridTable").jqGrid("setGridWidth", 300, false);
};
var addData = function() {
	var selectedId = $("#gridTable").jqGrid("getGridParam", "selrow");
	var dataRow = {
		id : 99,
		name : "Zhang",
		url : "San",
		logo : "zhang_san@126.com",
		displayOrder : "99"
	};

	if (selectedId) {
		$("#gridTable").jqGrid("addRowData", 99, dataRow, "before", selectedId);
	} else {
		$("#gridTable").jqGrid("addRowData", 99, dataRow, "first");
	}
};