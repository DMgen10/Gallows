package dev.dmitry;

public class WordGenerator {

    private Random random = new Random();
    private List<String> listOfWord = new ArrayList<>();    // сюда буду класть все слова

    private static String randomWord;                             // это слово буду отдавать на отгадывание

    public void generate(){
        randomWord = "";
    }
}
