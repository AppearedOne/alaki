package appeared.alaki.gui.clickGui.impl.settings;

import java.awt.Color;

import appeared.alaki.gui.clickGui.impl.Button;
import appeared.alaki.settings.Setting;
import appeared.alaki.settings.impl.ModeSetting;
import appeared.alaki.settings.impl.NumberSetting;
import appeared.alaki.utils.font.Fonts;
import appeared.alaki.utils.render.ColorUtil;
import appeared.alaki.utils.render.RenderUtil;
import net.minecraft.client.gui.Gui;

public class Slider extends SetBase {

    public Button parent;
    public double y;

    public Slider(Setting set, Button parent) {
        this.setting = set;
        this.parent = parent;
    }
    double x;
    double width;
    NumberSetting mode;
    boolean sliding;
    int height;

    public double drawScreen(int mouseX, int mouseY, float partialTicks, double settingHeight) {
        //if(sliding && !RenderUtil.isHovered(mouseX, mouseY, x, y, x + width, y + height))
        //    sliding = false;

        height = 12;
        mode = (NumberSetting) setting;
        x = parent.parent.x;
        width = parent.parent.width;
        y = settingHeight + parent.y + parent.height;

        if(sliding){
            double getValue = mouseX - (x);
            if(mode.getMin() + ((mode.getMax() - mode.getMin()) / width) * getValue < mode.getMax() &&
                    mode.getMin() + ((mode.getMax() - mode.getMin()) / width) * getValue > mode.getMin())
            mode.setValue(mode.getMin() + ((mode.getMax() - mode.getMin()) / width) * getValue);
        }

        Gui.drawRect(x, y, x + width, y + height, 0xFF252525);
        if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Astolfo") { RenderUtil.drawRect(x, y, x + (((mode.getValue() - mode.getMin()) / (mode.getMax() - mode.getMin())) * width), y + height, ColorUtil.getAstolfoColor(20000000, 1).getRGB()); }
        if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "White") { RenderUtil.drawRect(x, y, x + (((mode.getValue() - mode.getMin()) / (mode.getMax() - mode.getMin())) * width), y + height, -1); }
        if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Blue") { RenderUtil.drawRect(x, y, x + (((mode.getValue() - mode.getMin()) / (mode.getMax() - mode.getMin())) * width), y + height, new Color(89, 138, 178).getRGB()); }
        if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Green") { RenderUtil.drawRect(x, y, x + (((mode.getValue() - mode.getMin()) / (mode.getMax() - mode.getMin())) * width), y + height, new Color(89, 178, 166).getRGB()); }
        if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Purple") { RenderUtil.drawRect(x, y, x + (((mode.getValue() - mode.getMin()) / (mode.getMax() - mode.getMin())) * width), y + height, new Color(92, 89, 178).getRGB()); }
        //RenderUtil.drawRect(x, y, x + (((mode.getValue() - mode.getMin()) / (mode.getMax() - mode.getMin())) * width), y + height, this.parent.parent.color.darker().getRGB());
        Fonts.SFReg18.drawStringWithShadow(mode.getName(), x + 3, y + ((height - Fonts.SFReg18.getHeight()) / 2) + 0.5, -1);
        Fonts.SFReg18.drawStringWithShadow(Math.floor(mode.getValue() * 1000) / 1000 + "", x + width - 3 - Fonts.SFReg18.getStringWidth(String.valueOf(Math.floor(mode.getValue() * 1000) / 1000)), y + ((height - Fonts.SFReg18.getHeight()) / 2) + 0.5, -1);
        return height;
    }

    public double getHeight(){
        return height;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if(RenderUtil.isHovered(mouseX, mouseY, x, y, x + width, y + height)) {
            sliding = true;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        sliding = false;
    }

    public boolean isHidden() {
        return setting.isHidden();
    }
}
