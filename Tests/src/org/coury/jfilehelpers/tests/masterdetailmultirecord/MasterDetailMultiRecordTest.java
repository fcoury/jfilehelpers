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

import java.util.HashMap;
import java.util.Map;
import org.coury.jfilehelpers.masterdetail.MasterDetailSelector;
import org.coury.jfilehelpers.masterdetail.RecordAction;
import org.coury.jfilehelpers.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import org.coury.jfilehelpers.tests.types.customers.CustomersVerticalBarVirgula;
import org.coury.jfilehelpers.tests.types.multirecord.ObraMusicalHelper;
import org.coury.jfilehelpers.tests.types.multirecord.TitularHelper;
import org.coury.jfilehelpers.tests.types.orders.OrdersVerticalBar;

import junit.framework.TestCase;

public class MasterDetailMultiRecordTest extends TestCase {

	public void testMasterDetailMultiRecord() throws Exception {

		

	MasterDetailSelector selector =	  new MasterDetailSelector() {
		
			@Override
			public RecordAction getRecordAction(String recordString) {
				if (recordString.contains("TIT1")) {
					return RecordAction.Master;
				} else if (recordString.contains("TIT2")) {
					return RecordAction.Detail;
				} else if (recordString.contains("TIT3")) {
					return RecordAction.Detail;
				} else if (recordString.contains("TIT4")) {
					return RecordAction.Detail;
				}
				return RecordAction.Skip;
			}
		};
		
		Map<Class<?>[], MasterDetailSelector> masterDetail = new HashMap<>();
		
		masterDetail.put(new Class<?>[] {TitularHelper.class, 
										 ObraMusicalHelper.class}, selector);
		
		
		
		

		 MasterDetailSelector selector2 = new MasterDetailSelector() {
			@Override
			public RecordAction getRecordAction(String recordString) {
				if (recordString.contains(";")) {
					return RecordAction.Master;
				} else if (recordString.contains("|")) {
					return RecordAction.Detail;
				}
				return RecordAction.Skip;
			}
		};
		
		
		
		masterDetail.put(new Class<?>[] {CustomersVerticalBarVirgula.class, 
										 OrdersVerticalBar.class}, selector2);

		
	
		MasterDetailMultiRecordEngine engine = new MasterDetailMultiRecordEngine(masterDetail);

		engine.readFile(System.getProperty("user.dir") + "/Resources/test/Good/9QZ0000000001.IMP");

	}

}
