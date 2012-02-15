package net.windwaker.textureme.logging;

import java.util.logging.Level;

public class PluginLevel extends Level {
	
	public static final Level DEBUG = new PluginLevel("DEBUG", Level.INFO.intValue()+1);
	public static final Level ENABLE = new PluginLevel("ENABLE", DEBUG.intValue()+1);
	public static final Level DISABLE = new PluginLevel("DISABLE", ENABLE.intValue()+1);
	public static final Level PLAYER = new PluginLevel("PLAYER", DISABLE.intValue()+1);
	
	public PluginLevel(String name, int value) {
		super(name, value);
	}
}