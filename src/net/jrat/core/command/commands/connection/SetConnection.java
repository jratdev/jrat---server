package net.jrat.core.command.commands.connection;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.connection.Connection;
import net.jrat.core.listener.Listener;
import net.jrat.utils.Logger;

public class SetConnection extends Command
{
	public SetConnection()
	{
		super("setconnection", new String[] { "listener", "connection" }, new String[] { "defines the listener by name", "defines the connection by username" }, "sets the current connection");
	}
	
	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		final String listenerName = commandLine.getOptionValue("listener", null);
		
		if(listenerName == null)
			throw new Exception("listener name is needed");
		
		final Listener listener = this.server.listenerManager.getListener(listenerName);
		
		if(listener == null)
			throw new Exception("listener not found");
		
		final String connectionUsername = commandLine.getOptionValue("connection", null);
		
		if(connectionUsername == null)
			throw new Exception("connection username needed");
		
		final Connection connection = listener.getConnection(connectionUsername);
		
		if(connection == null)
			throw new Exception("connection not found");
		
		this.server.currentConnection = connection;
		Logger.log("current connection -> " + connection.informations.username);
	}
}
