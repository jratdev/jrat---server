package net.jrat.core.command;

import net.jrat.core.Server;

public abstract class Command
{
	public String name;
	public String[] arguments;
	public String[] meanings;
	public String description;
	
	public final Server server = Server.instance;
	
	public Command(String name, String[] arguments, String[] meanings, String description)
	{
		this.name = name;
		this.arguments = arguments;
		this.meanings = meanings;
		this.description = description;
	}
	
	public abstract void execute(String[] arguments) throws Exception;
}
