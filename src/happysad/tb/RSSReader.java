package happysad.tb;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RSSReader
{
	private URL url;
	private XmlReader reader;
	private List<SyndEntry> entryList;
	
	public RSSReader()
	{
		url = null;
		entryList = new ArrayList<SyndEntry>();
	}
	
	public RSSReader(URL url)
	{
		this.url = url;
		entryList = new ArrayList<SyndEntry>();
	}
	
	public RSSReader(String url) throws MalformedURLException
	{
		this.url = new URL(url);
		entryList = new ArrayList<SyndEntry>();
	}
	
	public void connectToFeed() throws IOException
	{
		if(url != null)
		{
			TradingBuddy.LOGGER.log(Level.INFO, "Connecting to feed.");
			
			reader = new XmlReader(url);
			
			TradingBuddy.LOGGER.log(Level.INFO, "Connected to the feed.");
		}
	}
	
	public SyndFeed getFeed() throws IllegalArgumentException, FeedException
	{
		if(url != null)
		{
			SyndFeed feed = new SyndFeedInput().build(reader);
			
			return feed;
		}
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<SyndEntry> getEntryList(SyndFeed feed)
	{
		if(url != null)
		{
			entryList = feed.getEntries();
			
			return entryList;
		}
		else
			return null;
	}
	
	public void setURL(URL url)
	{
		this.url = url;
	}
	
	public void setURL(String url) throws MalformedURLException
	{
		this.url = new URL(url);
	}
	
	public URL getURL()
	{
		return url;
	}
	
	public void close()
	{
		if(reader != null)
		{
			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				TradingBuddy.LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}
}
