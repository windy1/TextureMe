package net.windwaker.textureme.gui.widget;

import java.util.Set;

import net.windwaker.textureme.TextureMe;

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
		if (list.getSelectedItem() != null) {
			if (!list.getSelectedItem().getTitle().equals(ChatColor.YELLOW + "Player's Choice")) {
				player.setTexturePack(plugin.getConfig().getString("texturepacks." + this.getSelectedId() + ".url"));
				player.sendNotification("Texture pack selected!", "Downloading...", Material.GOLDEN_APPLE);
			} else if (player.hasPermission("textureme.playerschoice")) {
				player.resetTexturePack();
				player.sendNotification("Texture pack removed!", "", Material.GOLDEN_APPLE);
			} else {
				player.sendNotification(ChatColor.RED + "Error", "No permission", Material.GOLDEN_APPLE);
			}
		}
	}
	
	public String getSelectedId() {
		Set<String> ids = plugin.getConfig().getConfigurationSection("texturepacks").getKeys(false);
		for(String id : ids) {
			String name = plugin.getConfig().getString("texturepacks." + id + ".name");
			if (name.equalsIgnoreCase(list.getSelectedItem().getTitle())) {
				return id;
			}
		}
		return null;
	}
}