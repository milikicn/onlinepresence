package net.onlinepresence.domainmodel.sioc.interfaces;

import net.onlinepresence.domainmodel.foaf.interfaces.DocumentBean;

public interface PostBean extends ItemBean {

	PostBean getReplyOf();
	void setReplyOf(PostBean replyOf);
	
	DocumentBean getPrimaryTopicOf();
	void setPrimaryTopicOf(DocumentBean primaryTopicOf);
	void setPrimaryTopicOf(String primaryTopicOf);
}
