import java.util.*;

public class BrooklynTechNavigator {
    private Map<String, Node> nodes = new HashMap<>();  // A map of all nodes (rooms) in the building

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public BrooklynTechNavigator() {
        buildSchoolGraph();  // Initialize the school graph with rooms and edges
    }

    // Build the school graph with rooms (nodes) and their connections (edges)
    private void buildSchoolGraph() {
        // Add rooms for all floors based on your detailed description

        // Basement - Only North and West areas
        addNode("B1W1", "West 1, Basement");
        addNode("B1W2", "West 2, Basement");
        addNode("B1W3", "West 3, Basement");
        addNode("B1W4", "West 4, Basement");
        addNode("B1W5", "West 5, Basement");
        addNode("B1W6", "West 6, Basement");
        addNode("B1W7", "West 7, Basement");
        addNode("B1W8", "West 8, Basement");
        addNode("B1W9", "West 9, Basement");

        addNode("B1N1", "North 1, Basement");
        addNode("B1N2", "North 2, Basement");
        addNode("B1N3", "North 3, Basement");
        addNode("B1N4", "North 4, Basement");
        addNode("B1N5", "North 5, Basement");
        addNode("B1N6", "North 6, Basement");
        addNode("B1N7", "North 7, Basement");
        addNode("B1N8", "North 8, Basement");
        addNode("B1N9", "North 9, Basement");

        // Connect the West and North areas in the Basement (for example, adjacent rooms)
        addEdge("B1W1", "B1W2", 10);
        addEdge("B1W2", "B1W3", 10);
        addEdge("B1W3", "B1W4", 10);
        // Add more edges for other rooms on this floor

        // Floor 1: North, South, East, West, Center
        addNode("1W1", "West 1, Floor 1");
        addNode("1W2", "West 2, Floor 1");
        addNode("1W3", "West 3, Floor 1");
        addNode("1W4", "West 4, Floor 1");
        // ... Continue adding rooms for West, East, South, North, and Center for Floor 1
        // Add Auditorium and Gym center in the middle of Floor 1

        // Adding staircases on the 1st floor
        addNode("StairNW", "Staircase NorthWest, Floor 1");
        addNode("StairNE", "Staircase NorthEast, Floor 1");
        addNode("StairSW", "Staircase SouthWest, Floor 1");
        addNode("StairSE", "Staircase SouthEast, Floor 1");

        // Connecting rooms with staircases and other rooms
        addEdge("1W1", "StairNW", 10);
        addEdge("1E1", "StairNE", 10);
        addEdge("1S1", "StairSW", 10);
        addEdge("1C1", "StairSE", 10);

        // Floor 2 - Normal layout with rooms
        for (int i = 1; i <= 24; i++) {
            addNode("2W" + i, "West " + i + ", Floor 2");
            addNode("2E" + i, "East " + i + ", Floor 2");
        }

        // Adding Center Rooms for Floor 2
        for (int i = 1; i <= 8; i++) {
            addNode("2C" + i, "Center " + i + ", Floor 2");
        }

        // Add stair connections between floors
        addEdge("StairNW", "StairNE", 20); // Example stair connection
        addEdge("StairSW", "StairSE", 20);

        // Repeat the process for floors 3 to 8, making the necessary adjustments as per your layout
        // The 7th floor (Cafeteria) has its own rules for connections, which you would account for
        // on the layout (entry only from corners, etc.)

        // You can add more rooms for each floor based on your description
    }

    // Method to add rooms (nodes)
    private void addNode(String id, String label) {
        nodes.put(id, new Node(id, label));
    }

    // Method to add edges (connections between rooms)
    private void addEdge(String fromId, String toId, int time) {
        Node from = nodes.get(fromId);
        Node to = nodes.get(toId);
        if (from != null && to != null) {
            from.edges.add(new Edge(to, time));
            to.edges.add(new Edge(from, time));  // Bidirectional edges
        }
    }

    // Dijkstra's algorithm for shortest path between two rooms
    public List<Node> shortestPath(String startId, String endId) {
        if (!nodes.containsKey(startId) || !nodes.containsKey(endId)) {
            return null;  // Invalid rooms
        }

        // Initialize the distance map and priority queue for Dijkstra's algorithm
        Map<Node, Integer> dist = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        // Set all distances to infinity, except the start node
        for (Node n : nodes.values()) dist.put(n, Integer.MAX_VALUE);
        Node start = nodes.get(startId);
        Node end = nodes.get(endId);
        dist.put(start, 0);
        pq.add(start);

        // Perform Dijkstra's algorithm
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current == end) break;  // Stop if we reach the destination

            for (Edge e : current.edges) {
                int alt = dist.get(current) + e.time;  // Alternative path through this edge
                if (alt < dist.get(e.to)) {  // If this path is shorter, update
                    dist.put(e.to, alt);
                    prev.put(e.to, current);
                    pq.add(e.to);  // Add the neighbor to the queue
                }
            }
        }

        // Reconstruct the path from the destination to the start
        List<Node> path = new LinkedList<>();
        for (Node at = end; at != null; at = prev.get(at)) {
            path.add(0, at);  // Add to the start of the path
        }

        // If the start node is not in the path, no path was found
        if (path.get(0) != start) return null;

        return path;  // Return the path from start to end
    }

    // Calculate total travel time for the given path
    public int calculateTime(List<Node> path) {
        int totalTime = 0;

        // Loop through each pair of adjacent nodes in the path
        for (int i = 0; i < path.size() - 1; i++) {
            Node current = path.get(i);    // Current node
            Node next = path.get(i + 1);   // Next node

            // Find the edge between the current and next node
            for (Edge e : current.edges) {
                if (e.to == next) {  // If this is the edge between current and next node
                    totalTime += e.time;  // Add the edge's time to totalTime
                    break;  // Exit the loop once we find the correct edge
                }
            }
        }

        return totalTime;  // Return the total travel time in seconds
    }
}