package net.jrat.core.command.commands.connection;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S2PacketUninstall;

public class Uninstall extends Command
{
	public Uninstall()
	{
		super("uninstall", new String[] { "title", "message", "type" }, new String[] { "sets the title of dialog", "sets the message of dialog", "sets the dialog type (0-3)" }, "closes connection to client");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");

		final String title = commandLine.getOptionValue("title", null);
		final String message = commandLine.getOptionValue("message", null);
		final String typeString = commandLine.getOptionValue("type", null);
		
		if(title == null && message == null && typeString == null)
			this.server.currentConnection.outputStream.writeObject(new S2PacketUninstall());
		else
		{
			int type = 1;
			if(!(typeString == null))
			{
				if(!(typeString.matches("-?\\d+")))
					throw new Exception("type has to be an integer");
				
				type = Integer.valueOf(typeString);
				
				if(type < 0 || type > 3)
					throw new Exception("invalid dialog type");
			}
			
			this.server.currentConnection.outputStream.writeObject(new S2PacketUninstall(title == null ? "title" : title, message == null ? "message" : message, type));
		}
	}
}