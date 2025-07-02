package dev.dmitry;
import java.util.Scanner;

public class InputService {

    private final Scanner scanner;

    public InputService(){
        this.scanner = new Scanner(System.in);
    }

    public String readInput(){

        while (true){
            String letter = scanner.nextLine().toLowerCase();

            if (!isSingleCharacter(letter)){
                System.out.println("введите ровно один символ");
                continue;
            }

            if (!isLetter(letter)){
                System.out.println("Введенный символ не является буквой");
                continue;
            }

            if (!isRussianLetter(letter)){
                System.out.println("Необходимо использовать кириллицу");
                continue;
            }

            System.out.println("Вы ввели:" + letter);
            return letter;
        }
    }

    public String readMenuInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    private boolean isSingleCharacter(String letter){        // проверка что символ ОДИН!!!!
        return letter.length() == 1;
    }

    private boolean isLetter(String letter){        // проверка на то что строка является строкой а не цифрой и т.д...
        return Character.isLetter(letter.charAt(0));
    }

    private boolean isRussianLetter(String letter){       // проверяем на рус. язык
        return letter.matches("[А-Яа-яЁё]");
    }



}
