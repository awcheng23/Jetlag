import java.util.*;

/**
 * A Weighted Directed Graph
 *
 * @author Alice Cheng, May Ming
 * @version 0.0.2
 */
public class Graph {
    private Map<String, List<Node>> adjacencyList;

    /**
     * Constructs an empty graph
     */
    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    private class Node {
        public String label;
        public int weight;
        public int ref = -1;

        public Node(String label, int weight){
            this.label = label;
            this.weight = weight;
        }
        // Option to add additional reference to node
        public Node(String label, int weight, int ref){
            this.label = label;
            this.weight = weight;
            this.ref = ref;
        }

        @Override
        public String toString() {
            return label+":"+weight;
        }
    }

    /**
     * Adds a vertex to the graph
     * @param source the vertex to add
     */
    public void addVertex(String source) {
        if(!adjacencyList.containsKey(source))
            adjacencyList.put(source, new LinkedList<>());
    }

    /**
     * Adds a weighted directed edge to the graph
     * @param source the vertex the directed edge originates from
     * @param destination the vertex the directed edge points to
     * @param weight the weight of the directed edge
     */
    public void addEdge(String source, String destination, int weight) {
        if(!adjacencyList.containsKey(source))
            addVertex(source); // add source vertex if it does not exist
        if(!adjacencyList.containsKey(destination))
            addVertex(destination); // add destination vertex if it does not exist
        adjacencyList.get(source).add(new Node(destination, weight));
    }

    /**
     * Adds a weighted directed edge to the graph with additional reference
     * @param source the vertex the directed edge originates from
     * @param destination the vertex the directed edge points to
     * @param weight the weight of the directed edge
     * @param ref the reference to the edge
     */
    public void addEdge(String source, String destination, int weight, int ref) {
        if(!adjacencyList.containsKey(source))
            addVertex(source); // add source vertex if it does not exist
        if(!adjacencyList.containsKey(destination))
            addVertex(destination); // add destination vertex if it does not exist
        adjacencyList.get(source).add(new Node(destination, weight, ref));
    }

    /**
     * Finds the minimum weighted path from one vertex to another in a graph using Dijkstra's algorithm
     * @param source the start vertex of the path
     * @param destination the target vertex to reach
     * @return an array of length 2 containing the weight of the shortest path and the path itself delimited by commas
     */
    public String[] findShortestPath(String source, String destination) {
        Map<String, Node> paths =  new HashMap<>();
        Set<String> visited = new HashSet<>();
        
        // Initialize path weights
        for(String key : adjacencyList.keySet())
            paths.put(key, new Node("", Integer.MAX_VALUE));
    
        paths.put(source, new Node("", 0));

        pathHelper(source, 0, visited, paths, destination);
        
        return constructPath(paths, destination);
    }

    private void pathHelper(String curr, int length, Set<String> visited, Map<String, Node> paths, String destination) {
        if(curr.equals(destination)) // Reached destination 
            return;
        
        visited.add(curr);

        // Visit all edges of curr
        for(Node n : adjacencyList.get(curr)) {
            int newLength = length + n.weight;
            if(newLength < paths.get(n.label).weight) {
                if(n.ref >= 0)
                    paths.put(n.label, new Node(curr, newLength, n.ref));
                else 
                    paths.put(n.label, new Node(curr, newLength));
            }
        }

        String next = findMinUnvisited(paths, visited);

        if(next.isEmpty()) // Minimum unvisited is infinity meaning no more edges
            return;

        pathHelper(next, paths.get(next).weight, visited, paths, destination);
    }

    private static String findMinUnvisited(Map<String, Node> map, Set<String> visited) {
        String minKey = "";
        int minValue = Integer.MAX_VALUE;

        for (Map.Entry<String, Node> entry : map.entrySet()) {
            String key = entry.getKey();
            Node n = entry.getValue();

            if (!visited.contains(key) && n.weight < minValue) {
                minValue = n.weight;
                minKey = key;
            }
        }

        return minKey;
    }

    private String[] constructPath(Map<String, Node> paths, String destination) {
        String[] result = new String[3];
        Node end = paths.get(destination);

        if (end.weight != Integer.MAX_VALUE) { // Path exists from source to destination
            result[0] = Integer.toString(end.weight); // Minimum cost
    
            StringBuilder path = new StringBuilder(destination);
            StringBuilder refs = new StringBuilder();
            while (!"".equals(end.label)) {
                path.insert(0, end.label + ",");
                refs.insert(0, end.ref + ",");
                end = paths.get(end.label);
            }
    
            result[1] = path.toString();
            result[2] = refs.toString();
            result[2] = result[2].substring(0, result[2].length() - 1);
        }

        return result; // Elements are null if path does not exist
    }
}
