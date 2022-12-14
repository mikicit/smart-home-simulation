package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.time.ITimeTracker;
import dev.mikita.sh.entity.location.Room;

public abstract class AEntrance implements ITimeTracker, IEventSource {
    public AEntrance() {
        SHSystem.getInstance().getTimer().subscribe(this);
    }

    protected enum EntranceState {
        OPEN,
        CLOSE
    }

    protected Room room;
    protected EntranceState state = EntranceState.CLOSE;

    public AEntrance(Room room) {
        this.room = room;
    }

    protected void switchState() {
        state = state == EntranceState.OPEN ? EntranceState.CLOSE : EntranceState.OPEN;
    }

    public Room getRoom() {
        return room;
    }
}
