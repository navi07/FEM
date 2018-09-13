package Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InitialData {

    /**
     * Info :
     * initialTemperature - temperatura początkowa [°C]
     * simulationTime - czas symulacji [s]
     * simulationStepTime - krok czasowy symulacji [s]
     * ambientTemperature - temperatura otoczenia [°C]
     * alfa - współczynnik wymiany ciepła [W/(m^2)*K]
     * H - wysokość przekroju [m]
     * B - szerokość przekroju [m]
     * N_H - liczba węzłów w wysokości siatki
     * N_B - liczba węzłów w szerokości siatki
     * nh - liczba węzłów
     * ne - liczba elementów
     * dH - odległość pomiędzy węzłami po wysokości
     * dB - odległość pomiędzy węzłami po szerokości
     * specificHeat - ciepło właściwe [J/kg*°C]
     * conductivity - współczynnik przewodzenia ciepła [W/(m*°C)]
     * density - gęstość [kg/m^3]
     **/

    private int simulationTime,
            simulationStepTime,
            N_H,
            N_B,
            nh,
            ne;

    private double initialTemperature,
            ambientTemperature,
            alfa,
            H,
            B,
            dH,
            dB,
            specificHeat,
            conductivity,
            density;

    public InitialData() {
        loadData();
        nh = N_H * N_B;
        ne = (N_H - 1) * (N_B - 1);
        dH = H / (N_H - 1);
        dB = B / (N_B - 1);
    }

    private void loadData() {
        try {
            Scanner scanner = new Scanner(new File("Initial_Data.txt"));

            this.initialTemperature = scanner.nextDouble();
            scanner.nextLine();
            this.simulationTime = scanner.nextInt();
            scanner.nextLine();
            this.simulationStepTime = scanner.nextInt();
            scanner.nextLine();
            this.ambientTemperature = scanner.nextDouble();
            scanner.nextLine();
            this.alfa = scanner.nextDouble();
            scanner.nextLine();
            this.H = scanner.nextDouble();
            scanner.nextLine();
            this.B = scanner.nextDouble();
            scanner.nextLine();
            this.N_H = scanner.nextInt();
            scanner.nextLine();
            this.N_B = scanner.nextInt();
            scanner.nextLine();
            this.specificHeat = scanner.nextDouble();
            scanner.nextLine();
            this.conductivity = scanner.nextDouble();
            scanner.nextLine();
            this.density = scanner.nextDouble();

        } catch (FileNotFoundException e) {
            System.out.println("File not found !");
            e.printStackTrace();
        }
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public int getSimulationStepTime() {
        return simulationStepTime;
    }

    public int getN_H() {
        return N_H;
    }

    public int getN_B() {
        return N_B;
    }

    public int getNh() {
        return nh;
    }

    public int getNe() {
        return ne;
    }

    public double getInitialTemperature() {
        return initialTemperature;
    }

    public double getAmbientTemperature() {
        return ambientTemperature;
    }

    public double getAlfa() {
        return alfa;
    }

    public double getH() {
        return H;
    }

    public double getB() {
        return B;
    }

    public double getdH() {
        return dH;
    }

    public double getdB() {
        return dB;
    }

    public double getSpecificHeat() {
        return specificHeat;
    }

    public double getConductivity() {
        return conductivity;
    }

    public double getDensity() {
        return density;
    }

    @Override
    public String toString() {
        return "Resources.InitialData{" +
                "simulationTime=" + simulationTime +
                ", simulationStepTime=" + simulationStepTime +
                ", N_H=" + N_H +
                ", N_B=" + N_B +
                ", nh=" + nh +
                ", ne=" + ne +
                ", initialTemperature=" + initialTemperature +
                ", ambientTemperature=" + ambientTemperature +
                ", alfa=" + alfa +
                ", H=" + H +
                ", B=" + B +
                ", dH=" + dH +
                ", dB=" + dB +
                ", specificHeat=" + specificHeat +
                ", conductivity=" + conductivity +
                ", density=" + density +
                '}';
    }
}

