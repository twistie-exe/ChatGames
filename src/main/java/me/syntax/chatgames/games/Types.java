package me.syntax.chatgames.games;

public enum Types {

    TYPING("games.typing.rewards"),
    UNSCRAMBLE("games.unscramble.rewards"),
    FILL_IN("games.fill-in.rewards"),
    TRIVIA("games.trivia.rewards"),
    UNREVERSE("games.unreverse.rewards");

    private final String rewardPath;

    public String getRewardPath() {
        return rewardPath;
    }

    Types(String rewardPath) {
        this.rewardPath = rewardPath;
    }

}
