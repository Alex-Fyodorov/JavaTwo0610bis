package lesson2;

public class HomeWorkApp {
    public static void main(String[] args) {

        int a = 4;
        int b = 4;
        int count = 0;

        //Создаём и заполняем массив.
        String[][] arr = new String[a][b];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = "" + count;
                count++;
            }
        }

        //Строчка для проверки MyArrayDataException.
        //sArr[0][0] = "ноль";

        try {
            int sum = stringToIntAndSum(arr);
            System.out.println(sum);
        } catch (MyArraySizeException se) {
            se.printStackTrace(System.out);
        } catch (MyArrayDataException de) {
            de.printStackTrace(System.out);
        }
    }

    public static int stringToIntAndSum(String[][] sArr){
        int sum = 0;

        if (sArr.length != 4){
            throw new MyArraySizeException();
        }

        for (int i = 0; i < sArr.length; i++){
            /* Поскольку на вход метода может подаваться любой массив,
            соответствие его необходимым параметрам проверяется не в одном
            месте, а полностью.
             */
            if (sArr[i].length != 4){
                throw new MyArraySizeException();
            }
            for (int j = 0; j < sArr[i].length; j++){
                try {
                    sum += Integer.parseInt(sArr[i][j]);
                } catch (NumberFormatException nfe){
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}

