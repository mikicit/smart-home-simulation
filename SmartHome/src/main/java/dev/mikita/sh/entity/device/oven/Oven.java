package dev.mikita.sh.entity.device.oven;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.oven.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public class Oven extends ADevice {
    public Oven(Room room, String name) {
        super(room, name);
        this.state = new OvenIdleState(this);
        this.fixingTimeInHours = 2;
        this.operatingTimeInHours = 700;
        this.usageTimeInHour = 1;
        this.doc = new Documentation(this, this.fixingTimeInHours);
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
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new OvenBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            ((Adult) inhabitant).useObject(this);
            changeState(new OvenUsingState(this));
        }
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
         if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
             ((Adult) inhabitant).unUseObject(this);
            changeState(new OvenIdleState(this));
        }
    }

    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new OvenFixingState(this));
        }
    }

    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new OvenIdleState(this));
        }
    }

    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
