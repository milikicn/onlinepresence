package net.onlinepresence.domainmodel.opoactions;

import thewebsemantic.Namespace;
import thewebsemantic.RdfType;
import net.onlinepresence.domainmodel.opo.pojos.Action;
import net.onlinepresence.domainmodel.opoactions.interfaces.BeingWithBean;

@Namespace("http://online-presence.net/opo-actions/ns#")
@RdfType("BeingWith")
public class BeingWith extends Action implements BeingWithBean {

}
