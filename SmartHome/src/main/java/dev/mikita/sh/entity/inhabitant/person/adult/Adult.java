package dev.mikita.sh.entity.inhabitant.person.adult;

import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultDeviceUsingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.location.Room;

public class Adult extends APerson {
    public Adult(Room room, String name) {
        super(room, name);
        this.state = new AdultWaitingState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 5;
        this.deviceBreakingChance = 10;
    }

    public void fixDevice(ADevice device) {
        usingObject = device;
        device.fix(this);
    }

    @Override
    public void useObject(UsableObject object) {
        this.usingObject = object;
        changeState(new AdultDeviceUsingState(this));
    }

    @Override
    public void unUseObject(UsableObject object) {
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