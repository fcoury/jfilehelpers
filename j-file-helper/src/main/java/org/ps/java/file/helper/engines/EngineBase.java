/*
 * EngineBase.java
 *
 * Copyright (C) 2007 Felipe GonÃ¯Â¿Â½alves Coury <felipe.coury@gmail.com>
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

package org.ps.java.file.helper.engines;

import org.ps.java.file.helper.core.RecordInfo;
import org.ps.java.file.helper.enums.ProgressMode;
import org.ps.java.file.helper.progress.ProgressChangeHandler;

public abstract class EngineBase<T> {
	protected RecordInfo<T> recordInfo;
	protected Class<T> recordClass;
	protected Encoding encoding;	
	protected String footerText;
	protected String headerText;
	protected int lineNumber;
	protected int totalRecords;
	
	public EngineBase(Class<T> recordClass) {
		this.recordClass = recordClass;
		this.recordInfo = new RecordInfo<T>(recordClass);
	}
	
	protected ProgressMode progressMode = ProgressMode.DontNotify;
	// TODO Enable this field
	/*
	private ErrorManager errorManager;
	*/
	protected ProgressChangeHandler notifyHandler = null;
	
	public void setProgressHandler(ProgressChangeHandler handler) {
		setProgressHandler(handler, ProgressMode.NotifyRecords);
	}
	
	public void setProgressHandler(ProgressChangeHandler handler, ProgressMode mode) {
		this.notifyHandler = handler;
		
		if (mode == ProgressMode.NotifyBytes) {
			throw new UnsupportedOperationException("Not implemented yet. Planned for version 1.5.0");
		}
		
		this.progressMode = mode;
	}
	
	protected void resetFields() {
		this.lineNumber = 0;
		//this.errorManager.clearErrors();
		this.totalRecords = 0;
	}
	
	public Encoding getEncoding() {
		return encoding;
	}
	public void setEncoding(Encoding encoding) {
		this.encoding = encoding;
	}
	public String getFooterText() {
		return footerText;
	}
	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}
	public String getHeaderText() {
		return headerText;
	}
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
//	public ErrorManager getErrorManager() {
//		return errorManager;
//	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	
}
