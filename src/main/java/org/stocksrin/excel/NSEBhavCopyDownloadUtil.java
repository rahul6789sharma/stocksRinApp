package org.stocksrin.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.stocksrin.utils.APPConstant;

public class NSEBhavCopyDownloadUtil {

	public static void downloadBhavCopy(String toFile) throws Exception {

		ReadableByteChannel rbc = null;

		try (FileOutputStream fos = new FileOutputStream(toFile);) {

			URL website = new URL(APPConstant.NSE_bhavdata_URL);
			rbc = Channels.newChannel(website.openStream());

			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			rbc.close();

		} catch (IOException e) {
			throw new Exception("ERROR ! downloadBhavCopy " + e.getMessage());
		} finally {
			try {
				if (rbc != null) {
					rbc.close();
				}

			} catch (IOException e) {

				throw new Exception("ERROR ! downloadBhavCopy " + e.getMessage());
			}
		}
	}

}
