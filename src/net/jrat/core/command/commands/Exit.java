package net.jrat.core.command.commands;

import net.jrat.core.command.Command;
import net.jrat.utils.Logger;

public class Exit extends Command
{
	public Exit()
	{
		super("exit", new String[0], new String[0], "system shutdown");
	}

	@Override
	public void execute(String[] arguments) throws Exception
	{
		Logger.log("shutting down...");
		this.server.running = false;
	}
}