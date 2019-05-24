package io.github.thatsmusic99.specprotect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CommunicateEvent implements Listener {

    @EventHandler
    public void onCommunicate(io.github.thatsmusic99.headsplus.api.events.CommunicateEvent e) {
        if (e.getPlugin().equalsIgnoreCase("spectateprotection")) {
            CoreClass.getInstance().getLogger().info("I've got my eye on you...");
        }
    }
}
