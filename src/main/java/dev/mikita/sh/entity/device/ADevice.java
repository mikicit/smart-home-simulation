package dev.mikita.sh.entity.device;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.simulation.ITimeTracker;
import dev.mikita.sh.entity.IUsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

/**
 * Abstract class representing device.
 */
public abstract class ADevice implements ITimeTracker, IEventSource, IUsableObject {
    protected Room room;
    protected ADeviceState state;
    protected String name;
    protected AInhabitant user = null;
    protected long time = 0;

// Constants
    protected int operatingTimeInHours = 0;
    protected double usageTimeInHour = 0;
    protected Documentation doc;

// Consumption
    protected double currentElectricityConsumption = 0;
    protected double currentWaterConsumption = 0;
    protected double currentGasConsumption = 0;
    protected double lastElectricityConsumption = 0;
    protected double lastWaterConsumption = 0;
    protected double lastGasConsumption = 0;
    protected double fixingTimeInHours = 0;

    /**
     * Instantiates a new device.
     *
     * @param room the room
     * @param name the name
     */
    public ADevice(Room room, String name) {
        this.room = room;
        this.name = name;

        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    /**
     * Changes device's state (Using, Fixing, Broken, Idle, On, Off).
     *
     * @param state state to apply
     */
    public void changeState(ADeviceState state) {
        this.state = state;
    }

    /**
     * Returns the room in which device is located.
     *
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Returns device's documentation.
     *
     * @return documentation documentation
     */
    public Documentation getDocumentation() {
        return doc;
    }

    /**
     * Returns current electricity consumption.
     *
     * @return consumption current electricity consumption
     */
    public double getCurrentElectricityConsumption() {
        return currentElectricityConsumption;
    }

    /**
     * Sets electricity consumption.
     *
     * @param consumption consumption to set
     */
    public void setCurrentElectricityConsumption(double consumption) {
        currentElectricityConsumption = consumption;
    }

    /**
     * Returns current water consumption.
     *
     * @return consumption current water consumption
     */
    public double getCurrentWaterConsumption() {
        return currentWaterConsumption;
    }

    /**
     * Sets water consumption.
     *
     * @param consumption consumption to set
     */
    public void setCurrentWaterConsumption(double consumption) {
        currentWaterConsumption = consumption;
    }

    /**
     * Returns current gas consumption.
     *
     * @return consumption current gas consumption
     */
    public double getCurrentGasConsumption() {
        return currentGasConsumption;
    }

    /**
     * Sets gas consumption.
     *
     * @param consumption consumption to set
     */
    public void setCurrentGasConsumption(double consumption) {
        currentGasConsumption = consumption;
    }

    /**
     * Calculates electricity consumption.
     *
     * @return consumption double
     */
    public double calculateElectricityConsumption() {
        double result = currentElectricityConsumption - lastElectricityConsumption;
        lastElectricityConsumption = currentElectricityConsumption;

        return result;
    }

    /**
     * Calculates water consumption.
     *
     * @return consumption double
     */
    public double calculateWaterConsumption() {
        double result = currentWaterConsumption - lastWaterConsumption;
        lastWaterConsumption = currentWaterConsumption;

        return result;
    }

    /**
     * Calculates gas consumption.
     *
     * @return consumption double
     */
    public double calculateGasConsumption() {
        double result = currentGasConsumption - lastGasConsumption;
        lastGasConsumption = currentGasConsumption;

        return result;
    }

    /**
     * Returns current device's time.
     *
     * @return time
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets device's time.
     *
     * @param time time to set
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Returns time that device can be used before breaking.
     * @return time
     */
    public int getOperatingTimeInHours() {
        return operatingTimeInHours;
    }

    /**
     * Returns time which is needed to fix the device.
     *
     * @return time to fix
     */
    public double getFixingTimeInHours() {
        return fixingTimeInHours;
    }

    /**
     * Returns device's current state.
     *
     * @return state
     */
    public ADeviceState getState() {
        return state;
    }

    /**
     * Returns inhabitant that is currently using the device.
     *
     * @return user user
     */
    public AInhabitant getUser() {
        return user;
    }

    /**
     * Sets a new user for the device.
     *
     * @param inhabitant user to set
     */
    public void setUser(AInhabitant inhabitant) {
        user = inhabitant;
    }

    /**
     * Checks if device is turned on.
     * @return true if turned on
     */
    public boolean isOn() {
        return state instanceof ADeviceIdleState;
    }

    /**
     * Checks if device is turned off.
     * @return true if turned off
     */
    public boolean isOff() {
        return state instanceof ADeviceOffState;
    }

    /**
     * Checks if device is broken.
     * @return true if broken
     */
    public boolean isBroken() {
        return state instanceof ADeviceBrokenState;
    }

    /**
     * Checks if device is being fixed.
     * @return true if being fixed
     */
    public boolean isFixing() {
        return state instanceof ADeviceFixingState;
    }

    /**
     * Returns device's name (Fridge, Oven, etc.).
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns time that device is supposed to be used.
     * @return time
     */
    @Override
    public double getUsageTimeInHour() {
        return usageTimeInHour;
    }

    /**
     * Checks if device is being used.
     * @return true if being used
     */
    @Override
    public boolean isUsing() {
        return state instanceof ADeviceUsingState;
    }

    /**
     * Turns the device on.
     */
    public abstract void on();

    /**
     * Turns the device off.
     */
    public abstract void off();

    /**
     * Fix device.
     *
     * @param person person that is fixing the device
     */
    public abstract void fix(Adult person);

    /**
     * Complete fixing the device.
     *
     * @param person person that is fixing the device
     */
    public abstract void completeFixing(Adult person);

    /**
     * Break the device.
     */
    public abstract void toBreak();
}
