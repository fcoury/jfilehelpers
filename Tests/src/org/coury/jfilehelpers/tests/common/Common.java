package org.coury.jfilehelpers.tests.common;

import java.io.IOException;
import java.util.Date;

import org.coury.jfilehelpers.engines.FileHelperEngine;

public class Common {
	public static Object readTest(FileHelperEngine<? extends Object> engine, String fileName) throws IOException {
		return engine.readResource("/test/" + fileName);
	}

	public static boolean assertSameDate(Date d1, Date d2) {
		return d1.equals(d2);
	}
}
