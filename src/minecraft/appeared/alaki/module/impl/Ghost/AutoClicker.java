package appeared.alaki.module.impl.Ghost;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.events.impl.UpdateEvent;
import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import appeared.alaki.settings.impl.NumberSetting;
import appeared.alaki.utils.math.Stopwatch;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import org.apache.commons.lang3.RandomUtils;
import org.lwjgl.input.Mouse;

@Getter@Setter
public class AutoClicker extends Module {
    public NumberSetting minCPS = new NumberSetting("Min CPS",9,1,20,1);
    public NumberSetting maxCPS = new NumberSetting("Max CPS",14,1,20,1);
    private final Stopwatch timer = new Stopwatch();

    public AutoClicker(){
        super("AutoClicker","Automatically click", Category.GHOST, ServerType.All);
        this.addSettings(minCPS,maxCPS);
    }

    @Subscribe
    public void onUpdate(UpdateEvent e) {
        if (Minecraft.getMinecraft().currentScreen == null && Mouse.isButtonDown(0)) {
            if (timer.hasReached(1000 / RandomUtils.nextInt((int) minCPS.getValue(), (int) maxCPS.getValue()))) {
                KeyBinding.setKeyBindState(-100, true);
                KeyBinding.onTick(-100);
                timer.reset();
            } else {
                KeyBinding.setKeyBindState(-100, false);
            }
        }
    }
}
