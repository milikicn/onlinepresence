import java.beans.Visibility;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import presenceComponents.*;

import statusComponents.*;


/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Aug 7, 2008
 * @version: 0.1
 */

/**
 * @author Nikola Milikic
 *
 */
public class PropertyCreator {
	private static Properties opoProperties = new Properties();
	static {
		opoProperties.setProperty("http://ggg.milanstankovic.org/opo/ns#Visible", "statusComponents.Visibility");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#Invisible", "statusComponents.Visibility");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#Available", "statusComponents.Disturbability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#DoNotDistrub", "statusComponents.Disturbability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#FreelyContactable", "statusComponents.Contactability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#ConstrainedContactability", "statusComponents.Contactability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#Active", "statusComponents.Activity");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#Inactive", "statusComponents.Activity");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#ProlongedInactive", "statusComponents.Activity");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#AllNotificationsPass", "presenceComponents.Notifiability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#NotificationsConstrained", "presenceComponents.Notifiability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#NotificationsProhibited", "presenceComponents.Notifiability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#ConstrainedContactability", "presenceComponents.Findability");
		opoProperties.put("http://ggg.milanstankovic.org/opo/ns#PubliclyFindable", "presenceComponents.Findability");
	}
	
	public static void createPropertyFile(String fileName) throws IOException{
		opoProperties.storeToXML(new FileOutputStream(fileName),"");
	}
	
	public static void main(String[] args) {
		try {
			PropertyCreator.createPropertyFile("opoProperties.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
