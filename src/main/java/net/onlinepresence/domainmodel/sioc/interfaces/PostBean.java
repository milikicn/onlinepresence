package net.onlinepresence.domainmodel.sioc.interfaces;

import java.net.URI;

public interface PostBean extends ItemBean {

	PostBean getReplyOf();
	void setReplyOf(PostBean replyOf);
	
	URI getPrimaryTopicOf();
	void setPrimaryTopicOf(URI primaryTopicOf);
	void setPrimaryTopicOf(String primaryTopicOf);
}
