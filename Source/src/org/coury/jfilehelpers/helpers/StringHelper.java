/*
 * StringHelper.java
 *
 * Copyright (C) 2007 Felipe Gonçalves Coury <felipe.coury@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package org.coury.jfilehelpers.helpers;

import java.io.IOException;

import org.apache.commons.lang.builder.ToStringStyle;
import org.coury.jfilehelpers.core.ExtractedInfo;
import org.coury.jfilehelpers.engines.LineInfo;

public class StringHelper {
	public static void main(String[] args) {
		String test1 = "abcdeCAmanducaia";

		System.out.println(StringHelper.trimStart(test1, "abcdefghijklmnopqrstuvwxyz".toCharArray()));
		System.out.println(StringHelper.trimEnd(test1, "abcdefghijklmnopqrstuvwxyz".toCharArray()));
		System.out.println(StringHelper.trimBoth(test1, "abcdefghijklmnopqrstuvwxyz".toCharArray()));
	}
	
	public static String toStringBuilder(Object o) {
		return toStringBuilder(o, o.toString());
	}

	public static String toStringBuilder(Object o, String defaultString) {
		try {
			return getToString(o);
		} catch (ClassNotFoundException e) {
			return defaultString;
		}
	}

	private static String getToString(Object o) throws ClassNotFoundException {
		Class.forName("org.apache.commons.lang.builder.ToStringBuilder");
		return org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
	}

	public static void createQuotedString(StringBuffer sb, String source, char quoteChar) {
		if (source == null) source = "";

		String quotedCharStr = Character.toString(quoteChar);
		String escapedString = source.replace(quotedCharStr, quotedCharStr + quotedCharStr);

		sb.append(quoteChar);
		sb.append(escapedString);
		sb.append(quoteChar);		
	}
	
	public static ExtractedInfo extractQuotedString(LineInfo line, char quoteChar, boolean allowMultiline) throws IOException {
		if (line.isEol()) {
			throw new IllegalArgumentException(
					"An empty String found and can be parsed like " +
					"a QuotedString try to use SafeExtractQuotedString");
		}

		if (line.getLine()[line.getCurrentPos()] != quoteChar) {
			throw new IllegalArgumentException(
					"The source string not begins with the quote char: " + 
					quoteChar);
		}

		StringBuilder res = new StringBuilder(32);
		//int lines = 0;

		boolean firstFound = false;

		int i = line.getCurrentPos() + 1;
		//bool mustContinue = true;

		while (line.getLineStr() != null) {
			while (i < line.getLine().length) {
				if (line.getLine()[i] == quoteChar) {
					if (firstFound == true) {
						// Is an escaped quoted char
						res.append(quoteChar);
						firstFound = false;
					}
					else {
						firstFound = true;
					}
				}
				else {
					if (firstFound) {
						// This was the end of the string
						line.setCurrentPos(i);
						return new ExtractedInfo(res.toString());
//						ExtractedInfo ei = ;
//						return ei;

					}
					else {
						res.append(line.getLine()[i]);
					}
				}
				i++;
			}

			if (firstFound) {
				line.setCurrentPos(i);
				return new ExtractedInfo(res.toString());
			}
			else {
				if (allowMultiline == false) {
					throw new IllegalArgumentException(
							"The current field has an UnClosed quoted string. Complete line: " + res.toString());
				}

				line.readLine();
				res.append(StringHelper.NEW_LINE);
				//lines++;
				i = 0;
			}
		}

		throw new IllegalArgumentException(
				"The current field has an unclosed quoted string. Complete Filed String: " + res.toString());
	}
	
	public static String trimBoth(String s, char[] toTrim) {
		return trimStart(trimEnd(s, toTrim), toTrim);
	}
	
	public static String trimStart(String s, char[] toTrim) {
		char[] sChars = s.toCharArray();
		String toTrimStr = new String(toTrim);
		StringBuffer sb = new StringBuffer();
		
		boolean trimming = true;
		for (int i = 0; i < sChars.length; i++) {
			if (toTrimStr.indexOf(sChars[i]) == -1) {
				trimming = false;
			}
			
			if (!trimming) {
				sb.append(sChars[i]);
			}
		}
		
		return sb.toString();
	}

	public static String trimEnd(String s, char[] toTrim) {
		char[] sChars = s.toCharArray();
		String toTrimStr = new String(toTrim);
		StringBuffer sb = new StringBuffer(s);
		
		for (int i = sChars.length-1; i >= 0; i--) {
			if (toTrimStr.indexOf(sChars[i]) > -1) {
				sb.deleteCharAt(i);
			}
			else {
				break;
			}
		}
		
		return sb.toString();
	}

	public static final char[] WHITESPACE_CHARS = 
		new char[] { 
			'\t', '\n', '\f', '\r', ' ', '\u2000', '\u2001', '\u2002', '\u2003', '\u2004', '\u2005', '\u2006', '\u2007', '\u2008', 
			'\u2009', '\u200a', '\u200b', '\u3000', '\ufeff'
		};
	
	public static final String NEW_LINE = System.getProperty("line.separator");
}
