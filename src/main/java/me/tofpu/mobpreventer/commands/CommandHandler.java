package me.tofpu.mobpreventer.commands;

import org.bukkit.command.CommandSender;

public class CommandHandler {
    private String name = null;
    private String permission = "";
    private String description = "";
    
    public void onCommand(CommandSender sender, String[] args){
    
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setPermission(String permission) {
        this.permission = permission;
    }
    
    public String getPermission() {
        return permission;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void register(){
        CommandManager.commands.add(this);
    }
}
