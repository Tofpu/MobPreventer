package me.tofpu.mobpreventer.commands;

import me.tofpu.mobpreventer.MobPreventer;
import me.tofpu.mobpreventer.Utils;
import me.tofpu.mobpreventer.commands.module.CommandHandler;
import me.tofpu.mobpreventer.module.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Toggle extends CommandHandler {
    private final MobPreventer mobPreventer;
    
    public Toggle(MobPreventer mobPreventer) {
        this.mobPreventer = mobPreventer;
        
        super.setName("toggle");
        super.setDescription("The ability to toggle per-world and reverse!");
        super.setTabArgs(2);
    }
    
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length != 2) {
            sender.sendMessage(Utils.color("&8[&5Mob&dPreventer&8] &dYou could toggle per-worlds and reverse"));
            return;
        }
        Config config = mobPreventer.getStaticConfig();
        String target = args[1];
        switch (target) {
            case "reverse":
                config.setReverse(!config.isReverse());
                sender.sendMessage(template("reverse", config.isReverse() + ""));
                return;
            case "per-world":
                config.setPerWorld(!config.isPerWorld());
                sender.sendMessage(template("per-world", config.isReverse() + ""));
                return;
            default:
                sender.sendMessage(Utils.color("&8[&5Mob&dPreventer&8] &5Invalid option. &dYou could only toggle &5per-worlds &dor &5reverse."));
        }
    }
    
    public List<String> onTabComplete() {
        List<String> list = new ArrayList<>();
        list.add("reverse");
        list.add("per-world");
        return list;
    }
    
    public String template(String option, String result){
        return Utils.color(String.format("&8[&5Mob&dPreventer&8] &dYou have successfully toggled &5%s &dto &5%s&d!", option, result));
    }
}
