package net.windwaker.textureme.gui.widget;

import java.util.Set;

import net.windwaker.textureme.TextureMe;

import net.windwaker.textureme.configuration.Packs;
import net.windwaker.textureme.configuration.Settings;
import net.windwaker.textureme.configuration.Users;
import org.bukkit.ChatColor;
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
		String id = getSelectedId();
		boolean permission;
		boolean removePack = false;
		String url = packs.getPackAddress(id);

		if (list.getSelectedItem().getTitle().equals(ChatColor.YELLOW + "Player's Choice") && player.hasPermission("textureme.playerschoice")) {
			permission = true;
			removePack = true;
		}

		if (!removePack && packs.getPackAddress(id) == null | !packs.getPackAddress(id).endsWith(".zip")) {
			player.sendMessage(ChatColor.RED + "Error: Invalid URL");
			return;
		}
		
		if (packs.getPackName(id) == null && !removePack) {
			player.sendMessage(ChatColor.RED + "Error: Invalid pack");
			return;
		}
		
		if (packs.getPackNode(id) == null || player.hasPermission("textureme.playerschoice") && !removePack) {
			permission = true;
		} else {
			permission = false;
		}
		
		if (list.getSelectedItem() == null) {
			plugin.sendNotification(player, "Please select a texture pack.");
			return;
		}

		if (!permission) {
			player.sendMessage("Error: No permission.");
			return;
		}
		
		if (removePack) {
			player.resetTexturePack();
			users.setSelection(player.getName(),  "no selection");
			plugin.sendNotification(player, "Pack removed!");
			return;
		}

		player.setTexturePack(url);
		plugin.sendNotification(player, "Downloading pack...");
		if (config.rememberSelections()) {
			users.setSelection(player.getName(), id);
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