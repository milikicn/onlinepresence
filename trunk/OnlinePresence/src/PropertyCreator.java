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
		opoProperties.setProperty("http://ggg.milanstankovic.org/opo/ns#declaredBy", "handlers.AgentHandler");
		opoProperties.setProperty("http://ggg.milanstankovic.org/opo/ns#avatar", "handlers.AvatarHandler");
		opoProperties.setProperty("http://ggg.milanstankovic.org/opo/ns#customMessage", "handlers.CustomMessageHandler");
		opoProperties.setProperty("http://ggg.milanstankovic.org/opo/ns#hasPresenceComponent", "handlers.PresenceComponentHandler");
		opoProperties.setProperty("http://ggg.milanstankovic.org/opo/ns#hasStatusComponent", "handlers.StatusComponentHandler");
		opoProperties.setProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type", "handlers.TypeHandler");
	}
	
	public static void createPropertyFile(String fileName) throws IOException{
		opoProperties.storeToXML(new FileOutputStream(fileName),"");
	}
	
	public static void main(String[] args) {
		try {
			PropertyCreator.createPropertyFile("opoProperties.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
