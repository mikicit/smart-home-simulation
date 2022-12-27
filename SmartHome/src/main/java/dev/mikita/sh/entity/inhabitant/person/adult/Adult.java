package dev.mikita.sh.entity.inhabitant.person.adult;

import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultDeviceUsingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.location.Room;

import java.util.HashMap;

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

    private void usedObjectsMap(APerson person, IUsableObject object) {
        if (!usedObjects.containsKey(person)) {
            usedObjects.put(person, new HashMap<>());
        }

        if (!usedObjects.get(person).containsKey(object)) {
            usedObjects.get(person).put(object, 0);
        }

        usedObjects.get(person).put(object, usedObjects.get(person).get(object) + 1);
    }

    @Override
    public void useObject(IUsableObject object) {
        this.usingObject = object;
        usedObjectsMap(this, object);
        changeState(new AdultDeviceUsingState(this));
    }

    @Override
    public void unUseObject(IUsableObject object) {
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