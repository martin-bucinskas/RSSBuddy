package happysad.example;

import happysad.tb.RSSParser;
import happysad.tb.RSSReader;
import happysad.tb.TradingBuddy;
import happysad.tb.utils.Utility;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;

public class GetFilteredRSSFeed
{	
	public GetFilteredRSSFeed()
	{
		TradingBuddy.logToFile(true);
	}
	
	public void runExample()
	{
		String path = "data/feed.txt";
		String forexURL = "http://articlefeeds.nasdaq.com/nasdaq/categories?category=Forex%20and%20Currencies&format=xml";
		
		if(!Utility.doesDirExist("data"))
			Utility.createDir("data");
		
		RSSReader reader = null;
		RSSParser parser = null;
		
		try
		{
			reader = new RSSReader(forexURL);
			parser = new RSSParser();
			
			parser.populateKeywordList("USD", "EUR", "dollar", "GBP", "pound");
			
			reader.connectToFeed();
			
			SyndFeed feed = reader.getFeed();
			
			Utility.writeToFile(path, "\n\n------ " + new Date().toString() + " ------\n\n");
			Utility.writeToFile(path, "New Feed\n", "\n\tFeed Author: " + feed.getAuthor(), "\n\tFeed Title: " + feed.getTitle(), "\n\tFeed Copyright:" + feed.getCopyright());
			Utility.writeToFile(path, "\nKeywords used in filtering entries in feed: \n");
			
			StringBuilder sb = new StringBuilder();
			for(String keyword : parser.getKeywords())
				sb.append(keyword).append(' ');
			Utility.writeToFile(path, sb.toString());
			
			/*
			 * Pass feed.getEntries() to parser.getFilteredEntries as a generic type
			 * to prevent unchecked type errors.
			 * parser.getFilteredEntries accepts List<?>.
			 */
			List<?> entryList = feed.getEntries();
			List<SyndEntry> filteredEntries = parser.getFilteredEntries(entryList);
			
			Utility.writeToFile(path, "\nFeed Entries: ");
			
			for(SyndEntry entry : filteredEntries)
			{
				Utility.writeToFile(path, "\n\tTitle: " + entry.getTitle(), "\n\tDate: " + entry.getPublishedDate(), "\n\tURI: " + entry.getUri(), "\n\tLink: " + entry.getLink());
			}
		}
		catch (IOException | IllegalArgumentException | FeedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			reader.close();
		}
	}

	public static void main(String[] args)
	{
		GetFilteredRSSFeed example = new GetFilteredRSSFeed();
		
		example.runExample();
	}

}
