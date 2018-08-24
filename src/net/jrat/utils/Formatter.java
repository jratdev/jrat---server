package net.jrat.utils;

public class Formatter
{
	private String value;
	
	public Formatter(String value)
	{
		this.value = value;
	}
	
	public Formatter()
	{
		this(null);
	}
	
	public static Formatter getValue(String[] arguments, String key)
	{
		for(String argument : arguments)
		{
			if(argument.startsWith(key))
			{
				final String value = argument.substring(key.length(), argument.length());
				return new Formatter(value);
			}
		}
		
		return new Formatter();
	}
	
	public String defaultTo(String value)
	{
		if(this.value == null)
			this.value = value;
		
		return this.value;
	}
}
