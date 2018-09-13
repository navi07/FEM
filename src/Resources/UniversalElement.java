package Resources;

import static java.lang.Math.sqrt;

public class UniversalElement {

    /**
     * Info :
     * -> F. kształtu :
     * 𝑁1=0.25(1−ksi)(1−eta)
     * 𝑁2=0.25(1+ksi)(1−eta)
     * 𝑁3=0.25(1+ksi)(1+eta)
     * 𝑁4=0.25(1−ksi)(1+eta)
     * ----------------
     * -> N/dKsi :
     * 𝑁1/𝑑Ksi=−0.25(1−eta)
     * 𝑁2/𝑑Ksi=0.25(1−eta)
     * 𝑁3/𝑑Ksi=0.25(1+eta)
     * 𝑁4/𝑑Ksi=−0.25(1+eta)
     * ----------------
     * -> N/dEta :
     * 𝑁1/𝑑Eta=−0.25(1−ksi)
     * 𝑁2/𝑑Eta=−0.25(1+ksi)
     * 𝑁3/𝑑Eta=0.25(1+ksi)
     * 𝑁4/𝑑Eta=0.25(1−ksi)
     **/

    private static double[] ksi = new double[]{((-1) / sqrt(3)), ((1) / sqrt(3)), ((1) / sqrt(3)), ((-1) / sqrt(3))};
    private static double[] eta = new double[]{((-1) / sqrt(3)), ((-1) / sqrt(3)), ((1) / sqrt(3)), ((1) / sqrt(3))};

    private double[][] N = new double[4][4]; // f. kształtu
    private double[][] dN_dEta = new double[4][4]; // N/deta - pochodna
    private double[][] dN_dKsi = new double[4][4]; // N/dksi - pochodna

    public UniversalElement() {

        for (int i = 0; i < 4; i++) {
            N[i][0] = (1.0 / 4.0) * (1 - ksi[i]) * (1 - eta[i]);
            N[i][1] = (1.0 / 4.0) * (1 + ksi[i]) * (1 - eta[i]);
            N[i][2] = (1.0 / 4.0) * (1 + ksi[i]) * (1 + eta[i]);
            N[i][3] = (1.0 / 4.0) * (1 - ksi[i]) * (1 + eta[i]);

            dN_dKsi[i][0] = -(1.0 / 4.0) * (1 - eta[i]);
            dN_dKsi[i][1] = (1.0 / 4.0) * (1 - eta[i]);
            dN_dKsi[i][2] = (1.0 / 4.0) * (1 + eta[i]);
            dN_dKsi[i][3] = -(1.0 / 4.0) * (1 + eta[i]);

            dN_dEta[i][0] = -(1.0 / 4.0) * (1 - ksi[i]);
            dN_dEta[i][1] = -(1.0 / 4.0) * (1 + ksi[i]);
            dN_dEta[i][2] = (1.0 / 4.0) * (1 + ksi[i]);
            dN_dEta[i][3] = (1.0 / 4.0) * (1 - ksi[i]);
        }
    }

    public double[][] getN() {
        return N;
    }

    public double[][] getdN_dEta() {
        return dN_dEta;
    }

    public double[][] getdN_dksi() {
        return dN_dKsi;
    }

    public static double[] getKsi() {
        return ksi;
    }

    public static double[] getEta() {
        return eta;
    }
}
