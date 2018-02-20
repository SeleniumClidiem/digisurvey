import org.testng.annotations.Test;

public class splitString {

	@Test
	public void stringSplit()
	{
		/*String string ="https://www.youtube.com/embed/G-PGEPRMPm4";
		String[] parts = string.split("/");
		System.out.println("length="+parts.length);
		for(int i=0;i<parts.length;i++)
		{
			System.out.println(parts[i]);
		}
		System.out.println("Last part of link:"+parts[parts.length-1]);*/
		
		/*String web="3,565.00";
		String[] fundsplit = web.split(".");
		System.out.println("Length:"+fundsplit.length);
		for(int i=0;i<fundsplit.length;i++)
		{
			System.out.println("fund:"+fundsplit[i]);
		}*/
		
		/*String web="3,565.00";
		String excel="3565";
		
		String contact=web.replaceAll("[,]", "");
		System.out.println(contact);*/
		
		String languages="am-Amharic,ar-Arabic";
		String[] lang= languages.split(",");
		System.out.println(lang.length);
		for(int i=0;i<lang.length;i++)
		{
			System.out.println(lang[i]);
		}
	}
}
