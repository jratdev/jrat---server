package net.jrat.core.threads;

import java.util.Scanner;

import net.jrat.core.Server;
import net.jrat.core.command.Command;
import net.jrat.utils.Logger;
import net.jrat.utils.Variables;

public class CommandListener implements Runnable
{
	private Server server = Server.instance;
	
	@Override
	public void run()
	{
		try
		{
			Thread.sleep(100L);
		}
		catch(Exception e) {}
		
		final Scanner scanner = new Scanner(System.in);
		
		while(this.server.running)
		{
			System.out.print(Variables.instance.appname + " -> ");
			final String input = scanner.nextLine();
			
			if(input.equals(""))
				continue;
			
			try
			{
				Thread.sleep(100L);
			}
			catch(Exception e) {}
			
			String commandName = null;
			String[] commandArgs = null;
			
			final String[] split = input.split(" ");
			if(split.length > 1)
			{
				commandName = split[0];
				commandArgs = new String[split.length - 1];
				
				for(int i = 0; i < commandArgs.length; i++)
					commandArgs[i] = split[i + 1];
			}
			else
			{
				commandName = input;
				commandArgs = new String[0];
			}
			
			final Command command = this.server.commandManager.getCommand(commandName);
			
			if(command == null)
			{
				Logger.err("command \"" + commandName + "\" not found.");
				Logger.space();
				continue;
			}
			
			try
			{
				command.execute(commandArgs);
			}
			catch(Exception e)
			{
				Logger.err("could not handle command: " + e.getMessage());
			}
			
			Logger.space();
		}
		
		scanner.close();
	}
}
