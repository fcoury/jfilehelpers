package org.coury.jfilehelpers.engines;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.coury.jfilehelpers.helpers.StringHelper;

public class FileHelperEngine<T> extends EngineBase<T> {
	
	public FileHelperEngine(Class<T> recordClass) {
		super(recordClass);
	}

	public List<T> readFile(String fileName) throws IOException {
		return readFile(fileName, Integer.MAX_VALUE);
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
