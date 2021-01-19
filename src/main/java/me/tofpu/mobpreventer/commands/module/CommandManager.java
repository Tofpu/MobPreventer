package me.tofpu.mobpreventer.commands.module;

import me.tofpu.mobpreventer.MobPreventer;
import me.tofpu.mobpreventer.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandManager implements CommandExecutor, TabCompleter {
    private final MobPreventer mobPreventer;
    public static ArrayList<CommandHandler> commands = new ArrayList<>();

    public CommandManager(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage(Utils.prefixColorize("&dYou have to provide an argument!"));
            sender.sendMessage(Utils.prefixColorize("&dType &5/mobpreventer help &dfor further help!"));
            return false;
        }

        if (args[0].equalsIgnoreCase("help")){
            String header = Utils.prefixColorize("&dMobPreventer Commands:");
            sender.sendMessage(header);
            commands.forEach(commandHandler ->
                    sender.sendMessage(Utils.prefixColorize(" &8» &5/mobpreventer " + commandHandler.getName())));
            return false;
        }

        for(CommandHandler handler : commands){
            if (handler.getName().equals(args[0])){
                if (!handler.getPermission().isEmpty()){
                    if (!sender.hasPermission(handler.getPermission())) return true;
                }
                handler.onCommand(sender, args);
                return true;
            }
        }

        sender.sendMessage(Utils.prefixColorize("&dThis command does not exist!!"));
        sender.sendMessage(Utils.prefixColorize("&dType &5/mobpreventer help &dfor further help!"));
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        // credits to nicuch for this, much love <3
        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();
        if (args.length == 1){
            commands.add("help");
            for(CommandHandler handler : CommandManager.commands){
                if (sender.hasPermission(handler.getPermission())){
                    commands.add(handler.getName());
                }
            }
            StringUtil.copyPartialMatches(args[0], commands, completions);
        } else if (args.length == 2){
            for(CommandHandler handler : CommandManager.commands){
                if (sender.hasPermission(handler.getPermission()) && args[0].equalsIgnoreCase(handler.getName())){
                    List<String> list = handler.onTabComplete();
                    if (list != null){
                        commands = list;
                    }
                }
            }
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }

        Collections.sort(completions);
        return completions;
    }
}
