package dev.mikita.sh.entity.location.atmosphere;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.ITimeTracker;

public abstract class AAtmosphere implements ITimeTracker {
    public AAtmosphere() {
        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    protected double temperature;

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
