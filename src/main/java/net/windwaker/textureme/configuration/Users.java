package net.windwaker.textureme.configuration;

import java.io.File;
import net.windwaker.textureme.TextureMe;

public class Users extends Configuration {
	private final TextureMe plugin = TextureMe.getInstance();
	
	public Users() {
		super(new File("plugins/TextureMe/users.yml"));
	}
	
	@Override
	public void load() {
		super.load();
		if (this.getConfigurationSection("users") == null) {
			this.addDefault("users.wjcrouse913.texture pack", "dokucrafthigh");
			this.options().copyDefaults(true);
			this.save();
		}
	}
	
	public void setSelection(String name, String id) {
		this.set("users." + name + ".texture pack", id);
		this.save();
	}
	
	public String getSelection(String name) {
		return getString("users." + name + ".texture pack");
	}
	
	public boolean hasSelection(String name) {
		String selection = this.getString("users." + name + ".texture pack", "");
		return !selection.equalsIgnoreCase("no selection") && !selection.equals("");
	}
}