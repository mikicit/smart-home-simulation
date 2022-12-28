package dev.mikita.sh.entity.device.oven;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.oven.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

import java.util.Objects;

public class Oven extends ADevice {
    public Oven(Room room, String name) {
        super(room, name);
        this.state = new OvenIdleState(this);
        this.operatingTimeInHours = 700;
        this.usageTimeInHour = 1;
        this.hungerPerHour = 25;
        this.leisurePerHour = 11;
    }

    @Override
    public void on() {
        if (isOff()) {
            changeState(new OvenIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new OvenOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isUsing() && !isBroken()) {
            setUser(inhabitant);
            inhabitant.useObject(this);
            changeState(new OvenUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            inhabitant.unUseObject(this);
            changeState(new OvenIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        Objects.requireNonNull(person);

        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new OvenFixingState(this));
        }
    }

    @Override
    public void toBeBroken(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isBroken()) {
            inhabitant.toBreakDevice(this);
            changeState(new OvenBrokenState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
