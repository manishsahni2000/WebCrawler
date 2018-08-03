package com.pramati.producer;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RecursiveAction;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.pramati.URLHandlerIntf.URLHandler;
import com.pramati.exception.ExceptionHandler;

/**
 * @author Manish
 * 
 * 
 *         This class used for parsing the HTML and the URL in it , and is used
 *         by multiple threads in parallel to fetch the URL and store in the
 *         blocking queue
 *
 */
public class URLFinderAction extends RecursiveAction {

	private static final long serialVersionUID = 1L;
	private String url;
	private URLHandler urlHandler;
	private BlockingQueue<String> queue;
	private String year;

	/* year is the value to determine which years URL needs to be fetched */

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public URLFinderAction(String url, URLHandler urlHandler,
			BlockingQueue<String> queue, String year) {
		this.url = url;
		this.urlHandler = urlHandler;
		this.queue = queue;
		this.year = year;
	}

	@Override
	public void compute() {
		/*
		 * re-check if the URL is already visited , then no need to visit it
		 * again
		 */
		if (!urlHandler.visited(url)) {
			try {
				List<RecursiveAction> actions = new ArrayList<RecursiveAction>();
				URL uriLink = new URL(url);

				/* Parsing the HTML for fetching the URL of specific year */

				Parser parser = new Parser(uriLink.openConnection());
				NodeList list = parser
						.extractAllNodesThatMatch(new NodeClassFilter(
								LinkTag.class));

				for (int i = 0; i < list.size(); i++) {
					LinkTag extracted = (LinkTag) list.elementAt(i);

					if (!extracted.extractLink().isEmpty()
							&& !urlHandler.visited(extracted.extractLink())) {

						/*
						 * To determine the which year URL needs to be fetched .
						 * Can also use pattern matching here
						 */
						if (extracted.extractLink().contains(getYear())) {

							System.out.println(extracted.extractLink());
							actions.add(new URLFinderAction(extracted
									.extractLink(), urlHandler, queue,
									getYear()));
						}
					}
				}
				/* Adding the URL to the visited List */
				urlHandler.addVisited(url);

				/* adding URL on the blocking queue */
				queue.put(url);

				/* invoking all worker threads */
				invokeAll(actions);

			} catch (Exception e) {

				ExceptionHandler.handleException(e.getMessage(), e);

			}
		}
	}
}