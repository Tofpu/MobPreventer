package me.tofpu.mobpreventer.utils;

import org.bukkit.ChatColor;

public class Utils {
    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String prefixColorize(String s) {
        return color("&8[&5Mob&dPreventer&8]&r " + s);
    }
}
