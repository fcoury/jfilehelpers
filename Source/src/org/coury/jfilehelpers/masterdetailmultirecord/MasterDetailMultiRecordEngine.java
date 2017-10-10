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
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.coury.jfilehelpers.core.RecordInfo;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.masterdetail.MasterDetailSelector;
import org.coury.jfilehelpers.masterdetail.MasterDetails;
import org.coury.jfilehelpers.masterdetail.RecordAction;

public class MasterDetailMultiRecordEngine {

	private Map<Class<?>[], MasterDetailSelector> externalofmasterdetails;
	private MasterDetails  masterdetailentity;
	private List<MasterDetails> list = new ArrayList<>();

	
	public MasterDetailMultiRecordEngine(Map<Class<?>[], MasterDetailSelector> externalofmasterdetails) {
		this.externalofmasterdetails = externalofmasterdetails;
		
	}
	
	
	public List<MasterDetails<?, ?>>  readFile(String fileName) throws IOException {
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
		
		return null;
	}
	

	
	
	
	private List<MasterDetails<?, ?>> readStream(InputStreamReader fileReader) throws IOException {
		BufferedReader reader = new BufferedReader(fileReader);
		masterdetailentity = null;
		reader.lines().forEach(line -> {

			externalofmasterdetails.forEach((masterdetail, seletor) -> {
				
				LineInfo info = new LineInfo(line);	 
				RecordAction action = seletor.getRecordAction(line);
				
				switch (action) {
					case Master:
						System.out.println(line);
							try {
							masterdetailentity = gerarMaster(masterdetail);
							masterdetailentity.setMaster(new RecordInfo<>(masterdetail[0].newInstance().getClass()).strToRecord(info));
							list.add(masterdetailentity);
							
							} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
							break;
					case Detail:
						try {
							masterdetailentity.getDetails().add(new RecordInfo<>( masterdetail[1].newInstance().getClass() ).strToRecord(info));
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					case Skip:
						break;
					default: 
						break;
					}
					
				
			});

			
		});

		list.forEach(a -> {
			
			System.out.println(a.getMaster() + "  "+ a.getDetails().size());
		});
		
		return null;
	}


	private MasterDetails gerarMaster(Class<?>[] masterdetail) {
		MasterDetails masterdetailentity = new MasterDetails<>();
		try {
			masterdetailentity.setMaster(masterdetail[0].newInstance());
			masterdetailentity.addDetails(new ArrayList<>());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return masterdetailentity;
	}



	private MasterDetails gerarMasterDetails() {
		List details = new ArrayList<>();
		MasterDetails<?, ?> mt = new MasterDetails();
		mt.addDetails(details);
		return mt;
	}


	private MasterDetails gerarDetail(String line, MasterDetails mt) {
		LineInfo info = new LineInfo(line);
		mt.getDetails().add(new RecordInfo<>(mt.getDetail().getClass()).strToRecord(info));
		return mt;
	}


	private  MasterDetails gerarMaster(String line, MasterDetails mt) {
		LineInfo info = new LineInfo(line);

		
		mt.setMaster(new RecordInfo<>(mt.getMaster().getClass()).strToRecord(info));
		return mt;
		
	}



	
	
	
	
}
