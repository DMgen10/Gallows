package dev.dmitry;

public class VisibilityModifier {



    private String maskOn(String word){
        return word.replace(".","*");
    }

    public void updateState(String word){


        // Проверки введенного символа: 1.что бы он был один 2. все символы будут притянуты к нижнему регистру 3. русский язык!ир
//        if (letter.contains(word)){
//            // буква открывается
//        } else {
//            // буква не открывается
//        }


        System.out.println(word.replaceAll(".","*"));
    }


}
