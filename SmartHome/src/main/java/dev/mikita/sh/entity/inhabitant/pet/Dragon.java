package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.location.Room;

public class Dragon extends APet {
    public Dragon(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {
        state.update(time);
    }

    @Override
    public void changeState(AInhabitantState state) {
        this.state = state;
    }
}
