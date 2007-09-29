package org.coury.jfilehelpers.helpers;

public class ConditionHelper {
	public static boolean beginsWith(String line, String s) {
		return line.startsWith(s);
	}
	
	public static boolean endsWith(String line, String s) {
		return line.endsWith(s);
	}
	
	public static boolean contains(String line, String s) {
		return line.indexOf(s) >= 0;
	}
	
	public static boolean enclosed(String line, String s) {
		return line.startsWith(s) && line.endsWith(s);
	}
	
}
