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
                System.out.println("введенный символ не является буквой");
                continue;
            }

            if (!isRussianLetter(letter)){
                System.out.println("необходимо использовать кириллицу");
                continue;
            }

            System.out.println("вы ввели:" + letter);
            return letter;
        }
    }

    public String readMenuInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }

    private boolean isSingleCharacter(String letter){
        return letter.length() == 1;
    }

    private boolean isLetter(String letter){
        return Character.isLetter(letter.charAt(0));
    }

    private boolean isRussianLetter(String letter){
        return letter.matches("[А-Яа-яЁё]");
    }



}
