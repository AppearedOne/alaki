package appeared.alaki.module.impl.Player;

import appeared.alaki.module.Module;
import appeared.alaki.module.data.Category;
import appeared.alaki.module.data.ServerType;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ResetVL extends Module {
    public ResetVL(){
        super("ResetVL","Resets VL", Category.PLAYER, ServerType.All);
    }


}
