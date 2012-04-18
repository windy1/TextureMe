package net.windwaker.textureme.gui.widget;

import java.util.Set;

import net.windwaker.textureme.TextureMe;

import net.windwaker.textureme.configuration.Packs;
import org.bukkit.ChatColor;
import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.ListWidgetItem;

public class TexturePackList extends GenericListWidget {

	public TexturePackList(TextureMe plugin) {
		// Textures
		Packs packs = plugin.getPacks();
		this.addItem(new ListWidgetItem(ChatColor.YELLOW + "Player's Choice", ""));
		Set<String> ids = packs.getPacks();
		for (String id : ids) {
			String name = packs.getPackName(id);
			this.addItem(new ListWidgetItem(name, ""));
		}
	}
}