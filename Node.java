import java.util.*;

// Node represents a room, hallway intersection, or staircase
class Node {
    String id;         // e.g., "W12F1" (West 12, Floor 1)
    String label;      // e.g., "West 12, Floor 1"
    List<Edge> edges = new ArrayList<>();

    Node(String id, String label) {
        this.id = id;
        this.label = label;
    }
}
