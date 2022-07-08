package appeared.alaki.module.impl.Movement;

import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.NumberSetting;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Timer extends Module {
    public NumberSetting timer = new NumberSetting("Timer",1,0.05,0.1,5);
    public Timer(){
        super("Timer","Increase game speed", Category.MOVEMENT, ServerType.All);
        this.addSettings(timer);
    }
}
