<select id="levelOneSelect" name="orderReport.categoryLevelOne" class="selectTree">
</select>
<select id="levelTwoSelect" name="orderReport.categoryLevelTwo"  class="selectTree">  
</select>
<select id="levelThreeSelect" name="orderReport.categoryLevelThree"  class="selectTree">
</select>    
<select id="levelFourSelect" name="orderReport.categoryLevelFour"  class="selectTree">
</select>                    
<script>

var categoryLevelOne = '${orderReport.categoryLevelOne}';
var categoryLevelTwo = '${orderReport.categoryLevelTwo}';
var categoryLevelThree = '${orderReport.categoryLevelThree}';
var categoryLevelFour = '${orderReport.categoryLevelFour}';
var initLevelTwo=true;
var initLevelThree=true;
var initLevelFour=true;
    $().ready(function(){
        $.getJSON('${base}/order/report/category_level_one.action', {
            selectId: categoryLevelOne
        }, levelOne);
        
        
        
        function levelOne(data){
            var list = data.list;
            var options = "<option value=''>一级分类</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#levelOneSelect").html(options);
            if(initLevelTwo){
				$.getJSON('${base}/order/report/category_level_two.action', {
                	upLevel: categoryLevelOne,
                	selectId: categoryLevelTwo
		        }, levelTwo);
				initLevelTwo=false;
			}
        }
        
        
        $("#levelOneSelect").change(function(){
            $.getJSON('${base}/order/report/category_level_two.action', {
                upLevel: this.value,
                selectId: categoryLevelTwo
            }, levelTwo);
        });
        
             
        
        function levelTwo(data){
            var list = data.list;
            var options = "<option value=''>二级分类</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#levelTwoSelect").html(options);
            if(list.length==0){
				$("#levelTwoSelect").attr("class","selectTree");
				$("#levelTwoSelect").hide();
		    }else{
		       	$("#levelTwoSelect").attr("class","selectTree");
		       	$("#levelTwoSelect").show();
		    }
		    
		    $("#levelThreeSelect").attr("class","selectTree");
			$("#levelThreeSelect").hide();
			
			$("#levelFourSelect").attr("class","selectTree");
			$("#levelFourSelect").hide();
			
			if(initLevelThree){
				$.getJSON('${base}/order/report/category_level_three.action', {
                upLevel: categoryLevelTwo,
                selectId: categoryLevelThree
		        }, levelThree);
				initLevelThree=false;
			}
        }
           
        
        $("#levelTwoSelect").change(function(){
            $.getJSON('${base}/order/report/category_level_three.action', {
                upLevel: this.value,
                selectId: categoryLevelThree
            }, levelThree);
        });
        
             
        function levelThree(data){
            var list = data.list;
            var options = "<option value=''>三级分类</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#levelThreeSelect").html(options);
            if(list.length==0){
				$("#levelThreeSelect").attr("class","selectTree");
				$("#levelThreeSelect").hide();
            }else{
            	$("#levelThreeSelect").attr("class","selectTree");
            	$("#levelThreeSelect").show();
            }
            $("#levelFourSelect").attr("class","selectTree");
			$("#levelFourSelect").hide();
			
			if(initLevelFour){
				$.getJSON('${base}/order/report/category_level_four.action', {
                upLevel: categoryLevelThree,
                selectId: categoryLevelFour
		        }, levelFour);
				initLevelFour=false;
			}
        }
        
        
        $("#levelThreeSelect").change(function(){
            $.getJSON('${base}/order/report/category_level_four.action', {
                upLevel: this.value,
                selectId: categoryLevelFour
            }, levelFour);
        });
        
        
        function levelFour(data){
            var list = data.list;
            var options = "<option value=''>四级分类</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#levelFourSelect").html(options);
            if(list.length==0){
				$("#levelFourSelect").attr("class","selectTree");
				$("#levelFourSelect").hide();
            }else{
            	$("#levelFourSelect").attr("class","selectTree");
            	$("#levelFourSelect").show();
            }
			
			if(initLevelFour){
				$.getJSON('${base}/order/report/category_level_four.action', {
                upLevel: categoryLevelThree,
                selectId: categoryLevelFour
		        }, levelFour);
				initLevelFour=false;
			}
        }
        
    });
</script>