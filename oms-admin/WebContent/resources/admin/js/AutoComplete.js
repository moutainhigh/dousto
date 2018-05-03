var highlightindex = -1;
var timeoutid;
$(document).ready(function() {
	var wordInput = $("#word");
    var wordInputOffset = wordInput.offset();
    $("#auto").hide().css("border","1px black solid").css("background-color","white").css("position","relative").css("top",-30 + "px").width(wordInput.width() + 3);   
    wordInput.keyup(function(event) {
        var myEvent = event || window.event;
        var keyCode = myEvent.keyCode;
        if (keyCode >= 65 && keyCode <= 90 || keyCode == 8 || keyCode == 46 || keyCode == 32 || keyCode >= 48 && keyCode <= 57 || keyCode >= 97 && keyCode <= 105) {
        	var wordText = encodeURI($("#word").val().replace(/^\s+/, "").replace(/\s+/g, " ")).toLowerCase();           
            var autoNode = $("#auto");
            if (wordText != "") {
            	clearTimeout(timeoutid);
                timeoutid = setTimeout(function(){
                	$.ajax({type:"get",url:url,data:{word:wordText},dataType:"json",error:function(){
                		alert("无法打开拼音辅助输入数据库，请检查PYServer.xml设置，并确保没有其它进程在使用该数据库");
                		},success:function(data){
                			autoNode.html("");
                    		var backword = new Array();
                    		backword = data.backword.split(";");
                    		var len = backword.length;
                    		len = len > 11 ? 10 : len - 1;
                    		for(var i = 0; i < len; i++){
                    			var eachword = backword[i];
                    			var thekey = eachword.substring(0, eachword.indexOf("\t"));
                    			var newDivNode = $("<div>").attr("id",i);
                    			newDivNode.html(thekey).appendTo(autoNode);               			
                    			newDivNode.mouseover(function(){
                                	if(highlightindex != -1){
                                		$("#auto").children("div").eq(highlightindex).css("background-color","white");
                                	}
                                	highlightindex = $(this).attr("id");
                                	$(this).css("background-color","#C4C4C4");
                                });
                                newDivNode.mouseout(function(){
                                	$(this).css("background-color","white");
                                });
                                newDivNode.click(function(){
                                    var comText = $(this).text();
                                    $("#auto").hide();
                                    highlightindex = -1;
                                    $("#word").val(comText);
                                });
                    		}
                    		if (len > 0) {
                                autoNode.show();
                            } else {
                                autoNode.hide();
                                highlightindex = -1;
                            }
                    }});
                },100);
            } else {
                autoNode.hide();
                highlightindex = -1;
            }
        } else if (keyCode == 38 || keyCode == 40) {
            if (keyCode == 38) {
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {
                    autoNodes.eq(highlightindex).css("background-color","white");
                    highlightindex--;
                } else {
                    highlightindex = autoNodes.length - 1;    
                }
                if (highlightindex == -1) {
                    highlightindex = autoNodes.length - 1;
                }
                autoNodes.eq(highlightindex).css("background-color","#C4C4C4");
            }
            if (keyCode == 40) {
                var autoNodes = $("#auto").children("div")
                if (highlightindex != -1) {
                    autoNodes.eq(highlightindex).css("background-color","white");
                }
                highlightindex++;
                if (highlightindex == autoNodes.length) {
                    highlightindex = 0;
                }
                autoNodes.eq(highlightindex).css("background-color","#C4C4C4");
            }
        } else if (keyCode == 13) {
            if (highlightindex != -1) {
                var comText = $("#auto").hide().children("div").eq(highlightindex).text();
                highlightindex = -1;
                $("#word").val(comText);
            } else {
            	  //EnterPress();
            	  //alter("no!");
                $("#auto").hide();
                $("#word").get(0).blur();
            }
        }
    });
})