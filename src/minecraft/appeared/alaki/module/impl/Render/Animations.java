package appeared.alaki.module.impl.Render;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.UpdateEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.ModeSetting;
import appeared.alaki.settings.impl.NumberSetting;

public class Animations extends Module {
    public static ModeSetting mode = new ModeSetting("Mode", "Diablo", "Slide", "Exhi", "Swing");
    public static NumberSetting scale = new NumberSetting("Scale", 1, 0.05, 0.1, 2);
    public static NumberSetting speed = new NumberSetting("Speed", 1, 0.05, 0.05, 3);
    public Animations() {
        super("Animations", "Animate your weapon", Category.RENDER, ServerType.All);
        this.addSettings(mode, scale, speed);
    }

    @Subscribe
    public void onUpdate(UpdateEvent e) {
        this.setSuffix(mode.getMode());
    }
}
