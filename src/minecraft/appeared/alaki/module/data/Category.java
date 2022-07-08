package appeared.alaki.module.data;

import java.awt.*;

public enum Category {
    /*
    COMBAT("Combat", new Color(184, 55, 55)),
    MOVEMENT("Movement",new Color(63, 117, 187)),
    PLAYER("Player", new Color(83, 208, 84)),
    RENDER("Render", new Color(137, 33, 203)),
    EXPLOIT("Exploit", new Color(234, 203, 62)),
    SETTINGS("Settings", new Color(184, 55, 55));

     */

    COMBAT("Combat", new Color(92, 89, 178)),
    MOVEMENT("Movement",new Color(89, 178, 166)),
    PLAYER("Player", new Color(178, 89, 145)),
    RENDER("Render", new Color(89, 138, 178)),
    EXPLOIT("Exploit", new Color(122, 89, 178)),
	GHOST("Ghost", new Color(15, 170, 184)),
	CLIENT("Client", new Color(15, 170, 184));

    private String name;
    private Color color;

    Category(String s, Color color) {
        this.name = s;
        this.color = color;
    }

    public String getName(){
        return name;
    }

    public Color getColor(){
        return this.color;
    }
}
