package scriptss;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.parabot.core.Context;
import org.parabot.core.reflect.RefClass;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

@ScriptManifest(author="Scriptss", category=Category.OTHER, description="Changes your UUID", name="UUID Changer", servers={"Ikov"}, version=1.1)
public class UUIDChanger extends Script {
    private final ArrayList<Strategy> strategies = new ArrayList<Strategy> ();
    
    public boolean onExecute() {
        new RefClass((Object)Context.getInstance().getServerProvider()).getMethod("refreshProcess").invoke();
        JOptionPane.showMessageDialog(null, "UUID changed, Happy botting!");
        provide(strategies);
        return true;
    }
}
