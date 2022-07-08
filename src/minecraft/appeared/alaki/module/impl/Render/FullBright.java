package appeared.alaki.module.impl.Render;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.UpdateEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class FullBright extends Module {
    private float lastGam;
    public FullBright(){
        super("FullBright","Full brightness", Category.RENDER, ServerType.All);
    }

    @Override
    public void onEnable() {
        lastGam = mc.gameSettings.gammaSetting;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        mc.gameSettings.gammaSetting = lastGam;
        super.onDisable();
    }

    @Subscribe
    public void onUpdate(UpdateEvent e){
        mc.gameSettings.gammaSetting = 999;
    }
}
