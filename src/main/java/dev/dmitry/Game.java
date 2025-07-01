package dev.dmitry;

import java.util.ArrayList;
import java.util.List;

public class Game {

    /*
    1. Приветствие + инструкция 1 раз (цикл do while) или пре-метод
    2. Получаем слова, генерим слово -> кладем в место для угадывания
    3. Пользователь вводит буквы, мы их проверяем на валидность
    4. Если ок то у слова снимается маска в месте, где была буква, если нет то минус был (5 попыток)
    5. Визуальное составление виселицы
     */
    private final ReplayGame replayGame = new ReplayGame();
    private final InputService inputService = new InputService();
    private final WordParser parser = new WordParser();
    private final WordGenerator generator = new WordGenerator(parser);
    private VisibilityModifier modifier;
    private String hiddenWord;
    private int numberOfErrors = 0;
    private final int maxErrors = 6;
    private final List<String> usedLetters = new ArrayList<>();
    private final List<String> wrongLetters = new ArrayList<>();


    private void doPlay(){
        parser.loadWords();
        generator.generate();
        hiddenWord = generator.getRandomWord();
        modifier = new VisibilityModifier(hiddenWord);
    }
//
//    public void play(){
//        boolean isGame = true;
//
//        game();
//        if (isEnd){
//            if (replayGame)
//        }
//        // крутится сама игра
//        // если игра проиграна или выиграна - задается вопрос о продолжении
//        // если 1 - обновляем и продолжаем если 2 - закрываем
//
//
//        do {
//            //приветствие один раз
//        } while (isGame);
//    }

    public void play() {
        System.out.println("Добро пожаловать в игру Виселица!");
        System.out.printf("У вас %s попыток.\n", maxErrors);

        boolean isGameRunning = true;

        while (isGameRunning) {
            reset();
                    // сбрасываем состояние
            game();
            // запускаем


            isGameRunning = replayGame.askReplay();
        }

        System.out.println("Выход из игры...");
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
            System.out.printf("Слово: %s\n", modifier.getMaskedWord());
            System.out.printf("Осталось попыток: %s\n",(maxErrors - numberOfErrors));
            String input = inputService.readInput();
            System.out.printf("Ошибочные буквы: %s ",String.join(", ", wrongLetters));
            if (usedLetters.contains(input)) {
                System.out.printf("Вы уже вводили букву '%s'\n", input);
                continue;
            }
            usedLetters.add(input);
            boolean isFound = modifier.updateState(input);
            if (!isFound) {
                numberOfErrors++;
                wrongLetters.add(input);
                System.out.printf("В загаданном слове буква '%s' отсутствует\n", input);
            } else {
                System.out.printf("Ты прав, буква %s есть в загаданном слове! \n", input);
            }
            System.out.println();
        }
        if (modifier.isFullyRevealed()){
            System.out.printf("Вы выиграли, загаданным словом было: %s\n", hiddenWord);
        } else {
            System.out.printf("Вы проиграли, загаданным словом было: %s\n", hiddenWord);
        }
    }
}