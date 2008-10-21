/*
 * MasterDetailsSample.java
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
package org.coury.jfilehelpers.samples.masterdetail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.coury.jfilehelpers.annotations.DelimitedRecord;
import org.coury.jfilehelpers.masterdetail.MasterDetailEngine;
import org.coury.jfilehelpers.masterdetail.MasterDetailSelector;
import org.coury.jfilehelpers.masterdetail.MasterDetails;
import org.coury.jfilehelpers.masterdetail.RecordAction;

public class MasterDetailsSample {
	public static void main(String[] args) throws IOException {
		MasterDetailEngine<Order, Item> engine = new MasterDetailEngine<Order, Item>(Order.class, Item.class, new MasterDetailSelector() {

			@Override
			public RecordAction getRecordAction(String recordString) {
				if (recordString.startsWith("-")) {
					return RecordAction.Detail;
				}
				else {
					return RecordAction.Master;
				}
			}
			
		});
		
		String teste = 
			"1,4039BF,1130.45" + "\n" +
			"-1,teste,12,120.01" + "\n" +
			"2,302512,1320.11" + "\n" +
			"-2,awesome,14,130.01";
		
		List<MasterDetails<Order, Item>> orders = engine.fromString(teste);
		for (MasterDetails<Order, Item> md : orders) {
			Order order = md.getMaster();
			System.out.println("Order: " + order.id + " - " + order.orderNo);
			for (Item item : md.getDetails()) {
				System.out.println("   - " + (item.id*-1) + " - " + item.description);
			}
		}
		
		engine.writeFile("c:/temp/out.txt", orders);

		MasterDetails<Order, Item> masterDetail = new MasterDetails<Order, Item>();

		Order sampleOrder = new Order(1, "1234ORD", 1032.11);
		List<Item> items = new ArrayList<Item>();
		items.add(new Item(-2, "Teste 123", 2, 100.00));
		items.add(new Item(-3, "Teste 456", 2, 200.00));
		
		masterDetail.setMaster(sampleOrder);
		masterDetail.addDetails(items);
		
		// before the addition of my new method
		List<MasterDetails<Order, Item>> mdList = new ArrayList<MasterDetails<Order, Item>>();
		mdList.add(masterDetail);
		
		engine.writeFile("c:/temp/out2.txt", mdList);
		
		// after the additions
		engine.writeFile("c:/temp/out3.txt", masterDetail);		
	}
	
	@DelimitedRecord(",")
	public static class Order {
		public int id;
		public String orderNo;
		public double total;
		
		public Order() {
		}
		
		public Order(int id, String orderNo, double total) {
			super();
			this.id = id;
			this.orderNo = orderNo;
			this.total = total;
//			this.items = items;
		}
	}
	
	@DelimitedRecord(",")
	public static class Item {
		public int id;
		public String description;
		public int qtd;
		public double price;
		
		public Item() {
		}
		
		public Item(int id, String description, int qtd, double price) {
			super();
			this.description = description;
			this.id = id;
			this.qtd = qtd;
			this.price = price;
		}
		
		
	}
}
