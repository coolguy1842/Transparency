package coolguy1842.transparency.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import coolguy1842.discordrelay.Globals;
import coolguy1842.discordrelay.Util.SendToDiscordEvent;
import coolguy1842.transparency.Transparency;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class TransparencyMessaging {
    public static void broadcastMessage(Player broadcaster, Component... components) {
        Component msg = Component.empty();
        for (Component component : components) {
            msg = msg.append(component);
        }
        
        Bukkit.broadcast(msg);

        
        if(broadcaster != null) {
            String contents = StringUtil.componentsToString(components);
            String username = PlainTextComponentSerializer.plainText().serialize(broadcaster.getPlayer().displayName());
            String avatar = "https://crafatar.com/avatars/" + broadcaster.getUniqueId();
            
            sendToDiscord(contents, username, avatar);
        }
    }

    public static void sendToDiscord(String contents, String username, String avatar) {
        Bukkit.getScheduler().runTaskAsynchronously(Globals.plugin, () -> Bukkit.getPluginManager().callEvent(new SendToDiscordEvent(username, avatar, contents, Transparency.config.getString("webhookurl"))));
    }

    public static void sendToDiscord(String username, String avatar, Component... contents) {
        Bukkit.getScheduler().runTaskAsynchronously(Globals.plugin, () -> Bukkit.getPluginManager().callEvent(new SendToDiscordEvent(username, avatar, StringUtil.componentsToString(contents), Transparency.config.getString("webhookurl"))));
    }
}
