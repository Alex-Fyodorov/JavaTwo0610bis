package lesson2;

public class MyArrayDataException extends RuntimeException{

    public MyArrayDataException(int i, int j){
        super("Неверный тип данных в ячейке [" + i + "][" + j + "]");
    }

}
