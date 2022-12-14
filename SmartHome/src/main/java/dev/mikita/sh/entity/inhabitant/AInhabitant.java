package dev.mikita.sh.entity.inhabitant;

import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.time.ITimeTracker;
import dev.mikita.sh.entity.location.Room;

public abstract class AInhabitant implements ITimeTracker, IEventSource {
    protected String name;
    protected Room room;
    protected AInhabitantState state;

    public abstract void changeState(AInhabitantState state);

    public AInhabitant(Room room, String name) {
        this.room = room;
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }

    public String getName() {
        return name;
    }

    public void moveTo(Room room) {
        this.room = room;
    }
}
