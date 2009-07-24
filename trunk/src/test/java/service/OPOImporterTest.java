package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import presence.OnlinePresence;
import presenceComponents.OnlineStatus;

public class OPOImporterTest {

	public static void main(String[] args) {
		OPOImporter oim = new OPOImporter();
		
		OnlinePresence o = null;
		try {
			o = oim.importRDF("exported.rdf");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (InvalidPropertiesFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		
		OnlineStatus os = (OnlineStatus)o.getObjectProperty("OnlineStatus");
		
		OPOExporter oex = new OPOExporter(o);
		oex.makeModel();

		oex.serializeToXMLRDF("imported.rdf");

	}
}
