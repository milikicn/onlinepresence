/**
 *  Java OPO library
 *  Copyright (C) 2010  Filip Radulovic, Nikola Milikic
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see http://www.gnu.org/licenses/.
 *  
 *  You any further questions regarding usage of this software you can 
 *  find appropriate contacts on the OPO Prject website 
 *  http://online-presence.net.
 */
package net.onlinepresence.ontmodel.sioc.beans;

import java.net.URI;
import java.net.URISyntaxException;

import net.onlinepresence.ontmodel.sioc.Post;
import net.onlinepresence.util.Constants;
import net.onlinepresence.util.EqualsUtil;

import thewebsemantic.Namespace;
import thewebsemantic.RdfProperty;
import thewebsemantic.RdfType;

@Namespace(Constants.SIOC_NS)
@RdfType("Post")
public class PostBean extends ItemBean implements Post {

	private static final long serialVersionUID = 3408687529954231482L;
	private Post replyOf;
	private URI primaryTopicOf;
	
	@Deprecated
	public PostBean() {
		super();
	}

	@Deprecated
	public PostBean(String uri) {
		super(uri);
	}

	@RdfProperty(Constants.SIOC_NS + "reply_of")
	public Post getReplyOf() {
		return replyOf;
	}

	public void setReplyOf(Post replyOf) {
		if(replyOf != null){
			replyOf.setUri(replyOf.getUri().toString().replaceFirst("Post", "ReplyPost"));
			this.replyOf = replyOf;
		}
	}

	@RdfProperty(Constants.FOAF_NS + "isPrimaryTopicOf")
	public URI getPrimaryTopicOf() {
		return primaryTopicOf;
	}

	public void setPrimaryTopicOf(URI primaryTopicOf) {
		this.primaryTopicOf = primaryTopicOf;
	}
	
	public void setPrimaryTopicOf(String primaryTopicOf) {
		if(primaryTopicOf != null)
			try {
				setPrimaryTopicOf(new URI(primaryTopicOf));
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		
		if (!(o instanceof PostBean))
			return false;

		PostBean post = (PostBean) (o);
		
		return
			EqualsUtil.areEqual(getReplyOf(), post.getReplyOf()) &&
			EqualsUtil.areEqual(getPrimaryTopicOf(), post.getPrimaryTopicOf()) &&
			super.equals(post);
	}
}
