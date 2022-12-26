package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.entity.location.Room;

public class Door extends AEntrance {
    public Door(Room room) {
        super(room);
    }

    public void open() {
        state = EntranceState.OPEN;
    }

    public void close() {
        state = EntranceState.CLOSE;
    }
}
