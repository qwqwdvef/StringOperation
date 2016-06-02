class StringMinus  
{
    
    public static void run(String a,String b){
        
    	 
    	final int aLength = a.length();
		final int bLength = b.length();

		int a_isDecimal = 0;
		int b_isDecimal = 0;

		int a_raidx_point = a.indexOf('.');
		int b_raidx_point = b.indexOf('.');

		if (a_raidx_point != -1) a_isDecimal = 1;
		if (b_raidx_point != -1) b_isDecimal = 1;

		int aIndex = a_raidx_point == -1 ? aLength : a_raidx_point; // 小数点前的长度
		int bIndex = b_raidx_point == -1 ? bLength : b_raidx_point; // 小数点前的长度

		/* 小数点前补0 */
		int len = aIndex - bIndex;
		if (len < 0) {
			len = len * -1;
			a = appendZeroAtHead(a, len);
		} else {
			b = appendZeroAtHead(b, len);
		}

		/* 补小数点 */

		if (b_isDecimal == 1 && a_isDecimal == 0) {
			a += ".";
		}
		if (a_isDecimal == 1 && b_isDecimal == 0) {
			b += ".";
		}

		/* 小数点后补0 */

		aIndex = aLength - aIndex - a_isDecimal; // 小数点后的长度
		bIndex = bLength - bIndex - b_isDecimal; // 小数点后的长度

		len = aIndex - bIndex;
		if (len < 0) {
			len = len * -1;
			a = appendZeroAtLast(a, len);

		} else {
			b = appendZeroAtLast(b, len);
		}
        
        
        String result = subs(a,b);
        
        System.out.println("结果是="+result);
    }
    
   
    public static String subs(String a,String b){
String result = "";
        
        char[] arrMin = a.toCharArray();
        char[] arrMax = b.toCharArray();
        
        int minLength = arrMin.length<arrMax.length?arrMin.length:arrMax.length;       
        
         boolean isNegative = false;
         
         if(minLength !=  arrMin.length){
        	 isNegative = true;
         }
        
        // 将最大的数设置到arr  
        for(int i = 0 ; i < minLength && !isNegative; i++){
            if(arrMin[i]<arrMax[i]){
                isNegative = true;
            }else if (arrMin[i] > arrMax[i]){
                break;
            }
        }
        
        if(isNegative){
        	  char[] tmp = arrMin;
              arrMin = arrMax;
              arrMax = tmp;
        }
      
        int value = 0 ;
       
        for(int i = minLength -1 ;i >= 0 ;i --){
        	
        	if(arrMin[i] == '.' && arrMax[i] == '.' ){
				result = arrMin[i] + result;
				continue;
			}else if(arrMin[i] == '.' && arrMax[i] != '.' ){
				return "小数点位置没有对齐";
			}
        	
            int c = toInt(arrMin[i])-toInt(arrMax[i])-value;
            if( c < 0){
                c += 10;
                value = 1;
            }else{
                value = 0;
            }
            
            result = c + result; 
        }
        
        char[] result1 = result.toCharArray();
        
        result = "";
        boolean flag = false;
        for(int i = 0 ; i < result1.length ; i++){
        	if(result1[i] == '0' && i < result1.length -1 && result1[i+1] != '.' && !flag){
        		continue;
        	}else if( !flag ){
        		flag = true;
        	}
        	
        	result += result1[i] ;
        }

        return isNegative?"-"+result:result;
    }
    
    public static String appendZeroAtLast(String a, int length) {

		for (int i = 0; i < length; i++) {
			a += "0";
		}
		return a;
	}
	
	public static String appendZeroAtHead(String a, int length) {

		for (int i = 0; i < length; i++) {
			a = "0" + a;
		}
		return a;
	}
    
    
    public static int toInt(char s){
        return (int)s-48;
    }
    
    public static char toChar(int s) {
        return (char)(s+48);
    }
    
	public static void main (String[] args) 
	{
		StringMinus.run("512.0990003","568.1");
	}
}
