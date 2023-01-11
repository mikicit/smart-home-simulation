package dev.mikita.sh.entity.device.tv;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.tv.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the TV.
 */
public class TV extends ADevice {
    /**
     * Instantiates a new Tv
     *
     * @param room the room
     * @param name the name
     */
    public TV(Room room, String name) {
        super(room, name);
        this.state = new TVIdleState(this);
        this.fixingTimeInHours = 1.5;
        this.operatingTimeInHours = 2500;
        this.usageTimeInHour = 1;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the TV on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new TVIdleState(this));
        }
    }

    /**
     * Turns the TV off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new TVOffState(this));
        }
    }

    /**
     * Use the TV.
     * @param inhabitant inhabitant that uses object
     */
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

    /**
     * UnUse the TV.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            ((Adult) inhabitant).unUseObject(this);
            changeState(new TVIdleState(this));
        }
    }

    /**
     * To fix the TV.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new TVFixingState(this));
        }
    }

    /**
     * Complete fixing the TV.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new TVIdleState(this));
        }
    }

    /**
     * To break the TV.
     */
    @Override
    public void toBreak() {
        changeState(new TVBrokenState(this));
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
