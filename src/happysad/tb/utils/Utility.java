package happysad.tb.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utility
{
	/**
	 * Checks if the given directory exists.
	 * @param path path to directory
	 * @return true if the directory exists, false if it does not
	 */
	public static boolean doesDirExist(String path)
	{
		File temp = new File(path);
		try
		{
			if(temp.exists() && temp.isDirectory())
				return true;
			return false;
		}
		finally
		{
			temp = null;
		}
	}
	
	/**
	 * Creates a directory.
	 * @param path path to the directory
	 */
	public static void createDir(String path)
	{
		System.out.println("Creating a directory:\r" + path);
		File temp = new File(path);
		try
		{
			temp.mkdirs();
			System.out.println("Directory created successfully!");
		}
		finally
		{
			temp = null;
		}
	}
	
	/**
	 * Writes the specified string to a file.
	 * @param path path to the file
	 * @param strings string array to write to the file
	 */
	public static boolean writeToFile(String path, String...strings)
	{
		try 
		{
			StringBuilder sb = new StringBuilder();
			
			for(String string : strings)
				sb.append(string);
			
			BufferedWriter out = new BufferedWriter(new FileWriter(path, true));
			
			out.write(sb.toString());
			out.newLine();
			out.flush();
			out.close();
			return true;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
}
