package me.tofpu.mobpreventer.listeners;

import me.tofpu.mobpreventer.MobPreventer;
import me.tofpu.mobpreventer.config.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {
    private final MobPreventer mobPreventer;

    public CreatureSpawnListener(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onCreatureSpawnEvent(CreatureSpawnEvent e) {
        final Config config = mobPreventer.getStaticConfig();
        final String type = e.getEntityType().toString().replace("_", "").toLowerCase();

        if (!config.isSpawnersEnabled()){
            if (e.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER){
                return;
            }
        }
        if (config.isPerWorld()){
            if (!config.getWorlds().contains(e.getEntity().getWorld().getName())){
                return;
            }
        }

        if (config.isReverse()){
            if (config.getWhitelist().contains(type)){
                return;
            }
        } else {
            if (!config.getBlacklist().contains(type)){
                return;
            }
        }
        e.setCancelled(true);
    }
}
