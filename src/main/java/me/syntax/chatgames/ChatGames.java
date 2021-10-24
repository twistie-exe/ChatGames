package me.syntax.chatgames;

import me.syntax.chatgames.games.Games;
import me.syntax.chatgames.games.Types;
import me.syntax.chatgames.listeners.OnChatListener;
import me.syntax.chatgames.utilities.Util;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Random;

public final class ChatGames extends JavaPlugin {

    private static final Random random = new Random();
    private static Plugin instance;
    private final ArrayList<Types> enabledGames = new ArrayList<>();

    public static Plugin getInstance() {
        return instance;
    }

    public static Random getRandom() {
        return random;
    }

    @Override
    public void onEnable() {
        instance = this;


        saveDefaultConfig();

        if(getConfig().getBoolean("games.typing.enabled")) {
            enabledGames.add(Types.TYPING);
        }
        if(getConfig().getBoolean("games.unscramble.enabled")) {
            enabledGames.add(Types.UNSCRAMBLE);
        }
        if(getConfig().getBoolean("games.fill-in.enabled")) {
            enabledGames.add(Types.FILL_IN);
        }
        if(getConfig().getBoolean("games.trivia.enabled")) {
            enabledGames.add(Types.TRIVIA);
        }
        if(getConfig().getBoolean("games.unreverse.enabled")) {
            enabledGames.add(Types.UNREVERSE);
        }

        Bukkit.getScheduler().runTaskTimerAsynchronously(this,() -> {
            if (!Games.isRunning && enabledGames.size() > 0) {
                Types gameType = enabledGames.get(random.nextInt(enabledGames.size() -1));
                new Games(gameType).start();
                OnChatListener.reward = getConfig().getStringList(gameType.getRewardPath());
                Games.isRunning = true;
                Games.startTime = System.currentTimeMillis();
            }
        }, Util.convertToSeconds(getConfig().getInt("games.settings.time")), Util.convertToSeconds(getConfig().getInt("games.settings.time")));
    }
}
