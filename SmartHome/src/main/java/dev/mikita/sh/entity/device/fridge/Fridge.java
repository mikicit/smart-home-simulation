package dev.mikita.sh.entity.device.fridge;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.fridge.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the Fridge.
 */
public class Fridge extends ADevice {
    /**
     * Instantiates a new Fridge.
     *
     * @param room the room
     * @param name the name
     */
    public Fridge(Room room, String name) {
        super(room, name);
        this.state = new FridgeIdleState(this);
        this.fixingTimeInHours = 1;
        this.operatingTimeInHours = 2500;
        this.usageTimeInHour = 0.25;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the fridge on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new FridgeIdleState(this));
        }
    }

    /**
     * Turns the fridge off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new FridgeOffState(this));
        }
    }

    /**
     * Use the fridge.
     * @param inhabitant inhabitant that uses object
     */
    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new FridgeBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            ((Adult) inhabitant).useObject(this);
            changeState(new FridgeUsingState(this));
        }
    }

    /**
     * UnUse the fridge.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            ((Adult) inhabitant).unUseObject(this);
            changeState(new FridgeIdleState(this));
        }
    }

    /**
     * Fix the fridge.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new FridgeFixingState(this));
        }
    }

    /**
     * Complete fixing the fridge.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new FridgeIdleState(this));
        }
    }

    /**
     * To break the fridge.
     */
    @Override
    public void toBreak() {
        changeState(new FridgeBrokenState(this));
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
