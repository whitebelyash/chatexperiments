package ru.whbex.chatexperiments;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.whbex.chatexperiments.cmd.stop;
import ru.whbex.chatexperiments.cmd.test;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public final class ChatExperiments extends JavaPlugin {
    public static Timer timer;
    private static ChatExperiments instance;

    @Override
    public void onEnable() {
        this.getCommand("testplayer").setExecutor(new test());
        this.getCommand("stopplayer").setExecutor(new stop());
        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static ChatExperiments getInstance(){
        return instance;
    }
}
