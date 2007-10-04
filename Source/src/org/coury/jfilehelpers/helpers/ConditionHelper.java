/*
 * ConditionHelper.java
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

package org.coury.jfilehelpers.helpers;

public class ConditionHelper {
	public static boolean beginsWith(String line, String s) {
		return line.startsWith(s);
	}
	
	public static boolean endsWith(String line, String s) {
		return line.endsWith(s);
	}
	
	public static boolean contains(String line, String s) {
		return line.indexOf(s) >= 0;
	}
	
	public static boolean enclosed(String line, String s) {
		return line.startsWith(s) && line.endsWith(s);
	}
	
}
