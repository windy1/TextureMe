package net.windwaker.textureme.gui.container;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.widget.DeleterButton;
import net.windwaker.textureme.gui.widget.TexturePackList;

import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.RenderPriority;

public class Deleter extends GenericContainer {
	
	public Deleter(TextureMe plugin) {	
		// Texture list
		TexturePackList list = new TexturePackList(plugin);
		list.setX(90).setY(50);
		list.setWidth(250).setHeight(125);
		list.setPriority(RenderPriority.Lowest);
		
		DeleterButton delete = new DeleterButton(list);
		delete.setX(110).setY(25);
		delete.setWidth(200).setHeight(20);
		
		this.addChildren(delete, list);
		this.setWidth(0).setHeight(0);
	}
}