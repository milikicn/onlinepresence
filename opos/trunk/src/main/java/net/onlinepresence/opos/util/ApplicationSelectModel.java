/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Mar 28, 2009
 * @version: 0.1
 */
package net.onlinepresence.opos.util;

import java.util.ArrayList;
import java.util.List;

import net.onlinepresence.opos.domain.Application;

import org.apache.tapestry5.OptionGroupModel;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.util.AbstractSelectModel;

/**
 * @author Nikola Milikic
 * 
 */
public class ApplicationSelectModel extends AbstractSelectModel{

	private List<Application> applications;

	public ApplicationSelectModel(List<Application> applications) {
		this.applications = applications;
	}

	public List<OptionGroupModel> getOptionGroups() {
		return null;
	}

	public List<OptionModel> getOptions() {
		List<OptionModel> list = new ArrayList<OptionModel>();
		for (Application a : applications) {
			list.add(new ApplicationOptionModel(a));
		}
		return list;
	}

}
