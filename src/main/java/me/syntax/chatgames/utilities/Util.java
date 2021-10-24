package me.syntax.chatgames.utilities;

import me.syntax.chatgames.ChatGames;
import org.bukkit.ChatColor;

import java.util.*;
import java.util.regex.Pattern;

public class Util {

    private String question;
    private String answer;

    public static java.lang.String colourise(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static double millisToSecond(long end, long start) {
        return (end - start) / 1000.0;
    }

    public static String randomizeString(String input) {
        List<Character> characterList = new ArrayList<>();

        for (char c : input.toCharArray()) {
            characterList.add(c);
        }

        Collections.shuffle(characterList);
        StringBuilder builder = new StringBuilder();

        for (char c : characterList) {
            builder.append(c);
        }
        return builder.toString();
    }

    public static String randomizePhrase(String input) {
        StringBuilder builder = new StringBuilder();
        for (String str : input.split(" ")) {
            builder.append(randomizeString(str));
        }
        return builder.toString();
    }

    public static String reverseString(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void splitString(String input) {
        if(!(input.contains("|"))) {
            throw new IllegalArgumentException("String " + input + " Does not contain divider '|'");
        }
        String[] parts = input.split(Pattern.quote("|"));
        this.question = parts[0];
        this.answer = parts[1];
    }

    public static int convertToSeconds(int input) {
        return 20*input;
    }

    public static String replacePartOfWord(String string, int numberOfUnderscores) {

        Random random = ChatGames.getRandom();
        StringBuilder builder = new StringBuilder();

        int lengthWithoutSpaces = string.replace(" ", "").length();
        char[] chars = string.toCharArray();
        HashSet<Integer> underscoreSet = new HashSet<>();

        if (numberOfUnderscores > lengthWithoutSpaces) {
            numberOfUnderscores = lengthWithoutSpaces;
        }

        while (underscoreSet.size() < numberOfUnderscores) {
            int index = random.nextInt(chars.length);
            if (underscoreSet.contains(index) || chars[index] == ' ') {
                continue;
            }
            underscoreSet.add(index);
        }

        for (int i = 0; i < string.length(); i++) {
            if (underscoreSet.contains(i)) {
                builder.append("_");
            } else {
                builder.append(chars[i]);
            }
        }

        return builder.toString();
    }

    public static int nextInt(int origin, int bound) {
        return ChatGames.getRandom().nextInt(bound) + origin;
    }

    public static String roundToDecimal(double input, int decimalPlaces) {
        int rounded = (int) (input*(Math.pow(10,decimalPlaces)));
        double notRounded = rounded/Math.pow(10,decimalPlaces);
        return String.valueOf(notRounded);
    }

}
