package org.coury.jfilehelpers.core;

import java.util.Arrays;

import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.helpers.StringHelper;

public final class ExtractedInfo {
	public static final ExtractedInfo Empty = new ExtractedInfo("");

	private LineInfo line;
	private int extractedFrom;
	private int extractedTo;
	private String customExtractedString = null;

	public ExtractedInfo(LineInfo line) {
		this.line = line;
		this.extractedFrom = line.getCurrentPos();
		this.extractedTo = line.getLine().length - 1;
	}
	
	public ExtractedInfo(LineInfo line, int extractTo) {
		this.line = line;
		this.extractedFrom = line.getCurrentPos();
		this.extractedTo = extractTo - 1;
	}
	
	public ExtractedInfo(String customExtract) {
		customExtractedString = customExtract;
	}
	
	public void trimStart(char[] sortedToTrim) {
		if (customExtractedString != null)
			// customExtractedString = customExtractedString.trimStart(sortedToTrim);
			customExtractedString = StringHelper.trimStart(customExtractedString, sortedToTrim);
		else
			while (extractedFrom < extractedTo && Arrays.binarySearch(sortedToTrim, line.getLine()[extractedFrom]) >= 0)
				extractedFrom++;
	}

	public void TrimEnd(char[] sortedToTrim) {
		if (customExtractedString != null)
			//customExtractedString = customExtractedString.trimEnd(sortedToTrim);
			customExtractedString = StringHelper.trimEnd(customExtractedString, sortedToTrim);
		else
			while(extractedTo > extractedFrom && Arrays.binarySearch(sortedToTrim, line.getLine()[extractedTo]) >= 0)
				extractedTo--;
	}

	public void trimBoth(char[] sortedToTrim) {
		if (customExtractedString != null) {
			//customExtractedString = mCustomExtractedString.Trim(sortedToTrim);
			customExtractedString = StringHelper.trimBoth(customExtractedString, sortedToTrim);
		}
		else {
			while (extractedFrom <= extractedTo && Arrays.binarySearch(sortedToTrim, line.getLine()[extractedFrom]) >= 0) {
				extractedFrom++;
			}
		
			while (extractedTo > extractedFrom && Arrays.binarySearch(sortedToTrim, line.getLine()[extractedTo]) >= 0) {
				extractedTo--;
			}
		}
	}

	public boolean hasOnlyThisChars(char[] sortedArray) {
		// Check if the chars at pos or right are empty ones
		if (customExtractedString != null) {
			int pos = 0;
			while (pos <  customExtractedString.length() && 
					Arrays.binarySearch(sortedArray, customExtractedString.charAt(pos)) >= 0) {
				pos++;
			}
		
			return pos == customExtractedString.length();
		}
		else {
			int pos = extractedFrom;
			while (pos <= extractedTo && Arrays.binarySearch(sortedArray, line.getLine()[pos]) >= 0) {
				pos++;
			}
		
			return pos > extractedTo;
		}
	}

	public int length() {
		return extractedTo - extractedFrom + 1;
	}
	
	public String extractedString() {
		if (customExtractedString == null) {
			return new String(line.getLine(), extractedFrom, extractedTo - extractedFrom + 1);
		}
		else {
			return customExtractedString;
		}
	}

	public LineInfo getLine() {
		return line;
	}

	public void setLine(LineInfo line) {
		this.line = line;
	}

	public int getExtractedFrom() {
		return extractedFrom;
	}

	public void setExtractedFrom(int extractedFrom) {
		this.extractedFrom = extractedFrom;
	}

	public int getExtractedTo() {
		return extractedTo;
	}

	public void setExtractedTo(int extractedTo) {
		this.extractedTo = extractedTo;
	}

	public String getCustomExtractedString() {
		return customExtractedString;
	}

	public void setCustomExtractedString(String customExtractedString) {
		this.customExtractedString = customExtractedString;
	}

	@Override
	public String toString() {
		return StringHelper.toStringBuilder(this);
	}
}
