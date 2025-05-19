public class BrooklynTechNavigator {
    private Map<String, Node> nodes = new HashMap<>();

    public Map<String, Node> getNodes() {
    return nodes;
}

    public BrooklynTechNavigator() {
        buildSchoolGraph();
    }

    // Build a simplified version of the school graph
    private void buildSchoolGraph() {
        // Add some example nodes (expand as needed)
        addNode("W12F1", "West 12, Floor 1");
        addNode("W14F1", "West 14, Floor 1");
        addNode("W12F2", "West 12, Floor 2");
        addNode("W14F2", "West 14, Floor 2");
        addNode("E12F1", "East 12, Floor 1");
        addNode("E14F1", "East 14, Floor 1");
        addNode("StairW12F1", "Staircase West 12, Floor 1");
        addNode("StairW12F2", "Staircase West 12, Floor 2");
        addNode("StairE12F1", "Staircase East 12, Floor 1");
        addNode("StairE12F2", "Staircase East 12, Floor 2");

        // Hallways (edges on same floor)
        addEdge("W12F1", "W14F1", 10);
        addEdge("E12F1", "E14F1", 10);
        // Connect rooms to staircases
        addEdge("W12F1", "StairW12F1", 5);
        addEdge("W12F2", "StairW12F2", 5);
        addEdge("E12F1", "StairE12F1", 5);
        addEdge("E12F2", "StairE12F2", 5);
        // Stairs between floors
        addEdge("StairW12F1", "StairW12F2", 20);
        addEdge("StairE12F1", "StairE12F2", 20);
        // Hallways on floor 2
        addEdge("W12F2", "W14F2", 10);
        // Example: connect east and west via central hallway (add more as needed)
        // addEdge("W14F1", "E14F1", 30);

        // You would continue adding all rooms, intersections, staircases, and their connections here
    }

    private void addNode(String id, String label) {
        nodes.put(id, new Node(id, label));
    }

    private void addEdge(String fromId, String toId, int time) {
        Node from = nodes.get(fromId);
        Node to = nodes.get(toId);
        if (from != null && to != null) {
            from.edges.add(new Edge(to, time));
            to.edges.add(new Edge(from, time)); // bidirectional
        }
    }

    // Dijkstra's algorithm for shortest path
    public List<Node> shortestPath(String startId, String endId) {
        if (!nodes.containsKey(startId) || !nodes.containsKey(endId)) {
            return null;
        }
        Map<Node, Integer> dist = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));
        for (Node n : nodes.values()) dist.put(n, Integer.MAX_VALUE);
        Node start = nodes.get(startId);
        Node end = nodes.get(endId);
        dist.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr == end) break;
            for (Edge e : curr.edges) {
                int alt = dist.get(curr) + e.time;
                if (alt < dist.get(e.to)) {
                    dist.put(e.to, alt);
                    prev.put(e.to, curr);
                    pq.add(e.to);
                }
            }
        }
        // Reconstruct path
        List<Node> path = new LinkedList<>();
        for (Node at = end; at != null; at = prev.get(at)) {
            path.add(0, at);
        }
        if (path.get(0) != start) return null; // No path found
        return path;
    }

    // Calculate total time for a path
    public int calculateTime(List<Node> path) {
        int total = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            Node current = path.get(i);
            Node next = path.get(i + 1);
            for (Edge e : current.edges) {
                if (e.to == next) {
                    total += e.time;
                    break;
                }
            }
        }
        return total;
    }

    // Helper: Get node ID from user-friendly input
    public String parseRoomInput(String input) {
        input = input.trim().toUpperCase();
        // Example: "West 12, Floor 1" -> "W12F1"
        String[] parts = input.split(",");
        if (parts.length != 2) return null;
        String sideRoom = parts[0].trim();
        String floorStr = parts[1].trim();
        String side = sideRoom.substring(0, 1);
        String roomNum = sideRoom.replaceAll("[^0-9]", "");
        String floorNum = floorStr.replaceAll("[^0-9]", "");
        if (side.equals("W")) side = "W";
        else if (side.equals("E")) side = "E";
        else return null;
        return side + roomNum + "F" + floorNum;
    }
}