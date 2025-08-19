package fr.razi;

import fr.razi.commands.commandGivePerm;
import fr.razi.commands.commandRemovePerm;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SingleGivePerm extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("giveperm")).setExecutor(new commandGivePerm(this));
        Objects.requireNonNull(getCommand("removeperm")).setExecutor(new commandRemovePerm(this));

        getServer().getPluginManager().registerEvents(this, this);

    }

}
