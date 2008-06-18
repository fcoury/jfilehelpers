/*
 * FixedLengthField.java
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

import org.coury.jfilehelpers.core.ExtractedInfo;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.enums.AlignMode;
import org.coury.jfilehelpers.enums.FixedMode;
import org.coury.jfilehelpers.helpers.StringHelper;

public class FixedLengthField extends FieldBase {

	private final int fieldLength;
	private final FieldAlignBean align;
	private final FixedMode fixedMode;
	
	public FixedLengthField(Field fi, int length, FieldAlignBean align, FixedMode fixedMode) {
		super(fi);
		this.fieldLength = length;
		this.align = align;
		this.fixedMode = fixedMode;
	}
	
	@Override
	protected ExtractedInfo extractFieldString(LineInfo line) {
		if (line.getCurrentLength() == 0) {
			if (isOptional())
				return ExtractedInfo.Empty;
			else
				throw new IllegalArgumentException(
						"End Of Line found processing the field: " + 
						getFieldInfo().getName() + " at line " + 
						line.getLineNumber() + ". " +
						"(You need to mark it as @FieldOptional if you want to avoid this exception)");
		}
		
		ExtractedInfo res;

		if (line.getCurrentLength() < this.fieldLength) {
			if (fixedMode == FixedMode.AllowLessChars || fixedMode == FixedMode.AllowVariableLength) {
				res = new ExtractedInfo(line);
			}
			else {
				throw new IllegalArgumentException(
						"The string '" + line.getCurrentString() + 
						"' (length " + line.getCurrentLength() + ") at line " + 
						line.getLineNumber() + " has less chars than the defined for " + 
						getFieldInfo().getName() + " (" + fieldLength + "). " +
						"You can use the @FixedLengthRecord(fixedMode=FixedMode.AllowLessChars) to avoid this problem.");
			}
		}
		else if (isLast() && line.getCurrentLength() > fieldLength && fixedMode != FixedMode.AllowMoreChars && fixedMode != FixedMode.AllowVariableLength) {
			throw new IllegalArgumentException(
					"The string '" + line.getCurrentString() + 
					"' (length " + line.getCurrentLength() + ") at line " + 
					line.getLineNumber() + " has more chars than the defined for the last field " + 
					getFieldInfo().getName() + " (" + fieldLength + "). " +
					"You can use the @FixedLengthRecord(fixedMode=FixedMode.AllowMoreChars) to avoid this problem.");
		}
		else {
			res = new ExtractedInfo(line, line.getCurrentPos() + fieldLength);
		}

		return res;
	}

	@Override
	protected void createFieldString(StringBuffer sb, Object fieldValue) {
		String field = super.baseFieldString(fieldValue);
		field = StringHelper.trimBoth(field, StringHelper.WHITESPACE_CHARS);

		if (field.length() > fieldLength) {
			field = field.substring(0, fieldLength);
			//sb.Length = length + this.mFieldLength;
		}

		if (align.getAlign() == AlignMode.Left) {
			sb.append(field);
			for (int i = 0; i < fieldLength - field.length(); i++) {
				sb.append(align.getChar());
			}
		}
		else if (align.getAlign() == AlignMode.Right) {
			for (int i = 0; i < fieldLength - field.length(); i++) {
				sb.append(align.getChar());
			}
			sb.append(field);
		}
		else {
			int middle = (fieldLength - field.length()) / 2;

			for (int i = 0; i < middle; i++) {
				sb.append(align.getChar());
			}
			sb.append(field);
			for (int i = 0; i < fieldLength - field.length() - middle; i++) {
				sb.append(align.getChar());
			}
		}
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public FieldAlignBean getAlign() {
		return align;
	}

	public FixedMode getFixedMode() {
		return fixedMode;
	}

}
