import org.testng.annotations.Test;

public class dateStringSplit {
	@Test
	public void dateString()    //output:
	{
		String date = "01/31/2018";
		String output="31 Jan 2018";
		String[] parts = date.split("/");
		
		for(int i=0;i<parts.length;i++)
		{
			System.out.println(parts[i]);
			if(i==0)
			{
				switch(parts[i])
				{
					case "01":parts[i]= "Jan";
							  break;
					case "02":parts[i]= "Feb";
							  break;
					case "03":parts[i]= "Mar";
							  break;
					case "04":parts[i]= "Apr";
							  break;
					case "05":parts[i]= "May";
							  break;
					case "06":parts[i]= "Jun";
							  break;
					case "07":parts[i]= "Jul";
							  break;
					case "08":parts[i]= "Aug";
							  break;
					case "09":parts[i]= "Sep";
							  break;
					case "10":parts[i]= "Oct";
							  break;
					case "11":parts[i]= "Nov";
							  break;
					case "12":parts[i]= "Dec";
							  break;
					
				}
				System.out.println("Month value is:"+parts[i]);
				if(output.contains(parts[i]))
					System.out.println("display success expected and actual same");
				else
					System.out.println("expected and actual are not same");
			}
			if(i==1)
			{
				if(output.contains(parts[i]))
					System.out.println("display success expected and actual same");
				else
					System.out.println("expected and actual are not same");
			}
			if(i==2)
			{
				if(output.contains(parts[i]))
					System.out.println("display success expected and actual same");
				else
					System.out.println("expected and actual are not same");
			}
		}
		
		
	}

}
