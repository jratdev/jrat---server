package net.jrat;

import net.jrat.core.Server;
import net.jrat.utils.Logger;
import net.jrat.utils.Variables;

public class Bootstrap
{
	public static void main(String[] arguments)
	{
		Server server;
		Variables variables;
		
		Logger.space();
		Logger.log("setting up jrat");
		{
			Logger.startFrequence();
			
			server = new Server();
			Logger.log("created server instance");
			
			variables = new Variables();
			variables.setup();
			
			Logger.log("created variables instance");
			
			Logger.stopFrequence();
		}
		Logger.log("done");
		Logger.space();
		Logger.log("starting " + variables.appname);
		{
			Logger.startFrequence();
			
			server.start();
			
			Logger.stopFrequence();
		}
		Logger.log("done");
		Logger.space();
	}
}
