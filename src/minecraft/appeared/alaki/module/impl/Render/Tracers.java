package appeared.alaki.module.impl.Render;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.Render3DEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.BooleanSetting;
import appeared.alaki.settings.impl.NumberSetting;
import appeared.alaki.utils.render.ColorUtil;
import appeared.alaki.utils.render.RenderUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

@Getter@Setter
public class Tracers extends Module {

    public static NumberSetting red = new NumberSetting("Red",45,1,1,255);
    public static NumberSetting green = new NumberSetting("Green",24,1,1,255);
    public static NumberSetting blue = new NumberSetting("Blue",158,1,1,255);
    public static BooleanSetting rainbow = new BooleanSetting("Sync", true);


    public Tracers(){
        super("Tracers","Trace ez", Category.RENDER, ServerType.All);
        this.addSettings(red,green,blue,rainbow);
    }

    @Subscribe
    public void onTracer(Render3DEvent e){
        for (Entity object : Minecraft.getMinecraft().theWorld.loadedEntityList) {
            if(object instanceof EntityPlayer) {
                if(!object.isInvisible() && object != mc.thePlayer) {
                    RenderUtil.drawLineToPosition(object.posX,object.posY,object.posZ, getColor());
                }
            }
        }
    }

    public static int getColor() {
        return rainbow.getValue() ? ColorUtil.getColor(0) : new Color((int) red.getValue(), (int) green.getValue(), (int) blue.getValue()).getRGB();
    }
}
