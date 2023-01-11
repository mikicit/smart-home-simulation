package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.Room;

/**
 * Abstract class representing entrance.
 */
public abstract class AEntrance implements IEventSource {
    // State
    protected enum EntranceState {
        OPEN,
        CLOSE
    }

    protected Room room;
    protected EntranceState state = EntranceState.CLOSE;

    public AEntrance(Room room) {
        this.room = room;
    }

    /**
     * Returns the room.
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Checks if open.
     * @return true if open
     */
    public boolean isOpen() {
        return state == EntranceState.OPEN;
    }

    /**
     * Returns the name.
     * @return name
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Opens the entrance.
     */
    public abstract void open();

    /**
     * Closes the entrance.
     */
    public abstract void close();
}
