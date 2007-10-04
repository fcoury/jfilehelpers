/*
 * FieldFactory.java
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

import org.coury.jfilehelpers.annotations.DelimitedRecord;
import org.coury.jfilehelpers.annotations.FieldAlign;
import org.coury.jfilehelpers.annotations.FieldDelimiter;
import org.coury.jfilehelpers.annotations.FieldFixedLength;
import org.coury.jfilehelpers.annotations.FieldIgnored;
import org.coury.jfilehelpers.annotations.FieldOptional;
import org.coury.jfilehelpers.annotations.FieldTrim;
import org.coury.jfilehelpers.annotations.FixedLengthRecord;

public class FieldFactory {
	/**
	 * Creates a field descriptor, given a record class field acquired by reflection
	 * @param fi record class field to generate field information for
	 * @param recordClass the record class
	 * @param someOptional indicates whether some of the fields on the collection are optional
	 * @return a FieldBase instance with informations from the field acquired by reflection 
	 */
	@SuppressWarnings("unchecked")
	public static FieldBase createField(Field fi, Class recordClass, boolean someOptional) {
		if (fi.isAnnotationPresent(FieldIgnored.class)) {
			return null;
		}
		
		FieldBase res = null;

		/*
		// TODO CHECK USAGE ERRORS !!!
		
		if (attributes.Length > 1)
			throw new BadUsageException("The field: " + fi.Name + " has more than one FieldAttribute (left only one or none)");

		if (attributes.Length == 0 && recordAttribute is FixedLengthRecordAttribute)
			throw new BadUsageException("The record class marked with the FixedLengthRecord attribute must include a FixedLength attribute in each field.");

		if (recordAttribute is DelimitedRecordAttribute && fi.IsDefined(typeof (FieldAlignAttribute), true))
			throw new BadUsageException("The AlignAttribute is only valid for fixed length records and are used only for write purpouse.");
		*/

		// checks if field is FieldFixedLength 
		if (fi.isAnnotationPresent(FieldFixedLength.class)) {
			if (recordClass.isAnnotationPresent(DelimitedRecord.class)) {
				throw new IllegalArgumentException(
						"The FieldFixedLength is only for the FixedLength not for the delimited ones.");
			}
			
			FieldFixedLength attb = fi.getAnnotation(FieldFixedLength.class);
			FieldAlign align = fi.getAnnotation(FieldAlign.class);
			
			res = new FixedLengthField(
					fi, attb.value(), FieldAlignBean.createFromAnnotation(align, fi));
		}
		else if (fi.isAnnotationPresent(FieldDelimiter.class)) {
			if (recordClass.isAnnotationPresent(FixedLengthRecord.class)) {
				throw new IllegalArgumentException(
						"The DelimitedAttribute is only for DelimitedRecords not for the fixed ones.");
			}
			
			res = new DelimitedField(fi, fi.getAnnotation(FieldDelimiter.class).value());
		}
		else {
			// default
			DelimitedRecord delimitedAnn = ((DelimitedRecord) recordClass.getAnnotation(DelimitedRecord.class));
			String delimiter = ",";
			if (delimitedAnn != null) {
				delimiter = delimitedAnn.value();
			}
			res = new DelimitedField(fi, delimiter);		
		}
		
		FieldTrim ft = fi.getAnnotation(FieldTrim.class);
		if (ft != null) {
			res.setTrimMode(ft.trimMode());
			res.setTrimChars(ft.trimChars());
		}
		
		res.setOptional(fi.isAnnotationPresent(FieldOptional.class));
		
		
		return res;
	}
}
