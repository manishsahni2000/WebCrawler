package com.pramati.consumer;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import com.pramati.downloader.Downloader;
import com.pramati.exception.ExceptionHandler;

/**
 * @author Manish
 *
 *         This is the class used as a worker for consuming the URL those are
 *         crawled already and then pass it to the helper downloader class for
 *         downloading Multiple threads are used for downloading in parallel
 */
public class URLConsumer implements Runnable {

	private BlockingQueue<String> queue;
	Downloader downloader;

	public URLConsumer(BlockingQueue<String> queue, Downloader downloader) {
		this.queue = queue;
		this.downloader = downloader;
	}

	public void run() {
		try {

			System.out
					.println("***********************************************");
			System.out
					.println("Downloading all Mails in Progress ......Please wait for sometime.You can check the output folder!!");
			System.out
					.println("***********************************************");

			/* consuming messages until exit message is received */
			while (!queue.isEmpty()) {

				/* send the queue data for download to the consumer */

				downloader.downloadPage(queue.take().toString());

			}
			System.out
					.println("***********************************************");
			System.out.println("All Mails are downloaded Succesfully !!!!");
			System.out
					.println("***********************************************");

		} catch (IOException e) {
			ExceptionHandler.handleException(e.getMessage(), e);
		} catch (InterruptedException e) {
			ExceptionHandler.handleException(e.getMessage(), e);
		}
	}
}
