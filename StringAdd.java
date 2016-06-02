package a;


class StringAdd  
{
    
    public static void run(String a,String b){
        
    	int aLength = a.length();
		int bLength = b.length();

		int a_isDecimal = 0;
		int b_isDecimal = 0;

		int a_raidx_point = a.indexOf('.');
		int b_raidx_point = b.indexOf('.');

		if (a_raidx_point != -1) a_isDecimal = 1;
		if (b_raidx_point != -1) b_isDecimal = 1;

		int aIndex = a_raidx_point == -1 ? aLength : a_raidx_point; // С����ǰ�ĳ���
		int bIndex = b_raidx_point == -1 ? bLength : b_raidx_point; // С����ǰ�ĳ���

		/* С����ǰ��0 */
		int len = aIndex - bIndex;
		if (len < 0) {
			len = len * -1;
			a = appendZeroAtHead(a, len);
		} else {
			b = appendZeroAtHead(b, len);
		}

		/* ��С����,�Լ�С������0 */

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

		/* С�����0 */

		aIndex = aLength - aIndex - a_isDecimal; // С�����ĳ���
		bIndex = bLength - bIndex - b_isDecimal; // С�����ĳ���

		len = aIndex - bIndex;
		if (len < 0) {
			len = len * -1;
			a = appendZeroAtLast(a, len);

		} else {
			b = appendZeroAtLast(b, len);
		}
        String result = add(a,b);
        
        System.out.println("�����="+result);
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
    			 * ����a+b������
    			 */
    			char[] arr1 = a.toCharArray();
    			char[] arr2 = b.toCharArray();

    			int maxLength = arr1.length > arr2.length ? arr1.length : arr2.length;
    			int var1 = 0; // ��λ����

    			for (int i = maxLength - 1; i >= 0; i--) {
    				if(arr1[i] == '.' && arr2[i] == '.' ){
    					result = arr1[i] + result;
    					continue;
    				}else if(arr1[i] == '.' && arr2[i] != '.' ){
    					return "С����λ��û�ж���";
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