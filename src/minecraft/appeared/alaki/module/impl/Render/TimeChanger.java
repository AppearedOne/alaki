package appeared.alaki.module.impl.Render;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.PacketEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.NumberSetting;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import org.lwjgl.input.Keyboard;

@Getter@Setter
public class TimeChanger extends Module {
    public NumberSetting timeSetting = new NumberSetting("Time", 10, 0.1, 1, 24);
    public TimeChanger() {
        super("Time Changer", "Change the ingame time", Category.RENDER, ServerType.All);
        addSettings(timeSetting);
    }

    @Subscribe
    public void onPacket(PacketEvent e){
        if(mc.theWorld == null)
            return;
        long time = 20000;
        if(e.getPacket() instanceof S03PacketTimeUpdate){
            ((S03PacketTimeUpdate) e.getPacket()).worldTime = time;
            e.setCanceled(true);
        }
        mc.theWorld.setWorldTime(time);
    }
}
