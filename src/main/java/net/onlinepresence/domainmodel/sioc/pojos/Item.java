package net.onlinepresence.domainmodel.sioc.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.general.Resource;
import net.onlinepresence.domainmodel.sioc.interfaces.ItemBean;

@Namespace("http://rdfs.org/sioc/ns#")
@RdfType("Item")
public class Item extends Resource implements ItemBean {

	private String content;

	public Item() {
		super();
	}
	
	public Item(String uri) {
		super(uri);
	}
	
	@RdfProperty("http://rdfs.org/sioc/ns#content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if(content != null)
			this.content = content;
	}

}
