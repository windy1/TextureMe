package net.windwaker.textureme.listener;

import net.windwaker.textureme.TextureMe;

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
		SpoutPlayer player = event.getPlayer();
		String def = plugin.getConfig().getString("default texture pack");
		if (!def.equalsIgnoreCase("default")) {
			player.sendNotification("Default texture pack set!", "Downloading...", Material.GOLDEN_APPLE);
			player.setTexturePack(plugin.getConfig().getString("texturepacks." + def + ".url"));
		}
	}
}