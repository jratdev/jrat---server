package net.jrat.core.connection;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection
{
	public Socket socket;
	public ObjectInputStream inputStream;
	public ObjectOutputStream outputStream;
	
	public SystemInformations informations;
	
	public Connection(Socket socket, ObjectInputStream inputStream, ObjectOutputStream outputStream)
	{
		this.socket = socket;
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.informations = null;
	}
}
