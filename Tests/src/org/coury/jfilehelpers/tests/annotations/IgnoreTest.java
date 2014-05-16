/*
 * IgnoreTest.java
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
package org.coury.jfilehelpers.tests.annotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.coury.jfilehelpers.engines.FileHelperEngine;
import org.coury.jfilehelpers.tests.common.Common;
import org.coury.jfilehelpers.tests.converters.testobjects.SampleCustomer;
import org.coury.jfilehelpers.tests.converters.testobjects.SampleCustomer2;
import org.coury.jfilehelpers.tests.converters.testobjects.SampleCustomer3;

public class IgnoreTest extends TestCase {
	public void testIgnoreBoth() throws IOException {
		FileHelperEngine<SampleCustomer> engine = new FileHelperEngine<SampleCustomer>(SampleCustomer.class);

		@SuppressWarnings("unchecked")
		List<SampleCustomer> res =
			(ArrayList<SampleCustomer>) Common.readTest(engine, "Data/customers.txt");

		assertEquals(2, res.size());

		assertEquals("Felipe Coury", res.get(0).name);
		assertEquals("12", res.get(0).age);

		assertEquals("Ana Maria", res.get(1).name);
		assertEquals("8", res.get(1).age);
	}

	public void testIgnoreLast() throws IOException {
		FileHelperEngine<SampleCustomer2> engine = new FileHelperEngine<SampleCustomer2>(SampleCustomer2.class);

		@SuppressWarnings("unchecked")
		List<SampleCustomer2> res =
			(ArrayList<SampleCustomer2>) Common.readTest(engine, "Data/customers2.txt");

		assertEquals(2, res.size());

		assertEquals("Felipe Coury", res.get(0).name);
		assertEquals("12", res.get(0).age);

		assertEquals("Ana Maria", res.get(1).name);
		assertEquals("8", res.get(1).age);
	}


	public void testIgnoreFirst() throws IOException {
		FileHelperEngine<SampleCustomer3> engine = new FileHelperEngine<SampleCustomer3>(SampleCustomer3.class);

		@SuppressWarnings("unchecked")
		List<SampleCustomer3> res =
			(ArrayList<SampleCustomer3>) Common.readTest(engine, "Data/customers3.txt");

		assertEquals(2, res.size());

		assertEquals("Felipe Coury", res.get(0).name);
		assertEquals("12", res.get(0).age);

		assertEquals("Ana Maria", res.get(1).name);
		assertEquals("8", res.get(1).age);
	}
	
	public void testIgnoreCommentedLines() throws IOException {
		FileHelperEngine<SampleCustomer> engine = new FileHelperEngine<SampleCustomer>(SampleCustomer.class);
		
		@SuppressWarnings("unchecked")
		List<SampleCustomer> res =
			(ArrayList<SampleCustomer>) Common.readTest(engine, "Data/customers-comments.txt");

		assertEquals(3, res.size());

		assertEquals("Felipe Coury", res.get(0).name);
		assertEquals("12", res.get(0).age);

		assertEquals("Ana Maria", res.get(1).name);
		assertEquals("8", res.get(1).age);

		assertEquals("Anderson Polga", res.get(2).name);
		assertEquals("32", res.get(2).age);
	}
}
