package net.windwaker.textureme;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.windwaker.textureme.gui.SelectorBindingDelegate;
import net.windwaker.textureme.listener.TmPlayerListener;
import net.windwaker.textureme.listener.TmSpoutListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

public class TextureMe extends JavaPlugin {
	
	private static final Logger logger = Logger.getLogger("Minecraft");
	private final SelectorBindingDelegate delegate = new SelectorBindingDelegate(this);
	
	@Override
	public void onEnable() {
		this.init();
		logger.log(Level.INFO, "[TextureMe] TextureMe " + this.getDescription().getVersion() + " enabled!");
	}
	
	@Override
	public void onDisable() {
		logger.log(Level.INFO, "[TextureMe] TextureMe " + this.getDescription().getVersion() + " disabled.");
	}
	
	public void init() {
		this.initConfig();
		this.registerEvents();
		SpoutManager.getKeyBindingManager().registerBinding("textureme", Keyboard.KEY_GRAVE, "Toggles the selector", delegate, this);
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new TmSpoutListener(this), this);
		pm.registerEvents(new TmPlayerListener(this), this);
	}
	
	public void initConfig() {
		try {
			File file = new File("plugins/TextureMe/config.yml");
			if (!file.exists()) {
				this.getConfig().addDefault("prompt title", ChatColor.YELLOW + "Texture Packs");
				this.getConfig().addDefault("prompt on login", false);
				this.getConfig().addDefault("default texture pack", "default");
				this.getConfig().addDefault("texturepacks.dokucraftlight.name", "Dokucraft - Light");
				this.getConfig().addDefault("texturepacks.dokucraftlight.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20Light.zip");
				this.getConfig().addDefault("texturepacks.dokucraftdark.name", "Dokucraft - Dark");
				this.getConfig().addDefault("texturepacks.dokucraftdark.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20Dark.zip");
				this.getConfig().addDefault("texturepacks.dokucrafthigh.name", "Dokucraft - High");
				this.getConfig().addDefault("texturepacks.dokucrafthigh.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20High.zip");
				this.getConfig().options().copyDefaults(true);
				this.saveConfig();
			} else {
				this.getConfig().addDefault("prompt title", "&eTexture Packs");
				this.getConfig().addDefault("prompt on login", false);
				this.getConfig().addDefault("default texture pack", "default");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}