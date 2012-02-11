package net.windwaker.textureme.gui.container;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.widget.TexturePackList;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.RenderPriority;

public class Deleter extends GenericContainer {
	
	private TexturePackList list;
	private GenericButton delete;
	
	public Deleter(TextureMe plugin) {
				
		// TODO: Button
		delete = new GenericButton("Delete");
		delete.setX(110).setY(25);
		delete.setWidth(200).setHeight(20);
		
		// Texture list
		list = new TexturePackList(plugin);
		list.setX(90).setY(50);
		list.setWidth(250).setHeight(125);
		list.setPriority(RenderPriority.Lowest);
		
		this.addChildren(delete, list);
		this.setWidth(0).setHeight(0);
	}
}