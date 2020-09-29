package me.tofpu.mobpreventer.module;

import me.tofpu.mobpreventer.MobPreventer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {
    private File configFile;
    private final MobPreventer mobPreventer;
    private FileConfiguration customConfig;
    
    public CustomConfig(MobPreventer mobPreventer){
        this.mobPreventer = mobPreventer;
        this.configFile = new File(mobPreventer.getDataFolder(), "config.yml");
        if (!configFile.exists()){
            configFile.getParentFile().mkdir();
            mobPreventer.saveResource("config.yml", false);
        }
        
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
    
    public FileConfiguration getCustomConfig() {
        return customConfig;
    }
    
    public void reload(){
        if (configFile == null) {
            configFile = new File(mobPreventer.getDataFolder(), "config.yml");
            mobPreventer.saveResource("config.yml", false);
        }
        customConfig = YamlConfiguration.loadConfiguration(configFile);
    }
    
    public void save(){
        try {
            customConfig.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
