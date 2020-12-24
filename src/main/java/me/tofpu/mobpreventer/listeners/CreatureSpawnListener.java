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

        // If Entity is not of interface type Monster then do not proceed.
        // Type Monster may be in first or second-level interface.
        if (config.isMonstersOnly()) {
            final String interface0 = e.getEntity().getClass().getSuperclass().getInterfaces()[0].getSimpleName();
            final String interface1 = e.getEntity().getClass().getSuperclass().getInterfaces()[0].getInterfaces()[0].getSimpleName();
            if (!interface0.equalsIgnoreCase("Monster") && !interface1.equalsIgnoreCase("Monster")) {
                return;
            }
        }

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
