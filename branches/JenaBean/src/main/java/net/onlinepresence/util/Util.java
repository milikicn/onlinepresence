package net.onlinepresence.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Util {

	public static CharSequence encode(String content) {
		try {
			return URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}
	
	public static String readString(InputStream in) throws IOException {

		try {
			BufferedReader reader = new BufferedReader( new InputStreamReader (in) );
			StringBuffer b = new StringBuffer();
			String s = reader.readLine();
			while ( s != null ) {
				b.append(s);
				if ( (s = reader.readLine()) != null )
					b.append('\n');
			}
			return b.toString();
		} finally {
			in.close();
		}
	}
	
	public static String getTime(){
        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		return formater.format(new GregorianCalendar().getTime());
	}
}
