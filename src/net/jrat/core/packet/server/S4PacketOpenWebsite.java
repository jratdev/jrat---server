package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S4PacketOpenWebsite implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String url;
	
	public S4PacketOpenWebsite(String url)
	{
		this.url = url;
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}