package xyz.lotho.me.listeners;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import xyz.lotho.me.RandomHub;
import xyz.lotho.me.utils.Chat;

public class LoadRandomHub implements Listener {

    RandomHub instance;

    public LoadRandomHub(RandomHub instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onConnect(ServerConnectEvent event) {
        ServerInfo server = this.instance.network.findRandomHub();

        event.setTarget(server);
        event.getPlayer().sendMessage(new TextComponent(Chat.colorize("&5&l<!> &dYou were connected to &f" + server.getName() + "&d.")));
    }
}
