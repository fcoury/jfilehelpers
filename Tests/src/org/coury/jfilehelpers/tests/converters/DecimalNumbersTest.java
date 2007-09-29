package org.coury.jfilehelpers.tests.converters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.coury.jfilehelpers.engines.FileHelperEngine;
import org.coury.jfilehelpers.tests.common.Common;
import org.coury.jfilehelpers.tests.converters.testobjects.DecimalType;

public class DecimalNumbersTest extends TestCase {
	@SuppressWarnings("unchecked")
	FileHelperEngine engine;

	@SuppressWarnings("unchecked")
	public void testDecimals1() throws IOException {
		engine = new FileHelperEngine<DecimalType>(DecimalType.class);

		List<DecimalType> res = 
			(ArrayList<DecimalType>) Common.readTest(engine, "Good/NumberFormat.txt");

		assertEquals(10, res.size());

		assertDecimal((double) 32.38, res.get(0));
		assertDecimal((double) 11.61, res.get(1));
		assertDecimal((double) 65.83, res.get(2));
		assertDecimal((double) 41.34, res.get(3));
		assertDecimal((double) 51.3, res.get(4));
		assertDecimal((double) 58.17, res.get(5));
		assertDecimal((double) 22.98, res.get(6));
		assertDecimal((double) 148.33, res.get(7));
		assertDecimal((double) 13.97, res.get(8));
		assertDecimal((double) 81.91, res.get(9));
	}

	private static void assertDecimal(double dec, DecimalType res) {
		assertEquals((double) dec, res.doubleField);
		assertEquals((float) dec, res.floatField);
	}
}
