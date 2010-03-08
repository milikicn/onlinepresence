package net.onlinepresence.domainmodel.foaf.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.ImageBean;
import net.onlinepresence.domainmodel.foaf.interfaces.PersonBean;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Person")
public class Person extends Agent implements PersonBean {

	private ImageBean img;

	public Person() {
		super();
	}

	public Person(String uri) {
		super(uri);
	}

	@RdfProperty("http://xmlns.com/foaf/0.1/img")
	public ImageBean getImg() {
		return img;
	}

	public void setImg(ImageBean img) {
		if(img != null)
			this.img = img;
	}

}
