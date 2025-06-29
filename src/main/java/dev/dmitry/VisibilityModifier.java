package dev.dmitry;

public class VisibilityModifier {

    String originalWord;
    StringBuilder maskedWord;

    public VisibilityModifier(String originalWord) {
        this.originalWord = originalWord;
        this.maskedWord = new StringBuilder("*".repeat(originalWord.length()));
    }

    public void updateState(String letter){
        char letterChar = letter.charAt(0);
        for (int index = 0; index < originalWord.length();index++){
            if (letter.charAt(index) == letterChar){
                maskedWord.setCharAt(index, letterChar);
            }
        }
    }


}
