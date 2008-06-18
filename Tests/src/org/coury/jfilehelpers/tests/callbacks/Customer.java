/*
 * Customer.java
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

import org.coury.jfilehelpers.annotations.DelimitedRecord;
import org.coury.jfilehelpers.engines.EngineBase;
import org.coury.jfilehelpers.interfaces.NotifyRead;
import org.coury.jfilehelpers.interfaces.NotifyWrite;

/**
 * @author Robert Eccardt
 *
 */
@DelimitedRecord(",")
public class Customer implements NotifyWrite<Customer>, NotifyRead<Customer> {
	public Integer custId;
	public String name;
	public Integer rating;
	@Override
	public void beforeWrite(EngineBase<Customer> e) {
		NotifiersTest.engineTester(e);
		NotifiersTest.incrementWriteCount();
	}
	@Override
	public void afterRead(EngineBase<Customer> e, String line) {
		NotifiersTest.engineTester(e);
		NotifiersTest.setNotifyLine(line);
		NotifiersTest.incrementReadCount();
	}
}
