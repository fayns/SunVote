package com.sunstrike.vote;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SunVote extends JavaPlugin {

    private VoteManager voteManager;

    @Override
    public void onEnable() {
        // Загрузка конфига
        saveDefaultConfig();
        voteManager = new VoteManager(this);
        getCommand("vote").setExecutor(new VoteCommand(voteManager));
        getCommand("voteSource").setExecutor(new AdminCommand(voteManager));

        getCommand("votereload").setExecutor(new CommandExecutor() {
            @Override
            public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
                commandSender.sendMessage(ChatColor.GREEN + "Плагин перезагружен");
                reloadConfig();
                return true;
            }
        });
    }

    @Override
    public void onDisable() {
        // Any necessary cleanup here
    }

    public VoteManager getVoteManager() {
        return voteManager;
    }

    public String getMessage(String key) {
        return getConfig().getString("messages." + key);
        
    }
}
