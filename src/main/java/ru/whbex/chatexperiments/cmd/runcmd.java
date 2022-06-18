package ru.whbex.chatexperiments.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.whbex.chatexperiments.ChatExperiments;
import ru.whbex.chatexperiments.tasks.ProcessTask;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class runcmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage("Not enough args");
            return true;
        }
        Bukkit.getScheduler().runTaskAsynchronously(ChatExperiments.getInstance(), new ProcessTask(args, sender));
        return true;
    }
}
