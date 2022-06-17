package ru.whbex.chatexperiments.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.whbex.chatexperiments.ChatExperiments;

import java.util.Timer;

public class stop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Timer timer = ChatExperiments.getInstance().getTimer(sender);
        if(timer == null){
            sender.sendMessage("Timer not found.");
            return true;
        }
        ChatExperiments.getInstance().getTimer(sender).cancel();
        sender.sendMessage("Stopped player");
        return true;
    }
}
