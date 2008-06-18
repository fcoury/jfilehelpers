package org.coury.jfilehelpers.samples.fixed.lesschars;

import org.coury.jfilehelpers.annotations.FieldFixedLength;
import org.coury.jfilehelpers.annotations.FixedLengthRecord;
import org.coury.jfilehelpers.enums.FixedMode;
import org.coury.jfilehelpers.helpers.StringHelper;

@FixedLengthRecord(fixedMode=FixedMode.AllowLessChars)
public class Track {
	@FieldFixedLength(2) public int number;
	@FieldFixedLength(15) public String performer;
	@FieldFixedLength(15) public String album;
	@FieldFixedLength(20) public String music;
	@FieldFixedLength(10) public String genre;
	@FieldFixedLength(15) public String label;
	@FieldFixedLength(4) public int year;
	
	@Override
	public String toString() {
		String l = System.getProperty("line.separator");
		StringBuffer b = new StringBuffer();
		b.append("Track #").append(number).append("): ").append(l);
		b.append("   performer = ").append(performer).append(l);
		b.append("   album = ").append(album).append(l);
		b.append("   music = ").append(music).append(l);
		b.append("   genre = ").append(genre).append(l);
		b.append("   label = ").append(label).append(l);
		b.append("   year = ").append(year).append(l);
		return StringHelper.toStringBuilder(this, b.toString());
	}
}
