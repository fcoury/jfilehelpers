package org.coury.jfilehelpers.tests.converters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.coury.jfilehelpers.engines.FileHelperEngine;
import org.coury.jfilehelpers.tests.common.Common;
import org.coury.jfilehelpers.tests.converters.testobjects.Enum2;
import org.coury.jfilehelpers.tests.converters.testobjects.EnumType2;

public class EnumConverterTest extends TestCase {
	@SuppressWarnings("unchecked")
	FileHelperEngine engine;
    
	@SuppressWarnings("unchecked")
	public void testEnumSingleCase() throws IOException {
        engine = new FileHelperEngine<EnumType2>(EnumType2.class);

		List<EnumType2> res = (ArrayList<EnumType2>) Common.readTest(engine, "Good/EnumConverter2.txt");

		assertEquals(5, res.size());

		assertEquals(Enum2.One, res.get(0).enumValue);
		assertEquals(Enum2.One, res.get(1).enumValue);
		assertEquals(Enum2.Two, res.get(2).enumValue);
		assertEquals(Enum2.Three, res.get(3).enumValue);
		assertEquals(Enum2.Three, res.get(4).enumValue);
	}

}
