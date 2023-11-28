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

    public void findShortestPath(String source, String destination) {
        int i = 0;
        HashMap<String, Integer> ID = new HashMap<>();
        for(String key : adjacencyList.keySet()){
            ID.put(key, i);
            i++;
        }

        Node[] paths = new Node[ID.size()];
        Arrays.fill(paths, new Node("", Integer.MAX_VALUE));
        paths[ID.get(source)] = new Node("", 0);
        Set<String> visited = new HashSet<>();
        String curr = source;
        int length = 0;
        
    }

    private void pathHelper(String curr, int length, Set visited, Node[] paths, String destination) {
        if(curr.equals(destination))
            return;
        
        visited.add(curr);
    }
}

