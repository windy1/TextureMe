package net.windwaker.textureme.gui.widget;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.container.ConfigMenu;
import net.windwaker.textureme.gui.container.Deleter;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.PopupScreen;

public class DeleteButton extends GenericButton {
	
	private TextureMe plugin;
	private ConfigMenu config;
	private Deleter deleter;
	
	public DeleteButton(TextureMe plugin, ConfigMenu config, Deleter deleter) {
		super("Delete");
		this.plugin = plugin;
		this.config = config;
		this.deleter = deleter;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		PopupScreen popup = event.getPlayer().getMainScreen().getActivePopup();
		popup.removeWidget(config);
		popup.attachWidget(plugin, deleter);
	}
}