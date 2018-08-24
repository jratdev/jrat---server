package net.jrat.core.command.commands.connection;

import javax.swing.JOptionPane;

import net.jrat.core.command.Command;
import net.jrat.core.packet.server.S1PacketShowMessage;
import net.jrat.utils.Formatter;

public class ShowMessage extends Command
{
	public ShowMessage()
	{
		super("showmsg", new String[] { "title", "message", "type" }, new String[] { "sets the title", "sets the message", "sets the dialog type (0-3)" }, "shows a message to current connection");
	}

	@Override
	public void execute(String[] arguments) throws Exception
	{
		if(this.server.currentConnection == null)
			throw new Exception("current connection is equal to null");
		
		final String title = Formatter.getValue(arguments, "-title=").defaultTo("title");
		final String message = Formatter.getValue(arguments, "-message=").defaultTo("message");
		
		final String typeString = Formatter.getValue(arguments, "-type=").defaultTo(String.valueOf(JOptionPane.INFORMATION_MESSAGE));
		
		if(!(typeString.matches("-?\\d+")))
			throw new Exception("type has to be an integer");
		
		final int type = Integer.valueOf(typeString);
		
		if(type < 0 || type > 3)
			throw new Exception("invalid dialog type");
		
		this.server.currentConnection.outputStream.writeObject(new S1PacketShowMessage(title, message, type));
	}
}