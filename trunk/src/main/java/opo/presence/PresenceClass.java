package opo.presence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;

import opo.presenceProperties.PresenceProperty;
import opo.service.OPOImporter;


import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;
@SuppressWarnings("unchecked")
public abstract class PresenceClass extends OntologyConcept {
	
	{
		setNameSpace(this.getClassNameSpace());
	}

	protected LinkedList<PresenceProperty> propertyList = new LinkedList<PresenceProperty>();

	/**
	 * 
	 * @param <T>
	 * @param component
	 */
	public abstract <T> void addComponent(T component);
	
	/**
	 * 
	 * @param resource
	 */
	public void makeResource(Resource resource) {
		for (PresenceProperty pp : propertyList) {
			pp.attachAsProperty(resource);
		}
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	public Resource createAsNode(Model model){
		Resource s = null;
		if(getURI() != null)
			s = model.createResource(getURI().toString());
		else
			s = model.createResource();
		s.addProperty(RDF.type, model.createResource(getClassURI().toString()));
		makeResource(s);
		
		return s;
	}
	
	/**
	 * 
	 * 
	 * @param list
	 */
	public void addPropertyList(LinkedList<PresenceProperty> list){
		propertyList.addAll(list);
	}
	
	public void addProperty(PresenceProperty property){
		property.setNameSpace(this.getNameSpace());
		propertyList.add(property);
	}
	
	private String getClassNameSpace(){
		String result = "";
		try {
			result = OPOImporter.readXmlProperties("conf/namespaces.xml").getProperty(this.getClass().getName());
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}