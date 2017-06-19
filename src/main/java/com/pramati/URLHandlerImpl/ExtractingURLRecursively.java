package com.pramati.URLHandlerImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;

import com.pramati.URLHandlerIntf.URLHandler;
import com.pramati.producer.URLFinderAction;

/**
 * @author Manish
 * 
 *         This class is used for extracting and crawling the URL recursively
 *         using Fork Join work stealing algorithm We can define number of
 *         threads to perform the operation recursively and then joining it
 *         collectively
 * 
 *         This is a kind of divide and conquer approach i tried to used using
 *         fork join to improve performance over normal threadpool
 *
 */

public class ExtractingURLRecursively implements URLHandler {

	private final Collection<String> visitedLinks = Collections
			.synchronizedSet(new HashSet<String>());

	private String url;
	private ForkJoinPool mainPool;

	// Creating BlockingQueue of size 10
	BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	public BlockingQueue<String> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<String> queue) {
		this.queue = queue;
	}

	public ExtractingURLRecursively(String startingURL, int maxThreads) {
		this.url = startingURL;
		mainPool = new ForkJoinPool(maxThreads);
	}

	/* start to crawl */
	public void startCrawling(String year) {
		mainPool.invoke(new URLFinderAction(this.url, this, this.getQueue(),
				year));
	}

	public int size() {
		return visitedLinks.size();
	}

	public void addVisited(String s) {
		visitedLinks.add(s);
	}

	public boolean visited(String s) {
		return visitedLinks.contains(s);
	}

}