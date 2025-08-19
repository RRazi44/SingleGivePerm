package fr.razi;

import fr.razi.commands.commandGivePerm;
import fr.razi.commands.commandRemovePerm;

import fr.razi.listeners.PlayerJoinListener;
import fr.razi.utils.PermissionManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SingleGivePerm extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        PermissionManager.setInstance(this);

        Objects.requireNonNull(getCommand("giveperm")).setExecutor(new commandGivePerm());
        Objects.requireNonNull(getCommand("removeperm")).setExecutor(new commandRemovePerm());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable(){
        for(Player player : Bukkit.getOnlinePlayers()){
            PermissionManager.clear(player);
        }
    }

}
