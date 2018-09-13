package Model;

import Resources.PrintMatrix;
import Resources.UniversalElement;

public class Jacobian {

    private Element element;
    private UniversalElement uvEl = new UniversalElement();
    private double[][] J = new double[2][2];
    private double[][] J_inv = new double[2][2];
    private double detJ;

    public Jacobian(Element element) {
        this.element = element;

        for (int i = 0; i < 4; i++) {
            /** ->Macierz J */
            /** dx/dKsi  */
            J[0][0] = ((0.25) * (UniversalElement.getEta()[i] - 1) * element.getNodes()[0].getX()) + ((0.25) * (1 - UniversalElement.getEta()[i]) * element.getNodes()[1].getX()) + ((0.25) * (1 + UniversalElement.getEta()[i]) * element.getNodes()[2].getX()) - ((0.25) * (1 + UniversalElement.getEta()[i]) * element.getNodes()[3].getX());
            /** dx/dEta  */
            J[1][0] = ((0.25) * (UniversalElement.getKsi()[i] - 1) * element.getNodes()[0].getX()) - ((0.25) * (1 + UniversalElement.getKsi()[i]) * element.getNodes()[1].getX()) + ((0.25) * (1 + UniversalElement.getKsi()[i]) * element.getNodes()[2].getX()) + ((0.25) * (1 - UniversalElement.getKsi()[i]) * element.getNodes()[3].getX());
            /** dy/dKsi */
            J[0][1] = ((0.25) * (UniversalElement.getEta()[i] - 1) * element.getNodes()[0].getY()) + ((0.25) * (1 - UniversalElement.getEta()[i]) * element.getNodes()[1].getY()) + ((0.25) * (1 + UniversalElement.getEta()[i]) * element.getNodes()[2].getY()) - ((0.25) * (1 + UniversalElement.getEta()[i]) * element.getNodes()[3].getY());
            /** dy/dEta */
            J[1][1] = ((0.25) * (UniversalElement.getKsi()[i] - 1) * element.getNodes()[0].getY()) - ((0.25) * (1 + UniversalElement.getKsi()[i]) * element.getNodes()[1].getY()) + ((0.25) * (1 + UniversalElement.getKsi()[i]) * element.getNodes()[2].getY()) + ((0.25) * (1 - UniversalElement.getKsi()[i]) * element.getNodes()[3].getY());

            /** ->Wyznacznik */
            detJ = J[0][0] * J[1][1] - J[0][1] * J[1][0];

            /** ->Macierz J odwrotna */
            J_inv[0][0] = (J[1][1]) / detJ;
            J_inv[1][0] = (-1 * J[1][0]) / detJ;
            J_inv[0][1] = (-1 * J[0][1]) / detJ;
            J_inv[1][1] = (J[0][0]) / detJ;
        }
    }

    double[][] getJ_inv() {
        return J_inv;
    }

    double getDetJ() {
        return detJ;
    }

    UniversalElement getUvEl() {
        return uvEl;
    }

    public void showJacobianInfo(int i) {
        System.out.println("\nWęzeł [" + (i + 1) + "]");
        System.out.println("X: " + element.getNodes()[i].getX());
        System.out.println("Y: " + element.getNodes()[i].getY());
        System.out.print("Macierz J : \n");
        PrintMatrix.print_Matrix(J);
        System.out.println("Det J : " + detJ);
        System.out.print("\nMacierz J odwrotna : \n");
        PrintMatrix.print_Matrix(J_inv);
    }

}
