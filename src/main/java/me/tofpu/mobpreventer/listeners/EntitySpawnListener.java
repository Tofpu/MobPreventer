package me.tofpu.mobpreventer.listeners;

import me.tofpu.mobpreventer.Config;
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

//        e.setCancelled(config.isReverse() != config.get().contains(type));
    
        if (!config.isReverse()) {
            for (String value : config.getBlacklist()) {
                if (value.equalsIgnoreCase(type)) {
                    if (config.isPerWorld()){
                        for(String world : config.getWorlds()){
                            if (Bukkit.getWorld(world) != null){
                                if (world.equals(e.getEntity().getWorld().getName())){
                                    e.setCancelled(true);
                                    return;
                                }
                            }
                        }
                    }
                    e.setCancelled(true);
                    return;
                }
            }
        } else {
            for (String value : config.getWhitelist()) {
                if (value.equalsIgnoreCase(type)) {
                    if (config.isPerWorld()){
                        for(String world : config.getWorlds()){
                            if (Bukkit.getWorld(world) != null){
                                if (world.equals(e.getEntity().getWorld().getName())){
                                    return;
                                }
                            }
                        }
                    }
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
