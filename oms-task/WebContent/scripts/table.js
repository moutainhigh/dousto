$(document).ready(function() {
	//表格的删除按钮事件绑定   
	$("#tbody6 .del").click(function() {
		$(this).parents("tr:first").remove();
	});
	//
	$('form').submit(function() {
		  alert($(this).serialize());
		  return false;
	});
});
/**
 * add row
 * @param tableID
 * @returns {Boolean}
 */
var i=1;
function addRow(trid) {
	//取得模板行
	var tr = $("#"+trid);
	var table = tr.parents("table:first");
	//连同事件一起复制 
	var newRow = tr.clone();
	//去除模板标记   
	newRow.removeClass("template");
	//修改内部元素   
	newRow.find(".content").text("新增行").end();
	newRow.find(".del").attr("disabled",false).end();
	newRow.attr("id","tr03"+(i++)); 
	tr.after(newRow);
	return false;
}
/**
 * 
 * @param tableID
 */
function deleteRow(trID) {
	try {
		var tr = $("#"+trid);
		var table = tr.parents("table:first");
		var newRow = tr.clone();
		newRow.attr("id","tr02"); 
		
		
	} catch (e) {
		alert(e);
	}
}