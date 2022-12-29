package dev.mikita.sh.entity.device.tv;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.tv.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class TV extends ADevice {
    public TV(Room room, String name) {
        super(room, name);
        this.state = new TVIdleState(this);
        this.fixingTimeInHours = 4;
        this.operatingTimeInHours = 1000;
        this.usageTimeInHour = 1;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    @Override
    public void on() {
        if (isOff()) {
            changeState(new TVIdleState(this));
        }
    }

    @Override
    public void off() {
        if (isOn()) {
            changeState(new TVOffState(this));
        }
    }

    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new TVBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            ((Adult) inhabitant).useObject(this);
            changeState(new TVUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            ((Adult) inhabitant).unUseObject(this);
            changeState(new TVIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new TVFixingState(this));
        }
    }

    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new TVIdleState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
