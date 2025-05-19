class SchoolMap {
    Map<String, Room> rooms;

    public SchoolMap() {
        rooms = new HashMap<>();
        initializeRooms();
        connectRooms();
    }

    // Initialize rooms based on your description
    private void initializeRooms() {
        // Example: Create rooms for each side (North, South, East, West) and floor 1-8
        for (int floor = 1; floor <= 8; floor++) {
            // North side rooms
            for (int roomNumber = 1; roomNumber <= 8; roomNumber++) {
                String roomName = "North" + roomNumber + "F" + floor;
                rooms.put(roomName, new Room(roomName, floor, roomNumber));
            }
            // South side rooms
            for (int roomNumber = 1; roomNumber <= 8; roomNumber++) {
                String roomName = "South" + roomNumber + "F" + floor;
                rooms.put(roomName, new Room(roomName, floor, roomNumber));
            }
            // East side rooms (Room 2-24)
            for (int roomNumber = 2; roomNumber <= 24; roomNumber++) {
                String roomName = "East" + roomNumber + "F" + floor;
                rooms.put(roomName, new Room(roomName, floor, roomNumber));
            }
            // West side rooms (Room 2-24)
            for (int roomNumber = 2; roomNumber <= 24; roomNumber++) {
                String roomName = "West" + roomNumber + "F" + floor;
                rooms.put(roomName, new Room(roomName, floor, roomNumber));
            }
        }
    }

    // Connect the rooms based on adjacency
    private void connectRooms() {
        for (int floor = 1; floor <= 8; floor++) {
            // North-South Connections (same floor)
            for (int roomNumber = 1; roomNumber <= 8; roomNumber++) {
                String northRoom = "North" + roomNumber + "F" + floor;
                String southRoom = "South" + roomNumber + "F" + floor;
                if (rooms.containsKey(northRoom) && rooms.containsKey(southRoom)) {
                    rooms.get(northRoom).addNeighbor(rooms.get(southRoom));
                    rooms.get(southRoom).addNeighbor(rooms.get(northRoom));
                }
            }

            // East-West Connections (same floor)
            for (int roomNumber = 2; roomNumber <= 24; roomNumber++) {
                String eastRoom = "East" + roomNumber + "F" + floor;
                String westRoom = "West" + roomNumber + "F" + floor;
                if (rooms.containsKey(eastRoom) && rooms.containsKey(westRoom)) {
                    rooms.get(eastRoom).addNeighbor(rooms.get(westRoom));
                    rooms.get(westRoom).addNeighbor(rooms.get(eastRoom));
                }
            }
        }
    }
}
