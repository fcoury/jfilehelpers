/*
 * MasterDetailMultiRecord.java
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
package org.coury.jfilehelpers.tests.masterdetailmultirecord;


import org.coury.jfilehelpers.masterdetail.RecordAction;
import org.coury.jfilehelpers.masterdetail.RecordActionSelector;
import org.coury.jfilehelpers.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import org.coury.jfilehelpers.masterdetailmultirecord.MasterDetailMultiRecordFluentImplement;
import org.coury.jfilehelpers.tests.types.multirecord.LocalizacaoEDocumentacaoHelper;
import org.coury.jfilehelpers.tests.types.multirecord.PseudonimoHelper;
import org.coury.jfilehelpers.tests.types.multirecord.TitularHelper;

import junit.framework.TestCase;

public class MasterDetailMultiRecordTest extends TestCase {

	public void testMasterDetailMultiRecord() throws Exception {

		MasterDetailMultiRecordFluentImplement fluent = new MasterDetailMultiRecordFluentImplement();
		
		fluent
		.addMaster(TitularHelper.class, setSelector("TIT1", RecordAction.Master))
		.addDetail(LocalizacaoEDocumentacaoHelper.class, setSelector("TIT2", RecordAction.Detail))
		.addDetail(PseudonimoHelper.class, setSelector("TIT4", RecordAction.Detail));
		
		MasterDetailMultiRecordEngine engine = new MasterDetailMultiRecordEngine(fluent);
		
		engine.readFile(System.getProperty("user.dir") + "/Resources/test/Good/9QZ0000000001.IMP");
		
	}

	private RecordActionSelector setSelector(String token, RecordAction action) {
		return new RecordActionSelector() {
			@Override
			public RecordAction getRecordAction(String recordString) {
				if(recordString.contains(token))
				return action;
			return RecordAction.Skip;
			}
		};
	}
	
	
}
