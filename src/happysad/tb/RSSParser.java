package happysad.tb;

import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;

public class RSSParser
{
	private List<String> keywords;
	private boolean searchTitle = true;
	private boolean searchDescription = false;
	private boolean searchDate = false;
	
	public RSSParser()
	{
		keywords = new ArrayList<String>();
	}
	
	public List<SyndEntry> getFilteredEntries(List<?> entries)
	{
		List<SyndEntry> filteredEntries = new ArrayList<SyndEntry>();
		
		for(Object entryObject : entries)
		{
			SyndEntry entry = (SyndEntry)entryObject;
			
			for(String keyword : keywords)
			{
				if(searchTitle)
					if(entry.getTitle().contains(keyword))
						if(!filteredEntries.contains(entry))
							filteredEntries.add(entry);
				if(searchDescription)
					if(entry.getDescription().getValue().contains(keyword))
						if(!filteredEntries.contains(entry))
							filteredEntries.add(entry);
				if(searchDate)
					if(entry.getPublishedDate().toString().contains(keyword))
						if(!filteredEntries.contains(entry))
							filteredEntries.add(entry);
			}
		}
		
		return filteredEntries;
	}
	
	public void populateKeywordList(String...strings)
	{
		for(String keyword : strings)
		{
			keywords.add(keyword);
		}
	}
	
	public List<String> getKeywords()
	{
		return keywords;
	}
}
