package fr.razi.commands;

import fr.razi.SingleGivePerm;
import fr.razi.utils.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveGM implements CommandExecutor {

    private final SingleGivePerm instance;

    public GiveGM(SingleGivePerm instance){
        this.instance = instance;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String label, String[] args) {

        if(!sender.isOp() && !sender.hasPermission("razi.givegm.use")) return true;
        if(sender instanceof Player){
            Player player = (Player) sender;
            String usage = "§c/givegm <TargetPlayer>";

            if(args.length != 1){
                player.sendMessage(usage);
                return true;
            }

            Player targetPlayer = Bukkit.getPlayer(args[0]);
            if(targetPlayer == null){
                player.sendMessage("The player is offline or doesn't exist");
                player.sendMessage(usage);
            } else{
                PermissionManager.setIGGameModePermission(targetPlayer, instance, true);
                PermissionManager.setPermGMOnConfig(targetPlayer.getUniqueId(), instance, true);
                player.sendMessage("The player + " + targetPlayer.getName() + " has now the permissions to set his gamemode");
            }
            return true;
        }

        return false;
    }

}
