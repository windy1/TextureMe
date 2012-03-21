package net.windwaker.textureme.configuration;

import java.io.File;
import org.bukkit.ChatColor;

public class Settings extends Configuration {
	
	public Settings() {
		super(new File("plugins/TextureMe/config.yml"));
	}
	
	@Override
	public void load() {
		super.load();
		this.addDefault("default-key-binding", "F7");
		this.addDefault("notification-id", 322);
		this.addDefault("remember-selections", true);
		this.addDefault("prompt-title", ChatColor.YELLOW + "Texture Packs");
		this.addDefault("prompt-on-login", false);
		this.addDefault("default-texture-pack", "default");
		this.options().copyDefaults(true);
		this.save();
	}
	
	public String getKeyBinding() {
		return getString("default-key-binding");
	}
	
	public String getPromptTitle() {
		return getString("prompt title").replaceAll("&", "§");
	}
	
	public boolean rememberSelections() {
		return getBoolean("remember selections", false);
	}
	
	public boolean promptLogins() {
		return getBoolean("prompt on login");
	}
	
	public boolean useDefault() {
		return !getString("default texture pack").equalsIgnoreCase("default");
	}
	
	public String getDefaultPack() {
		return getString("default texture pack");
	}
	
	public int getNotificationId() {
		return getInt("notification id");
	}
}