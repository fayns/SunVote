package com.sunstrike.vote;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class VoteManager {

    private final SunVote plugin;
    private final Map<String, Map<String, Integer>> votes;

    public VoteManager(SunVote plugin) {
        this.plugin = plugin;
        this.votes = new HashMap<>();
        loadVotes();
    }

    public SunVote getPlugin() {
        return plugin;
    }

    public void createVote(String voteName) {
        votes.put(voteName, new HashMap<>());
    }

    public void removeVote(String voteName) {
        votes.remove(voteName);
    }

    public void castVote(String voteName, String player, int vote) {
        votes.get(voteName).put(player, vote);
    }

    public Map<String, Integer> getResults(String voteName) {
        return votes.get(voteName);
    }

    private void loadVotes() {
        FileConfiguration config = plugin.getConfig();
        // Load votes from config if necessary
    }

    public void saveVotes() {
        FileConfiguration config = plugin.getConfig();
        // Save votes to config if necessary
        plugin.saveConfig();
    }
    //public void reloadplugins() {
        //plugin.reloadConfig();
    //}
}
