package fr.razi.listeners;

import fr.razi.utils.PermissionManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        PermissionManager.applyAllFromConfig(event.getPlayer());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event){
        PermissionManager.clear(event.getPlayer());
    }

    @EventHandler
    public void onKick(PlayerKickEvent event){
        PermissionManager.clear(event.getPlayer());
    }

}
