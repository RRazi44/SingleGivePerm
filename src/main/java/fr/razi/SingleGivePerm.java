package fr.razi;

import fr.razi.commands.commandGivePerm;
import fr.razi.commands.commandRemovePerm;

import fr.razi.listeners.PlayerJoinListener;
import fr.razi.utils.DataManager;
import fr.razi.utils.PermissionManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class SingleGivePerm extends JavaPlugin implements Listener {

    private DataManager dataManager;
    public static final String PREFIX = "§8[§eSingleGivePerm§8]§r ";

    @Override
    public void onEnable() {

        PermissionManager.setInstance(this);

        dataManager = new DataManager(this);
        dataManager.setup();

        Objects.requireNonNull(getCommand("giveperm")).setExecutor(new commandGivePerm());
        Objects.requireNonNull(getCommand("removeperm")).setExecutor(new commandRemovePerm());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

    }

    public DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onDisable(){
        for(Player player : Bukkit.getOnlinePlayers()){
            PermissionManager.clear(player);
        }
    }

}
