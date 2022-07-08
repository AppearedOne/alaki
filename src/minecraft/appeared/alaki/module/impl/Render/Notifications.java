package appeared.alaki.module.impl.Render;

import appeared.alaki.events.EventType;
import appeared.alaki.events.impl.PacketEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.BooleanSetting;
import appeared.alaki.settings.impl.ModeSetting;

public class Notifications extends Module {
	
	public static BooleanSetting ToggleNotify = new BooleanSetting("Toggle Notify", true);
	public static BooleanSetting config = new BooleanSetting("Config Notify", true);
	public static ModeSetting visualMode = new ModeSetting("Mode", "Meth Rounded", "Simple");
	public static ModeSetting colorMode = new ModeSetting("Color","Astolfo" , "Green", "Blue", "Purple");
	public static BooleanSetting background = new BooleanSetting("Background", true);
	
	public Notifications() {
        super("Notifications", "Notifies you", Category.CLIENT, ServerType.All);
        this.addSettings(ToggleNotify, config, visualMode, colorMode, background);
        
        if(ToggleNotify.getValue()){
        	this.setSuffix("Toggle");
        }
        
        
	}
	

}
