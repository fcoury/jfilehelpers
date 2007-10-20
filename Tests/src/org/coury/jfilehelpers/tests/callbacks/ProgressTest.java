/*
 * ProgressTest.java
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

package org.coury.jfilehelpers.tests.callbacks;

import java.io.IOException;

import org.coury.jfilehelpers.enums.ProgressMode;
import org.coury.jfilehelpers.progress.ProgressChangeHandler;
import org.coury.jfilehelpers.progress.ProgressEventArgs;

/**
 * @author Robert Eccardt
 *
 */
public class ProgressTest extends CallbacksBase {
	/*
	 * These tests are not ideal because they rely on knowledge of the behavior of
	 * the FileHelper engine.  We know that the read and the write both notify at the
	 * start and then once for each record. Also, when doing percentage, read is not
	 * invoked at all.  There are 4 records.
	 * 
	 */
	public class ProgressChangeHandlerTester implements ProgressChangeHandler {
		private int invocationCount;
		
		public void handleProgressChange(ProgressEventArgs e) {
			if(e.getProgressMode() == ProgressMode.NotifyRecords) {
				// expected values are 0,1,2,3,4,0,1,2,3,4
				if(invocationCount < 5) {
					assertEquals(e.getProgressCurrent(), invocationCount);
				} else {
					assertEquals(e.getProgressCurrent(), invocationCount - 5);
				}
			}
			if(e.getProgressMode() == ProgressMode.NotifyPercent) {
				// expected values are 0, 25, 50, 75, 100
				assertEquals(e.getProgressCurrent(), invocationCount * 25);
			}
			++invocationCount;
		}

		public int getInvocationCount() {
			return invocationCount;
		}

		public void setInvocationCount(int invocationCount) {
			this.invocationCount = invocationCount;
		}
	}

	ProgressChangeHandlerTester progressChangeHandlerTester = new ProgressChangeHandlerTester();

	public void testNotifyRecords() {
		progressChangeHandlerTester.setInvocationCount(0);
		engine.setProgressHandler(progressChangeHandlerTester, ProgressMode.NotifyRecords);
		try {
			engine.writeFile(customerFile, customers);
			customers = engine.readFile(customerFile);
		} catch (IOException e) {
		}
		assertEquals(progressChangeHandlerTester.getInvocationCount(), 10);
	}
	
	public void testNotifyPercent() {
		progressChangeHandlerTester.setInvocationCount(0);
		engine.setProgressHandler(progressChangeHandlerTester, ProgressMode.NotifyPercent);
		try {
			engine.writeFile(customerFile, customers);
			customers = engine.readFile(customerFile);
		} catch (IOException e) {
		}
		assertEquals(progressChangeHandlerTester.getInvocationCount(), 5);
	}
	
	public void testDontNotify() {
		progressChangeHandlerTester.setInvocationCount(0);
		engine.setProgressHandler(progressChangeHandlerTester, ProgressMode.DontNotify);
		try {
			engine.writeFile(customerFile, customers);
			customers = engine.readFile(customerFile);
		} catch (IOException e) {
		}
		assertEquals(progressChangeHandlerTester.getInvocationCount(), 0);
	}
}
