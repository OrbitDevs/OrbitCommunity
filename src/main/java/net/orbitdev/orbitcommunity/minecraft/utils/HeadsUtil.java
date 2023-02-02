package net.orbitdev.orbitcommunity.minecraft.utils;

import org.bukkit.ChatColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HeadsUtil {

    public static List<String> getHead(String playerName) {
        try {
            List<String> list = new ArrayList<>();
            URL url = new URL("https://minotar.net/avatar/" + playerName + "/8" + ".png");
            BufferedImage image = ImageIO.read(url);
            for (int i = 0; i < image.getHeight(); i++) {
                StringBuilder chatHeadString = new StringBuilder();
                for (int j = 0; j < image.getWidth(); j++) {
                    Color color = new Color(image.getRGB(j, i));
                    ChatColor chatColor = ColorUtil.fromRGB(color.getRed(), color.getGreen(), color.getBlue());
                    chatHeadString.append(chatColor).append("⬛");
                }
                list.add(chatHeadString.toString());
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
