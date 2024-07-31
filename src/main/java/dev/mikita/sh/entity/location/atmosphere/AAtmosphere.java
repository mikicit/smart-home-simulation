package dev.mikita.sh.entity.location.atmosphere;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.ITimeTracker;

/**
 * Abstract class representing atmosphere.
 */
public abstract class AAtmosphere implements ITimeTracker {
    public AAtmosphere() {
        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    protected double temperature;

    /**
     * Returns the temperature.
     * @return temperature
     */
    public double getTemperature() {
        return this.temperature;
    }

    /**
     * Sets the temperature.
     * @param temperature the temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
