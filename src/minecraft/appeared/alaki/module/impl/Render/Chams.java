package appeared.alaki.module.impl.Render;

import java.awt.*;

import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.BooleanSetting;
import appeared.alaki.settings.impl.ModeSetting;
import appeared.alaki.settings.impl.NumberSetting;
import appeared.alaki.utils.render.ColorUtil;

public class Chams extends Module {
    public static ModeSetting mode = new ModeSetting("Mode", "Box", "Chams");
    public static NumberSetting red = new NumberSetting("Red", 250, 1, 0, 255);
    public static NumberSetting green = new NumberSetting("Green", 250, 1, 0, 255);
    public static NumberSetting blue = new NumberSetting("Blue", 250, 1, 0, 255);
    public static NumberSetting redHidden = new NumberSetting("Red Hidden", 250, 1, 0, 255);
    public static NumberSetting greenHidden = new NumberSetting("Green Hidden", 250, 1, 0, 255);
    public static NumberSetting blueHidden = new NumberSetting("Blue Hidden", 250, 1, 0, 255);
    public static BooleanSetting material = new BooleanSetting("Material", true);
    public static BooleanSetting rainbow = new BooleanSetting("Sync", true);

    public Chams() {
        super("Chams", "Chams", Category.RENDER, ServerType.All);
        this.addSettings(mode, red, green, blue, redHidden, greenHidden, blueHidden, material,rainbow);
        red.setParent(mode, "Chams");
        green.setParent(mode, "Chams");
        blue.setParent(mode, "Chams");
        redHidden.setParent(mode, "Chams");
        greenHidden.setParent(mode, "Chams");
        blueHidden.setParent(mode, "Chams");
        material.setParent(mode, "Chams");
        rainbow.setParent(mode,"Chams");
    }

    public static int getColor() {
        return rainbow.getValue() ? ColorUtil.getColor(0) : new Color((int) red.getValue(), (int) green.getValue(), (int) blue.getValue()).getRGB();
    }

    public static int getColorHidden() {
        return rainbow.getValue() ? ColorUtil.getColor(0) : new Color((int) redHidden.getValue(), (int) greenHidden.getValue(), (int) blueHidden.getValue()).getRGB();
    }
}
