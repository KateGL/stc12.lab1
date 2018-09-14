package ru.gluschenko.stc12.lab1;

import java.io.IOException;


public class Main {

    static Integer PARSER_COUNT = 10;


    static String[] sources = {"http://galo-go.ru/ru/%D0%B2%D0%B7%D0%B3%D0%BB%D1%8F%D0%B4-%D0%BD%D0%B0-%D0%BD%D0%B0%D1%83%D1%87%D0%BD%D1%8B%D0%B9-%D1%81%D0%B5%D1%80%D0%B2%D0%B8%D1%81-%D0%BA%D0%B0%D0%BA-%D0%BD%D0%B0-%D0%B8%D0%BD%D0%BD%D0%BE%D0%B2%D0%B0/",
    "42061149.txt"};
    static  String[] words= {"научный"};
    static String res = null;

    /**
     * нужно создать пул потоков
     * потоков надо штук 10-20
     * главный класс должен распределять задания для потоков,
     * если поток закончил работу, то он отдает ссылку на файл-результат в основной класс и тот дает ему новое задание, либо убивает его
     * основной класс - дает задания, принимает работу и передает в поток, который сливает результаты в один файл результата
     * так же отвечает за то
     *
     * void getOccurencies(String[] sources, String[] words, String res) throws …;
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        //void getOccurencies(String[] sources, String[] words, String res) throws …;
        //сначала проверяем файл
        ThreadCoordinator coordinator = new ThreadCoordinator(PARSER_COUNT);
        coordinator.getOccurencies(sources, words, res);

    }
}
