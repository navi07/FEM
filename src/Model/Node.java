package Model;

public class Node {

    private double x, y;
    private boolean boundaryCondition;
    private int ID;

    Node(double x, double y, double B, double H) {
        this.x = x;
        this.y = y;

        /** Warunek brzegowy: */
        boundaryCondition = (this.x == 0.0) || (this.x >= B) || (this.y == 0.0) || (this.y >= H);
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    int getID() {
        return ID;
    }

    void setID(int ID) {
        this.ID = ID;
    }

    boolean isBoundaryCondition() {
        return boundaryCondition;
    }

    @Override
    public String toString() {
        return "Model.Node{" +
                "x=" + x +
                ", y=" + y +
                ", boundaryCondition=" + boundaryCondition +
                '}';
    }
}

