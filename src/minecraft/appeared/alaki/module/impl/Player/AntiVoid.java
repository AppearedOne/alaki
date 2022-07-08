package appeared.alaki.module.impl.Player;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.UpdateEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.ModeSetting;
import appeared.alaki.utils.chat.ChatUtil;
import appeared.alaki.utils.player.MoveUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.AxisAlignedBB;

@Getter@Setter
public class AntiVoid extends Module {
	public ModeSetting mode = new ModeSetting("Mode", "BMC", "NoRules");
	
    public AntiVoid(){
        super("AntiVoid","Do not do da fall", Category.PLAYER, ServerType.All);
        this.addSettings(mode);
    }

    @Subscribe
    public void onUpdate(UpdateEvent e){
        if(!isBlockUnder() && mc.thePlayer.fallDistance > 3){
            e.setY(15);
            mc.thePlayer.fallDistance = 0;
        }
    }

    private boolean isBlockUnder() {
        if (!(mc.thePlayer.posY < 0.0D)) {
            for (int offset = 0; offset < (int) mc.thePlayer.posY + 2; offset += 2) {
                AxisAlignedBB bb = mc.thePlayer.getEntityBoundingBox().offset(0.0D, -offset, 0.0D);
                if (!Minecraft.getMinecraft().theWorld.getCollidingBoundingBoxes(mc.thePlayer, bb).isEmpty()) {
                    return true;
                }
            }

        }
        return false;
    }


}
