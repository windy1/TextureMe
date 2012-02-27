package net.windwaker.textureme.listener;

import net.windwaker.textureme.TextureMe;

import net.windwaker.textureme.configuration.Packs;
import net.windwaker.textureme.configuration.Settings;
import net.windwaker.textureme.configuration.Users;
import net.windwaker.textureme.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TmSpoutListener implements Listener {
	
	private final TextureMe plugin;
	private final Logger logger = Logger.getInstance();
	
	public TmSpoutListener(TextureMe plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void setTexturePack(SpoutCraftEnableEvent event) {
		SpoutPlayer player = event.getPlayer();
		Settings config = plugin.getConfig();
		Users users = plugin.getUsers();
		Packs packs = plugin.getPacks();
		if (config.rememberSelections() && users.hasSelection(player.getName())) {
			player.setTexturePack(packs.getPackAddress(users.getSelection(player.getName())));
			plugin.sendNotification(player, "Selection remembered!");
			logger.player(player.getName() 
					+ "'s texture pack was set to their selection. If this is a mistake, disable 'remember selections' in the config.");
		} else if (config.useDefault()) {
			player.setTexturePack(packs.getPackAddress(config.getDefaultPack()));
			plugin.sendNotification(player, "Downloading pack...");
			logger.player(player.getName() + "'s texture pack was set to the default texture pack.");
		}
	}
}