package appeared.alaki.module.impl.Combat;

import appeared.alaki.events.EventType;
import appeared.alaki.events.impl.PacketEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class WTap extends Module {
	
	public WTap() {
        super("WTap", "Wtaps", Category.GHOST, ServerType.All);
	}
	
	public void onSendPacket(PacketEvent e){
		if(e.getType() == EventType.Outgoing) {}
        if(e.getPacket() instanceof C02PacketUseEntity) {
            C02PacketUseEntity packet = (C02PacketUseEntity)e.getPacket();
            if(packet.getAction() == C02PacketUseEntity.Action.ATTACK) {
                mc.thePlayer.sendQueue.addToSendQueueSilent(new C0BPacketEntityAction(mc.thePlayer, C0BPacketEntityAction.Action.STOP_SPRINTING));

            }
        }
    }

}
