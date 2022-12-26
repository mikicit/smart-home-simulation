package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.entity.location.Room;

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

    public Room getRoom() {
        return room;
    }

    public boolean isOpen() {
        return state == EntranceState.OPEN;
    }

    public abstract void open();
    public abstract void close();
}
