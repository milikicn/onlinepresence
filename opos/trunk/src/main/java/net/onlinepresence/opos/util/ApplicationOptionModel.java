/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Mar 28, 2009
 * @version: 0.1
 */
package net.onlinepresence.opos.util;

import java.util.Map;

import net.onlinepresence.opos.domain.Application;
import net.onlinepresence.opos.domain.beans.ApplicationBean;

import org.apache.tapestry5.OptionModel;

/**
 * @author Nikola Milikic
 * 
 */
public class ApplicationOptionModel implements OptionModel {
	private ApplicationBean application;

	public ApplicationOptionModel(Application application) {
		this.application = (ApplicationBean) application;
	}

	public String getLabel() {
		return application.getName();
	}

	public boolean isDisabled() {
		return false;
	}

	public Map<String, String> getAttributes() {
		return null;
	}

	public Object getValue() {
		return application;
	}
}
