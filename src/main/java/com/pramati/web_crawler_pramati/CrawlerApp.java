package com.pramati.web_crawler_pramati;

import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.pramati.URLHandlerImpl.ExtractingURLRecursively;
import com.pramati.consumer.URLConsumer;
import com.pramati.downloader.Downloader;
import com.pramati.internet.InternetConnectionChecker;
import com.pramati.propertyLoader.PropertyFileLoader;

public class CrawlerApp {

	public static void main(String[] args) throws Exception {

		/* Load the property file */
		Properties prop = new PropertyFileLoader().loadProperty();

		/* checking for Internet connection */

		InternetConnectionChecker internetConnValidation = new InternetConnectionChecker(
				10, prop.getProperty("url"));
		
		System.out
		.println("***********************************************");
		System.out.println("Checking for internet connection !!");
		
		if (internetConnValidation.isConnected()) {

			/*
			 * Step 1 : Using fork join collect the URL , fill the blocking
			 * queue and then pass it to other thread
			 */

			System.out
					.println("***********************************************");
			System.out.println("About to Extract and Crawl URL specific to "
					+ prop.getProperty("year"));
			System.out
					.println("***********************************************");

			ExtractingURLRecursively crawler = new ExtractingURLRecursively(
					prop.getProperty("url"), Integer.parseInt(prop
							.getProperty("maxThread_crawlURL")));

			// setting year

			crawler.startCrawling(prop.getProperty("year"));

			/* Step 2 - Download Mail */

			Downloader downloader = new Downloader();
			downloader.setOutputPath(prop.getProperty("outputPath"));

			/* Step 3 - Consume URL for downloading the mails */

			URLConsumer consumer = new URLConsumer(crawler.getQueue(),
					downloader);

			/*
			 * Step 4- Initiate set of threads to execute the downloading tasks
			 * Parallel
			 */

			Executor executor = Executors.newFixedThreadPool(Integer
					.parseInt(prop.getProperty("numThreadsDownloader")));
			executor.execute(consumer);
		} else {

			System.out.println("Please check your internet connection");
		}
	}
}
