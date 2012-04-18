package net.windwaker.textureme.listener;

import net.windwaker.textureme.Logger;
import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.configuration.Packs;
import net.windwaker.textureme.configuration.Settings;
import net.windwaker.textureme.configuration.Users;
import net.windwaker.textureme.gui.Selector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.getspout.spoutapi.event.spout.SpoutCraftEnableEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class EventListener implements Listener {
	private final TextureMe plugin = TextureMe.getInstance();
	private final Logger logger = Logger.getInstance();
	
	@EventHandler
	public void attachPrompt(PlayerJoinEvent event) {
		SpoutPlayer player = (SpoutPlayer) event.getPlayer();
		if(plugin.getConfig().promptLogins() && player.hasPermission("textureme.select")) {
			player.getMainScreen().attachPopupScreen(new Selector(plugin, player));
		}
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
			logger.info(player.getName() + "'s texture pack was set to their selection. If this is a mistake, disable 'remember selections' in the config.");
			return;
		}

		if (config.useDefault()) {
			player.setTexturePack(packs.getPackAddress(config.getDefaultPack()));
			plugin.sendNotification(player, "Downloading pack...");
			logger.info(player.getName() + "'s texture pack was set to the default texture pack.");
		}
	}
}
