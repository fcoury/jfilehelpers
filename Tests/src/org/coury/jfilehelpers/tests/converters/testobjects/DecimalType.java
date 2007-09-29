package org.coury.jfilehelpers.tests.converters.testobjects;

import org.coury.jfilehelpers.annotations.DelimitedRecord;

@DelimitedRecord("|")
public class DecimalType {
	public int intField;
	public float floatField;
	public double doubleField;
	// added just for C# compatibility
	public float decimalField;
}
