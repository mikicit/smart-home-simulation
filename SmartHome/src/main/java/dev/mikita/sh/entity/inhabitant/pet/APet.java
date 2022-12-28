package dev.mikita.sh.entity.inhabitant.pet;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.inhabitant.person.child.Child;
import dev.mikita.sh.entity.inhabitant.pet.state.PetAwakeState;
import dev.mikita.sh.entity.inhabitant.pet.state.PetDeviceUsingState;
import dev.mikita.sh.entity.location.Room;

import java.util.logging.Logger;

public abstract class APet extends AInhabitant  {
    // Logger
    private static final Logger log = Logger.getLogger(APet.class.getName());
    protected boolean dispatchedHungerEvent = false;
    protected boolean dispatchedBoredEvent = false;

    public APet(Room room, String name) {
        super(room, name);
        this.state = new PetAwakeState(this);
        this.hungerIndicator = 100;
        this.leisureIndicator = 100;
        this.hungerPerHour = 10;
        this.leisurePerHour = 1;
        this.deviceBreakingChance = 0.2;
    }

    public void beFed(Adult adult) {
        adult.feedPet(this);
    }

    public void beEntertained(Adult adult) {
        adult.playWithPet(this);
    }

    @Override
    public void useObject(IUsableObject object) {
            this.usingObject = object;
            changeState(new PetDeviceUsingState(this));

//            log.info(String.format("Device \"%s\" is broken, cannot be used! [%s]",
//                    object.getName(),
//                    SHSystem.getInstance().getSimulation().getFormattedTime()));

    }

    @Override
    public void unUseObject(IUsableObject object) {
        this.usingObject = null;
        changeState(new PetAwakeState(this));
    }

    @Override
    public void toBreakDevice(ADevice device) {
        this.usingObject = device;
        changeState(new PetAwakeState(this));
    }
}
