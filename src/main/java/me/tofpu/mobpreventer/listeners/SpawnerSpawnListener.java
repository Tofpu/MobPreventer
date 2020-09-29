package me.tofpu.mobpreventer.listeners;

import me.tofpu.mobpreventer.MobPreventer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

import java.util.ArrayList;
import java.util.UUID;

import static org.bukkit.event.EventPriority.HIGHEST;

public class SpawnerSpawnListener implements Listener {
    private final MobPreventer mobPreventer;
    private static ArrayList<UUID> spawners = new ArrayList<>();

    public SpawnerSpawnListener(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
    }

    @EventHandler(priority = HIGHEST)
    public void onSpawnerSpawn(SpawnerSpawnEvent e){
        if (e.isCancelled() && mobPreventer.getStaticConfig().isSpawnersEnabled()){
            e.setCancelled(false);
            spawners.add(e.getEntity().getUniqueId());
        }
    }

    public static ArrayList<UUID> getSpawners() {
        return spawners;
    }
}
