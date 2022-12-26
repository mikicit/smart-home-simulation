package dev.mikita.sh.entity.device.oven;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.oven.state.OvenFixingState;
import dev.mikita.sh.entity.device.oven.state.OvenIdleState;
import dev.mikita.sh.entity.device.oven.state.OvenUsingState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class Oven extends ADevice {
    public Oven(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 1500;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 25;
        this.leisurePerHour = 11;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        changeState(new OvenUsingState(this));
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        changeState(new OvenIdleState(this));
    }

    @Override
    public void fix(Adult person) {
        person.fixDevice(this);
        changeState(new OvenFixingState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
