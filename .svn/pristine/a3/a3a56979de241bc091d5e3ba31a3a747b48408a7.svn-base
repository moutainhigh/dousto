package com.ibm.oms.dao.constant;

/**
 * Description: //模块目的、功能描述  
 * @author Yusl
 * Date:   2018年3月16日 
 */
public enum LogisticsMessage {
	/**
	 * 
	 */
	logisticsMessage_State_OnPassage("0","在途中"),
	/**
	 * 
	 */
	logisticsMessage_State_TakingExpress("1","已揽收"),
	/**
	 * 
	 */
	logisticsMessage_State_Difficult("2","疑难"),
	/**
	 * 
	 */
	logisticsMessage_State_HaveBeenReceived ("3","已签收"),
	/**
	 * 
	 */
	logisticsMessage_State_SignBack("4","退签"),
	/**
	 * 
	 */
	logisticsMessage_State_OutOfDelivery("5","同城派送中"),
	/**
	 * 
	 */
	logisticsMessage_State_Return("6","退回");
	
	
	

	private final String code;
	
	private final String Name;

	public String getCode() {
		return code;
	}

	public String getName() {
		return Name;
	}

	/**
	 * @param code
	 * @param name
	 */
	private LogisticsMessage(String code, String name) {
		this.code = code;
		Name = name;
	}
    public static String getDesc(String code){
   	 String  name = null;
   	 if (code == null) return null;
   	 for (LogisticsMessage s : LogisticsMessage.values()){  
            if (code.equalsIgnoreCase(s.getCode())){
            	 name = s.getCode();
           	 break;
            }
        }   
   	return  name;
   }
}
