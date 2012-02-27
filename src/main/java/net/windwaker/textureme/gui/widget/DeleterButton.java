package net.windwaker.textureme.gui.widget;

import net.windwaker.textureme.TextureMe;
import net.windwaker.textureme.configuration.Packs;
import org.getspout.spoutapi.event.screen.ButtonClickEvent;
import org.getspout.spoutapi.gui.GenericButton;
import org.getspout.spoutapi.gui.ListWidgetItem;

public class DeleterButton extends GenericButton {
	
	private final TexturePackList list;
	private final TextureMe plugin = TextureMe.getInstance();
	
	public DeleterButton(TexturePackList list) {
		super("Delete");
		this.list = list;
	}
	
	@Override
	public void onButtonClick(ButtonClickEvent event) {
		Packs packs = TextureMe.getInstance().getPacks();
		ListWidgetItem item = list.getSelectedItem();
		String pack = item.getTitle();
		packs.deletePack(packs.getPackId(pack));
		list.removeItem(item);
		plugin.sendNotification(event.getPlayer(), "Pack removed!");
	}
}