package org.goodoldai.demo.twitt2opo.converter.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Model;

public class FileWriter {
	
	Model model;
	
	public FileWriter(Model model){
		this.model = model;
	}
	
	public void write(String absoluteUrl) throws FileNotFoundException, IOException{
		if(!createParentFolders(absoluteUrl)){
			throw new IOException("Parent folders could not be created");
		}
		
		FileOutputStream fout;

//		File file = new File(absoluteUrl.substring(0, absoluteUrl.length() -1));
		File file = new File(absoluteUrl);
		fout = new FileOutputStream(file);
		model.write(fout, "RDF/XML-ABBREV", "");
		fout.close();
	}

	private boolean createParentFolders(String absoluteUrl) {
		String path = absoluteUrl.substring(0, absoluteUrl.lastIndexOf("/"));
		File parent = new File(path);
		if(parent.exists())
			return true;
		else
			return parent.mkdirs();
	}
}
