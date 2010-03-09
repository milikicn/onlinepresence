package net.onlinepresence.util.urigenerator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class FileSysemBasedUriGenerator extends UriGenerator {
	
	@Override
	public String generateInstanceUri(Object instance, String namespace) {
		long currentTime = System.currentTimeMillis();
		return namespace +
				instance.getClass().getSimpleName() +
				"/" +
				String.valueOf(currentTime);
	}
//
//	private String constructRelativeUrl(String username, String slash) {
//		String yearStr = time.substring(0, 4);
//		String monthStr = time.substring(5, 7);
//		String dayStr = time.substring(8, 10);
//		String hourStr = time.substring(11, 13);
//		String minuteStr = time.substring(14, 16);
//		String secondStr = time.substring(17, 19);
//		
//		return "repository" + slash + yearStr + slash + monthStr + slash + dayStr + slash + username + "-" + hourStr + "-" + minuteStr + "-" + secondStr + ".rdf#";
//	}
}
