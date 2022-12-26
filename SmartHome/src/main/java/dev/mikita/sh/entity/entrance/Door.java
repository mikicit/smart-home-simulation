package dev.mikita.sh.entity.entrance;

import dev.mikita.sh.entity.location.Room;

public class Door extends AEntrance {
    public Door(Room room) {
        super(room);
    }

    @Override
    public void open() {
        state = EntranceState.OPEN;
    }

    @Override
    public void close() {
        state = EntranceState.CLOSE;
    }
}
