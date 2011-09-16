package net.onlinepresence.opos.tapestry.pages.util;

import net.onlinepresence.opos.domain.Membership;
import net.onlinepresence.opos.tapestry.appconfig.UserAppSettings;

public class ApplicationSettingsConfigurator {

	public static void configureUserAppSettings(UserAppSettings appSettings, Membership mem) {
		appSettings.setUserUssesApp(true);
		appSettings.setSendDataToApp(mem.isSendTo());
		appSettings.setReceiveDataFromApp(mem.isReceiveFrom());
		appSettings.setUsername(mem.getUsername());
		appSettings.setPassword(mem.getPassword());
	}
}
