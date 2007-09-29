package org.coury.jfilehelpers.tests.converters;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.coury.jfilehelpers.engines.FileHelperEngine;
import org.coury.jfilehelpers.tests.common.Common;
import org.coury.jfilehelpers.tests.converters.testobjects.DateFormatType1;
import org.coury.jfilehelpers.tests.converters.testobjects.DateFormatType2;


public class DateFormatTest extends TestCase {
	@SuppressWarnings("unchecked")
	FileHelperEngine engine;
	
	@SuppressWarnings("unchecked")
	public void testDifferentSpanishFormat() throws IOException {
		engine = new FileHelperEngine<DateFormatType1>(DateFormatType1.class);

		List<DateFormatType1> res = (List<DateFormatType1>) Common.readTest(engine, "Good/DateFormat1.txt");
		assertEquals(6, res.size());
		
		Common.assertSameDate(getDate(1996, 7, 4), res.get(0).orderDate);
		Common.assertSameDate(getDate(1996, 7, 5), res.get(1).orderDate);
		Common.assertSameDate(getDate(1996, 7, 8), res.get(2).orderDate);
		Common.assertSameDate(getDate(1996, 7, 8), res.get(3).orderDate);

		Common.assertSameDate(getDate(1996, 8, 1), res.get(0).requiredDate);
		Common.assertSameDate(getDate(1996, 8, 16), res.get(1).requiredDate);
		Common.assertSameDate(getDate(1996, 8, 5), res.get(2).requiredDate);
		Common.assertSameDate(getDate(1996, 8, 5), res.get(3).requiredDate);

		Common.assertSameDate(getDate(1996, 7, 16), res.get(0).shippedDate);
		Common.assertSameDate(getDate(1996, 7, 10), res.get(1).shippedDate);
		Common.assertSameDate(getDate(1996, 7, 12), res.get(2).shippedDate);
		Common.assertSameDate(getDate(1996, 7, 15), res.get(3).shippedDate);
	}
	
	@SuppressWarnings("unchecked")
	public void testDifferentEnglishFormat() throws IOException {
		engine = new FileHelperEngine<DateFormatType2>(DateFormatType2.class);

		List<DateFormatType2> res = (List<DateFormatType2>) Common.readTest(engine, "Good/DateFormat2.txt");
		assertEquals(6, res.size());

		Common.assertSameDate(getDate(1996, 7, 4), res.get(0).orderDate);
		Common.assertSameDate(getDate(1996, 7, 5), res.get(1).orderDate);
		Common.assertSameDate(getDate(1996, 7, 8), res.get(2).orderDate);
		Common.assertSameDate(getDate(1996, 7, 8), res.get(3).orderDate);

		Common.assertSameDate(getDate(1996, 8, 1), res.get(0).requiredDate);
		Common.assertSameDate(getDate(1996, 8, 16), res.get(1).requiredDate);
		Common.assertSameDate(getDate(1996, 8, 5), res.get(2).requiredDate);
		Common.assertSameDate(getDate(1996, 8, 5), res.get(3).requiredDate);

		Common.assertSameDate(getDate(1996, 7, 16), res.get(0).shippedDate);
		Common.assertSameDate(getDate(1996, 7, 10), res.get(1).shippedDate);
		Common.assertSameDate(getDate(1996, 7, 12), res.get(2).shippedDate);
		Common.assertSameDate(getDate(1996, 7, 15), res.get(3).shippedDate);		
	}
	
	private static Date getDate(int y, int m, int d) {
		Calendar c = Calendar.getInstance();
		c.set(y, m-1, d, 0, 0, 0);
		return c.getTime();
	}
}
