function addToRole(){
    var resourceTbody = $(window.parent.document).find("#resourceTbody");
    
    $("input:checked[name=ids]").each(function(index){
        var addId = $(this).val();
        var flag = false;
		$(this).attr("name","resourceIds");
        $(resourceTbody).find("input:checked").each(function(i){
            if (this.value == addId) {
                flag = true;
            }
        });
        if (flag == false) {
            var tr = $(this).parents()[1];
            var newTr = $(tr).clone();
            $($(newTr).children()[4]).remove();
            $(newTr).appendTo($(resourceTbody));
        }
        
    });
}
