package me.tofpu.mobpreventer;

import me.tofpu.mobpreventer.commands.Toggle;
import me.tofpu.mobpreventer.commands.module.CommandManager;
import me.tofpu.mobpreventer.commands.Reload;
import me.tofpu.mobpreventer.listeners.EntitySpawnListener;
import me.tofpu.mobpreventer.listeners.SpawnerSpawnListener;
import me.tofpu.mobpreventer.module.Config;
import me.tofpu.mobpreventer.module.CustomConfig;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobPreventer extends JavaPlugin {
    private Config config;
    private CustomConfig customConfig;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        customConfig = new CustomConfig(this);
        this.config = new Config(this);
    
        int pluginId = 8986;
        new Metrics(this, pluginId);
    
        CommandManager manager = new CommandManager(this);
        PluginCommand pluginCommand = getCommand("mobpreventer");
        pluginCommand.setExecutor(manager);
        pluginCommand.setTabCompleter(manager);
        
        new Reload(this).register();
//        new Toggle(this).register();
        
        Bukkit.getPluginManager().registerEvents(new EntitySpawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SpawnerSpawnListener(this), this);
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    
    public Config getStaticConfig() {
        return config;
    }
    
    public FileConfiguration getConfig() {
        return customConfig.getCustomConfig();
    }
    
    public void reloadConfig(){
        customConfig.reload();
    }
    
    public void saveConfig(){
        customConfig.save();
    }
}
