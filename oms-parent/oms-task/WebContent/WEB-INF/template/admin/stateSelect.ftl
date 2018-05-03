<script>
    $().ready(function(){
        var addressState = '${(address.state)!}';
        var addressCity = '${(address.city)!}';
        var addressCounty = '${(address.county)!}';
        $.getJSON('${base}/json/state_json.action', {
            selectName: addressState
        }, stateJson);
        $.getJSON('${base}/json/city_json.action', {
            stateAbbr: addressState,
            selectName: addressCity
        }, cityJson);
        $.getJSON('${base}/json/county_json.action', {
            cityCode: addressCity,
            selectName: addressCounty
        }, countyJson);
        function stateJson(data){
            var list = data.list;
            var options = "<option value=''>请选择</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].id + "'>" + list[i].name + "</option>";
                }
            }
            $("#stateSelect").html(options);
        }
        $("#stateSelect").change(function(){
            $.getJSON('${base}/json/city_json.action', {
                stateAbbr: this.value,
                selectName: addressCity
            }, cityJson);
        });
        function cityJson(data){
            var list = data.list;
            var options = "<option value=''>请选择</option>";
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
				$("#citySelect").attr("class","{required: false}");
				$("#citySelect").hide();
            }else{
            	$("#citySelect").attr("class","{required: true}");
            	$("#citySelect").show();
            }
        }
        $("#citySelect").change(function(){
            $.getJSON('${base}/json/county_json.action', {
                cityCode: this.value,
                selectName: addressCounty
            }, countyJson);
        });
        function countyJson(data){
            var list = data.list;
            var options = "<option value=''>请选择</option>";
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
				$("#countySelect").attr("class","{required: false}");
				$("#countySelect").hide();
            }else{
            	$("#countySelect").attr("class","{required: true}");
            	$("#countySelect").show();
            }
            
        }
    });
</script>
<select id="stateSelect" name="address.state" class="{required: true}">
    <option value="">请选择</option>
</select>
<select id="citySelect" name="address.city" class="{required: true}">
    <option value="">请选择</option>
</select>
<select id="countySelect" name="address.county" class="{required: true}">
    <option value="">请选择</option>
</select>