import java.util.*;

public class Main {
    public static void main(String[] args) {
        BrooklynTechNavigator nav = new BrooklynTechNavigator();  // Creating an instance of the BrooklynTechNavigator class
        Scanner sc = new Scanner(System.in);  // Scanner to read user input

        // Introduction and instructions to the user
        System.out.println("Welcome to Brooklyn Tech Navigator!");
        System.out.println("Enter your current room (e.g., 'W12F1'):");

        String startId = sc.nextLine().trim().toUpperCase();  // User input for the start room

        System.out.println("Enter your destination room (e.g., 'W14F2'):");

        String endId = sc.nextLine().trim().toUpperCase();  // User input for the destination room

        // Validation to ensure the rooms entered exist in the graph
        if (!nav.getNodes().containsKey(startId) || !nav.getNodes().containsKey(endId)) {
            System.out.println("Invalid room(s). Please check your input.");
            return;
        }

        // Find the shortest path using Dijkstra's algorithm from BrooklynTechNavigator
        List<Node> path = nav.shortestPath(startId, endId);
        
        // If no path is found, inform the user
        if (path == null) {
            System.out.println("No path found between those rooms.");
        } else {
            // If path is found, display the directions
            System.out.println("\nDIRECTIONS:");
            for (Node n : path) {
                System.out.println(" - " + n.label);
            }

            // Calculate and display the estimated travel time
            int totalTime = nav.calculateTime(path);
            System.out.println("Estimated travel time: " + totalTime + " seconds.");
        }
    }
}