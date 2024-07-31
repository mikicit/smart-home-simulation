package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the door.
 */
public class Door extends AEntrance {
    public Door(Room room) {
        super(room);
    }

    /**
     * Opens the door.
     */
    @Override
    public void open() {
        state = EntranceState.OPEN;
    }

    /**
     * Closes the door.
     */
    @Override
    public void close() {
        state = EntranceState.CLOSE;
    }
}
