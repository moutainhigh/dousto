// 验证是否全为数字
function isNumber(strNumber){
	strNumber=trim(strNumber);
	var hasNumber = 0;//标记字符串中是否有数字
	var dotNum = 0;
	var minusNum = 0;
	
	var i=0;	
	for(i=0;i<strNumber.length;i++){
		var charIsNumber=strNumber.charAt(i);
		//if( (charIsNumber<'0' || charIsNumber>'9') && charIsNumber!='.' && charIsNumber!='-' && charIsNumber!='%' ){
		//非数字字符（不包括"."和"-"号）
		if( (charIsNumber<'0' || charIsNumber>'9') && charIsNumber!='.' && charIsNumber!='-' ){
			//alert("请检查输入框，判断数字输入正误！"+charIsNumber);
			return "false";
		}
		//"-"
		else if (charIsNumber=='-')
		{
			//"-"只能出现在最左边
			if (i!=0)
			{
			    return "false";
			}
			minusNum++;
		}
		//"."
	    else if (charIsNumber=='.')
		{
			//"."号不能出现在最左边
			if (i==0)
			{
			    return "false";
			}
			dotNum++;
		}
		//数字字符
		else
		{
			//标志字符串中含数字字符
			hasNumber++;
		}
	}
	
	//字符串不包含数字
	if (strNumber.length > 0 && hasNumber==0)
	{
		return "false";
	}
	//不能输入多个"."或"-"
	if(dotNum > 1 || minusNum >1)
	{
		return "false";
	}
	
	return "true";
}

// 去除字符串中的空格
function trim(s){
  if( s == null ) return "";
  s=s.replace(/^ +/, "");
  s=s.replace(/ +$/, "");
  return s;
}


