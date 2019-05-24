package io.github.thatsmusic99.specprotect;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class InfoCommand implements ISubCommand {

    public String getCmdName() {
        return "info";
    }

    public String getPermission() {
        return "sp.maincommand.info";
    }

    public void fire(String[] args, CommandSender cs) {
        cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " Plugin information:");
        cs.sendMessage(ChatColor.DARK_AQUA + "Name: " + ChatColor.GREEN + CoreClass.getInstance().getDescription().getName());
        cs.sendMessage(ChatColor.DARK_AQUA + "Author: " + ChatColor.GREEN + CoreClass.getInstance().getDescription().getAuthors().get(0));
        cs.sendMessage(ChatColor.DARK_AQUA + "Version: " + ChatColor.GREEN + CoreClass.getInstance().getDescription().getVersion());
    }

    public String getUsage() {
        return "/sp info";
    }

    @Override
    public String getDescription() {
        return "Displays plugin information.";
    }
}
