package net.jrat.core.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

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
			
			final String[] split = input.split(" ");
			final Command command = this.server.commandManager.getCommand(split[0]);
			
			if(command == null)
			{
				Logger.err("command \"" + split[0] + "\" not found.");
				Logger.space();
				continue;
			}
			
			final Options options = new Options();
			for(String argument : command.arguments)
			{
				final Option option = new Option(argument, true, null);
				option.setRequired(false);
				
				options.addOption(option);
			}
			
			try
			{
				final List<String> argList = new ArrayList<String>();
				
				if(split.length > 1)
				{
					for(int i = 1; i < split.length; i++)
					{
						String text = split[i];
						
						if(text.startsWith("\""))
						{
							int addition = 0;
							while(!(text.endsWith("\"")))
							{
								addition ++;
								text += " " + split[i + addition];
							}

							i += addition;
						}
						
						argList.add(text);
					}
				}
				
				final String[] arguments = new String[argList.size()];
				for(int i = 0; i < arguments.length; i++)
					arguments[i] = argList.get(i);
				
				final CommandLineParser parser = new DefaultParser();
				final CommandLine commandLine = parser.parse(options, arguments);
				command.execute(commandLine);
			}
			catch(Exception e)
			{
				Logger.space();
				Logger.err("could not handle command: " + e.getMessage());
			}
			
			Logger.space();
		}
		
		scanner.close();
	}
}
