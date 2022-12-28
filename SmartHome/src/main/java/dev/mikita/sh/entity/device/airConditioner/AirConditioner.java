package dev.mikita.sh.entity.device.airConditioner;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.airConditioner.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class AirConditioner extends ADevice {
    // Constants
    private final double COOLING_PER_HOUR = 3;

    public AirConditioner(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 200;
        this.usageTimeInHour = 2;
        this.hungerPerHour = 0;
        this.leisurePerHour = 0;
    }

    public double getCoolingPerHour() {
        return COOLING_PER_HOUR;
    }

    @Override
    public void on() {
        if (isOff()) {
            changeState(new AirConditionerIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new AirConditionerOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new AirConditionerBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            inhabitant.useObject(this);
            changeState(new AirConditionerUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            inhabitant.unUseObject(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new AirConditionerFixingState(this));
        }
    }

    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
