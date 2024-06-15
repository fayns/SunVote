package com.sunstrike.vote;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VoteCommand implements CommandExecutor {

    private final VoteManager voteManager;

    public VoteCommand(VoteManager voteManager) {
        this.voteManager = voteManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"vote_usage")));
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&', "vote_only_players")));
            return false;
        }

        String voteName = args[0];
        int vote;
        try {
            vote = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"vote_invalid_number")));
            return false;
        }

        Player player = (Player) sender;
        voteManager.castVote(voteName, player.getName(), vote);
        sender.sendMessage(voteManager.getPlugin().getMessage(ChatColor.translateAlternateColorCodes('&',"vote_casted")));
        return true;
    }
}
