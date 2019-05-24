package scriptss.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Menu;
import org.rev317.min.api.methods.Npcs;
import org.rev317.min.api.wrappers.Npc;

public class BuyingStrategy implements Strategy {
	private static int boxesCount = 0;

	public void execute() {
		try {
			if (Inventory.isFull()) {
				if (Interfaces.getOpenInterfaceId() != 5292) {
					Npc b2 = Npcs.getClosest(8948);
					System.out.println("opening bank");
					b2.interact(Npcs.Option.SECOND);
					Time.sleep(() -> Interfaces.getOpenInterfaceId() == 5292, 3000);
				}
				if (Interfaces.getOpenInterfaceId() == 5292) {
					Menu.sendAction(646, 0, 0, 21012, 0);
					Time.sleep(() -> Inventory.isEmpty(), 3000);
				}
			}
			if (Inventory.isFull()) {
				return;
			}
			if (Interfaces.getOpenInterfaceId() != 3824) {
				if (Interfaces.getBackDialogId() != 2459) {
					Npc c = Npcs.getClosest(883);
					c.interact(Npcs.Option.TALK_TO);
					Time.sleep(() -> Interfaces.getBackDialogId() == 2459, 3000);
				}
				if (Interfaces.getBackDialogId() == 2459) {
					Menu.sendAction(315, 0, 0, 2461, 0);
					Time.sleep(() -> Interfaces.getOpenInterfaceId() == 3824, 3000);
				}
			}
			if (Interfaces.getOpenInterfaceId() != 3824)
				return;
			boxesCount = Inventory.getCount(11823);
			Menu.sendAction(431, 11822, 4, 3900, 4);
			Time.sleep(() -> Inventory.getCount(11823) > getBoxesCount(), 2000);
			return;
		} catch (Exception a) {
			System.out.println("error in "); // ? ? ? ? ?
		}
	}

	public static int getBoxesCount() {
		return boxesCount;
	}

	public boolean activate() {
		return true;
	}
}
