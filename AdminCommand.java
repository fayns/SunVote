package com.sunstrike.vote;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class AdminCommand implements CommandExecutor {

    private final VoteManager voteManager;

    public AdminCommand(VoteManager voteManager) {
        this.voteManager = voteManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2 && !label.equalsIgnoreCase("voteReload")) {
            sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"admin_usage").replace("<команда>", label)));
            return false;
        }

        String action = args[0];
        String voteName = args.length > 1 ? args[1] : "";

        switch (action.toLowerCase()) {
            case "create":
                voteManager.createVote(voteName);
                sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"vote_created")));
                break;
            case "remove":
                voteManager.removeVote(voteName);
                sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"vote_removed")));
                break;
            case "results":
                Map<String, Integer> results = voteManager.getResults(voteName);
                sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"results_header") + voteName + ":"));
                results.forEach((player, vote) -> sender.sendMessage(player + ": " + vote));
                break;
            case "broadcast":
                Map<String, Integer> broadcastResults = voteManager.getResults(voteName);
                sender.getServer().broadcastMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"results_header") + voteName + ":"));
                broadcastResults.forEach((player, vote) -> sender.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',player + ": " + vote)));
                break;
            //case "reload":
                //sender.sendMessage(ChatColor.GREEN + "Плагин перезагружен!");
                //voteManager.getPlugin().getVoteManager().reloadplugins();
                //break;
            default:
                sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"unknown_action") + action));
                return false;
        }

        return true;
    }
}
