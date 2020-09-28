package me.tofpu.mobpreventer;

import java.util.HashSet;
import java.util.Set;

public class Config {
    private final MobPreventer mobPreventer;
    private final Set<String> blacklist;
    private final Set<String> whitelist;
    private final Set<String> worlds;
    private boolean reverse;
    private boolean perWorld;
    
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
}
