package net.jrat.core.command;

import java.util.ArrayList;
import java.util.List;

import net.jrat.core.command.commands.AddListener;
import net.jrat.core.command.commands.ConnectionInfo;
import net.jrat.core.command.commands.Exit;
import net.jrat.core.command.commands.ExportFile;
import net.jrat.core.command.commands.Help;
import net.jrat.core.command.commands.ToggleListener;
import net.jrat.core.command.commands.connection.Execute;
import net.jrat.core.command.commands.connection.OpenWeb;
import net.jrat.core.command.commands.connection.SetConnection;
import net.jrat.core.command.commands.connection.ShowMessage;
import net.jrat.core.command.commands.connection.Uninstall;

public class CommandManager
{
	public List<Command> commands;
	
	public CommandManager()
	{
		this.commands = new ArrayList<Command>();
		this.setup();
	}
	
	private void setup()
	{
		this.commands.add(new Help());
		this.commands.add(new AddListener());
		this.commands.add(new ToggleListener());
		this.commands.add(new ConnectionInfo());
		this.commands.add(new ExportFile());
		this.commands.add(new Exit());

		this.commands.add(new SetConnection());
		this.commands.add(new ShowMessage());
		this.commands.add(new Execute());
		this.commands.add(new OpenWeb());
		
		this.commands.add(new Uninstall());
	}
	
	public Command getCommand(String name)
	{
		for(Command command : this.commands)
		{
			if(command.name.equalsIgnoreCase(name))
				return command;
		}
		
		return null;
	}
}
