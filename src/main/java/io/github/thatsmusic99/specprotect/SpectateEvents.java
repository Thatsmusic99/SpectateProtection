package io.github.thatsmusic99.specprotect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.logging.Logger;

public class SpectateEvents implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Logger log = CoreClass.getInstance().getLogger();
        if (e.getCause().equals(PlayerTeleportEvent.TeleportCause.SPECTATE)) {
            if (CoreClass.getInstance().getConfig().getBoolean("deny-teleport")) {
                if (e.getPlayer().hasPermission("sp.bypass.teleport")) {
                    if (!e.getPlayer().hasPermission("sp.ignore")) {
                        Player v = null;
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.getLocation().equals(e.getTo())) {
                                v = p;
                            }
                        }
                        if (CoreClass.getInstance().getConfig().getBoolean("notify-teleport")) {
                            log.info(ChatColor.stripColor(CoreClass.getInstance().prefix + " " + ChatColor.RED + e.getPlayer().getName() + " teleported to " + (v == null ? "X: " + e.getTo().getBlockX() + ", Y: " + e.getTo().getBlockY() + ", Z: " + e.getTo().getBlockZ() : v.getName()) + " with spectate mode!"));
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission("sp.admin.notify")) {
                                    p.sendMessage(ChatColor.RED + e.getPlayer().getName() + " teleported to " + (v == null ? "X: " + e.getTo().getBlockX() + ", Y: " + e.getTo().getBlockY() + ", Z: " + e.getTo().getBlockZ() : v.getName()) + " with spectate mode!");
                                }
                            }
                        }

                    }
                } else {
                    e.setCancelled(true);
                    if (!e.getPlayer().hasPermission("sp.ignore")) {
                        Player v = null;
                        e.getPlayer().sendMessage(ChatColor.RED + "You can not teleport using spectate mode.");
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.getLocation().equals(e.getTo())) {
                                v = p;
                            }
                        }
                        if (CoreClass.getInstance().getConfig().getBoolean("notify-teleport")) {
                            log.info(ChatColor.stripColor(ChatColor.RED + e.getPlayer().getName() + " attempted to teleport to " + (v == null ? "X: " + e.getTo().getBlockX() + ", Y: " + e.getTo().getBlockY() + ", Z: " + e.getTo().getBlockZ() : v.getName()) + " with spectate mode!"));
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.hasPermission("sp.admin.notify")) {
                                    p.sendMessage(CoreClass.getInstance().prefix + " " + ChatColor.RED + e.getPlayer().getName() + " attempted to teleport to " + (v == null ? "X: " + e.getTo().getBlockX() + ", Y: " + e.getTo().getBlockY() + ", Z: " + e.getTo().getBlockZ() : v.getName()) + " with spectate mode!");

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
            Block u = e.getTo().getWorld().getBlockAt(e.getTo().getBlockX(), e.getTo().getBlockY() + 1, e.getTo().getBlockZ());
            if (e.getTo().getBlock() != null || u != null) {
                if (!(CoreClass.getInstance().getConfig().getList("ignored-blocks").contains(e.getTo().getBlock().getType().name()))) {
                    if (CoreClass.getInstance().getConfig().getBoolean("stop-movement-through-blocks")) {
                        if (!e.getPlayer().hasPermission("sp.bypass.movement")) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(ChatColor.RED + "You can not go through this block in spectator mode.");
                        }
                    }
                } else if (!CoreClass.getInstance().getConfig().getList("ignored-blocks").contains(u.getType().name())) {
                    if (CoreClass.getInstance().getConfig().getBoolean("stop-movement-through-blocks")) {
                        if (!e.getPlayer().hasPermission("sp.bypass.movement")) {
                            e.setCancelled(true);
                            e.getPlayer().sendMessage(ChatColor.RED + "You can not go through this block in spectator mode.");
                        }
                    }
                }
            }
        }
    }
}
