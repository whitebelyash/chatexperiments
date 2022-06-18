package ru.whbex.chatexperiments.tasks;

import org.bukkit.command.CommandSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessTask implements Runnable {
    private final CommandSender viewer;
    private Process process;
    private final String[] cmdline;
    private BufferedReader outputReader;
    public ProcessTask(String[] cmdline, CommandSender outputViewer){
        this.viewer = outputViewer;
        this.cmdline = cmdline;
    }
    @Override
    public void run() {
        try{
            ProcessBuilder procbuilder = new ProcessBuilder(cmdline).redirectErrorStream(true);
            process = procbuilder.start();
            outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while((line = outputReader.readLine()) != null){
                viewer.sendMessage(line.replaceAll("\u001B\\[[;\\d]*m", " "));
            }
        } catch (IOException e){
            viewer.sendMessage(String.format("Caught exception: %s", e.getLocalizedMessage()));
        }
    }
    public void kill(){
        if(process == null) return;
        process.destroyForcibly();
    }
}
