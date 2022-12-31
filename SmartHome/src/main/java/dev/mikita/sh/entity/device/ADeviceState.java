package dev.mikita.sh.entity.device;

/**
 * Abstract class representing device's state
 */
public abstract class ADeviceState {
    /**
     * The Time.
     */
    protected long time;
    /**
     * The Device.
     */
    protected ADevice device;

    /**
     * The Electricity consumption.
     */
    protected double ELECTRICITY_CONSUMPTION = 0;
    /**
     * The Water consumption.
     */
    protected double WATER_CONSUMPTION = 0;
    /**
     * The Gas consumption.
     */
    protected double GAS_CONSUMPTION = 0;

    /**
     * Instantiates a new A device state.
     *
     * @param device the device
     */
    public ADeviceState(ADevice device) {
        this.device = device;
    }

    /**
     * Gets electricity consumption.
     *
     * @return the electricity consumption
     */
    public double getElectricityConsumption() {
        return ELECTRICITY_CONSUMPTION;
    }

    /**
     * Gets water consumption.
     *
     * @return the water consumption
     */
    public double getWaterConsumption() {
        return WATER_CONSUMPTION;
    }

    /**
     * Gets gas consumption.
     *
     * @return the gas consumption
     */
    public double getGasConsumption() {
        return GAS_CONSUMPTION;
    }

    /**
     * Update.
     *
     * @param time the time
     */
    public abstract void update(long time);
}
