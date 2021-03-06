package ru.whbex.chatexperiments.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.whbex.chatexperiments.ChatExperiments;
import ru.whbex.chatexperiments.tasks.ShowMatrixTask;

import java.util.Timer;

public class test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatExperiments.getInstance().setTimer(sender, new Timer());
        ShowMatrixTask task = new ShowMatrixTask(sender, ChatExperiments.getInstance().getDataFolder().getAbsolutePath());
        Bukkit.getScheduler().runTaskAsynchronously(ChatExperiments.getInstance(), () -> {
            task.parseFrames();
            sender.sendMessage("Starting player in 2500 ms");
            ChatExperiments.getInstance().getTimer(sender).schedule(task, 2500L, 100L);
        });
        // Done
        return true;
    }


}
