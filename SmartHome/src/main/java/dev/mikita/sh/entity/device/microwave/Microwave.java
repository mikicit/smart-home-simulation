package dev.mikita.sh.entity.device.microwave;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.microwave.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

import java.util.Objects;

public class Microwave extends ADevice {
    public Microwave(Room room, String name) {
        super(room, name);
        this.state = new MicrowaveIdleState(this);
        this.operatingTimeInHours = 600;
        this.usageTimeInHour = 0.084;
        this.hungerPerHour = 25;
        this.leisurePerHour = 12;
    }

    @Override
    public void on() {
        if (isOff()) {
            changeState(new MicrowaveIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new MicrowaveOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isUsing() && !isBroken()) {
            setUser(inhabitant);
            inhabitant.useObject(this);
            changeState(new MicrowaveUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            inhabitant.unUseObject(this);
            changeState(new MicrowaveIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        Objects.requireNonNull(person);

        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new MicrowaveFixingState(this));
        }
    }

    @Override
    public void toBeBroken(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isBroken()) {
            inhabitant.toBreakDevice(this);
            changeState(new MicrowaveBrokenState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
