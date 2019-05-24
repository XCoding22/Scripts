package jketelaar;

import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.LoopTask;

import javax.swing.JOptionPane;

import org.parabot.core.ui.Logger;
import org.parabot.core.ui.utils.UILog;
import org.parabot.environment.api.utils.Random;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.rev317.min.Loader;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.input.Keyboard;

@ScriptManifest(author = "JKetelaar", category = Category.OTHER, description = "Sells any item in any store. Ensure the shop interface is open and item is in your inventory", name = "JKSeller", servers = {
		"Ikov" }, version = 1.0)
public class JKSeller extends Script implements LoopTask {
	private int itemId = -1;
	private int amount = -1;

	public int loop() {
		Item item = Inventory.getItem(itemId);
		if (item == null) {
			Logger.addMessage("Could not find item in inventory");
			setState(2);
			return Random.between(750, 1500);
		}
		Menu.sendAction(53, (item.getId() - 1), item.getSlot(), 3823, item.getSlot(), 0);
		Time.sleep(() -> Loader.getClient().getBackDialogId() > 0, 500);
		Time.sleep(500, 750);
		Keyboard.getInstance().sendKeys(String.valueOf(amount), false);
		Time.sleep(250, 500);
		Keyboard.getInstance().clickKey(10);
		return Random.between(750, 1500);
	}

	public boolean onExecute() {
		if (Loader.getClient().getOpenInterfaceId() <= 0) {
			UILog.log("Store not open.", "Store interface is not open. Open the store before starting the script.", 0);
			return false;
		}
		Item[] items = Inventory.getItems();
		Object[] itemIdsAsObject = new String[items.length];
		for (int i = 0; i < items.length; i++) {
			itemIdsAsObject[i] = String.valueOf(items[i].getId());
		}
		String inputResponse = (String) JOptionPane.showInputDialog(null, "Choose item available in your inventory", "Choose item",
				3, null, itemIdsAsObject, itemIdsAsObject[0]);
		try {
			itemId = Integer.parseInt(inputResponse);
		} catch (Exception a) {
			UILog.log("Not a Number", "Given ID is invalid", 0);
			return false;
		}
		if (itemId <= 0) {
			UILog.log("Invalid number", "Given ID is invalid", 0);
			return false;
		}
		try {
			amount = Integer.parseInt(JOptionPane.showInputDialog("How many items would you like to sell per action?"));
		} catch (Exception b) {
			UILog.log("Not a Number", "Given amount is invalid", 0);
			return false;
		}
		if (amount > 0) {
			return super.onExecute();
		}
		UILog.log("Invalid number", "Given amount is invalid", 0);
		return false;
	}
}
