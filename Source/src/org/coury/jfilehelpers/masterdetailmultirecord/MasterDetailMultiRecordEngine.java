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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.coury.jfilehelpers.core.RecordInfo;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.masterdetail.RecordActionSelector;
import org.coury.jfilehelpers.masterdetail.RecordAction;

public class MasterDetailMultiRecordEngine {

	private MasterDetailMultiRecordFluentImplement fluent;
	

	
	public MasterDetailMultiRecordEngine(MasterDetailMultiRecordFluentImplement fluent) {
		this.fluent = fluent;
		
	}

	private List<MasterDetailMultiRecord> readStream(InputStreamReader fileReader) {
		BufferedReader reader = new BufferedReader(fileReader);
		reader.lines().forEach(line -> {
			
			RecordAction action = checkRegisterType(line);
			System.out.println(action);
			switch (action) {
			case HeaderFile:
				break;
			case HeaderTransaction:
				startNewRegister(line);
				break;
			case Master:
				processMaster(line);
				break;
			case Detail:
				processDetail(line);
				break;
			case TraillerTransaction:
				finallyRegiter(line);
				break;
			case TraillerFile:
				break;
			case Skip:				
				
			default:
				break;
			}
			
		});
		return null;
	}

	

	private void finallyRegiter(String line) {
		// TODO Auto-generated method stub
		
	}

	private void startNewRegister(String line) {
	}

	private void processDetail(String line) {
	
	}

	private void processMaster(String line) {

	}

	private RecordAction checkRegisterType(String line) {
	   fluent.getMapper().values().forEach(action -> {
		   	checkLine(action, line);
	   });
	return RecordAction.Skip;
	}
	

	private void checkLine(RecordActionSelector action, String line) {
		System.out.println(action.getRecordAction(line));
	}
	
	

	public List<MasterDetailMultiRecord> readFile(String fileName) throws IOException {
		List<MasterDetailMultiRecord> result;
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
	
	

	public Object parseStrToRecord(Class<? extends Serializable> clazz, String text)
			throws InstantiationException, IllegalAccessException {
		return new RecordInfo<>(clazz).strToRecord(new LineInfo(text));
	}

	public <T extends Serializable> String parseRecordToStr(T record, Class<T> clazz)
			throws IllegalArgumentException, IllegalAccessException {
		return new RecordInfo<T>(clazz).recordToStr(record);
	}

}
