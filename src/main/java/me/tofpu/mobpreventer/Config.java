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
    
        blacklist = new HashSet<>();
        blacklist.addAll(mobPreventer.getConfig().getStringList("settings.blacklist"));
    
        whitelist = new HashSet<>();
        whitelist.addAll(mobPreventer.getConfig().getStringList("settings.whitelist"));
    
        worlds = new HashSet<>();
        worlds.addAll(mobPreventer.getConfig().getStringList("settings.worlds"));
        
        reverse = mobPreventer.getConfig().getBoolean("settings.reverse");
        perWorld = mobPreventer.getConfig().getBoolean("settings.per-world");
    }
    
    public void reload(){
        mobPreventer.reloadConfig();
        blacklist.addAll(mobPreventer.getConfig().getStringList("settings.blacklist"));
        whitelist.addAll(mobPreventer.getConfig().getStringList("settings.whitelist"));
        reverse = mobPreventer.getConfig().getBoolean("settings.reverse");
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
