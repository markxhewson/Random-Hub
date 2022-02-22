package xyz.lotho.me.utils;

import net.md_5.bungee.api.config.ServerInfo;
import xyz.lotho.me.RandomHub;

import java.util.ArrayList;
import java.util.Random;

public class Network {

    RandomHub instance;

    public Network(RandomHub instance) {
        this.instance = instance;
    }

    public ServerInfo findRandomHub() {
        ArrayList<String> servers = (ArrayList<String>) this.instance.config.getConfig().getStringList("servers");
        int index = new Random().nextInt(servers.size());

        ServerInfo server = this.instance.getProxy().getServerInfo(servers.get(index));

        if (server.isRestricted()) {
            throw new Error("One or more servers in your configuration file is restricted and cannot be accessed.");
        }

        if (!this.instance.getProxy().getServersCopy().containsKey(server.getName())) {
            throw new Error("One or more servers in your configuration file does not exist in the bungeecord server list.");
        }

        return server;
    }
}
