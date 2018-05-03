/*******************************************************************************
 * IBM Register JavaScript Copyright (c) 2008 IBM. All rights reserved.
 */

$().ready( function() {
	// 商品上架按钮，打开商品上架的dialog
	$("#onMarketButton").click( function() {
 		if (!isIdsChoiced()){
 			alert("请选择需要上架的商品!");	
 			return;
 		}
 		
 		//var param = $(".list input[name='ids']:checked").serialize();
 		var param="";
		var extParam=$("#listForm input:[name='isProductRel']").serialize();
		//param=param+"&"+extParam
		param = extParam;
		 $("#marketDialog").load("batch_operation!market.action", param, function(){
			 var marketForm = $("#marketForm");
			/* marketForm.validate({
				   errorLabelContainer: "#marketDialog .errorMsgBox",
				   wrapper: "p"

			 });*/
				// 商品上架dialog中的提交按钮
				$("#marketDialogSubmit").click(function(){
					if($("#startDate").val()=="" && $("#endDate").val()==""){
						$("#marketDialog .errorMsgBox").html("请输入上架开始时间<br/>请输入上架结束时间");
						return;
					}else if($("#endDate").val()==""){
						$("#marketDialog .errorMsgBox").text("请输入上架结束时间");
						return;
					}else if($("#startDate").val()==""){
						$("#marketDialog .errorMsgBox").text("请输入上架开始时间");
						return;
					}
					var $check_clone = $(".list input[name='ids']:checked").clone();
					var $hidden ="";
					$check_clone.each(function (){
						$hidden =$hidden+"<input type='hidden' name='"+$(this).attr("name")+"' value='"+$(this).attr("value")+"'/>";
						});
					$("#categoryId").attr("value");
					var $category_id="";
					if($("#category_Id").attr("value")!=""){
						$category_id="<input type='hidden' name='categoryId' value='"+$("#category_Id").attr("value")+"'/>";
					}
				
					marketForm.append($category_id);
					//var $hidden=$check_clone.attr("type","hidden");
					marketForm.append($hidden);
				/*	var canSubmit = marketForm.validate().form();
					if (canSubmit) {*/
						closeDialog("marketDialog");
							submitForm(marketForm,"batch_operation!batchOnMarket.action");
					/*}else{
						
					}*/
					 //debug_showForm(marketForm);
					
				});
				
				// 商品上架dialog中的取消按钮
				$("#marketDialogCancel").click(function(){
					closeDialog("marketDialog");
				});
				
			    Calendar.setup({
			        trigger    : "startDateButton",
			        inputField : "startDate",
			        onSelect   : function() { this.hide(); }
			    });
				
			    Calendar.setup({
			        trigger    : "endDateButton",
			        inputField : "endDate",
			        onSelect   : function() { this.hide(); }
			    });

		 });		
 		
		openDialog("marketDialog","商品上架",460,350);
	});


	
	// 商品下架按钮，显示是否确定，然后提交
	$("#downMarketButton").click( function() {
		var listForm= $("#listForm");
		if (!isIdsChoiced()) alert("请选择需要下架的商品!");	
		if (confirm("您确定要将这些商品下架吗?")){
			var $category_id="";
			if($("#category_Id").attr("value")!=""){
				$category_id="<input type='hidden' name='categoryId' value='"+$("#category_Id").attr("value")+"'/>";
			}
		
			listForm.append($category_id);
			submitForm(listForm, "batch_operation!batchDownMarket.action");
		}
	});

	// 商品置顶按钮，显示是否确定，然后提交
	$("#batchOnTopButton").click( function() {
		var listForm= $("#listForm");
		if (!isIdsChoiced()) alert("请选择需要置顶的商品!");	
		if (confirm("您确定要将这些商品置顶吗?")){
			var $category_id="";
			if($("#category_Id").attr("value")!=""){
				$category_id="<input type='hidden' name='categoryId' value='"+$("#category_Id").attr("value")+"'/>";
			}
		
			listForm.append($category_id);
			submitForm(listForm, "batch_operation!batchOnTop.action");
		}
	});

	// 商品取消置顶按钮，显示是否确定，然后提交
	$("#batchOffTopButton").click( function() {
		var listForm= $("#listForm");
		if (!isIdsChoiced()) alert("请选择需要取消置顶的商品!");	
		if (confirm("您确定要将这些商品取消置顶吗?")){
			var $category_id="";
			if($("#category_Id").attr("value")!=""){
				$category_id="<input type='hidden' name='categoryId' value='"+$("#category_Id").attr("value")+"'/>";
			}
		
			listForm.append($category_id);
			submitForm(listForm, "batch_operation!batchOffTop.action");
		}
	});

	
	// 商品调价按钮，打开商品调价窗口
	$("#changePriceButton").click( function() {
		if (!isIdsChoiced()){
			alert("请选择需要调价的商品!");
			return;
		}
		var param = $(".list input[name='ids']:checked").serialize();
		var extParam=$("#listForm input:[name='isProductRel']").serialize();
		param=param+"&"+extParam
		 $("#priceDialog").load("batch_operation!prices.action", param, function(){
			 var priceForm = $("#priceForm");
			 priceForm.validate({
				   errorLabelContainer: "#priceForm .errorMsgBox",
				   wrapper: "p"

			 });		 
			 
				// 商品调价提交按钮
				$("#priceSubmitButton").click( function() {
					var $category_id="";
					if($("#category_Id").attr("value")!=""){
						$category_id="<input type='hidden' name='categoryId' value='"+$("#category_Id").attr("value")+"'/>";
					}
				
					priceForm.append($category_id);
					if (priceForm.validate().form()){
						closeDialog("priceDialog");
						submitForm($("#priceForm"), "batch_operation!modifyPrices.action");
					}					
				});
				// 商品调价取消按钮
				$("#priceCancelButton").click( function() {
					closeDialog("priceDialog");
				});			 
		 });		 
		openDialog("priceDialog","商品调价",940,300);
	});
	
	// 商品库存调整按钮，打开商品库存调整窗口
	$("#changeStoreButton").click( function() {
		if (!isIdsChoiced()){
			alert("请选择需要调整库存的商品!");
			return;
		}
			
		var param = $(".list input[name='ids']:checked").serialize();
		var extParam=$("#listForm input:[name='isProductRel']").serialize();
		param=param+"&"+extParam
		 $("#storeDialog").load("batch_operation!stores.action", param, function(){
			 var storeForm = $("#storeForm");
			 storeForm.validate({
				   errorLabelContainer: "#storeForm .errorMsgBox",
				   wrapper: "li"

			 });	  
			 
				// 商品库存调整提交按钮
				$("#storeSubmitButton").click( function() {
					if ($("#storeForm").validate().form()){
						closeDialog("storeDialog");
						submitForm($("#storeForm"), "batch_operation!modifyStores.action");
					}				

				});
				// 商品库存调整取消按钮
				$("#storeCancelButton").click( function() {
					closeDialog("storeDialog");
				});			 
		 });		 
		openDialog("storeDialog","库存调整",600,300);
	});
	
	
	// 商品目录调整按钮，打开商品目录调整窗口
	$("#changeCategoryButton").click( function() {
		if (!isIdsChoiced()){
			alert("请选择需要修改目录的商品!");
			return;
		}
		var param = $(".list input[name='ids']:checked").serialize();
		var extParam=$("#listForm input:[name='isProductRel']").serialize();
		param=param+"&"+extParam;
		 $("#categoryDialog").load("batch_operation!categories.action", param, function(){
			  
			 var categoryForm = $("#categoryForm");
			 categoryForm.validate({
				   errorLabelContainer: "#categoryForm .errorMsgBox",
				   wrapper: "p"

			 });			 

				// 商品目录调整提交按钮
				$("#categorySubmitButton").click( function() {
					var $category_id="";
					if($("#category_Id").attr("value")!=""){
						$category_id="<input type='hidden' name='categoryId' value='"+$("#category_Id").attr("value")+"'/>";
					}
				
					categoryForm.append($category_id);
					 if (categoryForm.validate().form()){
						 submitForm($("#categoryForm"), "batch_operation!modifyCategories.action");
						 closeDialog("categoryDialog");						 
//						 debug_showForm(categoryForm);
					 }					
					
				});
				// 商品目录调整取消按钮
				$("#categoryCancelButton").click( function() {
					closeDialog("categoryDialog");
				});			 
		 });		 
		openDialog("categoryDialog","修改目录",900,600);
	});
	
	// 商品“需要编辑”状态调整按钮，打开需要编辑的商品窗口
	$("#clearEditFlagButton").click( function() {
		if (!isIdsChoiced()){
			alert("请选择需要修改目录的商品!");
			return;
		}
		var param = $(".list input[name='ids']:checked").serialize();
		var extParam=$("#listForm input:[name='isProductRel']").serialize();
		param=param+"&"+extParam;
		 $("#clearEditFlagDialog").load("batch_operation!needEdit.action", param, function(){
			  
			 var needEditForm = $("#needEditForm");
			 needEditForm.validate({
				   errorLabelContainer: "#needEditForm .errorMsgBox",
				   wrapper: "p"

			 });			 

				// 商品目录调整提交按钮
				$("#needEditSubmitButton").click( function() {
					 if (needEditForm.validate().form()){
						 submitForm($("#needEditForm"), "batch_operation!modifyNeedEdit.action");
						 closeDialog("clearEditFlagDialog");						 
//						 debug_showForm(categoryForm);
					 }					
					
				});
				// 商品目录调整取消按钮
				$("#needEditCancelButton").click( function() {
					closeDialog("clearEditFlagDialog");
				});			 
		 });		 
		openDialog("clearEditFlagDialog","清除编辑标记",600,300);
	});
});

function submitForm(form,url){
		$(form).attr("action",url);
		
		$(form).append($("#listForm input:[name='isProductRel']"));
		$(form).submit();
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

function isIdsChoiced(){
	var $idsChecked = $(".list input[name='ids']:checked");
	if ($idsChecked.size() <= 0) {		
		return false;
	}
	return true;
}

function openDialog(id,displayTitle,width,height){
	$("#"+id).dialog({
		autoOpen : true,
		width : width,
		height : height,
		modal : true,
		resizable : true,
		autoResize : true,
		close : function(event, ui) {
			$(this).dialog('destroy');
		},
		open : function(event, ui) {
			$(this).css('width', "100%").css('padding', "0")
		},
		title : displayTitle
	});
}

function closeDialog(id){
	$("#"+id).dialog("close");
}