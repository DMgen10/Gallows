package dev.dmitry;

import java.util.Scanner;

public class ReplayGame {

    private final Scanner scanner;
    private final static String START = "1";
    private final static String QUIT = "2";

    public ReplayGame(){
        this.scanner = new Scanner(System.in);
    }

    public boolean askReplay(){

        while (true){
            System.out.println("хотите сыграть ещё раз? \n1 - да, 2 - нет");
            String choice = scanner.nextLine();
            if (choice.equals(START)){
                return true;
            } else if (choice.equals(QUIT)){
                return false;
            } else {
                System.out.println("Вы вводите что-то другое...");
            }
        }
    }
}
