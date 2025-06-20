package appeared.alaki.module.impl.Render;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.PacketEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.world.storage.WorldInfo;
import org.lwjgl.input.Keyboard;

@Getter@Setter
public class NoWeather extends Module {
    public NoWeather(){
        super("NoWeather", "Remove all weather from your game", Category.RENDER, ServerType.All);
    }

    @Subscribe
    public void onPacket(PacketEvent e){
        if(mc.thePlayer == null) return;
        WorldInfo worldinfo = mc.theWorld.getWorldInfo();
        worldinfo.setCleanWeatherTime(0);
        worldinfo.setRainTime(0);
        worldinfo.setThunderTime(0);
        worldinfo.setRaining(false);
        worldinfo.setThundering(false);
        
        if(e.getPacket() instanceof S2BPacketChangeGameState){
            e.setCanceled(true);
        }
    }
}
