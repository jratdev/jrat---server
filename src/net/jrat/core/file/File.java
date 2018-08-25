package net.jrat.core.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.charset.Charset;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.jrat.core.Server;
import net.jrat.utils.Encryptor;
import net.jrat.utils.Logger;
import net.jrat.utils.Variables;

public abstract class File
{
	public final Server server = Server.instance;
	
	private BufferedInputStream reader;
	private BufferedOutputStream writer;
	
	private java.io.File file;
	private Gson gson;
	
	public File(String filename, boolean exclude)
	{
		this.file = new java.io.File(Variables.instance.workDir + filename);
		
		if(exclude)
			this.gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		else
			this.gson = new GsonBuilder().setPrettyPrinting().create();
		
		try
		{
			if(!(this.file.exists()))
			{
				this.file.getParentFile().mkdirs();
				this.file.createNewFile();
			}

			this.reader = new BufferedInputStream(new FileInputStream(this.file));
			this.writer = new BufferedOutputStream(new FileOutputStream(this.file, true));
		}
		catch(Exception e)
		{
			Logger.warn("could not setup file: " + e.getMessage());
		}
	}

	public abstract void save() throws Exception;
	public abstract void load() throws Exception;
	
	public void clear() throws Exception
	{
		final BufferedWriter writer = new BufferedWriter(new FileWriter(this.file, false));
		writer.write("");
		writer.close();
	}

	public void write(Object object) throws Exception
	{
		final String json = this.gson.toJson(object);
		final byte[] bytes = Encryptor.encrypt(this.file.getName(), json.getBytes());
		
		this.writer.write(bytes);
	}
	
	public Object read(Class<?> clazz) throws Exception
	{
		if(this.file.length() == 0)
			return null;
		
		final byte[] bytes = new byte[(int) this.file.length()];
		final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		int read;
		while(!((read = this.reader.read(bytes)) == -1))
			outputStream.write(bytes, 0, read);
		
		final byte[] json = Encryptor.decrypt(this.file.getName(), outputStream.toByteArray());
		final String text = new String(json, Charset.defaultCharset());

		outputStream.close();
		
		return this.gson.fromJson(text, clazz);
	}
	
	public void close() throws Exception
	{
		this.writer.close();
		this.reader.close();
	}
}