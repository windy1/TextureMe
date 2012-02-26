package net.windwaker.textureme.gui.widget;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.configuration.Packs;
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
		Packs packs = plugin.getPacks();
		if (!id.isEmpty() && !name.isEmpty() && !url.isEmpty()) {
			packs.setPackName(id, name);
			packs.setPackAddress(id, url);
			plugin.saveConfig();
			TextureMe.getInstance().sendNotification(player, "Pack added!");
			creator.clearText();
		} else {
			TextureMe.getInstance().sendNotification(player, "Fields must not be blank!");
		}
	}
}