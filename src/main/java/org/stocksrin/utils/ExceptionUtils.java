package org.stocksrin.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	private ExceptionUtils() {
	}

	public static String getStackTrace(Throwable aThrowable) {
		String traceMessage = null;
		try (StringWriter stringWriter = new StringWriter(); PrintWriter printWriter = new PrintWriter(stringWriter);

		) {
			aThrowable.printStackTrace(printWriter);
			traceMessage = stringWriter.toString();

		} catch (Exception e) {
			// logger.error("ExceptionUtils", "", "", e.getMessage());
			e.printStackTrace();
		}
		return traceMessage;

	}
}
