package dev.mikita.sh.entity.inhabitant.person.child;

import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildDeviceUsingState;
import dev.mikita.sh.entity.location.Room;

public class Child extends APerson {
    public Child(Room room, String name) {
        super(room, name);
        this.state = new ChildAwakeState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 5;
        this.deviceBreakingChance = 0.5;
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
    public void useObject(IUsableObject object) {
        this.usingObject = object;
        changeState(new ChildDeviceUsingState(this));
    }

    @Override
    public void unUseObject(IUsableObject object) {
        this.usingObject = null;
        changeState(new ChildAwakeState(this));
    }

    @Override
    public void toBreakDevice(ADevice device) {
        this.usingObject = device;
        changeState(new ChildAwakeState(this));
    }
}
