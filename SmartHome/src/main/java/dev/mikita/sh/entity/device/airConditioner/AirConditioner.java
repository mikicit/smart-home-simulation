package dev.mikita.sh.entity.device.airConditioner;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.airConditioner.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

import java.util.Objects;

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
        Objects.requireNonNull(inhabitant);

        if (!isUsing() && !isBroken()) {
            setUser(inhabitant);
            inhabitant.useObject(this);
            changeState(new AirConditionerUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            inhabitant.unUseObject(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        Objects.requireNonNull(person);

        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new AirConditionerFixingState(this));
        }
    }

    @Override
    public void toBeBroken(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isBroken()) {
            inhabitant.toBreakDevice(this);
            changeState(new AirConditionerBrokenState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
