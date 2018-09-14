package ru.gluschenko.stc12.lab1;

public class FileWriter implements Runnable {
    //словарь промежуточных файлов


    @Override
    public void run() {
        //условие остановки?
    }

    public void writeToFile(String fileName, String line){
        //записывает строку в нужный файл
        //если файл есть в мапе то пишем в него
        //если файла нет, то добавляем и пишем
    }

    public void appendToFile(String tmpFileName, String resultFileName){
        //сливает данные из временного файла в результирующий файл. Если результирующего не существует, то создает его
    }
}
