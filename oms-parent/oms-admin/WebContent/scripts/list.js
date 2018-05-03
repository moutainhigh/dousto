$().ready( function() {
	var $allCheck = $(".allCheck");// 全选复选框
	var $idsCheck = $("input[name='ids']");// ID复选框
	var $deleteButton = $(".list input.deleteButton");// 删除按钮
	var $approveButton = $(".list input.approveButton");//PRD-87 评论审核通过按钮
	
	var $freezeButton = $(".freezeButton");// 冻结按钮
	var $mergeButton = $(".mergeButton");// 合并按钮
	
	var $listForm = $("#listForm");// 列表表单
	var $searchButton =  $("#searchButton");// 查询按钮
	var $pageNumber = $("#pageNumber");// 当前页码
	var $pageSize = $("#pageSize");// 每页显示数
	var $sort = $(".list .sort");// 排序
	var $orderBy = $("#orderBy");// 排序方式
	var $order = $("#orderType");// 排序字段
	
	// 全选
	$allCheck.click( function() {
		var $idsCheck = $("input[name='ids']");
 
		if (this.checked) {
			$idsCheck.attr("checked", true);
		    //$deleteButton.attr("disabled", false);
			//$approveButton.attr("disabled",false);//PRD-87 评论审核通过按钮
			//$(".batchButton").attr("disabled", false);
			$(".freezeButton").attr("disabled", false);
			$(".mergeButton").attr("disabled", false);
		} else {
			$idsCheck.attr("checked", false);
			//$deleteButton.attr("disabled", true);
			//$approveButton.attr("disabled",true);//PRD-87 评论审核通过按钮
			//$(".batchButton").attr("disabled", true);
			$(".freezeButton").attr("disabled", true);
			$(".mergeButton").attr("disabled", true);
		}
	});
	
	// 无复选框被选中时,删除按钮不可用
	$idsCheck.click( function() {
		var $idsChecked = $("input[name='ids']:checked");
		var $idsCheck = $("input[name='ids']");
		var $allCheck = $(".allCheck");// 全选复选框
		if ($idsChecked.length > 0) {
			//$deleteButton.attr("disabled", false);
			//$approveButton.attr("disabled",false);//PRD-87 评论审核通过按钮
			$(".freezeButton").attr("disabled", false);
			$(".mergeButton").attr("disabled", false);
		} else {
			//$deleteButton.attr("disabled", true);
			//$approveButton.attr("disabled",true);//PRD-87 评论审核通过按钮
			$(".freezeButton").attr("disabled", true);
			$(".mergeButton").attr("disabled", true);
		}
		if($idsChecked.length == $idsCheck.length){
			$allCheck.attr("checked", true);
		}
		if($idsChecked.length < $idsCheck.length){
			$allCheck.attr("checked", false);
		}
	});
	
	// 批量删除
	$deleteButton.click( function() {
		var url = $(this).attr("url");
		var $idsCheckedCheck = $(".list input[name='ids']:checked");
		if (confirm("您确定要删除吗？") == true) {
			$.ajax({
				url: url,
				data: $idsCheckedCheck.serialize(),
				dataType: "json",
				async: false,
				beforeSend: function(data) {
					$deleteButton.attr("disabled", true);
				},
				success: function(data) {
					$deleteButton.attr("disabled", false);
					if (data.status == "success") {
						$idsCheckedCheck.parent().parent().remove();
					}
					var message = "";
					if (data.message) {
						message = data.message;
					} else if (data.messages) {
						$(data.messages).each(function(index, element) {
							message += element + "<br/>";
						});
					}
					if(($.browser.msie && parseInt($.browser.version) <= 6)){
						alert(message);
					}else {
						$.message(data.status, message);
					}
				}
			});
		}
	});
    
	//批量冻结
	$freezeButton.click( function() {
		var url = $(this).attr("url");
		var $idsCheckedCheck = $("input[name='ids']:checked");
		if (confirm("您确定要冻结吗？") == true) {
			$.ajax({
				url: url,
				data: $idsCheckedCheck.serialize(),
				dataType: "json",
				beforeSend: function(data) {
					$freezeButton.attr("disabled", true);
					$(".mergeButton").attr("disabled", true);
				},
				success: function(data) {
					$freezeButton.attr("disabled", false);
					if (data.status == "success") {
						alert(data.message);
						$listForm.submit();
					}
				}
			});
		}
	});
	//合并会员
	$mergeButton.click( function() {
		var url = $(this).attr("url");
		var $idsCheckedCheck = $("input[name='ids']:checked");
        if($idsCheckedCheck.size() != 2){
        	alert("合并会员时，必须选中两条会员信息！");
        	return;
        }
		if (confirm("您确定要合并吗？") == true) {
			$.ajax({
				url: url,
				data: $idsCheckedCheck.serialize(),
				dataType: "json",
				beforeSend: function(data) {
					$mergeButton.attr("disabled", true);
					$(".freezeButton").attr("disabled", true);
				},
				success: function(data) {
					$mergeButton.attr("disabled", false);
					if (data.status == "success") {
						alert(data.message);
						$listForm.submit();
					}
				}
			});
		}
	});
	// 查找
	$searchButton.click( function() {
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 每页显示数
	$pageSize.change( function() {
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 排序
	$sort.click( function() {
		var $currentOrderBy = $(this).attr("name");
		if ($orderBy.val() == $currentOrderBy) {
			if ($order.val() == "") {
				$order.val("asc");
			} else if ($order.val() == "desc") {
				$order.val("asc");
			} else if ($order.val() == "asc") {
				$order.val("desc");
			}
		} else {
			$orderBy.val($currentOrderBy);
			$order.val("asc");
		}
		$pageNumber.val("1");
		$listForm.submit();
	});

	// 排序图标效果
	sortStyle();
	function sortStyle() {
		var orderByValue = $orderBy.val();
		var orderValue = $order.val();
		if (orderByValue != "" && orderValue != "") {
			$(".sort[name='" + orderByValue + "']").after('<span class="' + orderValue + 'Sort">&nbsp;</span>');
		}
	}
	
	// 页码跳转
	$.gotoPage = function(id) {
		$pageNumber.val(id);
		$listForm.submit();
	};
	
	$("#reset").click( function() {
		$('input:not([type="button"],[name="column"])').each(function(){
			$(this).val("");
		 });
		$("select").each(function(){
			$(this).get(0).selectedIndex=0;
		 });
		$('.multipleSelect').each(function() {
			$(this).multiselect("uncheckAll");
			$(this).children("option:selected").prop('selected', false);
			$(this).multiselect('refresh');
		});
	});
	
});