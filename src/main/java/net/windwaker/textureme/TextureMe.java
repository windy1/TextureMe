package net.windwaker.textureme;

import net.windwaker.textureme.configuration.Configuration;
import net.windwaker.textureme.gui.SelectorBindingDelegate;
import net.windwaker.textureme.listener.TmPlayerListener;
import net.windwaker.textureme.listener.TmSpoutListener;

import net.windwaker.textureme.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.commons.ChatColor;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

public class TextureMe extends JavaPlugin {
	
	private static final Logger logger = Logger.getInstance();
	private static final Configuration config = new Configuration("plugins/TextureMe/config.yml");
	private static final Configuration users = new Configuration("plugins/TextureMe/users.yml");
	
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
	
	/**
	 * Initializes TextureMe.
	 */
	public void init() {
		config.load();
		this.copyConfigDefaults();
		if (config.getBoolean("remember selections")) {
			users.load();
			this.copyUserDefaults();
		}
		
		this.registerEvents();
		SpoutManager.getKeyBindingManager().registerBinding("textureme_open_selector", Keyboard.KEY_P, "Toggles the selector", 
				new SelectorBindingDelegate(this), this);
	}
	
	/**
	 * Registers listeners and events.
	 */
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new TmSpoutListener(this), this);
		pm.registerEvents(new TmPlayerListener(this), this);
	}
	
	@Override
	public Configuration getConfig() {
		return config;
	}
	
	public Configuration getUsers() {
		return users;
	}
	
	/**
	 * Checks to see if configuration file is new and adds defaults respectively.
	 */
	public void copyConfigDefaults() {
		config.addDefault("remember selections", true);
		config.addDefault("prompt title", ChatColor.YELLOW + "Texture Packs");
		config.addDefault("prompt on login", false);
		config.addDefault("default texture pack", "default");
		config.options().copyDefaults(true);
		if (config.isNew()) {
			config.addDefault("texturepacks.dokucraftlight.name", "Dokucraft - Light");
			config.addDefault("texturepacks.dokucraftlight.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20Light.zip");
			config.addDefault("texturepacks.dokucraftdark.name", "Dokucraft - Dark");
			config.addDefault("texturepacks.dokucraftdark.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20Dark.zip");
			config.addDefault("texturepacks.dokucrafthigh.name", "Dokucraft - High");
			config.addDefault("texturepacks.dokucrafthigh.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20High.zip");
		}
		
		config.save();
	}
	
	/**
	 * Checks to see if configuration file is new and adds defaults respectively.
	 */
	public void copyUserDefaults() {
		if (users.isNew()) {
			users.addDefault("players.wjcrouse913.texture pack", "dokucrafthigh");
			users.options().copyDefaults(true);
			users.save();
		}
	}
}