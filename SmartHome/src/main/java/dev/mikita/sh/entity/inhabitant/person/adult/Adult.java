package dev.mikita.sh.entity.inhabitant.person.adult;

import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.person.APerson;
import dev.mikita.sh.entity.inhabitant.AInhabitantState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultDeviceUsingState;
import dev.mikita.sh.entity.inhabitant.person.adult.state.AdultWaitingState;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.inhabitant.pet.APet;
import dev.mikita.sh.entity.location.Room;

import java.util.HashMap;
import java.util.logging.Logger;

public class Adult extends APerson {
    // Logger
    private static final Logger log = Logger.getLogger(Adult.class.getName());

    public Adult(Room room, String name) {
        super(room, name);
        this.state = new AdultWaitingState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 5;
        this.deviceBreakingChance = 0.1;
    }

    public void fixDevice(ADevice device) {
        usingObject = device;
        device.fix(this);
    }

    // TODO handle events
    public void feedChild(Child child) {
        moveTo(child.getRoom());
        child.setHungerIndicator(100);
    }

    public void feedPet(APet pet) {
        moveTo(pet.getRoom());
        pet.setHungerIndicator(100);
    }

    public void playWithPet(APet pet) {
        pet.setLeisureIndicator(85);
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
    public void toBreakDevice(ADevice device) {
        this.usingObject = device;
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

    private void usedObjectsMap(APerson person, IUsableObject object) {
        if (!usedObjects.containsKey(person)) {
            usedObjects.put(person, new HashMap<>());
        }

        if (!usedObjects.get(person).containsKey(object)) {
            usedObjects.get(person).put(object, 0);
        }

        usedObjects.get(person).put(object, usedObjects.get(person).get(object) + 1);
    }
}