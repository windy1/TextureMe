package net.windwaker.textureme;

import net.windwaker.textureme.configuration.Packs;
import net.windwaker.textureme.configuration.Settings;
import net.windwaker.textureme.configuration.Users;
import net.windwaker.textureme.gui.SelectorBindingDelegate;
import net.windwaker.textureme.listener.TmPlayerListener;
import net.windwaker.textureme.listener.TmSpoutListener;

import net.windwaker.textureme.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TextureMe extends JavaPlugin {
	
	private static TextureMe instance;
	private final Logger logger = Logger.getInstance();
	private final Settings config = new Settings();
	private final Packs packs = new Packs();
	private final Users users = new Users();
	
	public TextureMe() {
		instance = this;
	}
	
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
		config.load();
		packs.load();
		users.load();
		this.registerEvents();
		SpoutManager.getKeyBindingManager().registerBinding("textureme_open_selector", Keyboard.KEY_P, "Toggles the selector", 
				new SelectorBindingDelegate(this), this);
	}

	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new TmSpoutListener(this), this);
		pm.registerEvents(new TmPlayerListener(this), this);
	}
	
	public void sendNotification(SpoutPlayer player, String message) {
		player.sendNotification("Texture Packs", message, Material.getMaterial(config.getNotificationId()));
	}
	
	public static TextureMe getInstance() {
		return instance;
	}
	
	@Override
	public Settings getConfig() {
		return config;
	}
	
	public Users getUsers() {
		return users;
	}
	
	public Packs getPacks() {
		return packs;
	}
}