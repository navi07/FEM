package Model;

import Resources.InitialData;

import java.text.DecimalFormat;

public class Grid {

    private InitialData data;
    private Node[] nodes;
    public Element[] elements;

    public Grid() {

        data = new InitialData();

        /** Elementy : */
        elements = new Element[data.getNe()];
        int pos = 0;
        for (int i = 0; i < (data.getN_B() - 1); i++) {
            for (int j = 0; j < (data.getN_H() - 1); j++) {
                elements[pos++] = new Element(i * data.getdB(), j * data.getdH(), data);
            }
        }

        /** Węzły : */
        nodes = new Node[data.getNh()];
        pos = 0;
        for (int i = 0; i < data.getN_B(); i++) {
            for (int j = 0; j < data.getN_H(); j++) {
                nodes[pos++] = new Node(i * data.getdB(), j * data.getdH(), data.getB(), data.getH());
            }
        }

        setNodesID();
        //viewElements();
        //viewNodes();
    }

    private void setNodesID() {
        for (int i = 0; i < data.getNe(); i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < data.getNh(); k++) {
                    if (elements[i].getNodes()[j].getX() == nodes[k].getX() &&
                            elements[i].getNodes()[j].getY() == nodes[k].getY()) {
                        elements[i].getNodes()[j].setID(k);
                    }

                }
            }
        }
    }

    public void viewElements() {
        System.out.println("\nElementy :\n");
        DecimalFormat dec = new DecimalFormat("#0.0000");
        Node[] n;
        for (int i = 0; i < 1; i++) {
            n = elements[i].getNodes();
            for (int j = 0; j < 4; j++) {
                System.out.println(dec.format(n[j].getX()) + "\t" + dec.format(n[j].getY()) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void viewNodes() {
        System.out.println("\nWęzły :\n");
        DecimalFormat dec = new DecimalFormat("#0.0000");
        int k = 0;
        for (int i = data.getN_B(); i > 0; i--) {
            for (int j = data.getN_H(); j > 0; j--) {
                System.out.print(dec.format(nodes[k].getX()) + "\t" + dec.format(nodes[k].getY()) + "\t\t");
                k++;
            }
            System.out.println("\n");
        }
    }
}
