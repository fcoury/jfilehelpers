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

package org.coury.jfilehelpers.samples.fixed;

import java.util.Date;

import org.coury.jfilehelpers.annotations.FieldAlign;
import org.coury.jfilehelpers.annotations.FieldConverter;
import org.coury.jfilehelpers.annotations.FieldFixedLength;
import org.coury.jfilehelpers.annotations.FieldOptional;
import org.coury.jfilehelpers.annotations.FieldTrim;
import org.coury.jfilehelpers.annotations.FixedLengthRecord;
import org.coury.jfilehelpers.enums.AlignMode;
import org.coury.jfilehelpers.enums.ConverterKind;
import org.coury.jfilehelpers.enums.TrimMode;
import org.coury.jfilehelpers.helpers.StringHelper;

@FixedLengthRecord()
public class Customer {
	@FieldFixedLength(4)
	private Integer custId;

	@FieldAlign(alignMode=AlignMode.Right)
	@FieldFixedLength(20)
	private String name;

	@FieldFixedLength(3)
	private Integer rating;

	@FieldTrim(trimMode=TrimMode.Right)
	@FieldFixedLength(10)
	@FieldConverter(converter = ConverterKind.Date, format = "dd-MM-yyyy")
	private Date addedDate;
	
	@FieldFixedLength(3)
	@FieldOptional
	private String stockSimbol;	
	
	@Override
	public String toString() {
		String l = System.getProperty("line.separator");
		StringBuffer b = new StringBuffer();
		b.append("Customer: ").append(l);
		b.append("   custId = " + custId).append(l);
		b.append("   name = " + name).append(l);
		b.append("   rating = " + rating).append(l);
		b.append("   addedDate = " + addedDate).append(l);
		b.append("   stockSimbol = " + stockSimbol).append(l);
		return StringHelper.toStringBuilder(this, b.toString());
	}
}
