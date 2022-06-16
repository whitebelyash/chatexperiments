package ru.whbex.chatexperiments.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.whbex.chatexperiments.ChatExperiments;

public class stop implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ChatExperiments.timer.cancel();
        sender.sendMessage("Stopped player");
        return true;
    }
}
