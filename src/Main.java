import Model.*;
import Resources.CleanMatrix;
import Resources.Gauss;
import Resources.InitialData;
import Resources.PrintMatrix;

/**
 * Finite Element Method v1
 * Patryk Kurzeja IO
 */

public class Main {

    public static void main(String[] args) {
        System.out.println("<<Finite Element Method>>\n");

        /** DANE WEJSCIOWE : */
        InitialData initialData = new InitialData();
        System.out.println("Wczytane dane :\n" + initialData);

        /** SIATKA : */
        Grid grid = new Grid();
        Matrix_H_Global matrix_h_global = new Matrix_H_Global();
        Matrix_C_Global matrix_c_global = new Matrix_C_Global();
        Vector_P_Global vector_p_global = new Vector_P_Global();

        /** Wyświetlenie węzłów oraz elementów : */
        //grid.viewNodes();
        //grid.viewElements();

        for (int i = 0; i < grid.elements.length; i++) {
            Jacobian jacobian = new Jacobian(grid.elements[i]);
            Matrix_H matrix_h = new Matrix_H(jacobian, grid.elements[i], initialData);
            Matrix_C matrix_c = new Matrix_C(jacobian, initialData);

            matrix_c_global.compute_C_Global(matrix_c.CLocal, grid.elements[i]);
            vector_p_global.compute_P_Global(matrix_h.vectorPLocal.P, grid.elements[i]);
            matrix_h_global.compute_H_Global(matrix_h.Hsum, grid.elements[i]);
            matrix_h_global.compute_H_Global_HBC(matrix_h.HBC, grid.elements[i]);
        }

        /** Wyświetlenie macierzy H(samej) Global po agregacji : */
        System.out.println("\nMacierz H(sama) Globalna po agregacji - Iteracja 0 : ");
        PrintMatrix.print_Matrix(matrix_h_global.H_Global_testcase);

        /** Matrix [H] = [H]+[C]/dT and
         {P} = {P}+{[C]/dT}*{T0} : */
        vector_p_global.compute_P_Final(matrix_c_global.C_Global);
        matrix_h_global.compute_H_Final(matrix_c_global.C_Global);

        /** Wyświetlenie macierzy : */
        System.out.println("\nMacierz C Globalna - Iteracja 0 : ");
        PrintMatrix.print_Matrix(matrix_c_global.C_Global);
        System.out.println("\nMacierz H Globalna - Iteracja 0 : ");
        PrintMatrix.print_Matrix(matrix_h_global.H_Global);
        System.out.println("Wektor P Globalny - Iteracja 0 : ");
        PrintMatrix.print_Vector(vector_p_global.P_Global);

        /** SYMULACJA : */
        Gauss gauss = new Gauss();
        System.out.println("\nMax and min temperature in each step : ");
        System.out.println("\nTime[s] \tMinTemp[s]  \t\t\tMaxTemp[s]");

        for (int i = 0; i < initialData.getSimulationTime(); i += initialData.getSimulationStepTime()) {
            double[] temp = new double[initialData.getNh()];

            /** Wyświetlenie w pętli Macierzy H (nie zmienia się) : */
            //PrintMatrix.print_Matrix(matrix_h_global.H_Global);

            temp = gauss.solve(matrix_h_global.H_Global, vector_p_global.P_Global);
            CleanMatrix.clean_Vector(vector_p_global.P_Global);

            for (int j = 0; j < grid.elements.length; j++) {
                Jacobian jacobian = new Jacobian(grid.elements[j]);
                Matrix_H matrix_h = new Matrix_H(jacobian, grid.elements[j], initialData);
                vector_p_global.compute_P_Global(matrix_h.vectorPLocal.P, grid.elements[j]);
            }

            vector_p_global.compute_P_Simulation(matrix_c_global.C_Global, temp);

            /** Wyświetlenie w pętli zmiany wektora P : */
            //System.out.println("Wektor P Globalny - Iteracja : " + (i + 1));
            //PrintMatrix.print_Vector(vector_p_global.P_Global);

            double minTemp = temp[0];
            double maxTemp = temp[0];

            for (int k = 0; k < 16; k++) {
                if (maxTemp < temp[k])
                    maxTemp = temp[k];
                if (minTemp > temp[k])
                    minTemp = temp[k];
            }

            System.out.println((i + 50) + "  \t\t" + minTemp + " \t\t" + maxTemp);
        }
    }
}