import java.util.*;

public class Graph {
    private Map<String, List<Node>> adjacencyList;

    // Constructor
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    private class Node {
        public String data;
        public int cost;

        public Node(String data, int cost){
            this.data = data;
            this.cost = cost;
        }
    }

    // Add an aiport
    public void addVertex(String source) {
        if(!adjacencyList.containsKey(source))
            adjacencyList.put(source, new LinkedList<>());
    }

    // Add a path
    public void addEdge(String source, String destination, int cost) {
        adjacencyList.get(source).add(new Node(destination, cost));
    }

}

