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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.coury.jfilehelpers.multirecord.MultiRecordEngine;
import org.coury.jfilehelpers.tests.types.customers.CustomersVerticalBarVirgula;
import org.coury.jfilehelpers.tests.types.multirecord.ObraMusicalHelper;
import org.coury.jfilehelpers.tests.types.multirecord.TitularHelper;
import org.coury.jfilehelpers.tests.types.orders.OrdersVerticalBar;

import junit.framework.TestCase;

public class MultiRecordTest extends TestCase {
	private MultiRecordEngine engine;
	private Map<Class<?>, String> selector;

	public void testMultiRecordRead() throws IOException {

		
		List<Class<?>> multirecod = new ArrayList<>();
		multirecod.add(TitularHelper.class);
		multirecod.add(ObraMusicalHelper.class);
		
				
		selector = new HashMap<>();
		selector.put(ObraMusicalHelper.class, "OBM1");
		selector.put(TitularHelper.class, "TIT1");
		
		engine = new MultiRecordEngine(multirecod, selector);
		
		List<Object> mult = engine.readFile(System.getProperty("user.dir") + "/Resources/test/Good/9QZ0000000001.IMP");

		mult.forEach(a -> {
			if(a instanceof ObraMusicalHelper) {
				System.out.println("OBRA: " + a);
			}else if(a instanceof TitularHelper) {
				System.out.println("Titular: " + a);
			}

		});
		
		
		
		
		CustomersVerticalBarVirgula cusmer1 = new CustomersVerticalBarVirgula();
		cusmer1.address = "adress01";
		cusmer1.city = "city01";
		cusmer1.companyName = "companyName01";
		cusmer1.contactName = "contactName01";
		cusmer1.contactTitle = "contactTitle01";
		cusmer1.country = "country01";
		cusmer1.customerID = "customerID01";
		
		OrdersVerticalBar  orders1 = new OrdersVerticalBar();
				orders1.customerID = "customerID01";
				orders1.employeeID =  1;
				orders1.freight = 2;
				orders1.orderDate = new Date();
				orders1.orderID = 1;
				orders1.requiredDate = new Date();
				orders1.shippedDate = new Date();
				orders1.shipVia = 0;
						

				
				CustomersVerticalBarVirgula cusmer2 = new CustomersVerticalBarVirgula();
				cusmer2.address = "adress02";
				cusmer2.city = "city02";
				cusmer2.companyName = "companyName02";
				cusmer2.contactName = "contactName02";
				cusmer2.contactTitle = "contactTitle02";
				cusmer2.country = "country02";
				cusmer2.customerID = "customerID02";
				
				OrdersVerticalBar  orders2 = new OrdersVerticalBar();
				orders2.customerID = "customerID02";
				orders2.employeeID =  2;
				orders2.freight = 3;
				orders2.orderDate = new Date();
				orders2.orderID = 2;
				orders2.requiredDate = new Date();
				orders2.shippedDate = new Date();
				orders2.shipVia = 2;
								
						CustomersVerticalBarVirgula cusmer3 = new CustomersVerticalBarVirgula();
						cusmer3.address = "adress03";
						cusmer3.city = "city03";
						cusmer3.companyName = "companyName03";
						cusmer3.contactName = "contactName03";
						cusmer3.contactTitle = "contactTitle03";
						cusmer3.country = "country03";
						cusmer3.customerID = "customerID03";
						
						OrdersVerticalBar  orders3 = new OrdersVerticalBar();
						orders3.customerID = "customerID03";
						orders3.employeeID =  3;
						orders3.freight = 33;
						orders3.orderDate = new Date();
						orders3.orderID = 3;
						orders3.requiredDate = new Date();
						orders3.shippedDate = new Date();
						orders3.shipVia = 3;
						
						
		List<Object> list = new ArrayList<>();
		
		
		list.add(cusmer1);
		list.add(cusmer2);
		list.add(cusmer3);
		list.add(orders1);
		list.add(orders2);
		list.add(orders3);
		
		engine.writeFile(System.getProperty("user.dir") + "/Resources/test/Good/MultiRecord2.txt", list, 0);

		
	}
}
