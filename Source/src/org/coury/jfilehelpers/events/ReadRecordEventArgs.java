/*
 * BeforeReadRecord.java
 *
 * Copyright (C) 2007 Felipe Gon�alves Coury <felipe.coury@gmail.com>
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

package org.coury.jfilehelpers.events;

/**
 * @author Robert Eccardt
 *
 */
public abstract class ReadRecordEventArgs {
	private int lineNumber;
	private String recordLine;
	boolean recordLineChanged = false;

	public ReadRecordEventArgs(String recordLine, int lineNumber) {
		this.recordLine = recordLine;
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getRecordLine() {
		return recordLine;
	}

	public void setRecordLine(String recordLine) {
		this.recordLine = recordLine;
		recordLineChanged = true;
	}

	public boolean getRecordLineChanged() {
		return recordLineChanged;
	}

}
