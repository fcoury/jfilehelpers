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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.coury.jfilehelpers.core.ForwardReader;
import org.coury.jfilehelpers.core.RecordInfo;
import org.coury.jfilehelpers.engines.LineInfo;
public class MultiRecordEngine {

	
	private Class<?>[] params;
	private Map<Class<?>, String> selector;
	private List<Object> multiRecordList;
	private LineInfo lineInfo;
	private String line = null;

	
	public MultiRecordEngine(Class<?>[] params, Map<Class<?>, String> selector) {
		this.setParams(params);
		this.setSelector(selector);
		multiRecordList = new ArrayList<>();
	}
	
	
	
	private List<Object> readStream(InputStreamReader fileReader) throws IOException {
		BufferedReader reader = new BufferedReader(fileReader);
		ForwardReader forwardReader = new ForwardReader(reader);
		while((line = forwardReader.readNextLine()) != null) {
			
			selector.forEach((classe, seletor) -> {
			
				if(line.contains(seletor)) {
					lineInfo = new LineInfo(line);
					multiRecordList.add(new RecordInfo<>(classe).strToRecord(lineInfo));
					System.out.println(multiRecordList.size());
					return;
				}
			});
			
		}
		return multiRecordList;
	}
	
	
	public List<Object>  readFile(String fileName) throws IOException {
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
	
	
	
	
	
	
	
	
	

	public Class<?>[] getParams() {
		return params;
	}

	public void setParams(Class<?>[] params) {
		this.params = params;
	}




	public Map<Class<?>, String> getSelector() {
		return selector;
	}




	public void setSelector(Map<Class<?>, String> selector) {
		this.selector = selector;
	}

	
	
	
	
}
