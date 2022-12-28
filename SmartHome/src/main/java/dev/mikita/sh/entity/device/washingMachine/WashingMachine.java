package dev.mikita.sh.entity.device.washingMachine;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.washingMachine.state.*;
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
    public void on() {
        if (isOff()) {
            changeState(new WashingMachineIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new WashingMachineOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new WashingMachineBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            inhabitant.useObject(this);
            changeState(new WashingMachineUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            inhabitant.unUseObject(this);
            changeState(new WashingMachineIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new WashingMachineFixingState(this));
        }
    }

    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new WashingMachineIdleState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
