package net.windwaker.textureme.gui.widget;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.container.ConfigMenu;
import net.windwaker.textureme.gui.container.Creator;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.player.SpoutPlayer;

public class AddButton extends GenericButton {
	
	private TextureMe plugin;
	private ConfigMenu config;
	private Creator creator;
	
	public AddButton(TextureMe plugin, ConfigMenu config, Creator creator) {
		super("Add");
		this.plugin = plugin;
		this.config = config;
		this.creator = creator;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		SpoutPlayer player = event.getPlayer();
		player.getMainScreen().getActivePopup().removeWidget(config);
		player.getMainScreen().getActivePopup().attachWidget(plugin, creator);
	}
}