package net.windwaker.textureme.listener;

import net.windwaker.textureme.TextureMe;

import net.windwaker.textureme.configuration.Configuration;
import net.windwaker.textureme.logging.Logger;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TmSpoutListener implements Listener {
	
	private TextureMe plugin;
	
	public TmSpoutListener(TextureMe plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void setDefaultTexturePack(SpoutCraftEnableEvent event) {
		Logger logger = Logger.getInstance();
		SpoutPlayer player = event.getPlayer();
		Configuration config = plugin.getConfig();
		Configuration users = plugin.getUsers();
		String def = config.getString("default texture pack");
		String pack = users.getString("players." + player.getName() + ".texture pack");
		
		// If a default pack is enabled and a players choice is not overriding it, set the players pack to default.
		if (!def.equalsIgnoreCase("default")) { 
			if (config.getBoolean("remember selections") && users.getString("players." + player.getName() + ".texture pack") != null 
					&& !users.getString("players." + player.getName() + ".texture pack").equalsIgnoreCase("<no selection>")) {
				logger.player(player.getName() 
						+ " has a selected pack, overriding default pack. To change this: disable 'remember selections' in the configuration.");
			} else {
				player.sendNotification("Default texture pack set!", "Downloading...", Material.GOLDEN_APPLE);
				player.setTexturePack(plugin.getConfig().getString("texturepacks." + def + ".url"));
				logger.player(player.getName() + " was assigned the default texture pack.");
			}
		}
		
		// If we are remembering what the player chose and has a selection, set that selection. If not, say so.
		if (config.getBoolean("remember selections")) {
			if (users.getString("players." + player.getName() + ".texture pack") != null) {
				if (!users.getString("players." + player.getName() + ".texture pack").equalsIgnoreCase("<no selection>")) {
					player.sendNotification("Pack: Your selection!", "Downloading...", Material.GOLDEN_APPLE);
					player.setTexturePack(config.getString("texturepacks." + pack + ".url"));
					logger.player(player.getName() + " was assigned his chosen texture pack");
				}
			} else {
				users.set("players." + player.getName() + ".texture pack", "<no selection>");
				users.save();
			}
		}
	}
}