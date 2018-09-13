package Model;

import Resources.InitialData;
import Resources.PrintMatrix;

import static Resources.CleanMatrix.clean_Matrix;
import static java.lang.Math.sqrt;

public class Matrix_H {

    public double[][] Hsum = new double[4][4];
    public double[][] HBC = new double[4][4];
    public VectorP vectorPLocal;

    public Matrix_H(Jacobian jacobian, Element element, InitialData data) {

        double conductivity = data.getConductivity();
        double alfa = data.getAlfa();

        for (int i = 0; i < 4; i++) {

            /** ->dN/dx i dN/dy */
            double[] dNdy = new double[4];
            double[] dNdx = new double[4];
            for (int j = 0; j < 4; j++) {
                dNdx[j] = jacobian.getJ_inv()[0][0] * jacobian.getUvEl().getdN_dksi()[i][j] + jacobian.getJ_inv()[0][1] * jacobian.getUvEl().getdN_dEta()[i][j];
                dNdy[j] = jacobian.getJ_inv()[1][0] * jacobian.getUvEl().getdN_dksi()[i][j] + jacobian.getJ_inv()[1][1] * jacobian.getUvEl().getdN_dEta()[i][j];
            }

            /** ->Macierze Hx, Hy i H */
            double[][] h = new double[4][4];
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {
                    double[][] hx = new double[4][4];
                    hx[k][j] = (dNdx[j] * dNdx[k]);
                    double[][] hy = new double[4][4];
                    hy[k][j] = (dNdy[j] * dNdy[k]);
                    h[k][j] = (hx[k][j] + hy[k][j]);
                }
            }

            /** ->Macierz H = "K*({dN/dx}{dN/dx}T + {dN/dy}{dN/dy}T)*DetJ
             *    H = K(conductivity) * H[k][j] * detJ */
            double[][] HLocal = new double[4][4];
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {
                    HLocal[k][j] = (conductivity * h[k][j] * jacobian.getDetJ());
                }
            }

            /** ->Macierz HSum[k][j] += HLocal[k][j] */
            for (int k = 0; k < 4; k++) {
                for (int j = 0; j < 4; j++) {
                    Hsum[k][j] += HLocal[k][j];
                }
            }
        }

        /** ->Macierz H_BC(z warunkami brzegowymi) */

        final double[] ksi = new double[]{((-1) / sqrt(3)), ((1) / sqrt(3)), 1, 1, ((1) / sqrt(3)), ((-1) / sqrt(3)), -1, -1};
        final double[] eta = new double[]{-1, -1, ((-1) / sqrt(3)), ((1) / sqrt(3)), 1, 1, ((1) / sqrt(3)), ((-1) / sqrt(3)),};

        double[] lengthOfSide = new double[4];
        double[] detJ = new double[4];

        double[][] shapeFunctionPow1 = new double[2][4];
        double[][] shapeFunctionPow2 = new double[2][4];
        double[][] shapeFunctionPow3 = new double[2][4];
        double[][] shapeFunctionPow4 = new double[2][4];

        double[][] pc1pow1 = new double[4][4];
        double[][] pc2pow1 = new double[4][4];

        double[][] pc1pow2 = new double[4][4];
        double[][] pc2pow2 = new double[4][4];

        double[][] pc1pow3 = new double[4][4];
        double[][] pc2pow3 = new double[4][4];

        double[][] pc1pow4 = new double[4][4];
        double[][] pc2pow4 = new double[4][4];

        double[][] sum1 = new double[4][4];
        double[][] sum2 = new double[4][4];
        double[][] sum3 = new double[4][4];
        double[][] sum4 = new double[4][4];

        clean_Matrix(HBC);
        clean_Matrix(shapeFunctionPow1);
        clean_Matrix(shapeFunctionPow2);
        clean_Matrix(shapeFunctionPow3);
        clean_Matrix(shapeFunctionPow4);

        for (int i = 0; i < 4; i++) {

            if (i == 3) {
                lengthOfSide[i] = sqrt(Math.pow(element.getNodes()[3].getX() - element.getNodes()[0].getX(), 2) +
                        Math.pow(element.getNodes()[3].getY() - element.getNodes()[0].getY(), 2));
            } else {
                lengthOfSide[i] = sqrt(Math.pow(element.getNodes()[i + 1].getX() - element.getNodes()[i].getX(), 2) +
                        Math.pow(element.getNodes()[i + 1].getY() - element.getNodes()[i].getY(), 2));
            }

            detJ[i] = lengthOfSide[i] / 2;
        }

        for (int i = 0; i < 2; i++) {
            shapeFunctionPow1[i][0] = (1.0 / 4.0) * (1 - ksi[i]) * (1 - eta[i]);
            shapeFunctionPow1[i][1] = (1.0 / 4.0) * (1 + ksi[i]) * (1 - eta[i]);
            shapeFunctionPow1[i][2] = (1.0 / 4.0) * (1 + ksi[i]) * (1 + eta[i]);
            shapeFunctionPow1[i][3] = (1.0 / 4.0) * (1 - ksi[i]) * (1 + eta[i]);

            shapeFunctionPow2[i][0] = (1.0 / 4.0) * (1 - ksi[i + 2]) * (1 - eta[i + 2]);
            shapeFunctionPow2[i][1] = (1.0 / 4.0) * (1 + ksi[i + 2]) * (1 - eta[i + 2]);
            shapeFunctionPow2[i][2] = (1.0 / 4.0) * (1 + ksi[i + 2]) * (1 + eta[i + 2]);
            shapeFunctionPow2[i][3] = (1.0 / 4.0) * (1 - ksi[i + 2]) * (1 + eta[i + 2]);

            shapeFunctionPow3[i][0] = (1.0 / 4.0) * (1 - ksi[i + 4]) * (1 - eta[i + 4]);
            shapeFunctionPow3[i][1] = (1.0 / 4.0) * (1 + ksi[i + 4]) * (1 - eta[i + 4]);
            shapeFunctionPow3[i][2] = (1.0 / 4.0) * (1 + ksi[i + 4]) * (1 + eta[i + 4]);
            shapeFunctionPow3[i][3] = (1.0 / 4.0) * (1 - ksi[i + 4]) * (1 + eta[i + 4]);

            shapeFunctionPow4[i][0] = (1.0 / 4.0) * (1 - ksi[i + 6]) * (1 - eta[i + 6]);
            shapeFunctionPow4[i][1] = (1.0 / 4.0) * (1 + ksi[i + 6]) * (1 - eta[i + 6]);
            shapeFunctionPow4[i][2] = (1.0 / 4.0) * (1 + ksi[i + 6]) * (1 + eta[i + 6]);
            shapeFunctionPow4[i][3] = (1.0 / 4.0) * (1 - ksi[i + 6]) * (1 + eta[i + 6]);
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                pc1pow1[i][j] = shapeFunctionPow1[0][i] * shapeFunctionPow1[0][j] * alfa;
                pc2pow1[i][j] = shapeFunctionPow1[1][i] * shapeFunctionPow1[1][j] * alfa;

                pc1pow2[i][j] = shapeFunctionPow2[0][i] * shapeFunctionPow2[0][j] * alfa;
                pc2pow2[i][j] = shapeFunctionPow2[1][i] * shapeFunctionPow2[1][j] * alfa;

                pc1pow3[i][j] = shapeFunctionPow3[0][i] * shapeFunctionPow3[0][j] * alfa;
                pc2pow3[i][j] = shapeFunctionPow3[1][i] * shapeFunctionPow3[1][j] * alfa;

                pc1pow4[i][j] = shapeFunctionPow4[0][i] * shapeFunctionPow4[0][j] * alfa;
                pc2pow4[i][j] = shapeFunctionPow4[1][i] * shapeFunctionPow4[1][j] * alfa;

                sum1[i][j] = detJ[0] * (pc1pow1[i][j] + pc2pow1[i][j]);
                sum2[i][j] = detJ[1] * (pc1pow2[i][j] + pc2pow2[i][j]);
                sum3[i][j] = detJ[2] * (pc1pow3[i][j] + pc2pow3[i][j]);
                sum4[i][j] = detJ[3] * (pc1pow4[i][j] + pc2pow4[i][j]);
            }
        }

        /** ->Wektor P(po wyliczeniu potrzebnych danych) */

        vectorPLocal = new VectorP(data, shapeFunctionPow1, shapeFunctionPow2, shapeFunctionPow3, shapeFunctionPow4, detJ);

        if (element.getNodes()[0].isBoundaryCondition() && element.getNodes()[1].isBoundaryCondition()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    HBC[i][j] += sum1[i][j];
                }
            }
            vectorPLocal.addToP(1);
        }

        if (element.getNodes()[1].isBoundaryCondition() && element.getNodes()[2].isBoundaryCondition()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    HBC[i][j] += sum2[i][j];
                }
            }
            vectorPLocal.addToP(2);
        }

        if (element.getNodes()[2].isBoundaryCondition() && element.getNodes()[3].isBoundaryCondition()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    HBC[i][j] += sum3[i][j];
                }
            }
            vectorPLocal.addToP(3);
        }

        if (element.getNodes()[3].isBoundaryCondition() && element.getNodes()[0].isBoundaryCondition()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    HBC[i][j] += sum4[i][j];
                }
            }
            vectorPLocal.addToP(4);
        }

        vectorPLocal.calculateP();
        //showMatrix();
    }

    public void showMatrix() {
        System.out.println("\nMacierz HSum lokalna : ");
        PrintMatrix.print_Matrix(Hsum);
        System.out.println("\nMacierz H_BC(warunki brzegowe) lokalna : ");
        PrintMatrix.print_Matrix(HBC);
        vectorPLocal.showVectorP();
    }
}
