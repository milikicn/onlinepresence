package net.onlinepresence.opos.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author ziplies
 * @version $LastChangedRevision: $ <br />
 * @modified $LastChangedDate: $ <br />
 *           $LastChangedBy: $
 */
public class StringUtils {

	private static String indent = "";

	/**
	 * 
	 * @param <T>
	 * @param array
	 * @param delimiter
	 * @return
	 */
	public static <T extends Object> String arrayToString(final T[] array,
			final String delimiter) {
		return listToString(Arrays.asList(array), delimiter);
	}

	public static <T extends Object> String listToString(final List<T> list,
			final String delimiter) {
		//
		final String limiter = delimiter != null ? delimiter : "\r\n";

		//
		final StringBuffer strBuf = new StringBuffer();
		for (T element : list) {
			strBuf.append(element + limiter);
		}

		return (strBuf.length() == 0) ? "" : strBuf.substring(0,
				strBuf.lastIndexOf(limiter));
	}

	/**
	 * 
	 * @param token
	 * @param limiter
	 * @return
	 */
	public static List<String> tokenizeString(final String token,
			final String limiter) {
		final List<String> elements = new ArrayList<String>();
		int newIndex = 0;
		int oldIndex = 0;

		if (token.indexOf(limiter) != -1) {
			do {
				oldIndex = token.indexOf(limiter, newIndex);
				elements.add(token.substring(newIndex, oldIndex));
				newIndex = oldIndex + limiter.length();

			} while (token.indexOf(limiter, newIndex) != -1);
			elements.add(token.substring(newIndex, token.length()));

		} else {
			elements.add(token);
		}

		return elements;
	}

	/**
	 * 
	 * @param instance
	 * @return
	 */
	public static String toStringByReflection(final Object instance) {
		//
		indent += "\t";

		StringBuffer buffer = new StringBuffer(instance.getClass() + ":\r\n");

		//
		Class<?> factory = instance.getClass();

		Field[] fields = factory.getFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);

				// if(field.isAccessible()) {
				String name = field.getName();
				Object value = field.get(instance);

				buffer.append(indent + name + " = " + value + "\r\n");
				// }

			} catch (Exception e) {
			}
		}

		//
		indent = indent.substring(0, indent.length() - 1);

		return buffer.toString();
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalize(final String s) {
		final List<String> strings = tokenizeString(s, " ");
		final StringBuffer buffer = new StringBuffer();
		for (String string : strings) {
			buffer.append(string.substring(0, 1).toUpperCase()
					+ string.substring(1, string.length()).toLowerCase() + " ");
		}
		return buffer.toString();
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	public static String humanReadableFileSize(long number) {
		long absNumber = Math.abs(number);
		DecimalFormat oneDecimal = new DecimalFormat("0.0");
		double result = number;
		String suffix = "";
		if (absNumber < 1024) {
			// nothing
		} else if (absNumber < 1024 * 1024) {
			result = number / 1024.0;
			suffix = "k";
		} else if (absNumber < 1024 * 1024 * 1024) {
			result = number / (1024.0 * 1024);
			suffix = "M";
		} else {
			result = number / (1024.0 * 1024 * 1024);
			suffix = "G";
		}
		return oneDecimal.format(result) + suffix;
	}

	/**
	 * 
	 * @param content
	 * @return
	 */
	public static CharSequence encode(String content) {
		try {
			return URLEncoder.encode(content, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new AssertionError(e);
		}
	}

	/**
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	public static String readString(InputStream in) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuffer b = new StringBuffer();
			String s = reader.readLine();
			while (s != null) {
				b.append(s);
				if ((s = reader.readLine()) != null)
					b.append('\n');
			}
			return b.toString();
		} finally {
			in.close();
		}
	}

	public static String formatDecimalValue(double input) {
		DecimalFormat df = new DecimalFormat("#.###");
		String output = df.format(input);

		return output;
	}

}
