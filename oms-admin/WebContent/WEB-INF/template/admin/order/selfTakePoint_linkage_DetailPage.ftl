<#if isDetail || (orderCategory == 'ref') || (orderCategory == 'tsf')>
	<select id="merchantSelect"   disabled  class="selectTree">
	</select>
	<select id="takePointSelect"   disabled   class="selectTree">  
	</select>
<#elseif isModify && (order.statusConfirm = '0807')>
	<input type="hidden" name="selfTakePoint.pointDeliverPartnerId" value="${(selfTakePoint.pointDeliverPartnerId)!}"/>
	<input type="hidden" name="selfTakePoint.id" value="${(selfTakePoint.id)!}"/>
	<select id="merchantSelect"   disabled  class="selectTree">
	</select>
	<select id="takePointSelect"   disabled   class="selectTree">  
	</select>
<#else>
	<select id="merchantSelect"  name="selfTakePoint.pointDeliverPartnerId"   class="selectTree">
	</select>
	<select id="takePointSelect"  name="selfTakePoint.id"    class="selectTree">  
	</select>
</#if>


            
<script>
var selectedCode = '${selfTakePoint.pointDeliverPartnerId}';
var id = '${selfTakePoint.id}';
var initTakePoint=true;
var initCounty=true;
    $().ready(function(){
		//alert(selectedCode);
        $.getJSON('${base}/order/area/self_take_merchant_json.action', {
            code: selectedCode
        }, merchantJson);
        function merchantJson(data){
            var list = data.selfTakeMerchantTmpList;
            var options = "<option value=''>全部商户</option>";
            for (var i = 0; i < list.length; i++) {
            	if("自提柜（速递易）" == list[i].name){
            		continue;
            	}
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].code + "' selected='selected'>" + list[i].name + "</option>";
                }
                else {
                    options = options + "<option value='" + list[i].code + "'>" + list[i].name + "</option>";
                }
            }
            $("#merchantSelect").html(options);
            if(initTakePoint){
				$.getJSON('${base}/order/area/self_take_point_json.action', {
		            merchantCode: selectedCode,
		            selfTakePointId: id
		        }, takePointJson);
				initTakePoint=false;
			}
        }
        $("#merchantSelect").change(function(){
        	//$("#takePointSelect").val("");
            $.getJSON('${base}/order/area/self_take_point_json.action', {
                merchantCode: this.value,
                selfTakePointId: ''
            }, takePointJson);
        });
        
        function takePointJson(data){
            var list = data.selfTakePointTmpList;
            var options = "";
            var j=0;
            var options = "<option value=''>请选择</option>";
            for (var i = 0; i < list.length; i++) {
                if (list[i].checked == true) {
                    options = options + "<option value='" + list[i].id + "' selected='selected'>" + list[i].pointName + "</option>";

				    //$("#address").html(list[i].detailAddress);
						
                }
                else {
               		j++;
                    options = options + "<option value='" + list[i].id + "'>" + list[i].pointName + "</option>";
                    if(j==1){
                   	 //$("#address").html(list[i].detailAddress);
                    }
                }
            }
            $("#takePointSelect").html(options);
            if(list.length==0){
				$("#takePointSelect").attr("class","selectTree");
				$("#takePointSelect").hide();
				//$("#address").html("");
				$("#address").val("");
		    }else{
		       	$("#takePointSelect").attr("class","selectTree");
		       	$("#takePointSelect").show();
		    }
		    
        }
        
          $("#takePointSelect").change(function(){
          
            var params = {
			    ids:this.value
			};
            $.ajax({
				type:"POST",
				url:"${base}/order/area/self_take_point_json!address.action",
				data:params,
				dataType : "json",
				success:function(data){
					if(data.status == "success"){
					     //$("#address").html(data.message);
					     $("#address").val(data.message);
					}else{
					  
					}
				},
				error : function(data){
					
				}
			});	
            
        });
        

        
        
    });
</script>