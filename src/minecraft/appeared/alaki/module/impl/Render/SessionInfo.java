package appeared.alaki.module.impl.Render;

import java.awt.Color;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.Alaki;
import appeared.alaki.events.impl.OverlayEvent;
import appeared.alaki.events.impl.Render2DEvent;
import appeared.alaki.gui.guiElement.GuiElement;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.BooleanSetting;
import appeared.alaki.settings.impl.ModeSetting;
import appeared.alaki.settings.impl.NumberSetting;
import appeared.alaki.utils.font.Fonts;
import appeared.alaki.utils.render.ColorUtil;
import appeared.alaki.utils.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class SessionInfo extends Module {
	public BooleanSetting backg = new BooleanSetting("Background", true);
	public NumberSetting offset = new NumberSetting("Offset", 10, 1, 1, 150);
	public ModeSetting mode = new ModeSetting("Mode", "Round", "RGB");
	public ModeSetting clr = new ModeSetting("Color", "Astolfo", "Green", "Blue", "Purple", "White");
    public SessionInfo() {
        super("Session", "Session information", Category.CLIENT, ServerType.All);
        clr.setParent(mode, "Round");
        this.addSettings(backg, offset, mode ,clr);
    }
    //int yplus = (int) offset.getValue();
    int yplus = 50;
    public GuiElement guiElement = new GuiElement("Session", this,5,15 + yplus,150,60);

    @Subscribe
    public void onOverlay(OverlayEvent e) {
    	if(mode.getMode() == "Round") {
    	Color clr1 = new Color(0,0,0);
    	if(clr.getMode() == "Blue") { 
    		clr1 = new Color(89, 138, 178);
    	}
    	if(clr.getMode() == "Purple") { 
    		clr1 = new Color(92, 89, 178);
    	}
    	if(clr.getMode() == "Green") { 
    		clr1 = new Color(89, 178, 166);
    	}
    	if(clr.getMode() == "White") { 
    		clr1 = new Color(190,190,190);
    	}
    	if(clr.getMode() == "Astolfo") { 
    		clr1 = ColorUtil.getAstolfoColor(20000000, 1);
    	}
        ScaledResolution sr = new ScaledResolution(mc);
        int kills = Alaki.hypixelStatus.getSessionKills();
        guiElement.renderStart();
        	
        if(backg.getValue())
        	RenderUtil.drawRoundedRect(0,10,150,50,15, 0x9f232323);
        RenderUtil.drawOutlinedRoundedRect(0,10,150,49,12,3, clr1.getRGB());
        Fonts.axi18.drawCenteredString("Session Information",112 / 2f,16, clr1.getRGB());
        Fonts.SFReg18.drawStringWithShadow("Time Played: " + Alaki.hypixelStatus.getSessionLengthString(), 10,28,-1);
        Fonts.SFReg18.drawStringWithShadow("Session Kills: " + kills, 10,30 + (Fonts.SFReg18.getHeight() + 1),-1);
        Fonts.SFReg18.drawStringWithShadow("Server: " + (mc.isSingleplayer() ? "Singleplayer" : Minecraft.getMinecraft().getCurrentServerData().serverIP), 10,40 + (Fonts.SFReg18.getHeight() + 1),-1);

        //RenderUtil.drawRoundedRect(10,20,130,2,2, 0x9fffffff);
    	}
    	else if (mode.getMode() == "RGB") {
    		
    		ScaledResolution sr = new ScaledResolution(mc);
            int kills = Alaki.hypixelStatus.getSessionKills();
            guiElement.renderStart();
            	
            RenderUtil.drawRoundedRect(0,13,145,50,1, new Color(1,1,1, 178).getRGB());
            RenderUtil.drawRoundedRect(0,10,145,3,1, new Color(ColorUtil.getAstolfoColor(2000000, 2).getRed(), ColorUtil.getAstolfoColor(2000000, 2).getGreen(), ColorUtil.getAstolfoColor(2000000, 2).getBlue(), 140).getRGB());
            Fonts.axi18.drawCenteredString("Session Information",112 / 2f,16, -1);
            Fonts.SFReg18.drawStringWithShadow("Time Played: " + Alaki.hypixelStatus.getSessionLengthString(), 10,28,-1);
            Fonts.SFReg18.drawStringWithShadow("Session Kills: " + kills, 10,30 + (Fonts.SFReg18.getHeight() + 1),-1);
            Fonts.SFReg18.drawStringWithShadow("Server: " + (mc.isSingleplayer() ? "Singleplayer" : Minecraft.getMinecraft().getCurrentServerData().serverIP), 10,40 + (Fonts.SFReg18.getHeight() + 1),-1);
    		
    	}
        guiElement.renderEnd();
    }
}
