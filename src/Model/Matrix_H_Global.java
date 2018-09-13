package Model;

import Resources.CleanMatrix;
import Resources.InitialData;

public class Matrix_H_Global {
    private InitialData data = new InitialData();
    public double[][] H_Global = new double[data.getNh()][data.getNh()];
    public double[][] H_Global_testcase = new double[data.getNh()][data.getNh()];

    public Matrix_H_Global() {
        CleanMatrix.clean_Matrix(H_Global);
    }

    public void compute_H_Global(double[][] HLoc, Element element) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                H_Global[element.getNodes()[i].getID()][element.getNodes()[j].getID()] += HLoc[i][j];
                H_Global_testcase[element.getNodes()[i].getID()][element.getNodes()[j].getID()] = H_Global[element.getNodes()[i].getID()][element.getNodes()[j].getID()];
            }
        }
    }

    public void compute_H_Global_HBC(double[][] HBC, Element element) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                H_Global[element.getNodes()[i].getID()][element.getNodes()[j].getID()] += HBC[i][j];
            }
        }
    }

    public void compute_H_Final(double[][] C_Global) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                H_Global[i][j] += C_Global[i][j] / data.getSimulationStepTime();
            }
        }
    }
}
