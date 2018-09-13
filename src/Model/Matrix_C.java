package Model;

import Resources.InitialData;
import Resources.PrintMatrix;

import static Resources.CleanMatrix.clean_Matrix;

public class Matrix_C {

    public double[][] CLocal = new double[4][4];

    public Matrix_C(Jacobian jacobian, InitialData initialData) {

        /** ->Macierz C */

        double specificHeat = initialData.getSpecificHeat(); // c
        double density = initialData.getDensity(); // ro
        double[][] PC = new double[4][4]; // punkty całkowania

        for (int i = 0; i < 4; i++) {
            /**
             F. kształtu -> uvEl.getN()[k][i]

             double[] ksi = {-(1/sqrt(3)), (1/sqrt(3)), (1/sqrt(3)), -(1/sqrt(3))};
             double[] eta = {-(1/sqrt(3)), -(1/sqrt(3)), (1/sqrt(3)), (1/sqrt(3))};

             shapeF[0] = 0.25 * (1 - ksi[i]) * (1 - eta[i]);
             shapeF[1] = 0.25 * (1 + ksi[i]) * (1 - eta[i]);
             shapeF[2] = 0.25 * (1 + ksi[i]) * (1 + eta[i]);
             shapeF[3] = 0.25 * (1 - ksi[i]) * (1 + eta[i]);
             */

            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {
                    PC[k][j] += jacobian.getUvEl().getN()[k][i] * jacobian.getUvEl().getN()[j][i] * jacobian.getDetJ() * specificHeat * density;
                }
            }

            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {
                    CLocal[k][j] += PC[k][j];
                }
            }
            clean_Matrix(PC);
        }

        //showMatrixC();
    }

    public void showMatrixC() {
        System.out.print("Macierz C lokalna : \n");
        PrintMatrix.print_Matrix(CLocal);
    }
}
