package net.windwaker.textureme.listener;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.Selector;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.getspout.spoutapi.player.SpoutPlayer;

public class TmPlayerListener implements Listener {
	
	private TextureMe plugin;
	
	public TmPlayerListener(TextureMe plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void attachPrompt(PlayerJoinEvent event) {
		SpoutPlayer player = (SpoutPlayer) event.getPlayer();
		if(plugin.getConfig().promptLogins() && player.hasPermission("textureme.select")) {
			player.getMainScreen().attachPopupScreen(new Selector(plugin, player));
		}
	}
}