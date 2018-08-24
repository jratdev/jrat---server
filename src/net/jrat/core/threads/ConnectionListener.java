package net.jrat.core.threads;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.jrat.core.Server;
import net.jrat.core.listener.Listener;
import net.jrat.core.listener.State;

public class ConnectionListener implements Runnable
{
	private final Server server = Server.instance;
	
	private Listener listener;
	
	public ConnectionListener(Listener listener)
	{
		this.listener = listener;
	}
	
	@Override
	public void run()
	{
		while(this.listener.state == State.STARTED && this.server.running)
		{
			try
			{
				final Socket socket = this.listener.serverSocket.accept();
				
				final ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
				final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				
				new Thread(new ActionListener(this.listener, socket, inputStream, outputStream), "connection").start();
			}
			catch(Exception e) {}
		}
		
		this.listener.thread = null;
	}
}
