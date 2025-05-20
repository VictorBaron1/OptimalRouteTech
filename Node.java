import java.util.*;

public class Node {
    String id;         
    String label;      
    List<Edge> edges = new ArrayList<>();

    Node(String id, String label) {
        this.id = id;
        this.label = label;
    }
}
