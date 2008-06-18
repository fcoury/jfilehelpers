/*
 * TestDelimited.java
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

package org.coury.jfilehelpers.samples.delimited.open;

import java.io.IOException;

import org.coury.jfilehelpers.engines.FileHelperEngine;

public class TestDelimitedIterator {
	public static void main(String[] args) throws IOException {
		FileHelperEngine<Customer> engine = new FileHelperEngine<Customer>(
				Customer.class);
		
		if (args.length < 1) {
			engine.openResource("/samples/customers-delimited.txt");
		} else {
			engine.openFile(args[0]);
		}
		
		int count = 0;
		for (Customer c : engine) {
			if ("Felipe Coury".equals(c.getName())) {
				count++;
			}

		}
		
		engine.close();
		System.out.println(count);
	}
}
