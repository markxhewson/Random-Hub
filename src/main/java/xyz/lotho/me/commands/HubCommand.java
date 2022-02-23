package xyz.lotho.me.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Command;
import xyz.lotho.me.RandomHub;
import xyz.lotho.me.utils.Chat;

public class HubCommand extends Command {

    RandomHub instance;

    public HubCommand(RandomHub instance) {
        super("hub", "", "lobby");

        this.instance = instance;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) return;

        ProxiedPlayer player = (ProxiedPlayer) sender;
        ServerInfo server = this.instance.network.findRandomHub();

        if (player.getServer().getInfo().getName().equals(server.getName())) {
            player.sendMessage(new TextComponent(Chat.colorize(this.instance.config.getConfig().getString("messages.already-connected"))));
            return;
        }

        player.connect(server, ServerConnectEvent.Reason.COMMAND);

        if (this.instance.config.getConfig().getBoolean("messages.enabled")) {
            player.sendMessage(new TextComponent(Chat.colorize(this.instance.config.getConfig().getString("messages.connected").replace("{server}", server.getName()))));
        }
    }
}
