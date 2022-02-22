package xyz.lotho.me;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import xyz.lotho.me.commands.HubCommand;
import xyz.lotho.me.config.Config;
import xyz.lotho.me.listeners.LoadRandomHub;
import xyz.lotho.me.utils.Network;

public final class RandomHub extends Plugin implements Listener {

    public Network network = new Network(this);
    public Config config;

    @Override
    public void onEnable() {
        super.onEnable();

        config = new Config(this, "config.yml");

        loadCommands();
        loadListeners();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void loadListeners() {
        this.getProxy().getPluginManager().registerListener(this, new LoadRandomHub(this));
    }

    public void loadCommands() {
        this.getProxy().getPluginManager().registerCommand(this, new HubCommand(this));
    }
}
