package io.github.thatsmusic99.specprotect;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoreClass extends JavaPlugin {

    private static CoreClass instance;
    public String prefix = ChatColor.DARK_BLUE + "[" + ChatColor.AQUA+ "SpectateProtection" + ChatColor.DARK_BLUE + "]";
    public List<ISubCommand> cmds = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        if (getServer().getPluginManager().getPlugin("HeadsPlus") != null) {
            getServer().getPluginManager().registerEvents(new CommunicateEvent(), this);
        }
        getServer().getPluginManager().registerEvents(new SpectateEvents(), this);
        getCommand("sp").setExecutor(new MainCommand());
        cmds.add(new HelpCommand());
        cmds.add(new ListCommand());
        cmds.add(new InfoCommand());
        cmds.add(new ReloadCommand());
        setupConfig();
        getServer().getLogger().info("SpectateProtection has successfully been enabled!");
    }

    public static CoreClass getInstance() {
        return instance;
    }

    public ISubCommand getSubCommand(String name) {
        for (ISubCommand cmd : cmds) {
            if (cmd.getCmdName().equalsIgnoreCase(name)) {
                return cmd;
            }
        }
        return null;
    }

    private void setupConfig() {
        getConfig().options().header("SpectateProtection by Thatsmusic99");
        getConfig().addDefault("deny-teleport", true);
        getConfig().addDefault("notify-teleport", true);
        getConfig().addDefault("stop-movement-through-blocks", true);
        getConfig().addDefault("ignored-blocks", new ArrayList<>(Arrays.asList(Material.AIR.name(),
                Material.WATER.name(),
                Material.LAVA.name(),
                Material.YELLOW_FLOWER.name(),
                Material.LONG_GRASS.name(),
                Material.DEAD_BUSH.name(),
                Material.TORCH.name(),
                Material.BROWN_MUSHROOM.name(),
                Material.RED_MUSHROOM.name(),
                Material.RED_ROSE.name(),
                Material.CROPS.name(),
                Material.SAPLING.name(),
                Material.PORTAL.name(),
                Material.WALL_SIGN.name(),
                Material.SIGN_POST.name(),
                Material.WEB.name(),
                Material.VINE.name(),
                Material.LADDER.name())));
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

}
