/*
 * MasterDetailTest.java
 *
 * Copyright (C) 2007 Felipe Gon�alves Coury <felipe.coury@gmail.com>
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
package org.coury.jfilehelpers.tests.multirecord;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.coury.jfilehelpers.multirecord.MultiRecordEngine;
import org.coury.jfilehelpers.tests.types.customers.CustomersVerticalBar;
import org.coury.jfilehelpers.tests.types.customers.CustomersVerticalBarVirgula;
import org.coury.jfilehelpers.tests.types.orders.OrdersVerticalBar;

import junit.framework.TestCase;

public class MultiRecordTest extends TestCase {
	private MultiRecordEngine engine;
	private Map<Class<?>, String> selector;
	
	public void testMultiRecordRead() throws IOException {
		selector = new HashMap();
		selector.put(OrdersVerticalBar.class, "|");
		selector.put(CustomersVerticalBarVirgula.class, ";");
		engine = new MultiRecordEngine(new Class<?>[]{OrdersVerticalBar.class, CustomersVerticalBar.class }, selector);
		List<Object> mult = engine.readFile(System.getProperty("user.dir") + "/Resources/test/Good/MultiRecord1.txt");
		mult.forEach(a -> System.out.println(a));

		
	}
}
