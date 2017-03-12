/**
 * 
 */
package employee.training.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Andrei_Tsishkouski
 *
 */
public class PropertiesUtil {
	
	public Properties getProperties(String pPropertiesFile){
	  Properties properties = new Properties();
	  try{
		  properties.load(this.getClass().getClassLoader().getResourceAsStream(pPropertiesFile));
		  return properties;
	  }catch(IOException ex){
		  return null;
	  }
	}

}