package net.jrat.core.packet;

import java.io.Serializable;

public interface IPacket extends Serializable
{
	public abstract void execute(Object object) throws Exception;
}