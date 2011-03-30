/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Mar 28, 2009
 * @version: 0.1
 */
package net.onlinepresence.opos.util;

import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.service.Applications;

import org.apache.tapestry5.ValueEncoder;

/**
 * @author Nikola Milikic
 * 
 */
@SuppressWarnings("rawtypes")
public class ApplicationEncoder implements ValueEncoder {
	private Applications source;

	public ApplicationEncoder(Applications source) {
		this.source = source;
	}

	public String toClient(Object obj) {
		return "" + ((Application) obj).getWebAddress();
	}

	public Object toValue(String str) {
			return source.getApplication(str);
	}
}
