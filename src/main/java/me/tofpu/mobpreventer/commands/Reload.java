package me.tofpu.mobpreventer.commands;

import me.tofpu.mobpreventer.MobPreventer;
import me.tofpu.mobpreventer.utils.Utils;
import me.tofpu.mobpreventer.commands.module.CommandHandler;
import org.bukkit.command.CommandSender;

public class Reload extends CommandHandler {
    private final MobPreventer mobPreventer;
    public Reload(MobPreventer mobPreventer){
        this.mobPreventer = mobPreventer;
        
        super.setName("reload");
        super.setPermission("mobpreventer.reload");
        super.setDescription("Reloads the config");
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        long start = System.currentTimeMillis();
        mobPreventer.getStaticConfig().reload();
        long end = System.currentTimeMillis();
        int time = (int) (end - start);
        sender.sendMessage(String.format(Utils.color("&8[&5Mob&dPreventer&8] &dYou have successfully reloaded the &7config.yml &8(%dms)"), time));
    }
}
