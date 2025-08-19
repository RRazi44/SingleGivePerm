package fr.razi.commands;

import fr.razi.utils.PermissionManager;
import org.bukkit.entity.Player;

public class commandGivePerm extends AbstractPermissionCommand {

    public commandGivePerm() {
        super(true);
    }

    @Override
    protected void applyPermission(Player targetPlayer, String permission, boolean give) {
        PermissionManager.setInGamePermission(targetPlayer, permission, isGive());
        PermissionManager.setConfigPermission(targetPlayer.getUniqueId(), permission, isGive());
    }

}
