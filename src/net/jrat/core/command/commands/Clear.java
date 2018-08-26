package net.jrat.core.command.commands;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;

public class Clear extends Command
{
	public Clear()
	{
		super("clear", new String[0], new String[0], "clears the console");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		for(int i = 0; i < 125; i++)
			System.out.println();
	}
}