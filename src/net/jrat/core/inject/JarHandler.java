package net.jrat.core.inject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class JarHandler
{
	public void applyInjection(File output, HashMap<String, byte[]> classes) throws Exception
	{
		final File temp = new File(output.getAbsolutePath() + ".tmp");
		final JarFile jar = new JarFile(output);
		
		final JarOutputStream jarOutput = new JarOutputStream(new FileOutputStream(temp));
		
		final byte[] buffer = new byte[(int) output.length()];
		
		for(Entry<String, byte[]> entry : classes.entrySet())
		{
			try
			{
				final JarEntry jarEntry = new JarEntry(entry.getKey());
				
				jarOutput.putNextEntry(jarEntry);
				jarOutput.write(entry.getValue());
			}
			catch (Exception e)
			{
				jarOutput.putNextEntry(new JarEntry("stub"));
			}
		}
		
		for(Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();)
		{
			final JarEntry entry = (JarEntry) entries.nextElement();
			
			if(!(classes.containsKey(entry.getName())))
			{
				final InputStream input = jar.getInputStream(entry);
				jarOutput.putNextEntry(entry);
				
				int bytes;
				while(!((bytes = input.read(buffer)) == -1))
					jarOutput.write(buffer, 0, bytes);
				input.close();
			}
		}
		
		jarOutput.close();
		jar.close();
		
		if(output.delete())
			temp.renameTo(output);
	}
}