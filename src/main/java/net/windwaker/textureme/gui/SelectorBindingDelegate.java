package net.windwaker.textureme.gui;

import net.windwaker.textureme.TextureMe;

import org.bukkit.ChatColor;
import org.getspout.spoutapi.event.input.KeyBindingEvent;
import org.getspout.spoutapi.keyboard.BindingExecutionDelegate;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SelectorBindingDelegate implements BindingExecutionDelegate {

	private TextureMe plugin;
	
	public SelectorBindingDelegate(TextureMe plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void keyPressed(KeyBindingEvent event) {
		SpoutPlayer player = event.getPlayer();
		if(player.hasPermission("textureme.select")) {
			if(player.getMainScreen().getActivePopup() instanceof Selector) {
				//player.getMainScreen().getActivePopup().close();
			}else{
				player.getMainScreen().attachPopupScreen(new Selector(plugin, player));
			}
		} else {
			player.sendMessage(ChatColor.RED + "Error: No permission!");
		}
	}

	@Override
	public void keyReleased(KeyBindingEvent event) {
		// Nothing		
	}
}