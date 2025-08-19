package fr.razi.commands;

import fr.razi.config.MessageConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractPermissionCommand implements CommandExecutor {
    private final boolean give;

    protected AbstractPermissionCommand(boolean give) {
        this.give = give;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!hasPerm(sender)) return true;

        if (args.length < 2) {
            sender.sendMessage(MessageConfig.usage.replace("{label}", label));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(MessageConfig.playerNotFound);
            return true;
        }

        String permission = args[1];
        applyPermission(target, permission, give);
        sender.sendMessage(give ?
                MessageConfig.PermissionGiven.replace("{PlayerName}", target.getDisplayName())
                : MessageConfig.PermissionRemoved.replace("{PlayerName}", target.getDisplayName()));

        return true;
    }

    protected abstract void applyPermission(Player targetPlayer, String permission, boolean give);

    protected boolean hasPerm(CommandSender sender){
        return sender.isOp() || sender.hasPermission("singlegiveperm.commands.giveperm");
    }

    public boolean isGive(){return give;}

}
