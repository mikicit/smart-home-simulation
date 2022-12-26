package dev.mikita.sh.entity.device.microwave;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.microwave.state.MicrowaveFixingState;
import dev.mikita.sh.entity.device.microwave.state.MicrowaveIdleState;
import dev.mikita.sh.entity.device.microwave.state.MicrowaveUsingState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class Microwave extends ADevice {
    public Microwave(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 1000;
        this.usageTimeInHour = 0.084;
        this.hungerPerHour = 25;
        this.leisurePerHour = 12;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        changeState(new MicrowaveUsingState(this));
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        changeState(new MicrowaveIdleState(this));
    }

    @Override
    public void fix(Adult person) {
        person.fixDevice(this);
        changeState(new MicrowaveFixingState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
