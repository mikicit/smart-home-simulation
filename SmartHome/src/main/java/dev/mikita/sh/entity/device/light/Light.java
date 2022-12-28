package dev.mikita.sh.entity.device.light;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.light.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

import java.util.Objects;

public class Light extends ADevice {
    public Light(Room room, String name) {
        super(room, name);
        this.operatingTimeInHours = 500;
        this.usageTimeInHour = 0;
        this.hungerPerHour = 0;
        this.leisurePerHour = 0;
    }

    @Override
    public void on() {
        if (isOff()) {
            changeState(new LightIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new LightOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isUsing() && !isBroken()) {
            setUser(inhabitant);
            inhabitant.useObject(this);
            changeState(new LightUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            inhabitant.unUseObject(this);
            changeState(new LightIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        Objects.requireNonNull(person);

        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new LightFixingState(this));
        }
    }

    @Override
    public void toBeBroken(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isBroken()) {
            inhabitant.toBreakDevice(this);
            changeState(new LightBrokenState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
