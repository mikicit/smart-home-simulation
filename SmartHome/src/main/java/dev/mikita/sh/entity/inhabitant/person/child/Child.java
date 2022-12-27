package dev.mikita.sh.entity.inhabitant.person.child;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildAwakeState;
import dev.mikita.sh.entity.inhabitant.person.child.state.ChildDeviceUsingState;
import dev.mikita.sh.entity.location.Room;

import java.util.logging.Logger;

public class Child extends APerson {
    // Logger
    private static final Logger log = Logger.getLogger(Child.class.getName());

    public Child(Room room, String name) {
        super(room, name);
        this.state = new ChildAwakeState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 2;
        this.deviceBreakingChance = 0.5;
    }

    public void beFed(Adult adult) {
        adult.feedChild(this);
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

//            log.info(String.format("Device \"%s\" is broken, cannot be used! [%s]",
//                    object.getName(),
//                    SHSystem.getInstance().getSimulation().getFormattedTime()));

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
