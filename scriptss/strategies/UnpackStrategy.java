package scriptss.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Items;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;

public class UnpackStrategy implements Strategy {
	private static int boxesCount = 0;

	public boolean activate() {
		return true;
	}

	public static int getBoxesCount() {
		return boxesCount;
	}

	public void execute() {
		try {
			if (Inventory.getCount(11823) > 5 || Inventory.getCount(11823) == 0) {
				System.out.println("more or less than 5 boxes...banking");
				if (Interfaces.getOpenInterfaceId() != 5292) {
					Npc c2 = Npcs.getClosest(8948);
					c2.interact(Npcs.Option.SECOND);
					Time.sleep(() -> Interfaces.getOpenInterfaceId() == 5292, 3000);
					if (Interfaces.getOpenInterfaceId() == 5292) {
						Menu.sendAction(646, 0, 0, 21012, 0);
						Time.sleep(() -> Inventory.isEmpty(), 3000);
						Menu.sendAction(78, 11822, 0, 18687, 0);
						Time.sleep(() -> Inventory.contains(11823), 3000);
						if (Inventory.getCount(11823) == 5) {
							Menu.sendAction(200, 0, 0, 5384, 0);
							Time.sleep(() -> Interfaces.getOpenInterfaceId() != 5292, 3000);
						}
					}
				}
			}
			if (Inventory.getCount(11823) > 0) {
				boxesCount = Inventory.getCount(11823);
				System.out.println("selecting use");
				Item c2 = Inventory.getItem(11823);
				c2.interact(Items.Option.USE);
				Time.sleep(700);
				System.out.println("using on banker");
				Npc a = Npcs.getClosest(8948);
				Menu.sendAction(582, a.getIndex(), 0, 0, 0);
				Time.sleep(() -> getBoxesCount() > Inventory.getCount(11823), 3000);
			}
		} catch (Exception b) {
			System.out.println("error in "); // ? ? ? ?
		}
	}
}
