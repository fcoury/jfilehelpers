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
	public Integer custId;

	@FieldAlign(alignMode=AlignMode.Right)
	@FieldFixedLength(20)
	public String name;

	@FieldFixedLength(3)
	public Integer rating;

	@FieldTrim(trimMode=TrimMode.Right)
	@FieldFixedLength(10)
	@FieldConverter(converter = ConverterKind.Date, format = "dd-MM-yyyy")
	public Date addedDate;
	
	@FieldFixedLength(3)
	@FieldOptional
	public String stockSimbol;	
	
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
