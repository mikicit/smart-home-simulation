package dev.mikita.sh.entity.sensor;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.IEventSource;
import dev.mikita.sh.core.simulation.ITimeTracker;

/**
 * Abstract class representing a sensor.
 */
public abstract class ASensor implements ITimeTracker, IEventSource {
    protected long time = 0;

    public ASensor() {
        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    /**
     * Returns a name.
     * @return the name
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Switches a state.
     */
    protected abstract void switchState();

    /**
     * Resets a state.
     */
    public abstract void resetState();
}
