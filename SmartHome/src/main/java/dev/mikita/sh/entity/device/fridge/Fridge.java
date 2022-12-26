package dev.mikita.sh.entity.device.fridge;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.fridge.state.FridgeFixingState;
import dev.mikita.sh.entity.device.fridge.state.FridgeIdleState;
import dev.mikita.sh.entity.device.fridge.state.FridgeUsingState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class Fridge extends ADevice {
    public Fridge(Room room, String name) {
        super(room, name);
        this.state = new FridgeIdleState(this);
        this.operatingTimeInHours = 25;
        this.usageTimeInHour = 0.25;
        this.hungerPerHour = 35;
        this.leisurePerHour = 15;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        changeState(new FridgeUsingState(this));
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        changeState(new FridgeIdleState(this));
    }

    @Override
    public void fix(Adult person) {
        person.fixDevice(this);
        changeState(new FridgeFixingState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
