package dev.mikita.sh.entity.device.airConditioner;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.airConditioner.state.AirConditionerBrokenState;
import dev.mikita.sh.entity.device.airConditioner.state.AirConditionerFixingState;
import dev.mikita.sh.entity.device.airConditioner.state.AirConditionerIdleState;
import dev.mikita.sh.entity.device.airConditioner.state.AirConditionerUsingState;
import dev.mikita.sh.entity.device.washingMachine.state.WashingMachineBrokenState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class AirConditioner extends ADevice {
    public AirConditioner(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 1250;
        this.usageTimeInHour = 0;
        this.hungerPerHour = 0;
        this.leisurePerHour = 0;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        changeState(new AirConditionerUsingState(this));
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        changeState(new AirConditionerIdleState(this));
    }

    @Override
    public void fix(Adult person) {
        person.fixDevice(this);
        changeState(new AirConditionerFixingState(this));
    }

    @Override
    public void toBeBroken(AInhabitant inhabitant) {
        inhabitant.toBreakDevice(this);
        changeState(new AirConditionerBrokenState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
