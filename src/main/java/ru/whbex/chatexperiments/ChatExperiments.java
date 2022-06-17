package ru.whbex.chatexperiments;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import ru.whbex.chatexperiments.cmd.stop;
import ru.whbex.chatexperiments.cmd.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public final class ChatExperiments extends JavaPlugin implements Listener {
    private Map<CommandSender, Timer> timers = new HashMap<>();
    private static ChatExperiments instance;

    @Override
    public void onEnable() {
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        this.getCommand("testplayer").setExecutor(new test());
        this.getCommand("stopplayer").setExecutor(new stop());
        instance = this;

    }

    @Override
    public void onDisable() {
        timers.values().forEach(Timer::cancel);
    }
    public static ChatExperiments getInstance(){
        return instance;
    }
    public void setTimer(CommandSender viewer, Timer timer){
        timers.put(viewer, timer);
    }
    public Timer getTimer(CommandSender key){
        return timers.get(key);
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void on(PlayerQuitEvent event){
        if(timers.get(event.getPlayer()) == null) return;
        timers.get(event.getPlayer()).cancel();
        getLogger().info(String.format("%s has left, stopping player", event.getPlayer().getName()));
    }
}
