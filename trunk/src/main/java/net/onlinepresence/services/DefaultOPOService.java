package net.onlinepresence.services;

import java.util.Collection;

import net.onlinepresence.domainmodel.opo.interfaces.OnlinePresenceBean;
import net.onlinepresence.domainmodel.opo.pojos.OnlinePresence;

import thewebsemantic.Bean2RDF;
import thewebsemantic.RDF2Bean;

import com.hp.hpl.jena.rdf.model.Model;

public class DefaultOPOService {

	protected Bean2RDF writer;
	protected RDF2Bean reader;
	

	public Model save(Model model, OnlinePresenceBean onlinePresence) {

		writer = new Bean2RDF(model);
		writer.saveDeep(onlinePresence);

		return model;
	}

	public Collection<OnlinePresence> load(Model model) {

		reader = new RDF2Bean(model);

		return reader.loadDeep(OnlinePresence.class);
	}
}
