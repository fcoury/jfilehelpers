/*
 * TestDelimited.java
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

package org.coury.jfilehelpers.samples.delimited;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.coury.jfilehelpers.engines.FileHelperEngine;
import org.coury.jfilehelpers.samples.delimited.Customer;

public class TestDelimited {
	public static void main(String[] args) throws IOException {
		FileHelperEngine<Customer> engine = new FileHelperEngine<Customer>(Customer.class);	
		List<Customer> customers = new ArrayList<Customer>();
		
		if (args.length < 1) {
			customers = engine.readResource("/samples/customers-delimited.txt");
		}
		else {
			customers = engine.readFile(args[0]);
		}
		
		for (Customer c : customers) {
			System.out.println(c);
		}
		
		engine.writeFile("customers-delimited-out.txt", customers);
	}
}
