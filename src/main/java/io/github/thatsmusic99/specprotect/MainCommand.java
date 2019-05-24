package io.github.thatsmusic99.specprotect;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender.hasPermission("sp.maincommand")) {
            if (args.length == 0) {
                CoreClass.getInstance().getSubCommand("help").fire(args, sender);
                return true;
            } else {
                if (CoreClass.getInstance().getSubCommand(args[0]) != null) {
                    ISubCommand cmd = CoreClass.getInstance().getSubCommand(args[0]);
                    if (sender.hasPermission(cmd.getPermission())) {
                        cmd.fire(args, sender);
                        return true;
                    }
                } else {
                    CoreClass.getInstance().getSubCommand("help").fire(args, sender);
                    return true;
                }
            }
        }
        return false;
    }
}
