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

        String path = end.data;
        while(!"".equals(end.data)){
            end = paths.get(end.data);
            path = end.data + "," + path;
        }

        result[1] = path;
        
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
}

