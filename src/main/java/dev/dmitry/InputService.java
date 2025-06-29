package dev.dmitry;
import java.util.Scanner;

public class InputService {

    // Проверки введенного символа: 1.что бы он был один 2. все символы будут притянуты к нижнему регистру 3. русский язык!
    // 4. это должна быть строка

    private final Scanner scanner;

    public InputService(){
        this.scanner = new Scanner(System.in);
    }

    public String readInput(){

        while (true){
            System.out.println("Введите одну русскую букву");
            String letter = scanner.nextLine().toLowerCase();

            if (!isSingleCharacter(letter)){
                System.out.println("Введите ровно один символ");
                continue;
            }

            if (!isLetter(letter)){
                System.out.println("Введенный символ не является буквой");
                continue;
            }

            if (!isRussianLetter(letter)){
                System.out.println("Вы ввели не Русскую букву");
                continue;
            }

            System.out.println("Вы ввели:" + letter);
            return letter;
        }
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
