package me.tofpu.mobpreventer;

import org.bukkit.ChatColor;

public class Utils {
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
