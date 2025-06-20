package appeared.alaki.events.impl;

import appeared.alaki.events.Event;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.ScaledResolution;

@Getter@Setter
public class OverlayEvent extends Event {
    private ScaledResolution sr;

    public OverlayEvent(ScaledResolution sr){
        this.sr = sr;
    }

    public ScaledResolution getScaledResolution(){
        return sr;
    }
}
