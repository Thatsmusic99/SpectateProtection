package io.github.thatsmusic99.specprotect;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import util.PagedLists;

import java.util.ArrayList;
import java.util.List;

public class HelpCommand implements ISubCommand {
    @Override
    public String getCmdName() {
        return "help";
    }

    @Override
    public String getPermission() {
        return "sp.maincommand";
    }

    @Override
    public void fire(String[] args, CommandSender cs) {
        List<ISubCommand> cmds = new ArrayList<>();
        for (ISubCommand cmd : CoreClass.getInstance().cmds) {
            if (cs.hasPermission(cmd.getPermission())) {
                cmds.add(cmd);
            }
        }
        PagedLists<ISubCommand> pl = new PagedLists<>(cmds, 8);
        int page = 1;
        if (args.length > 0) {

            if (args[0].matches("^[0-9]+$")) {
                page = Integer.parseInt(args[0]);
                if (page > pl.getTotalPages()) {
                    cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.RED + " Invalid page number.");
                    return;
                }
                cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " Help menu: Page " + page + "/" + pl.getTotalPages());
                for (ISubCommand cmd : pl.getContentsInPage(page)) {
                    cs.sendMessage(ChatColor.DARK_AQUA + cmd.getUsage() + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + cmd.getDescription());
                }
            } else {
                if (args.length > 1) {
                    if (args[1].matches("^[0-9]+$")) {
                        page = Integer.parseInt(args[1]);
                        if (page > pl.getTotalPages()) {
                            cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.RED + " Invalid page number.");
                            return;
                        }
                        cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " Help menu: Page " + page + "/" + pl.getTotalPages());
                        for (ISubCommand cmd : pl.getContentsInPage(page)) {
                            cs.sendMessage(ChatColor.DARK_AQUA + cmd.getUsage() + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + cmd.getDescription());
                        }
                    } else {
                        cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.RED + " Invalid page number.");
                    }
                } else {
                    cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " Help menu: Page " + page + "/" + pl.getTotalPages());
                    for (ISubCommand cmd : pl.getContentsInPage(page)) {
                        cs.sendMessage(ChatColor.DARK_AQUA + cmd.getUsage() + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + cmd.getDescription());
                    }
                }
            }
        } else {
            cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " Help menu: Page " + page + "/" + pl.getTotalPages());
            for (ISubCommand cmd : pl.getContentsInPage(page)) {
                 cs.sendMessage(ChatColor.DARK_AQUA + cmd.getUsage() + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + cmd.getDescription());
            }
        }

    }

    @Override
    public String getUsage() {
        return "/sp help";
    }

    @Override
    public String getDescription() {
        return "Retrieves the help menu.";
    }
}
