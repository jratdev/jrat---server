package net.jrat.utils;

import java.io.File;

public class Variables
{
	public static Variables instance;
	
	public String author;
	public String company;
	
	public String appname;
	public float version;
	
	public String workDir;
	
	public void setup()
	{
		instance = this;

		this.author = "paolo v.";
		this.company = "aragon inc.";

		this.appname = "jrat";
		this.version = 0.01F;
		
		this.workDir = System.getProperty("user.dir") + File.separator;
	}
}