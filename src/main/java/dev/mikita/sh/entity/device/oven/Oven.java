package dev.mikita.sh.entity.device.oven;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.oven.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the Oven.
 */
public class Oven extends ADevice {
    /**
     * Instantiates a new Oven.
     *
     * @param room the room
     * @param name the name
     */
    public Oven(Room room, String name) {
        super(room, name);
        this.state = new OvenIdleState(this);
        this.fixingTimeInHours = 1;
        this.operatingTimeInHours = 1000;
        this.usageTimeInHour = 1;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the oven on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new OvenIdleState(this));
        }
    }

    /**
     * Turns the oven off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new OvenOffState(this));
        }
    }

    /**
     * Use the oven.
     * @param inhabitant inhabitant that uses object
     */
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

    /**
     * UnUse the oven.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
         if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
             ((Adult) inhabitant).unUseObject(this);
            changeState(new OvenIdleState(this));
        }
    }

    /**
     * To fix the oven.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new OvenFixingState(this));
        }
    }

    /**
     * Complete fixing the oven.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new OvenIdleState(this));
        }
    }

    /**
     * To break the oven.
     */
    @Override
    public void toBreak() {
        changeState(new OvenBrokenState(this));
    }

    /**
     * Update.
     * @param time the time
     */
    @Override
    public void update(long time) {
        this.time += time;
        state.update(time);
    }
}
