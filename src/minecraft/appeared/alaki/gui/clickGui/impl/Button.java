package appeared.alaki.gui.clickGui.impl;

import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

import appeared.alaki.gui.clickGui.impl.settings.*;
import appeared.alaki.module.Module;
import appeared.alaki.settings.Setting;
import appeared.alaki.settings.impl.BooleanSetting;
import appeared.alaki.settings.impl.KeybindSetting;
import appeared.alaki.settings.impl.ModeSetting;
import appeared.alaki.settings.impl.NumberSetting;
import appeared.alaki.utils.animations.Animation;
import appeared.alaki.utils.font.Fonts;
import appeared.alaki.utils.render.ColorUtil;
import appeared.alaki.utils.render.RenderUtil;

import java.awt.*;
import java.util.ArrayList;

public class Button {

    public Module mod;
    public Panel parent;
    public int index, height;
    public double y;

    public ArrayList<SetBase> settings = new ArrayList<>();
    private boolean expanded;

    public Button(Panel parent, Module mod, int index) {
        this.parent = parent;
        this.mod = mod;
        this.index = index;
        this.height = 17;

        for (Setting set : mod.getSettings()) {
            if (set instanceof ModeSetting) {
                settings.add(new Mode(set, this));
            }
            if (set instanceof NumberSetting) {
                settings.add(new Slider(set, this));
            }
            if(set instanceof BooleanSetting){
                settings.add(new CheckBox(set, this));
            }
            if (set instanceof KeybindSetting) {
                settings.add(new Keybind(set, this));
            }
        }
    }

    double settingHeight = 0;
    boolean openedOnce = false;

    Animation guiAnim = new Animation();

    public double drawScreen(int mouseX, int mouseY, float partialTicks, double offset) {
        settingHeight = 0;
        for (SetBase s : settings) {
            if (s.canRender())
                settingHeight += s.getHeight();
        }

        y = parent.y + parent.barHeight + (index * height) + offset;

        Color bgColor = new Color(0xFF1a1a1a);

        if (RenderUtil.isHovered(mouseX, mouseY, parent.x, y, parent.x + parent.width, y + height))
            bgColor = new Color(0xFF1c1c1c);
        if (mod.isToggled()) {
        	if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Blue") { 
        		bgColor = new Color(89, 138, 178);
        	}
        	if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Purple") { 
        		bgColor = new Color(92, 89, 178);
        	}
        	if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Green") { 
        		bgColor = new Color(89, 178, 166);
        	}
        	if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "White") { 
        		bgColor = new Color(190,190,190);
        	}
        	if(appeared.alaki.module.impl.Render.ClickGUI.clickGuiColorModeStringVal == "Astolfo") { 
        		bgColor = ColorUtil.getAstolfoColor(20000000, 1);
        	}
        	
        }
        Gui.drawRect(parent.x, y, parent.x + parent.width, y + height, bgColor.getRGB());
        Fonts.SFReg18.drawStringWithShadow(mod.getName(), parent.x + 3, y + ((height - Fonts.SFReg18.getHeight()) / 2f) + 0.5, mod.isToggled() ? -1 : 0x80FFFFFF);

        guiAnim.setSpeed(0.2);
        guiAnim.setAmount((int) settingHeight);
        guiAnim.setReverse(!expanded);
        guiAnim.updateAnimation();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        RenderUtil.prepareScissorBox(parent.x, y, parent.x + parent.width, y + height + guiAnim.getValue());

        int tempSettingsHeight = 0;
        for (SetBase s : settings) {
            if (s.canRender()) {
                tempSettingsHeight += s.drawScreen(mouseX, mouseY, partialTicks, tempSettingsHeight);
            }
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        settingHeight = guiAnim.getValue();
        return guiAnim.getValue();
    }

    public void keyTyped(char typedChar, int keyCode) {
        if (expanded) {
            for (SetBase s : settings) {
                if (!s.isHidden()) {
                    s.keyTyped(typedChar, keyCode);
                }
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (RenderUtil.isHovered(mouseX, mouseY, parent.x, y, parent.x + parent.width, y + height)) {
            switch (mouseButton) {
                case 0:
                    mod.toggle();
                    break;
                case 1:
                    guiAnim.start();
                    openedOnce = true;
                    expanded = !expanded;
                    break;
            }
        } else if (expanded) {
            for (SetBase s : settings)
                if (!s.isHidden())
                    s.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if(expanded)
            for (SetBase s : settings) {
                if(!s.isHidden())
                    s.mouseReleased(mouseX, mouseY, state);
            }
    }

    public double getHeight() {
        return settingHeight + height;
    }

    public void onOpenGUI() {
    }
}