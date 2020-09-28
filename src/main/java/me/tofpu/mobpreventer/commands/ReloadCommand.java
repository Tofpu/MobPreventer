package me.tofpu.mobpreventer.commands;

import me.tofpu.mobpreventer.MobPreventer;
import me.tofpu.mobpreventer.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReloadCommand implements CommandExecutor, TabCompleter {
    private final MobPreventer mobPreventer;
    
    public ReloadCommand(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].length() == 0){
            return true;
        }
        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("mobpreventer.reload")){
            long start = System.currentTimeMillis();
            mobPreventer.getStaticConfig().reload();
            long end = System.currentTimeMillis();
            int time = (int) (end - start);
            sender.sendMessage(String.format(Utils.color("&8[&5Mob&dPreventer&8] &dYou have successfully reloaded the &7config.yml &8(%dms)"), time));
        }
        return false;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // credits to nicuch for this, much love <3
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            if (sender.hasPermission("mobpreventer.reload")){
                commands.add("reload");
            }
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        Collections.sort(completions);
        return completions;
    }
}
