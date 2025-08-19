package fr.razi.utils;

import fr.razi.SingleGivePerm;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.UUID;

public class PermissionManager {

    public static void setIGGameModePermission(Player targetPlayer, SingleGivePerm instance, boolean perm) {
        PermissionAttachment permissionAttachment = targetPlayer.addAttachment(instance);
        permissionAttachment.setPermission("essentials.gamemode.*", perm);
        permissionAttachment.setPermission("essentials.gamemode", perm);
    }

    public static void setIGSpeedPermissions(Player targetPlayer, SingleGivePerm instance, boolean perm) {
        PermissionAttachment permissionAttachment = targetPlayer.addAttachment(instance);
        permissionAttachment.setPermission("essentials.speed", perm);
    }

    public static void setPermGMOnConfig(UUID playerUUID, SingleGivePerm instance, boolean isAllowed){
        FileConfiguration config = instance.getConfig();
        config.set("hasPerm.GameMode." + playerUUID.toString(), isAllowed);
        instance.saveConfig();
    }

    public static void setPermSpeedOnConfig(UUID playerUUID, SingleGivePerm instance, boolean isAllowed){
        FileConfiguration config = instance.getConfig();
        config.set("hasPerm.Speed." + playerUUID.toString(), isAllowed);
        instance.saveConfig();
    }


    public static boolean isAllowedGameMode(UUID playerUUID, SingleGivePerm instance) {
        FileConfiguration config = instance.getConfig();
        if(config.get("hasPerm.GameMode." + playerUUID.toString()) == null) return false;
        return config.getBoolean("hasPerm.GameMode." + playerUUID);
    }

    public static boolean isAllowedSpeed(UUID playerUUID, SingleGivePerm instance) {
        FileConfiguration config = instance.getConfig();
        if(config.get("hasPerm.Speed." + playerUUID.toString()) == null) return false;
        return config.getBoolean("hasPerm.Speed." + playerUUID);
    }

}
