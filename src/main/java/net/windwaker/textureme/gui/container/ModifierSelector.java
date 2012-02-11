package net.windwaker.textureme.gui.container;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.widget.TexturePackList;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericListWidget;
import org.getspout.spoutapi.gui.RenderPriority;

public class ModifierSelector extends GenericContainer {
	
	private GenericListWidget list;
	
	private GenericButton con;
	
	public ModifierSelector(TextureMe plugin) {
		
		// Button
		con = new GenericButton("Continue");
		con.setX(110).setY(25);
		con.setWidth(200).setHeight(20);
		
		// Texture list
		list = new TexturePackList(plugin);
		list.setX(90).setY(50);
		list.setWidth(250).setHeight(125);
		list.setPriority(RenderPriority.Lowest);		
		
		this.addChildren(con, list);
		this.setWidth(0).setHeight(0);
	}
}