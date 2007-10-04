/*
 * Common.java
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

package org.coury.jfilehelpers.tests.common;

import java.io.IOException;
import java.util.Date;

import org.coury.jfilehelpers.engines.FileHelperEngine;

public class Common {
	public static Object readTest(FileHelperEngine<? extends Object> engine, String fileName) throws IOException {
		return engine.readResource("/test/" + fileName);
	}

	public static boolean assertSameDate(Date d1, Date d2) {
		return d1.equals(d2);
	}
}
