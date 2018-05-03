//------------addOrderPayRow begin--------------------------------    
index = 1; 
function addOrderPayRow(){
		
		var targetTab = document.getElementById("retInfoTab");
	    var newRow = targetTab.rows.length;
	    index=  newRow-1;
	  
		var row = targetTab.insertRow(newRow);  
		
		var cell0 = row.insertCell(0); 
		//cell0.innerHTML=" <select name='orderPayLists[" + index + "].payCode'><#list payTypeList as type><option value='${type.id}'>${type.desc}</option></#list></select> ";
		cell0.innerHTML="" +
				"<input type='hidden' name='orderPayFlag'/> " +
				"<select name='orderPayLists[" + index + "].payCode' >" +
						"<#list refundMethodList as modelist>" +
							"<#if modelist.id != '50300' && modelist.id != '307' && modelist.id != '1' && modelist.id != '830' && modelist.id != '301' && modelist.id != '50600'>" +
								"<option value='${modelist.id}'>" +
									"<#if modelist.id == ''>" +
										"请选择" +
									"<#else>" +
										"${modelist.name}" +
									"</#if>" +
								"</option>" +
							"</#if>" +
						"</#list>" +
				"</select>";
		
		var cell1 = row.insertCell(1);  
		cell1.innerHTML="  <input type='text' name='orderPayLists[" + index + "].payAmount' id='payAmount_" + index + "'  onblur='javascript:changePayAmount(this)' class='formText {required: true, min: 0}' value='0' /> ";
		
	
		var cell2 = row.insertCell(2); 
		cell2.innerHTML="<input type='button' class='formButton' value='删除' hidefocus='true'   onclick='javascript:deletePayRow(this,null, null)'/>";
		
		index++;
}
//------------addOrderPayRow end--------------------------------