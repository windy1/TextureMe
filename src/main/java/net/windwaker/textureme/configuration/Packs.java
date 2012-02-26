package net.windwaker.textureme.configuration;

import java.io.File;
import java.util.Set;

public class Packs extends Configuration {
	
	public Packs() {
		super(new File("plugins/TextureMe/packs.yml"));
	}
	
	@Override
	public void load() {
		super.load();
		if (this.getConfigurationSection("texturepacks") == null) {
			this.addDefault("texturepacks.dokucraftlight.name", "Dokucraft - Light");
			this.addDefault("texturepacks.dokucraftlight.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20Light.zip");
			this.addDefault("texturepacks.dokucraftdark.name", "Dokucraft - Dark");
			this.addDefault("texturepacks.dokucraftdark.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20Dark.zip");
			this.addDefault("texturepacks.dokucrafthigh.name", "Dokucraft - High");
			this.addDefault("texturepacks.dokucrafthigh.url", "http://dl.dropbox.com/u/27507830/TextureMe/DokuCraft%20-%20High.zip");
			this.options().copyDefaults(true);
			this.save();
		}
	}
	
	public String getPackAddress(String id) {
		return getString("texturepacks." + id + ".url");
	} 
	
	public void setPackAddress(String id, String url) {
		this.set("texturepacks." + id + ".url", url);
		this.save();
	}
	
	public Set<String> getPacks() {
		return getConfigurationSection("texturepacks").getKeys(false);
	}
	
	public String getPackName(String id) {
		return getString("texturepacks." + id + ".name");
	}
	
	public void setPackName(String id, String name) {
		this.set("texturepacks." + id + ".name", name);
		this.save();
	}
	
	public void deletePack(String id) {
		this.set("texturepacks." + id, null);
		this.save();
	}
	
	public String getPackId(String name) {
		for (String pack : getPacks()) {
			if (getString("texturepacks." + pack + ".name").equalsIgnoreCase(name)) {
				return pack;
			}
		}
		
		return null;
	} 
}