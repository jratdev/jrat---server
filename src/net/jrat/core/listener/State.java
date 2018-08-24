package net.jrat.core.listener;

public enum State
{
	STARTED("Started"), STOPPED("Stopped");

	public String name;

	State(String name)
	{
		this.name = name;
	}
}
