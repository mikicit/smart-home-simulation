package dev.mikita.sh.entity.inhabitant.person.child;

import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.location.Room;

public class Child extends APerson {
    public Child(Room room, String name) {
        super(room, name);
        this.state = new ChildAwakeState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 5;
        this.deviceBreakingChance = 50;
    }

    @Override
    public void update(long time) {
        this.state.update(time);
    }

    @Override
    public void changeState(AInhabitantState state) {
        this.state = state;
    }

    @Override
    public void useObject(UsableObject object) {}

    @Override
    public void unUseObject(UsableObject object) {}
}
