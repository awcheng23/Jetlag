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

        @Override
        public String toString() {
            return data+":"+cost;
        }
    }

    // Add an aiport
    public void addVertex(String source) {
        if(!adjacencyList.containsKey(source))
            adjacencyList.put(source, new LinkedList<>());
    }

    // Add a path
    public void addEdge(String source, String destination, int cost) {
        if(!adjacencyList.containsKey(source))
            throw new NoSuchElementException();
        adjacencyList.get(source).add(new Node(destination, cost));
    }

    public String[] findShortestPath(String source, String destination) {
        HashMap<String, Node> paths = new HashMap<>();
        for(String key : adjacencyList.keySet())
            paths.put(key, new Node("", Integer.MAX_VALUE));
        
        paths.put(source, new Node("", 0));
        Set<String> visited = new HashSet<>();
        String curr = source;
        int length = 0;

        pathHelper(curr, length, visited, paths, destination);

        String[] result = new String[2];

        Node end = paths.get(destination);
        result[0] = Integer.toString(end.cost);

        String path = destination;
        while(!"".equals(end.data)){
            path = end.data + "," + path;
            end = paths.get(end.data);
        }

        result[1] = path;

        System.out.println(paths);
        
        return result;
    }

    private void pathHelper(String curr, int length, Set<String> visited, HashMap<String, Node> paths, String destination) {
        if(curr.equals(destination))
            return;
        
        visited.add(curr);
        for(Node n : adjacencyList.get(curr)) {
            int newLength = length + n.cost;
            if(newLength < paths.get(n.data).cost)
                paths.put(n.data, new Node(curr, newLength));
        }

        String next = findMinUnvisitedKey(paths, visited);

        pathHelper(next, paths.get(next).cost, visited, paths, destination);
    }

    private static String findMinUnvisitedKey(HashMap<String, Node> map, Set<String> visited) {
        String minKey = "";
        int minValue = Integer.MAX_VALUE;

        for(String key : map.keySet()) {
            if(!visited.contains(key) && map.get(key).cost < minValue) {
                minValue = map.get(key).cost;
                minKey = key;
            }
        }
        return minKey;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addVertex("A"); g.addVertex("B"); g.addVertex("C"); g.addVertex("D"); 
        g.addVertex("E"); g.addVertex("F"); g.addVertex("G"); g.addVertex("H");
        g.addEdge("A", "B", 6); g.addEdge("A", "C", 2); g.addEdge("A", "F", 4); g.addEdge("A", "G", 5); g.addEdge("A", "H", 6);
        g.addEdge("B", "A", 6); g.addEdge("B", "D", 5); g.addEdge("B", "E", 2); g.addEdge("B", "F", 8);
        g.addEdge("C", "A", 2); g.addEdge("C", "G", 3); g.addEdge("C", "H", 8);
        g.addEdge("D", "B", 5); g.addEdge("D", "F", 7); 
        g.addEdge("E", "F", 6); g.addEdge("E", "H", 11); g.addEdge("E", "B", 2);
        g.addEdge("F", "A", 4); g.addEdge("F", "B", 8); g.addEdge("F", "D", 7); g.addEdge("F", "E", 6); g.addEdge("F", "G", 3);
        g.addEdge("G", "A", 5); g.addEdge("G", "C", 3); g.addEdge("G", "H", 2); g.addEdge("G", "F", 3);
        g.addEdge("H", "A", 6); g.addEdge("H", "C", 8); g.addEdge("H", "G", 2); g.addEdge("H", "E", 11);

        String[] ans = g.findShortestPath("C", "D");

        System.out.println(ans[1]);
    }
}

