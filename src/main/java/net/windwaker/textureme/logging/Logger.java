package net.windwaker.textureme.logging;

import java.util.logging.Level;
import org.bukkit.Bukkit;

public class Logger {
	
	private static final Logger logger = new Logger();
	
	private Logger() {
		
	}
	
	public void log(Level level, Object obj) {
		Bukkit.getLogger().log(level, "[TextureMe] " + obj);
	}
	
	public void info(Object obj) {
		log(Level.INFO, obj);
	}
	
	public void warn(Object obj) {
		log(Level.WARNING, obj);
	}
	
	public void severe(Object obj) {
		log(Level.SEVERE, obj);
	}
	
	public void config(Object obj) {
		log(Level.CONFIG, obj);
	}
	
	public void debug(Object obj) {
		log(PluginLevel.DEBUG, obj);
	}
	
	public void enable(Object obj) {
		log(PluginLevel.ENABLE, obj);
	}
	
	public void disable(Object obj) {
		log(PluginLevel.DISABLE, obj);
	}
	
	public void player(Object obj) {
		log(PluginLevel.PLAYER, obj);
	}
	
	public static Logger getInstance() {
		return logger;
	}
}