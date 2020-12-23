package me.tofpu.mobpreventer;

import me.tofpu.mobpreventer.commands.Reload;
import me.tofpu.mobpreventer.commands.module.CommandManager;
import me.tofpu.mobpreventer.config.Config;
import me.tofpu.mobpreventer.config.updatechecker.SpigotUpdater;
import me.tofpu.mobpreventer.listeners.CreatureSpawnListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobPreventer extends JavaPlugin {
    private Config config;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    
        int pluginId = 8986;
        new Metrics(this, pluginId);
        
        new Reload(this).register();

        init();
        registerCommands();
        registerListeners();
    }
    
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void init(){
        this.config = new Config(this);
    }

    public void registerCommands(){
        CommandManager manager = new CommandManager(this);
        PluginCommand pluginCommand = getCommand("mobpreventer");
        pluginCommand.setExecutor(manager);
        pluginCommand.setTabCompleter(manager);
    }

    public void registerListeners(){
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new CreatureSpawnListener(this), this);
        pluginManager.registerEvents(new SpigotUpdater(this), this);
    }
    
    public Config getStaticConfig() {
        return config;
    }
}
