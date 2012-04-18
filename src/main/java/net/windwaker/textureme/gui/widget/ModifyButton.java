package net.windwaker.textureme.gui.widget;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.gui.container.ConfigMenu;
import net.windwaker.textureme.gui.container.ModifierSelector;

import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.PopupScreen;

public class ModifyButton extends GenericButton {
	private TextureMe plugin;
	private ConfigMenu config;
	private ModifierSelector selector;
	
	public ModifyButton(TextureMe plugin, ConfigMenu config, ModifierSelector selector) {
		super("Modify");
		this.plugin = plugin;
		this.config = config;
		this.selector = selector;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		PopupScreen popup = event.getPlayer().getMainScreen().getActivePopup();
		popup.removeWidget(config);
		popup.attachWidget(plugin, selector);
	}
}