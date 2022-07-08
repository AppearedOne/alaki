package appeared.alaki.events.impl;

import appeared.alaki.events.Event;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Render3DEvent extends Event {
    private final float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return partialTicks;
    }
}