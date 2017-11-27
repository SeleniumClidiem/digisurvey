import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class listaddremove {
	
	 List<String> list = new ArrayList<>();
	 
	 @Test
	 public void list_ad_remov()
	 {
		 System.out.println("Before adding elements to list "+list.size());
		 list.add("String1"); list.add("String2"); list.add("String3"); list.add("String4"); list.add("String5");
		 for(int i=0; i<list.size();i++)
		 {
			 System.out.println(list.get(i));
		 }
		 System.out.println(list.size());
		 int old_list =list.size();
//checkbox elements adding to the list		 
		 list.add("String6"); list.add("String7"); list.add("String8"); list.add("String9"); list.add("String10");
		 for(int i=0; i<list.size();i++)
		 {
			 System.out.println(list.get(i));
		 }
		 System.out.println(old_list);
		 int new_list = list.size();
//after adding check elements get the size		 
		 System.out.println(new_list);
		 
		int list_size =  new_list-old_list;
		 
		 String[] arr = new String[list_size];
		 System.out.println("string array size : "+arr.length);
		 for(int ind=arr.length-1,j=new_list-1;ind>=0;ind--,j--)
		 {
			 arr[ind]=list.get(j);
			 if(ind!=0)
			 {
				 arr[ind]=","+arr[ind];
			 }
			
		 }
		 
		 for(int arr_ind = 0,list_rem=new_list-1;arr_ind<arr.length;arr_ind++,list_rem--)
		 {
			 System.out.println(arr[arr_ind]);
			//removing newly added list elements			 
			 list.remove(list_rem);
		 }
		 System.out.println("After concatenation removing list elements "+list.size());
		 
		 if(old_list==list.size())
		 {
			 for(int arr_siz=1;arr_siz<arr.length;arr_siz++)
			 {
				 //int arr_temp=arr_siz+1;
				 arr[0]=arr[0].concat(arr[arr_siz]);
			 }
			 System.out.println("the elemnet to be added to the list is "+arr[0]);
			 list.add(arr[0]);
			 
			 System.out.println(list.size());
		 }

		 
		 
		 
		 
		 /*String str1=list.get(new_list-1);
		 String str2=list.get(new_list-2);
		 str2= str2+","+str1;
		 list.set(new_list-2,str2);
		 
		 System.out.println("after concating , and without removing last ele , size :"+list.size());
		 list.remove(new_list-1);
		 System.out.println("After concatination , size of list : "+list.size());
		 
		 for(int k=0;k<list.size();k++)
		 {
			 System.out.println(list.get(k));
		 }*/
		 
		 
	 }

}
