package ru.gluschenko.stc12.lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadCoordinator extends Thread {
    ArrayList<Parser> parsers = new ArrayList<>();
    public static List<String> searchedWords;

    public ThreadCoordinator(Integer parserCount){

    }

    public void getOccurencies(String[] sources, String[] words, String res) throws IOException {
        searchedWords = Arrays.asList(words);
        Parser parser = new Parser("one");
        parser.setSource(sources[0]);
        parser.run();
    }

}
