<select id="stateSelect" name="distributeAddress.state" class="selectTree">
</select>
<select id="citySelect" name="distributeAddress.city"  class="selectTree">  
</select>
<select id="countySelect" name="distributeAddress.county"  class="selectTree">
</select>
<select id="streetSelect" name="distributeAddress.street"  class="selectTree">
</select>
            
            
            
<script>

var addressState = '${distributeAddress.state}';
var addressCity = '${distributeAddress.city}';
var addressCounty = '${distributeAddress.county}';
var addressStreet = '${distributeAddress.street}';
var initCity=true;
var initCounty=true;
var initStreet=true;
    $().ready(function(){
        $.getJSON('${base}/order/area/state_json.action', {
            selectName: addressState
        }, stateJson);
        function stateJson(data){
            var list = data.list;
            var options = "<option value=''>省/直辖市</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#stateSelect").html(options);
            if(initCity){
				$.getJSON('${base}/order/area/city_json.action', {
		            stateAbbr: addressState,
		            selectName: addressCity
		        }, cityJson);
				initCity=false;
			}
        }
        $("#stateSelect").change(function(){
        	$("#countySelect").val("");
        	$("#streetSelect").val("");
        	addressCounty = "";
        	addressStreet = "";
        	$("#countySelect").hide();
        	$("#streetSelect").hide();
        
            $.getJSON('${base}/order/area/city_json.action', {
                stateAbbr: this.value,
                selectName: ''
            }, cityJson);
        });
        
        function cityJson(data){
            var list = data.list;
            var options = "<option value=''>城市</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#citySelect").html(options);
            if(list.length==0){
				$("#citySelect").attr("class","selectTree");
				$("#citySelect").hide();
		    }else{
		       	$("#citySelect").attr("class","selectTree");
		       	$("#citySelect").show();
		    }
		    
		    $("#countySelect").attr("class","selectTree");
			$("#countySelect").hide();
			if(initCounty){
				$.getJSON('${base}/order/area/county_json.action', {
		            cityId: addressCity,
		            selectName: addressCounty
		        }, countyJson);
				initCounty=false;
			}
        }
        $("#citySelect").change(function(){
        	$("#streetSelect").val("");
        	addressStreet = "";
        	$("#streetSelect").hide();
        	
            $.getJSON('${base}/order/area/county_json.action', {
            	cityId: this.value,
                selectName: ''
            }, countyJson);
        });
        function countyJson(data){
            var list = data.list;
            var options = "<option value=''>县区</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#countySelect").html(options);
            if(list.length==0){
				$("#countySelect").attr("class","selectTree");
				$("#countySelect").hide();
            }else{
            	$("#countySelect").attr("class","selectTree");
            	$("#countySelect").show();
            }
            
            
            $("#streetSelect").attr("class","selectTree");
			$("#streetSelect").hide();
			if(initStreet){
				$.getJSON('${base}/order/area/street_json.action', {
		            countyId: addressCounty,
		            selectName: addressStreet
		        }, streetJson);
				initStreet=false;
			}
        }
        $("#countySelect").change(function(){
            $.getJSON('${base}/order/area/street_json.action', {
            	countyId: this.value,
                selectName: ''
            }, streetJson);
        });
        function streetJson(data){
            var list = data.list;
            var options = "<option value=''>街道</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#streetSelect").html(options);
            if(list.length==0){
				$("#streetSelect").attr("class","selectTree");
				$("#streetSelect").hide();
            }else{
            	$("#streetSelect").attr("class","selectTree");
            	$("#streetSelect").show();
            }
        }
    });
</script>