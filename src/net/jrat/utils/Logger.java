package net.jrat.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger
{
	private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static int frequencies = 0;
	
	public static void startFrequence()
	{
		log("{");
		frequencies ++;
	}
	
	public static void log(String message)
	{
		String space = "";
		for(int i = 0; i < frequencies; i++)
			space += "    ";
		
		final Date date = new Date();
		final String output = "[INFO: " + dateFormat.format(date) + "] " + space + message;
		
		System.out.print(output);
		System.out.print("\n");
	}
	
	public static void warn(String message)
	{
		String space = "";
		for(int i = 0; i < frequencies; i++)
			space += "    ";

		final Date date = new Date();
		final String output = "[WARN: " + dateFormat.format(date) + "] " + space + message;

		System.out.print(output);
		System.out.print("\n");
	}
	
	public static void err(String message)
	{
		String space = "";
		for(int i = 0; i < frequencies; i++)
			space += "    ";

		final Date date = new Date();
		final String output = "[ERR: " + dateFormat.format(date) + "] " + space + message;

		System.err.print(output);
		System.err.print("\n");
	}
	
	public static void space()
	{
		System.out.print("\n");
	}
	
	public static void stopFrequence()
	{
		frequencies --;
		log("}");
	}
}
