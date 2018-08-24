package net.jrat.core;

import net.jrat.core.command.CommandManager;
import net.jrat.core.connection.Connection;
import net.jrat.core.listener.ListenerManager;
import net.jrat.core.threads.CommandListener;
import net.jrat.utils.Logger;

public class Server
{
	public static Server instance;
	
	public CommandListener commandListener;
	
	public ListenerManager listenerManager;
	public CommandManager commandManager;
	
	public Connection currentConnection;
	public boolean running;
	
	public void start()
	{
		instance = this;
		
		this.currentConnection = null;
		this.running = true;
		
		this.listenerManager = new ListenerManager();
		this.commandManager = new CommandManager();
		
		Logger.log("starting threads");
		{
			Logger.startFrequence();
			
			{
				final Thread commandThread = new Thread((this.commandListener = new CommandListener()), "command listener");
				commandThread.start();
				
				Logger.log("command listener started");
			}
			
			Logger.stopFrequence();
		}
		Logger.log("done");
	}
}
