# WebCrawler

WebCrawler_Pramati README

Welcome to WebCrawler_Pramati!

COMPONENTS

  com.pramati.consumer.URLConsumer.java - The Class for Consuming the Crawled URL for downloading them.
  com.pramati.downloader.Downloader.java - Helper Class for downloading the mails.
  com.pramati.exception.ExceptionHandler.java  - The custom Exception class for clean code
  com.pramati.internet.InternetConnectionChecker.java - Class for verifying the internet connectivity
  com.pramati.producer.URLFinderAction.java - Class used as the ForkJoin Task
  com.pramati.propertyLoader.PropertyFileLoader.java - Class for loading the property file
  com.pramati.URLHandlerImpl.ExtractingURLRecursively.java - Class used for extracting the URL recursively
  com.pramati.URLHandlerIntf.URLHandler.java - Interface used for defining the properties for URL visited
  com.pramati.web_crawler_pramati.CrawlerApp.java - Application entry point
  
  
PROPERTY FILE CONFIGURATION

url= the URL to be crawled
maxThread_crawlURL= Maximum number of threads used to crawl in parallel
year=The Year for which URL or mails are to be crawled and /or downloaded
outputPath= The output path of where all the mails will be downloaded
numThreadsDownloader= Number of threads needed to download the mails in parallel 

UTILITIES

  WebCrawler_Pramati.jar  - Runnable jar for crawling and downloading the mails
  crawler.properties - defining the properties for 
  

RUNNING

  For command line options invoke:

    $ java -jar WebCrawler_Pramati.jar

