/*
 * FileHelperEngine.java
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coury.jfilehelpers.helpers.StringHelper;

public class FileHelperEngine<T> extends EngineBase<T> {
	
	public FileHelperEngine(Class<T> recordClass) {
		super(recordClass);
	}

	public List<T> readFile(String fileName) throws IOException {
		return readFile(fileName, Integer.MAX_VALUE);
	}
	
	public void writeFile(String fileName, List<T> records) throws IOException {
		writeFile(fileName, records, -1);
	}
	
	public void writeFile(String fileName, List<T> records, int maxRecords) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(fileName));
			//fw.write("ABCDEF\n");
			writeStream(fw, records, maxRecords);
		}
		finally {
			if (fw != null) {
				fw.flush();
				fw.close();
			}
		}
	}
	
	private void writeStream(OutputStreamWriter osr, Iterable<T> records, int maxRecords) throws IOException {
		BufferedWriter writer = new BufferedWriter(osr);
		
		try {
			resetFields();
			if (getHeaderText() != null && getHeaderText().length() != 0) {
				if (getHeaderText().endsWith(StringHelper.NEW_LINE)) {
					writer.write(getHeaderText());
				}
				else {
					writer.write(getHeaderText() + StringHelper.NEW_LINE);
				}
			}
			
			int max = maxRecords;
			if (records instanceof Collection) {
				max = Math.min(max < 0 ? Integer.MAX_VALUE : max, ((Collection<T>) records).size());
			}
			
			// TODO progress
			// ProgressHelper.Notify(mNotifyHandler, mProgressMode, 0, max);
			
			String currentLine = null;
			int recIndex = 0;
			boolean first = true;
			
			for (T rec : records) {
				if (recIndex == maxRecords) {
					break;
				}
				
				this.lineNumber++;
				
				try {
					if (rec == null) {
						throw new IllegalArgumentException(
								"The record at index " + recIndex + " is null.");
					}
					
					if (first) {
						first = false;
					}
					
					boolean skip = false;
					// TODO progress and call back
					// ProgressHelper.Notify(mNotifyHandler, mProgressMode, recIndex+1, max);
					// skip = OnBeforeWriteRecord(rec);
					
					if (!skip) {
						currentLine = recordInfo.recordToStr(rec);
						// TODO call back
						// currentLine = OnAfterWriteRecord(currentLine, rec);
						writer.write(currentLine + StringHelper.NEW_LINE);
					}
					
				}
				catch (Exception ex) {
					ex.printStackTrace();
					// TODO error manager
//				switch (mErrorManager.ErrorMode)
//				{
//					case ErrorMode.ThrowException:
//						throw;
//					case ErrorMode.IgnoreAndContinue:
//						break;
//					case ErrorMode.SaveAndContinue:
//						ErrorInfo err = new ErrorInfo();
//						err.mLineNumber = mLineNumber;
//						err.mExceptionInfo = ex;
////						err.mColumnNumber = mColumnNum;
//						err.mRecordString = currentLine;
//						mErrorManager.AddError(err);
//						break;
//				}
				}
			}
		} 
		finally {
			writer.flush();
		}
	}

	public List<T> readFile(String fileName, int maxRecords) throws IOException {
		List<T> tempRes = null;
		
		FileReader fr = null;
		try {
			fr = new FileReader(new File(fileName));
			tempRes = readStream(fr, maxRecords);
		}
		finally {
			if (fr != null) {
				fr.close();
			}
		}
		
		return tempRes;
	}
	
	public List<T> readResource(String resourceName) throws IOException {
		return readResource(resourceName, Integer.MAX_VALUE);
	}
	
	public List<T> readResource(String fileName, int maxRecords) throws IOException {
		List<T> tempRes = null;
		
		
		InputStreamReader fr = null;
		try {
			fr = new InputStreamReader(getClass().getResourceAsStream(fileName));
			tempRes = readStream(fr, maxRecords);
		}
		finally {
			if (fr != null) {
				fr.close();
			}
		}
		
		return tempRes;
	}
	
	public List<T> readStream(InputStreamReader fileReader, int maxRecords) throws IOException {
		BufferedReader reader = new BufferedReader(fileReader);
		
		resetFields();
		setHeaderText("");
		setFooterText("");
		
		int currentRecord = 0;
		
		List<T> resArray = new ArrayList<T>();		
		String currentLine;
		String completeLine;
		
		setLineNumber(1);
		completeLine = reader.readLine();
		currentLine = completeLine;
		
		//ProgressHelper.Notify(mNotifyHandler, mProgressMode, 0, -1);
		
		if (recordInfo.getIgnoreFirst() > 0) {
			for (int i = 0; i < recordInfo.getIgnoreFirst() && currentLine != null; i++) {
				headerText += currentLine + StringHelper.NEW_LINE;
				currentLine = reader.readLine();
				lineNumber++;
			}
		}
		
		// TODO boolean byPass = false;
		
		if (maxRecords < 0) {
			maxRecords = Integer.MAX_VALUE;
		}
		
		LineInfo line = new LineInfo(currentLine);
		line.setReader(reader);

		while (currentLine != null && currentRecord < maxRecords) {
			totalRecords++;
			currentRecord++;
			
			line.reload(currentLine);
			
			boolean skip = false;
			
			//ProgressHelper.Notify(mNotifyHandler, mProgressMode, currentRecord, -1);
			//skip = OnBeforeReadRecord(currentLine);
			
			if (!skip) {
				T record = recordInfo.strToRecord(line);

				// skip = OnAfterReadRecord(currentLine, (T) record);
										
				if (skip == false && record != null) {
					resArray.add(record);
				}
			}
			
			currentLine = reader.readLine();
		}		
		
		return resArray;
	}
	
	
}
