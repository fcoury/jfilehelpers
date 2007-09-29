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
