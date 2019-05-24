package io.github.thatsmusic99.specprotect;

import fanciful.FancyMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import util.PagedLists;

import java.util.ArrayList;
import java.util.List;

public class ListCommand implements ISubCommand {
    public String getCmdName() {
        return "list";
    }

    public String getPermission() {
        return "sp.maincommand.list";
    }

    public void fire(String[] args, CommandSender cs) {
        List<Player> specs = new ArrayList<>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getGameMode().equals(GameMode.SPECTATOR)) {
                specs.add(p);
            }
        }
        PagedLists<Player> pl = new PagedLists<>(specs, 8);
        if (specs.isEmpty()) {
            cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " There are currently no players in spectate mode.");
            return;
        }

        if (args.length > 1) {
            if (args[1].matches("^[0-9]+$")) {
                int page = Integer.parseInt(args[1]);
                if (page <= pl.getTotalPages()) {
                    cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " List of spectators: Page " + page + "/" + pl.getTotalPages());
                    for (int i = 0; i < pl.getContentsInPage(page).size(); i++) {
                        if (cs instanceof Player) {
                            new FancyMessage()
                                    .command("/tp " + pl.getContentsInPage(page).get(i).getName())
                                    .text(ChatColor.DARK_AQUA + "" + (i + 1) + ". " + ChatColor.GREEN + pl.getContentsInPage(page).get(i).getName())
                                    .tooltip(ChatColor.GREEN + "Teleport to " + pl.getContentsInPage(page).get(i).getName())
                                    .send(cs);
                        } else {
                            cs.sendMessage(ChatColor.DARK_AQUA + "" + (i + 1) + ". " + ChatColor.GREEN + pl.getContentsInPage(page).get(i).getName());
                        }
                    }
                } else {
                    cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.RED + " Invalid page number.");
                }

            } else {
                cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.RED + " Invalid page number.");
            }

        } else {
            cs.sendMessage(CoreClass.getInstance().prefix + ChatColor.GREEN + " List of spectators: Page 1/" + pl.getTotalPages());
            for (int i = 0; i < pl.getContentsInPage(1).size(); i++) {
                if (cs instanceof Player) {
                    new FancyMessage()
                            .command("/tp " + pl.getContentsInPage(1).get(i).getName())
                            .text(ChatColor.DARK_AQUA + "" + (i + 1) + ". " + ChatColor.GREEN + pl.getContentsInPage(1).get(i).getName())
                            .tooltip(ChatColor.GREEN + "Teleport to " + pl.getContentsInPage(1).get(i).getName())
                            .send(cs);
                } else {
                    cs.sendMessage(ChatColor.DARK_AQUA + "" + (i + 1) + ". " + ChatColor.GREEN + pl.getContentsInPage(1).get(i).getName());
                }
            }
        }
    }

    public String getUsage() {
        return "/sp list [#]";
    }

    public String getDescription() {
        return "Displays the names of any players that are using spectate mode, and if hovered over, their location.";
    }
}
