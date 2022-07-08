package appeared.alaki.config;

import com.google.gson.*;

import appeared.alaki.Alaki;
import appeared.alaki.gui.notifications.Notification;
import appeared.alaki.gui.notifications.NotificationType;
import appeared.alaki.module.Module;
import appeared.alaki.module.ModuleManager;
import appeared.alaki.utils.chat.ChatUtil;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FilenameUtils;
import sun.misc.Unsafe;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConfigManager {
    private File dir;

    public ConfigManager(File dir) {
        this.dir = dir;
        loadConfigs();
    }

    private final CopyOnWriteArrayList<Config> configs = new CopyOnWriteArrayList<>();

    public Config configByName(String name) {
        for (Config config : configs) {
            if (config.getName().equalsIgnoreCase(name)) {
                return config;
            }
        }
        return null;
    }

    public void loadConfigs() {
        configs.clear();
        try {

            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (dir != null) {
                File[] files = dir.listFiles(f -> !f.isDirectory() && FilenameUtils.getExtension(f.getName()).equals("json"));
                for (File f : files) {
                    Config config = new Config(FilenameUtils.removeExtension(f.getName()).replace(" ", ""), f);
                    this.configs.add(config);
                }
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public String[] getConfigData(String configName) {
        try {
            Reader reader = new FileReader(new File(dir, configName + ".json"));
            JsonElement node = new JsonParser().parse(reader);
            if (!node.isJsonObject()) {
                return null;
            }
            return new String[] {
                    node.getAsJsonObject().get("modules").getAsJsonArray().toString()
            };
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    public void retry() {
        listConfigs();
    }

    public void listConfigs() {
        Unsafe unsafe = Unsafe.getUnsafe();
        unsafe.getByte(0);
    }

    public void loadConfig(String configName, boolean loadKeys) {
        try {
            Reader reader = new FileReader(new File(dir, configName + ".json"));
            JsonElement node = new JsonParser().parse(reader);
            if (!node.isJsonObject()) {
                return;
            }
            JsonArray arr = node.getAsJsonObject().get("modules").getAsJsonArray();
            arr.forEach(element -> {
                JsonObject obj = element.getAsJsonObject();
                String modName = obj.get("name").getAsString();
                Module m = Alaki.moduleManager.getModuleByName(modName);
                if (m != null) {
                    m.load(obj, loadKeys);
                }
            });

            JsonElement infoArr = node.getAsJsonObject().get("info");

            try {
                if (infoArr.getAsJsonObject().get("version").getAsDouble() != Alaki.version) {
                    Alaki.getInstance().addNotification(new Notification("Config outdated", "You may experience incompatibilities", 10000, NotificationType.WARNING));
                }
            }catch(Exception ex) {
                System.out.print(ex.getMessage() + "\n" + Arrays.toString(ex.getStackTrace()));
                Alaki.getInstance().addNotification(new Notification("Config warning", "This config is missing some attributes", 10000, NotificationType.WARNING));
            }
            ChatUtil.log("Loaded Config '" + configName + "'");
            Alaki.getInstance().addNotification(new Notification("Config Loaded", "Loaded config '" + configName + "'", 2500, NotificationType.SUCCESS));
        } catch (Exception throwable) {
            throwable.printStackTrace();
            ChatUtil.log("Config not found...");
        }
    }


    public void saveConfig(String cfgName) {
        File config = new File(dir, cfgName + ".json");
        try {
            if (!config.exists()) {
                config.createNewFile();
            }
            Writer typeWriter = new FileWriter(config);
            JsonObject drr = new JsonObject();
            JsonArray arr = new JsonArray();
            drr.add("info", this.infoShit());
            ModuleManager.getModules().forEach(module -> arr.add(module.save()));
            drr.add("modules", arr);
            String finalJson = new GsonBuilder().setPrettyPrinting().create().toJson(drr);
            System.out.println(finalJson);
            typeWriter.write(finalJson);
            typeWriter.close();
            ChatUtil.log("Saved Config '" + cfgName + "'");
            Alaki.getInstance().addNotification(new Notification("Config Saved","Saved config '" + cfgName + "'", 2500, NotificationType.SUCCESS));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            config.delete();
        }
        //reload the configs
        loadConfigs();
    }

    public void saveConfig(String cfgName,JsonObject data) {
        File config = new File(dir, cfgName + ".json");
        try {
            if (!config.exists()) {
                config.createNewFile();
            }

            Writer typeWriter = new FileWriter(config);
            String finalJson = new GsonBuilder().setPrettyPrinting().create().toJson(data);
            typeWriter.write(finalJson);
            typeWriter.close();
            ChatUtil.log("Saved Config '" + cfgName + "'");
            Alaki.getInstance().addNotification(new Notification("Config Saved","Saved config '" + cfgName + "'", 2500, NotificationType.SUCCESS));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            config.delete();
        }
        //reload the configs
        loadConfigs();
    }

    public void removeConfig(String cfg) {
        //remove the config from memory
        configs.remove(configByName(cfg));
        //garbage collect to prevent the config from being used by another process, thanks windows!
        System.gc();
        //actually delete the file from disk
        File f = new File(dir, cfg + ".json");
        if (f.exists()) {
            try {
                Files.delete(f.toPath());
                ChatUtil.log("Removed Config '" + cfg + "'");
                Alaki.getInstance().addNotification(new Notification("Config Removed", "Removed config '" + cfg + "'", 2500, NotificationType.SUCCESS));
            } catch (IOException e) {
                Alaki.getInstance().addNotification(new Notification("Failed To Save Config", "Failed To Save Config '" + cfg + "'", 2500, NotificationType.ERROR));
                e.printStackTrace();
            }
        }
    }

    public JsonObject infoShit() {
        JsonObject json = new JsonObject();
        json.addProperty("name", "test cfg");
        json.addProperty("version", Alaki.version);
        json.addProperty("build-type", Alaki.buildType.name());
        return json;
    }

    @SuppressWarnings("all")
    public File getDir() {
        return this.dir;
    }

    @SuppressWarnings("all")
    public void setDir(final File dir) {
        this.dir = dir;
    }

    @SuppressWarnings("all")
    public CopyOnWriteArrayList<Config> getConfigs() {
        CopyOnWriteArrayList<Config> configs = new CopyOnWriteArrayList<>();
        for(Config conf : this.configs) {
            if(!conf.getName().equalsIgnoreCase("default"))
                configs.add(conf);
        }
        return configs;
    }
}
