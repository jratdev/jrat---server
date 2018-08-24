package net.jrat.core.packet.client;

import net.jrat.core.connection.Connection;
import net.jrat.core.connection.SystemInformations;
import net.jrat.core.packet.IPacket;

public class C0PacketConnect implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private SystemInformations informations;
	
	public void execute(Object object) throws Exception
	{
		final Connection connection = (Connection) object;
		connection.informations = this.informations;
	}
}
