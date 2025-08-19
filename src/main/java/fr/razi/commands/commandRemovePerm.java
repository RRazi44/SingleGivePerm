package fr.razi.commands;

import fr.razi.utils.PermissionManager;
import org.bukkit.entity.Player;

public class commandRemovePerm extends AbstractPermissionCommand {

    public commandRemovePerm() {
        super(false);
    }

    @Override
    protected void applyPermission(Player targetPlayer, String permission, boolean give) {
        PermissionManager.setInGamePermission(targetPlayer, permission, isGive());
        PermissionManager.setConfigPermission(targetPlayer.getUniqueId(), permission, isGive());
    }

}
