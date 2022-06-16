package ru.whbex.chatexperiments;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class ShowMatrixTask extends TimerTask {
    private final CommandSender viewer;
    private int counter = 0;
    private final FrameProvider frameProvider;
    private final List<String> frames = new ArrayList<>();
    public ShowMatrixTask(CommandSender viewer, String path){
        this.viewer = viewer;
        frameProvider = new FrameProvider(path);

    }
    @Override
    public void run(){
        if(frames.isEmpty()){
            viewer.sendMessage("frames array is empty. Can't start player ");
            this.cancel();
            return;
        }
        if(counter + 1 > frames.size()){
            this.cancel();
            viewer.sendMessage("Done, out of bounds");
            return;
        }
        viewer.sendMessage(counter + "\n \n" + frames.get(counter));
        counter++;

    }
    public void parseFrames(){
        viewer.sendMessage("Parsing frames");
        if(!frameProvider.hasNext()){
            viewer.sendMessage("Couldn't find any frames");
            return;
        }
        while(frameProvider.hasNext()){
            frames.add(frameProvider.getNextFrame(viewer));
        }
        viewer.sendMessage(String.format("Done, parsed %d frames", frames.size()));
    }
}
