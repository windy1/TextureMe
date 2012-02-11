package net.windwaker.textureme.gui.container;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.widget.AddButton;
import net.windwaker.textureme.gui.widget.DeleteButton;
import net.windwaker.textureme.gui.widget.ModifyButton;

import org.bukkit.ChatColor;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;

public class ConfigMenu extends GenericContainer {
	
	private GenericButton add;
	private GenericButton modify;
	private GenericButton delete;
	
	public ConfigMenu(TextureMe plugin) {
		
		add = new AddButton(plugin, this, new Creator(plugin));
		add.setX(110).setY(45);
		add.setWidth(200).setHeight(20);
		add.setTooltip("Add a new texture pack");
		
		modify = new ModifyButton(plugin, this, new ModifierSelector(plugin));
		modify.setX(110).setY(70);
		modify.setWidth(200).setHeight(20);
		modify.setTooltip("Modify an existing texture pack");
		
		delete = new DeleteButton(plugin, this, new Deleter(plugin));
		delete.setX(110).setY(95);
		delete.setWidth(200).setHeight(20);
		delete.setTooltip(ChatColor.RED + "Not yet implemented");
		delete.setEnabled(false);
		
		this.addChildren(add, modify, delete);
		this.setWidth(0).setHeight(0);
	}
}