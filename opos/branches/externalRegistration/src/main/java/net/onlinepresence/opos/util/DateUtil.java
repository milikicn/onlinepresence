package net.onlinepresence.opos.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtil {

	private static final long ONE_HOUR = 60 * 60 * 1000L;
	public static final String DATE = "dd.MM.yyyy";
	public static final String TIME_DATE = "HH:mm - dd.MM.yyyy";

	public static long daysBetween(Date d1, Date d2) {
		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}
	
	public static String getPreetyDate(Date date) {
		return getPreetyDate(date, DateUtil.DATE);
	}
	public static String getPreetyDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
}
