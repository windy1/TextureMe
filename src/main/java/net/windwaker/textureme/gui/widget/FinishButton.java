package net.windwaker.textureme.gui.widget;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.container.Creator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class FinishButton extends GenericButton {
	
	private TextureMe plugin;
	private Creator creator;
	
	public FinishButton(TextureMe plugin, Creator creator) {
		super("Finish");
		this.plugin = plugin;
		this.creator = creator;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		SpoutPlayer player = event.getPlayer();
		String id = creator.getIdText();
		String name = creator.getName();
		String url = creator.getUrl();
		if (!id.isEmpty() && !name.isEmpty() && !url.isEmpty()) {
			plugin.getConfig().set("texturepacks." + id + ".name", name);
			plugin.getConfig().set("texturepacks." + id + ".url", url);
			plugin.saveConfig();
			player.sendNotification("Texture pack added!", "", Material.GOLDEN_APPLE);
			creator.clearText();
		} else {
			player.sendNotification(ChatColor.RED + "Error", "Fields must not be blank", Material.GOLDEN_APPLE);
		}
	}
}