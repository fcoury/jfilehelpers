/*
 * MasterDetailMultiRecordEngine.java
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
package org.coury.jfilehelpers.masterdetailmultirecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.coury.jfilehelpers.core.RecordInfo;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.masterdetail.RecordActionSelector;


import org.coury.jfilehelpers.masterdetail.RecordAction;

public class MasterDetailMultiRecordEngine {

	private MasterDetailMultiRecordFluent fluent;
	private Object master;
	private List<Object> details;
	Map<Object, List<?>> masterDetailMultiRecod;
	
	public MasterDetailMultiRecordEngine(MasterDetailMultiRecordFluent fluent) {
		this.fluent = fluent;
		masterDetailMultiRecod = new LinkedHashMap<>();
		details = new ArrayList<>();
		
	}

	private Map<Object, List<?>> readStream(InputStreamReader fileReader) {
		BufferedReader reader = new BufferedReader(fileReader);
		reader.lines().forEach(line -> {
			RecordAction action = RecordAction.Skip;
			Entry<Class<?>, RecordActionSelector> entry = checkRegisterType(line);
				if(entry != null){
					action = entry.getValue().getRecordAction(line);
				}

			switch (action) {
			case HeaderFile:
				break;
			case HeaderTransaction:
				startNewRegister(line, entry.getKey());
				break;
			case Master:
				processMaster(line, entry.getKey());
				break;
			case Detail:
				processDetail(line, entry.getKey());
				break;
			case TraillerTransaction:
				finallyRegiter(line, entry.getKey());
				break;
			case TraillerFile:
				break;
			case Skip:				
				
			default:
				break;
			}
			
		});
		
		
		masterDetailMultiRecod.forEach((master, details) -> {
			System.out.println( "MASTER:   " + master );
			details.forEach(action -> System.out.println("DETAIL:  " + action));
		});
		
		return masterDetailMultiRecod;
	}

	

	private void finallyRegiter(String line, Class<?> clazz) {
		
		if(master != null && details.size() > 0) {
			masterDetailMultiRecod.put(master, details);
		}
		
		master = null;
		details = new ArrayList<>();
	}

	private void startNewRegister(String line, Class<?> clazz) {

	}

	private void processDetail(String line, Class<?> clazz) {
		try {
			details.add(parseStrToRecord(clazz, line));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void processMaster(String line, Class<?> clazz) {
		try {
			master = parseStrToRecord(clazz, line);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Entry<Class<?>, RecordActionSelector> checkRegisterType(String line) {
		try {
		return	fluent.getMapper()
			  .entrySet()
			  .stream()
			  .filter(action -> action.getValue().getRecordAction(line) != RecordAction.Skip)
			  .findFirst()
			  .get();
		} catch (NoSuchElementException e) {
			return null;
		}
	
	}
	



	public Map<Object, List<?>> readFile(String fileName) throws IOException {
		Map<Object, List<?>> result;
		FileReader fr = null;
		try {
			fr = new FileReader(new File(fileName));
			result = readStream(fr);
		} finally {
			if (fr != null) {
				fr.close();
			}
		}
		return result;
	}
	
	

	public <T> T parseStrToRecord(Class<T> clazz, String text)
			throws InstantiationException, IllegalAccessException {
		return new RecordInfo<T>(clazz).strToRecord(new LineInfo(text));
	}

	public <T> String parseRecordToStr(T record, Class<T> clazz)
			throws IllegalArgumentException, IllegalAccessException {
		return new RecordInfo<T>(clazz).recordToStr(record);
	}

}
