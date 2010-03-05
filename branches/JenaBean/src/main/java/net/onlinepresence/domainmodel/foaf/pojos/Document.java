package net.onlinepresence.domainmodel.foaf.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;
import net.onlinepresence.domainmodel.general.Resource;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Document")
public class Document extends Resource implements DocumentBean {

}
