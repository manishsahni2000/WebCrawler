package com.pramati.internet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

	public static boolean pingUrl(final String address) {
		try {
			final URL url = new URL(address);
			final HttpURLConnection urlConn = (HttpURLConnection) url
					.openConnection();
			urlConn.setRequestMethod("HEAD");
			urlConn.setConnectTimeout(1000 * 20);
			urlConn.setReadTimeout(1000 * 20);
			final long startTime = System.currentTimeMillis();
			urlConn.connect();
			int responseCode = urlConn.getResponseCode();
			final long endTime = System.currentTimeMillis();
			if (200 <= responseCode && responseCode <= 399) {
				System.out.println("Ping to " + address + " was success, "
						+ responseCode + ", Time (ms) : "
						+ (endTime - startTime));
				return true;
			}
			System.out.println("Ping to " + address + " was failure, "
					+ responseCode + ", Time (ms) : " + (endTime - startTime));
			urlConn.disconnect();
		} catch (final MalformedURLException e1) {
			System.out.println(e1.getMessage());
		} catch (final IOException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
