import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BrooklynTechNavigator nav = new BrooklynTechNavigator();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Brooklyn Tech Navigator!");
        System.out.println("Sample valid rooms for this demo: W12F1, W14F1, W12F2, W14F2, E12F1, E14F1");
        System.out.println("Enter your current room (e.g., 'W12F1'):");
        String startId = sc.nextLine().trim().toUpperCase();

        System.out.println("Enter your destination room (e.g., 'W14F2'):");
        String endId = sc.nextLine().trim().toUpperCase();

        if (!nav.getNodes().containsKey(startId) || !nav.getNodes().containsKey(endId)) {
            System.out.println("Invalid room(s). Please check your input.");
            return;
}


        List<Node> path = nav.shortestPath(startId, endId);
        if (path == null) {
            System.out.println("No path found between those rooms.");
        } else {
            System.out.println("\nDIRECTIONS:");
            for (Node n : path) {
                System.out.println(" - " + n.label);
            }
            int totalTime = nav.calculateTime(path);
            System.out.println("Estimated travel time: " + totalTime + " seconds.");
        }
    }
}
