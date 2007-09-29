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
