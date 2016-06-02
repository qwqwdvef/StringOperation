class StringAdd  
{
    /*增加了一个测试注释*/
    public static void run(String a,String b){
        
    	int aLength = a.length();
		int bLength = b.length();

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

		/* 补小数点,以及小数点后的0 */

		if (b_isDecimal == 1 && a_isDecimal == 0) {
			a += ".";
			if(aIndex == aLength){
				a += "0";
				aLength++;
			}
		}
		if (a_isDecimal == 1 && b_isDecimal == 0) {
			b += ".";
			if(bIndex == bLength){
				b += "0";
				bLength++;
			}
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
        String result = add(a,b);
        System.out.println("结果是="+result);
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
    
    public static String add(String a,String b){
    	//System.out.println("a="+a);
    			//System.out.println("b="+b);
    			
    			String result = "";

    			/*
    			 * 进行a+b的运算
    			 */
    			char[] arr1 = a.toCharArray();
    			char[] arr2 = b.toCharArray();

    			int maxLength = arr1.length > arr2.length ? arr1.length : arr2.length;
    			int var1 = 0; // 进位变量

    			for (int i = maxLength - 1; i >= 0; i--) {
    				if(arr1[i] == '.' && arr2[i] == '.' ){
    					result = arr1[i] + result;
    					continue;
    				}else if(arr1[i] == '.' && arr2[i] != '.' ){
    					return "小数点位置没有对齐";
    				}
    				
    				int c = toInt(arr1[i]) + toInt(arr2[i]);
    				if (c >= 10) {
    					c -= 10;
    					var1 = 1;
    				} else {
    					var1 = 0;
    				}
    				if (i > 0) {
    					if(arr1[i-1] == '.'){
    						arr1[i - 2] += var1;
    					}else{
    						arr1[i - 1] += var1;
    					}
    				}

    				result = c + result;

    			}
    			if (var1 != 0) {
    				result = var1 + result;

    			}
    			return result;
    }
    
    public static int toInt(char s){
        return (int)s-48;
    }
    
    public static char toChar(int s) {
        return (char)(s+48);
    }
    
	public static void main (String[] args) 
	{
	  StringAdd.run("52.5","2.72");
	}
}
