package appeared.alaki.module.impl.Movement;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.EventType;
import appeared.alaki.events.impl.UpdateEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.ModeSetting;
import appeared.alaki.utils.packet.PacketUtil;
import appeared.alaki.utils.player.MoveUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

@Getter
@Setter
public class NoSlowdown extends Module {
    public ModeSetting mode = new ModeSetting("Mode", "Vanilla", "Watchdog");

    public NoSlowdown() {
        super("NoSlowdown", "Prevent slowdown of items", Category.MOVEMENT, ServerType.All);
        this.addSettings(mode);
    }

    @Subscribe
    public void onUpdate(UpdateEvent e) {
        this.setSuffix(mode.getMode());
        if(e.getType() == EventType.Pre) {
            if (mc.thePlayer.isUsingItem() && MoveUtil.isMoving()) {
                if (mc.thePlayer.ticksExisted % 8 == 0) {
                    PacketUtil.sendPacket(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0, 0, 0));
                    mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(mc.thePlayer.inventory.getCurrentItem()));                } else {

                }
            }
        }
    }
}
