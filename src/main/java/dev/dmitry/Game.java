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
    private final VisibilityModifier modifier;

    private int numberOfErrors = 0;

    private void doPlay(){
        parser.loadWords();
        generator.generate();
    }

    public void play(){
        doPlay();
        modifier = new VisibilityModifier(generator.getRandomWord())
        while (numberOfErrors < 6){
            String input = inputService.readInput();
            System.out.println(modifier.updateState(input));
        }
    }
}