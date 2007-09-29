package org.coury.jfilehelpers.converters;

public abstract class ConverterBase {
	private static String defaultDateTimeFormat = "ddMMyyyy";

	public abstract Object stringToField(String from);
	
	public String fieldToString(Object from) {
		if (from == null) {
			return "";
		}
		else {
			return from.toString();
		}
	}
	
	public boolean isCustomNullHandling() {
		return false;
	}
	
	public static String getDefaultDateTimeFormat() {
		return defaultDateTimeFormat;
	}

	public static void setDefaultDateTimeFormat(String defaultDateTimeFormat) {
		ConverterBase.defaultDateTimeFormat = defaultDateTimeFormat;
	}
	
}
