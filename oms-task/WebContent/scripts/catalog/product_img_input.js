	var bigTips = "&nbsp;&nbsp;请上传875*1000 jpg或png格式的图片";
	var colorTips = "&nbsp;&nbsp;请上传18*18 jpg或png格式的图片";
	var singleTips = "&nbsp;&nbsp;请上传420*480 jpg或png格式的图片";
	var listTips = "&nbsp;&nbsp;请上传175*200 jpg或png格式的图片";
	var currentColor = '';
	var currentColorIcon = '';
	var currentUploadType = '';
	var currentRowId = '';
	var isFristLoad=true;
	var preOrderId="";
	var currOrderId="";
	var preOrderValue=0;
	$.subscribe('gridInit', function() {
		if (isFristLoad) {
			$(".jqgrow").each(function(){
				$(".jqgfirstrow").show();
				var cl = $(this).attr("id")
				var curCommodityId = $(this).children("td[aria-describedby='gridTable_commodityId']").attr("title");
				var isEdit = $("#" + cl).attr("editable") == 1
				var mainImg = $("#" + cl).children("td[aria-describedby='gridTable_fullImage']").first().attr("title")
				var detalImg = $("#" + cl).children("td[aria-describedby='gridTable_detailImage']").first().attr("title")
				var listImg = $("#" + cl).children("td[aria-describedby='gridTable_listingImage']").first().attr("title")
				var desc = $("#" + cl).children("td[aria-describedby='gridTable_description']").first().attr("title")
				var isPrimaryImage = $("#" + cl).children("td[aria-describedby='gridTable_isPrimaryImage']").first().html()
				var isAssociateImage = $("#" + cl).children("td[aria-describedby='gridTable_isAssociateImage']").first().html()
				var detalImgShow = "";
				var listImgShow = "";
				var isPrimaryImageChecked = "";
				var isAssociateImageChecked = "";
				var isListImgTableShow = "";
				if (isPrimaryImage == "true") {
					isPrimaryImageChecked = "checked";
				}else{
					isListImgTableShow = "display:none"
				}
				if (isAssociateImage=="true") isAssociateImageChecked ="checked";
				if (detalImg=="") detalImgShow = "style='display:none'";
				if (listImg=="") listImgShow = "style='display:none'";
				jQuery('#gridTable').jqGrid('editRow',cl, true);
				$("#" + cl).children("td[aria-describedby='gridTable_mainImage']").each(function(){
					$(this).html("<img id='mainImg" + cl + "' src='"+imgBase + mainImg + "' onClick=showPreview('"+
					mainImg+"') onload= chgsize('mainImg" + cl + "',32,48) />")
				});
				$("#" + cl).children("td[aria-describedby='gridTable_singlePic']").each(function(){
					$(this).html("<table style='border-style:none' ><td  style='border-style:none' width=50><img src='"+imgBase+detalImg+
					 "' id='singleImg" + cl +"' onload= chgsize('singleImg" + cl + "',32,48) "+detalImgShow+"  /></td>" +
					"<td  style='border-style:none'><input type='button' class='formButton' onclick='uploadSinglePic(" +
					cl +
					")' value='上传' /><p>" +
					"<input type='button' class='formButton' value='清空' onclick='cleanSinglePic(" +
					cl +
					")' /></td></table>")
				});
				$("#" + cl).children("td[aria-describedby='gridTable_listPic']").each(function(){
					$(this).html("<table style='border-style:none;"+isListImgTableShow+"'  id='listImgTable"+cl+"' ><td style='border-style:none' width=50><img src='" +imgBase+ listImg +
					"' id='listImg" + cl +"' onload= chgsize('listImg" + cl + "',32,48) "+listImgShow+" /></td>" +
					"<td  style='border-style:none'><input type='button' class='formButton' onclick='uploadListPic(" +
					cl +
					")' value='上传' /><p>" +
					"<input type='button' class='formButton' value='清空' onclick='cleanListPic(" +
					cl +
					")' /></td></table>")
				});
				$("#" + cl).children("td[aria-describedby='gridTable_isPrimaryImage1']").each(function(){
					$(this).html("<input type='radio' name='isPrimaryImgrd"+curCommodityId+"' value='"+cl+
					"' id='isPrimaryImgChk" + cl + "' onclick='changeIsPrimaryImage(" + cl + ")' "+ isPrimaryImageChecked +" />")
				});
				$("#" + cl).children("td[aria-describedby='gridTable_isAssociateImage1']").each(function(){
					$(this).html("<input type='radio' name='isAssociateImgrd"+curCommodityId+"' value='"+cl+
					"' onclick='changeIsAssociateImage(" + cl + ")' "+ isAssociateImageChecked +" />")
				});
				$("#" + cl).children("td[aria-describedby='gridTable_description']").each(function(){
					$(this).html("<table style='border-style:none;"+isListImgTableShow+"'  id='descImgTable"+cl+"' ><td style='border-style:none' width=50>"+
							"<img id='cartImg" + cl + "' src='"+imgBase + desc + "' onClick=showPreview('"+
							mainImg+"') onload= chgsize('cartImg" + cl + "',35,40) /><input type='hidden' value='"+desc+"' name='description'></td>" +
							"</table>");
				});
				$("#" + cl).children("td[aria-describedby='gridTable_delete']").each(function(i){
					$(this).html("<input type='button' class='formButton' value='删除' onclick=\"jQuery('#gridTable').jqGrid('delRowData','" + $(this).parent().attr("id") + "')\" />")
				});
				$("#" + cl).children("td[aria-describedby='gridTable_displayOrder']").each(function(i){
					$(this).children("input").attr("style","width: 40%;");
					if(isPrimaryImageChecked=="checked"){
						currOrderId=cl;
						$(this).html("<input type='hidden' id='"+cl+"_displayOrder' name='displayOrder' value='0'>");
					}
				});
			})
			filterRow();
		}
		isFristLoad = false;
    });
	
	$.subscribe('tabchange', function(event,data) {
        var tab = event.originalEvent.ui.index;
		currentCommodityId = event.originalEvent.ui.panel.id.substr(3);
		$("#previewImg").hide();
		filterRow();
    });
	
	function filterRow(){
		$(".jqgrow").each(function(i){
			//注意edit状态中的列的原始值会保存在title属性中，故需要这样写
			
				if ($(this).children("td[aria-describedby='gridTable_commodityId']").attr("title")==currentCommodityId){
					$(this).show();
				}else{
					$(this).hide();
				}
		})
	}
    
    function uploadMainPic(commodityId,colorIcon){
		currentUploadType = 'mainPic';
		currentColorIcon = colorIcon;
		currentCommodityId = commodityId;
		openUplodImageWindow(10,bigTips);
	}
	
	function uploadSinglePic(cl){
		currentUploadType = 'singlePic';
		currentRowId = cl;
		openUplodImageWindow(1,singleTips);
	}
	
	function uploadListPic(cl){
		currentUploadType = 'listPic';
		currentRowId = cl;		
		openUplodImageWindow(1,listTips);
	}
	
	function uploadColorPic(){
		currentUploadType = 'colorPic';
		openUplodImageWindow(1,colorTips);
	}
	
	function cleanSinglePic(cl){
		$("#singleImg"+cl).attr("src","");
		$("#"+cl+"_DetailImage").attr("value","");
		$("#"+cl+"_isOverrideDetail").attr("value","false");
		$("#singleImg"+cl).hide();
	}
	
	function cleanListPic(cl){
		$("#listImg"+cl).attr("src","");
		$("#"+cl+"_ListingImage").attr("value","");
		$("#"+cl+"_isOverrideListing").attr("value","false");
		$("#listImg"+cl).hide();
	}
	
	function openUplodImageWindow(maxNumber,tips) {
		$("#file_upload_ifr").attr("src",base+"/html/fileupload.jsp?maxNumber="+maxNumber)
		$("#upload_tips").html(tips);
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
				$(this).css('width', "100%").css('padding', "0")
			},
			title : "文件上传"
		});
	}

	function imageCallback(fileList) {
		$("#file_upload").dialog('destroy');
		if (currentUploadType=='mainPic'){
			for(i in fileList){
				addRow(fileList[i].size,fileList[i].url,i);
			}
		}
		if (currentUploadType=='singlePic'){
			for(i in fileList){
				$("#singleImg"+currentRowId).attr("src",imgBase+fileList[i].url);
				$("#"+currentRowId+"_DetailImage").attr("value",fileList[i].url);
				$("#"+currentRowId+"_isOverrideDetail").attr("value","true");
				$("#singleImg"+currentRowId).show();
				chgsize('singleImg'+currentRowId,48,32);
			}
			
		}
		if (currentUploadType=='listPic'){
			for(i in fileList){
				$("#listImg"+currentRowId).attr("src",imgBase+fileList[i].url);
				$("#"+currentRowId+"_ListingImage").attr("value",fileList[i].url);
				$("#"+currentRowId+"_isOverrideListing").attr("value","true");
				$("#listImg"+currentRowId).show();
				chgsize('listImg'+currentRowId,48,32);
			}			
		}
		
		if (currentUploadType=='colorPic'){
			for(i in fileList){
				$("#colorImg"+currentCommodityId).attr("src",imgBase+fileList[i].url);
				$("#colorImgInput"+currentCommodityId).attr("value",currentCommodityId+"`"+fileList[i].url);
				chgsize('colorImg'+currentCommodityId,48,48);
			}	
		}
	}
    
	function addRow(_size,_img,i){
		isFristLoad = false;
		var cl = "-"+(new Date()).getTime()+i; 
		var datarow = {
				id:cl,
				colorIcon : currentColorIcon,
				commodityId : currentCommodityId,
				fullImage:_img,
				thumbnail:"",
				isPrimaryImage:"false",
				isAssociateImage:"false",
				singlePic:"",
				detailImage:"",
				isOverrideDetail : "",
				listPic : "",
				listingImage: "",
				isOverrideListing:""
		};
		var su = jQuery("#gridTable").jqGrid('addRowData',cl, datarow);
		jQuery('#gridTable').jqGrid('editRow',cl, true);
		$("#"+cl).children("td[aria-describedby='gridTable_mainImage']").each(function(){
			$(this).html("<img id='mainImg"+cl+"' src='"+imgBase+ _img +"'onClick=showPreview('"+
					_img+"') onload= chgsize('mainImg"+cl+"',32,48) />")
		});
		$("#"+cl).children("td[aria-describedby='gridTable_singlePic']").each(function(){
			$(this).html("<table style='border-style:none' ><td  style='border-style:none' width=50><img src='' id='singleImg"+cl+"' style='display:none' /></td>"+
						  "<td  style='border-style:none'><input type='button' class='formButton' onclick='uploadSinglePic("+cl+")' value='上传' /><p>"+
						  "<input type='button' class='formButton' value='清空' onclick='cleanSinglePic("+cl+")' /></td></table>")
		});
		$("#"+cl).children("td[aria-describedby='gridTable_listPic']").each(function(){
			$(this).html("<table id='listImgTable"+cl+"' style='border-style:none;display:none'><td style='border-style:none' width=50><img src='' id='listImg"+cl+"' style='display:none'/></td>"+
						  "<td  style='border-style:none'><input type='button' class='formButton' onclick='uploadListPic("+cl+")' value='上传' /><p>"+
						  "<input type='button' class='formButton' value='清空' onclick='cleanListPic("+cl+")' /></td></table>")
		});
		$("#"+cl).children("td[aria-describedby='gridTable_isPrimaryImage1']").each(function(){
			$(this).html("<input type='radio'  name='isPrimaryImgrd"+currentCommodityId+"' value='"+cl+
			" id='isPrimaryImgChk" + cl + "' onclick='changeIsPrimaryImage("+cl+")'/>")
		});
		$("#"+cl).children("td[aria-describedby='gridTable_isAssociateImage1']").each(function(){
			$(this).html("<input type='radio' name='isAssociateImgrd"+currentCommodityId+"' value='"+cl+
			" onclick='changeIsAssociateImage("+cl+")'/>")
		});
		$("#"+cl).children("td[aria-describedby='gridTable_description']").each(function(){
			$(this).html("<table style='border-style:none;display:none'  id='descImgTable"+cl+"' ><td style='border-style:none' width=50>"+
					"<input type='hidden' value='' name='description'></td>" +
					"</table>");
		});
		$("#"+cl).children("td[aria-describedby='gridTable_delete']").each(function(i){
			$(this).html("<input type='button' class='formButton' value='删除' onclick=\"jQuery('#gridTable').jqGrid('delRowData','"+$(this).parent().attr("id") + "')\" />")
		});
		$("#" + cl).children("td[aria-describedby='gridTable_displayOrder']").each(function(i){
			$(this).children("input").attr("style","width: 40%;");
			$(this).children("input").val("0");
		});
	}
	
	function chgsize(id, width, height){
		myImage = new Image()
		var img = document.getElementById(id);
		if ((img.width / img.height) > (width / height)) {
			img.width = width;
		}
		else{
			img.height = height
		}
	} 
	
//	以下这两个方法都为响应click事件，原方法实现方式有误。
	function changeIsPrimaryImage(cl){
		if ($("#" + cl + "_isPrimaryImage").val() == "false") {
			$("#" + cl + "_isPrimaryImage").val("true");
			/*
			var tempOrderValue=$("#" + cl + "_displayOrder").val();
			if(typeof(preOrderValue)=="undefined"){
				preOrderValue=0;
			}
			if(preOrderId==""){
				alert($("#displayOrder").parent().html());
				$("#displayOrder").parent().html("<input type='text' id='displayOrder' name='displayOrder' style='width: 40%;' role='textbox' class='editable' value='0'>");
				alert($("#displayOrder").parent().html());
			}else{
				$("#" + preOrderId + "_displayOrder").parent().html('<input type="text" id="'+preOrderId+'_displayOrder" name="displayOrder" style="width: 40%;" role="textbox" class="editable" value="'+preOrderValue+'">');
			}
			if($("#" + cl + "_displayOrder").parent().html()==null){
				preOrderId="";
				preOrderValue=0;
				$("#cl_displayOrder").parent().html("<input type='hidden' id='displayOrder' name='displayOrder' value='0'>");
			}else{	
				$("#" + cl + "_displayOrder").parent().html("<input type='hidden' id='"+cl+"_displayOrder' name='displayOrder' value='0'>");
				preOrderValue=tempOrderValue;
				preOrderId=cl;
			}
			*/
			preOrderId=currOrderId;
			preOrderValue=$("#" + cl + "_displayOrder").val();
			currOrderId=cl;
			changePrimaryImage(cl);
		}	
	}
	
	function changeIsAssociateImage(cl){
		if($("#"+cl+"_isAssociateImage").val()=="false")
			$("#"+cl+"_isAssociateImage").val("true");	
			changeAssociateImage(cl);
	}
/////////////////////////////////////////	
	function changePrimaryImage(_cl){
		$(".jqgrow").each(function(){
			if ($(this).children("td[aria-describedby='gridTable_commodityId']").attr("title") == currentCommodityId) {
				var cl = $(this).attr("id");
				if(typeof(preOrderValue)=="undefined"){
					preOrderValue=0;
				}
				if (cl!=_cl){
					if(preOrderId==cl){
						$("#"+preOrderId+"_displayOrder").parent().html("<input type='text' id='"+cl+"_displayOrder' name='displayOrder' style='width: 40%;' role='textbox' class='editable' value='"+preOrderValue+"'>");
					}
					$("#listImgTable"+cl).hide();
					$("#descImgTable"+cl).hide();
					$("#" + cl + "_isPrimaryImage").val("false");
				}else{
					$("#" + cl + "_displayOrder").parent().html("<input type='hidden' id='"+cl+"_displayOrder' name='displayOrder' value='0'>");
					$("#listImgTable"+cl).show();
					$("#descImgTable"+cl).show();
				}
			}
		})
	}
	
	function changeAssociateImage(_cl){
		$(".jqgrow").each(function(){
			if ($(this).children("td[aria-describedby='gridTable_commodityId']").attr("title") == currentCommodityId) {
				var cl = $(this).attr("id");
				if (cl!=_cl){
					$("#" + cl + "_isAssociateImage").val("false");
				}
			}
		})
	}
	
	function showPreview(src){
		$("#previewImg").attr("src",imgBase+src);
		$("#previewImg").show();
		chgsize('previewImg',320,480);
	}
