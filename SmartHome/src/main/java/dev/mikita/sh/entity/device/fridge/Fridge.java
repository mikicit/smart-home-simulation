package dev.mikita.sh.entity.device.fridge;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.fridge.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

import java.util.Objects;

public class Fridge extends ADevice {
    public Fridge(Room room, String name) {
        super(room, name);
        this.state = new FridgeIdleState(this);
        this.operatingTimeInHours = 376;
        this.usageTimeInHour = 0.25;
        this.hungerPerHour = 35;
        this.leisurePerHour = 15;
    }

    @Override
    public void on() {
        if (isOff()) {
            changeState(new FridgeIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new FridgeOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (!isUsing() && !isBroken()) {
            setUser(inhabitant);
            inhabitant.useObject(this);
            changeState(new FridgeUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            inhabitant.unUseObject(this);
            changeState(new FridgeIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        Objects.requireNonNull(person);

        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new FridgeFixingState(this));
        }
    }

    @Override
    public void toBeBroken(AInhabitant inhabitant) {
        Objects.requireNonNull(inhabitant);

        inhabitant.toBreakDevice(this);
        changeState(new FridgeBrokenState(this));
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
