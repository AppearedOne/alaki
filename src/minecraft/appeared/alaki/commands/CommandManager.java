package appeared.alaki.commands;

import com.google.common.eventbus.Subscribe;

import appeared.alaki.Alaki;
import appeared.alaki.commands.impl.BindCommand;
import appeared.alaki.commands.impl.ConfigCommand;
import appeared.alaki.commands.impl.FriendCommand;
import appeared.alaki.events.impl.ChatEvent;
import appeared.alaki.utils.chat.ChatUtil;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
        registerCommands();
        Alaki.getInstance().getEventBus().register(this);
    }

    @Subscribe
    public void callCommand(ChatEvent event) {
        if (!event.getMessage().startsWith(".")) return;
        event.setCanceled(true);
        String cmd = event.getMessage();
        String[] split = cmd.split(" ");
        String command = split[0];
        String args = cmd.substring(command.length()).trim();
        for (Command command1 : commands) {
            String cmdName = "." + command1.getClass().getAnnotation(CommandData.class).name();
            if (cmdName.equalsIgnoreCase(command)) {
                try {
                    command1.run(args.split(" "));
                } catch (Exception e) {
                    ChatUtil.log("Invalid command usage.");
                }
                break;
            } else {
                for (String alias : command1.getClass().getAnnotation(CommandData.class).aliases()) {
                    String aliasName = "." + alias;
                    if (aliasName.equalsIgnoreCase(command)) {
                        try {
                            command1.run(args.split(" "));
                        } catch (Exception e) {
                            ChatUtil.log("Invalid command usage.");
                        }
                        break;
                    }
                }
            }
        }
    }

    public void clearCommands() {
        commands.clear();
    }

    public void registerCommands() {
        commands.add(new FriendCommand());
        commands.add(new ConfigCommand());
        commands.add(new BindCommand());

    }

    @SuppressWarnings("all")
    public ArrayList<Command> getCommands() {
        return this.commands;
    }
}
