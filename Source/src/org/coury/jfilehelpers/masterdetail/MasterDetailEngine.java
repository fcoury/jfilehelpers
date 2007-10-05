/*
 * MasterDetailEngine.java
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
package org.coury.jfilehelpers.masterdetail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.coury.jfilehelpers.core.ForwardReader;
import org.coury.jfilehelpers.core.RecordInfo;
import org.coury.jfilehelpers.engines.EngineBase;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.helpers.StringHelper;

public class MasterDetailEngine<MT, DT> extends EngineBase<DT> {

	@SuppressWarnings("unused")
	private Class<MT> masterRecordClass;
	private RecordInfo<MT> masterInfo;
	private MasterDetailSelector recordSelector;

	public MasterDetailEngine(Class<MT> masterRecordClass, Class<DT> detailRecordClass, MasterDetailSelector recordSelector) {
		super(detailRecordClass);
		this.masterRecordClass = masterRecordClass;
		this.masterInfo = new RecordInfo<MT>(masterRecordClass);
		this.recordSelector = recordSelector;
	}
	
	public MasterDetailEngine(Class<MT> masterRecordClass, Class<DT> detailRecordClass, CommonSelector action, String selector) {
		super(detailRecordClass);
		this.masterInfo = new RecordInfo<MT>(masterRecordClass);
		final CommonInternalSelector sel = new CommonInternalSelector(action, selector, masterInfo.isIgnoreEmptyLines() || recordInfo.isIgnoreEmptyLines());
		
		this.recordSelector = new MasterDetailSelector() {

			@Override
			public RecordAction getRecordAction(String recordString) {
				return sel.getCommonSelectorMethod(recordString);			
			}
			
		};
	}
	
	public List<MasterDetails<MT, DT>> readResource(String fileName) throws IOException {
		List<MasterDetails<MT, DT>> tempRes = null;		
		
		InputStreamReader fr = null;
		try {
			fr = new InputStreamReader(getClass().getResourceAsStream(fileName));
			tempRes = readStream(fr);
		}
		finally {
			if (fr != null) {
				fr.close();
			}
		}
		
		return tempRes;
	}
	
	public List<MasterDetails<MT, DT>> readFile(String fileName) throws IOException {
		List<MasterDetails<MT, DT>> tempRes = null;
		
		FileReader fr = null;
		try {
			fr = new FileReader(new File(fileName));
			tempRes = readStream(fr);
		}
		finally {
			if (fr != null) {
				fr.close();
			}
		}
		
		return tempRes;
	}

	@SuppressWarnings("unchecked")
	private List<MasterDetails<MT, DT>> readStream(InputStreamReader fileReader) throws IOException {
		BufferedReader reader = new BufferedReader(fileReader);

		resetFields();
		setHeaderText("");
		setFooterText("");
		
		List<MasterDetails<MT,DT>> resArray = new ArrayList<MasterDetails<MT,DT>>();
		
		ForwardReader freader = new ForwardReader(reader, masterInfo.getIgnoreLast());
		freader.setDiscardForward(true);

		String currentLine, completeLine;

		lineNumber = 1;

		completeLine = freader.readNextLine();
		currentLine = completeLine;

		// TODO progress
		// ProgressHelper.Notify(mNotifyHandler, mProgressMode, 0, -1);
		
		int currentRecord = 0;

		if (masterInfo.getIgnoreFirst() > 0) {
			for (int i = 0; i < masterInfo.getIgnoreFirst() && currentLine != null; i++) {
				headerText += currentLine + StringHelper.NEW_LINE;
				currentLine = freader.readNextLine();
				lineNumber++;
			}
		}

		boolean byPass = false;
		MasterDetails<MT,DT> record = null;
		
		List<DT> tmpDetails = new ArrayList<DT>();

		LineInfo line = new LineInfo(currentLine);
		line.setReader(freader);
		
		while (currentLine != null) {
			try
			{
				currentRecord++; 

				line.reload(currentLine);
				
				// TODO progress
				// ProgressHelper.Notify(mNotifyHandler, mProgressMode, currentRecord, -1);

				RecordAction action = recordSelector.getRecordAction(currentLine);
				switch (action) {
					case Master:
						if (record != null) {							
							record.addDetails(tmpDetails);
							resArray.add(record);
						}

						totalRecords++;
						record = new MasterDetails<MT, DT>();
						tmpDetails.clear();
						
						MT lastMaster = (MT) masterInfo.strToRecord(line);

						if (lastMaster != null) {
							record.setMaster(lastMaster);
						}

						break;

					case Detail:
						DT lastChild = (DT) recordInfo.strToRecord(line);

						if (lastChild != null) {
							tmpDetails.add(lastChild);
						}
						break;

					default:
						break;
				}
			}
			catch (Exception ex) {
				// TODO error handling
				ex.printStackTrace();
//				switch (mErrorManager.ErrorMode)
//				{
//					case ErrorMode.ThrowException:
//						byPass = true;
//						throw;
//					case ErrorMode.IgnoreAndContinue:
//						break;
//					case ErrorMode.SaveAndContinue:
//						ErrorInfo err = new ErrorInfo();
//						err.mLineNumber = mLineNumber;
//						err.mExceptionInfo = ex;
////						err.mColumnNumber = mColumnNum;
//						err.mRecordString = completeLine;
//
//						mErrorManager.AddError(err);
//						break;
//				}
			}
			finally
			{
				if (byPass == false) {
					currentLine = freader.readNextLine();
					completeLine = currentLine;
					lineNumber = freader.getLineNumber();
				}
			}

		}

		if (record != null) {
			record.addDetails(tmpDetails);
			resArray.add(record);
		}

		if (masterInfo.getIgnoreLast() > 0) {
			footerText = freader.getRemainingText();
		}

		return resArray;		
	}

	class CommonInternalSelector {
		
		private String selector;
		private boolean ignoreEmpty;
		private CommonSelector action;
		
		public CommonInternalSelector(CommonSelector action, String selector, boolean ignoreEmpty) {
			this.action = action;
			this.selector = selector;
			this.ignoreEmpty = ignoreEmpty;
		}
		
		protected RecordAction getCommonSelectorMethod(String recordString) {
			if (ignoreEmpty && recordString.length() < 1) {
				return RecordAction.Skip;
			}

			switch (action) {
			case DetailIfContains:
				if (recordString.indexOf(selector) >= 0)
					return RecordAction.Detail;
				else
					return RecordAction.Master;

			case MasterIfContains:
				if (recordString.indexOf(selector) >= 0)
					return RecordAction.Master;
				else
					return RecordAction.Detail;

			case DetailIfBegins:
				if (recordString.startsWith(selector))
					return RecordAction.Detail;
				else
					return RecordAction.Master;
			
			case MasterIfBegins:
				if (recordString.startsWith(selector))
					return RecordAction.Master;
				else
					return RecordAction.Detail;

			case DetailIfEnds:
				if (recordString.endsWith(selector))
					return RecordAction.Detail;
				else
					return RecordAction.Master;

			case MasterIfEnds:
				if (recordString.endsWith(selector))
					return RecordAction.Master;
				else
					return RecordAction.Detail;

			case DetailIfEnclosed:
				if (recordString.startsWith(selector) && recordString.endsWith(selector))
					return RecordAction.Detail;
				else
					return RecordAction.Master;

			case MasterIfEnclosed:
				if (recordString.startsWith(selector) && recordString.endsWith(selector))
					return RecordAction.Master;
				else
					return RecordAction.Detail;
			}

			return RecordAction.Skip;
		}
		
	}
}
