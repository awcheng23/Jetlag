import java.util.*;

public class Graph {
    private Map<String, List<Node<String>>> adjacencyList;

    // Constructor
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    private class Node<String> {
        
    }

    // Add a vertex to the graph
    public void addVertex(int vertex) {
        adjacencyList.put(vertex, new LinkedList<>());
    }

    // Add an edge to the graph
    public void addEdge(int source, int destination) {
        // Add the edge from source to destination
        adjacencyList.get(source).add(destination);
        // For an undirected graph, you might want to add the reverse edge as well
        // adjacencyList.get(destination).add(source);
    }

}

