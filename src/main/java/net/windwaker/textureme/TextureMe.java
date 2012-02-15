package net.windwaker.textureme;

import net.windwaker.textureme.configuration.Configuration;
import net.windwaker.textureme.gui.SelectorBindingDelegate;
import net.windwaker.textureme.listener.TmPlayerListener;
import net.windwaker.textureme.listener.TmSpoutListener;

import net.windwaker.textureme.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

public class TextureMe extends JavaPlugin {
	
	private static final Logger logger = Logger.getInstance();
	
	@Override
	public void onEnable() {
		logger.enable("TextureMe initializing...");
		this.init();
		logger.enable("TextureMe v" + this.getDescription().getVersion() + " by Windwaker enabled!");
	}
	
	@Override
	public void onDisable() {
		logger.disable("TextureMe v" + this.getDescription().getVersion() + " by Windwaker disabled.");
	}
	
	public void init() {
		Configuration config = new Configuration("plugins/TextureMe/config.yml");
		config.load();
		config.options().copyDefaults(true);
		config.save();
		this.registerEvents();
		SpoutManager.getKeyBindingManager().registerBinding("textureme_open_selector", Keyboard.KEY_P, "Toggles the selector", new SelectorBindingDelegate(this), this);
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new TmSpoutListener(this), this);
		pm.registerEvents(new TmPlayerListener(this), this);
	}
}