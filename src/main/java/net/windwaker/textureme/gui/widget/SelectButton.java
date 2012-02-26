package net.windwaker.textureme.gui.widget;

import java.util.Set;

import net.windwaker.textureme.TextureMe;

import net.windwaker.textureme.configuration.Packs;
import net.windwaker.textureme.configuration.Settings;
import net.windwaker.textureme.configuration.Users;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.player.SpoutPlayer;

public class SelectButton extends GenericButton {
	
	private ListWidget list;
	private TextureMe plugin;
	
	public SelectButton(TextureMe plugin, ListWidget list) {
		super("Select");
		this.plugin = plugin;
		this.list = list;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		SpoutPlayer player = event.getPlayer();
		Packs packs = plugin.getPacks();
		Settings config = plugin.getConfig();
		Users users = plugin.getUsers();
		if (list.getSelectedItem() != null) {
			if (!list.getSelectedItem().getTitle().equals(ChatColor.YELLOW + "Player's Choice")) {
				player.setTexturePack(packs.getPackAddress(this.getSelectedId()));
				TextureMe.getInstance().sendNotification(player, "Downloading pack...");
				if (config.rememberSelections()) {
					users.setSelection(player.getName(), this.getSelectedId());
				}
				
			} else if (player.hasPermission("textureme.playerschoice")) {
				player.resetTexturePack();
				users.setSelection(player.getName(), "no selection");
				TextureMe.getInstance().sendNotification(player, "Pack removed!");
			} else {
				player.sendMessage(ChatColor.RED + "Error: No permission.");
			}
		}
	}
	
	public String getSelectedId() {
		Packs packs = plugin.getPacks();
		Set<String> ids = packs.getPacks();
		for(String id : ids) {
			String name = packs.getPackName(id);
			if (name.equalsIgnoreCase(list.getSelectedItem().getTitle())) {
				return id;
			}
		}
		
		return null;
	}
}