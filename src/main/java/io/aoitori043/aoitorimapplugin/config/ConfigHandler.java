package io.aoitori043.ashcan.config;


import com.germ.germplugin.api.GermSrcManager;
import com.germ.germplugin.api.RootType;
import io.aoitori043.aoitoriproject.config.ConfigProperties;
import io.aoitori043.aoitoriproject.config.impl.BasicMapper;
import io.aoitori043.ashcan.Ashcan;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

@ConfigProperties
public class ConfigHandler extends BasicMapper {

    public static ConfigHandler instance;

    public static void load() {
        try {
            instance = new ConfigHandler();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JavaPlugin getPlugin() {
        return Ashcan.plugin;
    }

    public static List<String> blacklist;
    public static List<String> messageCommand;
    public static String guiTitle;


    @Override
    public void loadConfig() {
        try {
            File jexl = new File(getPlugin().getDataFolder(), "jexl");
            jexl.mkdirs();
            GermSrcManager.getGermSrcManager().registerSrcFolder(RootType.JEXL, jexl);
            File hud = new File(getPlugin().getDataFolder(),"gui");
            hud.mkdirs();
            GermSrcManager.getGermSrcManager().registerSrcFolder(RootType.GUI,hud);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
