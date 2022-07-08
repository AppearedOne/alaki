package appeared.alaki.events.impl;

import appeared.alaki.events.Event;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class KeyEvent extends Event {
    private int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
