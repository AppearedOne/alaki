package appeared.alaki.module.impl.Render;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.PacketEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AntiBlind extends Module {

    public AntiBlind() {
        super("AntiBlind", "Allows u to see, you absolute chinese ninja", Category.RENDER, ServerType.All);
    }

    @Subscribe
    public void onNinjaEvent(PacketEvent e){
        for(PotionEffect potion : mc.thePlayer.getActivePotionEffects()){
            if(isPotionBlackListed(potion)){
                mc.thePlayer.removePotionEffect(potion.getPotionID());
            }
        }
    }

    private boolean isPotionBlackListed(PotionEffect potion) {
        Integer[] blackListedIds = new Integer[] { Potion.blindness.getId(), Potion.confusion.getId() };

        for(int id : blackListedIds){
            if(id == potion.getPotionID()) return true;
        }
        return false;
    }
}
