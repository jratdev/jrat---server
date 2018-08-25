package net.jrat.core.listener;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class ListenerManager
{
	@Expose
	public List<Listener> listeners;
	
	public ListenerManager()
	{
		this.listeners = new ArrayList<Listener>();
	}
	
	public Listener getListener(String name)
	{
		for(Listener listener : this.listeners)
		{
			if(listener.name.equalsIgnoreCase(name))
				return listener;
		}
		
		return null;
	}
	
	public State getState(String name)
	{
		for(State state : State.values())
		{
			if(state.name.equalsIgnoreCase(name))
				return state;
		}
		
		return null;
	}
}
