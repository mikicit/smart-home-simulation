package dev.mikita.sh.entity.device.light;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.light.state.LightFixingState;
import dev.mikita.sh.entity.device.light.state.LightIdleState;
import dev.mikita.sh.entity.device.light.state.LightUsingState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class Light extends ADevice {
    public Light(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 500;
        this.usageTimeInHour = 0;
        this.hungerPerHour = 0;
        this.leisurePerHour = 0;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        changeState(new LightUsingState(this));
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        changeState(new LightIdleState(this));
    }

    @Override
    public void fix(Adult person) {
        person.fixDevice(this);
        changeState(new LightFixingState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
