import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class listdropdown 
{
	 List<String> list = new ArrayList<>();
	 @Test
	 public void list_ad_remov()
	 {
		 System.out.println("Before adding elements to list "+list.size());
		 list.add("String1"); list.add("String2");list.add("String2"); list.add("String3"); list.add("String4"); list.add("String5");
		 for(int i=0; i<list.size();i++)
		 {
			 System.out.println(list.get(i));
		 }
		 System.out.println(list.size());
		 int beforDroplist =list.size();
		 int list_withdrop = list.size()+1;
//Dropdown elements adding to the list		 
		 list.add("String6");list.add("String7");
		 for(int i=0; i<list.size();i++)
		 {
			 System.out.println(list.get(i));
		 }
		 System.out.println(beforDroplist);
		 int newDrop_list = list.size();
//after adding check elements get the size		 
		 System.out.println(newDrop_list);
		 
		/*int list_size =  newDrop_list-beforDroplist;
		System.out.println(list_size);*/
		list.add(beforDroplist,list.get(newDrop_list-1));
		list.remove(newDrop_list);
		
		
		System.out.println("after adding drop elem List size "+list.size());
		int list_incl_drop =list.size();
		
		System.out.println(list.get(beforDroplist));//list.get(5)
		
		System.out.println(list_incl_drop);
		
		for(int i=0;i<list_incl_drop;i++)//i=0;i<11;i++
		{
			System.out.println(list.get(i));
			//list.remove(i);
		}
		for(int i=list_incl_drop-1;i>=list_withdrop;i--)
		{
			System.out.println(list.get(i));
			list.remove(i);
		}
		System.out.println(list.size());
		 
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
		
 
	 }

}
