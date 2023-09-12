package coolguy1842.transparency.Events.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.permissions.PermissionDefault;

import coolguy1842.transparency.Util.TransparencyMessaging;
import net.kyori.adventure.text.Component;

public class ServerCommand implements Listener {
    @EventHandler
    private void onServerCommand(ServerCommandEvent e) {
        if(e == null) return;
        
        String[] args = e.getCommand().split(" ");
        String commandName = args[0].replace("/", "");

        Command command = Bukkit.getCommandMap().getCommand(commandName);
        if(command == null) return;
        
        String permission = command.getPermission();

        PermissionDefault permDefault = Bukkit.getPluginManager().getPermission(permission).getDefault();
        
        switch(permDefault) {
        case FALSE: case OP:
            Bukkit.broadcast(Component.text("Server operator has used the op level command: " + commandName + "\nfull usage: " + e.getCommand()));
            TransparencyMessaging.sendToDiscord("Server operator has used the op level command: " + commandName + "\nfull usage: " + e.getCommand(), "Server", "");

            break;
        default: break;
        }
    }
}
