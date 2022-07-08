package appeared.alaki.commands.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import appeared.alaki.Alaki;
import appeared.alaki.commands.Command;
import appeared.alaki.commands.CommandData;
import appeared.alaki.config.ConfigManager;
import appeared.alaki.utils.chat.ChatUtil;

import org.lwjgl.Sys;

import java.io.File;
import java.util.Arrays;

@CommandData(
        name = "config",
        description = "Handles configs"
)
public class ConfigCommand extends Command {

    @Override
    public void run(String[] args) {
        try {
            System.out.println(Arrays.toString(args));

            switch (args[0].toLowerCase()) {
                case "save": {
                    Alaki.getInstance().getConfigManager().saveConfig(args[1]);
                    ChatUtil.log("Saved Config " + args[1]);
                    return;
                }
                case "load": {
                    if (args.length == 3) {
                        Alaki.getInstance().getConfigManager().loadConfig(args[1], args[2].equals("keys"));
                    } else {
                        Alaki.getInstance().getConfigManager().loadConfig(args[1], false);
                    }
                    // .CONFIG BLOCKS ADESGOJHAOG
                    return;
                }
                case "reload": {
                    Alaki.getInstance().getConfigManager().loadConfigs();
                    ChatUtil.log("Reloaded Configs");
                    return;
                }
                case "delete":
                case "remove": {
                    Alaki.getInstance().getConfigManager().removeConfig(args[1]);
                    ChatUtil.log("Removed Config " + args[1]);
                    return;
                }
                case "list": {
                    for (File file : Alaki.getInstance().getConfigManager().getDir().listFiles()) {
                        ChatUtil.log("- " + file.getName().replaceAll(".sleek", ""));
                    }
                    return;
                }
                

            }
            ChatUtil.log(".config save <configName>");
            ChatUtil.log(".config load <configName>");
            ChatUtil.log(".config remove <configName>");
            ChatUtil.log(".config online <list | load> [name]");
            ChatUtil.log(".config reload");
            ChatUtil.log(".config list");
        } catch (Throwable e) {
            e.printStackTrace();
            ChatUtil.log(".config save <configName>");
            ChatUtil.log(".config load <configName>");
            ChatUtil.log(".config remove <configName>");
            ChatUtil.log(".config online <list | load> [name]");
            ChatUtil.log(".config reload");
            ChatUtil.log(".config list");
        }
    }
}
