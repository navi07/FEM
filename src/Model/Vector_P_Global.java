package Model;

import Resources.CleanMatrix;
import Resources.InitialData;

public class Vector_P_Global {
    private InitialData data = new InitialData();
    public double[] P_Global = new double[data.getNh()];

    public Vector_P_Global() {
        CleanMatrix.clean_Vector(P_Global);
    }

    public void compute_P_Global(double[] PLoc, Element element) {
        for (int i = 0; i < 4; i++) {
            P_Global[element.getNodes()[i].getID()] += PLoc[i];
        }
    }

    public void compute_P_Final(double[][] C) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                P_Global[i] += C[i][j] / data.getSimulationStepTime() * data.getInitialTemperature();
            }
        }
    }

    public void compute_P_Simulation(double[][] C, double[] temp) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                P_Global[i] += C[i][j] / data.getSimulationStepTime() * temp[j];
            }
        }
    }
}



