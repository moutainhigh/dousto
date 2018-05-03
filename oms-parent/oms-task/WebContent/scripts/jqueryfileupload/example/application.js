/*
 * jQuery File Upload Plugin JS Example 5.0
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://creativecommons.org/licenses/MIT/
 */

/*jslint nomen: false */
/*global $ */

$(function () {

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
    	maxNumberOfFiles: _maxNumberOfFiles,
    	acceptFileTypes:  /\.(png)|(jpg)|(gif)$/i,
    	onCompleteAll:function(i){alert(i)}
    });

//    // Load existing files:
//    $.getJSON($('#fileupload form').prop('action'), function (files) {
//        var fu = $('#fileupload').data('fileupload');
//        fu._adjustMaxNumberOfFiles(-files.length);
//        fu._renderDownload(files)
//            .appendTo($('#fileupload .files'))
//            .fadeIn(function () {
//                // Fix for IE7 and lower:
//                $(this).show();
//            });
//    });

    // Open download dialogs via iframes,
    // to prevent aborting current uploads:
    $('#fileupload .files a:not([rel^=gallery])').live('click', function (e) {
        e.preventDefault();
        $('<iframe style="display:none;"></iframe>')
            .prop('src', this.href)
            .appendTo('body');
    });

});

function getFileList(){
	var list = Array();
	$(".template-download").each(function(i){
		var file = new Object;
		file.url = $(this).children(".file_name").children("a").attr("href");
		file.name = $(this).children(".file_name").children("a").text();
		file.size = $(this).children(".file_size").text();
		list.push(file);
	})
	return list
}

function onClose(){
	parent.imageCallback(getFileList());
	window.close();
	
}