package fr.razi;

import fr.razi.commands.commandGivePerm;
import fr.razi.commands.commandRemovePerm;

import fr.razi.config.MessageConfig;
import fr.razi.listeners.PlayerJoinListener;
import fr.razi.utils.DataManager;
import fr.razi.utils.PermissionManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SingleGivePerm extends JavaPlugin implements Listener {

    private DataManager dataManager;

    @Override
    public void onEnable() {

        PermissionManager.setInstance(this);

        dataManager = new DataManager(this);
        dataManager.setup();

        getCommand("giveperm").setExecutor(new commandGivePerm());
        getCommand("removeperm").setExecutor(new commandRemovePerm());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        saveDefaultConfig();
        reloadConfigMessage();

    }

    public void reloadConfigMessage() {
        MessageConfig.prefix = getConfig().getString("messages.prefix");
        MessageConfig.usage = getConfig().getString("messages.usage");
        MessageConfig.playerNotFound = getConfig().getString("messages.player-not-found");
        MessageConfig.PermissionGiven = getConfig().getString("messages.permission-given");
        MessageConfig.PermissionRemoved = getConfig().getString("messages.permission-removed");
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
