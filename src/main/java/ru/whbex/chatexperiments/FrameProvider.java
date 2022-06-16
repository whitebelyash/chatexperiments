package ru.whbex.chatexperiments;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FrameProvider {
    private final String path;
    private final char[][] matrix = new char[18][24];
    private int pointer = 0;
    private int fileCount = 0;
    public FrameProvider(String path){
        this.path = path;
        Path dirPath = new File(path).toPath();
        try {
            fileCount = Files.list(dirPath).toArray().length;
        } catch (IOException e){
            ChatExperiments.getInstance().getLogger().severe("Caught exception " + e.getMessage());
        }
        // fill with ■
        for(int counter1 = 0; counter1 < 18; counter1++){
            for(int counter2 = 0; counter2 < 24; counter2++){
                matrix[counter1][counter2] = '■';
            }
        }

    }

    private File getFileByPointer(){
        return new File(path, pointer + ".png");
    }
    // returns frame as string
    private String getColoredLines(BufferedImage image){
        if(image == null) return "";
        int counter1 = 0;
        String result = "";
        for(char[] chars1 : matrix){
            int counter2 = 0;
            String out = "";
            for(char c : chars1){
                Color color = new Color(image.getRGB(counter2, counter1));
                String hexcolor = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
                out = out + ChatColor.of(hexcolor) + c + " ";
                counter2++;
            }
            counter1++;
            result = result + out + "\n";
        } return result;
    }
    // returns downscaled to 24x18 bufferedimage
    private BufferedImage getImage(File file){
        BufferedImage bufImage;
        try{
            bufImage = ImageIO.read(file);
        } catch (Exception e){
            ChatExperiments.getInstance().getLogger().severe(String.format("Couldn't read %s: %s", file.getAbsolutePath(), e.getLocalizedMessage()));
            e.printStackTrace();
            return null;
        }
        if(bufImage == null){
            return null;
        }
        Image resized = bufImage.getScaledInstance(24, 18, Image.SCALE_DEFAULT);
        BufferedImage bufImageResized = new BufferedImage(24, 18, BufferedImage.TYPE_INT_RGB);
        bufImageResized.getGraphics().drawImage(resized, 0, 0, null);
        return bufImageResized;
    }
    public boolean hasNext(){
        if(fileCount == 0) return false;
        return getFileByPointer().exists();
    }
    /*public List<String> getNext(){
        pointer++;
        return resultFrame;
    }*/
    public String getNextFrame(){
        String result = getColoredLines(getImage(getFileByPointer()));
        pointer++;
        return result;
    }
    public String getNextFrame(CommandSender viewer){
        String frame = getNextFrame();
        if(frame.isEmpty()){
            viewer.sendMessage(String.format(ChatColor.RED + "Couldn't parse %d frame", pointer));
        }
        return frame;
    }

}
