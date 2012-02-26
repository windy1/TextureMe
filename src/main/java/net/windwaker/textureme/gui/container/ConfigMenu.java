package net.windwaker.textureme.gui.container;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.widget.AddButton;
import net.windwaker.textureme.gui.widget.DeleteButton;
import net.windwaker.textureme.gui.widget.ModifyButton;

import org.getspout.spoutapi.gui.GenericContainer;

public class ConfigMenu extends GenericContainer {
	
	public ConfigMenu(TextureMe plugin) {
		AddButton add = new AddButton(plugin, this, new Creator(plugin));
		add.setX(110).setY(45);
		add.setWidth(200).setHeight(20);
		add.setTooltip("Add a new texture pack");
		
		ModifyButton modify = new ModifyButton(plugin, this, new ModifierSelector(plugin));
		modify.setX(110).setY(70);
		modify.setWidth(200).setHeight(20);
		modify.setTooltip("Modify an existing texture pack");
		
		DeleteButton delete = new DeleteButton(plugin, this, new Deleter(plugin));
		delete.setX(110).setY(95);
		delete.setWidth(200).setHeight(20);
		delete.setTooltip("Delete an existing texture pack");
		
		this.addChildren(add, modify, delete);
		this.setWidth(0).setHeight(0);
	}
}