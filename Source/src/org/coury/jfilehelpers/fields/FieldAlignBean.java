/*
 * FieldAlignBean.java
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

package org.coury.jfilehelpers.fields;

import java.lang.reflect.Field;

import org.coury.jfilehelpers.annotations.FieldAlign;
import org.coury.jfilehelpers.enums.AlignMode;

public class FieldAlignBean {
	private AlignMode mode = AlignMode.Left;
	private char alignChar = ' ';

	private FieldAlignBean() {
	}
	
	public static FieldAlignBean createFromAnnotation(FieldAlign ann, Field fi) {
		FieldAlignBean fab = new FieldAlignBean();
		if (ann != null) {
			fab.mode = ann.alignMode();
			fab.alignChar = ann.alignChar();			
		}
		else {
			if (fi.getType() == Integer.class || fi.getType() == Float.class || fi.getType() == Double.class) {
				fab.mode = AlignMode.Right;
			}
		}
		return fab;
	}
	
	public AlignMode getAlign() {
		return mode;
	}

	public char getChar() {
		return alignChar;
	}
}
