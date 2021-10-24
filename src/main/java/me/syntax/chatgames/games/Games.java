package me.syntax.chatgames.games;

import me.syntax.chatgames.ChatGames;
import me.syntax.chatgames.listeners.OnChatListener;
import me.syntax.chatgames.utilities.Util;
import org.bukkit.Bukkit;

import java.util.List;

public class Games {

    private final Types type;
    public static long startTime;
    public static boolean isRunning = false;

    public Games(Types type) {
        this.type = type;
    }

    public void start() {

        switch (type) {

            case TYPING:
                final List<String> getTypingWords = ChatGames.getInstance().getConfig().getStringList("games.typing.answers");
                final int randomTypingWord = ChatGames.getRandom().nextInt(getTypingWords.size());
                final String typingWord = getTypingWords.get(randomTypingWord);

                new OnChatListener(ChatGames.getInstance(), typingWord);
                Bukkit.getOnlinePlayers().forEach( player -> {
                    player.sendMessage(" ");
                    player.sendMessage(Util.colourise("                         &c&lC&6&lH&e&lA&a&lT &b&lG&d&lA&c&lM&6&lE"));
                    player.sendMessage(Util.colourise("           &eThe first to type '" + typingWord + "' wins!"));
                    player.sendMessage(Util.colourise("              Type your answer in chat!"));
                    player.sendMessage(" ");
                });
                break;
            case UNSCRAMBLE:
                final List<String> getUnscrambleWords = ChatGames.getInstance().getConfig().getStringList("games.unscramble.answers");
                final int randomUnscrambleWord = ChatGames.getRandom().nextInt(getUnscrambleWords.size());
                final String unscrambleWord = getUnscrambleWords.get(randomUnscrambleWord);

                new OnChatListener(ChatGames.getInstance(), unscrambleWord);
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(" ");
                    player.sendMessage(Util.colourise("                         &c&lC&6&lH&e&lA&a&lT &b&lG&d&lA&c&lM&6&lE"));
                    player.sendMessage(Util.colourise("        &eThe first to unscramble '" + Util.randomizePhrase(unscrambleWord) + "' wins!"));
                    player.sendMessage(Util.colourise("              Type your answer in chat!"));
                    player.sendMessage(" ");
                });
                break;
            case FILL_IN:
                final List<String> getFillWords = ChatGames.getInstance().getConfig().getStringList("games.fill-in.answers");
                final int randomFillWord = ChatGames.getRandom().nextInt(getFillWords.size());
                final String fillWord = getFillWords.get(randomFillWord);

                int randNum = Util.nextInt(ChatGames.getInstance().getConfig().getInt("games.fill-in.how-many-underscores.min"),ChatGames.getInstance().getConfig().getInt("games.fill-in.how-many-underscores.max"));

                new OnChatListener(ChatGames.getInstance(), fillWord);
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(" ");
                    player.sendMessage(Util.colourise("                         &c&lC&6&lH&e&lA&a&lT &b&lG&d&lA&c&lM&6&lE"));
                    player.sendMessage(Util.colourise("        &eThe first to fill-in '" + Util.replacePartOfWord(fillWord, randNum) + "' wins!"));
                    player.sendMessage(Util.colourise("              Type your answer in chat!"));
                    player.sendMessage(" ");
                });
                break;
            case TRIVIA:
                final List<String> getQuizWords = ChatGames.getInstance().getConfig().getStringList("games.trivia.qna");
                final int randomQuizWord = ChatGames.getRandom().nextInt(getQuizWords.size());
                final String quizWord = getQuizWords.get(randomQuizWord);

                Util getQNA = new Util();
                getQNA.splitString(quizWord);

                new OnChatListener(ChatGames.getInstance(), getQNA.getAnswer());
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(" ");
                    player.sendMessage(Util.colourise("                         &c&lC&6&lH&e&lA&a&lT &b&lG&d&lA&c&lM&6&lE"));
                    player.sendMessage(Util.colourise("             &e" + getQNA.getQuestion() + "?"));
                    player.sendMessage(Util.colourise("              Type your answer in chat!"));
                    player.sendMessage(" ");
                });
                break;
            case UNREVERSE:
                final List <String> getUnreverseWords = ChatGames.getInstance().getConfig().getStringList("games.unreverse.answers");
                final int randomUnreverseWord = ChatGames.getRandom().nextInt(getUnreverseWords.size());
                final String unreverseWord = getUnreverseWords.get(randomUnreverseWord);

                new OnChatListener(ChatGames.getInstance(), unreverseWord);
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.sendMessage(" ");
                    player.sendMessage(Util.colourise("                         &c&lC&6&lH&e&lA&a&lT &b&lG&d&lA&c&lM&6&lE"));
                    player.sendMessage(Util.colourise("        &eThe first to un-reverse '" + Util.reverseString(unreverseWord) + "' wins!"));
                    player.sendMessage(Util.colourise("              Type your answer in chat!"));
                    player.sendMessage(" ");
                });
                break;
        }

    }

}
