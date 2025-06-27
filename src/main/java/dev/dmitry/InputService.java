package dev.dmitry;

import java.util.Scanner;

public class InputService {

    // Проверки введенного символа: 1.что бы он был один 2. все символы будут притянуты к нижнему регистру 3. русский язык!
    // 4. это должна быть строка

    static Scanner scanner = new Scanner(System.in);

    public static String input(){
        System.out.println("введите любой символ");
        String letter = scanner.nextLine();
        System.out.println("вы ввели " + letter);
        return letter;
    }

    public static boolean checkForNumberOfCharacters(String letter){
            if (letter.length() == 1){
                System.out.println("1 символ - ок пропускаем");
                return true;
            } else {
                System.out.println("нихуя не ок");
                return false;
        }
    }


}
