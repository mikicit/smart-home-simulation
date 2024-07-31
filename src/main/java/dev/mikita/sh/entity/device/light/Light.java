package dev.mikita.sh.entity.device.light;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.light.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the Light.
 */
public class Light extends ADevice {
    /**
     * Instantiates a new Light
     *
     * @param room the room
     * @param name the name
     */
    public Light(Room room, String name) {
        super(room, name);
        this.state = new LightIdleState(this);
        this.fixingTimeInHours = 0.5;
        this.operatingTimeInHours = 2000;
        this.usageTimeInHour = 0;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the light on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new LightIdleState(this));
        }
    }

    /**
     * Turns the light off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new LightOffState(this));
        }
    }

    /**
     * Use the light.
     * @param inhabitant inhabitant that uses object
     */
    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new LightBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            ((Adult) inhabitant).useObject(this);
            changeState(new LightUsingState(this));
        }
    }

    /**
     * UnUse the light.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            ((Adult) inhabitant).unUseObject(this);
            changeState(new LightIdleState(this));
        }
    }

    /**
     * Fix the light.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new LightFixingState(this));
        }
    }

    /**
     * Complete fixing the light.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new LightIdleState(this));
        }
    }

    /**
     * To break the light.
     */
    @Override
    public void toBreak() {
        changeState(new LightBrokenState(this));
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
