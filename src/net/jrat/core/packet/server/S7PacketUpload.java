package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S7PacketUpload implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private byte[] filedata;
	private String outputpath;
	
	public S7PacketUpload(byte[] filedata, String outputpath)
	{
		this.filedata = filedata;
		this.outputpath = outputpath;
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}