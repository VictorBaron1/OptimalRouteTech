import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BrooklynTechNavigator nav = new BrooklynTechNavigator();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Brooklyn Tech Navigator!");
        System.out.println("Sample valid rooms for this demo: 1W12, 1W14, 2W12, 2W14, 1E12, 1E14");
        System.out.println("Enter your current room (e.g., '1W12'):");
        String startId = sc.nextLine().trim().toUpperCase();

        System.out.println("Enter your destination room (e.g., '2W14'):");
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
