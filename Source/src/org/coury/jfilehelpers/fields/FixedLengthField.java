package org.coury.jfilehelpers.fields;

import java.lang.reflect.Field;

import org.coury.jfilehelpers.core.ExtractedInfo;
import org.coury.jfilehelpers.engines.LineInfo;
import org.coury.jfilehelpers.enums.AlignMode;
import org.coury.jfilehelpers.enums.FixedMode;

public class FixedLengthField extends FieldBase {

	private int fieldLength;
	private FieldAlignBean align;
	private FixedMode fixedMode = FixedMode.ExactLength;
	
	public FixedLengthField(Field fi, int length, FieldAlignBean align) {
		super(fi);
		this.fieldLength = length;
		this.align = align;
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
