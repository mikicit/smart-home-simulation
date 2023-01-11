package dev.mikita.sh.entity.device.airConditioner;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.airConditioner.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the Air conditioner.
 */
public class AirConditioner extends ADevice {
    // Constants
    private final double COOLING_PER_HOUR = 3;

    /**
     * Instantiates a new Air conditioner.
     *
     * @param room the room
     * @param name the name
     */
    public AirConditioner(Room room, String name) {
        super(room, name);
        this.state = new AirConditionerIdleState(this);
        this.fixingTimeInHours = 0.5;
        this.operatingTimeInHours = 1500;
        this.usageTimeInHour = 2;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Gets cooling per hour.
     *
     * @return the cooling per hour
     */
    public double getCoolingPerHour() {
        return COOLING_PER_HOUR;
    }

    /**
     * Turns the air conditioner on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new AirConditionerIdleState(this));
        }
    }

    /**
     * Turns the air conditioner off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new AirConditionerOffState(this));
        }
    }

    /**
     * Use air conditioner.
     * @param inhabitant inhabitant that uses object
     */
    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new AirConditionerBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            ((Adult) inhabitant).useObject(this);
            changeState(new AirConditionerUsingState(this));
        }
    }

    /**
     * UnUse air conditioner.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            ((Adult) inhabitant).unUseObject(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    /**
     * Fix air conditioner.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new AirConditionerFixingState(this));
        }
    }

    /**
     * Complete fixing air conditioner.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new AirConditionerIdleState(this));
        }
    }

    /**
     * To break air conditioner.
     */
    @Override
    public void toBreak() {
        changeState(new AirConditionerBrokenState(this));
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
