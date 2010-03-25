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
