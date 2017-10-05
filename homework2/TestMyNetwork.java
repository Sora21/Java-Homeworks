/**
 * Created by pietrocenciarelli on 31/05/17.
 */
import java.util.*;

public class TestNetwork {
    public static void main(String[] args) throws NoSuchNodeException, NoSuchPathException {

        // Test per l'homework 3. Deve stampare: 0 1 2 8 9

        Integer[] Nodes = new Integer[10];
        MyNetwork<Integer> net = new MyNetwork<Integer>();

        for (int i = 0; i < 10; i++) {
            Nodes[i] = new Integer(i);
            net.addNode(Nodes[i]);
        }

        net.setSource(Nodes[0]);
        net.setTarget(Nodes[9]);

        net.addEdge(Nodes[0], Nodes[0]);
        net.addEdge(Nodes[0], Nodes[1]);
        net.addEdge(Nodes[1], Nodes[2]);
        net.addEdge(Nodes[2], Nodes[1]);
        net.addEdge(Nodes[2], Nodes[3]);
        net.addEdge(Nodes[3], Nodes[4]);
        net.addEdge(Nodes[2], Nodes[5]);
        net.addEdge(Nodes[5], Nodes[6]);
        net.addEdge(Nodes[6], Nodes[4]);
        net.addEdge(Nodes[2], Nodes[7]);
        net.addEdge(Nodes[2], Nodes[8]);
        net.addEdge(Nodes[8], Nodes[9]);

        List<Integer> result = net.shortestPath();

        for (int i = 0; i < result.size(); i++)
            System.out.print(result.get(i) + " ");
        System.out.println();
    }
}
