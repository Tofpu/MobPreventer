package me.tofpu.mobpreventer.listeners;

import me.tofpu.mobpreventer.module.Config;
import me.tofpu.mobpreventer.MobPreventer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntitySpawnListener implements Listener {
    private final MobPreventer mobPreventer;
    
    public EntitySpawnListener(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
    }
    
    @EventHandler
    public void EntitySpawnEvent(EntitySpawnEvent e) {
        Config config = mobPreventer.getStaticConfig();
        String type = e.getEntityType().toString().replace("_", "");
        
        if (!config.isReverse()) {
            if (config.isPerWorld()) {
                for (String world : config.getWorlds()) {
                    if (world.equalsIgnoreCase(e.getEntity().getWorld().getName())) {
                        for (String value : config.getBlacklist()) {
                            if (value.equalsIgnoreCase(type)) {
                                e.setCancelled(true);
                                return;
                            }
                        }
                        return;
                    }
                }
    
                for (String value : config.getBlacklist()) {
                    if (value.equalsIgnoreCase(type)) {
                        e.setCancelled(true);
                        return;
                    }
                }
            } else {
                if (config.isPerWorld()) {
                    for (String world : config.getWorlds()) {
                        if (world.equalsIgnoreCase(e.getEntity().getWorld().getName())) {
                            for (String value : config.getWhitelist()) {
                                if (value.equalsIgnoreCase(type)) {
                                    return;
                                }
                            }
                            e.setCancelled(true);
                        }
                        return;
                    }
                }
    
                for (String value : config.getWhitelist()) {
                    if (value.equalsIgnoreCase(type)) {
                        return;
                    }
                }
    
                e.setCancelled(true);
            }
//        for (String value : config.get()) {
//            if (!config.isReverse()) { // BLACKLIST
//                if (value.equalsIgnoreCase(type)) {
//                    e.setCancelled(true);
//                    return;
//                }
//            } else { // WHITELIST
//                if (value.equalsIgnoreCase(type)) {
//                    return;
//                }
//            }
//        }
//        e.setCancelled(true);
        }
    }
}
