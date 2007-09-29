package org.coury.jfilehelpers.engines;

import org.coury.jfilehelpers.core.RecordInfo;
import org.coury.jfilehelpers.enums.ProgressMode;

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
	// TODO Enable those fields
	/*
	private ErrorManager errorManager;
	protected ProgressChangeHandler notifyHandler = null;
	
	private void setProgressHandler(ProgressChangeHandler handler) {
		setProgressHandler(handler, ProgressMode.NOTIFY_RECORDS);
	}
	
	private void setProgressHandler(ProgressChangeHandler handler, ProgressMode mode) {
		this.notifyHandler = handler;
		
		if (mode == ProgressMode.NOTIFY_BYTES) {
			throw new NotImplementedException("Not implemented yet. Planed for version 1.5.0")
		}
		
		this.progressMode = mode;
	}
	*/
	
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
