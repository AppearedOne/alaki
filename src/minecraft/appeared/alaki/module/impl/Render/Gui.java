package appeared.alaki.module.impl.Render;

import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.BooleanSetting;
import appeared.alaki.settings.impl.ModeSetting;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Gui extends Module {
    public static BooleanSetting clearChat = new BooleanSetting("Clear Chat", true);
    public static ModeSetting guiContainerAnimation = new ModeSetting("Inventory", "Scale", "Slide","Default");
    public static BooleanSetting lowerScoreboard = new BooleanSetting("Lower Scoreboard", true);

    public Gui(){
        super("Settings","Custom GUI", Category.CLIENT, ServerType.All);
        addSettings(lowerScoreboard,guiContainerAnimation, clearChat);
    }
}
