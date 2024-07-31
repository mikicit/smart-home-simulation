package dev.mikita.sh.entity.device.microwave;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.microwave.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the Microwave.
 */
public class Microwave extends ADevice {
    /**
     * Instantiates a new Microwave.
     *
     * @param room the room
     * @param name the name
     */
    public Microwave(Room room, String name) {
        super(room, name);
        this.state = new MicrowaveIdleState(this);
        this.fixingTimeInHours = 1;
        this.operatingTimeInHours = 2500;
        this.usageTimeInHour = 0.084;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the microwave on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new MicrowaveIdleState(this));
        }
    }

    /**
     * Turns the microwave off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new MicrowaveOffState(this));
        }
    }

    /**
     * Use the microwave.
     * @param inhabitant inhabitant that uses object
     */
    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new MicrowaveBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            ((Adult) inhabitant).useObject(this);
            changeState(new MicrowaveUsingState(this));
        }
    }

    /**
     * UnUse the microwave.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            ((Adult) inhabitant).unUseObject(this);
            changeState(new MicrowaveIdleState(this));
        }
    }

    /**
     * To fix the microwave.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new MicrowaveFixingState(this));
        }
    }

    /**
     * To complete fixing the microwave.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new MicrowaveIdleState(this));
        }
    }

    /**
     * To break the microwave.
     */
    @Override
    public void toBreak() {
        changeState(new MicrowaveBrokenState(this));
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
