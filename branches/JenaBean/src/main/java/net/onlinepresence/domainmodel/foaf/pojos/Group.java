package net.onlinepresence.domainmodel.foaf.pojos;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.foaf.interfaces.GroupBean;
import net.onlinepresence.domainmodel.general.Resource;

@Namespace("http://xmlns.com/foaf/0.1/")
@RdfType("Group")
public class Group extends Resource implements GroupBean {

}
