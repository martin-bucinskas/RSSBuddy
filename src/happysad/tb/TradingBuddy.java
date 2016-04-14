package happysad.tb;

import happysad.tb.utils.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TradingBuddy
{
	public static final Logger LOGGER;
	private static File file = new File(new String("logs/" + new Date().toString() + ".log").replace(':', '-'));
	private static FileHandler _FILE_HANDLER;
	
	private static boolean log;
	
	static
	{
		LOGGER = Logger.getLogger(TradingBuddy.class.getCanonicalName());
		
		if(!Utility.doesDirExist("logs"))
			Utility.createDir("logs");
		
		LOGGER.log(Level.INFO, "Logger created.");
	}
	
	public static void logToFile(boolean logToFile)
	{
		log = logToFile;
		
		if(log)
		{
			try
			{
				_FILE_HANDLER =  new FileHandler(file.getCanonicalPath());
				_FILE_HANDLER.setFormatter(new SimpleFormatter());
				LOGGER.addHandler(_FILE_HANDLER);
			}
			catch (SecurityException | IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			_FILE_HANDLER.close();
		}
	}
}
