package net.jrat.core.packet.server;

import net.jrat.core.packet.IPacket;

@SuppressWarnings("unused")
public class S8PacketScreenshot implements IPacket
{
	private static final long serialVersionUID = 1L;
	
	private String outputPath;
	
	public S8PacketScreenshot(String outputPath)
	{
		this.outputPath = outputPath;
	}
	
	@Override
	public void execute(Object object) throws Exception {}
}