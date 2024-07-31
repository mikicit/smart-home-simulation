package dev.mikita.sh.entity.device.washingMachine;

import dev.mikita.sh.entity.device.ADevice;
import dev.mikita.sh.entity.device.Documentation;
import dev.mikita.sh.entity.device.washingMachine.state.*;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Class representing the Washing machine.
 */
public class WashingMachine extends ADevice {
    /**
     * Instantiates a new Washing machine.
     *
     * @param room the room
     * @param name the name
     */
    public WashingMachine(Room room, String name) {
        super(room, name);
        this.state = new WashingMachineIdleState(this);
        this.fixingTimeInHours = 1;
        this.operatingTimeInHours = 2000;
        this.usageTimeInHour = 1;
        this.doc = new Documentation(this, this.fixingTimeInHours);
    }

    /**
     * Turns the washing machine on.
     */
    @Override
    public void on() {
        if (isOff()) {
            changeState(new WashingMachineIdleState(this));
        }
    }

    /**
     * Turns the washing machine off.
     */
    @Override
    public void off() {
        if (isOn()) {
            changeState(new WashingMachineOffState(this));
        }
    }

    /**
     * Use the washing machine.
     * @param inhabitant inhabitant that uses object
     */
    @Override
    public void use(AInhabitant inhabitant) {
        if (!isUsing() && !isBroken()) {
            if (Math.random() <= inhabitant.getDeviceBreakingChance()) {
                changeState(new WashingMachineBrokenState(this));
                return;
            }

            if (!(inhabitant instanceof Adult)) return;

            setUser(inhabitant);
            ((Adult) inhabitant).useObject(this);
            changeState(new WashingMachineUsingState(this));
        }
    }

    /**
     * UnUse the washing machine.
     * @param inhabitant inhabitant that unUses object
     */
    @Override
    public void unUse(AInhabitant inhabitant) {
        if (isUsing() && inhabitant.equals(getUser())) {
            setUser(null);
            ((Adult) inhabitant).unUseObject(this);
            changeState(new WashingMachineIdleState(this));
        }
    }

    /**
     * To fix the washing machine.
     * @param person person that is fixing the device
     */
    @Override
    public void fix(Adult person) {
        if (isBroken()) {
            setUser(person);
            person.fixDevice(this);
            changeState(new WashingMachineFixingState(this));
        }
    }

    /**
     * Complete fixing the washing machine.
     * @param person person that is fixing the device
     */
    @Override
    public void completeFixing(Adult person) {
        if (isFixing() && person.equals(getUser())) {
            setUser(null);
            person.completeFixingDevice(this);
            changeState(new WashingMachineIdleState(this));
        }
    }

    /**
     * To break the washing machine.
     */
    @Override
    public void toBreak() {
        changeState(new WashingMachineBrokenState(this));
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
