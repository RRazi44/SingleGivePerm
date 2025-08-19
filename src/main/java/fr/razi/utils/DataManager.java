package fr.razi.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

import static fr.razi.SingleGivePerm.PREFIX;

public class DataManager {

    private final JavaPlugin plugin;
    private File dataFile;
    private FileConfiguration dataConfig;

    public DataManager(JavaPlugin plugin) {
        this.plugin = plugin;
        setup();
    }

    public void setup() {

        dataFile = new File(plugin.getDataFolder(), "data.yml");

        if (!plugin.getDataFolder().exists()) {
            if(plugin.getDataFolder().mkdir()) plugin.getLogger().info("");
        }

        if (!dataFile.exists()) {
            try {
                if(dataFile.createNewFile()) plugin.getLogger().info(PREFIX + "File created.");
            } catch (IOException e) {
                plugin.getLogger().severe(PREFIX + "Impossible to create the data.yml file.");
            }
        }

        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    public FileConfiguration getConfig() {
        return dataConfig;
    }

    public void saveConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Impossible de sauvegarder le fichier data.yml !");
        }
    }

}
