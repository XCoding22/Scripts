package scriptss;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

import scriptss.strategies.BuyingStrategy;
import scriptss.strategies.UnpackStrategy;

@ScriptManifest(author = "Scriptss", category = Category.MONEYMAKING, description = "Buys and unboxes steel armour sets", name = "IBoxer", servers = {
		"ikov" }, version = 1.0D)
public class IBoxer extends Script {
	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	public static int option = 0;

	public boolean onExecute() {
		JDialog d = new JDialog();
		d.setPreferredSize(new Dimension(250, 90));
		d.setLocationRelativeTo((Component) null);
		d.setDefaultCloseOperation(2);
		FlowLayout c = new FlowLayout();
		c.setHgap(5);
		c.setVgap(5);
		d.setLayout(c);
		JComboBox<String> b = new JComboBox<String>();
		b.setPreferredSize(new Dimension(150, 30));
		b.setFocusable(false);
		b.addItem("Unpack Boxes");
		b.addItem("Buy Boxes");
		JButton var10003 = new JButton("Start");
		var10003.setFocusable(false);
		var10003.setPreferredSize(new Dimension(60, 32));
		var10003.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selected = (String) b.getSelectedItem();
				switch (selected) {
				case "Buy Boxes":
					option = 1;
					break;
				case "Unpack Boxes":
					option = 2;
					break;
				}
				d.dispose();
			}

		});
		d.add(b);
		d.add(var10003);
		d.setTitle("IBoxes - Scriptss");
		d.pack();
		d.setVisible(true);

		while (d.isVisible()) {
			Time.sleep(500);
		}

		if (option == 1) {
			strategies.add(new BuyingStrategy());
		}

		if (option == 2) {
			strategies.add(new UnpackStrategy());
		}

		provide(strategies);
		return true;
	}
}
