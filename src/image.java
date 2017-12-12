import org.apache.poi.hssf.record.RightMarginRecord;

public class image {

	public static void main(String[] args) {
		String str="C:\\Users\\Public\\Pictures\\Sample Pictures\\Penguins.jpguoi";
		for (int i = 0 ; i<str.length() ; i++)
		{
			System.out.println(str.charAt(i)+" ="+i);
	        if (str.charAt(i)=='.')
	        {
	        	System.out.println(i);
	        	String subString=str.substring(i, str.length());
	        	System.out.println(subString);
	        	
	        	String str3 = str.replaceAll(subString, "");
	        	System.out.println("removing.jpg:"+str3);
	        	int k=str3.length();
	        	System.out.println(k);
	        	String[] arr=str3.split("\\\\");
	        	System.out.println(arr.length);
	        	System.out.println(arr[arr.length-1]);
	        	break;
	        }
		}

	}

}
