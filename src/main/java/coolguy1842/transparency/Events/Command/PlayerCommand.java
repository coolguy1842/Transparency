package coolguy1842.transparency.Events.Command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.permissions.PermissionDefault;

import coolguy1842.transparency.Util.TransparencyMessaging;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class PlayerCommand implements Listener {
    @EventHandler
    private void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if(!e.getPlayer().isOp()) return;

        String[] args = e.getMessage().split(" ");
        String commandName = args[0].substring(1);

        Command command = Bukkit.getCommandMap().getCommand(commandName);
        if(command == null) return;
        
        String permission = command.getPermission();

        PermissionDefault permDefault = Bukkit.getPluginManager().getPermission(permission).getDefault();
        
        switch(permDefault) {
        case FALSE: case OP:
            String username = PlainTextComponentSerializer.plainText().serialize(e.getPlayer().displayName());
            String avatar = "https://crafatar.com/avatars/" + e.getPlayer().getUniqueId();
            
            Bukkit.broadcast(e.getPlayer().displayName().append(Component.text(" has used the op level command: " + commandName + "\nfull usage: " + e.getMessage())));
            TransparencyMessaging.sendToDiscord(username + "  has used the op level command: " + commandName + "\nfull usage: " + e.getMessage(), "Server", avatar);

            break;
        default: break;
        }
    }
}
