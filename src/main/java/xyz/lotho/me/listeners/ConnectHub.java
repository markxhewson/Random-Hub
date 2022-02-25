package xyz.lotho.me.listeners;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import xyz.lotho.me.RandomHub;
import xyz.lotho.me.utils.Chat;

public class ConnectHub implements Listener {

    RandomHub instance;

    public ConnectHub(RandomHub instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onConnect(ServerConnectEvent event) {
        if (event.getReason() == ServerConnectEvent.Reason.COMMAND || event.getReason() == ServerConnectEvent.Reason.PLUGIN_MESSAGE) return;

        ProxiedPlayer player = event.getPlayer();

        ServerInfo server = this.instance.network.findRandomHub();
        event.setTarget(server);

        if (this.instance.config.getConfig().getBoolean("messages.enabled")) {
            player.sendMessage(new TextComponent(Chat.colorize(this.instance.config.getConfig().getString("messages.connected").replace("{server}", server.getName()))));
        }
    }
}
