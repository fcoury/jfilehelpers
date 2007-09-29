package org.coury.jfilehelpers.samples.delimited;

import java.util.Date;

import org.coury.jfilehelpers.annotations.DelimitedRecord;
import org.coury.jfilehelpers.annotations.FieldConverter;
import org.coury.jfilehelpers.enums.ConverterKind;
import org.coury.jfilehelpers.helpers.StringHelper;

@DelimitedRecord(",")
public class Customer {
	public Integer custId;
	public String name;
	public Integer rating;
	
	@FieldConverter(converter = ConverterKind.Date, format = "dd-MM-yyyy")
	public Date addedDate;
	
	@Override
	public String toString() {
		String l = System.getProperty("line.separator");
		StringBuffer b = new StringBuffer();
		b.append("Customer: ").append(l);
		b.append("   custId = " + custId).append(l);
		b.append("   name = " + name).append(l);
		b.append("   rating = " + rating).append(l);
		b.append("   addedDate = " + addedDate).append(l);
		return StringHelper.toStringBuilder(this, b.toString());
	}
}
