/**
* @author: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Aug 9, 2008
* @version: 0.1
*/
package handlers;

import presence.OnlinePresence;
import presenceComponents.Post;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author Nikola Milikic
 *
 */
public class TypeHandler implements AbstractHandler {

	/* (non-Javadoc)
	 * @see handlers.AbstractHandler#handleNode(presence.OnlinePresence, com.hp.hpl.jena.rdf.model.RDFNode)
	 */
	
	public void handleNode(OnlinePresence oPresence, Resource subject, RDFNode object){
		if(!subject.isAnon()){
			String objectString = object.toString();
			String className = null;
			
			if(objectString.contains("#"))
				className = objectString.substring(objectString.indexOf("#") + 1);
			else
				className = objectString.substring(objectString.lastIndexOf("/") + 1);
			
			System.out.println(subject.toString());
			
			if(className.equals("OnlinePresence"))
				oPresence.setURI(subject.toString());
			else{
				System.out.println(className + "-----" + subject.toString());
				
				try {
					oPresence.getObjectProperty(className).setURI(subject.toString());
				} catch (Exception e) {
					Post sp = new Post();
					oPresence.setCustomMessage(sp);
					oPresence.getObjectProperty("StatusMessage").setURI(subject.toString());
				}
			}
		}
	}

}
