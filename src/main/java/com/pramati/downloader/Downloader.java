package com.pramati.downloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Manish
 * 
 *         This class is a helper class used to download the mail to a
 *         particular location
 */
public class Downloader {
	private String url;
	private static int counter = 0;
	private String outputPath;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public void downloadPage(String url) throws IOException {

		URL urls = new URL(url);
		OutputStream out = new FileOutputStream("" + outputPath + ""
				+ ++counter + ".html");

		URLConnection conn = urls.openConnection();
		conn.connect();
		InputStream is = conn.getInputStream();

		copy(is, out);
		is.close();
		out.close();

	}

	private static void copy(InputStream from, OutputStream to)
			throws IOException {
		byte[] buffer = new byte[4096];
		while (true) {
			int numBytes = from.read(buffer);
			if (numBytes == -1) {
				break;
			}

			to.write(buffer, 0, numBytes);
		}
	}
}
