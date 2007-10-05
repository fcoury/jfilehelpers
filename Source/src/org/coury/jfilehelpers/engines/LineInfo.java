/*
 * LineInfo.java
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

package org.coury.jfilehelpers.engines;

import java.io.IOException;
import java.util.Arrays;

import org.coury.jfilehelpers.core.ForwardReader;
import org.coury.jfilehelpers.helpers.StringHelper;

public final class LineInfo {
	private static char[] emptyChars = new char[] {};
	
	private String lineStr;
	private char[] line;
	private int currentPos = 0;
	private int lineNumber;
	
	private ForwardReader reader;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("currentLine = [").append(this.getLineStr()).append("]").append(StringHelper.NEW_LINE);
		sb.append("   -> currentString = [").append(this.getCurrentString()).append("]").append(StringHelper.NEW_LINE);
		sb.append("   -> currentLength = ").append(this.getCurrentLength()).append(StringHelper.NEW_LINE);
		sb.append("   -> isEol = ").append(this.isEol()).append(StringHelper.NEW_LINE);
		sb.append("   -> isEmptyFromPos = ").append(this.isEmptyFromPos()).append(StringHelper.NEW_LINE);
		
		return sb.toString();
		//+ StringHelper.toStringBuilder(this);
	}
	
	public LineInfo(String line) {
		this.lineStr = line;
		this.line = line == null ? emptyChars : line.toCharArray();
		this.currentPos = 0;
	}
	
	public String currentString() {
		return new String(line, currentPos, line.length - currentPos);
	}
	
	public int getCurrentLength() {
		return line.length - currentPos;
	}
	
	public boolean isEol() {
		return currentPos >= line.length;
	}
	
	public boolean isEmptyFromPos() {
		int length = line.length;
		int pos = currentPos;
		
		while (pos < length && Arrays.binarySearch(StringHelper.WHITESPACE_CHARS, line[pos]) > 0) {
			pos++;
		}
		
		return pos > length;
	}
	
	public void trimStart() {
		trimStartSorted(StringHelper.WHITESPACE_CHARS);
	}
	
	public void trimStart(char[] toTrim) {
		Arrays.sort(toTrim);
		trimStartSorted(toTrim);
	}
	
	private void trimStartSorted(char[] toTrim) {
		// Move the pointer to the first non to Trim char
		int length = line.length;
		
		while(currentPos < length && Arrays.binarySearch(toTrim, line[currentPos]) >= 0) {
			currentPos++;
		}		
	}

	public boolean startsWith(String str) {
		// Returns true if the string begin with str
		if (currentPos >= lineStr.length())
			return false;
		else
			return lineStr.substring(currentPos, str.length()).equalsIgnoreCase(str);
			//return Arrays.equals(mLineStr, mCurrentPos, str.Length, str, 0, str.Length, CompareOptions.IgnoreCase) == 0;
	}
	
	public boolean startsWithTrim(String str) {
		int length = line.length;
		int pos = currentPos;
		
		while (pos < length && Arrays.binarySearch(StringHelper.WHITESPACE_CHARS, line[pos]) > 0) {
			pos++;
		}
		
		return lineStr.substring(pos, str.length()).equalsIgnoreCase(str);
	}
	
//	public String readLine() throws IOException {
//		String str = reader.readNextLine();
//		lineNumber++;
//		return str;
//	}
	
	public void readNextLine() throws IOException {
		lineStr = reader.readNextLine();
		line = lineStr.toCharArray();
		
		currentPos = 0;
	}
	
	public String getCurrentString() {
		return new String(line, currentPos, line.length - currentPos);
	}

	public void setReader(ForwardReader reader) {
		this.reader = reader;
	}
	
	public int indexOf(String toFind) {
		return lineStr.substring(currentPos).toUpperCase().indexOf(toFind.toUpperCase()) + currentPos;
	}
	
	public void reload(String line) {
		this.line = line == null ? emptyChars : line.toCharArray();
		this.lineStr = line;
		this.currentPos = 0;
	}

	public String getLineStr() {
		return lineStr;
	}

	public void setLineStr(String lineStr) {
		this.lineStr = lineStr;
	}

	public char[] getLine() {
		return line;
	}

	public void setLine(char[] line) {
		this.line = line;
	}

	public int getCurrentPos() {
		return currentPos;
	}

	public void setCurrentPos(int currentPos) {
		this.currentPos = currentPos;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * @return the reader
	 */
	public ForwardReader getReader() {
		return reader;
	}
}
