package net.onlinepresence.domainmodel.foaf.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.ImageBean;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Image")
public class Image extends Thing implements ImageBean {

}
