package dev.dmitry;

public class VisibilityModifier {



    private String maskOn(String word){
        return word.replace(".","*");
    }

    public void updateState(String word, String letter){


        if (letter.contains(word)){
            // буква открывается
        } else {
            // буква не открывается
        }


        System.out.println(updatedWord);
    }


}
