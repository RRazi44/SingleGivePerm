package fr.razi.utils;

import fr.razi.SingleGivePerm;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class PermissionManager {

    private static final String ROOT = "Permissions";
    private static SingleGivePerm instance = null;

    private static Map<UUID, PermissionAttachment> attachmentMap = new HashMap<>();

    public static void setInstance(SingleGivePerm singleGivePerm){
        instance = singleGivePerm;
        instance.saveConfig();
    }

    public static String node(UUID uniqueId){
        return ROOT + "." + uniqueId.toString();
    }

    public static void setInGamePermission(Player targetPlayer, String permission, boolean givePerm) {
        PermissionAttachment permissionAttachment = attachmentMap.get(targetPlayer.getUniqueId());
        if(permissionAttachment == null){
            permissionAttachment = targetPlayer.addAttachment(instance);
            attachmentMap.put(targetPlayer.getUniqueId(), permissionAttachment);
        }
        permissionAttachment.setPermission(permission, givePerm);
        targetPlayer.recalculatePermissions();
    }

    public static void setConfigPermission(UUID uniqueId, String permission, boolean givePerm) {
        if(givePerm) addConfigPermission(permission, uniqueId);
        else removeConfigPermission(permission, uniqueId);
    }

    public static void addConfigPermission(String permission, UUID uniqueId){
        final FileConfiguration config = instance.getConfig();
        List<String> listPermission = config.getStringList(node(uniqueId));
        if(listPermission == null) listPermission = new ArrayList<>();
        if(contains(listPermission,permission)) return;

        listPermission.add(permission);

        config.set(node(uniqueId), listPermission);
        instance.saveConfig();
    }

    public static void removeConfigPermission(String permission, UUID uniqueId){
        final FileConfiguration config = instance.getConfig();
        List<String> listPermission = config.getStringList(node(uniqueId));
        if(listPermission == null || listPermission.isEmpty()) return;
        if(!contains(listPermission, permission)) return;

        listPermission.removeIf(perm -> perm.equalsIgnoreCase(permission));

        config.set(node(uniqueId), listPermission);
        instance.saveConfig();
    }


    public static boolean hasPermissionOnConfig(UUID uniqueId, String permission){
        return contains(instance.getConfig().getStringList(node(uniqueId)), permission);
    }

    public static void applyAllFromConfig(Player player) {
        final FileConfiguration config = instance.getConfig();
        List<String> listPermission = config.getStringList(node(player.getUniqueId()));
        PermissionAttachment permissionAttachment = attachmentMap.get(player.getUniqueId());
        if(permissionAttachment == null){
            permissionAttachment = player.addAttachment(instance);
            attachmentMap.put(player.getUniqueId(), permissionAttachment);
        }

        for(String permission : new ArrayList<>(permissionAttachment.getPermissions().keySet())){
            permissionAttachment.unsetPermission(permission);
        }

        for(String permission : listPermission){
            permissionAttachment.setPermission(permission, true);
        }

        player.recalculatePermissions();
    }

    public static void clear(Player player){
        PermissionAttachment permissionAttachment = attachmentMap.remove(player.getUniqueId());
        if (permissionAttachment == null) return;

        Set<String> listPermission = permissionAttachment.getPermissions().keySet();

        for (String permission : listPermission) {
            permissionAttachment.unsetPermission(permission);
        }

        player.removeAttachment(permissionAttachment);
        player.recalculatePermissions();
    }

    private static boolean contains(List<String> listPermission, String permission){
        if(listPermission == null) return false;
        for(String perm : listPermission) if(permission.equalsIgnoreCase(perm)) return true;
        return false;
    }

}
