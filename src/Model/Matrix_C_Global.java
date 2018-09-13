package Model;

import Resources.CleanMatrix;
import Resources.InitialData;

public class Matrix_C_Global {
    private InitialData data = new InitialData();
    public double[][] C_Global = new double[data.getNh()][data.getNh()];

    public Matrix_C_Global() {
        CleanMatrix.clean_Matrix(C_Global);
    }

    public void compute_C_Global(double[][] CLocal, Element element) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                C_Global[element.getNodes()[i].getID()][element.getNodes()[j].getID()] += CLocal[i][j];
            }
        }
    }
}
