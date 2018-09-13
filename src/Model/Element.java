package Model;

import Resources.InitialData;

import java.util.Arrays;

public class Element {

    private Node[] nodes = new Node[4];

    public Element(double x, double y, InitialData data) {

        nodes[0] = new Node(x, y, data.getB(), data.getH());
        nodes[1] = new Node(x + data.getdB(), y, data.getB(), data.getH());
        nodes[2] = new Node(x + data.getdB(), y + data.getdH(), data.getB(), data.getH());
        nodes[3] = new Node(x, y + data.getdH(), data.getB(), data.getH());
    }

    Node[] getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "Model.Element{" +
                "nodes=" + (nodes == null ? null : Arrays.asList(nodes)) +
                '}';
    }
}
