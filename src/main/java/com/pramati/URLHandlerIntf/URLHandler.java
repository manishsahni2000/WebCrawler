package com.pramati.URLHandlerIntf;

/**
 * @author Manish
 * 
 *         This is the interface used for controlling the URL already visited , * 
 *         determining the size of the URL
 *
 */
public interface URLHandler {

	/**
	 * Returns the number of visited links
	 * 
	 * @return
	 */
	int size();

	/**
	 * Checks if the link was already visited
	 * 
	 * @param link
	 * @return
	 */
	boolean visited(String url);

	/**
	 * Marks this link as visited
	 * 
	 * @param link
	 */
	void addVisited(String url);

}
