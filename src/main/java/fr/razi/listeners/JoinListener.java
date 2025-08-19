package fr.razi.listeners;

import fr.razi.SingleGivePerm;
import fr.razi.utils.PermissionManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {

    private final SingleGivePerm instance;

    public JoinListener(SingleGivePerm instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if(PermissionManager.isAllowedGameMode(playerUUID, instance)){
            PermissionManager.setIGGameModePermission(player, instance, true);
        }
        if(PermissionManager.isAllowedSpeed(player.getUniqueId(), instance)){
            PermissionManager.setIGSpeedPermissions(player, instance, true);
        }

    }

}
