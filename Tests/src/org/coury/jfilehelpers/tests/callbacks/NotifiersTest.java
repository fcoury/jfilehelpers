/*
 * NotifiersTest.java
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

package org.coury.jfilehelpers.tests.callbacks;

import java.io.IOException;

/**
 * @author Robert Eccardt
 *
 */
public class NotifiersTest extends CallbacksBase {
	public void testNotifiers() {
		try {
			engine.writeFile(customerFile, customers);
			customers = engine.readFile(customerFile);
		} catch (IOException e) {
		}
		assertEquals(readCount,4);
		assertEquals(writeCount,4);
		assertEquals(notifyLine,"4,Homer Simpson,4");
		assertEquals(customers.size(),4);
	}
}
