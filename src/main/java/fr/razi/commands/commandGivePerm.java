package fr.razi.commands;

import fr.razi.SingleGivePerm;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class commandGivePerm implements CommandExecutor {

    private final SingleGivePerm instance;

    public commandGivePerm(SingleGivePerm singleGivePerm) {
        instance = singleGivePerm;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

}
