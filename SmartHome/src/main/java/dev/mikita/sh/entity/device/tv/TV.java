package dev.mikita.sh.entity.device.tv;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.tv.state.TVBrokenState;
import dev.mikita.sh.entity.device.tv.state.TVFixingState;
import dev.mikita.sh.entity.device.tv.state.TVIdleState;
import dev.mikita.sh.entity.device.tv.state.TVUsingState;
import dev.mikita.sh.entity.device.washingMachine.state.WashingMachineBrokenState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class TV extends ADevice {
    public TV(Room room, String name) {
        super(room, name);
        this.state = new TVIdleState(this);
        this.operatingTimeInHours = 1000;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 0;
        this.leisurePerHour = 20;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        changeState(new TVUsingState(this));
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        changeState(new TVIdleState(this));
    }

    @Override
    public void fix(Adult person) {
        person.fixDevice(this);
        changeState(new TVFixingState(this));
    }

    @Override
    public void toBeBroken(AInhabitant inhabitant) {
        inhabitant.toBreakDevice(this);
        changeState(new TVBrokenState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
