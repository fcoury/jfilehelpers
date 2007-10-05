/*
 * ForwardReader.java
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
package org.coury.jfilehelpers.core;

import java.io.BufferedReader;
import java.io.IOException;

import org.coury.jfilehelpers.helpers.StringHelper;

public class ForwardReader {
	private BufferedReader reader;
	private int forwardIndex = 0;
	private int forwardLines = 0;
	
//	private char[] EOF = StringHelper.NEW_LINE.toCharArray();
//	private int capacityHint = 64;
	
	private int remainingLines = 0;
	private int lineNumber = 0;
	private String[] forwardStrings;
	
	private boolean discardForward = false;

	public ForwardReader(BufferedReader reader) throws IOException {
		this(reader, 0, 0);
	}
	
	public ForwardReader(BufferedReader reader, int forwardLines) throws IOException {
		this(reader, forwardLines, 0);
	}
	
	public ForwardReader(BufferedReader reader, int forwardLines, int startLine) throws IOException {
		this.reader = reader;
		this.forwardLines = forwardLines;
		this.lineNumber = startLine;
		
		this.forwardStrings = new String[forwardLines + 1];
		remainingLines = forwardLines + 1;
		
		for (int i = 0; i < forwardLines + 1; i++) {
			forwardStrings[i] = reader.readLine();
			lineNumber++;
			
			if (forwardStrings[i] == null) {
				remainingLines = i;
				break;
			}
		}
	}
	
	public String readNextLine() throws IOException {
		if (remainingLines <= 0) {
			return null;
		}
		else {
			String res = forwardStrings[forwardIndex];
			
			if (remainingLines == (forwardLines + 1)) {
				forwardStrings[forwardIndex] = reader.readLine();
				lineNumber++;

				if (forwardStrings[forwardIndex] == null) {
					remainingLines--;
				}
			}
			else {
				remainingLines--;
				if (discardForward) {
					return null;
				}
			}
			
			forwardIndex = (forwardIndex + 1) % (forwardLines + 1);
			
			return res;
		}
	}
	
	public String getRemainingText() {
		StringBuffer sb = new StringBuffer(100);
		
		for (int i = 0; i < remainingLines + 1; i++) {
			sb.append(forwardStrings[(forwardIndex + i) % (forwardLines + 1)] + StringHelper.NEW_LINE);
		}

		return sb.toString();		
	}
	
	public void close() {
		try {
			reader.close();
		}
		catch (IOException e) {
		}
	}

	/**
	 * @return the lineNumber
	 */
	public int getLineNumber() {
		return lineNumber - 1;
	}

	/**
	 * @return the discardForward
	 */
	public boolean isDiscardForward() {
		return discardForward;
	}

	/**
	 * @param discardForward the discardForward to set
	 */
	public void setDiscardForward(boolean discardForward) {
		this.discardForward = discardForward;
	}
	
}
