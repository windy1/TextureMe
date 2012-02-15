package net.windwaker.textureme.configuration;

import java.io.File;
import java.io.IOException;
import net.windwaker.textureme.logging.Logger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration extends YamlConfiguration {
	
	private File file;
	private final Logger logger = Logger.getInstance();
	
	public Configuration(String path) {
		file = new File(path);
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void load() {
		try {
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
				this.load(file);
				logger.config("Created and loaded config file at: " + file.getAbsolutePath());
			} else {
				this.load(file);
				logger.config("Loaded config file at: " + file.getAbsolutePath());
			}
		} catch (IOException e) {
			logger.severe("Config file at " + file.getAbsolutePath() + " failed to load: " + e.getMessage());
		} catch (InvalidConfigurationException ce) {
			logger.severe("Config at " + file.getAbsolutePath() + " failed to load: " + ce.getMessage());
		}
	}
	
	public void save() {
		try {
			this.save(file);
			logger.config("Saved config file at: " + file.getAbsolutePath());
		} catch (IOException e) {
			logger.severe("Config file at " + file.getAbsolutePath() + " failed to save: " + e.getMessage());
		}
	}
}