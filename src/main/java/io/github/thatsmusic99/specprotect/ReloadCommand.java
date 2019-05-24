package io.github.thatsmusic99.specprotect;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements ISubCommand {

    @Override
    public String getCmdName() {
        return "reload";
    }

    @Override
    public String getPermission() {
        return "sp.maincommand.reload";
    }

    @Override
    public void fire(String[] args, CommandSender cs) {
         CoreClass.getInstance().reloadConfig();
         cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " The config has been reloaded!");
    }

    @Override
    public String getUsage() {
        return "/sp reload";
    }

    @Override
    public String getDescription() {
        return "Reloads the config.";
    }
}
