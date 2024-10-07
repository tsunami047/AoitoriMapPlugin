package io.aoitori043.aoitorimapplugin;

import io.aoitori043.aoitorimapplugin.business.ScriptExecutor;
import io.aoitori043.aoitorimapplugin.commands.MapBasicCommand;
import io.aoitori043.aoitorimapplugin.config.MapConfigHandler;
import io.aoitori043.aoitorimapplugin.database.MapDatabaseClient;
import io.aoitori043.aoitorimapplugin.network.NetworkImpl;
import io.aoitori043.aoitoriproject.command.BasicCommandExecute;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AoitoriMapPlugin extends JavaPlugin {

    public static AoitoriMapPlugin plugin;
    public static NetworkImpl networkImpl;
    public static ScriptExecutor scriptExecutor;

    @Override
    public void onEnable() {
        plugin = this;
        BasicCommandExecute.registerCommandExecute(new MapBasicCommand(this));
        MapConfigHandler.load();
        MapDatabaseClient.init();
        Bukkit.getMessenger().registerIncomingPluginChannel(this, NetworkImpl.CHANNEL_NAME, new NetworkImpl());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, NetworkImpl.CHANNEL_NAME);
        networkImpl = new NetworkImpl();
        Bukkit.getPluginManager().registerEvents(networkImpl,this);
        MapDatabaseClient.DatabaseListener databaseListener = new MapDatabaseClient.DatabaseListener();
        Bukkit.getPluginManager().registerEvents(databaseListener,this);
    }

    @Override
    public void onDisable() {

    }
}
