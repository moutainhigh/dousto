    function uploadImage(){
		openUplodImageWindow(10);
	}
	
	function openUplodImageWindow(maxNumber) {
		$("#file_upload_ifr").attr("src",base+"/html/fileupload.jsp?maxNumber="+maxNumber+"&method=uploadImage");
		$("#upload_tips").html("");
		$("#file_upload").dialog({
			autoOpen : true,
			width : 640,
			height : 480,
			modal : true,
			resizable : true,
			autoResize : true,
			close : function(event, ui) {
				$(this).dialog('destroy');
			},
			open : function(event, ui) {
				$(this).css('width', "100%").css('padding', "0");
			},
			title : "文件上传"
		});
	}

	function imageCallback(fileList) {
		$("#file_upload").dialog('destroy');
		for(i in fileList){
			addRow(fileList[i].size,fileList[i].url,i);
		}
	}
    
	function addRow(_size,_img,i){
		isFristLoad = false;
		var cl = "-"+(new Date()).getTime()+i; 
		var datarow = {
				fullImages:_img
		};
		var su = jQuery("#gridTable").jqGrid('addRowData',cl, datarow);
		jQuery('#gridTable').jqGrid('editRow',cl, true);
		$("#"+cl).children("td[aria-describedby='gridTable_fullImages']").each(function(){
			$(this).html("<img id='fullImages"+cl+"' src='"+imgBase+ _img +"'onClick=showPreview('"+
					_img+"') onload= chgsize('fullImages"+cl+"',32,48) /><input type='hidden' name='fullImages' value='"+_img+"'/>");
		});
		$("#"+cl).children("td[aria-describedby='gridTable_delete']").each(function(i){
			$(this).html("<input type='button' class='formButton' value='删除' onclick=\"jQuery('#gridTable').jqGrid('delRowData','"+$(this).parent().attr("id") + "')\" />")
		});
	}
	
	function chgsize(id, width, height){
		myImage = new Image();
		var img = document.getElementById(id);
		if ((img.width / img.height) > (width / height)) {
			img.width = width;
		}
		else{
			img.height = height;
		}
	} 
	
	
	function showPreview(src){
		$("#previewImg").attr("src",imgBase+src);
		$("#previewImg").show();
		chgsize('previewImg',320,480);
	}
