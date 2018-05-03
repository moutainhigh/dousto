var table = "#attributesTable";
var addButton = "#addAttribute";
var rowTemplate = "<tr rowNo='#rowNum'><td><input type='text' name='attributes[#i].name' maxLength='30' size='20' value='attributes[#i].name'></td>"
		+ "<td><select name='attributes[#i].dataType' ><option value='1' >下拉框<option value='2' >输入框</select></td>"
		+ "<td><input type='text' name='attributes[#i].attributeType' size='80'></td>"
		+ "<td><input type='checkbox' name='attributes[#i].display' checked></td>"
		+ "<td><input type='checkbox' name='attributes[#i].sort' checked></td>"
		+ "<td><a href='#' onClick='deleteRow(this);'>[删除]</a></td>" + "</tr>";


var createRow = (function() {
	//有个title行，所以需要减一
	var max_line_num= $("#attributesTable tr").length-1;

	var row=rowTemplate.replace('#rowNum', max_line_num);
	var reg=new RegExp("#i","g");
	row=row.replace(reg, max_line_num);
	
	$(table).append(row);
});

function deleteRow(element) {
	var cur_tr = $(element).parents("tr");
	alert("this "+cur_tr.get(0).getAttribute("rowNo"));

}
function removeRow() {

}

$().ready(function() {
	$(addButton).click(createRow);

});