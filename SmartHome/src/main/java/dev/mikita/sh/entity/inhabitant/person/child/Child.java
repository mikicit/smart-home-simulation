package dev.mikita.sh.entity.inhabitant.person.child;

import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.location.Room;

public class Child extends APerson {
    public Child(Room room, String name) {
        super(room, name);
    }

    @Override
    public void update(long time) {
        this.state.update(time);
    }

    @Override
    public void changeState(AInhabitantState state) {
        this.state = state;
    }
}
