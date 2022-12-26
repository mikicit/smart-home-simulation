package dev.mikita.sh.entity.device.washingMachine;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.washingMachine.state.WashingMachineFixingState;
import dev.mikita.sh.entity.device.washingMachine.state.WashingMachineIdleState;
import dev.mikita.sh.entity.device.washingMachine.state.WashingMachineUsingState;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class WashingMachine extends ADevice {
    public WashingMachine(Room room, String name) {
        super(room, name);
        this.state = new WashingMachineIdleState(this);
        this.operatingTimeInHours = 1400;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 0;
        this.leisurePerHour = 15;
    }

    @Override
    public void use(AInhabitant inhabitant) {
        inhabitant.useObject(this);
        changeState(new WashingMachineUsingState(this));
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        inhabitant.unUseObject(this);
        changeState(new WashingMachineIdleState(this));
    }

    @Override
    public void fix(Adult person) {
        person.fixDevice(this);
        changeState(new WashingMachineFixingState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
