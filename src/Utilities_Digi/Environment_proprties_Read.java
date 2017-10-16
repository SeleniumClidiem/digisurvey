package Utilities_Digi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Environment_proprties_Read {
	public Properties loadProperties()
	{	

		
		InputStream input=getClass().getClassLoader().getResourceAsStream("ObjectRepository_Digi.Properties");
		Properties obj = new Properties();
		try {
			if(input!=null)
			{
				obj.load(input);
				return obj;
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String Environment(String propertyname) throws IOException
	{
		Properties prop1 = loadProperties();
		return prop1.getProperty(propertyname);
	}

}
