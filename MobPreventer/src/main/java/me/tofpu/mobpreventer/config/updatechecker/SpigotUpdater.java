package me.tofpu.mobpreventer.config.updatechecker;

import me.tofpu.mobpreventer.MobPreventer;
import me.tofpu.mobpreventer.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.logging.Level;

public class SpigotUpdater implements Listener {
    private final MobPreventer mobPreventer;

    private String latestVersion;
    private boolean updateAvailable;

    private static final String RESOURCE = "84308";

    public SpigotUpdater(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
        if (!notifyUpdates()) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    HttpsURLConnection connection = (HttpsURLConnection) new URL(
                            "https://api.spigotmc.org/legacy/update.php?resource=" + RESOURCE
                    ).openConnection();
                    connection.setRequestMethod("GET");
                    latestVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                } catch (IOException e) {
                    mobPreventer.getLogger().log(Level.WARNING, e.getMessage());
                }

                updateAvailable = isUpdateAvailable();
                if (!updateAvailable) {
                    return;
                }

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        mobPreventer.getLogger().info("An update for MobPreventer (v" + latestVersion + ") is available at:");
                        mobPreventer.getLogger().info("https://www.spigotmc.org/resources/mobpreventer-1-8-8-1-16-4-prevent-mobs-from-spawning-into-your-world." + RESOURCE);
                    }
                }.runTask(mobPreventer);
            }
        }.runTaskAsynchronously(mobPreventer);
    }

    public boolean isUpdateAvailable(){
        if (latestVersion == null || latestVersion.isEmpty()) {
            return false;
        }

        String pluginVersion = toReadable(mobPreventer.getDescription().getVersion());
        String latest = toReadable(latestVersion);

        return pluginVersion.compareTo(latest) < 0;
    }

    private String toReadable(String pluginVersion) {
        if (pluginVersion.contains("-SNAPSHOT")) {
            pluginVersion = pluginVersion.split("-SNAPSHOT")[0];
        }

        return pluginVersion.replaceAll("\\.", "");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        if (notifyUpdates() && updateAvailable && e.getPlayer().hasPermission("mobpreventer.notify")) {
            String message = String.format("&dAn update for &5Mob&dPreventer &7(&5v&d%s&7) &dis available at &5https://www.spigotmc.org/resources/mobpreventer-1-8-8-1-16-4-prevent-mobs-from-spawning-into-your-world.%s", latestVersion, RESOURCE);
            e.getPlayer().sendMessage(Utils.color(message));
        }
    }

    public final boolean notifyUpdates(){
        return mobPreventer.getConfig().getBoolean("settings.notify-updates");
    }
}
