class Navigation {
    SchoolMap schoolMap;

    public Navigation(SchoolMap schoolMap) {
        this.schoolMap = schoolMap;
    }

    public List<Room> findShortestPath(Room start, Room destination) {
        Map<Room, Room> previousRoom = new HashMap<>();
        Queue<Room> queue = new LinkedList<>();
        Set<Room> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Room currentRoom = queue.poll();

            // If we reach the destination, backtrack to find the path
            if (currentRoom.equals(destination)) {
                List<Room> path = new ArrayList<>();
                for (Room at = destination; at != null; at = previousRoom.get(at)) {
                    path.add(at);
                }
                Collections.reverse(path);
                return path;
            }

            for (Room neighbor : currentRoom.neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    previousRoom.put(neighbor, currentRoom);
                }
            }
        }

        return new ArrayList<>(); // Return empty list if no path found
    }

    public int calculateTime(List<Room> path) {
        // Time per step, assuming 1 minute per room traversal (can be adjusted)
        return path.size();
    }
}