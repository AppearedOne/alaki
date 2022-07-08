package appeared.alaki.utils.logging;

import appeared.alaki.Alaki;
import appeared.alaki.utils.render.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class LoggingUtil {
    public static void log(String message) {
        System.out.println("[" + Alaki.name + "] " + message + ColorUtil.RESET);
    }

    public static void infoLog(String message) {
        System.out.println(ColorUtil.BLUE_BRIGHT + "[INFO] " + message + ColorUtil.RESET);
    }

    public static void warningLog(String message) {
        System.out.println(ColorUtil.YELLOW + "[WARNING] " + message + ColorUtil.RESET);
    }

    public static void errorLog(String message) {
        System.out.println(ColorUtil.RED_BRIGHT + "[ERROR] " + message + ColorUtil.RESET);
    }

    public static void successLog(String message) {
        System.out.println(ColorUtil.GREEN_BRIGHT + "[SUCCESS] " + message + ColorUtil.RESET);
    }


}
