package net.windwaker.textureme.gui.widget;

import java.util.Set;

import net.windwaker.textureme.TextureMe;

import org.bukkit.ChatColor;
import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.ListWidgetItem;

public class TexturePackList extends GenericListWidget {
		
	public TexturePackList(TextureMe plugin) {
			
		// Textures
		this.addItem(new ListWidgetItem(ChatColor.YELLOW + "Player's Choice", ""));
		Set<String> ids = plugin.getConfig().getConfigurationSection("texturepacks").getKeys(false);
		for (String id : ids) {
			String name = plugin.getConfig().getString("texturepacks." + id + ".name");
			this.addItem(new ListWidgetItem(name, ""));
		}
	}
}