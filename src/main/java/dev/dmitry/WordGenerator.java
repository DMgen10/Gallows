package dev.dmitry;

import java.util.Random;

public class WordGenerator {

    private WordParser wordParser;
    private Random random = new Random();
    private static String randomWord;
    private static int index;

    public WordGenerator(WordParser wordParser) {
        this.wordParser = wordParser;
    }

    public void generate(){
        index = random.nextInt(wordParser.getWords().size() );
        randomWord = wordParser.getWords().get(index);
    }

    public String getRandomWord(){
        return randomWord;
    }
}
