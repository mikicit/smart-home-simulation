package dev.mikita.sh.entity.location;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int level;
    private final List<Room> rooms = new ArrayList<>();

    public Floor(int level) {
        this.level = level;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public int getLevel() {
        return level;
    }
}
