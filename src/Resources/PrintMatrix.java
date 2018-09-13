package Resources;

import java.text.DecimalFormat;

public class PrintMatrix {

    static public void print_Matrix(double[][] arr) {
        DecimalFormat dec = new DecimalFormat("#0.0000");
        int columns = arr[0].length;
        StringBuilder str = new StringBuilder("|\t");

        for (double[] anArr : arr) {
            for (int j = 0; j < columns; j++) {
                str.append(dec.format(anArr[j])).append("\t");
            }

            System.out.println(str + "|");
            str = new StringBuilder("|\t");
        }
        System.out.println();
    }

    static public void print_Vector(double[] arr) {
        DecimalFormat dec = new DecimalFormat("#0.0000");
        System.out.print("[");
        for (double anArr : arr) {
            System.out.print(dec.format(anArr) + "\t");
        }
        System.out.print("]");
        System.out.println();
    }
}
