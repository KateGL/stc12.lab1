package ru.gluschenko.stc12.lab1;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

enum SourceType {UNKNOWN, FILE, URL}

public class Parser implements Runnable {
    private static int BUFFER_SIZE = 1024;
    private String source;
    private SourceType sourceType = SourceType.UNKNOWN;
    private String res;
    private BufferedInputStream in;
    private String resultFile;

    public Parser(String name){
        resultFile = "result_"+name+".txt";
    }

    @Override
    public void run() {
        try {
            parseData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getSource() {
        return source;
    }


    public void setSource(String source) throws IOException {
        this.source = source;
        this.sourceType = SourceType.UNKNOWN;

        //тут определяем что это за ресурс,
        File f = new File(source);
        URL url = null;
        if(f.exists() && !f.isDirectory()) {
            // do something
            this.sourceType = SourceType.FILE;
        }
        else{
            try {
                url = new URL(source);
                this.sourceType = SourceType.URL;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        switch(this.sourceType){
            case UNKNOWN:
                System.out.println("неизвестный тип ресурса");
                return;// TODO throw exception;
            case URL:
                in = new BufferedInputStream(url.openStream());
                break;
            case FILE:
                break;
        }


    }

    void parseData() throws IOException {

        byte[] data = new byte [BUFFER_SIZE];
        int bytesRead = 0;
        String strContent;
        String[] lines;
        String last_line = "";
        boolean isFind = false;

        while((bytesRead = in.read(data)) != -1) {
            strContent = last_line + new String(data, 0, bytesRead);
            System.out.println("NEWLINE------------------------------");
            System.out.println(strContent);
            System.out.println("------------------------------");
            /**
             * прочитали буфер, пробегаем по нему и набираем строку. Если в строке есть наше слово, то
             */
            lines = strContent.split("\\.!?");
            for(String line: lines){
                System.out.println("LINE:"+line);
                isFind = findWordInLine(line);
                if(isFind){
                    //отдаем тому кто пишет в файл
                }

            }
            //если в конце буфера не стоит знак препинания И не было найдено ничего в строке,
            if((!isFind) && (data[BUFFER_SIZE-1] != '.' || data[BUFFER_SIZE-1] != '!' || data[BUFFER_SIZE-1] != '?')){
                last_line = lines[lines.length - 1];
            }
            // то значит что последняя строка не окончилась и надо ее клеить к началу следующего буфера
        }


    }

    boolean findWordInLine(String line){
        String[] words;
        //пробегаемся по строкам и ищем там наши слова
        words = line.split("\\s+");
        //System.out.println("WORDS:"+words.toString());
        //слова надо очищать от лишних символов
        for(String word: words){
            //System.out.println("WORD:"+word);
            word = word.replaceAll("[^\\W\\s]","");
            //System.out.println("WORD_NEW:"+word);
            if(word != ""){
                if(ThreadCoordinator.searchedWords.contains(word)){
                    System.out.println("Find!!!!!!!!"+line);
                    return true;
                }

            }
        }

        return false;
    }


    public String getRes() {
        return res;
    }
}
