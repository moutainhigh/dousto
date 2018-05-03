<select id="stateSelect" name="distributeAddress.state" class="selectTree" onchange="selectedProvice(this.value);">
</select>
<select id="citySelect" name="distributeAddress.city"  class="selectTree" onchange="selectedCity(this.value);">  
</select>
<select id="countySelect" name="distributeAddress.county"  class="selectTree">
</select>
            
<script type="text/javascript">

var addressState = '${distributeAddress.state}';
var addressCity = '${distributeAddress.city}';
var addressCounty = '${distributeAddress.county}';
    $().ready(function(){
    	$.ajax({
			type:"GET",
			url:"${base}/order/area/area!findProvinces.action",
			dataType : "json",
			success:function(data){
				var select = $("#stateSelect");
				select.empty();
				select[0].options.add(new Option("请选择", ""));
				$(data).each(function(index, result){
					select[0].options.add(new Option(result.areaName, result.areaCode));
				});
				
				if(addressState){
					$("#stateSelect").val(addressState);
					loadSelect("citySelect",addressState,addressCity);
					if(addressCity){
						loadSelect("countySelect",addressCity,addressCounty);
					} 
				}
			}
		});	
    });
    
	function selectedProvice(province) {
		$("#countySelect").empty();
		loadSelect("citySelect", province);
	}
	function selectedCity(city) {
		loadSelect("countySelect", city);
	}
	function loadSelect(selectId, parentId,selectedCode) {
		$.ajax({
			type:"GET",
			url:"${base}/order/area/area!findByParentId.action",
			data: {parentId : parentId},
			dataType : "json",
			success:function(data){
				var select = $("#"+selectId);
				select.empty();
				select[0].options.add(new Option("请选择", ""));
				$(data).each(function(index, result){
					select[0].options.add(new Option(result.areaName, result.areaCode));
				});
				if(selectedCode){
					$("#"+selectId).val(selectedCode);
				}
			}
		});	
	}
</script>