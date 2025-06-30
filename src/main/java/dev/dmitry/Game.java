package dev.dmitry;

public class Game {

    /*
    1. Приветствие + инструкция 1 раз (цикл do while) или пре-метод
    2. Получаем слова, генерим слово -> кладем в место для угадывания
    3. Пользователь вводит буквы, мы их проверяем на валидность
    4. Если ок то у слова снимается маска в месте, где была буква, если нет то минус был (5 попыток)
    5. Визуальное составление виселицы
     */

    private final InputService inputService = new InputService();
    private final WordParser parser = new WordParser();
    private final WordGenerator generator = new WordGenerator(parser);
    private VisibilityModifier modifier;
    private String hiddenWord;
    private int numberOfErrors = 0;
    private final int maxErrors = 6;
    private final List<String> usedLetters = new ArrayList<>();



    private void doPlay(){
        parser.loadWords();
        generator.generate();
        hiddenWord = generator.getRandomWord();
        modifier = new VisibilityModifier(generator.getRandomWord());
    }

    public void play(){
        doPlay();
        while (numberOfErrors < maxErrors && !modifier.isFullyRevealed()){
            System.out.println("Слово " + modifier.getMaskedWord());
            System.out.println("Осталось попыток: " + (maxErrors - numberOfErrors));

            String input = inputService.readInput();

        }
    }
}