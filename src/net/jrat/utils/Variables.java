package net.jrat.utils;

public class Variables
{
	public static Variables instance;
	
	public String author;
	public String company;
	
	public String appname;
	public float version;
	
	public void setup()
	{
		instance = this;

		author = "paolo v.";
		company = "aragon inc.";

		appname = "jrat";
		version = 0.01F;
	}
}
