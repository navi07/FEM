package Model;

import Resources.InitialData;
import Resources.PrintMatrix;

public class VectorP {
    private double ambientTemperature;
    private double alfa;
    public double[] P = new double[4];
    private double[] sum1 = new double[4];
    private double[] sum2 = new double[4];
    private double[] sum3 = new double[4];
    private double[] sum4 = new double[4];


    VectorP(InitialData data, double[][] N1, double[][] N2, double[][] N3, double[][] N4, double[] detJ) {
        ambientTemperature = data.getAmbientTemperature();
        alfa = data.getAlfa();

        for (int i = 0; i < 4; i++) {
            sum1[i] = (N1[0][i] + N1[1][i]) * detJ[0];
            sum2[i] = (N2[0][i] + N2[1][i]) * detJ[1];
            sum3[i] = (N3[0][i] + N3[1][i]) * detJ[2];
            sum4[i] = (N4[0][i] + N4[1][i]) * detJ[3];
        }

    }

    void addToP(int BC) {
        if (BC == 1)
            for (int i = 0; i < 4; i++)
                P[i] += sum1[i];
        if (BC == 2)
            for (int i = 0; i < 4; i++)
                P[i] += sum2[i];
        if (BC == 3)
            for (int i = 0; i < 4; i++)
                P[i] += sum3[i];
        if (BC == 4)
            for (int i = 0; i < 4; i++)
                P[i] += sum4[i];

    }

    void calculateP() {
        for (int i = 0; i < 4; i++) {
            P[i] = P[i] * alfa * ambientTemperature;
        }
    }

    void showVectorP() {
        PrintMatrix.print_Vector(P);
    }
}
