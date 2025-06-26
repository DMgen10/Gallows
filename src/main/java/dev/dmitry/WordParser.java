package dev.dmitry;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WordParser {

    private List<String> words = new ArrayList<>();

    public void loadWords(){

        try
            (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("words");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
                words = reader.lines()
                        .map(String::trim)
                        .filter(line -> !line.isEmpty())
                        .collect(Collectors.toList());

        } catch (Exception e){
            System.err.println("Error loading words" + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<String> getWords() {
        return words;
    }
}
