package dev.mikita.sh.entity.device;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.time.ITimeTracker;
import dev.mikita.sh.entity.UsableObject;
import dev.mikita.sh.entity.inhabitant.AInhabitant;
import dev.mikita.sh.entity.inhabitant.person.adult.Adult;
import dev.mikita.sh.entity.location.Room;

public abstract class ADevice implements ITimeTracker, IEventSource, UsableObject {
    protected Room room;
    protected ADeviceState state;
    protected String name;
    protected long time = 0;

    // Constants
    protected int operatingTimeInHours = 0;
    protected double usageTimeInHour = 0;
    protected int hungerPerHour = 0;
    protected int leisurePerHour = 0;

    // Consumption
    protected double currentElectricityConsumption = 0;
    protected double currentWaterConsumption = 0;
    protected double currentGasConsumption = 0;
    protected double lastElectricityConsumption = 0;
    protected double lastWaterConsumption = 0;
    protected double lastGasConsumption = 0;

    public ADevice(Room room, String name) {
        this.room = room;
        this.name = name;

        // Init
        SHSystem.getInstance().getTimer().subscribe(this);
    }

    public void changeState(ADeviceState state) {
        this.state = state;
    }

    public Room getRoom() {
        return room;
    }

    public double getCurrentElectricityConsumption() {
        return currentElectricityConsumption;
    }

    public void setCurrentElectricityConsumption(double consumption) {
        currentElectricityConsumption = consumption;
    }

    public double getCurrentWaterConsumption() {
        return currentWaterConsumption;
    }

    public void setCurrentWaterConsumption(double consumption) {
        currentWaterConsumption = consumption;
    }

    public double getCurrentGasConsumption() {
        return currentGasConsumption;
    }

    public void setCurrentGasConsumption(double consumption) {
        currentGasConsumption = consumption;
    }

    public double calculateElectricityConsumption() {
        double result = currentElectricityConsumption - lastElectricityConsumption;
        lastElectricityConsumption = currentElectricityConsumption;

        return result;
    }

    public double calculateWaterConsumption() {
        double result = currentWaterConsumption - lastWaterConsumption;
        lastWaterConsumption = currentWaterConsumption;

        return result;
    }

    public double calculateGasConsumption() {
        double result = currentGasConsumption - lastGasConsumption;
        lastGasConsumption = currentGasConsumption;

        return result;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ADeviceState getState() {
        return state;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getOperatingTimeInHours() {
        return operatingTimeInHours;
    }

    @Override
    public double getUsageTimeInHour() {
        return usageTimeInHour;
    }

    @Override
    public int getHungerPerHour() {
        return hungerPerHour;
    }

    @Override
    public int getLeisurePerHour() {
        return leisurePerHour;
    }

    @Override
    public void use(AInhabitant inhabitant) {
//        System.out.println(inhabitant.getName() + " юзает девайс " + name);
    }

    @Override
    public void unUse(AInhabitant inhabitant) {
//        System.out.println(inhabitant.getName() + " перестает юзать девайс " + name);
    }

    @Override
    public boolean isUsing() {
        return state instanceof ADeviceUsingState;
    }

    // TODO change to abstract
    public void fix(Adult person) {

    }
}
