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
