package net.windwaker.textureme.gui.container;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.widget.FinishButton;

import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.GenericContainer;
import org.getspout.spoutapi.gui.GenericTextField;

public class Creator extends GenericContainer {
	
	private GenericTextField id;
	private GenericTextField name;
	private GenericTextField url;
	
	private GenericButton button;
	
	public Creator(TextureMe plugin) {
		
		id = new GenericTextField();
		id.setX(110).setY(45);
		id.setWidth(200).setHeight(20);
		id.setText("Id...");
		
		name = new GenericTextField();
		name.setX(110).setY(70);
		name.setWidth(200).setHeight(20);
		name.setText("Name...");
		
		url = new GenericTextField();
		url.setX(110).setY(95);
		url.setWidth(200).setHeight(20);
		url.setText("Url...");
		
		button = new FinishButton(plugin, this);
		button.setX(110).setY(130);
		button.setWidth(200).setHeight(20);
		
		this.setWidth(0).setHeight(0);
		this.addChildren(id, name, url, button);
	}
	
	public String getIdText() {
		return id.getText();
	}
	
	public String getName() {
		return name.getText();
	}
	
	public String getUrl() {
		return url.getText();
	}
	
	public void clearText() {
		id.setText("");
		name.setText("");
		url.setText("");
	}
}