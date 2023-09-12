package coolguy1842.transparency;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import coolguy1842.transparency.Events.Command.PlayerCommand;
import coolguy1842.transparency.Events.Command.ServerCommand;

public final class Transparency extends JavaPlugin {
    public static FileConfiguration config;

    @Override
    public void onEnable() {
        config = this.getConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerCommand(), this);
        Bukkit.getPluginManager().registerEvents(new ServerCommand(), this);   
    }

    @Override
    public void onDisable() {
        config = null;
    }
}
