package me.syntax.chatgames.listeners;

import me.syntax.chatgames.ChatGames;
import me.syntax.chatgames.games.Games;
import me.syntax.chatgames.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class OnChatListener implements Listener {

    private final String word;
    private static double endTime;
    private boolean isAnswered = false;
    public static List<String> reward;

    public OnChatListener(Plugin plugin, String word) {
        this.word = word;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        disabler();
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onASyncChatMessage(AsyncPlayerChatEvent event) {
        if (!event.getMessage().equalsIgnoreCase(word)) {
            return;
        }

        endTime = Util.millisToSecond(System.currentTimeMillis(), Games.startTime);

        final Player player = event.getPlayer();
        Bukkit.getOnlinePlayers().forEach(player1 -> {
            player1.sendMessage(Util.colourise("                        &c&lC&6&lH&e&lA&a&lT &b&lG&d&lA&c&lM&6&lE "));
            player1.sendMessage(Util.colourise("    &e" + player.getName() + " &fgave the correct answer first! "));
            player1.sendMessage(Util.colourise("        &7They answered in just &e&n" + Util.roundToDecimal(endTime, 1) + "s&7! "));
        });

        for (int i = 0; i < reward.size() ; i++) {
            reward.set(i, reward.get(i).replace("%PLAYER%", player.getName()));
        }
        reward.forEach(str -> Bukkit.getServer().getScheduler().runTask(ChatGames.getInstance(), () -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), str)));

        isAnswered = true;
        Games.isRunning = false;

        AsyncPlayerChatEvent.getHandlerList().unregister(this);

    }

    public void disabler() {
        Bukkit.getScheduler().runTaskLater(ChatGames.getInstance(), () -> {
            if (!(isAnswered)) {
                Games.isRunning = false;
                AsyncPlayerChatEvent.getHandlerList().unregister(this);
                if(ChatGames.getInstance().getConfig().getBoolean("games.settings.timeout-message-enabled")) {
                    Bukkit.getOnlinePlayers().forEach(player1 -> player1.sendMessage(Util.colourise("&7No-one has answered in time! The answer was &e&n" + word)));
                }
            }
        }, Util.convertToSeconds(ChatGames.getInstance().getConfig().getInt("games.settings.timeout")));
    }

}
