/*
 * MultiRecordEngine.java
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
package org.coury.jfilehelpers.multirecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.coury.jfilehelpers.core.RecordInfo;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.helpers.StringHelper;
public class MultiRecordEngine {

	
	private List<Class<?>> params;
	private Map<Class<?>, String> selector;
	private List<Object> multiRecordList;
	private LineInfo lineInfo;
	
	public MultiRecordEngine(List<Class<?>> params, Map<Class<?>, String> selector) {
		this.params  = params;
		this.setSelector(selector);
		multiRecordList = new ArrayList<>();
	}
	
	
	
	
	
	public void writeFile(String fileName, List<Object> records, int maxRecords) throws IOException {
		FileWriter fw = null;
		try {
			
			fw = new FileWriter(new File(fileName));
			writeStream(fw, records, maxRecords);
		}
		finally {
			if (fw != null) {
				fw.flush();
				fw.close();
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void writeStream(OutputStreamWriter osr, List<Object> records, int maxRecords) throws IOException {
		
		BufferedWriter writer = new BufferedWriter(osr);
			
			records.forEach(record -> {
					
					selector.forEach((classe, selector) -> {
						if(record.getClass().equals(classe) ) {
							RecordInfo recordinfo = new RecordInfo<>(classe);
							try {
								writer.write( recordinfo.recordToStr(recordinfo.convertObjectToGenertics(record)) + StringHelper.NEW_LINE);
							} catch (IllegalArgumentException | IllegalAccessException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					});
				
			});
			writer.flush();

	
	
	}
	
	
	private List<Object> readStream(InputStreamReader fileReader) throws IOException {
		BufferedReader reader = new BufferedReader(fileReader);
		reader.lines().forEach(line -> {
			selector.forEach((classe, seletor) -> {

				if(line.contains(seletor)) {
					lineInfo = new LineInfo(line);
					multiRecordList.add(new RecordInfo<>(classe).strToRecord(lineInfo));
					return;
				}
		});
		
		});
		return multiRecordList;
	}
	
	
	public List<Object> readFile(String fileName) throws IOException {
		FileReader fr = null;
		try {
			fr = new FileReader(new File(fileName));
		readStream(fr);
		}
		finally {
			if (fr != null) {
				fr.close();
			}
		}
		
		return multiRecordList;
	}
	
	
	
	
	
	
	
	
	





	public Map<Class<?>, String> getSelector() {
		return selector;
	}




	public void setSelector(Map<Class<?>, String> selector) {
		this.selector = selector;
	}

	
	
	
	
}
