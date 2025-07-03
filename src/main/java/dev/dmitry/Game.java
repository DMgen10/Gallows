package dev.dmitry;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final GallowsVisualizer visualizer = new GallowsVisualizer();
    private final ReplayGame replayGame = new ReplayGame();
    private final InputService inputService = new InputService();
    private final WordParser parser = new WordParser();
    private final WordGenerator generator = new WordGenerator(parser);
    private final SelectStyle selectStyle = new SelectStyle();
    private VisibilityModifier modifier;
    private String hiddenWord;
    private int numberOfErrors = 0;
    private final int maxErrors = 6;
    private boolean useSmileStyle = false;
    private final List<String> usedLetters = new ArrayList<>();
    private final List<String> wrongLetters = new ArrayList<>();

    private void doPlay(){
        parser.loadWords();
        generator.generate();
        hiddenWord = generator.getRandomWord();
        modifier = new VisibilityModifier(hiddenWord);
    }

    public void play() {
        showGameImage();
        System.out.println("ВИСЕЛИЦА\n");
        useSmileStyle = selectStyle.selectStyle();
        System.out.printf("у вас %s попыток.\n", maxErrors);
        System.out.println("вводите буквы...");

        boolean isGameRunning = true;

        while (isGameRunning) {
            reset();
            game();
            isGameRunning = replayGame.askReplay();
        }
        System.out.println("выход из игры...");
    }

    private void reset() {
        usedLetters.clear();
        wrongLetters.clear();
        numberOfErrors = 0;
        parser.loadWords();
        generator.generate();
        hiddenWord = generator.getRandomWord();
        modifier = new VisibilityModifier(hiddenWord);
    }

    public void game(){
        doPlay();

        while (numberOfErrors < maxErrors && !modifier.isFullyRevealed()){
            System.out.printf("слово: %s\n", modifier.getMaskedWord());
            System.out.printf("осталось попыток: %s\n",(maxErrors - numberOfErrors));
            String input = inputService.readInput();
            System.out.printf("ошибочные буквы: %s \n",String.join(", ", wrongLetters));
            if (usedLetters.contains(input)) {
                System.out.printf("вы уже вводили букву '%s'...\n", input);
                continue;
            }
            usedLetters.add(input);
            boolean isFound = modifier.updateState(input);
            if (!isFound) {
                numberOfErrors++;
                wrongLetters.add(input);
                System.out.printf("в загаданном слове буква '%s' отсутствует...\n", input);
            } else {
                System.out.printf("вы правы... буква %s присутствует в загаданном слове! \n", input);
            }
            System.out.println();

            if (useSmileStyle) {
                visualizer.showStatusSmile(numberOfErrors);
            } else {
                visualizer.showStatusGallows(numberOfErrors);
            }
        }
        if (modifier.isFullyRevealed()){
            System.out.printf("вы выиграли, загаданным словом было: %s\n", hiddenWord);
        } else {
            System.out.printf("вы проиграли, загаданным словом было: %s\n", hiddenWord);
        }
    }

    public void showGameImage(){
        System.out.println("\n" +
                "\n" +
                "████████████████████████████████████████████████████████████████████\n" +
                "████████████████████████████████████████████████████████████████████\n" +
                "████████████████████████████████████████████████████████████████████\n" +
                "████████████████████████████████████████████████████████████████████\n" +
                "██████████████████████████████▓█████▓▓██████████████████████████████\n" +
                "████████████████████▓▓▓▓▓▓▓▓▓▓▓▓████▓▓▓▓▓▓▓▓████▓███████████████████\n" +
                "████████████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████▓▓▓▓▓█████████▓▓████████████████\n" +
                "███████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓██▓█▓▓▓██▓████████▓▓▓▓▓▓▓███████████\n" +
                "█████████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓████▓██▓▓█████████▓▓▓▓▓▓▓▓▓▓████████\n" +
                "██████▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓█████▓█████▓██████████▓▓▓▓▓▓▓▓▓▓▓█▓████\n" +
                "███▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒███▒████████▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓███\n" +
                "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒███▓█▓▓▓▓▒▓█▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▓▓█▓█▓███████▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓██▓██▒▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒██▓▒█████████▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒░░░▓██▓█▓▒▓▓▓██▒░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░██▓▒█▓▒▓▓███░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░█▓█▓▒▓▓▓██▒░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░█████████░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░████▒████▒░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓\n" +
                "▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░▓█▓█░░░▓▒▓█▓░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▓▓▓▓\n" +
                "▓▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░█▒██░░░░░▓▓███░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▓▓▓\n" +
                "▓▓▓▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░▓█▓█░░░░░░░▒▓▓▓█░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▓▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░▒█▒██░░░░░░░░░▒▒▓██▒░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▓▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░▓███░░░░░░░░░░░░▓▓██░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▓▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░█▓██░░░░░░░░░░░░░▓▓███░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒░░░░░░░░░░░▓▓██░░░░░░░░░░░░░░░█▓██░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒░░░░░░░░░░░████░░░░░░░░░░░░░░░▓▓██░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░▒▓██░░░░░░░░░░░░░░░█▓██░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░▓███░░░░░░░░░░░░░░█▓██░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▓▓\n" +
                "▓▓▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░▓▓███░░░░░░░░░░▓█▓███░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓▓\n" +
                "▓▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░████████░████▓▓███░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓▓▓\n" +
                "▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░▓███▓▓██▓████▒░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓\n" +
                "▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░▒▓████▒░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓\n" +
                "▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓\n" +
                "▓▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓\n" +
                "\n");
    }

    private void doPlay(){
        parser.loadWords();
        generator.generate();
        hiddenWord = generator.getRandomWord();
        modifier = new VisibilityModifier(hiddenWord);
    }

    private void reset() {
        usedLetters.clear();
        wrongLetters.clear();
        numberOfErrors = 0;
        parser.loadWords();
        generator.generate();
        hiddenWord = generator.getRandomWord();
        modifier = new VisibilityModifier(hiddenWord);
    }
}