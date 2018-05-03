function redirect(obj) {
	location.href = obj.attr("url") + "?id=" + $("#operateId").val();
}

function sendPublicationAjax(obj) {
	var operateId = $('#operateId').val();
	$.ajax({
		url: obj.attr('url') + '?id=' + operateId,
		dataType: 'json',
		async: false,
		beforeSend: function() {
			obj.attr('disabled', true);
		},
		success: function(data) {
			obj.attr("disabled", false);
			var message = data.message;
			var row = $('.listTable tr[operateId=' + operateId + ']');
			if (data.status == 'success') {
				row.attr('isPublished', data.isPublished);
				
				var titleCell = row.find('td[name="title"]');
				if (data.url) {
					message += '<div><a href=\"' + data.url + '\" target=\"blank\"><span class="published">[浏览]</span></a></div>';
					titleCell.html('<a href=\"' + data.url + '\" target=\"blank\"><span class="published">' + titleCell.text() + '</span></a>');
				} else {
					titleCell.html(titleCell.text());
				}
				
				row.find('td[name="status"]').text(data.articleStatus);
				
				var publishDateCell = row.find('td[name="publishDate"]');
				if (data.publishDate) {
					publishDateCell.text(data.publishDate);
				} else {
					publishDateCell.text('');
				}
			}
			updateStatusByRow(row);
			$.message(data.status, message);
		}
	});
}

function updateStatusByRow(row) {
	var isPublished = row.attr('isPublished');
	var publishButton = $('#publishButton');
	var cancelPublishButton = $('#cancelPublishButton');
	
	publishButton.removeAttr('disabled');
		
	if (isPublished == 'true') {
		publishButton.val('重新发布');
		cancelPublishButton.removeAttr('disabled');
	} else {
		publishButton.val('发布');
		cancelPublishButton.attr('disabled', 'disabled');
	}
}

$(document).ready(function() {
	var editButton = $('#editButton');
	var publishButton = $('#publishButton');
	var cancelPublishButton = $('#cancelPublishButton');
	var operateId = $("#operateId");
	
	editButton.click(function() {
		redirect($(this));
	});
	
	publishButton.click(function() {
		sendPublicationAjax($(this));
	});
	
	cancelPublishButton.click(function() {
		sendPublicationAjax($(this));
	});
	
	$(".listTable td").click(function() {
		var row = $(this).parent();
		var rowId = row.attr('operateId');
		
		operateId.val(rowId);
		
		editButton.removeAttr('disabled');
		
		updateStatusByRow(row);
		
		row.siblings(".highlightD").removeClass("highlightD");
		row.addClass("highlightD");
	});
});
