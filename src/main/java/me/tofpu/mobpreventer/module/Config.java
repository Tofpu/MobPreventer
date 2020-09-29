package me.tofpu.mobpreventer.module;

import me.tofpu.mobpreventer.MobPreventer;

import java.util.HashSet;
import java.util.Set;

public class Config {
    private final MobPreventer mobPreventer;
    private final Set<String> blacklist;
    private final Set<String> whitelist;
    private final Set<String> worlds;
    private boolean reverse;
    private boolean perWorld;
    private boolean spawners;
    
    public Config(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
        this.blacklist = new HashSet<>();
        this.blacklist.addAll(mobPreventer.getConfig().getStringList("settings.blacklist"));
        
        this.whitelist = new HashSet<>();
        this.whitelist.addAll(mobPreventer.getConfig().getStringList("settings.whitelist"));
        
        this.worlds = new HashSet<>();
        this.worlds.addAll(mobPreventer.getConfig().getStringList("settings.worlds"));
        
        this.reverse = mobPreventer.getConfig().getBoolean("settings.reverse");
        this.perWorld = mobPreventer.getConfig().getBoolean("settings.per-world");
        
        for(String keys : mobPreventer.getConfig().getConfigurationSection("settings").getKeys(false)){
            if (!keys.contains("enable-spawners")){
                mobPreventer.getConfig().set("settings.enable-spawners", false);
                mobPreventer.saveConfig();
            }
        }
        
        this.spawners = mobPreventer.getConfig().getBoolean("settings.enable-spawners");
    }
    
    public void reload(){
        mobPreventer.reloadConfig();
        this.blacklist.clear();
        this.blacklist.addAll(this.mobPreventer.getConfig().getStringList("settings.blacklist"));
        this.whitelist.clear();
        this.whitelist.addAll(this.mobPreventer.getConfig().getStringList("settings.whitelist"));
        this.worlds.clear();
        this.worlds.addAll(this.mobPreventer.getConfig().getStringList("settings.worlds"));
        this.reverse = this.mobPreventer.getConfig().getBoolean("settings.reverse");
        this.perWorld = this.mobPreventer.getConfig().getBoolean("settings.per-world");
        this.spawners = mobPreventer.getConfig().getBoolean("settings.enable-spawners");
    }
    
    public Set<String> getBlacklist() {
        return blacklist;
    }
    
    public Set<String> getWhitelist(){
        return whitelist;
    }
    
    public Set<String> getWorlds() {
        return worlds;
    }
    
    public boolean isReverse() {
        return reverse;
    }
    
    public boolean isPerWorld() {
        return perWorld;
    }
    
    public boolean isSpawnersEnabled() {
        return spawners;
    }
    
    public void setPerWorld(boolean perWorld) {
        this.mobPreventer.getConfig().set("settings.per-world", perWorld);
        this.perWorld = perWorld;
        this.mobPreventer.saveConfig();
//        this.mobPreventer.reloadConfig();
    }
    
    public void setReverse(boolean reverse) {
        this.mobPreventer.getConfig().set("settings.reverse", reverse);
        this.reverse = reverse;
        this.mobPreventer.saveConfig();
//        this.mobPreventer.reloadConfig();
    }
}
