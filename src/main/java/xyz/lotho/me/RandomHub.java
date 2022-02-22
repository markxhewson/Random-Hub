package xyz.lotho.me;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import xyz.lotho.me.config.Config;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class RandomHub extends Plugin implements Listener {

    public Config config;

    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerListener(this, this);

        config = new Config(this, "config.yml");
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @EventHandler
    public void onPostLogin(ServerConnectEvent event) {
        ArrayList<String> servers = (ArrayList<String>) config.getConfig().getStringList("servers");
        int index = new Random().nextInt(servers.size());

        ServerInfo server = this.getProxy().getServerInfo(servers.get(index));

        if (server.isRestricted()) {
            throw new Error("One or more servers in your configuration file is restricted and cannot be accessed.");
        }

        if (!this.getProxy().getServersCopy().containsKey(server.getName())) {
            throw new Error("One or more servers in your configuration file does not exist in the bungeecord server list.");
        }

        event.setTarget(server);
        event.getPlayer().sendMessage(new TextComponent("You were connected to " + server.getName()));
    }
}
