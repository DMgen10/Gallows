package dev.dmitry;

public class VisibilityModifier {

    private String originalWord;
    private StringBuilder maskedWord;

    public VisibilityModifier(String originalWord) {
        this.originalWord = originalWord;
        this.maskedWord = new StringBuilder("*".repeat(originalWord.length()));
    }

    public boolean updateState(String letter){
        char letterChar = letter.charAt(0);
        boolean isFound = false;

        for (int index = 0; index < originalWord.length();index++){
            if (originalWord.charAt(index) == letterChar){
                maskedWord.setCharAt(index, letterChar);
                isFound = true;
            }
        }
        return isFound;
    }

    public boolean isFullyRevealed(){
        return !maskedWord.toString().contains("*");
    }

    public StringBuilder getMaskedWord() {
        return maskedWord;
    }
}
