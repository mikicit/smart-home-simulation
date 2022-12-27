package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
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

    @Override
    public void toBreakDevice(ADevice device) {
        this.usingObject = device;
        changeState(new AdultWaitingState(this));
    }
}
