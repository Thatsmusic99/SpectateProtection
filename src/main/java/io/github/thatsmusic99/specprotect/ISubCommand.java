package io.github.thatsmusic99.specprotect;

import org.bukkit.command.CommandSender;

public interface ISubCommand {

    String getCmdName();

    String getPermission();

    void fire(String[] args, CommandSender cs);

    String getUsage();

    String getDescription();
}
