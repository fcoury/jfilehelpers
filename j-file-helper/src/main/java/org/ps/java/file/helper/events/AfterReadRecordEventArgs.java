/*
 * AfterReadRecord.java
 *
 * Copyright (C) 2007 Felipe Gonï¿½alves Coury <felipe.coury@gmail.com>
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

package org.ps.java.file.helper.events;

/**
 * @author Robert Eccardt
 *
 * @param <T> the data record type
 */
public class AfterReadRecordEventArgs<T> extends ReadRecordEventArgs {
	private T record;
	private boolean skipThisRecord = false;

	public AfterReadRecordEventArgs(String recordLine, T newRecord, int lineNumber) {
		super(recordLine, lineNumber);
		record = newRecord;
	}

	public AfterReadRecordEventArgs(String recordLine, T newRecord) {
		super(recordLine, -1);
		record = newRecord;
	}

	public T getRecord() {
		return record;
	}

	public void setRecord(T record) {
		this.record = record;
	}

	public boolean getSkipThisRecord() {
		return skipThisRecord;
	}

	public void setSkipThisRecord(boolean skipThisRecord) {
		this.skipThisRecord = skipThisRecord;
	}

}
