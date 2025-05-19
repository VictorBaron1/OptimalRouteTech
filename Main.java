import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SchoolMap schoolMap = new SchoolMap();
        Navigation navigation = new Navigation(schoolMap);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the starting room (e.g., East12F1): ");
        String startRoomName = scanner.nextLine();
        System.out.println("Enter the destination room (e.g., West24F5): ");
        String destinationRoomName = scanner.nextLine();

        Random startRoom = schoolMap.rooms.get(startRoomName);
        Room destinationRoom = schoolMap.rooms.get(destinationRoomName);

        if (startRoom == null || destinationRoom == null) {
            System.out.println("Invalid room names.");
            return;
        }

        List<Room> path = navigation.findShortestPath(startRoom, destinationRoom);
        if (path.isEmpty()) {
            System.out.println("No path found.");
        } else {
            System.out.println("Shortest path:");
            for (Room room : path) {
                System.out.print(room + " -> ");
            }
            System.out.println("\nTotal time: " + navigation.calculateTime(path) + " minutes.");
        }
    }
}