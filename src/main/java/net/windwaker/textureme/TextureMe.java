package net.windwaker.textureme;

import net.windwaker.textureme.configuration.Packs;
import net.windwaker.textureme.configuration.Settings;
import net.windwaker.textureme.configuration.Users;
import net.windwaker.textureme.gui.SelectorBindingDelegate;
import net.windwaker.textureme.listener.EventListener;

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
	private final PluginManager pluginManager = Bukkit.getPluginManager();
	
	public TextureMe() {
		instance = this;
	}
	
	@Override
	public void onEnable() {
		
		// Check for Spout
		if (pluginManager.getPlugin("Spout") == null || !pluginManager.isPluginEnabled("Spout")) {
			logger.info("TextureMe requires SpoutPlugin to run, please download Spout at http://get.spout.org - Shutting down TextureMe...");
			pluginManager.disablePlugin(this);
			return;
		}

		// Load data
		config.load();
		packs.load();
		users.load();

		// Register events
		pluginManager.registerEvents(new EventListener(), this);

		// Register key binding
		Keyboard key = Keyboard.valueOf("KEY_" + config.getKeyBinding());
		if (key == null) {
			key = Keyboard.KEY_F7;
		}

		SpoutManager.getKeyBindingManager().registerBinding(
				"textureme_open_selector", key, "Toggles the selector", new SelectorBindingDelegate(this), this);

		// Hello world!
		logger.info("TextureMe v" + this.getDescription().getVersion() + " by Windwaker enabled!");
	}
	
	@Override
	public void onDisable() {
		logger.info("TextureMe v" + this.getDescription().getVersion() + " by Windwaker disabled.");
	}
	
	public static TextureMe getInstance() {
		return instance;
	}
	
	@Override
	public Settings getConfig() {
		return config;
	}

	public void sendNotification(SpoutPlayer player, String message) {
		player.sendNotification("Texture Packs", message, Material.getMaterial(config.getNotificationId()));
	}
	
	public Users getUsers() {
		return users;
	}
	
	public Packs getPacks() {
		return packs;
	}
}
