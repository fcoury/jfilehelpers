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
import java.util.List;
import java.util.Map;

import org.coury.jfilehelpers.masterdetail.MasterDetailSelector;
import org.coury.jfilehelpers.masterdetail.MasterDetails;
import org.coury.jfilehelpers.masterdetail.RecordAction;

public class MasterDetailMultiRecordEngine {

	private List<MasterDetails<?,?>> internallistofmasterdetails;
	private List<MasterDetails<?,?>> externalofmasterdetails;
	private Map<MasterDetails<?, ?>, MasterDetailSelector> selectors;
	
	public MasterDetailMultiRecordEngine(List<MasterDetails<?,?>> externalofmasterdetails, Map<MasterDetails<?, ?>, MasterDetailSelector> selectors) {
		internallistofmasterdetails = new ArrayList<>();
		this.externalofmasterdetails = externalofmasterdetails;
		this.selectors = selectors;
		
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
		
		return internallistofmasterdetails;
	}
	

	
	
	
	private List<MasterDetails<?, ?>> readStream(InputStreamReader fileReader) throws IOException {
		BufferedReader reader = new BufferedReader(fileReader);
		reader.lines().forEach(line -> {
			selectors.forEach((inForMasterdetail, inForSelector) -> {
					externalofmasterdetails.forEach(action ->{
							if( inForMasterdetail == action ) {
										RecordAction tes = inForSelector.getRecordAction(line);
										switch (tes) {
										case Master:
											System.out.println("MASTER: " +  line);
											return;
										case Detail:
											System.out.println("DETAIL: " +  line);
											return;
										case Skip:
											return;
										default:
											break;
										}
							}
					});
				
			});
			
		});
		
		
	return internallistofmasterdetails;
	}



	public List<MasterDetails<?, ?>> getInternallistofmasterdetails() {
		return internallistofmasterdetails;
	}



	public void setInternallistofmasterdetails(List<MasterDetails<?, ?>> internallistofmasterdetails) {
		this.internallistofmasterdetails = internallistofmasterdetails;
	}
	
	
	
	
	
	
}
