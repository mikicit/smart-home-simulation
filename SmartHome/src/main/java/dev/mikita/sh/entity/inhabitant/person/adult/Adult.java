package dev.mikita.sh.entity.inhabitant.person.adult;

import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultDeviceUsingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.location.Room;

public class Adult extends APerson {
    public Adult(Room room, String name) {
        super(room, name);
    }

    public void useObject(UsableObject object) {
        this.usingObject = object;
        changeState(new AdultDeviceUsingState(this));
    }

    public void unUseObject() {
        this.usingObject = null;
        changeState(new AdultWaitingState(this));
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