package net.windwaker.textureme.gui.widget;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.container.ConfigMenu;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.ListWidget;
import org.getspout.spoutapi.player.SpoutPlayer;

public class ConfigureButton extends GenericButton {
	
	private TextureMe plugin;
	private ListWidget list;
	private ConfigMenu config;
	
	public ConfigureButton(TextureMe plugin, ListWidget list, ConfigMenu config) {
		super("Configure");
		this.plugin = plugin;
		this.list = list;
		this.config = config;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		SpoutPlayer player = event.getPlayer();
		player.getMainScreen().getActivePopup().removeWidget(list);
		player.getMainScreen().getActivePopup().attachWidget(plugin, config);
	}
}